package tp1.exceptions;

public class CommandExecuteException extends CommandException {

    public CommandExecuteException(String mensaje) {
        super(mensaje);
    }

    public CommandExecuteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}