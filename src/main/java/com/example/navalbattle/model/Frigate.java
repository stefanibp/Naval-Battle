package com.example.navalbattle.model;

import com.example.navalbattle.view.Shape;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Class representing a Frigate ship. This class implements the IShip interface and is responsible for
 * providing the name, size, and visual representation of the Frigate ship.
 */
public class Frigate implements IShip {

    /**
     * Returns the name of the ship.
     *
     * @return The name of the ship ("Frigate").
     */
    @Override
    public String getName() {
        return "Frigate";
    }

    /**
     * Returns the size of the ship.
     *
     * @return The size of the ship in cells (1).
     */
    @Override
    public int getSize() {
        return 1; // The size in cells
    }

    /**
     * Renders the visual representation of the Frigate ship.
     *
     * @return A Pane containing the visual representation of the Frigate ship.
     */
    @Override
    public Pane render() {
        Pane root = new Pane();
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                50.0, 50.0,
                75.0, 50.0,
                85.0, 37.5,
                75.0, 25.0,
                50.0, 25.0
        );

        polygon.setFill(Color.DARKGRAY);
        polygon.setStroke(Color.DARKGRAY);
        root.getChildren().addAll(polygon);

        // Adding lines to create ship details
        Line line1 = Shape.lineStyle(50, 50, 50, 25, Color.DARKGRAY, 2.0);
        Line line2 = Shape.lineStyle(60, 42, 74, 42, Color.GREY, 4.0);
        Line line3 = Shape.lineStyle(54, 45, 54, 30, Color.GREY, 2.0);
        Line line4 = Shape.lineStyle(60, 33, 74, 33, Color.GREY, 4.0);
        Line line5 = Shape.lineStyle(49, 41, 48, 41, Color.DARKGRAY, 4.0);
        Line line6 = Shape.lineStyle(49, 34, 48, 34, Color.DARKGRAY, 4.0);

        root.getChildren().addAll(line1, line2, line3, line4, line5, line6);
        return root;
    }
}
