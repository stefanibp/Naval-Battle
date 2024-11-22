package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Represents a "Miss" action in the Naval Battle game.
 * Implements the {@link IMove} interface to define the behavior and visual effect
 * for when a shot does not hit any target.
 *
 * The class provides a textual notification and renders a graphical "X" symbol
 * to indicate the miss.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Miss implements IMove {

    /**
     * Executes the "Miss" action.
     * Prints a message indicating that the shot missed and the turn passes to the opponent.
     */
    @Override
    public void execute() {
        System.out.println("¡Agua! Pasa el turno al oponente.");
    }

    /**
     * Renders the visual effect for a "Miss."
     * Displays a red "X" symbol, slightly shifted to the left and upwards for alignment.
     *
     * @return a {@link Pane} containing the "X" graphical effect.
     */
    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();

        // Create the first line of the "X"
        Line line1 = new Line(10 - 5, 10 - 5, 40 - 5, 40 - 5); // Shift 5 pixels left and up
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(2);

        // Create the second line of the "X"
        Line line2 = new Line(40 - 5, 10 - 5, 10 - 5, 40 - 5); // Shift 5 pixels left and up
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(2);

        pane.getChildren().addAll(line1, line2);
        return pane;
    }
}
