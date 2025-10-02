package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class Land {
  private Position pos;
  private boolean isAlive;

  public Land(Position pos) {
    this.pos = pos;
    this.isAlive = true;
  }

  public String getIcon() {
    return Messages.LAND;
  }

  public boolean isAlive() {
    return this.isAlive;
  }

  public boolean isInPosition(Position p) {
    return this.pos.equals(p);
  }
}
