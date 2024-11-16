package com.example.navalbattle.controller;



import com.example.navalbattle.view.GameStage;
import com.example.navalbattle.view.LoginStage;
import com.example.navalbattle.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;


public class WelcomeController {


    @FXML
    void handleClickPlay(ActionEvent event) throws IOException {
        WelcomeStage.deleteInstance();
        LoginStage.getInstance();
    }
    @FXML
    void handleClickContinue(ActionEvent event) {
        WelcomeStage.deleteInstance();
        GameStage.getInstance();
    }
    @FXML
    public void handleClickExit(ActionEvent event) {
        WelcomeStage.deleteInstance();
    }


    @FXML
    public void initialize() {

    }


}
