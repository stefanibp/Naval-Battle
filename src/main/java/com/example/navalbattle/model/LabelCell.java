package com.example.navalbattle.model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class LabelCell extends Pane {
    public LabelCell(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-border-color: grey; -fx-alignment: center; -fx-font-weight: bold;");
        label.setPrefSize(40, 40);
        this.getChildren().add(label);
    }
}