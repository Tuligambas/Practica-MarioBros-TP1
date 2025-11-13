package tp1.control.Commands;

import java.util.Arrays;

import tp1.logic.GameModel;
import tp1.logic.gameobjects.GameObject;
import tp1.view.GameView;
import tp1.view.Messages;

public class AddObjectCommand extends NoParamsCommand {
    public static final String NAME = Messages.COMMAND_LOAD_NAME;
    public static final String SHORTCUT = Messages.COMMAND_LOAD_SHORTCUT;
    public static final String DETAILS = Messages.COMMAND_LOAD_DETAILS;
    public static final String HELP = Messages.COMMAND_LOAD_HELP;
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
    public void execute(GameModel game, GameView view) {
        String fullDescription = String.join(" ", infoObj);
        GameObject obj = game.parseObject(infoObj);

        if (obj == null) {
            view.showMessage("Invalid game object: " + fullDescription);
            return;
        }

        game.addObject(obj);
        view.showGame();
    }

    @Override
    public Command parse(String[] words) {
        if (words.length == 0 || !matchCommandName(words[0]))
            return null;

        // Si el usuario solo puso addobject
        if (words.length < 2)
            return null;

        // Copiamos SOLO los parámetros después del nombre del comando
        String[] infoObj = Arrays.copyOfRange(words, 1, words.length);

        return new AddObjectCommand(infoObj);
    }

}
