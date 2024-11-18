package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ShipAdapter implements IShip {
    private IShip ship;  // Barco específico a adaptar.

    public ShipAdapter(IShip ship) {
        this.ship = ship;
    }

    @Override
    public String getName() {
        return ship.getName();
    }

    @Override
    public int getSize() {
        return ship.getSize();
    }

    @Override
    public Pane render() {
        Pane shipPane = ship.render();

        // Se pueden agregar funcionalidades o detalles adicionales para todos los barcos, ej:
        Rectangle border = new Rectangle(0, 0, 200, 200);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        shipPane.getChildren().add(border);

        return shipPane;
    }
}
