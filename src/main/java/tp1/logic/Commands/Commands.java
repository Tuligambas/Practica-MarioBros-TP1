package tp1.logic.Commands;

import tp1.logic.Game;
import tp1.view.GameView;

public interface Commands {
     void execute(Game game, GameView view);
     Commands parse(String[] commandWords);
     String helpText();
}
