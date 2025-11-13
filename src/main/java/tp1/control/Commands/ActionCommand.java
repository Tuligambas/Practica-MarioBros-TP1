package tp1.control.Commands;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.Action;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ActionCommand extends AbstractCommand { // He cambiado el noparams a abstract como dice el enunciado
    private static final String NAME = Messages.COMMAND_ACTION_NAME;
    private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
    private static final String HELP = Messages.COMMAND_ACTION_HELP;

    private boolean incorrectParameters;
    private boolean missingParameters;
    private List<Action> actions;

    // CONSTRUCTOR
    public ActionCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    @Override
    public Command parse(String[] commandWords) {
        actions = new ArrayList<>();
        incorrectParameters = false;
        missingParameters = false;
        this.valid = false;

        if (!matchCommandName(commandWords[0])) {
            return null;
        }

        if (commandWords.length < 2) {
            System.out.println(Messages.ERROR.formatted(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
            this.missingParameters = true;
            return this;
        }

        // Si tiene más palabras, las intentamos convertir a acciones
        for (int i = 1; i < commandWords.length; i++) {
            Action act = Action.StringToDir(commandWords[i]);
            if (act != null) {
                actions.add(act);
            } else {
                System.out.println(Messages.ERROR.formatted(Messages.UNKNOWN_ACTION.formatted(commandWords[i])));
                this.incorrectParameters = true;
            }
        }

        ActionCommand cmd = new ActionCommand();
        cmd.actions = actions;
        cmd.incorrectParameters = this.incorrectParameters;
        cmd.missingParameters = this.missingParameters;

        return cmd;
    }

    @Override
    public void execute(GameModel game, GameView view) {
        if (missingParameters) {
            return;
        }

        game.addActions(actions);
        game.update();
        view.showGame();
    }

}
