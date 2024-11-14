package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game extends GameAdapter {
    private ArrayList<ArrayList<Integer>> currentPlayerBoard;
    private ArrayList<ArrayList<Integer>> currentEnemyBoard;


    public Game() {
        currentPlayerBoard = generateBoard10x10();
        currentEnemyBoard = generateBoard10x10();
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

    @Override
    public void displayBoards() {
        System.out.println("Player Board:");
        displayBoard(currentPlayerBoard);

        System.out.println("\nEnemy Board:");
        displayBoard(currentEnemyBoard);
    }

    @Override
    public void displayBoard(ArrayList<ArrayList<Integer>> board) {
        for (ArrayList<Integer> row : board) {
            for (Integer cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
    }



   public ArrayList<ArrayList<Integer>> getCurrentPlayerBoard() {
        return currentPlayerBoard;
    }

    public ArrayList<ArrayList<Integer>> getCurrentEnemyBoard() {
        return currentEnemyBoard;
    }


}
