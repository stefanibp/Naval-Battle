package com.example.navalbattle.controller;

import com.example.navalbattle.model.Board;
import com.example.navalbattle.view.EnemyStage;
import com.example.navalbattle.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GameController {


    @FXML
    private AnchorPane enemyAnchorPane;

    @FXML
    private AnchorPane playerAnchorPane;

    private Board boardModel;

    @FXML
    public void initialize() {
        boardModel = new Board();
        initializeBoard(playerAnchorPane);
        initializeBoard(enemyAnchorPane);
    }

    private void initializeBoard(AnchorPane anchorPane) {
        GridPane board = boardModel.createBoard();
        anchorPane.getChildren().add(board);
    }

    @FXML
    void buttonViewEnemy(ActionEvent event) {
        EnemyStage.getInstance();
    }

    @FXML
    void handleClickExit(ActionEvent event) {
        GameStage.deleteInstance();
    }

    @FXML
    void buttonPlayGame() {
    }
}

