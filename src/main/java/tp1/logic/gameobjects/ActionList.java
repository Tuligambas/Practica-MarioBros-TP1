package tp1.logic.gameobjects;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.Action;

public class ActionList {
    private List<Action> actions;
    private Action firstHorizontal; // para los casos que hace left + right
    private Action firstVertical; // para los casos que hace up + down
    private int horizontalCount; // maximo 4 de estos
    private int verticalCount; // maximo 4 de estos

    public ActionList() {
        this.actions = new ArrayList<>();
        this.firstHorizontal = null;
        this.firstVertical = null;
        this.horizontalCount = 0;
        this.verticalCount = 0;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
        if (action == null)
            return;

        switch (action) {
            case LEFT:
            case RIGHT:
                if (firstHorizontal == null) {
                    firstHorizontal = action; // primera dirección horizontal
                } else if (isOpposite(firstHorizontal, action)) {
                    return; // si es opuesta, se ignora
                }
                if (horizontalCount < 4) {
                    actions.add(action);
                    horizontalCount++;
                }
                break;

            case UP:
            case DOWN:
                if (firstVertical == null) {
                    firstVertical = action; // primera vertical
                } else if (isOpposite(firstVertical, action)) {
                    return; // si es opuesta, se ignora
                }
                if (verticalCount < 4) {
                    actions.add(action);
                    verticalCount++;
                }
                break;

            default:
                // casos normales (stop...)
                actions.add(action);
                break;
        }
    }

    private boolean isOpposite(Action a1, Action a2) {
        return (a1 == Action.LEFT && a2 == Action.RIGHT) ||
                (a1 == Action.RIGHT && a2 == Action.LEFT) ||
                (a1 == Action.UP && a2 == Action.DOWN) ||
                (a1 == Action.DOWN && a2 == Action.UP);
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public Action getNext() {
        if (actions.isEmpty())
            return null;
        return actions.remove(0);
    }

}
