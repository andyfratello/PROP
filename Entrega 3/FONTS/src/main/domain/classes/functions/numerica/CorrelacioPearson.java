package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe CorrelacioPearson es una classe que exten de la classe FN_Binaria_Blocs, retorna la correlacio de pearson entre dos blocs
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class CorrelacioPearson extends FN_Binaria_Blocs {
    /** "Identificador" de la funció */
    public static final String Nom = "PEARSON";

    /** Constructora
     * @param params Llista de parametres
     * @throws RuntimeException <p>
     * [#PEARSON] Expected 2 parameter </p> <p>
     * [#PEARSON] Blocs han de tenir el mateix nombre final de parametres</p> <p>
     * [#PEARSON] Un dels parametres no es un bloc
     * </p>
     */
    public CorrelacioPearson(List<IVisitable> params) throws RuntimeException { super(params); }

    /**	Implementació polimorfica de executa():
     *  Donat dos blocs, fa la Correlacio de Pearson dels valors
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() throws RuntimeException {
        double sx = 0.0;
        double sy = 0.0;
        double sxx = 0.0;
        double syy = 0.0;
        double sxy = 0.0;

        if (_a.size() != _b.size()) throw new RuntimeException
                ("[#PEARSON] Blocs han de tenir el mateix nombre de parametres: " + _a.size() + " != " + _b.size());

        int n = _a.size();

        for(int i = 0; i < n; ++i) {
            double x = _a.get(i);
            double y = _b.get(i);
            sx += x;
            sy += y;
            sxx += x * x;
            syy += y * y;
            sxy += x * y;
        }

        double cov = sxy / n - sx * sy / n / n;
        double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
        double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);

        return new NumberT(cov / sigmax / sigmay);
    }
}
