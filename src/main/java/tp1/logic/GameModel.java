package tp1.logic;

import java.util.List;

public interface GameModel {
    public boolean isFinished();

    public void update();

    public void reset();

    public void addActions(List<Action> actions);

    public void exit();

    public void reset(int level);

    public void showHelp();

}
