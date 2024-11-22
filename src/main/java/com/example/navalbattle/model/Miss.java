package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Represents the "Miss" move in the Naval Battle game.
 * This move occurs when a shot does not hit any ship, and the turn passes to the opponent.
 *
 * It includes functionality for executing the move logic and rendering its visual effect.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class Miss implements IMove {

    /**
     * Executes the logic for the "Miss" move.
     * Indicates that the shot missed and passes the turn to the opponent.
     */
    @Override
    public void execute() {
        System.out.println("¡Agua! Pasa el turno al oponente.");
    }

    /**
     * Renders the visual effect for the "Miss" move.
     * This is represented by a red "X" drawn on the screen.
     *
     * @return A Pane containing the visual representation of a "Miss."
     */
    @Override
    public Pane renderEffect() {
        Pane pane = new Pane();

        // Visual effect with a red "X"
        Line line1 = new Line(10, 10, 40, 40);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(2);

        Line line2 = new Line(40, 10, 10, 40);
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(2);

        pane.getChildren().addAll(line1, line2);
        return pane;
    }
}

