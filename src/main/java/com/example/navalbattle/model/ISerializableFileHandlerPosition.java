package com.example.navalbattle.model;

import com.example.navalbattle.controller.FleetController;

import java.util.ArrayList;

/**
 * Interface for handling the serialization and deserialization of fleet positions in the Naval Battle game.
 * Provides methods for saving and loading fleet coordinates to and from a file.
 *
 * This interface is designed to be implemented by classes that need to manage the persistence of game state.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public interface ISerializableFileHandlerPosition {

    /**
     * Serializes the fleet coordinates for both the enemy and the player to a specified file.
     *
     * @param fileName               the name of the file where the data will be stored.
     * @param fleetCoordinatesEnemy  the enemy fleet coordinates to serialize.
     * @param fleetCoordinatesPlayer the player fleet coordinates to serialize.
     */
    void serialize(String fileName, ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy, ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer);

    /**
     * Deserializes the fleet coordinates from a file and updates the provided fleet controller.
     *
     * @param fileName   the name of the file from which the data will be read.
     * @param controller the fleet controller to update with deserialized data.
     */
    void deserialize(String fileName, FleetController controller);
}
