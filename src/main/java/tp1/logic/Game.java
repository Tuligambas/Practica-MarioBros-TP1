package tp1.logic;

import java.util.List;

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
  private int numLives = 3;
  private GameObjectContainer gameObjects;
  private Mario mario;
  public boolean exit = false;
  private int points;
  private boolean win = false;

  public Game(int nLevel) {
    this.nLevel = nLevel;
    this.gameObjects = new GameObjectContainer();
    this.points = 0;
    this.numLives = 3;
    this.exit = false;
    this.remainingTime = 100;
    if (nLevel == 0)
      initLevel0();
    if (nLevel == 1)
      initLevel1();
  }

  public String positionToString(int col, int row) {
    return gameObjects.positionToString(col, row);
  }

  public boolean playerWins() {
    return win;
  }

  public boolean playerLoses() {
    return (numLives <= 0);
  }

  public int remainingTime() {
    return remainingTime;
  }

  public int points() {
    return points;
  }

  public int numLives() {
    return numLives;
  }

  @Override
  public String toString() {
    return "TODO: Hola soy el game";
  }

  private void initLevel0() {
    this.remainingTime = 100;
    this.win = false;
    this.exit = false;
    gameObjects = new GameObjectContainer();

    // personajes
    this.mario = new Mario(new Position(0, Game.DIM_Y - 3), this, true);
    gameObjects.add(this.mario);

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
  }

  public void exit() {
    this.exit = true;
  }

  public void reset(int level) {
    if (level == 0)
      initLevel0();
    if (level == 1)
      initLevel1();
  }

  public void showHelp() {
    System.out.println(Messages.HELP);
  }

  public boolean isFinished() {
    return remainingTime == 0 || exit || playerLoses();
  }

  public void update() {
    remainingTime--;
    gameObjects.update();
  }

  public boolean isSolid(Position p) {
    return gameObjects.isSolid(p);
  }

  public void addActions(List<Action> actionList) {
    gameObjects.addActions(actionList);
  }

  public void marioExited() {
    this.win = true;
    this.points += this.remainingTime * 10;
    this.remainingTime = 0;
    playerWins();

  }

  public boolean receiveInteractionsFrom(Mario mario) {
    return gameObjects.receiveInteractionsFrom(mario);
  }

  public void goombaWasKilled() {
    this.points += 100;
  }

  public void looseLife() {
    this.numLives--;
    if (this.numLives > 0) {
      reset();
    }
  }

  public int getNumLives() {
    return this.numLives;
  }

  public void reset() {
    if (this.nLevel == 0)
      initLevel0();
    if (this.nLevel == 1)
      initLevel1();
  }

}