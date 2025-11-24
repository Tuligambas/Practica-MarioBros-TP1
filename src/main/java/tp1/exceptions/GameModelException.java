package tp1.exceptions;

public class GameModelException extends Exception {

    public GameModelException(String mensaje) {
        super(mensaje);
    }

    public GameModelException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
