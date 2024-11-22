package com.example.navalbattle.controller;

import com.example.navalbattle.model.Game;
import com.example.navalbattle.model.IAFleet;
import com.example.navalbattle.model.SerializableFileHandler;
import com.example.navalbattle.model.SerializableFileHandlerPosition;
import com.example.navalbattle.view.GameStage;
import com.example.navalbattle.view.LoginStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The controller class for the Welcome screen in the naval battle game.
 * It handles user interactions in the Welcome stage, such as starting a new game,
 * continuing a saved game, and exiting the application.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class WelcomeController {

    private Game game;
    private IAFleet enemyFleet;
    private static String userName;  // Variable for storing the username extracted from the file
    private static WelcomeController instance;

    private ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy;
    private ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer;

    /**
     * Constructor for WelcomeController. Initializes the instance variable.
     */
    public WelcomeController() {
        // Save this instance when WelcomeController is created
        instance = this;
    }

    /**
     * Gets the instance of WelcomeController.
     *
     * @return the singleton instance of WelcomeController
     */
    public static WelcomeController getInstance() {
        return instance;
    }

    /**
     * Gets the game object associated with this controller.
     *
     * @return the Game object
     */
    public Game getGame() {
        return game;
    }

    /**
     * Handles the Play button action. It initializes the enemy fleet,
     * places it on the enemy board, and transitions to the LoginStage.
     *
     * @param event the action event triggered by clicking the Play button
     * @throws IOException if an error occurs while changing the stage
     */
    @FXML
    void handleClickPlay(ActionEvent event) throws IOException {
        WelcomeStage.deleteInstance();
        enemyFleet = new IAFleet();
        enemyFleet.placeEnemyFleet(game.getEnemyBoard());
        fleetCoordinatesEnemy = enemyFleet.getEnemyFleetCoordinates();
        // fleetCoordinatesPlayer = enemyFleet.getEnemyFleetCoordinates(); // Optional
        game.printBoard();

        LoginStage.getInstance();
    }

    /**
     * Gets the coordinates of the enemy fleet.
     *
     * @return a list of coordinates representing the enemy fleet
     */
    public ArrayList<ArrayList<Integer>> getFleetCoordinatesEnemy() {
        return fleetCoordinatesEnemy;
    }

    /**
     * Gets the coordinates of the player's fleet.
     *
     * @return a list of coordinates representing the player's fleet
     */
    public ArrayList<ArrayList<Integer>> getFleetCoordinatesPlayer() {
        return fleetCoordinatesPlayer;
    }

    /**
     * Handles the Continue button action. It loads the saved game state
     * and transitions to the GameStage.
     *
     * @param event the action event triggered by clicking the Continue button
     */
    @FXML
    void handleClickContinue(ActionEvent event) {
        WelcomeStage.deleteInstance();

        // Loads the saved game boards from file
        loadGameBoards(game);

        // Starts the GameStage
        GameStage.getInstance();
    }

    /**
     * Loads the saved game boards and positions from files and updates the game state.
     *
     * @param game the Game object to load the boards into
     */
    private void loadGameBoards(Game game) {
        SerializableFileHandler fileHandler = new SerializableFileHandler();
        String fileName = "game_boards.dat";
        fileHandler.deserialize(fileName, game);  // Deserializes the boards and loads them into the game object
        game.printBoard();  // Prints the loaded boards for verification

        SerializableFileHandlerPosition fileHandlerPosition = new SerializableFileHandlerPosition();
        String fileNamePosition = "game_boardsPositions.dat";
        fileHandlerPosition.deserialize(fileNamePosition, fleetCoordinatesEnemy, fleetCoordinatesPlayer);
        printBoard();
    }

    /**
     * Handles the Exit button action. It exits the WelcomeStage.
     *
     * @param event the action event triggered by clicking the Exit button
     */
    @FXML
    public void handleClickExit(ActionEvent event) {
        WelcomeStage.deleteInstance();
    }

    /**
     * Prints the fleet coordinates for the player and enemy for debugging purposes.
     */
    public void printBoard() {
        System.out.println("Player's Board:");
        for (ArrayList<Integer> row : fleetCoordinatesPlayer) {
            System.out.println(row);
        }

        System.out.println("Enemy's Board:");
        for (ArrayList<Integer> row : fleetCoordinatesEnemy) {
            System.out.println(row);
        }
    }

    /**
     * Sets the coordinates of the player's fleet.
     *
     * @param fleetCoordinatesPlayer the coordinates of the player's fleet
     */
    public void setfleetCoordinatesPlayer(ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer) {
        this.fleetCoordinatesPlayer = fleetCoordinatesPlayer;
    }

    /**
     * Initializes the WelcomeController by setting up the game and loading the initial state.
     */
    @FXML
    public void initialize() {
        fleetCoordinatesEnemy = new ArrayList<>();
        fleetCoordinatesPlayer = new ArrayList<>();
        game = new Game(10);

        // Deserialize the boards on startup
        // If no saved files are found, initialize the boards
        game.initializeBoardList();
        game.printBoard();
    }
}
