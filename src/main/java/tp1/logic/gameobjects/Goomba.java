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
    this.setAlive(false);
    game.goombaWasKilled();
    return true;
  }

  @Override
  protected GameObject parse(String[] words, GameWorld game) {
    String nombre = words[1]; // guarda la segunda palabra como el nombre del objeto
    Position posNueva; // Creamos la posición del nuevo goomba

    if (words.length != 3 && words.length != 2) { // si no tiene 3 o 2 palabras, no es un goomba válido
      return null;
    }

    if (matchObjectName(nombre)) {// comprueba que el nombre que le entra corresponde con el de goomba
      String[] ws = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
      int col = Integer.parseInt(ws[1]); // convierte lo que le llega en un entero (columna de la posición)
      int row = Integer.parseInt(ws[0]); // convierte lo que le llega en un entero (fila de la posición)
      posNueva = new Position(col, row); // crea la posición con la columna y fila que hemos conseguido
      if (!posNueva.isInBoard()) // si la posición conseguida no está en el tablero lanzará una excepción
        return null;

      if (words.length == 2) // si solo tiene 2 palabras, crea el goomba con dirección LEFT por defecto
        return new Goomba(posNueva, game);

      Action dir = Action.StringToDir(words[2].toUpperCase()); // convierte la palabra en una dirección
      if (dir == null) { // si le ponemos una dirección que no existe
        return null;
      }

      return new Goomba(posNueva, game); // devuelve el nuevo goomba creado con los parámetros obtenidos
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

  public String serialize(){
    return "(" + pos.getRow() + "," + pos.getCol() + ")" + " " + getName() + " " + this.dir.toString();
  }

}