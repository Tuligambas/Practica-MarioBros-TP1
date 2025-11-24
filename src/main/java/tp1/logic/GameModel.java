package tp1.logic;

import java.util.List;

import tp1.logic.gameobjects.GameObject;

public interface GameModel {
    public boolean isFinished();

    public void update();

    public void reset();

    public void exit();

    public boolean reset(int level);

    // public void showHelp(); // esta no

    public void addActions(List<Action> actions);

    // public void load(String filename);

    public void addObject(GameObject obj);

    public GameObject parseObject(String[] infoObj);

}
