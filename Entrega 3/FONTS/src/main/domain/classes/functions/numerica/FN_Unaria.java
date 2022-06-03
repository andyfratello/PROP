package main.domain.classes.functions.numerica;

import main.domain.classes.types.BlocReferencies;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe FN_Unaria es una classe abstracta que exten de la classe FNumerica, representa les funcions
 *  que només admeten un sol paràmetre numeric
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public abstract class FN_Unaria extends FNumerica {
    /** Parametre com a numero */
    Number _n;

    /** Constructora privada
     * @param params Llista de parametres, només en tindrà un
     */
    private FN_Unaria(List<IVisitable> params) { super(params); }

    /** Constructora amb un sol parametre
     *  @param param Partametre
     */
    public FN_Unaria(IVisitable param) { this(List.of(param)); }

    /** Funció unaria només espera un valor
     * @param t IVisitable de tipus Token
     */
    @Override
    public void visit(Token t) { _n = t.asNumber(); }

    /** Funcio Unaria no admet blocs
     * @param b IVisitable de tipus BlocReferencies
     * @throws RuntimeException [#FUNARIA] Parametre no pot ser bloc */
    @Override
    public void visit(BlocReferencies b) throws RuntimeException { throw new RuntimeException("[#FUNARIA] Parametre no pot ser bloc"); }
}
