package com.example.navalbattle.model;

import com.example.navalbattle.controller.WelcomeController;
import javafx.animation.PauseTransition;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

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

    public void mapShipsToAnchorPane(AnchorPane pane, boolean isPlayer) {
        // Buscar o crear un contenedor para los barcos
        Group shipLayer = (Group) pane.lookup("#shipLayer");
        if (shipLayer == null) {
            shipLayer = new Group();
            shipLayer.setId("shipLayer");
            pane.getChildren().add(shipLayer);
        }

        // Limpiar solo los barcos, no el tablero
        shipLayer.getChildren().clear();

        double cellSize = 40; // Tamaño de cada celda

        // Obtener el mapa de posiciones
        Map<String, List<List<Object>>> shipPositions = isPlayer ? shipsPositionsPlayer : new HashMap<>();

        // Iterar por cada tipo de barco
        for (Map.Entry<String, List<List<Object>>> entry : shipPositions.entrySet()) {
            String shipType = entry.getKey(); // El nombre del barco es la clave
            List<List<Object>> shipDataList = entry.getValue();

            // Iterar sobre los datos de cada barco (posición y representación visual)
            for (List<Object> shipData : shipDataList) {
                ShipPlacement placement = (ShipPlacement) shipData.get(0); // El primer objeto es el ShipPlacement
                StackPane shipRepresentation = (StackPane) shipData.get(1); // El segundo objeto es la representación visual del barco


                List<Point2D> positions = placement.getPositions();
                int rotation = placement.getRotation();

                int shipSize = positions.size();

                if (!positions.isEmpty()) {
                    // Usar solo la primera posición del barco
                    Point2D firstPosition = positions.get(0);

                    // Calcular la posición en la cuadrícula
                    double x = firstPosition.getY() * cellSize;
                    double y = firstPosition.getX() * cellSize;

                    // Rotar y posicionar la representación del barco
                    applyRotationToShipPart(shipRepresentation, rotation);

                    setShipPosition(shipRepresentation, x, y, rotation, shipSize);



                    // Añadir la representación visual completa del barco al contenedor de barcos
                    shipLayer.getChildren().add(shipRepresentation);
                } else {
                    System.out.println("El barco " + shipType + " no tiene posiciones asignadas.");
                }
            }
        }
    }

    public void setShipPosition(Node shipRepresentation, double x, double y, int rotation, int size) {
        switch (size) {
            case 4 -> {
                switch (rotation) {
                    case 90:  // vertical -- cara apuntando hacia abajo
                        shipRepresentation.setLayoutX(x - 40);
                        shipRepresentation.setLayoutY(y + 65);
                        break;
                    case 180: // horizontal -- cara apuntando hacia izquierda
                        shipRepresentation.setLayoutX(x + 20);
                        shipRepresentation.setLayoutY(y + 5.5);
                        break;
                    case 270: // vertical -- cara apuntando hacia arriba
                        shipRepresentation.setLayoutX(x - 40);
                        shipRepresentation.setLayoutY(y + 65);
                        break;
                    default:   // horizontal -- cara apuntando hacia derecha
                        shipRepresentation.setLayoutX(x + 20);
                        shipRepresentation.setLayoutY(y + 5.5);
                }
            }
            case 3 -> {
                switch (rotation) {
                    case 90:  // vertical -- cara apuntando hacia abajo
                        shipRepresentation.setLayoutX(x - 25);
                        shipRepresentation.setLayoutY(y + 50);
                        break;
                    case 180: // horizontal -- cara apuntando hacia izquierda
                        shipRepresentation.setLayoutX(x + 15);
                        shipRepresentation.setLayoutY(y + 9);
                        break;
                    case 270: // vertical -- cara apuntando hacia arriba
                        shipRepresentation.setLayoutX(x - 25);
                        shipRepresentation.setLayoutY(y + 40);
                        break;
                    default:   // horizontal -- cara apuntando hacia derecha
                        shipRepresentation.setLayoutX(x + 20);
                        shipRepresentation.setLayoutY(y + 9);
                }
            }
            case 2 -> {
                switch (rotation) {
                    case 90:  // vertical -- cara apuntando hacia abajo
                        shipRepresentation.setLayoutX(x - 15);
                        shipRepresentation.setLayoutY(y + 20);
                        break;
                    case 180: // horizontal -- cara apuntando hacia izquierda
                        shipRepresentation.setLayoutX(x + 10);
                        shipRepresentation.setLayoutY(y + 5.5);
                        break;
                    case 270: // vertical -- cara apuntando hacia arriba
                        shipRepresentation.setLayoutX(x - 15);
                        shipRepresentation.setLayoutY(y + 30);
                        break;
                    default:   // horizontal -- cara apuntando hacia derecha
                        shipRepresentation.setLayoutX(x + 2);
                        shipRepresentation.setLayoutY(y + 5.5);
                }
            }
            case 1 -> {
                switch (rotation) {
                    case 90:  // vertical -- cara apuntando hacia abajo
                        shipRepresentation.setLayoutX(x + 1);
                        shipRepresentation.setLayoutY(y + 5);
                        break;
                    case 180: // horizontal -- cara apuntando hacia izquierda
                        shipRepresentation.setLayoutX(x + 1);
                        shipRepresentation.setLayoutY(y + 5.8);
                        break;
                    case 270: // vertical -- cara apuntando hacia arriba
                        shipRepresentation.setLayoutX(x + 1);
                        shipRepresentation.setLayoutY(y + 5);
                        break;
                    default:   // horizontal -- cara apuntando hacia derecha
                        shipRepresentation.setLayoutX(x + 1);
                        shipRepresentation.setLayoutY(y + 5.8);
                }
            }
            default -> {
                shipRepresentation.setLayoutX(x);
                shipRepresentation.setLayoutY(y);
            }
        }
    }
    
    // Método auxiliar para aplicar rotación a las partes del barco
    private void applyRotationToShipPart(Node shipPart, int rotation) {
        // Rotación de 0, 90, 180, 270 grados
        switch (rotation) {
            case 90:
                shipPart.setRotate(90);
                break;
            case 180:
                shipPart.setRotate(180);
                break;
            case 270:
                shipPart.setRotate(270);
                break;
            default:
                shipPart.setRotate(0);
                break;
        }
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
    public void placeAircraftCarrier(GridPane board, ArrayList<ArrayList<Integer>> ArrayList) {
        // Recorre las coordenadas de la flota del jugador

        for (ArrayList<Integer> shipCoordinates : ArrayList) {
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

