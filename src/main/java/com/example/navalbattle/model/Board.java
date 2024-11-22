package com.example.navalbattle.model;

import com.example.navalbattle.controller.WelcomeController;
import javafx.animation.PauseTransition;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Board {
    private static Board instance;
    private Map<String, List<List<Object>>> shipsPositionsPlayer = new HashMap<>();


    private Game game;
    int counter;
    GridPane boardPlayer = new GridPane();
    GridPane boardEnemy = new GridPane();
    //private Board WelcomeController;
    WelcomeController welcomeController = WelcomeController.getInstance();
    private ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy;
    private ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer;
    private Button newButton;

    public Board() {
        game = WelcomeController.getInstance().getGame();
        fleetCoordinatesEnemy = new ArrayList<>();
        fleetCoordinatesPlayer = new ArrayList<>();
        fleetCoordinatesPlayer=welcomeController.getFleetCoordinatesPlayer();
        fleetCoordinatesEnemy=welcomeController.getFleetCoordinatesEnemy();

    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public GridPane createBoardPlayer(ArrayList<ArrayList<Integer>> boards) {

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
                    Label label = new Label(letters[col - 1]);
                    label.setPrefSize(40, 40);
                    label.setStyle("-fx-border-color: grey; -fx-alignment: center; -fx-background-color: #c8c8c8; -fx-font-weight: bold;");
                    board.add(label, col, row);
                } else if (col == 0 && row > 0) {
                    Label label = new Label(String.valueOf(row));
                    label.setPrefSize(40, 40);
                    label.setStyle("-fx-border-color: grey; -fx-alignment: center; -fx-background-color: #c8c8c8; -fx-font-weight: bold;");
                    board.add(label, col, row);
                } else if (row > 0 && col > 0) {

                    int value = boards.get(row - 1).get(col - 1);
                    Button cell = new Button(String.valueOf(value));
                    cell.setPrefSize(40, 40);

                   /* if (value == 0) {
                        cell.setStyle("-fx-border-color: grey; -fx-background-color: #3e8ee8;");
                    } else {
                        cell.setStyle("-fx-border-color: white; -fx-background-color: red;");
                    }      */
                    cell.setStyle("-fx-border-color: grey; -fx-background-color: transparent;");
                    board.add(cell, col, row);
                }
            }
        }
        return board;
    }

    public void registerShipPosition(IShip ship, List<Point2D> positions, boolean isPlayer) {
        String shipType = ship.getClass().getSimpleName();
        int rotation = ship.getCurrentRotation();

        // Crear un mapa para almacenar las posiciones ajustadas y la representación visual del barco
        List<Point2D> adjustedPositions = new ArrayList<>();

        for (Point2D position : positions) {
            double newX = position.getX();
            double newY = position.getY();

            // Agregar la posición ajustada a la lista
            adjustedPositions.add(new Point2D(newX, newY));
        }

        // Crear un objeto ShipPlacement que contiene las posiciones ajustadas y la rotación
        ShipPlacement placement = new ShipPlacement(adjustedPositions, rotation);

        // Crear la representación visual del barco

        Pane shipRepresentation = null;

        switch (shipType) {
            case "AircraftCarrier":
                shipRepresentation = new AircraftCarrier().render();
                break;
            case "Submarine":
                shipRepresentation = new Submarine().render();
                break;
            case "Destroyer":
                shipRepresentation = new Destroyer().render();
                break;
            // Agregar más casos según otros tipos de barcos
            case "Frigate":
                shipRepresentation = new Frigate().render();
                break;
            default:
                System.out.println("Barco desconocido: " + shipType);
                break;
        }

        // Si el jugador es el que está registrando el barco, agregamos la información
        if (isPlayer) {
            // Verifica si ya existe una lista para este tipo de barco
            shipsPositionsPlayer.putIfAbsent(shipType, new ArrayList<>());

            // Usamos un Pair o una estructura similar para almacenar tanto las posiciones como la representación visual
            List<Object> shipData = new ArrayList<>();
            shipData.add(placement); // Agregamos el ShipPlacement
            shipData.add(shipRepresentation); // Agregamos la representación visual

            // Agregar las posiciones y la representación visual del barco
            shipsPositionsPlayer.get(shipType).add(shipData);

            System.out.println("Barco registrado para " + shipType + " con posiciones: " + adjustedPositions);
        }

        System.out.println("Posiciones ajustadas registradas para " + shipType + ": " + adjustedPositions);
    }


  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        } else if (cellValue == 7) {  // Sink (hundido)
            Sink sinkEffect = new Sink();
            Pane effectPane = sinkEffect.renderEffect();
            effectPane.setPrefSize(40, 40);
            GridPane.setRowIndex(effectPane, row + 1);
            GridPane.setColumnIndex(effectPane, col + 1);
            board.getChildren().add(effectPane);
        }
        else if (cellValue == 6) {  // Hit (tocado)
           //System.out.println("HOLAAAAAAAAAAAAAAAAAAA");
            Hit hitEffect = new Hit();
            Pane effectPane = hitEffect.renderEffect();
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

        // Limpiar el tablero eliminando todos los nodos existentes


        // Si el tablero es para el jugador, colocamos el AircraftCarrier
        if (boardName.equals("EnemyF")) {
            placeAircraftCarrier(board,fleetCoordinatesEnemy);
            // Coloca el AircraftCarrier en el tablero del jugador
        }else if(boardName.equals("Player")){
            placeAircraftCarrier(board,fleetCoordinatesPlayer);
         // board.getChildren().clear();
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
    public void placeAircraftCarrier(GridPane board, ArrayList<ArrayList<Integer>> fleetCoordinates) {
        // Dimensiones del GridPane (número de filas y columnas)
        int maxRows = board.getRowCount();
        int maxCols = board.getColumnCount();

        // Recorre las coordenadas de la flota del jugador
        for (ArrayList<Integer> shipCoordinates : fleetCoordinates) {
            int shipType = shipCoordinates.get(0); // El primer valor es el tipo de barco
            int startRow = shipCoordinates.get(1); // Fila inicio
            int startCol = shipCoordinates.get(2); // Columna inicio
            int endRow = shipCoordinates.get(3);   // Fila final
            int endCol = shipCoordinates.get(4);   // Columna final

            Pane shipPane = null;
            double rotationAngle = 0;

            // Verificar si el barco está fuera de los límites y ajustarlo
            if (endRow >= maxRows) {
                int offset = endRow - maxRows + 1;
                startRow -= offset;
                endRow -= offset;
            }
            if (endCol >= maxCols) {
                int offset = endCol - maxCols + 1;
                startCol -= offset;
                endCol -= offset;
            }
            if (startRow < 0) {
                endRow -= startRow; // Ajustar para mantener el tamaño
                startRow = 0;
            }
            if (startCol < 0) {
                endCol -= startCol; // Ajustar para mantener el tamaño
                startCol = 0;
            }

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
                board.add(shipPane, startCol, startRow); // Agregar el barco al GridPane
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

