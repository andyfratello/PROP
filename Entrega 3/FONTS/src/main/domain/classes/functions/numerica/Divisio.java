package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe Divisio es una classe que exten de la classe FN_Binaria
 *  Retorna la divisió del primer paràmetre dividit per el segon paràmetre
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Divisio extends FN_Binaria {

    /** "Identificador" de la funció */
    public static final String Nom = "DIVISIO";

    /** Constructora
     * @param params els dos parametres a dividir
     * @throws RuntimeException [#FBINARIA] Expected 2 parameters
     */
    public Divisio(List<IVisitable> params) throws RuntimeException { super(params); }

    /**	Implementació polimorfica de executa():
     *  Donat dos parametres, els divideix
     *  @return Token(NumberT)
     *  @throws RuntimeException [#DIV] No es pot dividir entre 0
     */
    @Override
    protected Token executa() throws RuntimeException {
        double dividend = _a.doubleValue();
        double divisor  = _b.doubleValue();

        if (divisor == 0) throw new RuntimeException("[#DIV] No es pot dividir entre 0");
        return new NumberT( Double.valueOf(dividend/divisor));
    }
}