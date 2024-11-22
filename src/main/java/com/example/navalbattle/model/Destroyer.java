package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

/**
 * Represents a Destroyer ship in the Naval Battle game.
 * Implements the {@link IShip} interface to define the ship's properties,
 * rendering, and rotation logic.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Destroyer implements IShip {

    private int currentRotation = 0;

    /**
     * Gets the name of the ship.
     *
     * @return The name of the ship, in this case, "Destroyer".
     */
    @Override
    public String getName() {
        return "Destroyer";
    }

    /**
     * Gets the size of the ship in terms of grid cells.
     *
     * @return The size of the Destroyer, which is 2 cells.
     */
    @Override
    public int getSize() {
        return 2; // The size in grid cells
    }

    /**
     * Renders the Destroyer ship as a graphical shape.
     *
     * @return A {@link Pane} containing the graphical representation of the Destroyer.
     */
    @Override
    public Pane render() {
        Pane root = new Pane();
        root.setPrefSize(80, 40); // Set the pane size to 80x40px

        // Create and add the first triangle part of the ship
        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                60.0, 20.0,
                43.0, 5.0,
                43.0, 35.0
        );
        triangle1.setFill(Color.DARKGRAY);
        root.getChildren().add(triangle1);

        // Create and add the body of the ship
        Rectangle body = Shape.square(10, 5, 60, 30, Color.DARKGRAY);
        body.setArcWidth(20);
        body.setArcHeight(40);
        root.getChildren().add(body);

        // Create and add squares to the body of the ship
        double squareSize = 10;
        Rectangle square1 = Shape.squareStyle(25, 8, squareSize, squareSize, Color.GREY);
        Rectangle square2 = Shape.squareStyle(38, 8, squareSize, squareSize, Color.GREY);
        Rectangle square3 = Shape.squareStyle(25, 22, squareSize, squareSize, Color.GREY);
        Rectangle square4 = Shape.squareStyle(38, 22, squareSize, squareSize, Color.GREY);
        root.getChildren().addAll(square1, square2, square3, square4);

        // Create and add hexagonal part of the ship
        double deltaX = 10;
        double deltaY = 2;
        Polygon hexagon = new Polygon(
                40.0 + deltaX, 5.0 + deltaY,
                50.0 + deltaX, 10.0 + deltaY,
                50.0 + deltaX, 25.0 + deltaY,
                40.0 + deltaX, 30.0 + deltaY,
                30.0 + deltaX, 25.0 + deltaY,
                30.0 + deltaX, 10.0 + deltaY
        );
        hexagon.setFill(Color.GREY);
        hexagon.setStrokeWidth(0);
        root.getChildren().add(hexagon);

        // Create and add ellipses to the ship
        Ellipse e1 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 20, 25);
        Ellipse e2 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 20, 15);
        root.getChildren().addAll(e1, e2);

        return root;
    }

    /**
     * Creates a copy of the current Destroyer object.
     *
     * @return A new instance of the Destroyer ship.
     */
    @Override
    public IShip clone() {
        return new Destroyer();  // Return a new Destroyer instance
    }

    /**
     * Gets the current rotation of the Destroyer.
     *
     * @return The current rotation in degrees.
     */
    @Override
    public int getCurrentRotation() {
        return currentRotation;
    }

    /**
     * Sets the current rotation of the Destroyer.
     *
     * @param currentRotation The new rotation value in degrees.
     */
    @Override
    public void setCurrentRotation(int currentRotation) {
        this.currentRotation = currentRotation;
    }
}