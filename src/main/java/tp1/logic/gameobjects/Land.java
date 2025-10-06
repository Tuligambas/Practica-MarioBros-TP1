package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Land extends GameObject {
  private static final String ICON = Messages.LAND;
  private Position pos;
  private boolean isAlive;

  public Land(Position pos, Game game) {
    super(pos, game);
    this.isAlive = true;
  }

  // el land no se actualiza
  @Override
  public void update() {
  }

  @Override
  public boolean isSolid() {
    return true;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public String getIcon() {
    return ICON;
  }

  @Override
  public void addAction(List<Action> actionList) {
  }

}
