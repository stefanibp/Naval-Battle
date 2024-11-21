package com.example.navalbattle.model;

import com.example.navalbattle.controller.FleetController;

import java.util.ArrayList;

public interface ISerializableFileHandlerPosition {
    void serialize(String fileName, ArrayList<ArrayList<Integer>> fleetCoordinatesEnemy, ArrayList<ArrayList<Integer>> fleetCoordinatesPlayer);
    void deserialize(String fileName, FleetController controller);
}