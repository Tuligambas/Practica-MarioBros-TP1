package tp1.logic.gameobjects;

import tp1.logic.Position;

public class ExitDoor {
  private Position pos;
  private boolean isAlive;
  private boolean isSolid;

  public ExitDoor(Position position) {
    this.pos = position;
    this.isAlive = true;
    this.isSolid = false;
  }

  public boolean isAlive() { return this.isAlive; }
  public String getIcon() { return "🚪"; }
  public boolean isInPosition(Position p) {
    return this.pos.equals(p);
  }
}
