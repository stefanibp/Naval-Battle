package com.example.navalbattle.model;

import java.io.*;
import java.util.ArrayList;

public class SerializableFileHandlerPosition {

    public void serialize(String fileName, ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy, ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(fleetCoordinatesEnemy);  // Serializa las coordenadas de la flota enemiga
            oos.writeObject(fleetCoordinatesPlayer); // Serializa las coordenadas de la flota del jugador
            System.out.println("Posiciones de flotas serializadas correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize(String fileName, ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy, ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            fleetCoordinatesEnemy.clear();  // Limpiamos la lista antes de cargar nuevos datos
            fleetCoordinatesPlayer.clear();  // Limpiamos la lista antes de cargar nuevos datos
            fleetCoordinatesEnemy.addAll((ArrayList<ArrayList<Integer>>) ois.readObject()); // Deserializa las coordenadas de la flota enemiga
            fleetCoordinatesPlayer.addAll((ArrayList<ArrayList<Integer>>) ois.readObject()); // Deserializa las coordenadas de la flota del jugador
            System.out.println("Posiciones de flotas deserializadas correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
