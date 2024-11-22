package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 * Represents a Frigate ship in the Naval Battle game.
 * Implements the {@link IShip} interface to define the ship's properties,
 * rendering, and rotation logic.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Frigate implements IShip {

    private int currentRotation = 0;

    /**
     * Gets the name of the ship.
     *
     * @return The name of the ship, in this case, "Frigate".
     */
    @Override
    public String getName() {
        return "Frigate";
    }

    /**
     * Gets the size of the ship in terms of grid cells.
     *
     * @return The size of the Frigate, which is 1 cell.
     */
    @Override
    public int getSize() {
        return 1; // The size in grid cells
    }

    /**
     * Renders the Frigate ship as a graphical shape.
     *
     * @return A {@link Pane} containing the graphical representation of the Frigate.
     */
    @Override
    public Pane render() {
        // Create a Pane for the Frigate's graphical representation
        Pane root = new Pane();
        root.setPrefSize(40, 40); // Set the pane size to 40x40px

        // Create the polygon shape for the Frigate
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                10.0, 10.0,   // Point 1
                30.0, 10.0,   // Point 2
                35.0, 20.0,   // Point 3
                30.0, 30.0,   // Point 4
                10.0, 30.0    // Point 5
        );

        polygon.setFill(Color.DARKGRAY);  // Set the fill color to dark gray
        polygon.setStroke(Color.DARKGRAY); // Set the stroke color to dark gray
        root.getChildren().add(polygon);

        // Draw additional details (lines) on the Frigate
        Line line1 = Shape.lineStyle(10, 10, 10, 30, Color.DARKGRAY, 2.0);
        Line line2 = Shape.lineStyle(12, 17, 28, 17, Color.GREY, 4.0);
        Line line3 = Shape.lineStyle(13, 20, 13, 28, Color.GREY, 2.0);
        Line line4 = Shape.lineStyle(12, 25, 28, 25, Color.GREY, 4.0);
        Line line5 = Shape.lineStyle(9, 22, 8, 22, Color.DARKGRAY, 4.0);
        Line line6 = Shape.lineStyle(9, 19, 8, 19, Color.DARKGRAY, 4.0);

        root.getChildren().addAll(line1, line2, line3, line4, line5, line6);

        return root;
    }

    /**
     * Creates a copy of the current Frigate object.
     *
     * @return A new instance of the Frigate ship.
     */
    @Override
    public IShip clone() {
        return new Frigate();  // Return a new Frigate instance
    }

    /**
     * Gets the current rotation of the Frigate.
     *
     * @return The current rotation in degrees.
     */
    @Override
    public int getCurrentRotation() {
        return currentRotation;
    }

    /**
     * Sets the current rotation of the Frigate.
     *
     * @param currentRotation The new rotation value in degrees.
     */
    @Override
    public void setCurrentRotation(int currentRotation) {
        this.currentRotation = currentRotation;
    }
}
