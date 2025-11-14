package tp1.logic;

/**
 *
 * TODO: Immutable class to encapsulate and manipulate positions in the game
 * board
 *
 */
public class Position {
  private int col;
  private int row;

  public Position(int x, int y) {
    this.col = x;
    this.row = y;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) // mismo objeto en memoria
      return true;
    if (obj == null) // comparando con null
      return false;
    if (getClass() != obj.getClass()) // tipos diferentes
      return false;
    Position other = (Position) obj; // casteo seguro
    return this.col == other.col && this.row == other.row;
  }

  public int getCol() {
    return this.col;
  }

  public int getRow() {
    return this.row;
  }

  public Position move(Action dir) {
    return new Position(this.col + dir.getX(), this.row + dir.getY());
  }

  public boolean isInBoard() {
    return col >= 0 && col < Game.DIM_X && row >= 0 && row < Game.DIM_Y;
  }

  public boolean isWall() {
    return this.col == -1 || this.col == Game.DIM_X || this.row == -1 || this.row == Game.DIM_Y - 1;
  }

  public boolean above(Position p) {
    return this.col == p.col && this.row + 1 == p.row;
  }

}
