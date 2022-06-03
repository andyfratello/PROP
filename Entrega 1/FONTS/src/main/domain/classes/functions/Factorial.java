package main.domain.classes.functions;

import main.domain.classes.exceptions.NegativeFactorialException;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Factorial extends FuncioInt {
    private static int factorialR(int n) {
        if (n <= 2) {
            return n;
        }
        else {
            return (n * factorialR(n-1));
        }
    }

    /** Aquest metode retorna el factorial de _value
     * @param _value no pot ser negatiu, sino es generara una excepcio (NegativeFactorialException)
     * @return int
     */
    public int factorial() throws NegativeFactorialException {
        if (_value < 0) {
            throw new NegativeFactorialException(_value);
        }
        return factorialR(_value);
    }
}