package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class Box extends GameObject {

    private boolean empty;
    private static final String NAME = Messages.BOX_NAME;
    private static final String SHORTCUT = Messages.SHORTCUT_BOX;

    public Box(Position pos, GameWorld game, boolean empty) {
        super(pos, game);
        this.empty = empty;
    }

    public Box() {
    }

    @Override
    public void update() {
        // No hace nada
    }

    @Override
    public boolean receiveInteraction(Mario mario) {
        if (!empty) {
            this.empty = true;
            if (!mario.isBig()) {
                mario.makeBig();
            }
            return true;
        }
        return false;
    }

    @Override
    protected GameObject parse(String[] words, GameWorld game) {
        if (words.length != 3 && words.length != 2) {
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

            if (words.length == 2) { // Caja sin estado
                return new Box(pos, game, true);
            } else if (words.length == 3) {
                String empty; // crea la fuerza de caída que lleva en ese momento, que siempre va a ser 0
                empty = words[2]; // la lee, que normalmente es 0
                boolean isEmpty;
                if (empty.equalsIgnoreCase("FULL"))
                    isEmpty = true;
                else if (empty.equalsIgnoreCase("EMPTY"))
                    isEmpty = false;
                else
                    return null;

                return new Box(pos, game, isEmpty);
            }

        }
        return null;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public String getIcon() {
        return empty ? Messages.BOX_ABIERTO : Messages.BOX_CERRADO;
    }

    @Override
    protected String getName() {
        return NAME;
    }

    @Override
    protected String getShortCut() {
        return SHORTCUT;
    }

}
