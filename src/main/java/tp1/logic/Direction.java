package tp1.logic;

public enum Direction {
    LEFT(-1, 0), RIGHT(1, 0), DOWN(0, 1), UP(0, -1), NONE(0, 0);

    private int x;
    private int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Método que convierte un string en una dirección
    public static Direction StringToDir(String nombre) {
        if (nombre.equals("RIGHT")) {
            return RIGHT;
        } else if (nombre.equals("LEFT")) {
            return LEFT;
        } else if (nombre.equals("UP")) {
            return UP;
        } else if (nombre.equals("DOWN")) {
            return DOWN;
        }
        return null;
    }
}
