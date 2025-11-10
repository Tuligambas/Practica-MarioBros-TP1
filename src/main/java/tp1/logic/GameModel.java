package tp1.logic;

import java.util.List;

public interface GameModel {
    public boolean isFinished();

    public void update();

    public void reset();

    // Cuidado que al igual no van aqui:

    public void exit(); // esta si

    public void reset(int level); // esta si

    public void showHelp(); // esta no

    public void addActions(List<Action> actions);

}
