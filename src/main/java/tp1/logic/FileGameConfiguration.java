package tp1.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tp1.exceptions.GameLoadException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.view.Messages;

public class FileGameConfiguration implements GameConfiguration {
    public static final FileGameConfiguration NONE = new FileGameConfiguration();
    private List<GameObject> objects;
    private int remainingTime, points, numLives;

    @Override
    public int getRemainingTime() {
        return remainingTime;
    }

    @Override
    public int points() {
        return points;
    }

    @Override
    public int numLives() {
        return numLives;
    }

    private FileGameConfiguration() {
    }

    public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException {
        try (BufferedReader entrada = new BufferedReader(new FileReader(fileName))) { // abre el archivo para lectura
            this.objects = new ArrayList<>(); // Se crea una nueva lista de objetos del juego, que es la que se copiará

            String linea = entrada.readLine(); // leo la primera línea, que tiene el tiempo restante,...
            parseDeEstado(linea); // parseo toda la primera línea

            linea = entrada.readLine(); // leo la siguiente línea

            while (linea != null && !linea.isEmpty()) { // si la líena que lee no es nula y no está vacía, entonces...
                String[] objWords = linea.trim().split("\\s+");
                GameObject obj = GameObjectFactory.parse(objWords, game); // parseo cada objeto objects.add(obj);
                objects.add(obj); // lo añado en la lista de objetos de copias
                linea = entrada.readLine(); // leo el siguiente objeto
            }
        } catch (FileNotFoundException a) { // se lanza si no encuentra el fichero
            throw new GameLoadException(Messages.FILE_NOT_FOUND.formatted(fileName), a);
        } catch (IOException e) { // se lanza si el fichero no se puede leer bien
            throw new GameLoadException(Messages.READ_ERROR.formatted(fileName), e);
        } catch (ObjectParseException | OffBoardException o) { // se lanza si no puede guardar correctamente el objeto
                                                               // ya que éste tiene un nombre o posición incorrecta
            throw new GameLoadException(Messages.INVALID_FILE_CONFIGURATION.formatted(fileName), o);
        }
    }

    public void parseDeEstado(String linea) throws GameLoadException {
        String[] parts = linea.trim().split("\\s+");
        try {
            if (parts.length == 3) { // guarda todos los valores de la primera línea
                remainingTime = Integer.parseInt(parts[0]);
                points = Integer.parseInt(parts[1]);
                numLives = Integer.parseInt(parts[2]);
            } else { // se lanza si la primera línea no tiene 3 palabras
                throw new GameLoadException(Messages.INCORRECT_GAME_STATUS.formatted(linea));
            }
        } catch (NumberFormatException e) { // se lanza si algo que lee no es un número
            throw new GameLoadException(Messages.INCORRECT_GAME_STATUS.formatted(linea));
        }
    }

    @Override
    public List<GameObject> getObjects() {
        List<GameObject> container = new ArrayList<>();
        for (GameObject obj : objects) {
            container.add(obj.copy());
        }
        return container;
    } // este método se llama en el load de game
}
