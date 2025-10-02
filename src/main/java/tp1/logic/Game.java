package tp1.logic;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;
import tp1.view.Messages;

public class Game {

  public static final int DIM_X = 30;
  public static final int DIM_Y = 15;

  // ATRIBUTOS AÑADIDOS
  private int nLevel;
  private int remainingTime;
  private GameObjectContainer gameObjects;
  private Mario mario;
  public boolean exit = false;

  // TODO fill your code

  public Game(int nLevel) {
    // this.nLevel = nLevel;
    this.gameObjects = new GameObjectContainer();
    if (nLevel == 0)
      initLevel0();
    if (nLevel == 1)
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
      gameObjects.add(new Land(new Position(col, 13), this));
      gameObjects.add(new Land(new Position(col, 14), this));
    }

    // bloques sueltos
    gameObjects.add(new Land(new Position(9, Game.DIM_Y - 3), this));
    gameObjects.add(new Land(new Position(12, Game.DIM_Y - 3), this));

    for (int col = 17; col < Game.DIM_X; col++) {
      gameObjects.add(new Land(new Position(col, Game.DIM_Y - 2), this));
      gameObjects.add(new Land(new Position(col, Game.DIM_Y - 1), this));
    }

    gameObjects.add(new Land(new Position(2, 9), this));
    gameObjects.add(new Land(new Position(5, 9), this));
    gameObjects.add(new Land(new Position(6, 9), this));
    gameObjects.add(new Land(new Position(7, 9), this));
    gameObjects.add(new Land(new Position(6, 5), this));

    // Salto final
    int tamX = 8, tamY = 8;
    int posIniX = Game.DIM_X - 3 - tamX, posIniY = Game.DIM_Y - 3;

    for (int dx = 0; dx < tamX; dx++) {
      for (int dy = 0; dy < dx + 1; dy++) {
        gameObjects.add(new Land(new Position(posIniX + dx, posIniY - dy), this));
      }
    }

    // puerta de salida
    gameObjects.add(new ExitDoor(new Position(Game.DIM_X - 1, Game.DIM_Y - 3), this));

    // personajes
    this.mario = new Mario(new Position(0, Game.DIM_Y - 3), this);
    gameObjects.add(this.mario);

    gameObjects.add(new Goomba(new Position(19, 0), this));
  }

  private void initLevel1() {
    initLevel0();
    gameObjects.add(new Goomba(new Position(6, 4), this));
    gameObjects.add(new Goomba(new Position(6, 12), this));
    gameObjects.add(new Goomba(new Position(8, 12), this));
    gameObjects.add(new Goomba(new Position(10, 10), this));
    gameObjects.add(new Goomba(new Position(11, 12), this));
    gameObjects.add(new Goomba(new Position(14, 12), this));
    gameObjects.makeBig();
  }

  public void exit() {
    this.exit = true;
  }

  public void reset() {
    if (nLevel == 0)
      initLevel0();
    if (nLevel == 1)
      initLevel1();
  }

  public void showHelp() {
    System.out.println(Messages.HELP);
  }

  public boolean isFinished() {
    boolean acabado = false;
    if (remainingTime == 0 || (exit)) {
      acabado = true;
    }
    return acabado;
  }

}
