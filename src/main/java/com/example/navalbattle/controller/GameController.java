package com.example.navalbattle.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GameController {


    @FXML
    private AnchorPane enemyAnchorPane;

    @FXML
    private AnchorPane playerAnchorPane;

    @FXML
    private Button buttonPlayGame;

    @FXML
    void buttonPlayGame(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        initializeBoard(playerAnchorPane);
        initializeBoard(enemyAnchorPane);
    }

    private void initializeBoard(AnchorPane anchorPane) {
        GridPane board = new GridPane();
        board.setPrefSize(440, 440);
        board.setLayoutX(0);
        board.setLayoutY(0);

        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                Button cell = new Button();
                cell.setPrefSize(40, 40);
                cell.setStyle("-fx-border-color: grey; -fx-background-color: #3e8ee8");
                board.add(cell, col, row);
            }
        }

        anchorPane.getChildren().add(board);
    }


}
