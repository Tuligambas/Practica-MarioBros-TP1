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
  private ActionList actionList;

  public Mario(Position position, Game game) {
    super(position, game);
    this.big = false;
    this.dir = Action.RIGHT;
    this.isAlive = true;
    this.actionList = new ActionList();
  }

  // CONSTRUCTOR VACIO
  public Mario() {
  }

  @Override
  public void update() {
    checkInteractions();
    if (actionList.isEmpty()) {
      automaticMovement();
    } else {
      commandMovement();
    }
    // chequea si esta en una posicion valida
    checkPosition();

  }

  private void commandMovement() {
    Position initPos = this.pos;
    while (!actionList.isEmpty()) {
      Action action = actionList.getNext(); // método auxiliar que devuelve y elimina

      // cambia de direccion
      if (action == Action.LEFT || action == Action.RIGHT || action == Action.STOP)
        this.dir = action;

      // calculo la siguiente posicion y si no es solido ni pared se mueve
      Position next = this.pos.move(action);
      if (!solidNextTo(action) && !wallNextTo(action))
        this.pos = next;
    }

    if (initPos.equals(this.pos))
      automaticMovement();
  }

  public void automaticMovement() {

    // si es solido abajo dos opciones
    if (solidBelow()) {
      // si es solido a la izquierda o derecha cambia de direccion
      if (solidNextTo(dir) || wallNextTo(dir))
        dir = dir.opposite(dir);

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

  public void makeBig() {
    this.big = true;
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
    if (this.big) {
      icon = icon.toUpperCase();
    }
    return icon;
  }

  // MÉTODO QUE DICE SI EL MARIO ESTÁ EN EL AIRE
  public boolean isInAir() {
    Position abajo = new Position(this.pos.getCol(), this.pos.getRow() + 1);
    boolean aire = false;
    if (!game.isSolid(abajo) && pos.isInBoard()) { // Si no tiene pared abajo y la posición está en el tablero, estará
                                                   // en el aire
      aire = true;

    } else if (!pos.isInBoard()) { // Si la posición no está en el tablero, mata al mario
      this.isAlive = false;
      game.marioWasKilled();
    }
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

  // MÉTODOS PARA LAS INTERACCIONES
  // @Override
  // public boolean receiveInteraction(GameItem other) {
  // return this.role.receiveInteraction(other, this);
  // }

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
    boolean onTop = this.pos.equals(goomba.getPos().move(Action.UP));
    if (onTop) {
      killGoomba(goomba);
    } else if (this.pos.equals(goomba.getPos())) {
      killGoomba(goomba);
      marioGetAttacked();
    }
    return onTop;
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

  /*
   * 
   * 
   * 
   * 
   * @Override
   * public boolean interactWith(Lemming lemming) {
   * return this.role.interactWith(lemming, this);
   * }
   * 
   * public boolean checkInteractions() {
   * return game.receiveInteractionsFrom(this);
   * }
   */

}
