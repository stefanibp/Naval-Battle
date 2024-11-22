package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Class representing a Destroyer ship. This class implements the IShip interface and is responsible for
 * providing the name, size, and visual representation of the Destroyer ship.
 */
public class Destroyer implements IShip {

    /**
     * Returns the name of the ship.
     *
     * @return The name of the ship ("Destroyer").
     */
    @Override
    public String getName() {
        return "Destroyer";
    }

    /**
     * Returns the size of the ship.
     *
     * @return The size of the ship in cells (2).
     */
    @Override
    public int getSize() {
        return 2; // The size in cells
    }

    /**
     * Renders the visual representation of the Destroyer ship.
     *
     * @return A Pane containing the visual representation of the Destroyer ship.
     */
    @Override
    public Pane render() {
        Pane root = new Pane();

        // Create the first triangle for the Destroyer
        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                100.0, 40.0,
                83.0, 25.0,
                83.0, 55.0);
        triangle1.setFill(Color.DARKGRAY);
        root.getChildren().addAll(triangle1);

        // Create the body of the ship as a rounded rectangle
        Rectangle body = Shape.square(20, 25, 70, 30, Color.DARKGRAY);
        body.setArcWidth(20);
        body.setArcHeight(40);
        root.getChildren().add(body);

        // Create four small squares for the details of the ship
        double squareSize = 10;
        Rectangle square1 = Shape.squareStyle(35, 28, squareSize, squareSize, Color.GREY);
        Rectangle square2 = Shape.squareStyle(48, 28, squareSize, squareSize, Color.GREY);
        Rectangle square3 = Shape.squareStyle(35, 42, squareSize, squareSize, Color.GREY);
        Rectangle square4 = Shape.squareStyle(48, 42, squareSize, squareSize, Color.GREY);
        root.getChildren().addAll(square1, square2, square3, square4);

        // Create a hexagon for the ship's details
        double deltaX = 20;
        double deltaY = 22;
        Polygon hexagon = new Polygon(
                50.0 + deltaX, 5.0 + deltaY,
                60.0 + deltaX, 10.0 + deltaY,
                60.0 + deltaX, 25.0 + deltaY,
                50.0 + deltaX, 30.0 + deltaY,
                40.0 + deltaX, 25.0 + deltaY,
                40.0 + deltaX, 10.0 + deltaY
        );
        hexagon.setFill(Color.GREY);
        hexagon.setStrokeWidth(0);
        root.getChildren().add(hexagon);

        // Create two ellipses as small details
        Ellipse e1 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 30, 45);
        Ellipse e2 = Shape.circleStyle(3, 3, Color.GREY, Color.BLACK, 1.0, 30, 35);
        root.getChildren().addAll(e1, e2);

        return root;
    }
}


