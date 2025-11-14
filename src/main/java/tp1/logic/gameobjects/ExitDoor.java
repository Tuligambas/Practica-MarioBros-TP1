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
  public String getIcon() {
    return ICON;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public boolean receiveInteraction(Mario mario) {
    game.marioArrived();
    return true;

  }

  @Override
  public String getShortCut() {
    return SHORTCUT;
  }

  @Override
  public void update() {
  }

  @Override
  protected GameObject parse(String[] words, GameWorld game) {
    if (words.length != 2)
      return null;

    String nombre = words[1];
    if (matchObjectName(words[1])) {
      String[] w = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
      int fila = Integer.parseInt(w[0]);
      int col = Integer.parseInt(w[1]);
      Position pos = new Position(col, fila);

      if (!pos.isInBoard())
        return null;
      else
        return new ExitDoor(pos, game);
    }
    return null;
  }

}
