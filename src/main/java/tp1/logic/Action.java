package tp1.logic;

/**
 * Represents the allowed actions in the game
 *
 */
public enum Action {
	LEFT(-1, 0), RIGHT(1, 0), DOWN(0, 1), UP(0, -1), STOP(0, 0);

	private int x;
	private int y;

	private Action(int x, int y) {
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
	public static Action StringToDir(String nombre) {
		nombre = nombre.toUpperCase();
		switch (nombre) {
			case "RIGHT", "R" -> {
				return RIGHT;
			}
			case "LEFT", "L" -> {
				return LEFT;
			}
			case "UP", "U" -> {
				return UP;
			}
			case "DOWN", "D" -> {
				return DOWN;
			}
			case "STOP", "S" -> {
				return STOP;
			}
			default -> {
			}
		}
		return null;
	}

	public Action opposite() {
		switch (this) {
			case RIGHT:
				return Action.LEFT;
			case LEFT:
				return Action.RIGHT;
			case UP:
				return Action.DOWN;
			case DOWN:
				return Action.UP;
			default:
				return Action.STOP;
		}
	}

	public boolean isHorizontal() {
		return this.equals(Action.RIGHT) || this.equals(Action.LEFT);
	}

}
