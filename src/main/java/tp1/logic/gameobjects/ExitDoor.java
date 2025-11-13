package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {
  private static final String ICON = Messages.EXIT_DOOR;
  private static final String NAME = Messages.EXITDOOR_NAME;
  private static final String SHORTCUT = Messages.SHORTCUT_EXITDOOR;

  public ExitDoor(Position position, GameWorld game) {
    super(position, game);
    this.isAlive = true;
  }

  // CONSTRUCTOR VACIO
  public ExitDoor() {
  }

  @Override
  public boolean isSolid() {
    return true;
  }

  @Override
  public String getIcon() {
    return ICON;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public String getShortCut() {
    return SHORTCUT;
  }

  @Override
  public void update() {
  }

  @Override
  protected GameObject parse(String[] objWords, GameWorld game) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'parse'");
  }

}
