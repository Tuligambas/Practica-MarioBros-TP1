package tp1.control.Commands;

import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand/* NoParamsCommand */ { // A lo mejor extiende de AbstractCommand y no
                                                                         // de noParams
    private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;
    private static final int NO_LEVEL = -2;
    private int level;

    // CONSTRUCTORA
    public ResetCommand(int level) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.level = level;
    }

    // Constructora sin nivel: se usa -2 para reiniciar el nivel actual por defecto
    protected ResetCommand() {
        this(NO_LEVEL);
    }

    // llama al game.reset para que se reinicie el juego
    @Override
    public void execute(GameModel game, GameView view) {
        boolean exito = game.reset(this.level);
        if (exito)
            view.showGame();
        else
            view.showError(Messages.INVALID_LEVEL_NUMBER);
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        // no hay palabras o el comando no coincide
        if (commandWords.length == 0 || !matchCommandName(commandWords[0]))
            return null;

        if (commandWords.length > 2) { // si se escriben más de 2 palabras, lanza una excepción
            throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
        }
        try {
            if (commandWords.length == 1) { // devuelve la constructora sin nivel,reset el nivel en el que está
                return new ResetCommand();
            } else { // si le entra reset y un nivel, parseará el nivel que le entra, que tiene q ser
                     // -1, 0, 1 o 2
                if (Integer.parseInt(commandWords[1]) <= 2
                        && Integer.parseInt(commandWords[1]) >= -1) {
                    return new ResetCommand(Integer.parseInt(commandWords[1])); // devuelve constructora reset con nivel
                                                                                // que ha parseado
                } else { // si el nivel no está entre -1 y 2, lanza la excepción
                    throw new CommandParseException(Messages.INVALID_LEVEL_NUMBER.formatted(commandWords[1]));
                }
            }
        } catch (NumberFormatException e) { // se lanza cuando el nivel no tiene formato válido (es una letra)
            throw new CommandParseException(Messages.LEVEL_NOT_A_NUMBER_ERROR.formatted(commandWords[1]), e);
        }
    }
}
