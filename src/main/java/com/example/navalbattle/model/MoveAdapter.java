package com.example.navalbattle.model;

import javafx.scene.layout.Pane;

public class MoveAdapter implements IMove {
    private final IMove move;

    public MoveAdapter(IMove move) {
        this.move = move; // Envuelve el movimiento concreto
    }

    @Override
    public void execute() {
        move.execute(); // Delega la ejecución al movimiento concreto
    }

    @Override
    public Pane renderEffect() {
        Pane pane = move.renderEffect();
        // Añadir efectos adicionales aquí
        return pane;
    }
}


git init                       # Inicializa un repositorio local (si es necesario)
git add .                      # Agrega todos los archivos al área de preparación
git commit -m "Mensaje"        # Realiza un commit con un mensaje
git remote add origin <url>    # (Solo la primera vez) Añade la URL remota
git push -u origin main        # Envía tus cambios al repositorio remoto
