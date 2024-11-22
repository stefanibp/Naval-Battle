package com.example.navalbattle.controller;

import com.example.navalbattle.model.Board;
import com.example.navalbattle.model.Game;
import com.example.navalbattle.model.IAFleet;
import com.example.navalbattle.model.SerializableFileHandlerPosition;
import com.example.navalbattle.view.FleetStage;
import com.example.navalbattle.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import com.example.navalbattle.model.*;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.List;

public class FleetController {

    @FXML
    private Game game;
    @FXML
    private IAFleet enemyFleet;
    @FXML
    private AnchorPane playerAnchorPane;
    @FXML
    private Board boardModel;
    @FXML
    private Pane boardWater;
    WelcomeController welcomeController = WelcomeController.getInstance();
    private GridPane playerBoard;

    private int frigateCount = 0; // Contador para las fragatas colocadas
    private static final int MAX_FRIGATES = 4; // Límite máximo

    private int destroyerCount = 0;
    private static final int MAX_DESTROYER = 3;

    private int aircraftCarrierCount = 0;
    private static final int MAX_AIRCRAFTCARRIER = 1;

    private int submarineCount = 0;
    private static final int MAX_SUBMARINE = 2;
    private ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer;

    private int totalShip = 0, address = 0,  rowI = 0, rowE = 0, colI = 0, colE = 0;

    @FXML
            //listo
    void buttonStartGame(ActionEvent event) {
              /*  if(frigateCount == MAX_FRIGATES && destroyerCount == MAX_DESTROYER && aircraftCarrierCount == MAX_AIRCRAFTCARRIER && submarineCount == MAX_SUBMARINE){
                    FleetStage.deleteInstance();
                    GameStage.getInstance();
                }   */
        String FILE_NAME = "game_boardsPositions.dat";
        SerializableFileHandlerPosition fileHandler = new SerializableFileHandlerPosition();

        WelcomeController.getInstance().setfleetCoordinatesPlayer(fleetCoordinatesPlayer);

        fileHandler.serialize(FILE_NAME, welcomeController.getFleetCoordinatesEnemy(), welcomeController.getFleetCoordinatesPlayer());
        System.out.println("Juego guardado en " + FILE_NAME);


        FleetStage.deleteInstance();
        GameStage.getInstance();

    }

    @FXML
    void handleClickExit(ActionEvent event) {
        FleetStage.deleteInstance();
    }

    @FXML
    public void initialize() {
        fleetCoordinatesPlayer = new ArrayList<>();
        initializeBoardList();
        game = WelcomeController.getInstance().getGame();
                game.printBoard();
    playerBoard = new Board().createBoardPlayer(game.getPlayerBoard());
        playerAnchorPane.getChildren().add(playerBoard);
        fleetView();
    }
    public void initializeBoardList() {
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                row.add(0); // Rellenar con valores por defecto, como 0
            }
            fleetCoordinatesPlayer.add(row);
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

            if (ship instanceof Frigate && frigateCount >= MAX_FRIGATES) {
                System.out.println("No se pueden colocar más de " + MAX_FRIGATES + " fragatas.");
                return; // No permitir más fragatas
            }

            if (ship instanceof Destroyer && destroyerCount >= MAX_DESTROYER) {
                System.out.println("No se pueden colocar más de " + MAX_DESTROYER + " destructores.");
                return; // No permitir más fragatas
            }

            if (ship instanceof AircraftCarrier && aircraftCarrierCount >= MAX_AIRCRAFTCARRIER) {
                System.out.println("No se pueden colocar más de " + MAX_AIRCRAFTCARRIER + " portaaviones.");
                return; // No permitir más fragatas
            }

            if (ship instanceof Submarine && submarineCount >= MAX_SUBMARINE) {
                System.out.println("No se pueden colocar más de " + MAX_SUBMARINE + " submarinos.");
                return; // No permitir más fragatas
            }

            // Crear una copia del barco y agregarla al tablero de agua
            T clonedShip = (T) ship.clone(); // Clonación del barco usando el método clone
            Pane clonedPane = clonedShip.render();

            // Copiar posición y rotación del barco original
            clonedPane.setLayoutX(shipPane.getLayoutX());
            System.out.println("XXXXXXXXXX"+shipPane.getLayoutX()); ////
            clonedPane.setLayoutY(shipPane.getLayoutY());
            System.out.println("yyyyyyyyy"+shipPane.getLayoutX()); ////
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

            clonedPane.setOnMouseReleased(e -> {   //////////////////
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
                    if (game.getPlayerBoard().get(row - 1).get(col - 1) != 0) {       //////////
                        isValidPlacement = false;
                        break;
                    }

                    occupiedPositions.add(new Point2D(row, col));
                }

                  int counter=0;

                if (isValidPlacement) {
                    // Ajustar el barco al inicio de las posiciones ocupadas
                    double moveX = -10;  // Ajuste en X para mover hacia la izquierda (puedes cambiar este valor)
                    double moveY = -5;  // Ajuste en Y para mover hacia arriba (puedes cambiar este valor)

// Ajustar el barco al inicio de las posiciones ocupadas
                    Point2D startPosition = occupiedPositions.get(0);
                    double newLayoutX = startPosition.getY() * cellSize + moveX; // Columna determina X
                    double newLayoutY = startPosition.getX() * cellSize + moveY; // Fila determina Y

// < posicionando los barcos en el playerAnchorPane >
                    @SuppressWarnings("unchecked")
                    T associatedShip = (T) clonedPane.getUserData();

// Ajustar la posición del barco
                    clonedPane.setLayoutX(newLayoutX);
                    clonedPane.setLayoutY(newLayoutY);


                    switch (associatedShip.getSize()) {
                        case 4 -> {
                            switch (associatedShip.getCurrentRotation()) {
                                case 0: // horizontal -- cara apuntando hacia derecha
                                    clonedPane.setLayoutX(newLayoutX + 20);
                                    clonedPane.setLayoutY(newLayoutY + 5.5);
                                    break;
                                case 90: // vertical -- cara apuntando hacia abajo
                                    clonedPane.setLayoutX(newLayoutX - 40);
                                    clonedPane.setLayoutY(newLayoutY + 65);
                                    address=1;
                                    break;
                                case 180: // horizontal -- cara apuntando hacia izquierda
                                    clonedPane.setLayoutX(newLayoutX + 20);
                                    clonedPane.setLayoutY(newLayoutY + 5.5);
                                    address=2;
                                    break;
                                case 270: // vertical -- cara apuntando hacia arriba
                                    clonedPane.setLayoutX(newLayoutX - 40);
                                    clonedPane.setLayoutY(newLayoutY + 65);
                                    address=3;
                                    break;
                                default:
                                    clonedPane.setLayoutX(newLayoutX);
                                    clonedPane.setLayoutY(newLayoutY);
                            }
                        }
                        case 3 -> {
                            switch (associatedShip.getCurrentRotation()) {
                                case 0: // horizontal -- cara apuntando hacia derecha
                                    clonedPane.setLayoutX(newLayoutX + 20);
                                    clonedPane.setLayoutY(newLayoutY + 9);
                                    break;
                                case 90: // vertical -- cara apuntando hacia abajo
                                    clonedPane.setLayoutX(newLayoutX - 25);
                                    clonedPane.setLayoutY(newLayoutY + 50);
                                    address=1;
                                    break;
                                case 180: // horizontal -- cara apuntando hacia izquierda
                                    clonedPane.setLayoutX(newLayoutX + 15);
                                    clonedPane.setLayoutY(newLayoutY + 9);
                                    address=2;
                                    break;
                                case 270: // vertical -- cara apuntando hacia arriba
                                    clonedPane.setLayoutX(newLayoutX - 25);
                                    clonedPane.setLayoutY(newLayoutY + 40);
                                    address=3;
                                    break;
                                default:
                                    clonedPane.setLayoutX(newLayoutX);
                                    clonedPane.setLayoutY(newLayoutY);
                            }
                        }
                        case 2 -> {
                            switch (associatedShip.getCurrentRotation()) {
                                case 0: // horizontal -- cara apuntando hacia derecha
                                    clonedPane.setLayoutX(newLayoutX + 2);
                                    clonedPane.setLayoutY(newLayoutY + 5.5);
                                    break;
                                case 90: // vertical -- cara apuntando hacia abajo
                                    clonedPane.setLayoutX(newLayoutX - 15);
                                    clonedPane.setLayoutY(newLayoutY + 20);
                                    address=1;
                                    break;
                                case 180: // horizontal -- cara apuntando hacia izquierda
                                    clonedPane.setLayoutX(newLayoutX + 10);
                                    clonedPane.setLayoutY(newLayoutY + 5.5);
                                    address=2;
                                    break;
                                case 270: // vertical -- cara apuntando hacia arriba
                                    clonedPane.setLayoutX(newLayoutX - 15);
                                    clonedPane.setLayoutY(newLayoutY + 30);
                                    address=3;
                                    break;
                                default:
                                    clonedPane.setLayoutX(newLayoutX);
                                    clonedPane.setLayoutY(newLayoutY);
                            }
                        }
                        case 1 -> {
                            switch (associatedShip.getCurrentRotation()) {
                                case 0: // horizontal -- cara apuntando hacia derecha
                                    clonedPane.setLayoutX(newLayoutX + 1);
                                    clonedPane.setLayoutY(newLayoutY + 5.8);
                                    break;
                                case 90: // vertical -- cara apuntando hacia abajo
                                    clonedPane.setLayoutX(newLayoutX + 1);
                                    clonedPane.setLayoutY(newLayoutY + 5);
                                    address=1;
                                    break;
                                case 180: // horizontal -- cara apuntando hacia izquierda
                                    clonedPane.setLayoutX(newLayoutX + 1);
                                    clonedPane.setLayoutY(newLayoutY + 5.8);
                                    address=2;
                                    break;
                                case 270: // vertical -- cara apuntando hacia arriba
                                    clonedPane.setLayoutX(newLayoutX + 1);
                                    clonedPane.setLayoutY(newLayoutY + 5);
                                    address=3;
                                    break;
                                default:
                                    clonedPane.setLayoutX(newLayoutX);
                                    clonedPane.setLayoutY(newLayoutY);
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


                            if (counter == 0 ) {
                                                rowI = row;
                                                colI = col;
                                    fleetCoordinatesPlayer.get(totalShip).set(0, associatedShip.getSize());
                            }
                            if (counter == associatedShip.getSize()-1) {
                                rowE = row;
                                colE = col;
                            }



                            counter++;

                        switch (associatedShip.getSize()) {
                            case 4 -> {
                                game.getPlayerBoard().get(row).set(col, 4);

                            }
                            case 3 -> {
                                game.getPlayerBoard().get(row).set(col, 3);
                            }
                            case 2 -> {
                                game.getPlayerBoard().get(row).set(col, 2);
                            }
                            case 1 -> {
                                game.getPlayerBoard().get(row).set(col, 1);
                            }
                            default -> {
                                game.getPlayerBoard().get(row).set(col, 0);
                            }
                        }
                    }

                    // Agregar el barco al playerAnchorPane si no está ya
                    if (!playerAnchorPane.getChildren().contains(clonedPane)) {

                        if (rowI == rowE && colI == colE) {
                            // Si las coordenadas inicial y final son iguales, asignarlas directamente
                            fleetCoordinatesPlayer.get(totalShip).set(1, rowI); // Fila
                            fleetCoordinatesPlayer.get(totalShip).set(2, colI); // Columna
                            fleetCoordinatesPlayer.get(totalShip).set(3, rowI); // Fila (repetida, ya que es la misma celda)
                            fleetCoordinatesPlayer.get(totalShip).set(4, colI); // Columna (repetida)

                        } else {
                            // Lógica normal para múltiples celdas
                            int[][] positions = {
                                    {rowI, Math.max(colI, colE), rowI, Math.min(colI, colE)}, // case 0
                                    {Math.max(rowI, rowE), colI, Math.min(rowI, rowE), colI}, // case 1
                                    {rowE, Math.min(colI, colE), rowE, Math.max(colI, colE)}, // case 2
                                    {Math.min(rowI, rowE), colE, Math.max(rowI, rowE), colE}  // case 3
                            };

                            if (address >= 0 && address <= 3) {
                                int[] coords = positions[address];
                                fleetCoordinatesPlayer.get(totalShip).set(1, coords[0]); // Fila inicial o final
                                fleetCoordinatesPlayer.get(totalShip).set(2, coords[1]); // Columna inicial o final
                                fleetCoordinatesPlayer.get(totalShip).set(3, coords[2]); // Fila final o inicial
                                fleetCoordinatesPlayer.get(totalShip).set(4, coords[3]); // Columna final o inicial
                            }
                        }


                        rowE=0;
                        colE=0;
                        rowI=0;
                        colI=0;
                        totalShip++;
                        System.out.println("Tablero del Jugador:");
                        for (ArrayList<Integer> t : fleetCoordinatesPlayer) {
                            System.out.println(t);
                        }

                        
                        playerAnchorPane.getChildren().add(clonedPane);
                        boardWater.getChildren().remove(clonedPane);

                        if (clonedShip instanceof Frigate) {
                            frigateCount++;
                        }

                        if (clonedShip instanceof Destroyer) {
                            destroyerCount++;
                        }

                        if (clonedShip instanceof AircraftCarrier) {
                            aircraftCarrierCount++;
                        }

                        if (clonedShip instanceof Submarine) {
                            submarineCount++;
                        }

                        // Usar el Singleton para registrar posiciones
                        Board.getInstance().registerShipPosition(clonedShip, occupiedPositions, true);

                        System.out.println("Barco colocado en posiciones: " + occupiedPositions);
                    }
                } else {
                    System.out.println("Ubicación inválida. El barco no puede colocarse aquí.");
                    boardWater.getChildren().remove(clonedPane);
                }





            });
        });

    }

    @FXML
    void rotateShipsLeft(ActionEvent event) {
        //System.out.println("Método rotateShipsLeft llamado.");
        //System.out.println("Nodos en boardWater: " + boardWater.getChildren().size());

        for (Node node : boardWater.getChildren()) {
            //System.out.println("Iterando sobre nodo...");
            if (node instanceof Pane) {
                //System.out.println("Nodo es StackPane.");
                Pane shipPane = (Pane) node;

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
            if (node instanceof Pane) {
                Pane shipPane = (Pane) node;

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

}
