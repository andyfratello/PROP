package main.domain.classes.functions;

import main.domain.classes.types.Binari;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class decimalABinari extends FuncioInt {
    /** Converteix el valor enter decimal entrat _value a un objecte Binari.
     * @param
     * @return Binari
     */
    public Binari decimalABinari() {
        return new Binari(_value);
    }
}