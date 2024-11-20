package com.example.navalbattle.exceptions;

public class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException(String message) {
        super(message);
    }
}

//(Excepción Marcada)
//Propósito: Asegurarse de que las coordenadas utilizadas (fila, columna) estén dentro de los límites del tablero.
//Cuándo se lanza:
//Si intentas colocar un barco o disparar en una celda que no existe.

//if (row < 0 || row >= 10 || col < 0 || col >= 10) {
//        throw new OutOfBoundsException("Coordenadas fuera del tablero.");
//}
