package tp1.control.Commands;

import java.util.Arrays;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class AddObjectCommand extends AbstractCommand /* NoParamsCommand */ { // ver si esta bien
    public static final String NAME = Messages.COMMAND_ADD_NAME;
    public static final String SHORTCUT = Messages.COMMAND_ADD_SHORTCUT;
    public static final String DETAILS = Messages.COMMAND_ADD_DETAILS;
    public static final String HELP = Messages.COMMAND_ADD_HELP;
    String[] infoObj;

    // CONSTRUCTORA CON LA INFORMACION DEL OBJETO A AÑADIR
    public AddObjectCommand(String[] infoObj) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.infoObj = infoObj;
    }

    // CONSTRUCTOR NORMAL
    public AddObjectCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        try {
            game.addObject(infoObj);
            view.showGame();
        } catch (Exception e) {
            throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, e);
        }
    }

    @Override
    public Command parse(String[] words) throws CommandParseException {
        if (words.length == 0 || !matchCommandName(words[0]))
            return null;

        // Necesitamos al menos posición y nombre
        if (words.length < 3)
            throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);

        // Copiamos SOLO los parámetros después del nombre del comando
        String[] params = Arrays.copyOfRange(words, 1, words.length);
        // he cambiado el nombre a parms para que no se repita con infoObj
        return new AddObjectCommand(params);
    }

}
