package com.example.navalbattle.model;

import javafx.scene.layout.Pane;

public class MoveAdapter implements IMove {
    private final IMove move;

    public MoveAdapter(IMove move) {
        this.move = move; // Envuelve el movimiento concreto
    }

    @Override
    public void execute() {
        move.execute(); // Delega la ejecución al movimiento concreto
    }

    @Override
    public Pane renderEffect() {
        Pane pane = move.renderEffect();
        // Añadir efectos adicionales aquí
        return pane;
    }
}

