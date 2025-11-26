package tp1.exceptions;

public class GameLoadException extends Exception {

    public GameLoadException(String mensaje) {
        super(mensaje);
    }

    public GameLoadException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
