package tp1.control;

import tp1.control.Commands.Command;
import tp1.control.Commands.CommandGenerator;
import tp1.exceptions.CommandException;
import tp1.logic.GameModel;
import tp1.view.GameView;

/**
 * Accepts user input and coordinates the game execution logic
 */
public class Controller {

  private GameModel game;
  private GameView view;

  public Controller(GameModel game, GameView view) {
    this.game = game;
    this.view = view;
  }

  public void run() {
    String[] words = null;

    view.showWelcome();

    view.showGame();
    while (!game.isFinished()) {
      try {
        words = view.getPrompt();
        Command command = CommandGenerator.parse(words);
        if (command != null)
          command.execute(game, view);
      } catch (CommandException e) {
        view.showError(e.getMessage());
        Throwable cause = e.getCause();
        while (cause != null) {
          view.showError(cause.getMessage());
          cause = cause.getCause();
        }
      }
    }
    view.showEndMessage();
  }
}
