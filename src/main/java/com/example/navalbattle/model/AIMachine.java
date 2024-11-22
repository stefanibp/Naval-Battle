package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Class representing the AI machine in the game. The AIMachine class manages the board, the fleet of ships,
 * and the random number generation for the AI actions.
 */
public class AIMachine {

    private Board board; // AI's board
    private List<IShip> fleet; // List of ships in the fleet
    private Random random;
    private Game game;

    /**
     * Constructor for the AIMachine class. Initializes the board, fleet, and random number generator.
     */
    public AIMachine() {
        this.board = new Board(game);
        this.fleet = new ArrayList<>();
        this.random = new Random();
        initializeFleet();
    }

    /**
     * Sets the game instance for the AI machine.
     *
     * @param game The game instance to be associated with the AI machine.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Initializes the fleet of ships for the AI machine. The fleet consists of various ships including
     * an Aircraft Carrier, Submarines, Destroyers, and Frigates.
     */
    private void initializeFleet() {
        fleet.add(new AircraftCarrier()); // 1 aircraft carrier
        fleet.add(new Submarine());
        fleet.add(new Submarine());       // 2 submarines
        fleet.add(new Destroyer());
        fleet.add(new Destroyer());
        fleet.add(new Destroyer());       // 3 destroyers
        fleet.add(new Frigate());
        fleet.add(new Frigate());
        fleet.add(new Frigate());
        fleet.add(new Frigate());         // 4 frigates
    }
}

