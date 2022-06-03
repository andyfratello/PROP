package main.domain.classes.functions;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Potencia extends Funcio2Doubles {
    /** Aquest metode retorna el valor de la potencia de _n^_pow
     * @param
     * @return double
     */
    public double potencia() {
        return Math.pow(_x, _y);
    }
}