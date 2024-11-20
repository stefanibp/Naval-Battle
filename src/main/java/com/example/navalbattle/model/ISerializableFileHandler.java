package com.example.navalbattle.model;

public interface ISerializableFileHandler {
    void serialize(String fileName, Game game);
    void deserialize(String fileName, Game game);
}
