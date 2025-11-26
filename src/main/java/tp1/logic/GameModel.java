package tp1.logic;

import java.util.List;
import java.util.concurrent.ExecutionException;

import tp1.exceptions.GameModelException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.gameobjects.GameObject;

public interface GameModel {
    public boolean isFinished();

    public void update();

    public void exit();

    public boolean reset(int level);

    public void addActions(List<Action> actions);

    public void addObject(String[] objWords) throws OffBoardException, ObjectParseException;

    public GameObject parseObject(String[] infoObj);

    public void save(String fileName) throws GameModelException; // Nueva con el save
}
