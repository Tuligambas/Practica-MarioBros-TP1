package tp1.control.Commands;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends NoParamsCommand {
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
    public void execute(Game game, GameView view) {
        // if (this.valid) {
        if (this.variousParameters)
            game.reset(this.level);
        else
            game.reset();
        view.showGame();
        // }
    }

    @Override
    public Command parse(String[] commandWords) {
        this.valid = false;
        // no hay palabras o el comando no coincide
        if (commandWords.length == 0 || !matchCommandName(commandWords[0]))
            return null;

        this.variousParameters = false;

        // comando correcto pero con más parámetros de los esperados
        if (commandWords.length > 1) {
            this.variousParameters = true;
            int nivel = Integer.parseInt(commandWords[1]);
            if (nivel == 0 || nivel == 1)
                this.level = nivel;
            return this;
        }

        // comando correcto
        this.valid = true;
        return this;
    }
}