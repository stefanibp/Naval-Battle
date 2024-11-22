package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

/**
 * Represents a submarine ship in the Naval Battle game.
 * Implements the {@link IShip} interface to define the submarine's properties and rendering logic.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Submarine implements IShip {
    private int currentRotation = 0;

    /**
     * Returns the name of the ship.
     *
     * @return the name of the submarine, "Submarine".
     */
    @Override
    public String getName() {
        return "Submarine";
    }

    /**
     * Returns the size of the submarine in terms of grid cells.
     *
     * @return the size of the submarine, which is 3 cells.
     */
    @Override
    public int getSize() {
        return 3; // The size in grid cells
    }

    /**
     * Renders a visual representation of the submarine.
     * Creates and arranges various shapes such as rectangles, ellipses, and polygons to form the submarine.
     *
     * @return a {@link Pane} containing the graphical elements of the submarine.
     */
    @Override
    public Pane render() {
        // Creates a Pane with dimensions of 120px in length and 40px in width
        Pane root = new Pane();
        root.setPrefSize(120, 40); // Set Pane dimensions

        // Adjust starting coordinates to occupy the entire space
        double startX = 0;  // Starting X coordinate
        double startY = 0;  // Starting Y coordinate

        // Draw the submarine's body with adjusted coordinates
        Rectangle body = Shape.square(startX + 15, startY + 10, 90, 20, Color.DARKGRAY);

        body.setArcWidth(40);
        body.setArcHeight(40);
        root.getChildren().add(body);

        // Draw the submarine's front triangle
        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                startX + 20.0, startY + 15.0,
                startX + 5.0, startY + 20.0,
                startX + 20.0, startY + 25.0);
        triangle1.setFill(Color.DARKGRAY);
        root.getChildren().addAll(triangle1);

        // Draw the rear propeller
        Ellipse propeller1 = new Ellipse(startX + 13, startY + 20, 3, 8);
        propeller1.setFill(Color.DARKGRAY);
        root.getChildren().addAll(propeller1);

        // Draw the hatches
        Ellipse hatch1 = Shape.ellipseStyle(startX + 30, startY + 20, 3, 3, Color.WHITE);
        Ellipse hatch2 = Shape.ellipseStyle(startX + 50, startY + 20, 3, 3, Color.WHITE);
        Ellipse hatch3 = Shape.ellipseStyle(startX + 85, startY + 20, 3, 3, Color.WHITE);
        root.getChildren().addAll(hatch1, hatch2, hatch3);

        // Draw the rear fins
        Line fins1 = Shape.lineStyle(startX + 85, startY + 8, startX + 85, startY + 10, Color.DARKGRAY, 4.0);
        Line fins2 = Shape.lineStyle(startX + 85, startY + 32, startX + 85, startY + 30, Color.DARKGRAY, 4.0);
        root.getChildren().addAll(fins1, fins2);

        return root;
    }

    /**
     * Creates and returns a clone of this submarine.
     *
     * @return a new {@link Submarine} instance.
     */
    @Override
    public IShip clone() {
        return new Submarine();
    }

    /**
     * Retrieves the current rotation of the submarine.
     *
     * @return the current rotation value.
     */
    @Override
    public int getCurrentRotation() {
        return currentRotation;
    }


    /**
     * Sets the current rotation of the submarine.
     *
     * @param currentRotation the rotation value to set.
     */
    @Override
    public void setCurrentRotation(int currentRotation) {
        this.currentRotation = currentRotation;
    }
}
