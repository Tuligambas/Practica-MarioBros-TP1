package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.logic.GameWorld;

public class GameObjectFactory {
    private static final List<GameObject> availableObjects = Arrays.asList(
            new Mario(),
            new Land(),
            new Goomba(),
            new Mushroom(),
            new Box(),
            new ExitDoor());

    // Igual que el commandGenerator
    // Recorre toda la lista de objetos, para comprobar si el nombre que le entra es
    // un objeto
    public static GameObject parse(String objWords[], GameWorld game) {
        for (GameObject obj : availableObjects) {
            GameObject gameobject = obj.parse(objWords, game);
            if (gameobject != null) {
                return gameobject;
            }
        }
        return null;
    }
}
