package com.example.navalbattle.exceptions;

public class InvalidShipPlacementException extends Exception {
    public InvalidShipPlacementException(String message) {
        super(message);
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


