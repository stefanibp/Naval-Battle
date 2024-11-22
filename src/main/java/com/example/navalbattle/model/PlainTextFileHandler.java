package com.example.navalbattle.model;

import java.io.*;

/**
 * A file handler implementation for writing and reading plain text files.
 * This class provides methods to write a string to a file and read content from a file into an array of strings.
 *
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 */
public class PlainTextFileHandler implements IPlaneTextFileHandler {

    /**
     * Writes a string content to a plain text file.
     *
     * @param fileName The name of the file where the content will be written.
     * @param content  The content to write into the file.
     */
    @Override
    public void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the content of a plain text file and splits it into an array of strings.
     * Each line is trimmed and separated by a comma.
     *
     * @param fileName The name of the file to read.
     * @return An array of strings containing the trimmed content of the file.
     */
    @Override
    public String[] readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.trim()).append(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().split(",");
    }
}
