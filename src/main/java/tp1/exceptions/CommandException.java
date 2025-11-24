package tp1.exceptions;

public class CommandException extends Exception {
    public CommandException(String mensaje) {
        super(mensaje);
    }

    public CommandException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
