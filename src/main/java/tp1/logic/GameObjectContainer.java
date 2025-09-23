package tp1.logic;

import java.util.ArrayList;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;

public class GameObjectContainer {
  // TODO fill your code

  private ArrayList<Land> lands; // arrayList = arrayMasComodo
  // el <> es el tipo del contenido
  private ArrayList<Goomba> goombas;
  private ArrayList<ExitDoor> exits;
  private ArrayList<Mario> marios;

  public GameObjectContainer() {
    lands = new ArrayList<>(); // inicializo el array, queda vacio
    goombas = new ArrayList<>();
    exits = new ArrayList<>();
    marios = new ArrayList<>();
  }

  public void add(Land land) {
    lands.add(land); // añades ese trozo de "land" a la lista
    // insercion mucho mas facil
  }

  public void add(Goomba goomba) { goombas.add(goomba); }
  public void add(ExitDoor exit) { exits.add(exit); }
  public void add(Mario mario) { marios.add(mario); }
}
