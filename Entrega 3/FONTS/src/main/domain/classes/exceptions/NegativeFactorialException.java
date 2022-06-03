package main.domain.classes.exceptions;

import java.io.Serializable;

/**
 * La classe NegativeFactorialException gestiona les excepcions generades al
 * intentar fer una factorial d'un nombre negatiu.
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class NegativeFactorialException extends RuntimeException implements Serializable {
    private double _x;
    public NegativeFactorialException(double x) {
        super("MATH RUNTIME EXCEPTION! -> Tried to make factorial of a negative number (" + x + ")");
        _x = x;
    }

    public String toString() {
        return "MATH RUNTIME EXCEPTION! -> Tried to make factorial of a negative number (" + _x + ")";
    }
}
