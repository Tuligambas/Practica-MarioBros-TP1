package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.GameItem;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {
    protected Position pos;
    protected boolean isAlive;
    protected GameWorld game;
    // private boolean hasInteracted = false;

    // CONSTRUCTOR PARA INICIALIZAR OBJETOS
    public GameObject(Position pos, GameWorld game) {
        this.isAlive = true;
        this.pos = pos;
        this.game = game;
    }

    // CONSTRUCTOR VACIO
    GameObject() {
    }

    // MÉTODO QUE COMPRUEBA SI EL NOMBRE QUE TE LLEGA ES EL DEL OBJETO
    protected boolean matchObjectName(String name) {
        return getName().equalsIgnoreCase(name); // lo pone todo en minúsculas
    }

    // todos los objs se actualizan con su propio update
    // cada uno hace lo que le corresponde
    // si no hace nada, no se necesita implementar
    public abstract void update();

    public void addAction(List<Action> actionList) {

    }

    // PARA SABER EL ICONO DEL OBJETO
    public abstract String getIcon();

    public Position getPos() {
        return this.pos;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    protected void checkPosition() {
    }

    //////////////////////////////////////////////////////////////////////////////////////// GAMEITEM
    // TODAS LAS INTERACCIONES ESTÁN A FALSE PQ DEPENDIENDO DEL OBJETO SE MODIFICAN
    // Inicializado a false y cambiado en los override
    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isSolid(Position pos) {
        return false;
    }

    protected abstract String getName();

    protected abstract String getShortCut();

    // Ver si sigue vivo despues de la interaccion
    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean interactWith(GameItem other) {
        return false;
    }

    // Chequear si dos objs estan en la misma posicion
    @Override
    public boolean isInPosition(Position p) {
        return this.pos.equals(p);
    }

    @Override
    public boolean receiveInteraction(Land obj) {
        return false;
    }

    @Override
    public boolean receiveInteraction(ExitDoor obj) {
        return false;
    }

    @Override
    public boolean receiveInteraction(Mario obj) {
        return false;
    }

    @Override
    public boolean receiveInteraction(Goomba obj) {
        return false;
    }

    protected abstract GameObject parse(String objWords[], GameWorld game); // implementar en todas las clases

    /*
     * public boolean receiveInteraction(GameItem other) {
     * return false;
     * }
     * 
     * public boolean interactWith(Mario mario) {
     * return false;
     * }
     * 
     * public boolean interactWith(Land land) {
     * return false;
     * }
     * 
     * public boolean interactWith(Goomba goomba) {
     * return false;
     * }
     * 
     * public boolean interactWith(ExitDoor door) {
     * return false;
     * }
     */

}