package tp1.view;

// al igual cambiar game por gamestatus
import tp1.logic.Game;

public abstract class GameView implements ViewInterface {

	protected Game game;

	public GameView(Game game) {
		this.game = game;
	}

}
