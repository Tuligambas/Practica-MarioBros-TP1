package tp1.logic;


public interface GameWorld {

    public void addPoints(int points);

    public void marioArrived();

    // public void addActions(List<Action> actions); // En teoría va en gamemodel

    public boolean isSolid(Position pos);

    public void looseLife();

    public void goombaWasKilled();

}
