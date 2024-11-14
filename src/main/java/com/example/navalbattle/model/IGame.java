package com.example.navalbattle.model;

import java.util.ArrayList;


public interface IGame {


    ArrayList<ArrayList<Integer>> getCurrentPlayerBoard();
    ArrayList<ArrayList<Integer>> getCurrentEnemyBoard();
    ArrayList<ArrayList<Integer>> generateBoard10x10();

    void displayBoards();

     void displayBoard(ArrayList<ArrayList<Integer>> board) ;
}
