package main.domain.classes.functions.numerica;

import main.domain.classes.types.Hexadecimal;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;

/** La classe hexadecimalADecimal es una classe que exten de la classe FN_Unaria, retorna la el valor d’un Hexadcimal com a enter
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class hexadecimalADecimal extends FN_Unaria {

    /** "Identificador" de la funció */
    public static final String Nom = "HEX2DEC";

    /** Constructora amb un sol parametre
     * @param param Parametre
     * @throws RuntimeException [#HEX2DEC] Param no es Hexadecimal
     */
    public hexadecimalADecimal(IVisitable param) throws RuntimeException { super(param); }

    /**	Implementació polimorfica de executa():
     *  Fa la conversió de Hexadecimal a Decimal
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() { return new NumberT(_n.intValue() ); }

    /** Override de visit(Token) per assegurarnos de que el parametre es un hexadecimal */
    @Override
    public void visit(Token t)
    {
        try {
            Number n = t.asNumber();
            if (n instanceof Hexadecimal) super.visit(t);
            else throw new RuntimeException("[#HEX2DEC] Param no es Hexadecimal");
        }
        catch (Exception e) { System.err.println("[#HEX2DEC] Parametre no es Numero: " + t.asString() + " " + t.getClass() + "| Exception " + e.getMessage()); }
    }
}