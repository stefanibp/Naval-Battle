package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Represents a ship in the Naval Battle game.
 * Each ship has a name, size, and a method to render its visual representation on the game board.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public interface IShip {

    /**
     * Gets the name of the ship.
     *
     * @return The name of the ship.
     */
    String getName();

    /**
     * Gets the size of the ship.
     * The size refers to the number of cells occupied by the ship on the game board.
     *
     * @return The size of the ship.
     */
    int getSize();

    /**
     * Renders the visual representation of the ship.
     * This method returns a Pane containing the graphical elements of the ship.
     *
     * @return A Pane containing the ship's visual representation.
     */
    Pane render();
    IShip clone();

    int getCurrentRotation();

    void setCurrentRotation(int currentRotation);
}
