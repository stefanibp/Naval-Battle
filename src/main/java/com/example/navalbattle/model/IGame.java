package com.example.navalbattle.model;

import java.util.ArrayList;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Interface for the game logic, managing the player's and enemy's boards and their operations.
 * Provides methods to initialize boards, get and set board values, and print boards.
 */
public interface IGame {
    /**
     * Initializes the boards for both the player and the enemy.
     */
    void initializeBoardList();

    /**
     * Retrieves the value of a cell from the specified board.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @param isPlayerBoard If true, it retrieves the value from the player's board. If false, it retrieves from the enemy's board.
     * @return The value of the specified cell.
     */
    int getBoardValue(int row, int col, boolean isPlayerBoard);

    /**
     * Retrieves the player's board.
     *
     * @return The player's board as a 2D ArrayList of integers.
     */
    ArrayList<ArrayList<Integer>> getPlayerBoard();

    /**
     * Retrieves the enemy's board.
     *
     * @return The enemy's board as a 2D ArrayList of integers.
     */
    ArrayList<ArrayList<Integer>> getEnemyBoard();

    /**
     * Sets the player's board to the provided 2D ArrayList of integers.
     *
     * @param playerBoard The 2D ArrayList representing the player's board.
     */
    void setPlayerBoard(ArrayList<ArrayList<Integer>> playerBoard);

    /**
     * Sets the enemy's board to the provided 2D ArrayList of integers.
     *
     * @param enemyBoard The 2D ArrayList representing the enemy's board.
     */
    void setEnemyBoard(ArrayList<ArrayList<Integer>> enemyBoard);

    /**
     * Prints the current state of the boards (optional method for displaying the boards in the console).
     */
    void printBoard();
}