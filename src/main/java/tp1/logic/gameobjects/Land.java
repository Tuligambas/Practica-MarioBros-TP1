package tp1.logic.gameobjects;

import tp1.logic.Position;

public class Land {
  private Position pos;
  private boolean isAlive;

  public Land(Position pos) {
    this.pos = pos;
    this.isAlive = true;
  }

  public String getIcon() { return "▓▓▓▓"; }
  public boolean isAlive() { return this.isAlive; }

  public boolean isInPosition(Position p) { return this.pos.equals(p); }
}
