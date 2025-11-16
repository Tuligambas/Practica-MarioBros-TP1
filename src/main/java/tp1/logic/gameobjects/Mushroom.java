package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mushroom extends MovingObject {
    private static final String ICON = Messages.MUSHROOM;
    private static final String NAME = Messages.MUSHROOM_NAME;
    private static final String SHORTCUT = Messages.SHORTCUT_MUSHROOM;

    public Mushroom(Position pos, GameWorld game) {
        super(pos, game, false, Action.RIGHT);
    }

    public Mushroom() {
    }

    public Mushroom(Position pos, Action dir, GameWorld game) {
        super(pos, game, false, dir);
    }

    @Override
    public void update() {
        automaticMovement();
    }

    @Override
    public String getIcon() {
        return ICON;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getShortCut() {
        return SHORTCUT;
    }

    @Override
    public boolean receiveInteraction(Mario mario) {
        if (this.pos.equals(mario.getPos())) {
            if (!mario.isBig()) {
                mario.makeBig();
            }
            this.setAlive(false);
            return true;
        }
        return false;
    }

    @Override
    protected GameObject parse(String[] words, GameWorld game) {
        if (words.length != 3 && words.length != 2) { // si no tiene 3 o 2 palabras, no es un goomba válido
            return null;
        }
        String nombre = words[1];
        if (matchObjectName(words[1])) {
            String[] w = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
            int fila = Integer.parseInt(w[0]);
            int col = Integer.parseInt(w[1]);
            Position pos = new Position(col, fila);
            if (!pos.isInBoard())
                return null;

            if (words.length == 2) {
                return new Mushroom(pos, game);
            }

            Action dir = Action.StringToDir(words[2].toUpperCase()); // convierte la tercera palabra en una
            if (dir != Action.LEFT && dir != Action.RIGHT)
                return null;

            return new Mushroom(pos, dir, game);
        }
        return null;
    }

}