package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.Random;

public class IAFleet {

    private static final int boardSize = 10; // Tamaño del tablero (ajústalo según sea necesario)
    private ArrayList<String> enemyFleetInfo;

    public IAFleet() {
        enemyFleetInfo = new ArrayList<>();
    }

    public void placeEnemyFleet(ArrayList<ArrayList<Integer>> enemyBoard) {
        // Barcos con sus tamaños respectivos
        int[][] fleet = {
                {4, 1}, // 1 portaaviones de tamaño 4
                {3, 2}, // 2 submarinos de tamaño 3
                {2, 3}, // 3 destructors de tamaño 2
                {1, 4}  // 4 fragatas de tamaño 1
        };

        Random rand = new Random();

        for (int[] ship : fleet) {
            int size = ship[0];
            int count = ship[1];

            for (int i = 0; i < count; i++) {
                boolean placed = false;

                while (!placed) {
                    int row = rand.nextInt(boardSize);
                    int col = rand.nextInt(boardSize);
                    boolean horizontal = rand.nextBoolean();

                    if (canPlaceShip(row, col, size, horizontal, enemyBoard)) {
                        placeShip(row, col, size, horizontal, size, enemyBoard);

                        // Determinar la dirección del barco (sentido)
                        String direction = "";
                        if (horizontal) {
                            direction = col > 0 ? "derecha" : "izquierda";  // Determinar la dirección horizontal
                        } else {
                            direction = row > 0 ? "abajo" : "arriba";  // Determinar la dirección vertical
                        }

                        // Guardar información del barco como un string con dirección
                        String shipInfo = "Barco de tamaño " + size + " colocado en (" + row + "," + col + ") "
                                + (horizontal ? "horizontalmente hacia " + direction : "verticalmente hacia " + direction);
                        enemyFleetInfo.add(shipInfo);

                        placed = true;
                    }
                }
            }
        }

        // Imprimir la información de la flota
        System.out.println("Información de la flota del enemigo:");
        for (String shipInfo : enemyFleetInfo) {
            System.out.println(shipInfo);
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

    // Nuevo método para obtener la información de la flota del enemigo
    public ArrayList<String> getEnemyFleetInfo() {
        return enemyFleetInfo;
    }
}
