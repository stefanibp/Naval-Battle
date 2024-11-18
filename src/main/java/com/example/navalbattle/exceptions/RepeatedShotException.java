package com.example.navalbattle.exceptions;

// Excepción personalizada (no marcada)
public class RepeatedShotException extends RuntimeException {

    // Constructor por defecto
    public RepeatedShotException() {
        super(); // Llama al constructor de la clase RuntimeException
    }

    // Constructor con mensaje personalizado
    public RepeatedShotException(String message) {
        super(message); // Pasa el mensaje al constructor de la clase RuntimeException
    }

    // Constructor con mensaje personalizado y causa
    public RepeatedShotException(String message, Throwable cause) {
        super(message, cause); // Pasa el mensaje y la causa al constructor de la clase RuntimeException
    }

    // Constructor con causa
    public RepeatedShotException(Throwable cause) {
        super(cause); // Pasa la causa al constructor de la clase RuntimeException
    }
}


//(Excepción No Marcada, Personalizada)
//Propósito: Prevenir que un jugador (humano o máquina) dispare dos veces a la misma celda.
//Cuándo se lanza:
//Si el jugador intenta disparar a una celda ya atacada previamente.

//if (!isValidShot(row, col)) {
//        throw new RepeatedShotException("Ya disparaste a esta celda.");
//}

