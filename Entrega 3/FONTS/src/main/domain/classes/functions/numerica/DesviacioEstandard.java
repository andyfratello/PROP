package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe DesviacioEstandard es una classe que exten de la classe FNumerica, retorna la desviació estàndard dels paràmetres
 *  @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class DesviacioEstandard extends FNumerica {

    /** "Identificador" de la funció */
    public static final String Nom = "DESVEST";

    /** Constructora
     * @param params Llista de parametres
     */
    public DesviacioEstandard(List<IVisitable> params) { super(params); }

    /** @return Retorna la variança dels valors dels parametres */
    private double variance() {
        double n = _values.size();
        double sum = 0;
        for (Number num : _values) { sum += num.doubleValue(); }

        double mean = sum/n;
        double sqDiff = 0;
        for (Number num : _values) {
            sqDiff += (num.doubleValue() - mean) * (num.doubleValue() - mean);
        }
        return (sqDiff/n);
    }

    /**
     * Retorna la desviació estándar dels valors d'un vector de cel·les en Double
     * @return Token(NumberT)
     */
    @Override
    protected Token executa() throws RuntimeException {
        return new NumberT(Math.sqrt(variance() ));
    }
}
