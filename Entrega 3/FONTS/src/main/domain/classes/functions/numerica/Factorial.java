package main.domain.classes.functions.numerica;

import main.domain.classes.exceptions.NegativeFactorialException;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;

/** La classe Factorial es una classe que exten de la classe FN_Unaria, retorna el Factorial del paràmetre interpretat com a enter
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Factorial extends FN_Unaria {

    /** "Identificador" de la funció */
    public static final String Nom = "FACTORIAL";

    /** Constructora amb un parametre
     *  @param param Parametre
     */
    public Factorial(IVisitable param)  { super(param); }

    /**	Implementació polimorfica de executa():
     *  Calcula el factorial d'un parametre interpretat com a nombre enter
     *  @return Token(NumberT)
     *  @throws NegativeFactorialException si param[0] es negatiu
     */
    @Override
    protected Token executa() throws NegativeFactorialException {
        int x =_n.intValue();

        if (x < 0) throw new NegativeFactorialException(x);
        return new NumberT(Integer.valueOf(factorialR(x) ) );
    }

    /**
     * Calcula el factorial del parametre n de forma recursiva
     * @param n >= 0
     * @return n!
     */
    private static int factorialR(int n) {
        if (n == 0) return 1;
        return (n * factorialR(n-1));
    }
}
