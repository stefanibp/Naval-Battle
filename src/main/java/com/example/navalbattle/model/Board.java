package com.example.navalbattle.model;

import com.example.navalbattle.exceptions.InvalidShipPlacementException;
import com.example.navalbattle.exceptions.OutOfBoundsException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Board {

    public GridPane createBoard(ArrayList<ArrayList<Integer>> boards) {
        GridPane board = new GridPane();
        board.setPrefSize(440, 440);
        board.setLayoutX(0);
        board.setLayoutY(0);

        // Letras para la primera fila
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                if (row == 0 && col == 0) {
                    // Esquina superior izquierda
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
                    // Celdas del tablero basadas en el valor de boards
                    int value = boards.get(row - 1).get(col - 1); // Ajustar índices porque boards no tiene cabecera
                    Button cell = new Button(String.valueOf(value));
                    cell.setPrefSize(40, 40);

                    // Estilo según el valor
                    if (value == 0) {
                        cell.setStyle("-fx-border-color: grey; -fx-background-color: #3e8ee8;");
                    } else {
                        cell.setStyle("-fx-border-color: white; -fx-background-color: red;");
                    }

                    board.add(cell, col, row);
                }
            }
        }
        return board;
    }


}
