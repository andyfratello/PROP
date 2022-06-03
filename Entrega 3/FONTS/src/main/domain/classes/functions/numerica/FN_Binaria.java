package main.domain.classes.functions.numerica;

import main.domain.classes.types.BlocReferencies;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe FN_Binaria es una classe abstracta que exten de la classe FNumerica
 *  Representa les funcions que només admeten dos paràmetres numerics
 *  @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public abstract class FN_Binaria extends FNumerica {

    /** Primer parametre */
    protected Number _a;
    /** Segon parametre */
    protected Number _b;
    /** Determinant */
    boolean _isSecond = false;

    /**
     * Constructora
     * @param params Llista de parametres
     * @throws RuntimeException [#FBINARIA] Esperava dos parametres
     */
    public FN_Binaria(List<IVisitable> params) throws RuntimeException {
        super(params);
        if(_params.size() != 2) throw new RuntimeException("[#FBINARIA] Esperava dos parametres, has: " + _params.size());
    }

    /** Inicialització previa d'una funció */
    @Override
    protected void ini() {
        super.ini();
        _isSecond = false;
    }

    /**
     * Override de visit(Token)
     * @param t IVisitable de tipus Token
     */
    @Override
    public void visit(Token t) {
        super.visit(t);
        if (_isSecond) _b = _values.get(1);
        else           _a = _values.get(0);
        _isSecond = true;
    }

    /**
     * Override de visit(BlocReferencies), aquesta funció no admet blocs
     * @param b IVisitable de tipus BlocReferencies
     * @throws RuntimeException [#FBINARIA] Parametre no pot ser bloc
     */
    @Override
    public void visit(BlocReferencies b) throws RuntimeException { throw new RuntimeException("[#FBINARIA] Parametre no pot ser bloc"); }
}
