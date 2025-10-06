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

	// Método que devuelve la dirección contraria
	public Action opposite(Action dir) {
		Action r;
		if (dir == Action.RIGHT) {
			r = Action.LEFT;
		} else {
			r = Action.RIGHT;
		}
		return r;
	}

}
