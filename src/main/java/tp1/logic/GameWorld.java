package tp1.logic;

import java.util.List;

public interface GameWorld {

    public void addPoints(int points);

    public void marioArrived();

    public void addActions(List<Action> actions); //

    public boolean isSolid(Position pos);

    public void looseLife();

    public void goombaWasKilled();

}
