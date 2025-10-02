package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends GameObject {
  private static final String ICON = Messages.GOOMBA;
  private boolean isAlive;

  public Goomba(Position position, Game game) {
    super(position, game);
    this.isAlive = true;
  }

  public String getIcon() {
    return ICON;
  }

  @Override
  public void update() {
  }

  @Override
  public boolean isAlive() {
    return isAlive;
  }

  @Override
  public boolean isSolid() {
    return false;
  }

}
