package tp1.logic.gameobjects;

import java.util.List;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Action;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario extends MovingObject {
  private boolean big;
  private Position prevPosition;
  private ActionList actionList;
  private static final String NAME = Messages.MARIO_NAME;
  private static final String SHORTCUT = Messages.SHORTCUT_MARIO;
  private Action lastMove = null;

  public Mario(Position position, GameWorld game, boolean big) {
    super(position, game, false, Action.RIGHT);
    this.big = big;
    this.actionList = new ActionList();
  }

  // CONSTRUCTOR VACIO
  public Mario() {
  }

  public Mario(Position posNueva, GameWorld game, Action dir, boolean big) {
    super(posNueva, game, false, dir);
    this.big = big;
    this.actionList = new ActionList();
  }

  public Mario(Mario mario) {
    super(mario);
    this.big = mario.big;
    this.actionList = mario.getActionListCopy();
  }

  private ActionList getActionListCopy() {
    ActionList copy = new ActionList();
    for (Action a : this.actionList.getActions()) {
      copy.addAction(a);
    }
    return copy;
  }

  @Override
  public void update() {
    setPrevPosition(); // simplifica colisiones e interacciones
    if (actionList.isEmpty()) {
      automaticMovement();
    } else {
      commandMovement();
    }
    checkPosition(); // chequea si esta en una posicion valida
  }

  private void setPrevPosition() {
    if (dir != Action.STOP)
      this.prevPosition = new Position(this.pos.getCol(), this.pos.getRow());
  }

  private void commandMovement() {
    while (!actionList.isEmpty()) {
      Action action = actionList.getNext();

      // Cambia icono solo si horizontal o stop
      if (action.isHorizontal() || action == Action.STOP)
        this.dir = action;

      // Movimiento hacia abajo
      if (action == Action.DOWN) {
        this.lastMove = Action.DOWN;
        if (solidBelow())
          this.dir = Action.STOP;
        else
          toTheFloor();
      }

      // UP/LEFT/RIGHT
      else if (action != Action.STOP) {
        // Colisión lateral o pared
        if (solidNextTo(action) || wallNextTo(action)) {
          this.lastMove = action; //
          checkInteractions();
          if (action.isHorizontal())
            this.dir = action.opposite();
        }
        // Movimiento normal
        else {
          Position next = this.pos.move(action);
          this.prevPosition = this.pos;
          this.pos = next;
          this.lastMove = action;
          checkInteractions();
        }
      }
    }

    if (this.prevPosition.equals(this.pos))
      automaticMovement();
  }

  private void checkInteractions() {
    game.checkInteractions(this);
  }

  private void toTheFloor() {
    while (!solidBelow() && pos.isInBoard()) {
      setPrevPosition();
      this.pos = this.pos.move(Action.DOWN);
      checkInteractions();
    }

  }

  @Override
  protected boolean solidNextTo(Action dir) {
    Position next = this.pos.move(dir);
    if (big) {
      Position next_big = this.pos.move(dir).move(Action.UP);
      return this.game.isSolid(next) || game.isSolid(next_big);
    }
    return this.game.isSolid(next);
  }

  @Override
  public boolean isInPosition(Position p) {
    if (this.pos.equals(p)) {
      return true;
    }
    if (this.big) {
      Position above = pos.move(Action.UP);
      return above.equals(p);
    }

    return false;
  }

  @Override
  public String getIcon() {
    String icon = " ";
    switch (this.dir) {
      case LEFT:
        icon = Messages.MARIO_LEFT;
        break;
      case RIGHT:
        icon = Messages.MARIO_RIGHT;
        break;
      case STOP:
        icon = Messages.MARIO_STOP;
        break;
      default:
    }
    if (this.big)
      icon = icon.toUpperCase();

    return icon;
  }

  // MÉTODO QUE DICE SI EL MARIO ESTÁ EN EL AIRE
  public boolean isInAir() {
    Position abajo = new Position(this.pos.getCol(), this.pos.getRow() + 1);
    boolean aire = false;

    if (!game.isSolid(abajo) && pos.isInBoard())
      aire = true;

    return aire;
  }

  @Override
  public void addAction(List<Action> actionList) {
    this.actionList = new ActionList();
    for (Action a : actionList) {
      this.actionList.addAction(a);
    }
  }

  @Override
  public boolean interactWith(GameItem item) {
    Position above = this.pos.move(Action.UP);
    // Interacción normal (misma casilla)
    if (this.isInPosition(item.getPos())) {
      return item.receiveInteraction(this);
    }

    // Interacción desde abajo
    if (hitsFromBelow(item)) {
      return item.receiveInteraction(this);
    }

    return false;
  }

  public boolean hitsFromBelow(GameItem item) {
    if (lastMove != Action.UP)
      return false;

    Position above = this.pos.move(Action.UP);
    Position aboveBig = above.move(Action.UP);

    boolean crashSmall = item.isInPosition(above);
    boolean crashBig = this.isBig() && item.isInPosition(aboveBig);

    return item.isSolid() && (crashSmall || crashBig);
  }

  @Override
  public boolean receiveInteraction(Goomba goomba) {
    if (prevPosition.above(goomba.getPos()))
      return true;

    marioGetAttacked();
    return true;
  }

  public boolean goombaOnYou(Goomba g) {
    return prevPosition.equals(g.getPos()) || prevPosition.move(Action.UP).equals(g.getPos());
  }

  private void marioGetAttacked() {
    if (this.big) {
      this.big = false;
    } else {
      this.isAlive = false;
      game.looseLife();
    }
  }

  @Override
  protected void checkPosition() {
    if (!this.pos.isInBoard()) {
      this.big = false; // ayuda con test
      game.looseLife();
    }
  }

  public void makeBig() {
    this.big = true;
  }

  public boolean isBig() {
    return big;
  }

  @Override
  protected GameObject parse(String[] words, GameWorld game) throws ObjectParseException, OffBoardException {
    if (words.length < 2 || !matchObjectName(words[1]))
      return null;

    String fullDescription = String.join(" ", words);
    if (words.length > 4)
      throw new ObjectParseException(Messages.OBJECT_PARSE_ERROR_TOO_MUCH_ARGS.formatted(fullDescription));

    Position posNueva;
    try {
      String[] ws = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
      int col = Integer.parseInt(ws[1]); // columna
      int row = Integer.parseInt(ws[0]); // fila
      posNueva = new Position(col, row);
    } catch (Exception e) {
      throw new ObjectParseException(Messages.INVALID_OBJECT_POSITION.formatted(fullDescription), e);
    }

    if (!posNueva.isInBoard())
      throw new OffBoardException(Messages.OBJECT_POSITION_OFF_BOARD.formatted(fullDescription));

    Action dir = Action.RIGHT; // dirección por defecto
    if (words.length >= 3) {
      dir = Action.StringToDir(words[2].toUpperCase()); // convierte la tercera palabra en una dirección
      if (dir == null) {
        ObjectParseException cause = new ObjectParseException(Messages.UNKNOWN_ACTION.formatted(words[2]));
        throw new ObjectParseException(Messages.UNKNOWN_MOVING_OBJECT_DIRECTION.formatted(fullDescription), cause);
      }
      if (dir != Action.RIGHT && dir != Action.LEFT)
        throw new ObjectParseException(Messages.INVALID_MOVING_OBJECT_DIRECTION.formatted(fullDescription));
    }

    boolean big = true; // tamaño por defecto
    if (words.length == 4) {
      String size = words[3];
      if (size.equalsIgnoreCase("BIG") || size.equalsIgnoreCase("B"))
        big = true;
      else if (size.equalsIgnoreCase("SMALL") || size.equalsIgnoreCase("S"))
        big = false;
      else
        throw new ObjectParseException(Messages.INVALID_MARIO_SIZE.formatted(fullDescription));
    }

    return new Mario(posNueva, game, dir, big); // devuelve el nuevo mario creado con los parámetros obtenidos
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
    String tamano = this.isBig() ? "Big" : "Small";

    return "(" + pos.getRow() + "," + pos.getCol() + ")" + " " + getName() + " " + this.dir.toString() + " " + tamano;
  }

  @Override
  public GameObject copy() {
    return new Mario(this);
  }

}
