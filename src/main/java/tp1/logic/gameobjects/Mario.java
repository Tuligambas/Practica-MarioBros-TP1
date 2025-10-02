package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario extends GameObject {
  private boolean big;
  private Action dir;
  private boolean isAlive;

  public Mario(Position position, Game game) {
    super(position, game);
    this.big = false;
    this.dir = Action.RIGHT;
    this.isAlive = true;
  }

  // CONSTRUCTOR VACIO
  public Mario() {
  }

  public void update() {
    // TODO fill your code
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
  public boolean isSolid() {
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
      default:
    }
    if (this.big) {
      icon = icon.toUpperCase();
    }
    return icon;
  }

}
