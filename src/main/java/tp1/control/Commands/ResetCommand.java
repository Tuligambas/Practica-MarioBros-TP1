package tp1.control.Commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand/* NoParamsCommand */ { // A lo mejor extiende de AbstractCommand y no
                                                                         // de noParams
    private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;
    private boolean variousParameters;
    private int level;

    // CONSTRUCTORA
    public ResetCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    // llama al game.reset para que se reinicie el juego
    @Override
    public void execute(GameModel game, GameView view) {
        if (variousParameters == false) {
            game.reset();
            view.showGame();
        } else if (level == -1 || level == 0 || level == 1 || level == 2) {
            game.reset(level);
            view.showGame();
        }
    }

    @Override
    public Command parse(String[] commandWords) {
        // no hay palabras o el comando no coincide
        if (commandWords.length == 0 || !matchCommandName(commandWords[0]))
            return null;

        this.variousParameters = false;

        // comando correcto pero con más parámetros de los esperados
        if (commandWords.length > 1) {
            this.variousParameters = true;
            this.level = Integer.parseInt(commandWords[1]);
            if (this.level != 0 && this.level != 1 && this.level != -1 && this.level != 2) {
                System.out.println("[ERROR] Error: Not valid level number");
            }
        }

        // comando correcto
        return this;
    }
}