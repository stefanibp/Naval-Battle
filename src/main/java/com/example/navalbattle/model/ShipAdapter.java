package com.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ShipAdapter implements IShip {
    private IShip ship;  // Barco específico a adaptar.
    private int currentRotation = 0; // 90 - 270 = vertical ____  180 - 0 = horizontal

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
    public StackPane render() {
        Pane shipPane = ship.render();

        // Se pueden agregar funcionalidades o detalles adicionales para todos los barcos, ej:
        Rectangle border = new Rectangle(0, 0, 200, 200);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        shipPane.getChildren().add(border);

        return (StackPane) shipPane;
    }

    @Override
    public IShip clone() {
        // Clonación adaptada, si la clase Ship tiene el método `clone` puedes usarlo
        return new ShipAdapter(ship.clone()); // Retornar un nuevo adaptador para el barco clonado
    }


    @Override
    public int getCurrentRotation() {
        return currentRotation;
    }

    @Override
    public void setCurrentRotation(int currentRotation) {
        this.currentRotation = currentRotation;
    }
}
