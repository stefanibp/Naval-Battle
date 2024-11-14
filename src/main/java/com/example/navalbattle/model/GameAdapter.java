package com.example.navalbattle.model;

import java.util.ArrayList;

public abstract class GameAdapter implements IGame {

    @Override
    public ArrayList<ArrayList<Integer>> generateBoard10x10() {
        return new ArrayList<>();
    }

    @Override
    public void displayBoards(){

    }

    @Override
    public void displayBoard(ArrayList<ArrayList<Integer>> board){

    }
    @Override
    public ArrayList<ArrayList<Integer>> getCurrentPlayerBoard() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCurrentEnemyBoard() {
        return new ArrayList<>();
    }
}
