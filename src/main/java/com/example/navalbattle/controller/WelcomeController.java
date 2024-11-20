package com.example.navalbattle.controller;

import com.example.navalbattle.model.Game;
import com.example.navalbattle.model.SerializableFileHandler;
import com.example.navalbattle.view.GameStage;
import com.example.navalbattle.view.LoginStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class WelcomeController {
    private Game game;
    private static String userName;  // Variable para almacenar el nombre de usuario extraído del archivo
    private static WelcomeController instance;


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
        LoginStage.getInstance();
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
    }

    @FXML
    public void handleClickExit(ActionEvent event) {
        WelcomeStage.deleteInstance();
    }

    @FXML
    public void initialize() {
        //loadUserNameFromFile();
        game = new Game(10);
        // Deserializamos los tableros al inicio
        // Si no se encontró ningún archivo guardado, inicializamos los tableros
            game.initializeBoardList();
        game.printBoard();
    }
}
