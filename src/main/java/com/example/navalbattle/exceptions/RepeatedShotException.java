package com.example.navalbattle.exceptions;

/**
 * @author Jerson Alexis Ortiz Velasco
 * @author Jhon Antony Murillo Olave
 * @author Stefania Bolaños Perdomo
 * @version 1.0
 * @since 1.0
 *
 * Custom exception class for handling repeated shots in the game.
 * This exception is thrown when the player attempts to shoot in the same spot again.
 */
public class RepeatedShotException extends RuntimeException {

    /**
     * Default constructor for the RepeatedShotException.
     * Calls the constructor of the superclass RuntimeException.
     */
    public RepeatedShotException() {
        super(); // Calls the constructor of RuntimeException
    }

    /**
     * Constructor with a custom message.
     *
     * @param message The detailed message explaining the cause of the exception.
     */
    public RepeatedShotException(String message) {
        super(message); // Passes the message to the constructor of RuntimeException
    }

    /**
     * Constructor with a custom message and a cause.
     *
     * @param message The detailed message explaining the cause of the exception.
     * @param cause The cause of the exception (can be another throwable).
     */
    public RepeatedShotException(String message, Throwable cause) {
        super(message, cause); // Passes the message and cause to the constructor of RuntimeException
    }

    /**
     * Constructor with a cause.
     *
     * @param cause The cause of the exception (can be another throwable).
     */
    public RepeatedShotException(Throwable cause) {
        super(cause); // Passes the cause to the constructor of RuntimeException
    }
}

//(Excepción No Marcada, Personalizada)
//Propósito: Prevenir que un jugador (humano o máquina) dispare dos veces a la misma celda.
//Cuándo se lanza:
//Si el jugador intenta disparar a una celda ya atacada previamente.

//if (!isValidShot(row, col)) {
//        throw new RepeatedShotException("Ya disparaste a esta celda.");
//}

