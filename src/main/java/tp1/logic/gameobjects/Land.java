package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
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

  public Land(Land land) {
    super(land);
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
  protected GameObject parse(String[] words, GameWorld game) throws ObjectParseException, OffBoardException {
    if (words.length != 2 || !matchObjectName(words[1]))
      return null;

    String fullDescription = String.join(" ", words);
    Position pos;
    try {
      String[] w = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
      int fila = Integer.parseInt(w[0]);
      int col = Integer.parseInt(w[1]);
      pos = new Position(col, fila);
    } catch (Exception e) {
      throw new ObjectParseException(Messages.INVALID_OBJECT_POSITION.formatted(fullDescription), e);
    }

    if (!pos.isInBoard())
      throw new OffBoardException(Messages.OBJECT_POSITION_OFF_BOARD.formatted(fullDescription));

    return new Land(pos, game);
  }

  @Override
  public GameObject copy() {
    return new Land(this);
  }

}
