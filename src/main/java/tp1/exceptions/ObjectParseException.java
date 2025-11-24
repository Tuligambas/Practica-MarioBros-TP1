package tp1.exceptions;

public class ObjectParseException extends GameParseException {

    public ObjectParseException(String mensaje) {
        super(mensaje);
    }

    public ObjectParseException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
