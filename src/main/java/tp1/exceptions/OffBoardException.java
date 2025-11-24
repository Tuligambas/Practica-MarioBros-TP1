package tp1.exceptions;

public class OffBoardException extends GameModelException {

    public OffBoardException(String mensaje) {
        super(mensaje);
    }

    public OffBoardException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

}
