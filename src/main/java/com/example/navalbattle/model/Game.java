package com.example.navalbattle.model;

import com.example.navalbattle.model.IGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Game implements IGame, Serializable {

    private static final long serialVersionUID = 1L;  // Añadido para compatibilidad de serialización

    private ArrayList<ArrayList<Integer>> playerBoard;
    private ArrayList<ArrayList<Integer>> enemyBoard;
    private int boardSize;

    // Instancia estática única de la clase Game
    private static Game instance;

    // Constructor privado para evitar la creación de instancias desde fuera
    private Game(int boardSize) {
        this.boardSize = boardSize;
        this.playerBoard = new ArrayList<>();
        this.enemyBoard = new ArrayList<>();
    }

    // Método estático para obtener la única instancia de la clase Game
    public static Game getInstance(int boardSize) {
        if (instance == null) {
            instance = new Game(boardSize);
        }
        return instance;
    }

    public static Game getInstance() {
        return instance;
    }

    // Métodos de la interfaz IGame
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

    public void modifyRandomCell() {
        Random rand = new Random();

        // Elegir aleatoriamente si se modifica el tablero del jugador o el del enemigo
        boolean isPlayerBoard = rand.nextBoolean();

        // Seleccionar una fila y una columna aleatoria
        int row = rand.nextInt(boardSize); // Genera un número aleatorio entre 0 y boardSize - 1
        int col = rand.nextInt(boardSize); // Genera un número aleatorio entre 0 y boardSize - 1

        // Modificar la celda seleccionada de 0 a 1
        if (isPlayerBoard) {
            if (playerBoard.get(row).get(col) == 0) {  // Verificar que sea agua
                playerBoard.get(row).set(col, 1); // Cambiar de 0 a 1
            }
        } else {
            if (enemyBoard.get(row).get(col) == 0) {  // Verificar que sea agua
                enemyBoard.get(row).set(col, 1); // Cambiar de 0 a 1
            }
        }
    }

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

    // Método para imprimir los tableros (con fines de prueba)
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
}
