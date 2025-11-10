package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class MovingObject extends GameObject {
    protected boolean isfalling;
    protected Action dir;

    public MovingObject(Position position, GameWorld game, boolean isfalling, Action dir) {
        super(position, game);
        this.isfalling = isfalling;
        this.dir = dir;
    }

    // CONSTRUCTOR VACIO
    public MovingObject() {
    }

    // ESTAS FUNCIONES VAN AQUI???
    protected boolean wallNextTo(Action dir) {
        Position next = this.pos.move(dir);
        return next.isWall();
    }

    protected boolean solidBelow() {
        Position below = this.pos.move(Action.DOWN);
        return this.game.isSolid(below);
    }

    protected boolean solidNextTo(Action dir) {
        Position next = this.pos.move(dir);
        return this.game.isSolid(next);
    }

    @Override
    public boolean isSolid(Position pos) {
        Position next = this.pos.move(dir);
        return this.game.isSolid(next);
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
