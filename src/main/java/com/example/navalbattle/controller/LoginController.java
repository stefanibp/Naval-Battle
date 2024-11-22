package com.example.navalbattle.controller;

import com.example.navalbattle.model.*;
import com.example.navalbattle.view.FleetStage;
import com.example.navalbattle.view.LoginStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField userTxt;

    private Game game;


    @FXML
    void buttonPlayGame(ActionEvent event) {
        if (userTxt != null && !userTxt.getText().isEmpty()) {
            String userInput = userTxt.getText();
            System.out.println("El usuario ingresó: " + userInput);

            // Guardar el nombre de usuario en un archivo (opcional)
            PlainTextFileHandler fileHandler = new PlainTextFileHandler();
            fileHandler.writeToFile("usuario.txt", userInput);

            // Configurar el nombre de usuario en GameController
            game = WelcomeController.getInstance().getGame();
            saveGameBoards(game);

            // Proceder con la eliminación de la instancia de LoginStage y abrir GameStage
            LoginStage.deleteInstance();
            FleetStage.getInstance();

            // Crear la flota del enemigo y colocarla en el tablero

        } else {
            System.out.println("No se ingresó ningún nombre de usuario.");
        }

    }

    private void saveGameBoards(Game game) {
        SerializableFileHandler fileHandler = new SerializableFileHandler();
        String fileName = "game_boards.dat";
        fileHandler.serialize(fileName, game);  // Serializa el objeto completo
    }

    @FXML
    void handleClickExit(ActionEvent event) {
        LoginStage.deleteInstance();
        WelcomeStage.getInstance(); // Opcional si decides reactivar esta línea
    }
}
