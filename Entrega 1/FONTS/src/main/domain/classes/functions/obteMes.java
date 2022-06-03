package main.domain.classes.functions;

import java.time.LocalDate;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class obteMes extends FuncioDate {
    /** Aquest metode retorna el mes de la data _value
     * @param
     * @return int
     */
    public int obteMes() {
        return _value.getMonthValue();
    }
}