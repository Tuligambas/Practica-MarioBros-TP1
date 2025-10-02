package tp1.logic.gameobjects;

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

    // PARA SABER EL ICONO DEL OBJETO
    public abstract String getIcon();

}
