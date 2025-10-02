package tp1.logic.Commands;

import tp1.logic.Game;
import tp1.view.Messages;

public class HelpCommand extends NoParamsCommand {
    private static final String NAME = Messages.COMMAND_HELP_NAME;
    private static final String SHORTCUT = Messages.COMMAND_HELP_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_HELP_DETAILS;
    private static final String HELP = Messages.COMMAND_HELP_HELP;

    // CONSTRUCTORA
    public HelpCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    @Override
    public void execute(Game game) {
        game.showHelp();
    }

}
