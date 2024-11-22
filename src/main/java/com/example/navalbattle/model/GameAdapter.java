package com.example.navalbattle.model;

import java.util.ArrayList;

public abstract class GameAdapter implements IGame {

    @Override
    public ArrayList<ArrayList<Integer>> generateBoard10x10() {
        return new ArrayList<>();
    }

    @Override
    public void initializeBoardList(){}

    @Override
    public void setPlayerBoard(ArrayList<ArrayList<Integer>> playerBoard){}

    @Override
    public void setEnemyBoard(ArrayList<ArrayList<Integer>> playerBoard){}

    @Override
    public void printBoard(){}

    @Override
    public ArrayList<ArrayList<Integer>> getPlayerBoard() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<ArrayList<Integer>> getEnemyBoard() {
        return new ArrayList<>();
    }
}
