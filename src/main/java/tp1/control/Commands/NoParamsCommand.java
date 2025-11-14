package tp1.control.Commands;

public abstract class NoParamsCommand extends AbstractCommand {

    // CONSTRUCTORA
    public NoParamsCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    @Override
    public Command parse(String[] commandWords) {
        // no hay palabras o el comando no coincide
        if (commandWords.length == 0 || !matchCommandName(commandWords[0]) || commandWords.length > 1) {
            return null;
        }

        return this;
    }
}