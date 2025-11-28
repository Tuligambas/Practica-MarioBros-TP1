package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {
  private static final String ICON = Messages.EXIT_DOOR;
  private static final String NAME = Messages.EXITDOOR_NAME;
  private static final String SHORTCUT = Messages.SHORTCUT_EXITDOOR;

  public ExitDoor(Position position, GameWorld game) {
    super(position, game);
  }

  // CONSTRUCTOR VACIO
  public ExitDoor() {
  }

  public ExitDoor(ExitDoor exitDoor) {
    super(exitDoor);
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
  protected GameObject parse(String[] words, GameWorld game) throws ObjectParseException, OffBoardException {
    String nombre = words[1];
    if (matchObjectName(words[1])) {
      try {
        String[] w = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
        int fila = Integer.parseInt(w[0]);
        int col = Integer.parseInt(w[1]);
        Position pos = new Position(col, fila);
        if (!pos.isInBoard()) {
          throw new OffBoardException(Messages.OBJECT_POSITION_OFF_BOARD.formatted(words[0]));
        } else
          return new ExitDoor(pos, game);
      } catch (ArrayIndexOutOfBoundsException e1) {
        throw new ObjectParseException(Messages.INVALID_GAME_OBJECT.formatted(words[0]));
      } catch (NumberFormatException e2) {
        throw new ObjectParseException(Messages.INVALID_POSITION.formatted(words[0]));
      }
    }
    return null;
  }

  @Override
  public GameObject copy() {
    return new ExitDoor(this);
  }

}
