package com.example.navalbattle.controller;

import com.example.navalbattle.model.Board;
import com.example.navalbattle.model.Game;
import com.example.navalbattle.view.EnemyStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class EnemyController {

    @FXML
    private AnchorPane enemyAnchorPane;
    @FXML
    private Board boardModel;
    @FXML
    void handleClickExit(ActionEvent event) {
        EnemyStage.deleteInstance();
    }
    private Game game;

    @FXML
    public void initialize() {
//<<<<<<< sbp
 //       Board board = new Board();
 //       enemyAnchorPane.getChildren().add(board.createBoard(Game.getInstance().getEnemyBoard()));
//=======
        game = WelcomeController.getInstance().getGame();
        boardModel = Board.getInstance();
        initializeBoard(enemyAnchorPane);
    }

    private void initializeBoard(AnchorPane anchorPane) {
        if (boardModel != null) {
            GridPane board = boardModel.createBoard(Game.getInstance().getEnemyBoard()); // Ajuste en `createBoard`
            anchorPane.getChildren().add(board);
            Board.getInstance().mapShipsToAnchorPane(anchorPane, false);
        }

    }
}
