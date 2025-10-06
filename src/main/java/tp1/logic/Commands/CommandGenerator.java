package tp1.logic.Commands;

import java.util.Arrays;
import java.util.List;

public class CommandGenerator {
    // LISTA DE COMANDOS DISPONIBLES
    private static final List<Commands> availableCommands = Arrays.asList(
            new UpdateCommand(),
            new ResetCommand(),
            new HelpCommand(),
            new ExitCommand());
    // new SetRoleCommand(),
    // new LoadCommand());

    // RECORRE LA LISTA DE LOS COMANDOS, COMPROBANDO SI LO QUE LE ENTRA ES UN
    // COMANDO CON EL PARSE DE CADA COMANDO
    public static Commands parse(String[] commandWords) {
        for (Commands c : availableCommands) {
            Commands commandDevuelto = c.parse(commandWords);
            if (commandDevuelto != null) { // devuelve el comando que coincide
                return commandDevuelto;
            }
        }
        return null;
    }

    // MUESTRA LOS MENSAJES DE HELP DE CADA COMANDO
    public static String commandHelp() {
        StringBuilder commands = new StringBuilder();
        for (Commands c : availableCommands) {
            commands.append(c.helpText()).append("");
        }
        return commands.toString();
    }
}
