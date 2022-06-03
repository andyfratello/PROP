package main.domain.classes.exceptions;

/**
 * La classe NegativeRootException gestiona les excepcions generades al
 * intentar fer una arrel naria d'un nombre negatiu.
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class NegativeRootException extends Exception {
    private double _x;
    public NegativeRootException(double x) {
        super("MATH RUNTIME EXCEPTION! -> Tried to make root of a negative number (" + x + ")");
        _x = x;
    }

    public String toString() {
        return "MATH RUNTIME EXCEPTION! -> Tried to make root of a negative number (" + _x + ")";
    }
}
