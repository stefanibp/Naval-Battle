package com.example.navalbattle.model;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Interface for handling plain text file operations.
 * Provides methods for writing content to a file and reading content from a file.
 */
public interface IPlaneTextFileHandler {

    /**
     * Writes the specified content to a file with the given file name.
     *
     * @param fileName The name of the file to write to.
     * @param content The content to write to the file.
     */
    void writeToFile(String fileName, String content);

    /**
     * Reads content from a file with the specified file name.
     *
     * @param fileName The name of the file to read from.
     * @return An array of strings representing the content of the file, split by commas.
     */
    String[] readFromFile(String fileName);
}

