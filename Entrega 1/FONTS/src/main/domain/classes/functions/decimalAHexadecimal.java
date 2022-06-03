package main.domain.classes.functions;

import main.domain.classes.types.Hexadecimal;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class decimalAHexadecimal extends FuncioInt {
    /** Converteix el valor enter decimal entrat _value a un objecte Hexadecimal i el retorna.
     * @param
     * @return Hexadecimal
     */
    public Hexadecimal decimalAHexadecimal() {
        return new Hexadecimal(_value);
    }
}