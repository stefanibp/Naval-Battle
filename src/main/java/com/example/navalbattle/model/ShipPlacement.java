package com.example.navalbattle.model;

import javafx.geometry.Point2D;

import java.util.List;

public class ShipPlacement {
    private List<Point2D> positions;
    private int rotation;

    public ShipPlacement(List<Point2D> positions, int rotation) {
        this.positions = positions;
        this.rotation = rotation;
    }

    public List<Point2D> getPositions() {
        return positions;
    }

    public int getRotation() {
        return rotation;
    }

    @Override
    public String toString() {
        return "ShipPlacement{" +
                "positions=" + positions +
                ", rotation=" + rotation +
                '}';
    }
}