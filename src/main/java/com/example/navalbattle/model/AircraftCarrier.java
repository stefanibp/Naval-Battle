package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Class representing an Aircraft Carrier ship. This class implements the IShip interface and is responsible for
 * providing the name, size, and visual representation of the Aircraft Carrier ship.
 */
public class AircraftCarrier implements IShip {

    /**
     * Returns the name of the ship.
     *
     * @return The name of the ship ("Aircraft Carrier").
     */
    @Override
    public String getName() {
        return "Aircraft Carrier";
    }

    /**
     * Returns the size of the ship.
     *
     * @return The size of the ship in cells (4).
     */
    @Override
    public int getSize() {
        return 4; // The size in cells
    }

    /**
     * Renders the visual representation of the Aircraft Carrier ship.
     *
     * @return A Pane containing the visual representation of the Aircraft Carrier ship.
     */
    @Override
    public Pane render() {
        Pane root = new Pane();

        // Create the back ellipse for the Aircraft Carrier
        Ellipse back = new Ellipse(63, 150, 10, 15);
        back.setFill(Color.DARKGRAY);
        root.getChildren().add(back);

        // Create the body of the ship as a rectangle
        Rectangle body = Shape.square(60, 135, 120, 30, Color.DARKGRAY);
        root.getChildren().add(body);

        // Create the front ellipse for the Aircraft Carrier
        Ellipse front = Shape.ellipseStyle(180, 150, 25, 15, Color.DARKGRAY);
        root.getChildren().add(front);

        // Create the runway line with a dashed pattern
        Line runway = new Line(70, 160, 150, 140);
        runway.setStroke(Color.WHITE);
        runway.setStrokeWidth(2);
        runway.getStrokeDashArray().addAll(6.0, 10.0);
        root.getChildren().add(runway);

        // Create the tower as a rectangle
        Rectangle tower = Shape.square(160, 145, 30, 10, Color.GRAY);
        root.getChildren().add(tower);

        // Create windows on the tower
        Rectangle window1 = Shape.square(175, 137, 3, 3, Color.LIGHTGRAY);
        Rectangle window2 = Shape.square(182, 137, 3, 3, Color.LIGHTGRAY);
        root.getChildren().addAll(window1, window2);

        // Create airplanes on the deck
        Polygon airplane1 = createAirplane(80, 140);
        Polygon airplane2 = createAirplane(130, 155);
        root.getChildren().addAll(airplane1, airplane2);

        return root;
    }

    /**
     * Creates an airplane shape for the Aircraft Carrier deck.
     *
     * @param x The x-coordinate for the airplane's position.
     * @param y The y-coordinate for the airplane's position.
     * @return A Polygon representing the airplane shape.
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
}
