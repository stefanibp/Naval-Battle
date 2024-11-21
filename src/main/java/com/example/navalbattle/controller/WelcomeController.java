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

public class WelcomeController {
    private Game game;
    private IAFleet enemyFleet;    private static String userName;  // Variable para almacenar el nombre de usuario extraído del archivo
    private static WelcomeController instance;

    private ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy;
    private ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer;
    public WelcomeController() {
        // Guardar esta instancia al crear WelcomeController
        instance = this;
    }

    public static WelcomeController getInstance() {
        return instance;
    }

    public Game getGame() {
        return game;
    }

    @FXML
    void handleClickPlay(ActionEvent event) throws IOException {

        WelcomeStage.deleteInstance();
        enemyFleet = new IAFleet();
        enemyFleet.placeEnemyFleet(game.getEnemyBoard());
        fleetCoordinatesEnemy = enemyFleet.getEnemyFleetCoordinates();
        fleetCoordinatesPlayer = enemyFleet.getEnemyFleetCoordinates();///////opcional
        game.printBoard();



        LoginStage.getInstance();
    }

    public ArrayList<ArrayList<Integer>> getFleetCoordinatesEnemy() {
        return fleetCoordinatesEnemy;
    }

    public ArrayList<ArrayList<Integer>> getFleetCoordinatesPlayer() {
        return fleetCoordinatesPlayer;
    }

    @FXML
    void handleClickContinue(ActionEvent event) {
        WelcomeStage.deleteInstance();

        // Carga el nombre de usuario desde el archivo

        loadGameBoards(game);
        // Inicia GameStage
        GameStage.getInstance();

    }

    private void loadGameBoards(Game game) {
        SerializableFileHandler fileHandler = new SerializableFileHandler();
        String fileName = "game_boards.dat";
        fileHandler.deserialize(fileName, game);  // Deserializa los tableros y los carga en el objeto 'game'

        game.printBoard();  // Imprime los tableros cargados para verificar

        SerializableFileHandlerPosition fileHandlerPosition = new SerializableFileHandlerPosition();
        String fileNamePosition = "game_boardsPositions.dat";

        fileHandlerPosition.deserialize(fileNamePosition, fleetCoordinatesEnemy, fleetCoordinatesPlayer);
        printBoard();
    }

    @FXML
    public void handleClickExit(ActionEvent event) {
        WelcomeStage.deleteInstance();
    }
    public void printBoard() {
        System.out.println("Tablero del Jugador:");
        for (ArrayList<Integer> row : fleetCoordinatesPlayer) {
            System.out.println(row);
        }

        System.out.println("Tablero del Enemigo:");
        for (ArrayList<Integer> row : fleetCoordinatesEnemy) {
            System.out.println(row);
        }
    }

    @FXML
    public void initialize() {
//<<<<<<< sbp
        // Inicializa la instancia de Game
        //game = Game.getInstance(10);  // Se asume un tablero de tamaño 10x10

//=======
        fleetCoordinatesEnemy = new ArrayList<>();
        fleetCoordinatesPlayer = new ArrayList<>();
        //loadUserNameFromFile();
        game = new Game(10);
        // Deserializamos los tableros al inicio
//>>>>>>> team
        // Si no se encontró ningún archivo guardado, inicializamos los tableros
            game.initializeBoardList();
        game.printBoard();
    }
}
