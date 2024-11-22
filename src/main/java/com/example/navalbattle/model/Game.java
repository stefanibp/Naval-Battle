package com.example.navalbattle.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the game logic for a "Naval Battle" game, handling the board setup, ship placement,
 * and checking for hits, misses, and sunk ships.
 * Implements the {@link IGame} interface and is serializable.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Game implements IGame, Serializable {

    private static final long serialVersionUID = 1L;  // Añadido para compatibilidad de serialización

    private ArrayList<ArrayList<Integer>> playerBoard;
    private ArrayList<ArrayList<Integer>> enemyBoard;
    private int boardSize;
    private static Game instance;

    // Constructor
    /**
     * Initializes a new game with the specified board size.
     *
     * @param boardSize the size of the game board
     */
    public Game(int boardSize) {
        this.boardSize = boardSize;
        this.playerBoard = new ArrayList<>();
        this.enemyBoard = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the Game class.
     *
     * @return the singleton Game instance
     */
    public static Game getInstance() {
      
        return instance;
    }

    // Methods from IGame interface
    /**
     * Initializes both the player and enemy boards by creating a grid of water (0).
     */
    @Override
    public void initializeBoardList() {
        for (int i = 0; i < boardSize; i++) {
            ArrayList<Integer> playerRow = new ArrayList<>();
            ArrayList<Integer> enemyRow = new ArrayList<>();
            for (int j = 0; j < boardSize; j++) {
                playerRow.add(0); // Inicializar con 0 (agua)
                enemyRow.add(0);  // Inicializar con 0 (agua)
            }
            playerBoard.add(playerRow);
            enemyBoard.add(enemyRow);
        }
    }

    @Override
    public ArrayList<ArrayList<Integer>> generateBoard10x10() {
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();

        // Crear una estructura de 11x11 llena de ceros
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add(0); // Añadir un cero a cada celda
            }
            board.add(row); // Añadir cada fila a la lista principal
        }
        return board;
    }

    /**
     * Modifies a random cell on the player's board, simulating a shot.
     * The cell will either be marked as a miss (5), hit (6), or part of a sunk ship (7).
     */
    public void modifyRandomCell() {
        Random rand = new Random();

        // Siempre modificamos el tablero del jugador
        boolean isPlayerBoard = true;

        // Seleccionar una fila y una columna aleatoria
        int row = rand.nextInt(boardSize); // Genera un número aleatorio entre 0 y boardSize - 1
        int col = rand.nextInt(boardSize); // Genera un número aleatorio entre 0 y boardSize - 1

        if (isPlayerBoard) {
            // Capturamos el valor actual en la celda seleccionada
            int currentCellValue = playerBoard.get(row).get(col);

            // Lógica de modificación dependiendo del valor actual
            if (currentCellValue == 0) {
                // Si es agua, cambiamos a 5 (Miss)
                modifyArraylist(row, col, 5, "Player");
            } else if (currentCellValue >= 1 && currentCellValue <= 4) {
                // Si es parte de un barco, la marcamos como tocada (6)
                modifyArraylist(row, col, 6, "Player");

                // Verificamos si el barco debe hundirse
                checkAndSinkShip(row, col, currentCellValue, "Player");
            } else if (currentCellValue == 6) {
                // Si ya está tocada, verificamos si el barco completo puede hundirse
                List<int[]> shipCells = new ArrayList<>();

                // Determinamos las celdas asociadas al barco
                checkAndSinkShip(row, col, currentCellValue, "Player");
            } else if (currentCellValue == 7) {
                // Si ya está hundido, no hacemos nada
                System.out.println("La celda seleccionada ya pertenece a un barco hundido.");
            }
        }
    }

    /**
     * Returns the value at a specific cell of the board, either the player's or the enemy's.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param isPlayerBoard whether to fetch the value from the player's board or the enemy's
     * @return the value at the specified cell
     */
    @Override
    public int getBoardValue(int row, int col, boolean isPlayerBoard) {
        if (isPlayerBoard) {
            return playerBoard.get(row).get(col);
        } else {
            return enemyBoard.get(row).get(col);
        }
    }

    // Getters and Setters
    public ArrayList<ArrayList<Integer>> getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(ArrayList<ArrayList<Integer>> playerBoard) {
        this.playerBoard = playerBoard;
    }

    public ArrayList<ArrayList<Integer>> getEnemyBoard() {
        return enemyBoard;
    }

    public void setEnemyBoard(ArrayList<ArrayList<Integer>> enemyBoard) {
        this.enemyBoard = enemyBoard;
    }

    /**
     * Prints the current state of both the player's and enemy's boards for testing purposes.
     */
    public void printBoard() {
        System.out.println("Tablero del Jugador:");
        for (ArrayList<Integer> row : playerBoard) {
            System.out.println(row);
        }

        System.out.println("Tablero del Enemigo:");
        for (ArrayList<Integer> row : enemyBoard) {
            System.out.println(row);
        }
    }

    /**
     * Modifies a specific cell on the board by setting its value to the specified value (e.g., miss, hit).
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param value the new value to set the cell to (e.g., 5 for miss, 6 for hit)
     * @param boardType either "Player" or "Enemy" to specify which board to modify
     */
    public void modifyArraylist(int row, int col, int value, String boardType) {
        // Variable para almacenar el valor actual de la celda antes de modificarla
        int currentCellValue = 0;

        // Dependiendo del tipo de tablero, modificamos playerBoard o enemyBoard
        if (boardType.equals("Player")) {
            // Capturamos el valor actual de la celda
            currentCellValue = playerBoard.get(row).get(col);

            // Si la celda es agua (0), la cambiamos a 5 (parte del barco)
            if (currentCellValue == 0) {
                playerBoard.get(row).set(col, 5);  // Marcamos la celda como parte del barco
            }
            // Si la celda es parte de un barco (valor entre 1 y 4), la cambiamos a 6 (tocada)
            else if (currentCellValue >= 1 && currentCellValue <= 4) {
                playerBoard.get(row).set(col, 6);  // Marcamos como tocada
                // Llamamos a checkAndSinkShip con el valor actual de la celda
               checkAndSinkShip(row, col, currentCellValue, "Player");
            }
        } else if (boardType.equals("Enemy")) {
            // Capturamos el valor actual de la celda
            currentCellValue = enemyBoard.get(row).get(col);

            // Si la celda es agua (0), la cambiamos a 5 (parte del barco)
            if (currentCellValue == 0) {
                enemyBoard.get(row).set(col, 5);  // Marcamos la celda como parte del barco
            }
            // Si la celda es parte de un barco (valor entre 1 y 4), la cambiamos a 6 (tocada)
            else if (currentCellValue >= 1 && currentCellValue <= 4) {
                enemyBoard.get(row).set(col, 6);  // Marcamos como tocada
                // Llamamos a checkAndSinkShip con el valor actual de la celda
                checkAndSinkShip(row, col, currentCellValue, "Enemy");
            }
        }

        // Solo imprimimos la celda que ha sido modificada
        System.out.println("Tablero actualizado: ");
        printBoard();
    }

    /**
     * Checks if a ship is sunk by checking all its cells.
     *
     * @param row the row index of the hit cell
     * @param col the column index of the hit cell
     * @param currentCellValue the value of the cell (representing the ship part)
     * @param boardType the board type, either "Player" or "Enemy"
     */
    public void checkAndSinkShip(int row, int col, int currentCellValue, String boardType) {
        List<int[]> shipCells = new ArrayList<>();
        boolean isHorizontal = false;
        boolean isVertical = false;

        // Elegir el tablero dependiendo del valor de boardType
        ArrayList<ArrayList<Integer>> board;
        if (boardType.equals("Player")) {
            board = playerBoard;
        } else if (boardType.equals("Enemy")) {
            board = enemyBoard;
        } else {
            System.out.println("Tablero inválido.");
            return;
        }

        // Comprobar si el barco es horizontal (verificar celdas a la izquierda y derecha)
        if (col > 0 && (board.get(row).get(col - 1) == currentCellValue || board.get(row).get(col - 1) == 6)) {
            isHorizontal = true;
        } else if (col < board.get(0).size() - 1 && (board.get(row).get(col + 1) == currentCellValue || board.get(row).get(col + 1) == 6)) {
            isHorizontal = true;
        }

        // Comprobar si el barco es vertical (verificar celdas arriba y abajo)
        if (row > 0 && (board.get(row - 1).get(col) == currentCellValue || board.get(row - 1).get(col) == 6)) {
            isVertical = true;
        } else if (row < board.size() - 1 && (board.get(row + 1).get(col) == currentCellValue || board.get(row + 1).get(col) == 6)) {
            isVertical = true;
        }

        // Si es horizontal, recorrer las celdas en ambas direcciones
        if (isHorizontal) {
            int left = col;
            int right = col;

            // Buscar hacia la izquierda
            while (left > 0 && (board.get(row).get(left - 1) == currentCellValue || board.get(row).get(left - 1) == 6)) {
                left--;
            }
            // Buscar hacia la derecha
            while (right < board.get(0).size() - 1 && (board.get(row).get(right + 1) == currentCellValue || board.get(row).get(right + 1) == 6)) {
                right++;
            }

            // Añadir todas las celdas del barco
            for (int i = left; i <= right; i++) {
                shipCells.add(new int[]{row, i});
            }

            // Imprimir la dirección y las celdas
            System.out.println("Dirección: Horizontal");
            System.out.println("Celdas del barco:");
            for (int[] cell : shipCells) {
                System.out.println("Fila: " + cell[0] + ", Columna: " + cell[1]);
            }
        }

        // Si es vertical, recorrer las celdas en ambas direcciones
        if (isVertical) {
            int top = row;
            int bottom = row;

            // Buscar hacia arriba
            while (top > 0 && (board.get(top - 1).get(col) == currentCellValue || board.get(top - 1).get(col) == 6)) {
                top--;
            }
            // Buscar hacia abajo
            while (bottom < board.size() - 1 && (board.get(bottom + 1).get(col) == currentCellValue || board.get(bottom + 1).get(col) == 6)) {
                bottom++;
            }

            // Añadir todas las celdas del barco
            for (int i = top; i <= bottom; i++) {
                shipCells.add(new int[]{i, col});
            }

            // Imprimir la dirección y las celdas
            System.out.println("Dirección: Vertical");
            System.out.println("Celdas del barco:");
            for (int[] cell : shipCells) {
                System.out.println("Fila: " + cell[0] + ", Columna: " + cell[1]);
            }
        }

        // Si no es horizontal ni vertical, es un barco de un solo espacio
        if (!isHorizontal && !isVertical) {
            shipCells.add(new int[]{row, col});
            System.out.println("Barco de un solo espacio.");
            System.out.println("Celda del barco: Fila: " + row + ", Columna: " + col);
        }

        // Llamar al método auxiliar para actualizar las celdas si todas son 6
        updateShipCellsIfAllAreSix(shipCells, board);
    }

    /**
     * Método auxiliar para cambiar las celdas a 7 si todas contienen el número 6.
     *
     * @param shipCells la lista de celdas que forman un barco
     * @param board la matriz del tablero en el que se encuentran las celdas
     */
    public void updateShipCellsIfAllAreSix(List<int[]> shipCells, ArrayList<ArrayList<Integer>> board) {
        // Verificar si todas las celdas contienen el número 6
        boolean allAreSix = true;

        // Comprobar cada celda
        for (int[] cell : shipCells) {
            int row = cell[0];
            int col = cell[1];

            // Si alguna celda no contiene 6, marcar como false
            if (board.get(row).get(col) != 6) {
                allAreSix = false;
                break;
            }
        }

        // Si todas las celdas contienen el número 6, cambiar su valor a 7
        if (allAreSix) {
            for (int[] cell : shipCells) {
                int row = cell[0];
                int col = cell[1];
                board.get(row).set(col, 7); // Cambiar el valor de la celda a 7
            }
            System.out.println("Todas las celdas del barco son 6. Se han actualizado a 7.");
        } else {
            System.out.println("No todas las celdas del barco son 6. No se realizaron cambios.");
        }
    }


}
