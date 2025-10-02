package tp1.logic;

import java.util.ArrayList;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;
import tp1.view.Messages;

public class GameObjectContainer {
  // TODO fill your code

  private ArrayList<Land> lands; // arrayList = arrayMasComodo
  // el <> es el tipo del contenido
  private ArrayList<Goomba> goombas;
  private ExitDoor exit;
  private Mario mario;

  public GameObjectContainer() {
    lands = new ArrayList<>(); // inicializo el array, queda vacio
    goombas = new ArrayList<>(); // lo mismo
    exit = new ExitDoor();
    mario = new Mario();
  }

  public void add(Land land) {
    lands.add(land); // añades ese trozo de "land" a la lista
    // insercion mucho mas facil
  }

  public String positionToString(int col, int row) {
    Position p = new Position(col, row);
    StringBuilder sb = new StringBuilder();

    // Por cada object, su geticon recorriendo todos los objetos de ese tipo
    for (Land land : lands) {
      if (land.isInPosition(p) && land.isAlive())
        sb.append(land.getIcon());
    }

    for (Goomba goomba : goombas) {
      if (goomba.isInPosition(p) && goomba.isAlive())
        sb.append(goomba.getIcon());
    }

    if (exit.isInPosition(p) && exit.isAlive())
      sb.append(exit.getIcon());

    if (mario.isInPosition(p) && mario.isAlive())
      sb.append(mario.getIcon());

    // Si no hay nada en esa posición, devuelve un espacio vacío
    if (sb.length() == 0)
      sb.append(Messages.EMPTY);

    return sb.toString();
  }

  public void add(Goomba goomba) {
    goombas.add(goomba);
  }

  public void add(ExitDoor exit) {
    this.exit = exit;
  }

  public void add(Mario mario) {
    this.mario = mario;
  }

  public void makeBig() {
    this.mario.makeBig();
  }
}
