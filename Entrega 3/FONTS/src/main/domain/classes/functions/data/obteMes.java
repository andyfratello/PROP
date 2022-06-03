package main.domain.classes.functions.data;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class obteMes extends FData {
    /** "Identificador" de la funció */

    public static final String Nom = "OBTEMES";

    /** Constructora amb un sol parametre
     * @param param Parametre
     */
    public obteMes(IVisitable param) { super(param); }

    /** Implementació polimorfica de executa():
     *  Aquest metode retorna el numero del mes de la data
     *  @return Token(NumberT)
     */
    public Token executa() {
        return new NumberT(_data.getMonthValue());
    }
}

