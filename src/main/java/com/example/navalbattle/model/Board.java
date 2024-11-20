package com.example.navalbattle.model;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;

public class Board {

    private Game game;
    private IAFleet iaFleet;
    GridPane boardPlayer = new GridPane();
    GridPane boardEnemy = new GridPane();

    public Board(Game game) {
        this.game = game;

    }

    public void printEnemyFleetInfo() {
        ArrayList<String> enemyFleetInfo = iaFleet.getEnemyFleetInfo();  // Obtener la información de la flota
        System.out.println("Información de la flota del enemigo:");
        for (String shipInfo : enemyFleetInfo) {
            System.out.println(shipInfo);  // Imprimir cada barco en la flota
        }
    }


    public GridPane createBoard(String boardName) {
        GridPane board = new GridPane();
        board.setPrefSize(440, 440);
        board.setLayoutX(0);
        board.setLayoutY(0);

        // Letras para la primera fila
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                if (row == 0 && col == 0) {
                    // Esquina superior izquierda vacía
                    board.add(new Pane(), col, row);
                } else if (row == 0 && col > 0) {
                    // Letras en la primera fila
                    board.add(new LabelCell(letters[col - 1]), col, row);
                } else if (col == 0 && row > 0) {
                    // Números en la primera columna
                    board.add(new LabelCell(String.valueOf(row)), col, row);
                } else if (row > 0 && col > 0) {
                    // Celdas jugables
                    Button cell = new Button();
                    cell.setPrefSize(40, 40);
                    cell.setStyle("-fx-border-color: null; -fx-background-color: #3e8ee8;");

                    // Solo asignamos el evento de clic si estamos creando el tablero del enemigo
                    if (boardName.equals("Enemy")) {

                        boardEnemy = board;



                    } else {
                        boardPlayer=board;

                    }

                    board.add(cell, col, row);
                }
            }
        }

        updateBoardGraphics(board, boardName);

        return board;
    }


    // Método para purgar una celda y restablecer el botón
    public void purgeCell(GridPane board, int row, int col, String boardName) {
        // Buscamos la celda en el tablero y la eliminamos si es un efecto visual
        Node existingNode = getNodeByRowColumnIndex(row + 1, col + 1, board);

        if (existingNode != null) {
            board.getChildren().remove(existingNode); // Eliminamos el nodo anterior (si existe)
        }

        // Luego agregamos un nuevo botón en la celda
        Button newButton = new Button();
        newButton.setPrefSize(40, 40);
        newButton.setStyle("-fx-border-color: grey; -fx-background-color: #3e8ee8;");

        int currentRow = row;
        int currentCol = col;
        if (boardName.equals("Enemy")) {
            ///
            newButton.setOnAction(event -> {
                printEnemyFleetInfo();
                System.out.println("Celda oprimida: Columna " + (currentCol + 1) + ", Fila " + (currentRow + 1));
                game.modifyArraylist(currentRow, currentCol, 6, boardName);
                updateBoardGraphics(boardEnemy, boardName);
                for (Node node : boardEnemy.getChildren()) {
                    node.setDisable(true); // Deshabilita la interactividad del nodo
                }
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // 2 segundos de pausa
                pause.setOnFinished(event1 -> {
                    // Luego de la pausa, ejecutamos modifyRandomCell
                    game.modifyRandomCell(); // Ejecuta el método de forma retrasada
                    updateBoardGraphics(boardPlayer, "Player");

                });

                pause.play();



            });

        }


        board.add(newButton, col + 1, row + 1); // Aseguramos de colocar el botón en la celda correcta
    }

    // Método para actualizar los gráficos de una celda específica
    public void updateCellEffect(GridPane board, int row, int col, String boardName) {
        // Usamos el tablero correspondiente (Player o Enemy)
        var currentBoard = boardName.equals("Player") ? game.getPlayerBoard() : game.getEnemyBoard();
        int cellValue = currentBoard.get(row).get(col);

        // Purga la celda antes de agregar un nuevo efecto visual
        purgeCell(board, row, col, boardName);

        // Cambiar el efecto visual según el valor de la celda
        if (cellValue == 5) {  // Miss (agua)
            Miss missEffect = new Miss();
            Pane effectPane = missEffect.renderEffect();
            effectPane.setPrefSize(40, 40);
            GridPane.setRowIndex(effectPane, row + 1);
            GridPane.setColumnIndex(effectPane, col + 1);
            board.getChildren().add(effectPane);
        }
        else if (cellValue == 6) {  // Hit (tocado)
            Hit hitEffect = new Hit();
            Pane effectPane = hitEffect.renderEffect();
            effectPane.setPrefSize(40, 40);
            GridPane.setRowIndex(effectPane, row + 1);
            GridPane.setColumnIndex(effectPane, col + 1);
            board.getChildren().add(effectPane);
        }
        else if (cellValue == 7) {  // Sink (hundido)
            Sink sinkEffect = new Sink();
            Pane effectPane = sinkEffect.renderEffect();
            effectPane.setPrefSize(40, 40);
            GridPane.setRowIndex(effectPane, row + 1);
            GridPane.setColumnIndex(effectPane, col + 1);
            board.getChildren().add(effectPane);
        }


    }

    public void updateBoardGraphics(GridPane board, String boardName) {
        // Usamos el tablero correspondiente (Player o Enemy)
        System.out.println(boardName);
        var currentBoard = boardName.equals("Player") ? game.getPlayerBoard() : game.getEnemyBoard();
        // Recorremos todas las celdas del tablero actual
        for (int row = 0; row < currentBoard.size(); row++) {
            for (int col = 0; col < currentBoard.get(row).size(); col++) {
                // Actualizamos la celda con el efecto visual adecuado
                updateCellEffect(board, row, col, boardName); // Actualizar cada celda
            }

        }

        if(boardName.equals("Player")) {
            boardPlayer.setStyle("-fx-border-color: blue; -fx-border-width: 6px;"); // Borde azul para el turno del jugador
            boardEnemy.setStyle("-fx-border-color: null; -fx-border-width: null;");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // 2 segundos de pausa
            pause.setOnFinished(event1 -> {
                // Luego de la pausa, ejecutamos modifyRandomCell
                boardEnemy.setStyle("-fx-border-color: blue; -fx-border-width: 6px;"); // Borde azul para el turno del jugador
                boardPlayer.setStyle("-fx-border-color: null; -fx-border-width: null;");// Restablecer borde del tablero del jugador
                for (Node node : boardEnemy.getChildren()) {
                    node.setDisable(false); // Deshabilita la interactividad del nodo
                }

            });
            pause.play();

        }else{

            boardEnemy.setStyle("-fx-border-color: blue; -fx-border-width: 6px;"); // Borde azul para el turno del jugador
            boardPlayer.setStyle("-fx-border-color: null; -fx-border-width:null;");// Restablecer borde del tablero del jugador
        }
    }

    // Método para obtener el nodo de una celda en base a su fila y columna
    private Node getNodeByRowColumnIndex(int row, int col, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return node;
            }
        }
        return null;
    }
}
