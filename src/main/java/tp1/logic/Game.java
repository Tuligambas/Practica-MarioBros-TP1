package tp1.logic;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;

public class Game {

  public static final int DIM_X = 30;
  public static final int DIM_Y = 15;

  // ATRIBUTOS AÑADIDOS
  private int nLevel;
  private int remainingTime;
  private GameObjectContainer gameObjects;
  private Mario mario;

  // TODO fill your code

  public Game(int nLevel) {
    this.gameObjects = new GameObjectContainer();
    if (nLevel == 0)
    initLevel0();
    if (nLevel==1)
    initLevel1();
  }

  

  public String positionToString(int col, int row) {
    return gameObjects.positionToString(col, row);
  }

  public boolean playerWins() {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean playerLoses() {
    // TODO Auto-generated method stub
    return false;
  }

  public int remainingTime() {
    // TODO Auto-generated method stub
    return 100;
  }

  public int points() {
    // TODO Auto-generated method stub
    return 0;
  }

  public int numLives() {
    // TODO Auto-generated method stub
    return 3;
  }

  @Override
  public String toString() {
    return "TODO: Hola soy el game";
  }

  private void initLevel0() {
  this.nLevel = 0;
  this.remainingTime = 100;

  gameObjects = new GameObjectContainer();

  // suelo base filas 13 y 14
  for (int col = 0; col < 15; col++) {
    gameObjects.add(new Land(new Position(col, 13)));
    gameObjects.add(new Land(new Position(col, 14)));
  }

  // bloques sueltos
  gameObjects.add(new Land(new Position(9, Game.DIM_Y - 3)));
  gameObjects.add(new Land(new Position(12, Game.DIM_Y - 3)));

  for (int col = 17; col < Game.DIM_X; col++) {
    gameObjects.add(new Land(new Position(col, Game.DIM_Y - 2)));
    gameObjects.add(new Land(new Position(col, Game.DIM_Y - 1)));
  }

  gameObjects.add(new Land(new Position(2, 9)));
  gameObjects.add(new Land(new Position(5, 9)));
  gameObjects.add(new Land(new Position(6, 9)));
  gameObjects.add(new Land(new Position(7, 9)));
  gameObjects.add(new Land(new Position(6, 5)));

  // Salto final
  int tamX = 8, tamY = 8;
  int posIniX = Game.DIM_X - 3 - tamX, posIniY = Game.DIM_Y - 3;

  for (int dx = 0; dx < tamX; dx++) {
    for (int dy = 0; dy < dx + 1; dy++) {
      gameObjects.add(new Land(new Position(posIniX + dx, posIniY - dy)));
    }
  }

  // puerta de salida
  gameObjects.add(new ExitDoor(new Position(Game.DIM_X - 1, Game.DIM_Y - 3)));

  // personajes
  this.mario = new Mario(this, new Position(0, Game.DIM_Y - 3));
  gameObjects.add(this.mario);

  gameObjects.add(new Goomba(this, new Position(19, 0)));
}

private void initLevel1() {
  initLevel0();
  }

}
