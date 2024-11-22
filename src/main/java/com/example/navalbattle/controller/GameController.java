package com.example.navalbattle.controller;

import com.example.navalbattle.model.*;
import com.example.navalbattle.view.*;
import com.example.navalbattle.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * The controller class for managing the game functionality in the naval battle game.
 * It handles the interaction with the game boards, user interface elements, and transitions between stages.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class GameController {

    @FXML
    private AnchorPane enemyAnchorPane;

    @FXML
    private AnchorPane playerAnchorPane;

    @FXML
    private Pane idSunken;

    @FXML
    private Pane idTouched;

    @FXML
    private Pane idwater;

    private Game game;

    private Board boardModel;

    GridPane board;

    /**
     * Initializes the board in the specified AnchorPane.
     *
     * @param anchorPane the AnchorPane where the board will be initialized
     * @param boardName the name of the board to be created
     */
    private void initializeBoard(AnchorPane anchorPane, String boardName) {
        if (boardModel != null) {
            GridPane board = boardModel.createBoard(boardName); // Ajuste en `createBoard`
            anchorPane.getChildren().add(board);
        }
    }

    @FXML
    private Label idUser;
    private static String staticUserName; // Static variable to temporarily store the username
    private String userName; // Local variable in the controller instance

    /**
     * Handles the action of the View Enemy button.
     * It opens the EnemyStage.
     *
     * @param event the action event triggered by clicking the View Enemy button
     */
    @FXML
    void buttonViewEnemy(ActionEvent event) {
        EnemyStage.getInstance();
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
     * It saves the game state and transitions to the WelcomeStage.
     *
     * @param event the action event triggered by clicking the Exit button
     */
    @FXML
    void handleClickExit(ActionEvent event) {
        // Saves the game when the player exits
        saveGameBoards(game);
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
        EnemyStage.getInstance();
        EnemyStage.deleteInstance();
    }

    /**
     * Initializes the game by loading the board models and user information.
     */
    @FXML
    public void initialize() {
        game = WelcomeController.getInstance().getGame();

        game.printBoard();  // You can call the method to check if it loaded correctly
        boardModel = new Board(); // Now that 'game' is initialized, pass 'game' to Board
        initializeBoard(playerAnchorPane, "Player");
        initializeBoard(enemyAnchorPane, "Enemy");
        loadFigures();
        loadUserNameFromFile();
    }

    private static GameController controller;

    /**
     * Gets the instance of the GameController.
     *
     * @return the controller instance
     */
    public static GameController getController() {
        return controller; // Returns the controller reference
    }

    /**
     * Loads the username from a file and assigns it to the userName variable.
     */
    private void loadUserNameFromFile() {
        PlainTextFileHandler fileHandler = new PlainTextFileHandler();
        String[] content = fileHandler.readFromFile("usuario.txt");

        // If there is content in the file, assign the first value to the userName variable
        if (content.length > 0) {
            userName = content[0];  // Assumes the username is the first data saved
            System.out.println("User loaded: " + userName);  // Print the loaded username
        } else {
            System.out.println("No username found in the file.");
        }
        displayUserName(userName);
    }

    /**
     * Displays the user's name on the UI with special styling.
     *
     * @param user the user's name to be displayed
     */
    public void displayUserName(String user) {
        if (user != null && !user.isEmpty()) {
            // Convert to uppercase
            String userUpperCase = user.toUpperCase();

            // Set the text with a gradient and dark marine style
            idUser.setText(userUpperCase + " ¡Fuego a discreción!");
            idUser.setStyle(
                    "-fx-font-size: 55px; " +
                            "-fx-font-family: 'Seaweed Script'; " + // Marine-style font, can be changed if preferred
                            "-fx-text-fill: linear-gradient(from 0% 0% to 100% 100%, #001a33, #005266); " + // Dark blue gradient
                            "-fx-font-weight: bold;"
            );

            // Apply dark marine glow effect
            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetX(0);
            dropShadow.setOffsetY(0);
            dropShadow.setRadius(15); // Glow size
            dropShadow.setColor(Color.web("#003d66")); // Bright dark blue

            // Apply the effect to the text
            idUser.setEffect(dropShadow);
        }
    }

    /**
     * Loads the game figures (sink, miss, hit) and applies them to the corresponding UI elements.
     */
    @FXML
    public void loadFigures() {
        // Create instances of the figures
        Sink sink = new Sink();
        Miss miss = new Miss();
        Hit hit = new Hit();

        // Clear any previous content
        idSunken.getChildren().clear();
        idTouched.getChildren().clear();
        idwater.getChildren().clear();

        // Render and add the figures to the panes
        idSunken.getChildren().add(hit.renderEffect());
        idTouched.getChildren().add(sink.renderEffect());
        idwater.getChildren().add(miss.renderEffect());
    }
}

