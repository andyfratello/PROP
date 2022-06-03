package main.domain.classes.functions.data;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;

/** La classe obteDia es una classe ue exten de la classe FData, retorna el dia de la data pasada com a paràmetre
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class obteDia extends FData {
    /** "Identificador" de la funció */

    public static final String Nom = "OBTEDIA";

    /** Constructora amb un sol parametre
     * @param param Parametre
     */
    public obteDia(IVisitable param) { super(param); }

    /** Implementació polimorfica de executa():
     *  Aquest metode retorna el dia del mes de la data
     *  @return Token(NumberT)
     */
    public Token executa() { return new NumberT(_data.getDayOfMonth()); }
}
