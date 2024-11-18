package com.example.navalbattle.model;

import java.util.ArrayList;

public interface IGame {
    void initializeBoardList();  // Inicializa los tableros del jugador y enemigo
    int getBoardValue(int row, int col, boolean isPlayerBoard);  // Obtiene el valor de una celda de un tablero
    ArrayList<ArrayList<Integer>> getPlayerBoard();  // Obtiene el tablero del jugador
    ArrayList<ArrayList<Integer>> getEnemyBoard();  // Obtiene el tablero del enemigo
    void setPlayerBoard(ArrayList<ArrayList<Integer>> playerBoard);  // Establece el tablero del jugador
    void setEnemyBoard(ArrayList<ArrayList<Integer>> enemyBoard);  // Establece el tablero del enemigo
    void printBoard();  // Método para imprimir los tableros (opcional)
}
