package tp1.control.Commands;

import java.util.Arrays;
import java.util.List;

public class CommandGenerator {
    // LISTA DE COMANDOS DISPONIBLES
    private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
            new ActionCommand(),
            new UpdateCommand(),
            new ResetCommand(),
            new HelpCommand(),
            new AddObjectCommand(),
            new ExitCommand());

    // RECORRE LA LISTA DE LOS COMANDOS, COMPROBANDO SI LO QUE LE ENTRA ES UN
    // COMANDO CON EL PARSE DE CADA COMANDO
    public static Command parse(String[] commandWords) {
        for (Command c : AVAILABLE_COMMANDS) {
            Command commandDevuelto = c.parse(commandWords);
            if (commandDevuelto != null) { // devuelve el comando que coincide
                return commandDevuelto;
            }
        }
        return null;
    }

    // MUESTRA LOS MENSAJES DE HELP DE CADA COMANDO
    public static String commandHelp() {
        StringBuilder commands = new StringBuilder();
        for (Command c : AVAILABLE_COMMANDS) {
            commands.append(c.helpText()).append("");
        }
        return commands.toString();
    }

    public static List<Command> getAvailableCommands() {
        return AVAILABLE_COMMANDS;
    }
}
