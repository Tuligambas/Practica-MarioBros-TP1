package tp1.exceptions;

public class GameParseException extends GameModelException {

    public GameParseException(String mensaje) {
        super(mensaje);
    }

    public GameParseException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}