package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {
    protected boolean isfalling;
    protected Action dir;

    public MovingObject(Position position, Game game, boolean isfalling, Action dir) {
        super(position, game);
        this.isfalling = isfalling;
        this.dir = dir;
    }

    // CONSTRUCTOR VACIO
    public MovingObject() {
    }

    public void automaticMovement() {
        if (solidBelow()) {// si es solido abajo dos opciones
            if (solidNextTo(dir) || wallNextTo(dir)) // si es solido a la izquierda o derecha cambia de direccion
                dir = dir.opposite();

            // si no es solido a la izquierda o derecha se mueve
            else {
                move();
            }
        }
        // si no es solido abajo cae
        else {
            this.isfalling = true;
            fall();
        }
    }

    private void move() {
        this.pos = this.pos.move(dir);
    }

    private void fall() {
        this.pos = this.pos.move(Action.DOWN);
        if (solidBelow()) {
            this.isfalling = false;
        }
    }

}
