package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;


/** La classe Arrodonir es una classe que exten de la classe FN_Unaria, retorna el valor arrodonit del paràmetre
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Arrodonir extends FN_Unaria {

    /** "Identificador" de la funció */
    public static final String Nom = "ARRODONIR";

    /** Constructora amb un sol parametre
     *  @param param Parametre
     */
    public Arrodonir(IVisitable param) { super(param); }

    /**	Implementació polimorfica de executa():
     *  Arrodoneix un sol parametre
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() throws RuntimeException { return new NumberT(Math.round(_n.doubleValue() )); }


}