package com.example.navalbattle.model;

import javafx.scene.layout.Pane;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Interface for defining a move in the game (hit, miss, or sink).
 * Provides methods to execute the move and render the visual effect of the move.
 */
public interface IMove {

    /**
     * Executes the move (whether it's a miss, hit, or sink).
     */
    void execute();

    /**
     * Renders the visual effect of the move (such as showing an "X" for a miss or a flame for a sink).
     *
     * @return A Pane containing the visual effect of the move.
     */
    Pane renderEffect();
}

