package com.example.navalbattle.controller;

import com.example.navalbattle.model.Board;
import com.example.navalbattle.model.Game;
import com.example.navalbattle.model.IAFleet;
import com.example.navalbattle.model.SerializableFileHandlerPosition;
import com.example.navalbattle.view.FleetStage;
import com.example.navalbattle.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class FleetController {

    @FXML
    private Game game;
    private IAFleet enemyFleet;
    @FXML
    private AnchorPane playerAnchorPane;
    @FXML
    private Board boardModel;


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
        game = WelcomeController.getInstance().getGame();
        boardModel = new Board(game);
        initializeBoard(playerAnchorPane, "PlayerF");
    }
    private void initializeBoard(AnchorPane anchorPane, String boardName) {
        if (boardModel != null) {
            GridPane board = boardModel.createBoard(boardName); // Ajuste en `createBoard`
            anchorPane.getChildren().add(board);
        }
    }
}
