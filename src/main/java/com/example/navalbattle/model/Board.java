package com.example.navalbattle.model;

import com.example.navalbattle.controller.WelcomeController;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Board {

    private Game game;
    int counter;
    GridPane boardPlayer = new GridPane();
    GridPane boardEnemy = new GridPane();
    WelcomeController welcomeController = WelcomeController.getInstance();
    private ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy;
    private ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer;
    private Button newButton;
    public Board(Game game) {
        this.game = game;
        fleetCoordinatesEnemy = new ArrayList<>();
        fleetCoordinatesPlayer = new ArrayList<>();
        fleetCoordinatesPlayer=welcomeController.getFleetCoordinatesPlayer();
        fleetCoordinatesEnemy=welcomeController.getFleetCoordinatesEnemy();
        counter=1;
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
                    cell.setStyle("-fx-background-color: transparent; -fx-border-color:black; -fx-cursor: hand;");

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
        newButton = new Button();
        newButton.setPrefSize(40, 40);
        newButton.setStyle("-fx-background-color: transparent; -fx-border-color: lightgrey; -fx-cursor: hand;");

        int currentRow = row;
        int currentCol = col;
        if (boardName.equals("Enemy")) {

            ///
            newButton.setOnAction(event -> {
                System.out.println("Celda oprimida: Columna " + (currentCol + 1) + ", Fila " + (currentRow + 1));
                game.modifyArraylist(currentRow, currentCol, 6, boardName); // Modifica el estado de la celda
                updateBoardGraphics(boardEnemy, boardName);
                for (Node node : boardEnemy.getChildren()) {
                    node.setDisable(true); // Deshabilita la interactividad del nodo
                }

                // Aquí verificamos el cellValue
                int cellValue = game.getEnemyBoard().get(currentRow).get(currentCol); // Obtener el valor actual de la celda

                // Si el valor de la celda es 5 (miss), cambiamos de turno
                if (cellValue == 5) {
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // 0.5 segundos de pausa
                    pause.setOnFinished(event1 -> {
                        // Luego de la pausa, ejecutamos modifyRandomCell para el turno del enemigo
                        game.modifyRandomCell(); // El enemigo hace su movimiento
                        updateBoardGraphics(boardPlayer, "Player"); // Actualiza el tablero del jugador para su turno
                    });
                    pause.play();
                } else {
                    // Si el valor no es 5 (es decir, hit o sink), no cambiamos de turno
                    for (Node node : boardEnemy.getChildren()) {
                        node.setDisable(false); // Habilita de nuevo la interactividad del nodo
                    }
                }
            });

        }


        board.add(newButton, col + 1, row + 1); // Aseguramos de colocar el botón en la celda correcta
    }
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
            System.out.println("HOLAAAAAAAAAAAAAAAAAAA");
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
    private boolean shouldRotate(int shipType) {
        // Puedes poner la lógica que determines si el barco debe girar
        // Por ejemplo, si el tipo de barco es 4 (AircraftCarrier) o si tiene una bandera de rotación:
        return shipType == 4;  // Aquí puedes aplicar la condición que desees
    }

    public void updateBoardGraphics(GridPane board, String boardName) {
        // Usamos el tablero correspondiente (Player o Enemy)
        System.out.println(boardName);
        var currentBoard = boardName.equals("Player") ? game.getPlayerBoard() : game.getEnemyBoard();

        // Limpiar el tablero eliminando todos los nodos existentes


        // Si el tablero es para el jugador, colocamos el AircraftCarrier
        if (boardName.equals("EnemyF")) {
            placeAircraftCarrier(board);
            // Coloca el AircraftCarrier en el tablero del jugador
        }else{
          board.getChildren().clear();

        }

        // Recorremos todas las celdas del tablero actual
        for (int row = 0; row < currentBoard.size(); row++) {
            for (int col = 0; col < currentBoard.get(row).size(); col++) {
                // Actualizamos la celda con el efecto visual adecuado
                updateCellEffect(board, row, col, boardName); // Actualizar cada celda
            }
        }

        // Lógica para actualizar el borde y la interactividad del tablero
        if(boardName.equals("Player")) {
            boardPlayer.setStyle("-fx-border-color: blue; -fx-border-width: 6px;"); // Borde azul para el turno del jugador
            boardEnemy.setStyle("-fx-border-color: null; -fx-border-width: null;");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5)); // 0.5 segundos de pausa
            pause.setOnFinished(event1 -> {
                // Después de la pausa, restablecer los bordes y habilitar la interactividad
                boardEnemy.setStyle("-fx-border-color: blue; -fx-border-width: 6px;"); // Borde azul para el turno del jugador
                boardPlayer.setStyle("-fx-border-color: null; -fx-border-width: null;");
                for (Node node : boardEnemy.getChildren()) {
                    node.setDisable(false); // Habilita la interactividad del tablero del enemigo
                }
            });
            pause.play();
        } else {
            // Lógica para cuando es el turno del enemigo
            boardEnemy.setStyle("-fx-border-color: blue; -fx-border-width: 6px;"); // Borde azul para el tablero enemigo
            boardPlayer.setStyle("-fx-border-color: null; -fx-border-width:null;");
        }
    }


    // Método para colocar el AircraftCarrier en la celda correcta
    public void placeAircraftCarrier(GridPane board) {
        // Recorre las coordenadas de la flota del jugador
        for (ArrayList<Integer> shipCoordinates : fleetCoordinatesPlayer) {
            int shipType = shipCoordinates.get(0); // El primer valor es el tipo de barco
            int startRow = shipCoordinates.get(1); // Fila inicio
            int startCol = shipCoordinates.get(2); // Columna inicio
            int endRow = shipCoordinates.get(3);   // Fila final
            int endCol = shipCoordinates.get(4);   // Columna final

            Pane shipPane = null;
            double rotationAngle = 0;

            // Crear el barco según el tipo
            switch (shipType) {
                case 4: // AircraftCarrier
                    AircraftCarrier aircraftCarrier = new AircraftCarrier();
                    shipPane = aircraftCarrier.render();
                    break;
                case 3: // Submarine
                    Submarine submarine = new Submarine();
                    shipPane = submarine.render();
                    break;
                case 2: // Destroyer
                    Destroyer destroyer = new Destroyer();
                    shipPane = destroyer.render();
                    break;
                case 1: // Frigate
                    Frigate frigate = new Frigate();
                    shipPane = frigate.render();
                    break;
            }

            // Ajustar el tamaño del Pane para que no exceda las celdas
            if (shipPane != null) {
                shipPane.setPrefSize(40, 40); // Ajustar al tamaño de la celda
            }

            // Calcular la orientación del barco
            if (endCol > startCol) {
                rotationAngle = 0; // Horizontal
            } else if (endRow > startRow) {
                rotationAngle = 90; // Vertical
            }

            // Aplicar la rotación al barco
            if (shipPane != null) {
                shipPane.setRotate(rotationAngle);
            }

            // Colocar el barco en la celda de inicio
            if (shipPane != null) {
                board.add(shipPane, startCol + 1, startRow + 1); // Ajuste de índices para GridPane
            }
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

