package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
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
            game.addPoints(50);
            game.newMushroomAt(pos);
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

        if (words.length == 2) { // Caja sin estado explícito
            return new Box(pos, game, true);
        }

        String status = words[2];
        boolean isEmpty;
        if (status.equalsIgnoreCase("FULL"))
            isEmpty = true;
        else if (status.equalsIgnoreCase("EMPTY"))
            isEmpty = false;
        else
            throw new ObjectParseException(Messages.INVALID_BOX_STATUS.formatted(fullDescription));

        return new Box(pos, game, isEmpty);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public String getIcon() {
        if (empty)
            return Messages.BOX_ABIERTO;
        else
            return Messages.BOX_CERRADO;
    }

    @Override
    protected String getName() {
        return NAME;
    }

    @Override
    protected String getShortCut() {
        return SHORTCUT;
    }

    @Override
    public GameObject copy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copy'");
    }

}
