package tp1.logic.Commands;

public abstract class NoParamsCommand extends Commands {

    // CONSTRUCTORA
    public NoParamsCommand(String name, String shortcut, String details, String help) {
        super(name, shortcut, details, help);
    }

    @Override
    public Commands parse(String[] commandWords) {
        if (commandWords.length < 1 || !matchCommandName(commandWords[0])) // si no le entra nada, no devuelve nada
            return null;

        if (commandWords.length == 1 && matchCommandName(commandWords[0])) // si lo que le entra coincide con un
                                                                           // comando, devuelve ese comando
            return this;

        return null; // si le entra mas de un parametro, no devuelve nada
    }
}