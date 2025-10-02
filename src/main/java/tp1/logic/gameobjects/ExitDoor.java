package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor {
  private Position pos;
  private boolean isAlive;
  private boolean isSolid;

  public ExitDoor(Position position) {
    this.pos = position;
    this.isAlive = true;
    this.isSolid = false;
  }

  // CONSTRUCTOR VACIO
  public ExitDoor() {
  }

  public boolean isAlive() {
    return this.isAlive;
  }

  public String getIcon() {
    return Messages.EXIT_DOOR;
  }

  public boolean isInPosition(Position p) {
    return this.pos.equals(p);
  }
}
