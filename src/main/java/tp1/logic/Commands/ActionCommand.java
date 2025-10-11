package tp1.logic.Commands;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

public class ActionCommand extends NoParamsCommand {
    private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;
    private List<Action> actions;

    // CONSTRUCTORA
    public ActionCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    @Override
    public Commands parse(String[] commandWords) {
        actions = new ArrayList<>();
        if (!matchCommandName(commandWords[0]))
            return null;
        else {
            this.valid = true;
            if (commandWords.length < 2) // minimo
                System.out.println(Messages.ERROR.formatted(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));

            for (int i = 1; i < commandWords.length; i++) {
                Action act = Action.StringToDir(commandWords[i]); // convierte texto en enum
                if (act != null)
                    actions.add(act); // añade acción válida
                else {
                    System.out.println(Messages.ERROR.formatted(Messages.UNKNOWN_ACTION.formatted(commandWords[i])));
                }
            }

            ActionCommand cmd = new ActionCommand();
            cmd.actions = actions;

            return cmd;
        }
    }

    @Override
    public void execute(Game game, GameView view) {
        game.addActions(actions);
        if (valid) {
            game.update();
            view.showGame();
        }
    }

}
