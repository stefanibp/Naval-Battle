package com.example.navalbattle.controller;

import com.example.navalbattle.model.PlainTextFileHandler;
import com.example.navalbattle.view.FleetStage;
import com.example.navalbattle.view.LoginStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField userTxt;
    String userInput;
    @FXML
    void buttonPlayGame(ActionEvent event) {
        if (userTxt != null && !userTxt.getText().isEmpty()) {
            String userInput = userTxt.getText();
            System.out.println("El usuario ingresó: " + userInput);

            // Guardar el nombre de usuario en un archivo (opcional)
            PlainTextFileHandler fileHandler = new PlainTextFileHandler();
            fileHandler.writeToFile("usuario.txt", userInput);

            // Configurar el nombre de usuario en GameController
            GameController.setStaticUserName(userInput);

            // Proceder con la eliminación de la instancia de LoginStage y abrir GameStage
            LoginStage.deleteInstance();
            FleetStage.getInstance();
        } else {
            System.out.println("No se ingresó ningún nombre de usuario.");
        }


    }

    @FXML
    void handleClickExit(ActionEvent event) {
        LoginStage.deleteInstance();
        // WelcomeStage.getInstance(); // Opcional si decides reactivar esta línea
    }
}
