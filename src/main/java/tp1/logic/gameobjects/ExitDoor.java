package tp1.logic.gameobjects;

import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {
  private static final String ICON = Messages.EXIT_DOOR;
  private boolean isAlive;

  public ExitDoor(Position position, GameModel game) {
    super(position, game);
    this.isAlive = true;
  }

  // CONSTRUCTOR VACIO
  public ExitDoor() {
  }

  @Override
  public boolean isAlive() {
    return isAlive;
  }

  public String getIcon() {
    return ICON;
  }

  @Override
  public void update() {
  }

}
