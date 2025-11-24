package tp1.control.Commands;

import java.util.ArrayList;
import java.util.List;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.logic.Action;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ActionCommand extends AbstractCommand { // He cambiado el noparams a abstract como dice el enunciado
    private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    private List<Action> actions;

    // CONSTRUCTOR
    public ActionCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {

        if (commandWords.length == 0 || !matchCommandName(commandWords[0]))
            return null;

        if (commandWords.length < 2)
            throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);

        actions = new ArrayList<>();

        // Si tiene más palabras, las intentamos convertir a acciones
        for (int i = 1; i < commandWords.length; i++) {
            Action act = Action.StringToDir(commandWords[i]);
            if (act == null)
                throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(String.join(" ", commandWords)));
            actions.add(act);

        }

        ActionCommand cmd = new ActionCommand();
        cmd.actions = actions;

        return cmd;
    }

    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        try {
            game.addActions(actions);
            game.update();
            view.showGame();
        } catch (Exception e) {
            throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, e);
        }
    }

}
