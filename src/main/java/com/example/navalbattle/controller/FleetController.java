package com.example.navalbattle.controller;

import com.example.navalbattle.model.*;
import com.example.navalbattle.view.FleetStage;
import com.example.navalbattle.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class FleetController {

    @FXML
    private AnchorPane playerAnchorPane;

    @FXML
    private Pane boardWater;

    private GridPane playerBoard;

    @FXML
    void buttonStartGame(ActionEvent event) {
        FleetStage.deleteInstance();
        GameStage.getInstance();
    }

    @FXML
    void handleClickExit(ActionEvent event) {
        FleetStage.deleteInstance();
    }

    @FXML
    public void initialize() {
        playerBoard = new Board().createBoard(Game.getInstance().getPlayerBoard());
        playerAnchorPane.getChildren().add(playerBoard);
        fleetView();
    }

    @FXML
    void rotateShipsLeft(ActionEvent event) {
        //System.out.println("Método rotateShipsLeft llamado.");
        //System.out.println("Nodos en boardWater: " + boardWater.getChildren().size());

        for (Node node : boardWater.getChildren()) {
            //System.out.println("Iterando sobre nodo...");
            if (node instanceof StackPane) {
                //System.out.println("Nodo es StackPane.");
                StackPane shipPane = (StackPane) node;

                if (shipPane.getUserData() == null) {
                    //System.out.println("El StackPane no tiene userData.");
                    continue;
                }

                if (shipPane.getUserData() instanceof IShip) {
                    //System.out.println("Nodo asociado con IShip.");
                    IShip ship = (IShip) shipPane.getUserData();

                    int newRotation = (ship.getCurrentRotation() - 90) % 360;
                    if (newRotation < 0) {
                        newRotation += 360;
                    }
                    //System.out.println("Rotación anterior: " + ship.getCurrentRotation());
                    //System.out.println("Nueva rotación: " + newRotation);

                    ship.setCurrentRotation(newRotation);
                    shipPane.setRotate(newRotation);
                } else {
                    System.out.println("El userData no es una instancia de IShip.");
                }
            } else {
                System.out.println("Nodo no es StackPane.");
            }
        }
    }

    @FXML
    void rotateShipsRight(ActionEvent event) {
        for (Node node : boardWater.getChildren()) {
            if (node instanceof StackPane) {
                StackPane shipPane = (StackPane) node;

                if (shipPane.getUserData() == null) {
                    continue;
                }

                if (shipPane.getUserData() instanceof IShip) {
                    IShip ship = (IShip) shipPane.getUserData();

                    int newRotation = (ship.getCurrentRotation() + 90) % 360;

                    ship.setCurrentRotation(newRotation);
                    shipPane.setRotate(newRotation);
                } else {
                    System.out.println("El userData no es una instancia de IShip.");
                }
            } else {
                System.out.println("Nodo no es StackPane.");
            }
        }
    }


    private void fleetView() {
        // Crear instancias de los barcos
        Frigate frigate1 = new Frigate();
        Pane frigate1Pane = frigate1.render();
        frigate1Pane.setLayoutX(80); // Establecer la posición X del barco
        frigate1Pane.setLayoutY(70); // Establecer la posición Y del barco
        frigate1Pane.setUserData(frigate1); // Asociar el barco con su nodo visual
        enableDragWithClone(frigate1Pane, frigate1);

        Destroyer destroyer = new Destroyer();
        Pane destroyerPane = destroyer.render();
        destroyerPane.setLayoutX(60);
        destroyerPane.setLayoutY(220);
        destroyerPane.setUserData(destroyer); // Asociar el barco con su nodo visual
        enableDragWithClone(destroyerPane, destroyer);

        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        Pane aircraftCarrierPane = aircraftCarrier.render();
        aircraftCarrierPane.setLayoutX(210);
        aircraftCarrierPane.setLayoutY(70);
        aircraftCarrierPane.setUserData(aircraftCarrier); // Asociar el barco con su nodo visual
        enableDragWithClone(aircraftCarrierPane, aircraftCarrier);

        Submarine submarine = new Submarine();
        Pane submarinePane = submarine.render();
        submarinePane.setLayoutX(220);
        submarinePane.setLayoutY(230);
        submarinePane.setUserData(submarine); // Asociar el barco con su nodo visual
        enableDragWithClone(submarinePane, submarine);

        // Agregar los barcos al tablero de agua (boardWater)
        boardWater.getChildren().addAll(frigate1Pane, destroyerPane, aircraftCarrierPane, submarinePane);
    }

    private <T extends IShip> void enableDragWithClone(Pane shipPane, T ship) {
        shipPane.setOnMousePressed(event -> {
            // Crear una copia del barco y agregarla al tablero de agua
            T clonedShip = (T) ship.clone(); // Clonación del barco usando el método clone
            StackPane clonedPane = clonedShip.render();

            // Copiar posición y rotación del barco original
            clonedPane.setLayoutX(shipPane.getLayoutX());
            clonedPane.setLayoutY(shipPane.getLayoutY());
            clonedPane.setRotate(shipPane.getRotate()); // Copiar la rotación actual
            clonedShip.setCurrentRotation(ship.getCurrentRotation()); // Sincronizar modelo con vista

            // Asociar el barco con el Pane usando UserData
            clonedPane.setUserData(clonedShip);

            // Agregar la nueva instancia al tablero de agua
            boardWater.getChildren().add(clonedPane);


            // Establecer el punto de inicio del arrastre en la copia
            final double[] offset = new double[2];
            clonedPane.setOnMousePressed(e -> {
                offset[0] = e.getSceneX() - clonedPane.getLayoutX();
                offset[1] = e.getSceneY() - clonedPane.getLayoutY();
            });

            clonedPane.setOnMouseDragged(e -> {
                clonedPane.setLayoutX(e.getSceneX() - offset[0]);
                clonedPane.setLayoutY(e.getSceneY() - offset[1]);
            });

            clonedPane.setOnMouseReleased(e -> {
                double cellSize = 40; // Tamaño de la celda
                Bounds clonedBounds = clonedPane.localToScene(clonedPane.getBoundsInLocal());
                Point2D topLeftInPlayer = playerAnchorPane.sceneToLocal(clonedBounds.getMinX(), clonedBounds.getMinY());

                double paneX = topLeftInPlayer.getX();
                double paneY = topLeftInPlayer.getY();

                // Calcular fila y columna inicial
                int startCol = (int) Math.round(paneX / cellSize);
                int startRow = (int) Math.round(paneY / cellSize);

                // Obtener las posiciones ocupadas por el barco
                List<Point2D> occupiedPositions = new ArrayList<>();
                boolean isValidPlacement = true;

                for (int i = 0; i < ship.getSize(); i++) {
                    int row = startRow + (ship.getCurrentRotation() == 180 || ship.getCurrentRotation() == 0 ? 0 : i);
                    int col = startCol + (ship.getCurrentRotation() == 180 || ship.getCurrentRotation() == 0 ? i : 0);

                    // Validar los límites del tablero
                    if (row <= 0 || row >= 11 || col <= 0 || col >= 11) {
                        isValidPlacement = false;
                        break;
                    }

                    // Validar que la celda no esté ocupada
                    if (Game.getInstance().getPlayerBoard().get(row - 1).get(col - 1) != 0) {
                        isValidPlacement = false;
                        break;
                    }

                    occupiedPositions.add(new Point2D(row, col));
                }

                if (isValidPlacement) {
                    // Ajustar el barco al inicio de las posiciones ocupadas
                    Point2D startPosition = occupiedPositions.get(0);
                    double newLayoutX = startPosition.getY() * cellSize; // Columna determina X
                    double newLayoutY = startPosition.getX() * cellSize; // Fila determina Y


                    // < posicionando los barcos en el playerAnchordPane >
                    @SuppressWarnings("unchecked")
                    T associatedShip = (T) clonedPane.getUserData();

                    switch (associatedShip.getSize()) {
                        case 4 -> {
                            if (associatedShip.getCurrentRotation() == 90 || associatedShip.getCurrentRotation() == 270) {
                                clonedPane.setLayoutX(newLayoutX - 40);
                                clonedPane.setLayoutY(newLayoutY + 65);
                            } else if (associatedShip.getCurrentRotation() == 0 || associatedShip.getCurrentRotation() == 180) {
                                clonedPane.setLayoutX(newLayoutX + 20);
                                clonedPane.setLayoutY(newLayoutY + 5.5);
                            }
                        }
                        case 3 -> {
                            if (associatedShip.getCurrentRotation() == 90 || associatedShip.getCurrentRotation() == 270) {
                                clonedPane.setLayoutX(newLayoutX - 25);
                                clonedPane.setLayoutY(newLayoutY + 20);
                            } else if (associatedShip.getCurrentRotation() == 0 || associatedShip.getCurrentRotation() == 180) {
                                clonedPane.setLayoutX(newLayoutX + 20);
                                clonedPane.setLayoutY(newLayoutY + 8);
                            }
                        }
                        case 2 -> {
                            if (associatedShip.getCurrentRotation() == 90 || associatedShip.getCurrentRotation() == 270) {
                                clonedPane.setLayoutX(newLayoutX - 15);
                                clonedPane.setLayoutY(newLayoutY + 20);
                            } else if (associatedShip.getCurrentRotation() == 0 || associatedShip.getCurrentRotation() == 180) {
                                clonedPane.setLayoutX(newLayoutX + 6);
                                clonedPane.setLayoutY(newLayoutY + 5.5);
                            }
                        }
                        case 1 -> {
                            if (associatedShip.getCurrentRotation() == 90 || associatedShip.getCurrentRotation() == 270) {
                                clonedPane.setLayoutX(newLayoutX);
                                clonedPane.setLayoutY(newLayoutY + 5);
                            } else if (associatedShip.getCurrentRotation() == 0 || associatedShip.getCurrentRotation() == 180) {
                                clonedPane.setLayoutX(newLayoutX);
                                clonedPane.setLayoutY(newLayoutY + 5.8);
                            }
                        }
                        default -> {
                            clonedPane.setLayoutX(newLayoutX);
                            clonedPane.setLayoutY(newLayoutY);
                        }
                    }
                    // < end >

                    // desativar movimiento del barco

                    clonedPane.setOnMousePressed(null);
                    clonedPane.setOnMouseDragged(null);
                    clonedPane.setOnMouseReleased(null);

                    // Registrar las celdas ocupadas en el tablero
                    for (Point2D position : occupiedPositions) {
                        int row = (int) position.getX() - 1;
                        int col = (int) position.getY() - 1;


                        switch (associatedShip.getSize()) {
                            case 4 -> {
                                Game.getInstance().getPlayerBoard().get(row).set(col, 4);
                            }
                            case 3 -> {
                                Game.getInstance().getPlayerBoard().get(row).set(col, 3);
                            }
                            case 2 -> {
                                Game.getInstance().getPlayerBoard().get(row).set(col, 2);
                            }
                            case 1 -> {
                                Game.getInstance().getPlayerBoard().get(row).set(col, 1);
                            }
                            default -> {
                                Game.getInstance().getPlayerBoard().get(row).set(col, 0);
                            }
                        }
                    }

                    // Agregar el barco al playerAnchorPane si no está ya
                    if (!playerAnchorPane.getChildren().contains(clonedPane)) {
                        playerAnchorPane.getChildren().add(clonedPane);
                        boardWater.getChildren().remove(clonedPane);
                        System.out.println("Barco colocado en posiciones: " + occupiedPositions);
                    }
                } else {
                    System.out.println("Ubicación inválida. El barco no puede colocarse aquí.");
                    boardWater.getChildren().remove(clonedPane);
                }
            });
        });

    }
}
