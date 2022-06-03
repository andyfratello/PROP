package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe Variancia es una classe que exten de la classe FNumerica, retorna la variància dels paràmetres
 *  @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Variancia extends FNumerica {

    /** "Identificador" de la funció */
    public static final String Nom = "VARIANCIA";

    /** Constructora
     * @param params Llista de parametres
     */
    public Variancia(List<IVisitable> params) { super(params); }

    /**	Implementació polimorfica de executa():
     *	Retorna la variància dels valors de _params
     *	@return Token(NumberT)
     */
    @Override
    protected Token executa() {
        double n = _values.size();
        double sum = 0;
        for (Number num : _values) { sum += num.doubleValue(); }

        double mean = sum/n;
        double sqDiff = 0;
        for (Number num : _values) {
            sqDiff += (num.doubleValue() - mean) * (num.doubleValue() - mean);
        }
        return new NumberT(sqDiff/n);
    }
}
