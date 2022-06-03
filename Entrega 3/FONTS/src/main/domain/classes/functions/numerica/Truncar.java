package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;

/** La classe Truncar es una classe que exten de la classe FN_Unaria, retorna el valor enter truncat del paràmetre
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Truncar extends FN_Unaria {

    /** "Identificador" de la funció */
    public static final String Nom = "TRUNCAR";

    /** Constructora amb un sol parametre
     * @param param Parametre
     */
    public Truncar(IVisitable param) { super(param); }

    /**	Implementació polimorfica de executa():
     *  Trunca el valor del primer (i unic)  parametre a un enter, es a dir, elimina els decimals
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() { return new NumberT(_n.intValue()); }

}