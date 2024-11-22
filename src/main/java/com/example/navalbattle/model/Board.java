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

/**
 * Represents the board used in the Naval Battle game. This class handles the initialization
 * and management of the player's and enemy's boards, as well as the registration of ships.
 * Implements the Singleton pattern to ensure only one instance exists.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
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

    /**
     * Constructs a new Board instance, initializing necessary game components
     * and fetching fleet coordinates from the WelcomeController.
     */
    public Board() {
        game = WelcomeController.getInstance().getGame();
        fleetCoordinatesEnemy = new ArrayList<>();
        fleetCoordinatesPlayer = new ArrayList<>();
        fleetCoordinatesPlayer = welcomeController.getFleetCoordinatesPlayer();
        fleetCoordinatesEnemy = welcomeController.getFleetCoordinatesEnemy();
    }

    /**
     * Retrieves the singleton instance of the Board class.
     *
     * @return the singleton instance of the Board.
     */
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    /**
     * Creates the player's board grid with predefined labels and styles.
     *
     * @param boards a 2D ArrayList containing the board's cell values.
     * @return a GridPane representing the player's board.
     */
    public GridPane createBoardPlayer(ArrayList<ArrayList<Integer>> boards) {

        GridPane board = new GridPane();
        board.setPrefSize(440, 440);
        board.setLayoutX(0);
        board.setLayoutY(0);


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
                    label.setStyle("-fx-border-color: grey; -fx-alignment: center; "
                            + "-fx-background-color: #c8c8c8; -fx-font-weight: bold;");
                    board.add(label, col, row);
                } else if (col == 0 && row > 0) {
                    Label label = new Label(String.valueOf(row));
                    label.setPrefSize(40, 40);
                    label.setStyle("-fx-border-color: grey; -fx-alignment: center; "
                            + "-fx-background-color: #c8c8c8; -fx-font-weight: bold;");
                    board.add(label, col, row);
                } else if (row > 0 && col > 0) {

                    int value = boards.get(row - 1).get(col - 1);
                    Button cell = new Button(String.valueOf(value));
                    cell.setPrefSize(40, 40);


                    cell.setStyle("-fx-border-color: grey; -fx-background-color: transparent;");
                    board.add(cell, col, row);
                }
            }
        }
        return board;
    }

    /**
     * Registers a ship's position and visual representation on the board.
     *
     * @param ship      the ship to be registered.
     * @param positions a list of Point2D objects representing the ship's coordinates.
     * @param isPlayer  a boolean indicating whether the player is registering the ship.
     */
    public void registerShipPosition(IShip ship, List<Point2D> positions, boolean isPlayer) {
        String shipType = ship.getClass().getSimpleName();
        int rotation = ship.getCurrentRotation();
        List<Point2D> adjustedPositions = new ArrayList<>();

        for (Point2D position : positions) {
            adjustedPositions.add(new Point2D(position.getX(), position.getY()));
        }

        ShipPlacement placement = new ShipPlacement(adjustedPositions, rotation);
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
            case "Frigate":
                shipRepresentation = new Frigate().render();
                break;
            default:
                System.out.println("Unknown ship type: " + shipType);
                break;
        }

        // Si el jugador es el que está registrando el barco, agregamos la información
        if (isPlayer) {
            // Verifica si ya existe una lista para este tipo de barco
            shipsPositionsPlayer.putIfAbsent(shipType, new ArrayList<>());

            // Usamos un Pair o una estructura similar para almacenar tanto las posiciones como la representación visual
            List<Object> shipData = new ArrayList<>();
            shipData.add(placement);
            shipData.add(shipRepresentation);
            shipsPositionsPlayer.get(shipType).add(shipData);
            System.out.println("Ship registered for " + shipType + " with positions: " + adjustedPositions);
        }

        System.out.println("Adjusted positions registered for " + shipType + ": " + adjustedPositions);
    }

    /**
     * Creates a GridPane representing a game board for "Battleship". The board can be either the player's
     * or the enemy's board, as determined by the given board name. The method initializes the grid layout,
     * adds labels for rows and columns, and sets up clickable cells for interaction.
     *
     * @param boardName The name of the board being created. Use "Player" for the player's board and "Enemy"
     *                  for the opponent's board. Determines specific configurations such as event handling.
     * @return A GridPane object representing the game board with rows and columns labeled, and cells initialized.
     * @throws IllegalArgumentException if boardName is null or invalid.
     * @see LabelCell
     */
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
                        boardPlayer = board;

                    }

                    board.add(cell, col, row);
                }
            }
        }

        updateBoardGraphics(board, boardName);


        return board;
    }

    /**
     * Resets a specific cell in the game board by purging its current content and replacing it with a new button.
     * If the board is the "Enemy" board, the cell is assigned a click event to handle game logic.
     *
     * @param board     the {@link GridPane} representing the game board.
     * @param row       the row index of the cell to be purged (0-based).
     * @param col       the column index of the cell to be purged (0-based).
     * @param boardName the name of the board ("Player" or "Enemy").
     */
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

    /**
     * Updates the visual effect of a specific cell in the game board based on its state.
     * The visual effect corresponds to the cell's value, such as "Miss", "Hit", or "Sink".
     *
     * @param board     the {@link GridPane} representing the game board.
     * @param row       the row index of the cell to update (0-based).
     * @param col       the column index of the cell to update (0-based).
     * @param boardName the name of the board ("Player" or "Enemy").
     */
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
        } else if (cellValue == 6) {  // Hit (tocado)
            //System.out.println("HOLAAAAAAAAAAAAAAAAAAA");
            Hit hitEffect = new Hit();
            Pane effectPane = hitEffect.renderEffect();
            effectPane.setPrefSize(40, 40);
            GridPane.setRowIndex(effectPane, row + 1);
            GridPane.setColumnIndex(effectPane, col + 1);
            board.getChildren().add(effectPane);

        }


    }

    /**
     * Updates the graphical representation of the entire game board, including cells and visual effects.
     * Depending on the board type, it applies specific logic, such as placing ships or updating interactivity.
     *
     * @param board     the {@link GridPane} representing the game board.
     * @param boardName the name of the board ("Player", "Enemy", or "EnemyF").
     */
    public void updateBoardGraphics(GridPane board, String boardName) {
        // Usamos el tablero correspondiente (Player o Enemy)
        System.out.println(boardName);
        var currentBoard = boardName.equals("Player") ? game.getPlayerBoard() : game.getEnemyBoard();

        // Limpiar el tablero eliminando todos los nodos existentes


        // Si el tablero es para el jugador, colocamos el AircraftCarrier
        if (boardName.equals("EnemyF")) {
            placeAircraftCarrier(board, fleetCoordinatesEnemy);
            // Coloca el AircraftCarrier en el tablero del jugador
        } else if (boardName.equals("Player")) {
            placeAircraftCarrier(board, fleetCoordinatesPlayer);
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
        if (boardName.equals("Player")) {
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

    /**
     * Places an AircraftCarrier or other ship types on the specified GridPane at the correct cell position.
     * This method ensures that ships are within the bounds of the GridPane and adjusts their positions
     * and orientations accordingly. It uses the provided fleet coordinates to determine the placement
     * and rotation of the ships.
     *
     * @param board            The GridPane representing the player's game board.
     * @param fleetCoordinates A list of coordinates for the player's fleet. Each list entry contains:
     *                         - [0]: The type of the ship (1 = Frigate, 2 = Destroyer, 3 = Submarine, 4 = AircraftCarrier).
     *                         - [1]: The starting row of the ship.
     *                         - [2]: The starting column of the ship.
     *                         - [3]: The ending row of the ship.
     *                         - [4]: The ending column of the ship.
     *                         If the ship is out of bounds, its position will be adjusted.
     * @throws IllegalArgumentException  if an invalid ship type is provided.
     * @throws IndexOutOfBoundsException if fleetCoordinates contain invalid indices for rows or columns.
     */
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


    /**
     * Retrieves the node in the {@link GridPane} at the specified row and column index.
     * This method searches through all the children of the grid to find the node matching the given coordinates.
     *
     * @param row      the row index of the desired node (0-based).
     * @param col      the column index of the desired node (0-based).
     * @param gridPane the {@link GridPane} containing the nodes.
     * @return the {@link Node} at the specified position, or {@code null} if no node is found.
     */
    private Node getNodeByRowColumnIndex(int row, int col, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return node;
            }
        }
        return null;
    }
}

