package com.example.navalbattle.controller;

import com.example.navalbattle.model.Board;
import com.example.navalbattle.model.Game;
import com.example.navalbattle.view.EnemyStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class EnemyController {

    @FXML
    private AnchorPane enemyAnchorPane;

    @FXML
    void handleClickExit(ActionEvent event) {
        EnemyStage.deleteInstance();
    }

    @FXML
    public void initialize() {
        Board board = new Board();
        enemyAnchorPane.getChildren().add(board.createBoard(Game.getInstance().getEnemyBoard()));
    }
}
