package tp1.control;

import tp1.logic.Commands.CommandGenerator;
import tp1.logic.Commands.Commands;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic
 */
public class Controller {

  private Game game;
  private GameView view;

  public Controller(Game game, GameView view) {
    this.game = game;
    this.view = view;
  }

  public void run() {
    String[] words = null;

    view.showWelcome();
    view.showGame();
    while (!game.isFinished()) {
      // coge lo que has escrito
      words = view.getPrompt();

      // crea el comando y lo ejecuta
      Commands command = CommandGenerator.parse(words);
      if (command != null)
        command.execute(game, view);
      else
        view.showMessage(Messages.UNKNOWN_COMMAND);

    }
    view.showEndMessage();
  }
}
