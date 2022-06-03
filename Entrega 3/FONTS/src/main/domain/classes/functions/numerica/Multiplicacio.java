package main.domain.classes.functions.numerica;

import java.util.List;
import main.domain.classes.token.Token;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.IVisitable;

/** La classe Multiplicacio es una classe que exten de la classe FNumerica, retorna la multiplicacio dels paràmetres
 *  @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Multiplicacio extends FNumerica {

    /** "Identificador" de la funció */
    public static final String Nom = "MULT";

    /** Constructora
     * @param params Llista de parametres
     */
    public Multiplicacio(List<IVisitable> params) { super(params); }

    /**	Implementació polimorfica de executa():
     *	Retorna la multiplicacio dels valors de _params
     *	@return Token(NumberT)
     */
    @Override
    protected Token executa() {
        double mult = 1;
        for (Number n : _values) { mult *= n.doubleValue(); }
        return new NumberT(mult);
    }
}