package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {
  private static final String ICON = Messages.EXIT_DOOR;
  private boolean isAlive;

  public ExitDoor(Position position, Game game) {
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

  @Override
  public void addAction(List<Action> actionList) {
    // No hace nada, la puerta no se mueve
  }

  @Override
  public boolean receiveInteraction(GameObject other) {
    return other.interactWith(this);
  }

}
