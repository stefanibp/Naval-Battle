package com.example.navalbattle.controller;

import com.example.navalbattle.model.Board;
import com.example.navalbattle.model.Game;
import com.example.navalbattle.view.EnemyStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * The controller class for managing the enemy's board in the naval battle game.
 * It handles the initialization of the enemy's board and the exit functionality.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class EnemyController {

    @FXML
    private AnchorPane enemyAnchorPane;

    @FXML
    private Board boardModel;

    private Game game;

    /**
     * Handles the action of clicking the exit button.
     * It deletes the current instance of the enemy stage.
     *
     * @param event the action event triggered by clicking the exit button
     */
    @FXML
    void handleClickExit(ActionEvent event) {
        EnemyStage.deleteInstance();
    }

    /**
     * Initializes the enemy controller, setting up the game instance and board model.
     * It calls the method to initialize the enemy board on the UI.
     */
    @FXML
    public void initialize() {
        game = WelcomeController.getInstance().getGame();
        boardModel = new Board();
        initializeBoard(enemyAnchorPane, "EnemyF");
    }

    /**
     * Initializes the enemy's board by creating the grid and adding it to the given anchor pane.
     *
     * @param anchorPane the anchor pane where the board will be added
     * @param boardName the name of the board to be initialized
     */
    private void initializeBoard(AnchorPane anchorPane, String boardName) {
        if (boardModel != null) {
            GridPane board = boardModel.createBoard(boardName); // Adjusted in `createBoard`
            anchorPane.getChildren().add(board);
        }
    }
}
