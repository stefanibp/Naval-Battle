package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the Artificial Intelligence (AI) in the Naval Battle game.
 * Manages the AI's board, fleet of ships, and random behaviors for ship placement.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class AIMachine {
    private Board board; // AI's game board
    private List<IShip> fleet; // List of ships in the AI's fleet
    private Random random; // Random number generator for random actions

    private Game game; // The current game instance

    /**
     * Constructs a new AIMachine instance, initializing the board, fleet, and random number generator.
     */
    public AIMachine() {
        this.board = new Board(); // Initializes the board
        this.fleet = new ArrayList<>(); // Initializes the fleet list
        this.random = new Random(); // Initializes the random number generator
        initializeFleet(); // Fills the fleet with ships
    }

    /**
     * Sets the current game instance for the AI machine.
     *
     * @param game The game instance to be set for the AI.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Initializes the fleet for the AI with various ship types.
     * The fleet consists of:
     * <ul>
     *     <li>1 Aircraft Carrier</li>
     *     <li>2 Submarines</li>
     *     <li>3 Destroyers</li>
     *     <li>4 Frigates</li>
     * </ul>
     */
    private void initializeFleet() {
        fleet.add(new AircraftCarrier()); // 1 Aircraft Carrier
        fleet.add(new Submarine()); // 1 Submarine
        fleet.add(new Submarine()); // 2 Submarines
        fleet.add(new Destroyer()); // 1 Destroyer
        fleet.add(new Destroyer()); // 2 Destroyers
        fleet.add(new Destroyer()); // 3 Destroyers
        fleet.add(new Frigate()); // 1 Frigate
        fleet.add(new Frigate()); // 2 Frigates
        fleet.add(new Frigate()); // 3 Frigates
        fleet.add(new Frigate()); // 4 Frigates
    }

}

