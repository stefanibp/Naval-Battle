package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Adapter class that allows extending or modifying the functionality of existing ships.
 * This class decorates a ship with additional visual details or features.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class ShipAdapter implements IShip {
    private IShip ship; // Specific ship to be adapted.

    /**
     * Constructor to initialize the adapter with a specific ship.
     *
     * @param ship The ship to be adapted.
     */
    public ShipAdapter(IShip ship) {
        this.ship = ship;
    }

    /**
     * Returns the name of the adapted ship.
     *
     * @return The name of the ship.
     */
    @Override
    public String getName() {
        return ship.getName();
    }

    /**
     * Returns the size of the adapted ship.
     *
     * @return The size of the ship in cells.
     */
    @Override
    public int getSize() {
        return ship.getSize();
    }

    /**
     * Renders the adapted ship with additional features, such as a decorative border.
     *
     * @return A Pane containing the adapted ship with additional visual elements.
     */
    @Override
    public Pane render() {
        Pane shipPane = ship.render();

        // Adding additional functionality or details to all ships, e.g.:
        Rectangle border = new Rectangle(0, 0, 200, 200);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        shipPane.getChildren().add(border);

        return shipPane;
    }
}
