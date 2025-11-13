package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends MovingObject {
  private static final String ICON = Messages.GOOMBA;
  private static final String NAME = Messages.GOOMBA_NAME;
  private static final String SHORTCUT = Messages.SHORTCUT_GOOMBA;

  public Goomba(Position position, GameWorld game) {
    super(position, game, false, Action.LEFT);
  }

  public Goomba() {
  }

  @Override
  public void update() {
    automaticMovement();
  }

  @Override
  public String getIcon() {
    return ICON;
  }

  public Action geAction() {
    return this.dir;
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

  @Override
  protected GameObject parse(String[] objWords, GameWorld game) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'parse'");
  }

  @Override
  protected String getName() {
    return NAME;
  }

  @Override
  protected String getShortCut() {
    return SHORTCUT;
  }

}