<<<<<<< HEAD:src/main/java/tp1/logic/Commands/AbstractCommand.java
package tp1.logic.Commands;

import tp1.view.Messages;

public abstract class AbstractCommand implements Commands {
=======
package tp1.control.Commands;

import tp1.logic.Game;
import tp1.view.GameView;
<<<<<<<< HEAD:src/main/java/tp1/logic/Commands/Commands.java
========
import tp1.view.Messages;

public abstract class AbstractCommand implements Command {
>>>>>>> a3ab7fa22373ec8987f68dd9953fc3101d7612be:src/main/java/tp1/control/Commands/AbstractCommand.java
    private final String name;
    private final String shorcut;
    private final String details;
    private final String help;
    protected boolean valid = false;

    // CONSTRUCTORA
<<<<<<< HEAD:src/main/java/tp1/logic/Commands/AbstractCommand.java
    public AbstractCommand (String name, String shorcut, String details, String help) {
=======
    public AbstractCommand(String name, String shorcut, String details, String help) {
>>>>>>> a3ab7fa22373ec8987f68dd9953fc3101d7612be:src/main/java/tp1/control/Commands/AbstractCommand.java
        this.name = name;
        this.shorcut = shorcut;
        this.details = details;
        this.help = help;
        this.valid = false;
    }

    protected String getName() {
        return name;
    }

    protected String getShortcut() {
        return shorcut;
    }

    protected String getDetails() {
        return details;
    }

    protected String getHelp() {
        return help;
    }

<<<<<<< HEAD:src/main/java/tp1/logic/Commands/AbstractCommand.java
=======
    @Override
    public abstract void execute(Game game, GameView view);

    @Override
    public abstract Command parse(String[] commandWords);

>>>>>>> a3ab7fa22373ec8987f68dd9953fc3101d7612be:src/main/java/tp1/control/Commands/AbstractCommand.java
    // COMPRUEBA SI EL NOMBRE QUE LE ENTRA ES UN COMANDO
    protected boolean matchCommandName(String name) {
        return getShortcut().equalsIgnoreCase(name) ||
                getName().equalsIgnoreCase(name);
    }

<<<<<<< HEAD:src/main/java/tp1/logic/Commands/AbstractCommand.java
      public String helpText() {
        return Messages.LINE_TAB.formatted(Messages.HELP.formatted(getDetails(), getHelp()));
    }
=======
    @Override
    public String helpText() {
        return Messages.LINE_TAB.formatted(Messages.HELP.formatted(getDetails(), getHelp()));
    }
>>>>>>>> a3ab7fa22373ec8987f68dd9953fc3101d7612be:src/main/java/tp1/control/Commands/AbstractCommand.java

public interface Commands {
     void execute(Game game, GameView view);
     Commands parse(String[] commandWords);
     String helpText();
>>>>>>> a3ab7fa22373ec8987f68dd9953fc3101d7612be:src/main/java/tp1/control/Commands/AbstractCommand.java
}
