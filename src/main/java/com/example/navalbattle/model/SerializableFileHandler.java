package com.example.navalbattle.model;

import java.io.*;
import java.util.ArrayList;

/**
 * A handler class for serializing and deserializing the game state to and from a file.
 * This implementation saves and restores the player's and enemy's boards.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class SerializableFileHandler implements ISerializableFileHandler {

    /**
     * Serializes the player's and enemy's boards to a file.
     *
     * @param fileName The name of the file where the boards will be saved.
     * @param game     The game instance containing the boards to serialize.
     */
    @Override
    public void serialize(String fileName, Game game) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(game.getPlayerBoard());  // Serialize player's board
            oos.writeObject(game.getEnemyBoard());   // Serialize enemy's board
            System.out.println("Boards successfully serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the player's and enemy's boards from a file and updates the game instance.
     *
     * @param fileName The name of the file from which the boards will be restored.
     * @param game     The game instance where the boards will be updated.
     */
    @Override
    public void deserialize(String fileName, Game game) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            game.setPlayerBoard((ArrayList<ArrayList<Integer>>) ois.readObject());
            game.setEnemyBoard((ArrayList<ArrayList<Integer>>) ois.readObject());
            System.out.println("Boards successfully deserialized.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

