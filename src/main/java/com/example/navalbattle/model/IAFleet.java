package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.Random;

public class IAFleet {

    private static final int boardSize = 10; // Tamaño del tablero
    private ArrayList<ArrayList<Integer>> enemyFleetCoordinates; // Lista anidada para las coordenadas de la flota

    public IAFleet() {
        enemyFleetCoordinates = new ArrayList<>();
    }

    public void placeEnemyFleet(ArrayList<ArrayList<Integer>> enemyBoard) {
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

        // Imprimir las coordenadas de los barcos
        System.out.println("Coordenadas de la flota del enemigo:");
        for (ArrayList<Integer> shipCoords : enemyFleetCoordinates) {
            System.out.println("Barco de tamaño " + shipCoords.get(0) +
                    " desde (" + shipCoords.get(1) + ", " + shipCoords.get(2) +
                    ") hasta (" + shipCoords.get(3) + ", " + shipCoords.get(4) + ")");
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
