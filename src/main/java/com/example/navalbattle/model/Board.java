package com.example.navalbattle.model;

import com.example.navalbattle.exceptions.InvalidShipPlacementException;
import com.example.navalbattle.exceptions.OutOfBoundsException;
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

    // Campo estático para manejar la instancia única del registro de posiciones
    private static Board instance;

    // Mapa para almacenar las posiciones de los barcos
    private Map<String, List<List<Object>>> shipsPositionsPlayer = new HashMap<>();

    // Constructor privado para restringir la creación de instancias
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

    // Método Singleton para registrar la posición de un barco
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

        StackPane shipRepresentation = null;

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
}
