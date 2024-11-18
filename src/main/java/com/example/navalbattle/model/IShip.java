package com.example.navalbattle.model;

import javafx.scene.layout.Pane;

public interface IShip {
    String getName();
    int getSize();
    Pane render();
}
