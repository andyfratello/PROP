package main.domain.classes.functions.data;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.StringT;
import main.domain.classes.token.Token;

/** La classe obteDiaSetmana es una classe ue exten de la classe FData, retorna el dia de la setmana de la data pasada com a paràmetre
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class obteDiaSetmana extends FData {
    /** "Identificador" de la funció */
    public static final String Nom = "DIASETMANA";

    /** Constructora amb un sol parametre
     * @param param Parametre
     */
    public obteDiaSetmana(IVisitable param) { super(param); }

    /** Implementació polimorfica de executa():
     *  Aquest metode retorna el dia de la setmana de la data
     *  @return Token(NumberT)
     */
    public Token executa() {
        String dOw = "";
        switch (_data.getDayOfWeek())
        {
            case MONDAY:
                dOw = "Dilluns";
            break;
            case TUESDAY:
                dOw = "Dimarts";
            break;
            case WEDNESDAY:
                dOw = "Dimecres";
            break;
            case THURSDAY:
                dOw = "Dijous";
            break;
            case FRIDAY:
                dOw = "Divendres";
            break;
            case SATURDAY:
                dOw = "Dissabte";
            break;
            case SUNDAY:
                dOw = "Diumenge";
            break;
        }
        return new StringT(dOw);
    }
}

