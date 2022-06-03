package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe Covariancia es una classe que exten de la classe FN_Binaria_Blocs, retorna la covariancia entre dos blocs
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Covariancia extends FN_Binaria_Blocs {
    /** "Identificador" de la funció */
    public static final String Nom = "COVARIANCIA";

    /** Constructora
     * @param params Llista de parametres
     * @throws RuntimeException <p>
     * [#FNBLOCS] Expected 2 parameters </p> <p>
     * [#FNBLOCS] Blocs han de tenir el mateix nombre final de parametres</p> <p>
     * [#FNBLOCS] Un dels parametres no es un bloc
     * </p>
     */
    public Covariancia(List<IVisitable> params) throws RuntimeException { super(params); }

    /**	Implementació polimorfica de executa():
     *  Donat dos blocs, la covariancia dels valors
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() throws RuntimeException {
        int n = _a.size();
        double sum = 0.0;
        double mean_a = mean(_a);
        double mean_b = mean(_b);
        for(int i = 0; i < n; i++)  { sum += (_a.get(i) - mean_a)*(_b.get(i) - mean_b); }
        return new NumberT( Math.round((sum/(n - 1))*100000.0)/100000.0 );
    }



    /** Calcula la mitjana d'una llista de doubles
     *  @param vec Llista de Doubles
     *  @return Mitja del vector d'entrada
     */
    private double mean(List<Double> vec) {
        double sum = 0;
        int n = vec.size();
        for (Double v : vec) { sum = sum + v; }
        return sum / n;
    }

}