package com.example.navalbattle.controller;


import com.example.navalbattle.model.Game;
import com.example.navalbattle.model.IGame;
import com.example.navalbattle.model.SerializableFileHandler;
import com.example.navalbattle.view.*;
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

    @FXML
    public void initialize() {
        controller = this; // Almacena la instancia del controlador
        initializeBoard(playerAnchorPane);
        initializeBoard(enemyAnchorPane);
        displayUserName(userName);

    }
    private static GameController controller;
    public static GameController getController() {
        return controller; // Devuelve la referencia del controlador
    }

    private void initializeBoard(AnchorPane anchorPane) {
        GridPane board = new GridPane();
        board.setPrefSize(440, 440);
        board.setLayoutX(0);
        board.setLayoutY(0);

        // Letras para la primera fila
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                if (row == 0 && col == 0) {
                    Label label = new Label();
                    label.setPrefSize(40, 40);
                    label.setStyle("-fx-border-color: grey; -fx-background-color: #c8c8c8;");
                    board.add(label, col, row);
                } else if (row == 0 && col > 0) {
                    // Primera fila (etiquetas de letras)
                    Label label = new Label(letters[col - 1]);
                    label.setPrefSize(40, 40);
                    label.setStyle("-fx-border-color: grey; -fx-alignment: center; -fx-background-color: #c8c8c8; -fx-font-weight: bold;");
                    board.add(label, col, row);
                } else if (col == 0 && row > 0) {
                    // Primera columna (etiquetas de números)
                    Label label = new Label(String.valueOf(row));
                    label.setPrefSize(40, 40);
                    label.setStyle("-fx-border-color: grey; -fx-alignment: center; -fx-background-color: #c8c8c8; -fx-font-weight: bold;");
                    board.add(label, col, row);
                } else if (row > 0 && col > 0) {
                    // Resto de celdas (botones para el tablero)
                    Button cell = new Button();
                    cell.setPrefSize(40, 40);
                    cell.setStyle("-fx-border-color: grey; -fx-background-color: #3e8ee8");
                    board.add(cell, col, row);
                }
            }
        }

        anchorPane.getChildren().add(board);
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
