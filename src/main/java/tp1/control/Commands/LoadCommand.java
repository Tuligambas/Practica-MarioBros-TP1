package tp1.control.Commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameLoadException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class LoadCommand extends NoParamsCommand {
    public static final String NAME = Messages.COMMAND_LOAD_NAME;
    public static final String SHORTCUT = Messages.COMMAND_LOAD_SHORTCUT;
    public static final String DETAILS = Messages.COMMAND_LOAD_DETAILS;
    public static final String HELP = Messages.COMMAND_LOAD_HELP;
    String filename;

    // CONSTRUCTORA CON EL NOMBRE DEL FICHERO QUE LE ENTRA (la devuelve el parse)
    public LoadCommand(String filename) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.filename = filename;
    }

    // CONSTRUCTOR NORMAL
    public LoadCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        try {
            game.load(this.filename);
            view.showGame();
        } catch (GameLoadException e) { // si no puede cargar el juego, lanza una excepción
            throw new CommandExecuteException(Messages.UNABLE_TO_LOAD_GAME_CONFIGURATION.formatted(filename), e);
        }
    }

    @Override
    public Command parse(String[] parameter) throws CommandParseException {
        if (parameter.length == 0 || !matchCommandName(parameter[0])) { // si no se escribe nada o no coincide con
                                                                        // ningún comando, no devuelve nada
            return null;
        }
        if (parameter.length != 2) { // si le entra otro número diferente de 2, lanza una excepción
            throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
        } // si todo es correcto, devuelve una constructora con el nombre del fichero que
          // le ha entrado
        return new LoadCommand(parameter[1]);
    }

}
