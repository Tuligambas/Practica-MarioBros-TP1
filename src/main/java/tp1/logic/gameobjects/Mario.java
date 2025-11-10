package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario extends MovingObject {
  private boolean big;
  private boolean isAlive;
  private Position prevPosition;
  private ActionList actionList;

  public Mario(Position position, GameWorld game, boolean big) {
    super(position, game, false, Action.RIGHT);
    this.big = big;
    this.isAlive = true;
    this.actionList = new ActionList();
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
      this.isfalling = false;
    }
  }

  @Override
  public boolean interactWith(GameItem item) {
    boolean canInteract = item.isInPosition(this.pos);
    if (canInteract) {
      return item.receiveInteraction(this);
    }
    return false;
  }

  @Override
  public boolean receiveInteraction(Goomba goomba) {
    if (samePos(goomba) || goombaAgainstYou(goomba)) {
      marioGetAttacked();
      return true;
    }
    return false;

  }

  private boolean goombaAgainstYou(Goomba goomba) {
    return goomba.getPos().move(goomba.geAction()).equals(pos);
  }

  private boolean samePos(Goomba g) {
    return pos.equals(g.getPos()) || pos.move(Action.UP).equals(g.getPos());
  }

  public boolean goombaOnYou(Goomba g) {
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
  protected void checkPosition() {
    if (!this.pos.isInBoard()) {
      this.big = false; // ayuda con test
      game.looseLife();
    }
  }

}
