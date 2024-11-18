package com.example.navalbattle.controller;

import com.example.navalbattle.model.Game;
import com.example.navalbattle.model.PlainTextFileHandler;  // Asegúrate de importar esta clase
import com.example.navalbattle.model.SerializableFileHandler;
import com.example.navalbattle.view.GameStage;
import com.example.navalbattle.view.LoginStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class WelcomeController {
    private Game game;
    private String userName;  // Variable para almacenar el nombre de usuario extraído del archivo

    @FXML
    void handleClickPlay(ActionEvent event) throws IOException {
        WelcomeStage.deleteInstance();
        LoginStage.getInstance();
    }

    @FXML
    void handleClickContinue(ActionEvent event) {
        WelcomeStage.deleteInstance();

        // Carga el nombre de usuario desde el archivo
        loadUserNameFromFile();

        // Inicia GameStage
        GameStage.getInstance();

        // Obtén la instancia del controlador y actualiza el nombre de usuario
        GameController controller = GameController.getController();
        if (controller != null) {
            controller.displayUserName(userName);  // Pasa el nombre de usuario
        } else {
            System.out.println("GameController no está inicializado.");
        }

        loadGameBoards();
    }

    // Método para cargar el nombre de usuario desde el archivo
    private void loadUserNameFromFile() {
        PlainTextFileHandler fileHandler = new PlainTextFileHandler();
        String[] content = fileHandler.readFromFile("usuario.txt");

        // Si hay contenido en el archivo, asignar el primer valor a la variable userName
        if (content.length > 0) {
            userName = content[0];  // Asumimos que el nombre de usuario es el primer dato guardado
            System.out.println("Usuario cargado: " + userName);  // Imprimir el nombre de usuario cargado
        } else {
            System.out.println("No se encontró un nombre de usuario en el archivo.");
        }

    }

    private void loadGameBoards() {
        SerializableFileHandler fileHandler = new SerializableFileHandler();
        String fileName = "game_boards.dat";
        fileHandler.deserialize(fileName, game);  // Deserializa los tableros y los carga en el objeto 'game'
        game.printBoard();  // Imprime los tableros cargados para verificar
    }

    @FXML
    public void handleClickExit(ActionEvent event) {
        WelcomeStage.deleteInstance();
    }

    @FXML
    public void initialize() {
        game = new Game(10);
        // Deserializamos los tableros al inicio

        // Si no se encontró ningún archivo guardado, inicializamos los tableros
        if (game.getPlayerBoard().isEmpty() || game.getEnemyBoard().isEmpty()) {
            game.initializeBoardList();
        }
        game.printBoard();
    }
}
