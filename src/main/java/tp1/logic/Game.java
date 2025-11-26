package tp1.logic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import tp1.exceptions.GameModelException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
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
  private GameConfiguration conf = FileGameConfiguration.NONE; // FileGameConfiguration que no tiene nada
  private String fileName;
  private GameObjectContainer gameObjects;

  public Game(int nLevel) {
    this.nLevel = nLevel;
    initGame(nLevel);
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

  // METODO DE TE LLAMA AL METODO INITGAME QUE TE HACE EL REINICIO DEL JUEGO Y
  // SI HAY UNA CONFIGURACIÓN, LA RESETEA CON EL LOAD
  @Override
  public boolean reset(int nLevel) {
    if (conf == FileGameConfiguration.NONE) {
      return initGame(nLevel);
    } else {
      try {
        load(this.fileName);
      } catch (GameLoadException e) {
        conf = FileGameConfiguration.NONE;
      }
      return true;
    }
  }

  @Override
  public void exit() {
    this.exit = true;
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
      reset(nLevel);
    }
  }

  // METODO PARA EL REINCIO
  public boolean initGame(int nLevel) {
    boolean b = true;
    switch (nLevel) {
      case 0 -> initLevel0();
      case 1 -> initLevel1();
      case 2 -> initLevel2();
      case -1 -> initLevelMenos1();
      default -> reset(this.nLevel);
    }
    return b;
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

  public void load(String fileName) throws GameLoadException {
    conf = new FileGameConfiguration(fileName, this);
    this.fileName = fileName;
    this.remainingTime = conf.getTime();
    this.points = conf.getPoints();
    this.numLives = conf.getLives();
    this.gameObjects = new GameObjectContainer();

  for(GameObject obj : conf.getObjects()){
    this.gameObjects.add(obj);
  }
    
  }

  @Override
  public void addObject(String[] objWords) throws OffBoardException, ObjectParseException {
    gameObjects.add(objWords);
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


  // Función para leer
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(remainingTime).append(" ").append(points).append(" ").append(numLives).append("\n");

    for(GameObject obj : gameObjects){
    sb.append(obj.serialize()).append("\n");
    }

    return sb.toString();
  }
  

  @Override
  public void save(String fileName) throws GameModelException { // ver si esta bien esta función
    
    try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
        
        // Serializar el estado actual del juego
        pw.print(this.toString());

    } catch (IOException e) {
        throw new GameModelException("Error saving game to file: " + fileName, e); 
    }
  }



}