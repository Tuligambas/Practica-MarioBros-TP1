package tp1.exceptions;

public class CommandParseException extends CommandException {

    public CommandParseException(String mensaje) {
        super(mensaje);

    }

    public CommandParseException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}