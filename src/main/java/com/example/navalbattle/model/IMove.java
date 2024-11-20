package com.example.navalbattle.model;

import javafx.scene.layout.Pane;

public interface IMove {
    void execute(); // Ejecuta el movimiento (agua, tocado o hundido)
    Pane renderEffect(); // Renderiza el efecto visual del movimiento
}
