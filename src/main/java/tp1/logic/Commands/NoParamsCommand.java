package tp1.logic.Commands;

import tp1.view.Messages;

public abstract class NoParamsCommand extends AbstractCommand {

    // CONSTRUCTORA
    public NoParamsCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    @Override
    public Commands parse(String[] commandWords) {
        this.valid = false;
        // no hay palabras o el comando no coincide
        if (commandWords.length == 0 || !matchCommandName(commandWords[0])) {
            return null;
        }

        // comando correcto pero con más parámetros de los esperados
        if (commandWords.length > 1) {
            System.out.println(Messages.ERROR.formatted(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
            return this;
        }

        // comando correcto
        this.valid = true;
        return this;
    }
}