package main.domain.classes.functions.numerica;

import main.domain.classes.types.Hexadecimal;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;

/** La classe decimalAHexadecimal es una classe que exten de la classe FN_Unaria, retorna la interpretació en Hexadcimal d’un enter
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class decimalAHexadecimal extends FN_Unaria {

    /** "Identificador" de la funció */
    public static final String Nom = "DEC2HEX";

    /** Constructora
     * @param param Parametre
     * @throws RuntimeException Param no es Hexadecimal
     */
    public decimalAHexadecimal(IVisitable param) throws RuntimeException { super(param); }

    /**	Implementació polimorfica de executa():
     *  Interpreta un parametre com a enter i el passa a Hexadecimal
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() { return new NumberT(new Hexadecimal(_n.intValue()) ); }
}