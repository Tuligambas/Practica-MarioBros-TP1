package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario extends GameObject {
  private boolean big;
  private Action dir;
  private boolean isAlive;
  private boolean falling;
  private Position prevPosition;
  private ActionList actionList;

  public Mario(Position position, Game game, boolean big) {
    super(position, game);
    this.big = false;
    this.dir = Action.RIGHT;
    this.isAlive = true;
    this.actionList = new ActionList();
    this.big = big;
  }

  // CONSTRUCTOR VACIO
  public Mario() {
  }

  @Override
  public void update() {
    setPrevPosition(); // simplifica colisiones e interacciones
    if (actionList.isEmpty()) {
      automaticMovement();
    } else {
      commandMovement();
    }
    checkPosition(); // chequea si esta en una posicion valida
    checkInteractions(); // interactua con otros objetos if()
  }

  private void setPrevPosition() {
    if (dir != Action.STOP)
      this.prevPosition = new Position(this.pos.getCol(), this.pos.getRow());
  }

  private void commandMovement() {
    while (!actionList.isEmpty()) {
      Action action = actionList.getNext(); // método auxiliar que devuelve y elimina

      // cambia de direccion
      if (action.isHorizontal() || action == Action.STOP)
        this.dir = action;

      if (action == Action.DOWN) {
        if (solidBelow())
          this.dir = Action.STOP;
        else
          toTheFloor();
      } else if (action != Action.STOP) {
        if (solidNextTo(action) || wallNextTo(action))
          this.dir = action.opposite();
        else {
          Position next = this.pos.move(action);
          this.prevPosition = new Position(this.pos.getCol(), this.pos.getRow());
          this.pos = next;
        }
      }
    }

    if (this.prevPosition.equals(this.pos))
      automaticMovement();
  }

  private void toTheFloor() {
    while (!solidBelow() && pos.isInBoard()) {
      setPrevPosition();
      this.pos = this.pos.move(Action.DOWN);
    }

  }

  public void automaticMovement() {
    if (solidBelow()) {// si es solido abajo dos opciones
      if (solidNextTo(dir) || wallNextTo(dir)) // si es solido a la izquierda o derecha cambia de direccion
        dir = dir.opposite();

      // si no es solido a la izquierda o derecha se mueve
      else {
        move();
      }
    }
    // si no es solido abajo cae
    else {
      this.falling = true;
      fall();
    }
  }

  private void move() {
    this.pos = this.pos.move(this.dir);
  }

  protected boolean solidNextTo(Action dir) {
    Position next = this.pos.move(dir);
    Position next_big = this.pos.move(dir).move(Action.UP);
    return this.game.isSolid(next) || game.isSolid(next_big);
  }

  public boolean isInPosition(Position p) {
    if (this.pos.equals(p)) {
      return true;
    }
    if (this.big) {
      // casilla justo arriba (misma columna, fila - 1)
      Position above = new Position(pos.getCol(), pos.getRow() - 1);
      return above.equals(p);
    }
    return false;
  }

  @Override
  public boolean isAlive() {
    return isAlive;
  }

  public String getIcon() {
    String icon = " ";
    switch (this.dir) {
      case LEFT:
        icon = Messages.MARIO_LEFT;
        break;
      case RIGHT:
        icon = Messages.MARIO_RIGHT;
        break;
      case STOP:
        icon = Messages.MARIO_STOP;
        break;
      default:
    }
    if (this.big)
      icon = icon.toUpperCase();

    return icon;
  }

  // MÉTODO QUE DICE SI EL MARIO ESTÁ EN EL AIRE
  public boolean isInAir() {
    Position abajo = new Position(this.pos.getCol(), this.pos.getRow() + 1);
    boolean aire = false;

    if (!game.isSolid(abajo) && pos.isInBoard())
      aire = true;

    return aire;
  }

  @Override
  public void addAction(List<Action> actionList) {
    this.actionList = new ActionList();
    for (Action a : actionList) {
      this.actionList.addAction(a);
    }
  }

  private void fall() {
    this.pos = this.pos.move(Action.DOWN);
    if (solidBelow()) {
      this.falling = false;
    }
  }

  public boolean checkInteractions() {
    return game.receiveInteractionsFrom(this);
  }

  @Override
  public boolean interactWith(ExitDoor door) {
    boolean interact = this.pos.equals(door.getPos());
    if (interact)
      game.marioExited();
    return interact;
  }

  @Override
  public boolean interactWith(Goomba goomba) {
    boolean onTop = this.prevPosition.equals(goomba.getPos().move(Action.UP));
    if (onTop) {
      killGoomba(goomba);
    } else if (samePos(goomba) || goombaOnYou(goomba) || goombaAgainstYou(goomba)) {
      killGoomba(goomba);
      marioGetAttacked();
    }
    return onTop;
  }

  private boolean goombaAgainstYou(Goomba goomba) {
    return goomba.getPos().move(goomba.geAction()).equals(pos);
  }

  private boolean samePos(Goomba g) {
    return pos.equals(g.getPos()) || pos.move(Action.UP).equals(g.getPos());
  }

  private boolean goombaOnYou(Goomba g) {
    return prevPosition.equals(g.getPos()) || prevPosition.move(Action.UP).equals(g.getPos());
  }

  private void marioGetAttacked() {
    if (this.big) {
      this.big = false;
    } else {
      game.looseLife();
    }
  }

  private void killGoomba(Goomba goomba) {
    goomba.setAlive(false);
    game.goombaWasKilled();
  }

  @Override
  public void makeBig() {
    this.big = true;
  }

  @Override
  protected void checkPosition() {
    if (!this.pos.isInBoard()) {
      this.big = false; // ayuda con test
      game.looseLife();
    }
  }

}
