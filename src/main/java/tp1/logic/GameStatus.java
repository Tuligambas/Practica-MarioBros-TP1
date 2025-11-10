package tp1.logic;

public interface GameStatus {
    public int points();

    public boolean playerWins();

    public String positionToString(int col, int row);

    public int numLives();

    public int remainingTime();

    public boolean playerLoses();

    public int getNumLives();

}
