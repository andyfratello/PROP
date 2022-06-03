package main.domain.classes.functions;

import java.time.LocalDate;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class obteDiaSetmana extends FuncioDate {
    /** Aquest metode retorna el dia de la setmana de la data d
     * @param
     * @return String <p> <b> <i> ATENCIO: </i> </b> En cas d'error es retorna "NAD"</p>
     */
    public String obteDiaSetmana() {
        switch (_value.getDayOfWeek()) {
            case MONDAY:
                return "dl";
            case TUESDAY:
                return "dm";
            case WEDNESDAY:
                return "dc";
            case THURSDAY:
                return "dj";
            case FRIDAY:
                return "dv";
            case SATURDAY:
                return "ds";
            case SUNDAY:
                return "dg";
            default:
                return "NAD";
        }
    }
}