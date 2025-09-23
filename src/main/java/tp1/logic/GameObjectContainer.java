package tp1.logic;

import tp1.logic.gameobjects.Land;

public class GameObjectContainer {
	//TODO fill your code

	private Land[] lands;   // array para guardar los Land
    private int landCount;  // número actual de Lands guardados
	private static final int MAX_LANDS = 50; // tamaño máximo, pon el que necesites


	public GameObjectContainer() {
        lands = new Land[MAX_LANDS];
        landCount = 0;
    }

	public void add(Land land) {
        if (landCount < MAX_LANDS) {
            lands[landCount] = land;
            landCount++;
        } else {
            System.out.println("No se pueden añadir más Land, array lleno");
        }
    }

}
