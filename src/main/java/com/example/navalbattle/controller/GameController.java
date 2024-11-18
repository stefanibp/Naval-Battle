package com.example.navalbattle.controller;


import com.example.navalbattle.model.Game;

import com.example.navalbattle.model.IGame;
import com.example.navalbattle.model.SerializableFileHandler;
import com.example.navalbattle.view.*;

import com.example.navalbattle.model.Board;
import com.example.navalbattle.view.EnemyStage;
import com.example.navalbattle.view.GameStage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


import java.io.File;
import java.util.ArrayList;



public class GameController {


    @FXML
    private AnchorPane enemyAnchorPane;

    @FXML
    private AnchorPane playerAnchorPane;


    private Game game;

    private Board boardModel;




    private void initializeBoard(AnchorPane anchorPane) {
        GridPane board = boardModel.createBoard();
        anchorPane.getChildren().add(board);
    }

    @FXML
    private Label idUser;
    private static String staticUserName; // Variable estática para almacenar temporalmente el nombre de usuario
    private String userName; // Variable local en la instancia del controlador

    public static void setStaticUserName(String userName) {
        staticUserName = userName; // Método para configurar la variable estática
    }
    public GameController() {
        game = new Game(10); // Suponiendo que el tamaño del tablero es 10
        game.initializeBoardList();
        this.userName = staticUserName;
    }

    @FXML
    void buttonViewEnemy(ActionEvent event) {
       game.modifyRandomCell();
       game.printBoard();
        EnemyStage.getInstance();
    }
    private void saveGameBoards() {
        SerializableFileHandler fileHandler = new SerializableFileHandler();
        String fileName = "game_boards.dat";
        fileHandler.serialize(fileName, game);  // Serializa el objeto completo
    }

    @FXML
    void handleClickExit(ActionEvent event) {
        // Guarda el juego cuando el jugador salga
        saveGameBoards();
        GameStage.deleteInstance();
    }

    @FXML
    void buttonPlayGame() {


    }

    private Game game;

    @FXML
    public void initialize() {
        boardModel = new Board();
        controller = this; // Almacena la instancia del controlador
        initializeBoard(playerAnchorPane);
        initializeBoard(enemyAnchorPane);
        displayUserName(userName);
    }
  
    private static GameController controller;
  
    public static GameController getController() {
        return controller; // Devuelve la referencia del controlador
    }

    public void displayUserName(String user) {
        if (user != null && !user.isEmpty()) {
            idUser.setText("Usuario: " + user);
            idUser.setStyle("-fx-font-size: 60px; -fx-font-family: 'Arial'; -fx-text-fill: #6F4F28; -fx-font-weight: bold;");
        } else {
            idUser.setText("Usuario no encontrado");
            idUser.setStyle("-fx-font-size: 60px; -fx-font-family: 'Arial'; -fx-text-fill: #6F4F28; -fx-font-weight: bold;");
        }
    }
}