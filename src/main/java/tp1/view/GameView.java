package tp1.view;

// al igual cambiar game por gamestatus
import tp1.logic.GameStatus;

public abstract class GameView implements ViewInterface {

	protected GameStatus game;

	public GameView(GameStatus game) {
		this.game = game;
	}

}
