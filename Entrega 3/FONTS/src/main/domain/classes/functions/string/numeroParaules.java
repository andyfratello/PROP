package main.domain.classes.functions.string;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;


/** La classe numeroParaules exten FString, representa una funció que compta el nombre de paraules que té un String, accepta 1 paràmetre
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class numeroParaules extends FString {

    /** "Identificador" de la funció */
    public static final String Nom = "NUMPARAULES";

    /** Constructora amb un sol parametre
     * @param param Parametre
     */
    public numeroParaules(IVisitable param) { super(param); }

    /** Implementació polimorfica de executa():
     *  Aquest metode retorna el nombre de paraules en el text s (Considerant que l'apostrof forma part de la paraula)
     *  @return Token(NumberT)
     */
    @Override
    public Token executa() {
        if (_s.isBlank()) return new NumberT(Integer.valueOf(0));

        int numParaules = 0;
        boolean enParaula = false;
        for (int i = 0; i < _s.length(); i++) {
            if (esCharValidEnParaula(_s.charAt(i)) && !enParaula) {
                numParaules++;
                enParaula = true;
            }
            else if (!esCharValidEnParaula(_s.charAt(i))) {
                enParaula = false;
            }
        }
        return new NumberT(numParaules);
    }

    /** Determina si un caracter es part d'una paraula
     * @param c Caracter
     * @return (TRUE) Si el caracter es una lletra o un apostrof
     */
    private static boolean esCharValidEnParaula(char c) {
        return (c == '\'' || Character.isLetter(c));
    }

}