package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Land extends GameObject {
  private static final String ICON = Messages.LAND;
  private static final String NAME = Messages.LAND_NAME;
  private static final String SHORTCUT = Messages.SHORTCUT_LAND;

  public Land(Position pos, GameWorld game) {
    super(pos, game);
  }

  public Land() {
  }

  // el land no se actualiza
  @Override
  public void update() {
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
  protected GameObject parse(String[] words, GameWorld game) {
    String nombre = words[1];
    if (matchObjectName(words[1])) {
      String[] w = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
      int fila = Integer.parseInt(w[0]);
      int col = Integer.parseInt(w[1]);
      Position pos = new Position(col, fila);

      if (pos.isInBoard())
        return new Land(pos, game);

    }
    return null;
  }

}
