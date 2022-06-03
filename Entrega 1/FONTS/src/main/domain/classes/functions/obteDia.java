package main.domain.classes.functions;

import java.time.LocalDate;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class obteDia extends FuncioDate {
    /** Aquest metode retorna el dia del mes de la data d
     * @param
     * @return int
     */
    public int obteDia() {
        return _value.getDayOfMonth();
    }
}