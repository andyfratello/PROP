package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.types.Binari;
import main.domain.classes.token.Token;

/** La classe decimalABinari es una classe que exten de la classe FN_Unaria, retorna la interpretació en binari d’un enter
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class decimalABinari extends FN_Unaria {

    /** "Identificador" de la funció */
    public static final String Nom = "DEC2BIN";

    /** Cosntructora
     * @param param Parametre
     */
    public decimalABinari(IVisitable param) { super(param); }

    /**	Implementació polimorfica de executa():
     *  Interpreta un parametre com a enter i el passa a Binari
     *  @return Token(NumberT) */
    @Override
    protected Token executa() { return new NumberT(new Binari(_n.intValue()) ); }


}
