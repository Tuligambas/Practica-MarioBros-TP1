package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public class Goomba {
  private Game game;
  private Position pos;
  private boolean isAlive;
  private boolean isSolid;

  public Goomba(Game game, Position position) {
    this.game = game;
    this.pos = position;
    this.isAlive = true;
  }
  public String getIcon() { return "🐻"; }
  public boolean isAlive() { return this.isAlive; }
  public boolean isInPosition(Position p) { return this.pos.equals(p); }
}
  