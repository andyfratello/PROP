package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.types.Binari;
import main.domain.classes.token.Token;


/** La classe binariADecimal es una classe que exten de la classe FN_Unaria, retorna la el valor d’un numero binari com a enter
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class binariADecimal extends FN_Unaria {

    /** "Identificador" de la funció */
    public static final String Nom = "BIN2DEC";

    /** Constructora
     * @param param Parametre
     */
    public binariADecimal(IVisitable param) { super(param); }

    /**	Implementació polimorfica de executa():
     *  Fa la conversió de Binari a Decimal
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() { return new NumberT(_n.intValue() ); }
    
    /** Override de visit(Token) per assegurar-nos de que el parametre es un hexadecimal
     * @param t IVisitable de tipus Token
     */
    @Override
    public void visit(Token t)
    {
        try {
            Number n = t.asNumber();
            if (n instanceof Binari) super.visit(t);
            else throw new Exception("[#BIN2DEC] Param no es Binari");
        }
        catch (Exception e) { System.err.println("[#BIN2DEC] Parametre no es Number: " + t.asString() + " " + t.getClass()+ "| Exception " + e.getMessage()); }
    }
}