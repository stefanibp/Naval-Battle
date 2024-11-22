package com.example.navalbattle.controller;

import com.example.navalbattle.model.IAFleet;
import com.example.navalbattle.model.Game;
import com.example.navalbattle.model.PlainTextFileHandler;
import com.example.navalbattle.model.SerializableFileHandler;
import com.example.navalbattle.view.FleetStage;
import com.example.navalbattle.view.LoginStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Controller for the Login Stage in the naval battle game.
 * This controller manages user login, saves the username, and handles
 * transitioning to the fleet setup stage.
 */
public class LoginController {

    @FXML
    private TextField userTxt; // TextField for the user input (username)

    private Game game; // Game object
    private IAFleet enemyFleet; // Enemy fleet for the game

    /**
     * Handles the Play Game button click event.
     * Validates the username input, saves it, serializes the game state,
     * and transitions to the FleetStage.
     *
     * @param event The action event triggered by the Play button.
     */
    @FXML
    void buttonPlayGame(ActionEvent event) {
        // Check if the user has entered a valid username
        if (userTxt != null && !userTxt.getText().isEmpty()) {
            String userInput = userTxt.getText();
            System.out.println("The user entered: " + userInput);

            // Save the username to a file (optional)
            PlainTextFileHandler fileHandler = new PlainTextFileHandler();
            fileHandler.writeToFile("usuario.txt", userInput);

            // Set the username in the Game object (accessible through WelcomeController)
            game = WelcomeController.getInstance().getGame();
            saveGameBoards(game);

            // Delete the current instance of LoginStage and open FleetStage
            LoginStage.deleteInstance();
            FleetStage.getInstance();

            // Create the enemy fleet and place it on the enemy board
            enemyFleet = new IAFleet();
            enemyFleet.placeEnemyFleet(game.getEnemyBoard());
        } else {
            System.out.println("No username entered.");
        }
    }

    /**
     * Saves the current game state to a file by serializing the game object.
     *
     * @param game The Game object to be serialized and saved.
     */
    private void saveGameBoards(Game game) {
        SerializableFileHandler fileHandler = new SerializableFileHandler();
        String fileName = "game_boards.dat"; // Name of the saved file
        fileHandler.serialize(fileName, game);  // Serializes the entire game object
    }

    /**
     * Handles the Exit button click event.
     * Deletes the current instance of LoginStage and optionally
     * opens the WelcomeStage.
     *
     * @param event The action event triggered by the Exit button.
     */
    @FXML
    void handleClickExit(ActionEvent event) {
        LoginStage.deleteInstance();
        WelcomeStage.getInstance(); // Optionally reopens the WelcomeStage
    }
}
