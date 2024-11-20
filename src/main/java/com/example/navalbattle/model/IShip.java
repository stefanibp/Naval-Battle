package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public interface IShip {
    String getName();
    int getSize();
    StackPane render();
    IShip clone();

    int getCurrentRotation();

    void setCurrentRotation(int currentRotation);
}
