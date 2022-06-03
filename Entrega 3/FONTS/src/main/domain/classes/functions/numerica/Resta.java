package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe Resta es una classe que exten de la classe FN_Binaria
 *  Retorna la resta del primer paràmetre amb el segon
 *  @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Resta extends FN_Binaria {

    /** "Identificador" de la funció */
    public static final String Nom = "RESTA";

    /** Constructora, espera dos parametres
     * @param params Llista de parametres
     * @throws RuntimeException [#FBINARIA] Expected 2 parameters
     */
    public Resta(List<IVisitable> params) throws RuntimeException { super(params); }

    /**	Implementació polimorfica de executa():
     *  Resta els dos parametres introduits
     *  @return Token(NumberT)
     */
    @Override
    protected Token executa() { return new NumberT(_a.doubleValue() - _b.doubleValue()); }

}