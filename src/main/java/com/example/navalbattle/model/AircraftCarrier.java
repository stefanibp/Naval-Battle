package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

/**
 * Represents an Aircraft Carrier ship in the Naval Battle game.
 * Implements the {@link IShip} interface to define the ship's properties,
 * rendering, and rotation logic.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class AircraftCarrier implements IShip {

    private int currentRotation = 0;
    private int rotationAngle = 0;  // Rotation angle (0, 90, 180, 270)

    /**
     * Gets the name of the ship.
     *
     * @return The name of the ship, in this case, "Aircraft Carrier".
     */
    @Override
    public String getName() {
        return "Aircraft Carrier";
    }

    /**
     * Gets the size of the ship in terms of grid cells.
     *
     * @return The size of the Aircraft Carrier, which is 4 cells.
     */
    @Override
    public int getSize() {
        return 4; // The size in grid cells
    }

    /**
     * Renders the Aircraft Carrier ship as a graphical shape.
     *
     * @return A {@link Pane} containing the graphical representation of the Aircraft Carrier.
     */
    @Override
    public Pane render() {
        // Create a pane with dimensions of 160px in length and 40px in width
        Pane root = new Pane();
        root.setPrefSize(160, 40); // Set the pane size

        // Adjust coordinates to center the aircraft carrier
        double startX = (root.getPrefWidth() - 160) / 2;  // X-coordinate for center alignment
        double startY = (root.getPrefHeight() - 40) / 2;  // Y-coordinate for center alignment

        // Draw parts of the aircraft carrier, now adjusted to remove empty spaces
        Ellipse back = new Ellipse(startX + 10, startY + 20, 10, 15);  // Adjusted position
        back.setFill(Color.DARKGRAY);
        root.getChildren().add(back);

        Rectangle body = Shape.square(startX + 0, startY + 5, 120, 30, Color.DARKGRAY);  // Adjusted position
        root.getChildren().add(body);

        Ellipse front = Shape.ellipseStyle(startX + 120, startY + 20, 25, 15, Color.DARKGRAY);  // Adjusted position
        root.getChildren().add(front);

        Line runway = new Line(startX + 15, startY + 30, startX + 135, startY + 5);  // Adjusted position
        runway.setStroke(Color.WHITE);
        runway.setStrokeWidth(2);
        runway.getStrokeDashArray().addAll(6.0, 10.0);
        root.getChildren().add(runway);

        Rectangle tower = Shape.square(startX + 120, startY + 10, 30, 10, Color.GRAY);  // Adjusted position
        root.getChildren().add(tower);

        // Create and add windows to the tower
        Rectangle window1 = Shape.square(startX + 135, startY + 2, 3, 3, Color.LIGHTGRAY);
        Rectangle window2 = Shape.square(startX + 142, startY + 2, 3, 3, Color.LIGHTGRAY);
        root.getChildren().addAll(window1, window2);

        // Create and add airplane shapes on the aircraft carrier
        Polygon airplane1 = createAirplane(startX + 25, startY + 10);  // Adjusted position
        Polygon airplane2 = createAirplane(startX + 90, startY + 20);  // Adjusted position
        root.getChildren().addAll(airplane1, airplane2);

        // Apply rotation to the pane
        Rotate rotate = new Rotate();
        rotate.setAngle(rotationAngle);  // Set the rotation angle
        rotate.setPivotX(startX + 80);   // Set the rotation pivot X-coordinate
        rotate.setPivotY(startY + 20);   // Set the rotation pivot Y-coordinate
        root.getTransforms().add(rotate);

        return root;
    }

    /**
     * Creates a simple airplane shape for the aircraft carrier.
     *
     * @param x The X-coordinate of the airplane's position.
     * @param y The Y-coordinate of the airplane's position.
     * @return A {@link Polygon} representing the airplane shape.
     */
    private Polygon createAirplane(double x, double y) {
        Polygon airplane = new Polygon(
                x, y,
                x - 5, y + 10,
                x + 5, y + 10
        );
        airplane.setFill(Color.LIGHTGRAY);
        return airplane;
    }

    /**
     * Creates a copy of the current AircraftCarrier object.
     *
     * @return A new instance of the AircraftCarrier ship.
     */
    @Override
    public IShip clone() {
        return new AircraftCarrier();
    }

    /**
     * Gets the current rotation of the Aircraft Carrier.
     *
     * @return The current rotation angle in degrees.
     */
    @Override
    public int getCurrentRotation() {
        return currentRotation;
    }

    /**
     * Sets the current rotation of the Aircraft Carrier.
     *
     * @param currentRotation The new rotation value in degrees.
     */
    @Override
    public void setCurrentRotation(int currentRotation) {
        this.currentRotation = currentRotation;
    }
}