package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/** La classe Mediana es una classe que exten de la classe FNumerica, retorna la mediana dels paràmetres
 *  @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Mediana extends FNumerica {

    /** "Identificador" de la funció */
    public static final String Nom = "MEDIANA";

    /** Constructora
     * @param params Llista de parametres
     */
    public Mediana(List<IVisitable> params) { super(params); }

    /**	Implementació polimorfica de executa():
     *	Retorna la mediana dels valors de _params
     *	@return Token(NumberT)
     */
    @Override
    protected Token executa() {
        List<Double> values = new ArrayList<>();
        for (Number n : _values) values.add(n.doubleValue());

        Collections.sort(values);

        int n = values.size();
        double m;

        if(n%2 == 1) m = values.get((n-1)/2);
        else m = (values.get((n/2)-1) + values.get(n/2))/2;

        return new NumberT(m);
    }
}
