package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents an adapter for ships in the Naval Battle game.
 * This class wraps an {@link IShip} instance and provides additional functionality
 * or modifications to the ship's behavior or rendering.
 *
 * The adapter allows customization and reuse of existing ship implementations
 * while maintaining compatibility with the {@link IShip} interface.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class ShipAdapter implements IShip {
    private IShip ship;  // Specific ship instance to adapt.
    private int currentRotation = 0; // Rotation: 90-270 = vertical, 180-0 = horizontal

    /**
     * Constructs a new ShipAdapter for the given ship.
     *
     * @param ship the ship to be adapted.
     */
    public ShipAdapter(IShip ship) {
        this.ship = ship;
    }

    /**
     * Gets the name of the adapted ship.
     *
     * @return the name of the ship.
     */
    @Override
    public String getName() {
        return ship.getName();
    }

    /**
     * Gets the size of the adapted ship in grid cells.
     *
     * @return the size of the ship.
     */
    @Override
    public int getSize() {
        return ship.getSize();
    }

    /**
     * Renders the visual representation of the adapted ship.
     * Adds a customizable border around the ship's rendering.
     *
     * @return a {@link StackPane} containing the ship's graphical representation.
     */
    @Override
    public StackPane render() {
        Pane shipPane = ship.render();

        // Add additional functionalities or details to all ships, e.g., a border
        Rectangle border = new Rectangle(0, 0, 200, 200);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        shipPane.getChildren().add(border);

        return (StackPane) shipPane;
    }

    /**
     * Clones the adapted ship.
     * Creates a new ShipAdapter instance for the cloned ship.
     *
     * @return a new {@link ShipAdapter} for the cloned ship.
     */
    @Override
    public IShip clone() {
        return new ShipAdapter(ship.clone()); // Return a new adapter for the cloned ship
    }

    /**
     * Gets the current rotation of the ship.
     *
     * @return the current rotation, where 90-270 represents vertical and 180-0 represents horizontal.
     */
    @Override
    public int getCurrentRotation() {
        return currentRotation;
    }

    /**
     * Sets the current rotation of the ship.
     *
     * @param currentRotation the rotation to set, where 90-270 represents vertical and 180-0 represents horizontal.
     */
    @Override
    public void setCurrentRotation(int currentRotation) {
        this.currentRotation = currentRotation;
    }
}
