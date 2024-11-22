package com.example.navalbattle.exceptions;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Custom exception class for handling out-of-bounds errors in the game.
 * This exception is thrown when a shot or action is attempted outside the valid game area.
 */
public class OutOfBoundsException extends RuntimeException {

    /**
     * Constructor for OutOfBoundsException with a custom message.
     *
     * @param message The detailed message explaining the cause of the exception.
     */
    public OutOfBoundsException(String message) {
        super(message); // Passes the message to the constructor of RuntimeException
    }
}

//(Excepción Marcada)
//Propósito: Asegurarse de que las coordenadas utilizadas (fila, columna) estén dentro de los límites del tablero.
//Cuándo se lanza:
//Si intentas colocar un barco o disparar en una celda que no existe.

//if (row < 0 || row >= 10 || col < 0 || col >= 10) {
//        throw new OutOfBoundsException("Coordenadas fuera del tablero.");
//}
