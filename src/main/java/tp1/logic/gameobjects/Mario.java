package tp1.logic.gameobjects;

import java.util.List;

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

  public Mario(Position position, GameWorld game, boolean big) {
    super(position, game, false, Action.RIGHT);
    this.big = big;
    this.actionList = new ActionList();
  }

  // CONSTRUCTOR VACIO
  public Mario() {
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
      Action action = actionList.getNext(); // método auxiliar que devuelve y elimina

      // cambia de direccion
      if (action.isHorizontal() || action == Action.STOP)
        this.dir = action;

      if (action == Action.DOWN) {
        if (solidBelow())
          this.dir = Action.STOP;
        else
          toTheFloor();
      } else if (action != Action.STOP) {
        if (solidNextTo(action) || wallNextTo(action))
          this.dir = action.opposite();
        else {
          Position next = this.pos.move(action);
          this.prevPosition = new Position(this.pos.getCol(), this.pos.getRow());
          this.pos = next;
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
    }

  }

  @Override
  protected boolean solidNextTo(Action dir) {
    Position next = this.pos.move(dir);
    Position next_big = this.pos.move(dir).move(Action.UP);
    return this.game.isSolid(next) || game.isSolid(next_big);
  }

  @Override
  public boolean isInPosition(Position p) {
    if (this.pos.equals(p)) {
      return true;
    }
    if (this.big) {
      // casilla justo arriba (misma columna, fila - 1)
      Position above = new Position(pos.getCol(), pos.getRow() - 1);
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
    boolean canInteract = item.isInPosition(this.pos);
    boolean solidAndUp = item.isInPosition(this.pos.move(Action.UP)) && dir == Action.UP;
    if (canInteract || solidAndUp) {
      return item.receiveInteraction(this);
    }
    return false;
  }

  @Override
  public boolean receiveInteraction(Goomba goomba) {
    if (prevPosition.above(goomba.getPos()))
      return true;

    if (goomba.getPos().above(this.pos)) {
      marioGetAttacked();
      goomba.setAlive(false);
      game.goombaWasKilled();
      return true;
    }

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

  /*
   * private void killGoomba(Goomba goomba) {
   * goomba.setAlive(false);
   * game.goombaWasKilled();
   * }
   */
  // Eliminar porque no se usa

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
  protected GameObject parse(String[] words, GameWorld game) {
    String nombre = words[1]; // guarda la segunda palabra como el nombre del objeto
    Position posNueva; // Creamos la posición del nuevo mario

    if (words.length != 4) { // si no tiene 4 palabras, no es un mario válido
      return null;
    }

    if (matchObjectName(nombre)) {// comprueba que el nombre que le entra corresponde con el de mario
      String[] ws = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
      int col = Integer.parseInt(ws[1]); // convierte lo que le llega en un entero (columna de la posición)
      int row = Integer.parseInt(ws[0]); // convierte lo que le llega en un entero (fila de la posición)
      posNueva = new Position(col, row); // crea la posición con la columna y fila que hemos conseguido
      if (!posNueva.isInBoard()) { // si la posición conseguida no está en el tablero lanzará una excepción
        return null;
      }

      Action dir = Action.StringToDir(words[2].toUpperCase()); // convierte la tercera palabra en una
                                                               // dirección
      if (dir == null || (dir != Action.RIGHT && dir != Action.LEFT)) { // si le ponemos una dirección que no existe o
                                                                        // no es RIGHT o LEFT
        return null;
      }

      String size; // crea la fuerza de caída que lleva en ese momento, que siempre va a ser 0
      size = words[3]; // la lee, que normalmente es 0
      boolean big;
      if (size.equalsIgnoreCase("BIG"))
        big = true;
      else if (size.equalsIgnoreCase("SMALL"))
        big = false;
      else
        return null;

      return new Mario(posNueva, game, big); // devuelve el nuevo mario creado con los parámetros obtenidos

    }
    return null;

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
