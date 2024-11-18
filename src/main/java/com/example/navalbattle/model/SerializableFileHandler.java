package com.example.navalbattle.model;

import java.io.*;
import java.util.ArrayList;

public class SerializableFileHandler implements ISerializableFileHandler {

    @Override
    public void serialize(String fileName, Game game) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(game.getPlayerBoard());  // Solo serializa el tablero del jugador
            oos.writeObject(game.getEnemyBoard());   // Solo serializa el tablero del enemigo
            System.out.println("Tableros serializados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deserialize(String fileName, Game game) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            game.setPlayerBoard((ArrayList<ArrayList<Integer>>) ois.readObject());
            game.setEnemyBoard((ArrayList<ArrayList<Integer>>) ois.readObject());
            System.out.println("Tableros deserializados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
