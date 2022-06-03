package main.domain.classes.functions.numerica;

import main.domain.classes.exceptions.NegativeRootException;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe Arrel es una classe que exten de la classe FN_Binaria
 *  Retorna l'arrel del primer paràmetre del grau del segon paràmetre
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Arrel extends FN_Binaria {

    /** "Identificador" de la funció */
    public static final String Nom = "ARREL";

    /** Constructora
     * @param params Llista de parametres
     * @throws RuntimeException [#SQRT] Expected 2 parameters
     */
    public Arrel(List<IVisitable> params) throws RuntimeException {
        super(params);
    }

    /** Implementació polimorfica de executa():
     *  Donat dos parametres, fa l'arrel del primer parametre del grau del segon parametre
     *  ATENCIO: Si param[1] més gran que 2 es poden produir errors de presició sobretot en nombres grans
     *  (Millor no fer comparacions del tipus ==)
     *  @return Token(NumberT)
     *  @throws NegativeRootException si param[0] es negatiu
     */
    @Override
    protected Token executa() throws RuntimeException {
        //System.out.println("a "+ _a.doubleValue());
        //System.out.println("b " +_b.doubleValue());
        double exp = _b.doubleValue();
        double x = _a.doubleValue();

        if (x < 0) throw new NegativeRootException(x);

        return (exp == 2) ? new NumberT(Math.sqrt(x) ) : new NumberT(Math.pow(x, 1.0 / exp) );
    }
}