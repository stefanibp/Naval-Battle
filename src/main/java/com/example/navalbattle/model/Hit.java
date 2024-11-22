package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 * Represents the action and visual effect of a successful hit in the Naval Battle game.
 * This class implements the {@link IMove} interface to define behavior and rendering
 * of the "Hit" event.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Hit implements IMove {

    /**
     * Executes the logic for a successful hit.
     * Prints a message indicating that the player may take another shot.
     */
    @Override
    public void execute() {
        System.out.println("¡Tocado! El jugador puede volver a disparar.");
    }

    /**
     * Renders the visual effect for a successful hit.
     * Displays a bomb with a fuse and a star-shaped explosion.
     *
     * @return A {@link Pane} containing the graphical elements of the hit effect.
     */
    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();
        pane.setPrefSize(40, 40); // Set the size of the pane

        // Bomb body
        Circle body = new Circle(20, 20, 10, Color.BLACK);

        // Bomb fuse
        Line fuse = new Line(20, 10, 20, 0);
        fuse.setStroke(Color.GRAY);
        fuse.setStrokeWidth(1);

        // Explosion in the shape of a star
        Polygon explosion = new Polygon();
        explosion.getPoints().addAll(
                20.0, 5.0,
                21.0, 8.0,
                24.0, 8.0,
                22.0, 10.0,
                24.0, 13.0,
                21.0, 13.0,
                20.0, 15.0,
                19.0, 13.0,
                16.0, 13.0,
                18.0, 10.0,
                16.0, 8.0,
                19.0, 8.0
        );
        explosion.setFill(Color.ORANGE);

        // Add all graphical elements to the pane
        pane.getChildren().addAll(body, fuse, explosion);
        return pane;
    }
}
