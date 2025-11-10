package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.GameModel;
import tp1.logic.GameItem;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {
    protected Position pos;
    protected boolean isAlive;
    protected GameModel game;
    private boolean hasInteracted = false;

    // CONSTRUCTOR PARA INICIALIZAR OBJETOS
    public GameObject(Position pos, GameModel game) {
        this.isAlive = true;
        this.pos = pos;
        this.game = game;
    }

    // CONSTRUCTOR VACIO
    GameObject() {
    }

    // todos los objs se actualizan con su propio update
    // cada uno hace lo que le corresponde
    // si no hace nada, no se necesita implementar
    public abstract void update();

    public void addAction(List<Action> actionList) {

    }

    // PARA SABER EL ICONO DEL OBJETO
    public abstract String getIcon();

    // ESTAS FUNCIONES VAN AQUI???
    protected boolean wallNextTo(Action dir) {
        Position next = this.pos.move(dir);
        return next.isWall();
    }

    protected boolean solidNextTo(Action dir) {
        Position next = this.pos.move(dir);
        return this.game.isSolid(next);
    }

    protected boolean solidBelow() {
        Position below = this.pos.move(Action.DOWN);
        return this.game.isSolid(below);
    }

    public Position getPos() {
        return this.pos;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    protected void checkPosition() {
    }

    public void killMario() {
    }

    public void makeBig() {
    }

    public boolean itsMeMario() {
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////// GAMEITEM
    // TODAS LAS INTERACCIONES ESTÁN A FALSE PQ DEPENDIENDO DEL OBJETO SE MODIFICAN
    // Inicializado a false y cambiado en los override
    public boolean isSolid() {
        return false;
    }

    // Ver si sigue vivo despues de la interaccion
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public boolean interactWith(GameItem other) {
        return false;
    }

    // Chequear si dos objs estan en la misma posicion
    public boolean isInPosition(Position p) {
        return this.pos.equals(p);
    }

    public boolean receiveInteraction(Land obj) {
        return false;
    }

    public boolean receiveInteraction(ExitDoor obj) {
        return false;
    }

    public boolean receiveInteraction(Mario obj) {
        return false;
    }

    public boolean receiveInteraction(Goomba obj) {
        return false;
    }

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