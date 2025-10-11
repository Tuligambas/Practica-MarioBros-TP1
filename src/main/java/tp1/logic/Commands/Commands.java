package tp1.logic.Commands;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

public abstract class Commands {
    private final String name;
    private final String shorcut;
    private final String details;
    private final String help;
    protected boolean valid = false;

    // CONSTRUCTORA
    public Commands(String name, String shorcut, String details, String help) {
        this.name = name;
        this.shorcut = shorcut;
        this.details = details;
        this.help = help;
        this.valid = false;
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

    public abstract void execute(Game game, GameView view);

    public abstract Commands parse(String[] commandWords);

    // COMPRUEBA SI EL NOMBRE QUE LE ENTRA ES UN COMANDO
    protected boolean matchCommandName(String name) {
        return getShortcut().equalsIgnoreCase(name) ||
                getName().equalsIgnoreCase(name);
    }

    public String helpText() {
        return Messages.LINE_TAB.formatted(Messages.HELP.formatted(getDetails(), getHelp()));
    }

}
