package com.example.navalbattle.controller;

import com.example.navalbattle.model.*;
import com.example.navalbattle.view.FleetStage;
import com.example.navalbattle.view.LoginStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * The controller class for managing the login functionality in the naval battle game.
 * It handles the user's input for the username and manages transitions to the game stages.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class LoginController {

    @FXML
    private TextField userTxt;

    private Game game;

    /**
     * Handles the action of the Play Game button.
     * It validates the user's input, saves the username to a file, and prepares the game boards.
     * Then, it transitions from the login stage to the fleet stage of the game.
     *
     * @param event the action event triggered by clicking the Play Game button
     */
    @FXML
    void buttonPlayGame(ActionEvent event) {
        if (userTxt != null && !userTxt.getText().isEmpty()) {
            String userInput = userTxt.getText();
            System.out.println("The user entered: " + userInput);

            // Save the username to a file (optional)
            PlainTextFileHandler fileHandler = new PlainTextFileHandler();
            fileHandler.writeToFile("usuario.txt", userInput);

            // Set the username in the GameController
            game = WelcomeController.getInstance().getGame();
            saveGameBoards(game);

            // Proceed with deleting the LoginStage instance and opening the FleetStage
            LoginStage.deleteInstance();
            FleetStage.getInstance();

            // Create the enemy fleet and place it on the board

        } else {
            System.out.println("No username was entered.");
        }
    }

    /**
     * Saves the game boards to a file using serialization.
     *
     * @param game the game object containing the game boards to be serialized
     */
    private void saveGameBoards(Game game) {
        SerializableFileHandler fileHandler = new SerializableFileHandler();
        String fileName = "game_boards.dat";
        fileHandler.serialize(fileName, game);  // Serializes the entire game object
    }

    /**
     * Handles the action of the Exit button.
     * It deletes the current instance of the LoginStage and optionally opens the WelcomeStage.
     *
     * @param event the action event triggered by clicking the Exit button
     */
    @FXML
    void handleClickExit(ActionEvent event) {
        LoginStage.deleteInstance();
        WelcomeStage.getInstance(); // Optional if you choose to reactivate this line
    }
}
