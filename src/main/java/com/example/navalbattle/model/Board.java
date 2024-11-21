package com.example.navalbattle.model;
import com.example.navalbattle.controller.GameController;
import com.example.navalbattle.controller.WelcomeController;
import com.example.navalbattle.exceptions.InvalidShipPlacementException;
import com.example.navalbattle.exceptions.OutOfBoundsException;
import com.example.navalbattle.view.GameStage;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Duration;

public class Board {
    // Campo estático para manejar la instancia única del registro de posiciones
    private static Board instance;

    // Mapa para almacenar las posiciones de los barcos
    private Map<String, List<List<Object>>> shipsPositionsPlayer = new HashMap<>();

    private Map<String, List<List<Object>>> shipsPositionsMachine = new HashMap<>();


    public Board() {
    }

    // Método Singleton para acceder a la instancia única relacionada con el registro de posiciones
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    // Método normal para crear el tablero
    public GridPane createBoard(ArrayList<ArrayList<Integer>> boards) {

        GridPane board = new GridPane();
        board.setPrefSize(440, 440);
        board.setLayoutX(0);
        board.setLayoutY(0);

        // Letras para la primera fila
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        // Crear las instancias de las figuras fuera del bucle para evitar la creación repetida
        Sink sink = new Sink();
        Miss miss = new Miss();
        Hit hit = new Hit();

        for (int row = 0; row <= 10; row++) {
            for (int col = 0; col <= 10; col++) {
                if (row == 0 && col == 0) {
                    // Esquina superior izquierda vacía
                    board.add(new Pane(), col, row);
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
                    Button cell;

                    // Agregar acción al botón para imprimir las posiciones
                    int finalRow = row;
                    int finalCol = col;

                    if (value == 0) {
                        cell = new Button();
                        cell.setStyle("-fx-border-color: grey; -fx-background-color: #3e8ee8;");
                    } else {
                        cell = new Button(String.valueOf(value));
                        cell.setStyle("-fx-border-color: white; -fx-background-color: red;");
                    }

                    cell.setPrefSize(40, 40);

                    cell.setOnAction(e -> {

                        if (value > 20) { // El barco ya ha sido hundido (valor mayor a 20)
                            board.add(sink.renderEffect(), finalCol, finalRow); // Mostrar efecto de hundimiento
                            //boards.get(finalRow - 1).set(finalCol - 1, value + 30); // Incrementar en 30
                        } else if (value > 10) { // El barco fue impactado (valor entre 11 y 20)
                            board.add(hit.renderEffect(), finalCol, finalRow); // Mostrar efecto de impacto
                            //boards.get(finalRow - 1).set(finalCol - 1, value + 10); // Incrementar en 10
                        } else if (value == 10) { // El barco fue marcado como impactado (valor 10)
                            board.add(miss.renderEffect(), finalCol, finalRow); // Mostrar efecto de fallo
                            //boards.get(finalRow - 1).set(finalCol - 1, value + 10); // Incrementar en 10
                        } else {
                            //boards.get(finalRow - 1).set(finalCol - 1, value); // Si no hay cambios, dejar el valor
                            checkAndSinkShip(false, finalRow -1, finalCol -1);
                        }
                    });

                    board.add(cell, col, row);
                }
            }
        }

        return board;
    }


    public void checkAndSinkShip(boolean isPlayer, int finalRow, int finalCol) {
        ArrayList<ArrayList<Integer>> enemyBoard = isPlayer ? Game.getInstance().getPlayerBoard() : Game.getInstance().getEnemyBoard();

        // Iterar sobre todas las posiciones de los barcos registrados
        Map<String, List<List<Object>>> shipPositions = isPlayer ? shipsPositionsPlayer : WelcomeController.getInstance().getBoard().shipsPositionsMachine;

        System.out.println("shipPositions: " + shipPositions);

        // Primero verificamos si el clic fue en el agua o en un barco
        if (enemyBoard.get(finalRow).get(finalCol) == 0) {
            // Si el clic fue en agua, asignamos 10 y terminamos
            enemyBoard.get(finalRow).set(finalCol, 10);
            System.out.println("Impacto en el agua en (" + finalRow + ", " + finalCol + ")");
            Game.getInstance().printBoard();
            GameController.getController().updateBoard(isPlayer);
            return; // Salimos del método después de impactar agua
        }

        // Si el clic no fue en agua, procedemos a verificar si es un impacto sobre un barco
        for (Map.Entry<String, List<List<Object>>> entry : shipPositions.entrySet()) {
            String shipName = entry.getKey();
            int shipCode;

            // Determinar el código base del barco según su nombre
            switch (shipName) {
                case "AircraftCarrier":
                    shipCode = 4;
                    break;
                case "Submarine":
                    shipCode = 3;
                    break;
                case "Destroyer":
                    shipCode = 2;
                    break;
                case "Frigate":
                    shipCode = 1;
                    break;
                default:
                    System.out.println("Barco desconocido: " + shipName);
                    continue;
            }

            System.out.println("________________________________________" + "______________________________" );

            for (List<Object> shipData : entry.getValue()) {
                ShipPlacement placement = (ShipPlacement) shipData.get(0);
                List<Point2D> positions = placement.getPositions();

                boolean allHit = true; // Flag para verificar si todos los puntos fueron impactados

                // Verificar si alguna de las posiciones del barco ha sido impactada
                for (Point2D position : positions) {
                    int row = (int) position.getX();
                    int col = (int) position.getY();

                    // Si el valor en la celda es el impacto del barco (shipCode + 10), marcar el impacto
                    if (enemyBoard.get(row).get(col) == shipCode + 10) {
                        enemyBoard.get(row).set(col, shipCode + 10); // Marcar como impactado
                        System.out.println("Impacto en el barco en (" + row + ", " + col + ")");
                    } else {
                        allHit = false; // Si algún punto no está impactado, el barco no está hundido
                    }
                }

                // Si todos los puntos fueron impactados, hundimos el barco (incrementamos +20 en cada posición)
                if (allHit) {
                    for (Point2D position : positions) {
                        int row = (int) position.getX();
                        int col = (int) position.getY();
                        enemyBoard.get(row).set(col, shipCode + 20); // Marcar como hundido
                        System.out.println("Barco hundido: " + shipName + " en (" + row + ", " + col + ")");
                    }

                    GameController.getController().updateBoard(isPlayer);
                }
            }
        }
    }


    // Método Singleton para registrar la posición de un barco
    public void registerShipPosition(String shipName, int ShipRotation, List<Point2D> positions, boolean isPlayer) {
        // Crear un mapa para almacenar las posiciones ajustadas y la representación visual del barco
        List<Point2D> adjustedPositions = new ArrayList<>();

        for (Point2D position : positions) {
            double newX = position.getX();
            double newY = position.getY();

            // Agregar la posición ajustada a la lista
            adjustedPositions.add(new Point2D(newX, newY));
        }

        // Crear un objeto ShipPlacement que contiene las posiciones ajustadas y la rotación
        ShipPlacement placement = new ShipPlacement(adjustedPositions, ShipRotation);

        // Crear la representación visual del barco

        StackPane shipRepresentation = null;

        switch (shipName) {
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
                System.out.println("Barco desconocido: " + shipName);
                break;
        }

        // Si el jugador es el que está registrando el barco, agregamos la información
        if (isPlayer) {
            // Verifica si ya existe una lista para este tipo de barco
            shipsPositionsPlayer.putIfAbsent(shipName, new ArrayList<>());

            // Usamos un Pair o una estructura similar para almacenar tanto las posiciones como la representación visual
            List<Object> shipData = new ArrayList<>();
            shipData.add(placement); // Agregamos el ShipPlacement
            shipData.add(shipRepresentation); // Agregamos la representación visual

            // Agregar las posiciones y la representación visual del barco
            shipsPositionsPlayer.get(shipName).add(shipData);

            System.out.println("Player: Barco registrado para " + shipName + " con posiciones: " + adjustedPositions);
        } else {
            // Verifica si ya existe una lista para este tipo de barco
            shipsPositionsMachine.putIfAbsent(shipName, new ArrayList<>());

            // Usamos un Pair o una estructura similar para almacenar tanto las posiciones como la representación visual
            List<Object> shipData = new ArrayList<>();
            shipData.add(placement); // Agregamos el ShipPlacement
            shipData.add(shipRepresentation); // Agregamos la representación visual

            // Agregar las posiciones y la representación visual del barco
            shipsPositionsMachine.get(shipName).add(shipData);

            System.out.println("Machine: Barco registrado para " + shipName + " con posiciones: " + adjustedPositions);
        }

        System.out.println("Posiciones ajustadas registradas para " + shipName + ": " + adjustedPositions);
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
        Map<String, List<List<Object>>> shipPositions = isPlayer ? shipsPositionsPlayer : shipsPositionsMachine;

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

                    setShipPosition(shipRepresentation, x, y, rotation, shipSize , isPlayer);

                    // Añadir la representación visual completa del barco al contenedor de barcos
                    shipLayer.getChildren().add(shipRepresentation);
                } else {
                    System.out.println("El barco " + shipType + " no tiene posiciones asignadas.");
                }
            }
        }
    }


    public void setShipPosition(Node shipRepresentation, double x, double y, int rotation, int size, boolean isPlayer) {
        switch (size) {
            case 4 -> {
                switch (rotation) {
                    case 90:  // vertical -- cara apuntando hacia abajo
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x - 40);
                            shipRepresentation.setLayoutY(y + 65);
                        } else {
                            shipRepresentation.setLayoutX(x + 30);
                            shipRepresentation.setLayoutY(y + 80);
                        }
                        break;
                    case 180: // horizontal -- cara apuntando hacia izquierda
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 20);
                            shipRepresentation.setLayoutY(y + 5.5);
                        } else {
                            shipRepresentation.setLayoutX(x + 60);
                            shipRepresentation.setLayoutY(y + 45);
                        }
                        break;
                    case 270: // vertical -- cara apuntando hacia arriba
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x - 40);
                            shipRepresentation.setLayoutY(y + 65);
                        } else {
                            shipRepresentation.setLayoutX(x);
                            shipRepresentation.setLayoutY(y + 105);
                        }
                        break;
                    default:   // horizontal -- cara apuntando hacia derecha
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 20);
                            shipRepresentation.setLayoutY(y + 5.5);
                        } else {
                            shipRepresentation.setLayoutX(x + 60);
                            shipRepresentation.setLayoutY(y + 45);
                        }
                }
            }
            case 3 -> {
                switch (rotation) {
                    case 90:  // vertical -- cara apuntando hacia abajo
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x - 25);
                            shipRepresentation.setLayoutY(y + 50);
                        } else {
                            shipRepresentation.setLayoutX(x + 15);
                            shipRepresentation.setLayoutY(y + 95);
                        }
                        break;
                    case 180: // horizontal -- cara apuntando hacia izquierda
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 15);
                            shipRepresentation.setLayoutY(y + 9);
                        } else {
                            shipRepresentation.setLayoutX(x + 50);
                            shipRepresentation.setLayoutY(y + 50);
                        }
                        break;
                    case 270: // vertical -- cara apuntando hacia arriba
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x - 25);
                            shipRepresentation.setLayoutY(y + 40);
                        } else {
                            shipRepresentation.setLayoutX(x + 15);
                            shipRepresentation.setLayoutY(y + 85);
                        }
                        break;
                    default:   // horizontal -- cara apuntando hacia derecha
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 20);
                            shipRepresentation.setLayoutY(y + 9);
                        } else {
                            shipRepresentation.setLayoutX(x + 60);
                            shipRepresentation.setLayoutY(y + 50);
                        }
                }
            }
            case 2 -> {
                switch (rotation) {
                    case 90:  // vertical -- cara apuntando hacia abajo
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x - 15);
                            shipRepresentation.setLayoutY(y + 20);
                        } else {
                            shipRepresentation.setLayoutX(x + 25);
                            shipRepresentation.setLayoutY(y + 60);
                        }
                        break;
                    case 180: // horizontal -- cara apuntando hacia izquierda
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 10);
                            shipRepresentation.setLayoutY(y + 5.5);
                        } else {
                            shipRepresentation.setLayoutX(x + 48);
                            shipRepresentation.setLayoutY(y + 45);
                        }
                        break;
                    case 270: // vertical -- cara apuntando hacia arriba
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x - 15);
                            shipRepresentation.setLayoutY(y + 30);
                        } else {
                            shipRepresentation.setLayoutX(x + 25);
                            shipRepresentation.setLayoutY(y + 70);
                        }
                        break;
                    default:   // horizontal -- cara apuntando hacia derecha
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 2);
                            shipRepresentation.setLayoutY(y + 5.5);
                        } else {
                            shipRepresentation.setLayoutX(x + 42);
                            shipRepresentation.setLayoutY(y + 45);
                        }
                }
            }
            case 1 -> {
                switch (rotation) {
                    case 90:  // vertical -- cara apuntando hacia abajo
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 1);
                            shipRepresentation.setLayoutY(y + 5);
                        } else {
                            shipRepresentation.setLayoutX(x + 1);
                            shipRepresentation.setLayoutY(y + 5);
                        }
                        break;
                    case 180: // horizontal -- cara apuntando hacia izquierda
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 1);
                            shipRepresentation.setLayoutY(y + 5.8);
                        } else {
                            shipRepresentation.setLayoutX(x + 40);
                            shipRepresentation.setLayoutY(y + 40);
                        }
                        break;
                    case 270: // vertical -- cara apuntando hacia arriba
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 1);
                            shipRepresentation.setLayoutY(y + 5);
                        } else {
                            shipRepresentation.setLayoutX(x + 1);
                            shipRepresentation.setLayoutY(y + 5);
                        }
                        break;
                    default:   // horizontal -- cara apuntando hacia derecha
                        if (isPlayer) {
                            shipRepresentation.setLayoutX(x + 1);
                            shipRepresentation.setLayoutY(y + 5.8);
                        } else {
                            shipRepresentation.setLayoutX(x + 40);
                            shipRepresentation.setLayoutY(y + 40);
                        }
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

}

