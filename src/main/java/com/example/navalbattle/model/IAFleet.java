package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the AI-controlled fleet placement logic for the Naval Battle game.
 * This class is responsible for randomly placing ships on the enemy's game board while ensuring valid placements.
 * Ships are surrounded by water and do not overlap.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class IAFleet {

    private static final int boardSize = 10; // Board size
    private ArrayList<ArrayList<Integer>> enemyFleetCoordinates; // Nested list for fleet coordinates

    /**
     * Constructor to initialize the AI Fleet.
     */
    public IAFleet() {
        enemyFleetCoordinates = new ArrayList<>();
    }

    /**
     * Places the enemy fleet randomly on the game board.
     *
     * @param enemyBoard A 2D board represented as a list of lists where ships are placed.
     */
    public void placeEnemyFleet(ArrayList<ArrayList<Integer>> enemyBoard) {
        // Define the fleet: size and quantity of ships
        int[][] fleet = {
                {4, 1}, // 1 aircraft carrier of size 4
                {3, 2}, // 2 submarines of size 3
                {2, 3}, // 3 destroyers of size 2
                {1, 4}  // 4 frigates of size 1
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

                        // Store the size and coordinates in the nested list
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

        // Print the fleet's coordinates
        System.out.println("Enemy fleet coordinates:");
        for (ArrayList<Integer> shipCoords : enemyFleetCoordinates) {
            System.out.println("Ship of size " + shipCoords.get(0) +
                    " from (" + shipCoords.get(1) + ", " + shipCoords.get(2) +
                    ") to (" + shipCoords.get(3) + ", " + shipCoords.get(4) + ")");
        }
    }

    // Helper method to check if a ship can be placed in a position and direction
    private boolean canPlaceShip(int row, int col, int size, boolean horizontal, ArrayList<ArrayList<Integer>> board) {
        int dr = horizontal ? 0 : 1; // Row increment if vertical
        int dc = horizontal ? 1 : 0; // Column increment if horizontal

        for (int i = 0; i < size; i++) {
            int r = row + dr * i;
            int c = col + dc * i;

            if (!isWithinBounds(r, c) || board.get(r).get(c) != 0 || !isCellSurroundedByWater(r, c, board)) {
                return false;
            }
        }

        return true;
    }

    // Helper method to place a ship on the board
    private void placeShip(int row, int col, int size, boolean horizontal, int shipValue, ArrayList<ArrayList<Integer>> board) {
        int dr = horizontal ? 0 : 1;
        int dc = horizontal ? 1 : 0;

        for (int i = 0; i < size; i++) {
            int r = row + dr * i;
            int c = col + dc * i;
            board.get(r).set(c, shipValue);
        }
    }

    // Checks if a cell is within the board boundaries
    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
    }

    // Checks if a cell is surrounded by water
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

    /**
     * Public method to get the enemy fleet coordinates.
     *
     * @return A nested list of enemy fleet coordinates.
     */
    public ArrayList<ArrayList<Integer>> getEnemyFleetCoordinates() {
        return enemyFleetCoordinates;
    }
}
