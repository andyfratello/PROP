package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe Mitjana es una classe que exten de la classe FNumerica, retorna la mitjana dels paràmetres
 *  @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Mitjana extends FNumerica {

    /** "Identificador" de la funció */
    public static final String Nom = "MITJANA";

    /** Constructora
     * @param params Llista de parametres
     */
    public Mitjana(List<IVisitable> params) { super(params); }

    /**	Implementació polimorfica de executa():
     *	Retorna la mitjana dels valors de _params
     *	@return Token(NumberT)
     */
    @Override
    protected Token executa() {
        double sum = 0;
        for (Number n : _values) { sum += n.doubleValue(); }
        return new NumberT(sum/_values.size() );
    }
}