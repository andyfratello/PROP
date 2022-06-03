package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;

/** La classe Absolut es una classe abstracta que exten de la classe FN_Unaria, retorna el valor absolut del paràmetre
 *  @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Absolut extends FN_Unaria {

    /** "Identificador" de la funció */
    public static final String Nom = "ABS";

    /** Constructora
     *  @param param Parametre
     */
    public Absolut(IVisitable param) { super(param); }

    /** Implementació polimorfica de executa():
     *  Retorna el valor absolut d'un sol parametre
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() {
        return new NumberT( Math.abs(_n.doubleValue()) );
    }
}