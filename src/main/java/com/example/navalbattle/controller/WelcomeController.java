package com.example.navalbattle.controller;

import com.example.navalbattle.model.Game;
import com.example.navalbattle.model.SerializableFileHandler;
import com.example.navalbattle.view.GameStage;
import com.example.navalbattle.view.LoginStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Controller for the Welcome Stage in the naval battle game.
 * This controller handles user interactions for starting a new game, continuing an existing one,
 * and exiting the game.
 */
public class WelcomeController {
    private Game game; // Game instance
    private static String userName;  // Variable to store the username extracted from the file
    private static WelcomeController instance; // Singleton instance

    /**
     * Constructor for the WelcomeController.
     * Saves the instance when creating the WelcomeController.
     */
    public WelcomeController() {
        // Save this instance when WelcomeController is created
        instance = this;
    }

    /**
     * Gets the singleton instance of the WelcomeController.
     *
     * @return The instance of WelcomeController.
     */
    public static WelcomeController getInstance() {
        return instance;
    }

    /**
     * Gets the current Game object.
     *
     * @return The game object.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Handles the Play button click event.
     * Deletes the current instance of WelcomeStage and opens the LoginStage.
     *
     * @param event The action event triggered by the Play button.
     * @throws IOException If an error occurs while loading the LoginStage.
     */
    @FXML
    void handleClickPlay(ActionEvent event) throws IOException {
        WelcomeStage.deleteInstance();
        LoginStage.getInstance(); // Opens the login stage
    }

    /**
     * Handles the Continue button click event.
     * Deletes the current instance of WelcomeStage and loads the previously saved game state.
     *
     * @param event The action event triggered by the Continue button.
     */
    @FXML
    void handleClickContinue(ActionEvent event) {
        WelcomeStage.deleteInstance(); // Closes the WelcomeStage

        // Loads the game boards from the saved file
        loadGameBoards(game);
        // Initializes the GameStage
        GameStage.getInstance();
    }

    /**
     * Loads the saved game boards using the SerializableFileHandler.
     *
     * @param game The Game object to load the boards into.
     */
    private void loadGameBoards(Game game) {
        SerializableFileHandler fileHandler = new SerializableFileHandler();
        String fileName = "game_boards.dat"; // Name of the saved file
        fileHandler.deserialize(fileName, game);  // Deserializes the boards and loads them into the game object
        game.printBoard();  // Prints the loaded boards for verification
    }

    /**
     * Handles the Exit button click event.
     * Closes the WelcomeStage.
     *
     * @param event The action event triggered by the Exit button.
     */
    @FXML
    public void handleClickExit(ActionEvent event) {
        WelcomeStage.deleteInstance(); // Closes the WelcomeStage
    }

    /**
     * Initializes the WelcomeController.
     * This method is automatically called when the controller is loaded.
     */
    @FXML
    public void initialize() {
        // Initialize the game
        game = new Game(10);

        // Deserialize the saved game boards at the start
        // If no saved file is found, initialize the game boards
        game.initializeBoardList();
        game.printBoard();  // Prints the initial game boards
    }
}
