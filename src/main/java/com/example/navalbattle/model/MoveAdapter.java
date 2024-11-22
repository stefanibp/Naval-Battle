package com.example.navalbattle.model;

import javafx.scene.layout.Pane;

/**
 * Adapter class for IMove interface. This class allows adding additional effects
 * or functionalities to an existing move without altering its implementation.
 *
 * It wraps a concrete move and delegates its execution and rendering while providing
 * a way to enhance or modify its behavior.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class MoveAdapter implements IMove {
    private final IMove move;

    /**
     * Constructor for the MoveAdapter class.
     *
     * @param move The concrete move to be adapted and extended.
     */
    public MoveAdapter(IMove move) {
        this.move = move; // Wraps the concrete move
    }

    /**
     * Executes the wrapped move's action.
     */
    @Override
    public void execute() {
        move.execute(); // Delegates execution to the concrete move
    }

    /**
     * Renders the visual effect of the wrapped move and provides an opportunity
     * to add additional effects.
     *
     * @return A Pane containing the rendered effect.
     */
    @Override
    public Pane renderEffect() {
        Pane pane = move.renderEffect();
        // Add additional effects here
        return pane;
    }
}
