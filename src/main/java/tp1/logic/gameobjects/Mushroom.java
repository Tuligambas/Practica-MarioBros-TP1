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
        if (words.length != 2)
            return null;
        String nombre = words[1];
        if (matchObjectName(words[1])) {
            String[] w = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
            int fila = Integer.parseInt(w[0]);
            int col = Integer.parseInt(w[1]);
            Position pos = new Position(col, fila);

            if (pos.isInBoard())
                return new Mushroom(pos, game);

        }
        return null;
    }

}