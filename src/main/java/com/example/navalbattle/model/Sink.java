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
 * @author Jhon Antony Murillo
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
     * Renders a visual effect representing a sinking ship.
     * The effect is depicted as a flame with a gradient transitioning from red to yellow.
     *
     * @return a {@link Pane} containing the graphical elements of the sinking effect.
     */
    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();

        // Create a gradient for the flame effect
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1, true, null,
                new Stop(0, Color.RED),
                new Stop(0.5, Color.ORANGE),
                new Stop(1, Color.YELLOW)
        );

        // Define the flame shape
        Polygon flame = new Polygon();
        flame.getPoints().addAll(
                10.0, 0.0,
                15.0, 10.0,
                20.0, 5.0,
                25.0, 15.0,
                30.0, 5.0,
                35.0, 10.0,
                40.0, 0.0,
                35.0, 20.0,
                25.0, 30.0,
                15.0, 20.0
        );
        flame.setFill(gradient);

        pane.getChildren().add(flame);
        return pane;
    }
}


