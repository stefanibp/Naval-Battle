package com.example.navalbattle.model;

/**
 * Interface for handling the serialization and deserialization of game data.
 * It provides methods for saving and loading game states to and from a file.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public interface ISerializableFileHandler {

    /**
     * Serializes the current game data and saves it to a file.
     * The game data includes the player and enemy boards.
     *
     * @param fileName The name of the file to save the game data to.
     * @param game The game object containing the data to serialize.
     */
    void serialize(String fileName, Game game);

    /**
     * Deserializes game data from a file and loads it into the current game.
     * This method restores the player and enemy boards from the file.
     *
     * @param fileName The name of the file to load the game data from.
     * @param game The game object to load the data into.
     */
    void deserialize(String fileName, Game game);
}

