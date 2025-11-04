package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;

public abstract class GameObject {
    protected Position pos;
    protected boolean isAlive;
    protected Game game;

    // CONSTRUCTOR PARA INICIALIZAR OBJETOS
    public GameObject(Position pos, Game game) {
        this.isAlive = true;
        this.pos = pos;
        this.game = game;
    }

    // CONSTRUCTOR VACIO
    GameObject() {
    }

    // Chequear si dos objs estan en la misma posicion
    public boolean isInPosition(Position p) {
        return this.pos.equals(p);
    }

    // Ver si sigue vivo despues de la interaccion
    public boolean isAlive() {
        return isAlive;
    }

    // Inicializado a false y cambiado en los override
    public boolean isSolid() {
        return false;
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

    // TODAS LAS INTERACCIONES ESTÁN A FALSE PQ DEPENDIENDO DEL OBJETO SE MODIFICAN
    public boolean receiveInteraction(GameObject other) {
        return false;
    }

    public boolean interactWith(Mario mario) {
        return false;
    }

    public boolean interactWith(Land land) {
        return false;
    }

    public boolean interactWith(Goomba goomba) {
        return false;
    }

    public boolean itsMeMario() {
        return false;
    }

    public boolean interactWith(ExitDoor door) {
        return false;
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

}
