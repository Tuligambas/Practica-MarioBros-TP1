package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
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

  public Goomba(Position position, GameWorld game, Action dir) {
    super(position, game, false, dir);
  }

  public Goomba() {
  }

  public Goomba(Goomba goomba) {
    super(goomba);
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
    this.setAlive(false);
    game.goombaWasKilled();
    return true;
  }

  @Override
  protected GameObject parse(String[] words, GameWorld game) throws ObjectParseException, OffBoardException {
    if (words.length < 2 || !matchObjectName(words[1]))
      return null;

    String fullDescription = String.join(" ", words);

    if (words.length > 3)
      throw new ObjectParseException(Messages.OBJECT_PARSE_ERROR_TOO_MUCH_ARGS.formatted(fullDescription));

    Position posNueva;
    try {
      String[] ws = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
      int col = Integer.parseInt(ws[1]); // columna
      int row = Integer.parseInt(ws[0]); // fila
      posNueva = new Position(col, row);
    } catch (Exception e) {
      ObjectParseException cause = new ObjectParseException(Messages.INVALID_POSITION.formatted(words[0]), e);
      throw new ObjectParseException(Messages.INVALID_OBJECT_POSITION.formatted(fullDescription), cause);
    }

    if (!posNueva.isInBoard())
      throw new OffBoardException(Messages.OBJECT_POSITION_OFF_BOARD.formatted(fullDescription));

    if (words.length == 2) // sin dirección explícita: LEFT por defecto
      return new Goomba(posNueva, game);

    Action dir = Action.StringToDir(words[2].toUpperCase());
    if (dir == null) {
      ObjectParseException cause = new ObjectParseException(Messages.UNKNOWN_ACTION.formatted(words[2]));
      throw new ObjectParseException(Messages.UNKNOWN_MOVING_OBJECT_DIRECTION.formatted(fullDescription), cause);
    }
    if (dir != Action.LEFT && dir != Action.RIGHT)
      throw new ObjectParseException(Messages.INVALID_MOVING_OBJECT_DIRECTION.formatted(fullDescription));

    return new Goomba(posNueva, game, dir);
  }

  @Override
  protected String getName() {
    return NAME;
  }

  @Override
  protected String getShortCut() {
    return SHORTCUT;
  }

  public String serialize() {
    return "(" + pos.getRow() + "," + pos.getCol() + ")" + " " + getName() + " " + this.dir.toString();
  }

  @Override
  public GameObject copy() {
    return new Goomba(this);
  }
}
