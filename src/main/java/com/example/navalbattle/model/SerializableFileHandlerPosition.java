package com.example.navalbattle.model;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles the serialization and deserialization of fleet positions for the Naval Battle game.
 * This class provides methods to save and load fleet coordinates for both the enemy and player.
 *
 * The serialized data is stored in a file specified by the user, enabling persistence of game state.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class SerializableFileHandlerPosition {

    /**
     * Serializes the fleet coordinates for both the enemy and the player to a file.
     *
     * @param fileName               the name of the file where the data will be stored.
     * @param fleetCoordinatesEnemy  the enemy fleet coordinates to serialize.
     * @param fleetCoordinatesPlayer the player fleet coordinates to serialize.
     */
    public void serialize(String fileName, ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy, ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(fleetCoordinatesEnemy);  // Serialize enemy fleet coordinates.
            oos.writeObject(fleetCoordinatesPlayer); // Serialize player fleet coordinates.
            System.out.println("Fleet positions serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the fleet coordinates for both the enemy and the player from a file.
     *
     * @param fileName               the name of the file from which the data will be read.
     * @param fleetCoordinatesEnemy  the list to populate with deserialized enemy fleet coordinates.
     * @param fleetCoordinatesPlayer the list to populate with deserialized player fleet coordinates.
     */
    public void deserialize(String fileName, ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy, ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            fleetCoordinatesEnemy.clear();  // Clear the list before adding new data.
            fleetCoordinatesPlayer.clear();  // Clear the list before adding new data.
            fleetCoordinatesEnemy.addAll((ArrayList<ArrayList<Integer>>) ois.readObject()); // Deserialize enemy fleet coordinates.
            fleetCoordinatesPlayer.addAll((ArrayList<ArrayList<Integer>>) ois.readObject()); // Deserialize player fleet coordinates.
            System.out.println("Fleet positions deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
