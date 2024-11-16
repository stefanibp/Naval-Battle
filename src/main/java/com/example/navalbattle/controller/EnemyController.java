package com.example.navalbattle.controller;


import com.example.navalbattle.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class EnemyController {

    @FXML
    private AnchorPane enemyAnchorPane;


    @FXML
    void handleClickExit(ActionEvent event) {
        EnemyStage.deleteInstance();
        //   FleetStage.getInstance();
    }

    @FXML
    public void initialize() {

        initializeBoard(enemyAnchorPane);
    }

    private void initializeBoard(AnchorPane anchorPane) {
        GridPane board = new GridPane();
        board.setPrefSize(440, 440);
        board.setLayoutX(0);
        board.setLayoutY(0);

        // Letras para la primera fila
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                if (row == 0 && col == 0) {
                    Label label = new Label();
                    label.setPrefSize(40, 40);
                    label.setStyle("-fx-border-color: grey; -fx-background-color: #c8c8c8;");
                    board.add(label, col, row);
                } else if (row == 0 && col > 0) {
                    // Primera fila (etiquetas de letras)
                    Label label = new Label(letters[col - 1]);
                    label.setPrefSize(40, 40);
                    label.setStyle("-fx-border-color: grey; -fx-alignment: center; -fx-background-color: #c8c8c8; -fx-font-weight: bold;");
                    board.add(label, col, row);
                } else if (col == 0 && row > 0) {
                    // Primera columna (etiquetas de números)
                    Label label = new Label(String.valueOf(row));
                    label.setPrefSize(40, 40);
                    label.setStyle("-fx-border-color: grey; -fx-alignment: center; -fx-background-color: #c8c8c8; -fx-font-weight: bold;");
                    board.add(label, col, row);
                } else if (row > 0 && col > 0) {
                    // Resto de celdas (botones para el tablero)
                    Button cell = new Button();
                    cell.setPrefSize(40, 40);
                    cell.setStyle("-fx-border-color: grey; -fx-background-color: #3e8ee8");
                    board.add(cell, col, row);
                }
            }
        }

        anchorPane.getChildren().add(board);
    }
}
