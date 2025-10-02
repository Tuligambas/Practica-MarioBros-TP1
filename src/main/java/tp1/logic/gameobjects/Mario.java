package tp1.logic.gameobjects;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario {

  private Game game;
  private Position pos;
  private boolean isAlive;
  private boolean isSolid;
  private boolean big;
  private Direction dir = Direction.RIGHT;

  public Mario(Game game, Position position) {
    this.game = game;
    this.pos = position;
    this.isAlive = true;
    this.isSolid = false;
    this.big = false;
  }

  // CONSTRUCTOR VACIO
  public Mario() {
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

  public void update() {
    // TODO fill your code
  }

  public boolean isAlive() {
    return this.isAlive;
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

  public void makeBig() {
    this.big = true;
  }
}
