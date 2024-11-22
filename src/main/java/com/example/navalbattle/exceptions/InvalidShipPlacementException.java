package com.example.navalbattle.exceptions;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Custom exception class for handling invalid ship placement errors.
 * This exception is thrown when a ship placement is not valid according to the game rules.
 */
public class InvalidShipPlacementException extends Exception {

    /**
     * Constructor for InvalidShipPlacementException with a custom message.
     *
     * @param message The detailed message explaining the cause of the exception.
     */
    public InvalidShipPlacementException(String message) {
        super(message); // Passes the message to the constructor of Exception
    }
}

//(Excepción No Marcada)
//Propósito: Asegurarse de que los barcos se coloquen siguiendo las reglas del juego (sin superposiciones, dentro del tablero y en la orientación válida).
//Cuándo se lanza:
//Si intentas colocar un barco en una celda ocupada.
//Si el barco no cabe en el tablero debido a la orientación elegida.

//if (!isCellEmpty(row, col)) {
//        throw new InvalidShipPlacementException("Celda ocupada, no puedes colocar el barco aquí.");
//}


