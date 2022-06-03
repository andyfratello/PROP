package main.domain.classes.functions;

import java.time.LocalDate;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class obteAny extends FuncioDate {
    /** Aquest metode retorna l'any de la data d
     * @param
     * @return int
     */
    public int obteAny() {
        return _value.getYear();
    }
}