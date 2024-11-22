package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;

/**
 * Represents the action of sinking a ship in the Naval Battle game.
 * Implements the {@link IMove} interface to define the sinking behavior and render visual effects.
 *
 * The class provides a textual representation of the sinking event as well as a graphical effect
 * using a flame-like shape with a color gradient.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Sink implements IMove {

    /**
     * Executes the sinking action.
     * Prints a message indicating that the ship has been sunk and the player may shoot again.
     */
    @Override
    public void execute() {
        System.out.println("¡Hundido! El jugador puede volver a disparar.");
    }

    /**
     * Renders a graphical effect representing the sinking of a ship.
     * The effect is depicted as a flame-like shape with a gradient color transition.
     *
     * @return a {@link Pane} containing the flame graphical effect.
     */
    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();

        // Calculate a new scale to enlarge the figure slightly
        double scale = Math.min(48.0 / 40.0, 48.0 / 40.0); // Slightly larger scale

        // Create a color gradient for the flame
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1, true, null,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.ORANGE),
                new Stop(1, Color.YELLOW)
        );

        // Flame shape for the sinking effect: scaled and shifted left
        Polygon flame = new Polygon();
        flame.getPoints().addAll(
                (10.0 - 7.0) * scale, 0.0 * scale,  // Shifted left and enlarged
                (15.0 - 7.0) * scale, 10.0 * scale,
                (20.0 - 7.0) * scale, 5.0 * scale,
                (25.0 - 7.0) * scale, 15.0 * scale,
                (30.0 - 7.0) * scale, 5.0 * scale,
                (35.0 - 7.0) * scale, 10.0 * scale,
                (40.0 - 7.0) * scale, 0.0 * scale,
                (35.0 - 7.0) * scale, 20.0 * scale,
                (25.0 - 7.0) * scale, 30.0 * scale,
                (15.0 - 7.0) * scale, 20.0 * scale
        );
        flame.setFill(gradient);

        pane.getChildren().add(flame);
        return pane;
    }
}

