package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Mario;
import tp1.view.Messages;

public class GameObjectContainer {
  private List<GameObject> objects;

  // solo se necesita esta lista con todos los distintos objetos
  // ya que todos heredan de GameObject
  public GameObjectContainer() {
    objects = new ArrayList<>();
  }

  public void add(GameObject object) {
    objects.add(object);
  }

  public String positionToString(int col, int row) {
    Position p = new Position(col, row);
    StringBuilder sb = new StringBuilder();

    // Por cada object, su geticon recorriendo todos los objetos de ese tipo
    for (GameObject obj : objects) {
      GameObject object = obj;
      if (object.isInPosition(p) && object.isAlive()) {
        sb.append(object.getIcon());
      }
    }
    // Si no hay nada en esa posición, devuelve un espacio vacío
    if (sb.length() == 0)
      sb.append(Messages.EMPTY);

    return sb.toString();
  }

  public void makeBig() {
    for (Object obj : objects) {
      if (obj instanceof Mario) {
        ((Mario) obj).makeBig();
      }
    }
  }
}
