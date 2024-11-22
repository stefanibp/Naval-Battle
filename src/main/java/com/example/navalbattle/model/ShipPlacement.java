package com.example.navalbattle.model;

import javafx.geometry.Point2D;
import java.util.List;

/**
 * Represents the placement of a ship in the Naval Battle game.
 * Stores the positions of the ship's cells on the grid and its rotation.
 *
 * This class is used to manage and retrieve information about the ship's
 * position and orientation during gameplay.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class ShipPlacement {
    private List<Point2D> positions; // List of positions occupied by the ship.
    private int rotation; // The rotation angle of the ship.

    /**
     * Constructs a ShipPlacement with specified positions and rotation.
     *
     * @param positions the list of {@link Point2D} representing the ship's grid positions.
     * @param rotation the rotation of the ship in degrees.
     */
    public ShipPlacement(List<Point2D> positions, int rotation) {
        this.positions = positions;
        this.rotation = rotation;
    }

    /**
     * Retrieves the positions occupied by the ship.
     *
     * @return a list of {@link Point2D} representing the ship's positions.
     */
    public List<Point2D> getPositions() {
        return positions;
    }

    /**
     * Retrieves the rotation of the ship.
     *
     * @return the rotation of the ship in degrees.
     */
    public int getRotation() {
        return rotation;
    }

    /**
     * Returns a string representation of the ship's placement.
     *
     * @return a formatted string displaying the positions and rotation of the ship.
     */
    @Override
    public String toString() {
        return "ShipPlacement{" +
                "positions=" + positions +
                ", rotation=" + rotation +
                '}';
    }
}
