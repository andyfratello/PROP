package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe Potencia es una classe que exten de la classe FN_Binaria
 *  Retorna la el primer paràmetre elevat al segon paràmetre
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Potencia extends FN_Binaria {

    /** "Identificador" de la funció */
    public static final String Nom = "POW";

    /** Constructora, espera dos parametres
     * @param params Llista de parametres
     * @throws RuntimeException [#FBINARIA] Expected 2 parameters
     */
    public Potencia(List<IVisitable> params) throws RuntimeException { super(params); }

    /**	Implementació polimorfica de executa():
     *  Aquest metode retorna el valor de la potencia de param[0]^param[1]
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() { return new NumberT(Math.pow(_a.doubleValue(), _b.doubleValue()) );}
}
