package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
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
        if (mario.isInPosition(pos)) {
            if (!mario.isBig()) {
                mario.makeBig();
            }
            this.setAlive(false);
            return true;
        }
        return false;
    }

    @Override
    protected GameObject parse(String[] words, GameWorld game) throws ObjectParseException, OffBoardException {
        if (words.length < 2 || !matchObjectName(words[1]))
            return null;

        String fullDescription = String.join(" ", words);
        if (words.length > 3)
            throw new ObjectParseException(Messages.OBJECT_PARSE_ERROR_TOO_MUCH_ARGS.formatted(fullDescription));

        Position pos;
        try {
            String[] w = words[0].replace("(", " ").replace(",", " ").replace(")", " ").strip().split("( )+");
            int fila = Integer.parseInt(w[0]);
            int col = Integer.parseInt(w[1]);
            pos = new Position(col, fila);
        } catch (Exception e) {
            throw new ObjectParseException(Messages.INVALID_OBJECT_POSITION.formatted(fullDescription), e);
        }

        if (!pos.isInBoard())
            throw new OffBoardException(Messages.OBJECT_POSITION_OFF_BOARD.formatted(fullDescription));

        if (words.length == 2) {
            return new Mushroom(pos, game);
        }

        Action dir = Action.StringToDir(words[2].toUpperCase()); // convierte la tercera palabra en una
        if (dir == null) {
            ObjectParseException cause = new ObjectParseException(Messages.UNKNOWN_ACTION.formatted(words[2]));
            throw new ObjectParseException(Messages.UNKNOWN_MOVING_OBJECT_DIRECTION.formatted(fullDescription), cause);
        }
        if (dir != Action.LEFT && dir != Action.RIGHT)
            throw new ObjectParseException(Messages.INVALID_MOVING_OBJECT_DIRECTION.formatted(fullDescription));

            return new Mushroom(pos, dir, game);
        }return null;
}

}