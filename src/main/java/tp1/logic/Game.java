package tp1.logic;

import java.util.List;

import tp1.logic.gameobjects.Box;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Mushroom;

public class Game implements GameModel, GameStatus, GameWorld {
  public static final int DIM_X = 30;
  public static final int DIM_Y = 15;

  // ATRIBUTOS AÑADIDOS
  private int points;
  private int nLevel;
  private int remainingTime;
  private int numLives = 3;
  public boolean exit = false; // como finished
  private boolean win = false; // como playerwon

  private GameObjectContainer gameObjects;

  public Game(int nLevel) {
    this.nLevel = nLevel;
    this.gameObjects = new GameObjectContainer();
    this.points = 0;
    this.numLives = 3;
    this.exit = false;
    this.remainingTime = 100;
    if (nLevel == -1)
      initLevelMenos1();
    if (nLevel == 0)
      initLevel0();
    if (nLevel == 1)
      initLevel1();
    if (nLevel == 2)
      initLevel2();
  }

  // Métodos de GameModel
  @Override
  public boolean isFinished() {
    return remainingTime == 0 || exit || playerLoses();
  }

  @Override
  public void update() {
    remainingTime--;
    gameObjects.update();
  }

  @Override
  public void reset() {
    if (this.nLevel == -1)
      initLevelMenos1();
    if (this.nLevel == 0)
      initLevel0();
    if (this.nLevel == 1)
      initLevel1();
    if (this.nLevel == 2)
      initLevel2();
  }

  @Override
  public void exit() {
    this.exit = true;
  }

  @Override
  public void reset(int level) {
    this.nLevel = level;
    if (level == -1)
      initLevelMenos1();
    if (level == 0)
      initLevel0();
    if (level == 1)
      initLevel1();
    if (level == 2)
      initLevel2();
  }

  @Override
  public void addActions(List<Action> actionList) {
    gameObjects.addActions(actionList);
  }

  // Métodos de GameStatus
  @Override
  public int points() {
    return points;
  }

  @Override
  public boolean playerWins() {
    return win;
  }

  @Override
  public String positionToString(int col, int row) {
    return gameObjects.positionToString(col, row);
  }

  @Override
  public int getNumLives() {
    return this.numLives;
  }

  @Override
  public boolean playerLoses() {
    return (numLives <= 0);
  }

  @Override
  public int remainingTime() {
    return remainingTime;
  }

  @Override
  public int numLives() {
    return numLives;
  }

  // Métodos de GameWorld
  @Override
  public boolean isSolid(Position p) {
    return gameObjects.isSolid(p);
  }

  @Override
  public void addPoints(int points) {
    this.points += points;
  }

  @Override
  public void marioArrived() {
    this.win = true;
    this.points += this.remainingTime * 10;
    this.remainingTime = 0;
    playerWins();

  }

  @Override
  public void goombaWasKilled() {
    addPoints(100);
  }

  @Override
  public void looseLife() {
    this.numLives--;
    if (this.numLives > 0) {
      reset();
    }
  }

  private void initLevelMenos1() {
    this.remainingTime = 100;
    this.win = false;
    this.exit = false;
    // Se reinicia todo
    this.points = 0;
    this.numLives = 3;
    gameObjects = new GameObjectContainer();
  }

  private void initLevel0() {
    this.remainingTime = 100;
    this.win = false;
    this.exit = false;
    gameObjects = new GameObjectContainer();

    // personajes
    gameObjects.add(new Mario(new Position(0, Game.DIM_Y - 3), this, true));

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

  private void initLevel2() {
    initLevel1();
    gameObjects.add(new Mushroom(new Position(8, 12), this));
    gameObjects.add(new Mushroom(new Position(20, 2), this));
    gameObjects.add(new Box(new Position(4, 9), this, false));
  }

  @Override
  public String toString() {
    return "TODO: Hola soy el game";
  }

  @Override
  public void addObject(GameObject obj) {
    gameObjects.add(obj);
  }

  @Override
  public GameObject parseObject(String[] infoObj) {
    return GameObjectFactory.parse(infoObj, this);
  }

  @Override
  public void checkInteractions(GameItem obj) {
    gameObjects.doInteractionsOf(obj);
  }

  @Override
  public void newMushroomAt(Position pos) {
    gameObjects.new_add(new Mushroom(pos.move(Action.UP), this));
  }

}