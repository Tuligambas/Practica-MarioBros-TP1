package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
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

  void update() {
    int n = objects.size();
    for (int i = 0; i < n; i++) {
      GameObject a = objects.get(i);
      if (a.isAlive()) {
        a.update();
        doInteractionsOf(a);
      }
    }
    removeDead();
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
      obj.addAction(actionList);
    }
  }

  // Recorre todas las parejas de objetos SIN repetirlas
  public void doInteractionsOf(GameItem a) {
    for (GameObject b : objects) {
      if (b != a && b.isAlive()) {
        a.interactWith(b);
        b.interactWith(a);
      }
    }
  }

}
