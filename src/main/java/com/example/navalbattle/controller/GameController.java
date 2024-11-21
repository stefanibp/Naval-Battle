package com.example.navalbattle.controller;



import com.example.navalbattle.model.Game;
import com.example.navalbattle.model.IGame;
import com.example.navalbattle.model.SerializableFileHandler;

import com.example.navalbattle.view.*;
import com.example.navalbattle.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class GameController {


    @FXML
    private AnchorPane enemyAnchorPane;

    @FXML
    private AnchorPane playerAnchorPane;

    @FXML
    private Pane idSunken;

    @FXML
    private Pane idTouched;


    @FXML
    private Pane idwater;

    private Game game;

    private Board boardModel;

    GridPane board;



    private void initializeBoard(AnchorPane anchorPane, String boardName) {
        if (boardModel != null) {
            GridPane board = boardModel.createBoard(boardName); // Ajuste en `createBoard`
            anchorPane.getChildren().add(board);
        }
    }


    @FXML
    private Label idUser;
    private static String staticUserName; // Variable estática para almacenar temporalmente el nombre de usuario
    private String userName; // Variable local en la instancia del controlador

    public static void setStaticUserName(String userName) {
        staticUserName = userName; // Método para configurar la variable estática
    }
  
    public GameController() {
        game = Game.getInstance();
        game.initializeBoardList();
        this.userName = staticUserName;
    }

    @FXML
    void buttonViewEnemy(ActionEvent event) {
      EnemyStage.getInstance();
    }


    private void saveGameBoards(Game game) {
        SerializableFileHandler fileHandler = new SerializableFileHandler();
        String fileName = "game_boards.dat";
        fileHandler.serialize(fileName, game);  // Serializa el objeto completo
    }

    @FXML
    void handleClickExit(ActionEvent event) {
        // Guarda el juego cuando el jugador salga
        saveGameBoards(game);
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
        EnemyStage.deleteInstance();
    }

   //git  private Game game;

    @FXML
    public void initialize() {
//<<<<<<< sbp
      /*
        boardModel = new Board();
        controller = this; // Almacena la instancia del controlador
        initializeBoard(playerAnchorPane);
        initializeBoard(enemyAnchorPane);
        Board.getInstance().mapShipsToAnchorPane( playerAnchorPane, true);
        displayUserName(userName);
    }
  
    private static GameController controller;
  */
//=======
        game = WelcomeController.getInstance().getGame();

        game.printBoard();  // Puedes llamar al método para verificar si se cargó correctamente
        boardModel = new Board(game); // Ahora que 'game' está inicializado, pasamos 'game' a Board
        initializeBoard(playerAnchorPane, "Player");
        initializeBoard(enemyAnchorPane, "Enemy");
        loadFigures();
        loadUserNameFromFile();
    }

    private static GameController controller;

//>>>>>>> team
    public static GameController getController() {
        return controller; // Devuelve la referencia del controlador
    }
  
    private void loadUserNameFromFile() {
        PlainTextFileHandler fileHandler = new PlainTextFileHandler();
        String[] content = fileHandler.readFromFile("usuario.txt");

        // Si hay contenido en el archivo, asignar el primer valor a la variable userName
        if (content.length > 0) {
            userName = content[0];  // Asumimos que el nombre de usuario es el primer dato guardado
            System.out.println("Usuario cargado: " + userName);  // Imprimir el nombre de usuario cargado
        } else {
            System.out.println("No se encontró un nombre de usuario en el archivo.");
        }
      displayUserName(userName);
    }
  
    public void displayUserName(String user) {
        if (user != null && !user.isEmpty()) {
            // Convertir a mayúsculas
            String userUpperCase = user.toUpperCase();

            // Configurar texto con gradiente y estilo marino oscuro
            idUser.setText(userUpperCase + " ¡Fuego a discreción!");
            idUser.setStyle(
                    "-fx-font-size: 55px; " +
                            "-fx-font-family: 'Seaweed Script'; " + // Fuente estilo marina, puedes cambiarla si prefieres
                            "-fx-text-fill: linear-gradient(from 0% 0% to 100% 100%, #001a33, #005266); " + // Gradiente azul oscuro
                            "-fx-font-weight: bold;"
            );

            // Efecto de resplandor marino oscuro
            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetX(0);
            dropShadow.setOffsetY(0);
            dropShadow.setRadius(15); // Tamaño del resplandor
            dropShadow.setColor(Color.web("#003d66")); // Azul oscuro brillante

            // Aplicar el efecto al texto
            idUser.setEffect(dropShadow);

        }
    }

    @FXML
    public void loadFigures() {
        // Crear las instancias de las figuras
        Sink sink = new Sink();
        Miss miss = new Miss();
        Hit hit = new Hit();

        // Cargar los efectos en los panes correspondientes
        idSunken.getChildren().clear();  // Limpiar cualquier contenido previo
        idTouched.getChildren().clear();
        idwater.getChildren().clear();

        // Renderizar y agregar las figuras a los panes
        idSunken.getChildren().add(hit.renderEffect());
        idTouched.getChildren().add(sink.renderEffect());
        idwater.getChildren().add(miss.renderEffect());
    }


}

