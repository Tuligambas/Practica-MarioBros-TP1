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

        if (this.isInPosition(mario.getPos())) {
            if (!mario.isBig()) {
                mario.makeBig();
            }
            this.setAlive(false);
            return true;
        }
        return false;
    }

    @Override
    protected GameObject parse(String[] objWords, GameWorld game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parse'");
    }

}