package main.domain.classes.functions.data;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;


/** La classe obteAny es una classe ue exten de la classe FData, retorna el any de la data pasada com a parametre
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class obteAny extends FData {
    /** "Identificador" de la funció */

    public static final String Nom = "OBTEANY";

    /** Constructora amb un sol parametre
     * @param param Parametre
     */
    public obteAny(IVisitable param) { super(param); }

    /** Implementació polimorfica de executa():
     *  Aquest metode retorna l'any de la data
     *  @return Token(NumberT)
     */
    public Token executa() {
        return new NumberT(_data.getYear());
    }
}