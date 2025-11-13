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
        if (this.isInPosition(mario.getPos()) && !empty) {
            this.empty = true;
            return true;
        }
        return false;
    }

    @Override
    protected GameObject parse(String[] objWords, GameWorld game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parse'");
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
