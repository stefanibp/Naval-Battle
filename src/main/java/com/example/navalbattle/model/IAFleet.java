package com.example.navalbattle.model;

import com.example.navalbattle.controller.WelcomeController;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Random;

public class IAFleet {

    private static final int boardSize = 10; // Tamaño del tablero
    private ArrayList<ArrayList<Integer>> enemyFleetCoordinates; // Lista anidada para las coordenadas de la flota

    public IAFleet() {
        enemyFleetCoordinates = new ArrayList<>();
    }

    public void placeEnemyFleet(ArrayList<ArrayList<Integer>> enemyBoard, Board board) {
        // Barcos con sus tamaños respectivos
        int[][] fleet = {
                {4, 1}, // 1 portaaviones de tamaño 4
                {3, 2}, // 2 submarinos de tamaño 3
                {2, 3}, // 3 destructores de tamaño 2
                {1, 4}  // 4 fragatas de tamaño 1
        };

        Random rand = new Random();

        for (int[] ship : fleet) {
            int size = ship[0];
            int count = ship[1];

            for (int i = 0; i < count; i++) {
                boolean placed = false;

                while (!placed) {
                    int rowStart = rand.nextInt(boardSize);
                    int colStart = rand.nextInt(boardSize);
                    boolean horizontal = rand.nextBoolean();

                    if (canPlaceShip(rowStart, colStart, size, horizontal, enemyBoard)) {
                        int rowEnd = horizontal ? rowStart : rowStart + size - 1;
                        int colEnd = horizontal ? colStart + size - 1 : colStart;

                        placeShip(rowStart, colStart, size, horizontal, size, enemyBoard);

                        // Almacenar el tamaño y las coordenadas en la lista anidada
                        ArrayList<Integer> shipCoordinates = new ArrayList<>();
                        shipCoordinates.add(size);
                        shipCoordinates.add(rowStart);
                        shipCoordinates.add(colStart);
                        shipCoordinates.add(rowEnd);
                        shipCoordinates.add(colEnd);

                        enemyFleetCoordinates.add(shipCoordinates);

                        placed = true;
                    }
                }
            }
        }

        // Generador de números aleatorios
        Random random = new Random();

        // Imprimir coordenadas con el nuevo formato
        for (ArrayList<Integer> shipCoords : enemyFleetCoordinates) {
            int shipSize = shipCoords.get(0);
            String shipName;

            // Determinar el nombre del barco según el tamaño
            switch (shipSize) {
                case 4 -> shipName = "AircraftCarrier";
                case 3 -> shipName = "Submarine";
                case 2 -> shipName = "Destroyer";
                case 1 -> shipName = "Frigate";
                default -> {
                    System.out.println("Barco desconocido: " + shipSize);
                    continue;
                }
            }

            // Crear las posiciones como Point2D
            ArrayList<Point2D> positions = new ArrayList<>();
            int startX = shipCoords.get(1);
            int startY = shipCoords.get(2);
            int endX = shipCoords.get(3);
            int endY = shipCoords.get(4);

            int shipRotation;

            if (startX == endX) { // Barco horizontal
                for (int y = startY; y <= endY; y++) {
                    positions.add(new Point2D(startX, y));
                }
                // Rotación aleatoria entre 0 y 180 grados para horizontal
                shipRotation = random.nextBoolean() ? 0 : 180;
            } else { // Barco vertical
                for (int x = startX; x <= endX; x++) {
                    positions.add(new Point2D(x, startY));
                }
                // Rotación aleatoria entre 90 y 270 grados para vertical
                shipRotation = random.nextBoolean() ? 90 : 270;
            }

            board.registerShipPosition(shipName, shipRotation ,positions, false);
        }

    }

    // Método auxiliar para verificar si un barco cabe en una posición y dirección
    private boolean canPlaceShip(int row, int col, int size, boolean horizontal, ArrayList<ArrayList<Integer>> board) {
        int dr = horizontal ? 0 : 1; // Incremento para filas si es vertical
        int dc = horizontal ? 1 : 0; // Incremento para columnas si es horizontal

        for (int i = 0; i < size; i++) {
            int r = row + dr * i;
            int c = col + dc * i;

            if (!isWithinBounds(r, c) || board.get(r).get(c) != 0 || !isCellSurroundedByWater(r, c, board)) {
                return false;
            }
        }

        return true;
    }

    // Método auxiliar para colocar un barco en el tablero
    private void placeShip(int row, int col, int size, boolean horizontal, int shipValue, ArrayList<ArrayList<Integer>> board) {
        int dr = horizontal ? 0 : 1;
        int dc = horizontal ? 1 : 0;

        for (int i = 0; i < size; i++) {
            int r = row + dr * i;
            int c = col + dc * i;
            board.get(r).set(c, shipValue);
        }
    }

    // Verifica si una celda está dentro de los límites del tablero
    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
    }

    // Verifica que una celda esté rodeada por agua
    private boolean isCellSurroundedByWater(int row, int col, ArrayList<ArrayList<Integer>> board) {
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (isWithinBounds(r, c) && board.get(r).get(c) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Método público para obtener las coordenadas de la flota del enemigo
    public ArrayList<ArrayList<Integer>> getEnemyFleetCoordinates() {
        return enemyFleetCoordinates;
    }
}
