package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.GameItem;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends MovingObject {
  private static final String ICON = Messages.GOOMBA;

  public Goomba(Position position, GameWorld game) {
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
  protected void checkPosition() {
    if (!this.pos.isInBoard()) {
      setAlive(false);
    }
  }

  @Override
  public boolean interactWith(GameItem other) {
    boolean canInteract = other.isInPosition(this.pos);
    if (canInteract) {
      return other.receiveInteraction(this);
    }
    return false;
  }

  @Override
  public boolean receiveInteraction(Mario mario) {
    if (mario.goombaOnYou(this)) {
      this.setAlive(false);
      game.goombaWasKilled();
      return true;
    }
    return false;
  }

}