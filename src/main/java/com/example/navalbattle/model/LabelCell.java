package com.example.navalbattle.model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Represents a label cell in the game, which is used to display text in a cell.
 * This is used in various parts of the Naval Battle game to show information or status.
 *
 * The LabelCell contains a Label with specific styles for alignment, font, and border.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class LabelCell extends Pane {

    /**
     * Constructs a LabelCell with the specified text.
     * The text is displayed in a styled label with a fixed size and border.
     *
     * @param text The text to be displayed in the label.
     */
    public LabelCell(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-border-color: grey; -fx-alignment: center; -fx-font-weight: bold;");
        label.setPrefSize(40, 40);
        this.getChildren().add(label);
    }
}
