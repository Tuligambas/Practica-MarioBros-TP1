package tp1.control.Commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public abstract class AbstractCommand implements Command {
    private final String name;
    private final String shorcut;
    private final String details;
    private final String help;

    // CONSTRUCTORA
    public AbstractCommand(String name, String shorcut, String details, String help) {
        this.name = name;
        this.shorcut = shorcut;
        this.details = details;
        this.help = help;
    }

    protected String getName() {
        return name;
    }

    protected String getShortcut() {
        return shorcut;
    }

    protected String getDetails() {
        return details;
    }

    protected String getHelp() {
        return help;
    }

    @Override
    public abstract void execute(GameModel game, GameView view) throws CommandExecuteException;

    @Override
    public abstract Command parse(String[] commandWords) throws CommandParseException;

    // COMPRUEBA SI EL NOMBRE QUE LE ENTRA ES UN COMANDO
    protected boolean matchCommandName(String name) {
        return getShortcut().equalsIgnoreCase(name) ||
                getName().equalsIgnoreCase(name);
    }

    @Override
    public String helpText() {
        return Messages.LINE_TAB.formatted(Messages.HELP.formatted(getDetails(), getHelp()));
    }
}
