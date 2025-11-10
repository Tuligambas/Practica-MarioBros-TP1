package tp1.logic;

public interface GameWorld {

    public boolean isSolid(Position pos);

    public void addPoints(int points);

    public void marioArrived();

}
