package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Class to represent the enemy fleet and its logic in the game.
 * Handles the placement of the enemy fleet on the board, ensuring that ships do not overlap
 * and are placed in valid locations. It also stores and prints the fleet's information.
 */
public class IAFleet {

    private static final int boardSize = 10; // Board size (adjust as necessary)
    private ArrayList<String> enemyFleetInfo;

    /**
     * Constructor to initialize the enemy fleet information list.
     */
    public IAFleet() {
        enemyFleetInfo = new ArrayList<>();
    }

    /**
     * Places the enemy fleet on the board. It ensures that the ships are placed in valid positions
     * and does not overlap with other ships.
     * The fleet consists of ships of different sizes, and their placement is random.
     *
     * @param enemyBoard The board where the enemy fleet will be placed.
     */
    public void placeEnemyFleet(ArrayList<ArrayList<Integer>> enemyBoard) {
        // Ships with their respective sizes
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
                    int row = rand.nextInt(boardSize);
                    int col = rand.nextInt(boardSize);
                    boolean horizontal = rand.nextBoolean();

                    if (canPlaceShip(row, col, size, horizontal, enemyBoard)) {
                        placeShip(row, col, size, horizontal, size, enemyBoard);

                        // Determine ship's direction
                        String direction = "";
                        if (horizontal) {
                            direction = col > 0 ? "right" : "left";  // Determine horizontal direction
                        } else {
                            direction = row > 0 ? "down" : "up";  // Determine vertical direction
                        }

                        // Save ship info as a string with direction
                        String shipInfo = "Ship of size " + size + " placed at (" + row + "," + col + ") "
                                + (horizontal ? "horizontally towards " + direction : "vertically towards " + direction);
                        enemyFleetInfo.add(shipInfo);

                        placed = true;
                    }
                }
            }
        }

        // Print enemy fleet information
        System.out.println("Enemy fleet information:");
        for (String shipInfo : enemyFleetInfo) {
            System.out.println(shipInfo);
        }
    }

    /**
     * Helper method to check if a ship can be placed at the specified position and direction.
     *
     * @param row The starting row of the ship.
     * @param col The starting column of the ship.
     * @param size The size of the ship.
     * @param horizontal True if the ship is placed horizontally, false if vertically.
     * @param board The game board to check placement validity.
     * @return True if the ship can be placed, false otherwise.
     */
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

    /**
     * Helper method to place a ship on the board.
     *
     * @param row The starting row of the ship.
     * @param col The starting column of the ship.
     * @param size The size of the ship.
     * @param horizontal True if the ship is placed horizontally, false if vertically.
     * @param shipValue The value representing the ship on the board.
     * @param board The game board where the ship will be placed.
     */
    private void placeShip(int row, int col, int size, boolean horizontal, int shipValue, ArrayList<ArrayList<Integer>> board) {
        int dr = horizontal ? 0 : 1;
        int dc = horizontal ? 1 : 0;

        for (int i = 0; i < size; i++) {
            int r = row + dr * i;
            int c = col + dc * i;
            board.get(r).set(c, shipValue);
        }
    }

    /**
     * Checks if a cell is within the bounds of the board.
     *
     * @param row The row to check.
     * @param col The column to check.
     * @return True if the cell is within the board's bounds, false otherwise.
     */
    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
    }

    /**
     * Checks if a cell is surrounded by water (no other ships adjacent).
     *
     * @param row The row of the cell to check.
     * @param col The column of the cell to check.
     * @param board The game board to check the surrounding cells.
     * @return True if the cell is surrounded by water, false otherwise.
     */
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
     * Retrieves the enemy fleet's information.
     *
     * @return A list of strings containing information about the enemy fleet's ships.
     */
    public ArrayList<String> getEnemyFleetInfo() {
        return enemyFleetInfo;
    }
}
