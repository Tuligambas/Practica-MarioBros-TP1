package tp1.control.Commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class UpdateCommand extends NoParamsCommand {
    private static final String NAME = Messages.COMMAND_UPDATE_NAME;
    private static final String SHORTCUT = Messages.COMMAND_UPDATE_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_UPDATE_DETAILS;
    private static final String HELP = Messages.COMMAND_UPDATE_HELP;

    // CONSTRUCTORA
    public UpdateCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    // llama al game.update para que se actualice el juego
    @Override
    public void execute(GameModel game, GameView view) {
        game.update();
        view.showGame();

    }

    // es diferente porque si no le entra nada también se updatea
    @Override
    public boolean matchCommandName(String name) {
        return super.matchCommandName(name) || name.equals("");
    }

}
