package com.example.navalbattle.controller;

import com.example.navalbattle.model.Board;
import com.example.navalbattle.view.FleetStage;
import com.example.navalbattle.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class FleetController {

    @FXML
    private AnchorPane playerAnchorPane;

    @FXML
    void buttonStartGame(ActionEvent event) {
        FleetStage.deleteInstance();
        GameStage.getInstance();
    }

    @FXML
    void handleClickExit(ActionEvent event) {
        FleetStage.deleteInstance();
    }

    @FXML
    public void initialize() {
        Board playerBoard = new Board();
        playerAnchorPane.getChildren().add(playerBoard.createBoard());
    }
}
