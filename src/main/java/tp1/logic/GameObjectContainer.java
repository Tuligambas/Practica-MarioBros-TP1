package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.ExitDoor;
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

  void update() {
    for (GameObject obj : objects)
      obj.update();
    removeDead();

  }

  private void checkMarioInExit() {
    for (GameObject obj : objects) {
      if (obj instanceof ExitDoor) {
        obj.interactWith((ExitDoor) obj);
      }
    }

  }

  // Recorre la lista de objetos y si el objeto está muerto lo elimina
  private void removeDead() {
    GameObject objeto;
    int i = objects.size() - 1;
    while (i >= 0) {
      objeto = objects.get(i);
      if (!objeto.isAlive())
        eliminarObjeto(i);
      i--;
    }
  }

  // Elimina de objeto de la lista
  public void eliminarObjeto(int i) {
    this.objects.remove(i);
  }

  // Comprueba si el objeto en esa posición es sólido
  public boolean isSolid(Position pos) {
    boolean solido = false;
    for (int i = 0; i < objects.size(); i++) {
      GameObject object = objects.get(i);
      if (object.isInPosition(pos) && object.isSolid() && object.isAlive()) {
        solido = true;
      }
    }
    return solido;
  }

  public void addActions(List<Action> actionList) {
    for (GameObject obj : objects) {
      if (obj instanceof Mario)
        obj.addAction(actionList);
    }
  }

  public boolean receiveInteractionsFrom(Mario mario) {
    boolean si = false;
    for (GameObject objeto : objects) {
      if (objeto.receiveInteraction(mario)) {
        si = true;
      }
    }
    return si;
  }

  public void killMario() {
    for (GameObject obj : objects) {
      if (obj instanceof Mario) {
        obj.setAlive(false);
      }
    }

  }
}