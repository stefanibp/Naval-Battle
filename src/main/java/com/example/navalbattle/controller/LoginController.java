package com.example.navalbattle.controller;

import com.example.navalbattle.view.FleetStage;
import com.example.navalbattle.view.GameStage;
import com.example.navalbattle.view.LoginStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController  {



    @FXML
    private TextField userTxt;

    @FXML
    void buttonPlayGame(ActionEvent event) {
        LoginStage.deleteInstance();
        FleetStage.getInstance();
    }

    @FXML
    void handleClickExit(ActionEvent event) {
        LoginStage.deleteInstance();
       // WelcomeStage.getInstance();
    }
}
