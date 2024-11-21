package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIMachine {
    private Board board; // Tablero de la IA
    private List<IShip> fleet; // Lista de barcos
    private Random random;

    public AIMachine() {
       this.board = new Board();
        this.fleet = new ArrayList<>();
        this.random = new Random();
        initializeFleet();
    }
    private Game game;
    public void setGame(Game game) {
        this.game = game;
    }
    // Inicializa la flota de la IA
    private void initializeFleet() {
        fleet.add(new AircraftCarrier()); // 1 portaaviones
        fleet.add(new Submarine());
        fleet.add(new Submarine());       // 2 submarinos
        fleet.add(new Destroyer());
        fleet.add(new Destroyer());
        fleet.add(new Destroyer());       // 3 destructores
        fleet.add(new Frigate());
        fleet.add(new Frigate());
        fleet.add(new Frigate());
        fleet.add(new Frigate());         // 4 fragatas
    }


}

