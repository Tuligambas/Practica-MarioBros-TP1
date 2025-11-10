package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends MovingObject {
  private static final String ICON = Messages.GOOMBA;

  public Goomba(Position position, Game game) {
    super(position, game, false, Action.LEFT);
  }

  @Override
  public void update() {
    automaticMovement();
  }

  public String getIcon() {
    return ICON;
  }

  @Override
  public boolean isAlive() {
    return isAlive;
  }

  public Action geAction() {
    return this.dir;
  }

  public void setAlive(boolean alive) {
    this.isAlive = alive;
  }

  @Override
  public boolean receiveInteraction(GameObject other) {
    return other.interactWith(this);
  }

  @Override
  protected void checkPosition() {
    if (!this.pos.isInBoard()) {
      setAlive(false);
    }
  }
}