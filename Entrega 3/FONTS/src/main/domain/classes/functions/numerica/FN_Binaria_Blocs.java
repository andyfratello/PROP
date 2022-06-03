package main.domain.classes.functions.numerica;

import main.domain.classes.types.BlocReferencies;
import main.domain.classes.token.IVisitable;
import main.domain.classes.types.Referencia;
import main.domain.classes.token.Token;

import java.util.ArrayList;
import java.util.List;

/** La classe FN_Binaria_Blocs es una classe abstracta que exten de la classe FN_Binaria, representa les funcions que només admeten dos blocs de paràmetres de tipus numèric
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public abstract class FN_Binaria_Blocs extends FN_Binaria{

    /** Primer bloc */
    List<Double> _a;
    /** Segon bloc */
    List<Double> _b;


    /** Constructora
     *  @param params Llista de parametres
     * @throws RuntimeException <p>
     * [#FBINARIA] Expected 2 parameters </p> <p>
     * [#FNBLOCS] Blocs han de tenir el mateix nombre final de parametres</p> <p>
     * [#FNBLOCS] Un dels parametres no es un bloc
     */
    public FN_Binaria_Blocs(List<IVisitable> params) throws RuntimeException
    {
        super(params);
        try{
            BlocReferencies b1 = (BlocReferencies) _params.get(0);
            BlocReferencies b2 = (BlocReferencies) _params.get(1);

            if (b1.GetSize() != b2.GetSize()) throw new RuntimeException
                    ("[#FNBLOCS] Blocs han de tenir el mateix nombre final de parametres: " + b1.GetSize() + " != " + b2.GetSize());
        }
        catch(Exception e) {
            throw new RuntimeException
                    ("[#FNBLOCS] Un dels parametres no es un bloc: p1 " + _params.get(0).getClass() + " p2 " + _params.get(1).getClass());
        }
    }

    /** Inicialització previa d'una funció */
    @Override
    protected void ini() {
        super.ini();
        _a = new ArrayList<>();
        _b = new ArrayList<>();
    }

    /** Override de visit(BlocRefencies) per determinar a quina llista van els elements
     *  @param b IVisitable de tipus BlocReferencies
     */
    @Override
    public void visit(BlocReferencies b)
    {
        List<Referencia> refs = b.GetReferencies();
        for (Referencia r : refs) this.visit(r);
        _isSecond = true;
    }

    /** Override de visit(Token) de FNumerica per dividir els parametres en 2 llistes
     *  Donat un token t, l'afegeix a la llista corresponent
     *  @param t IVisitable de tipus Token
     */
    @Override
    public void visit(Token t) {
        try {
            if (_isSecond) _a.add(t.asNumber().doubleValue());
            else          _b.add(t.asNumber().doubleValue());
        }
        catch (Exception e) { System.err.println("[#FNBLOCS] Parametre no es numero: " + t.asString() + " " + t.getClass()); }
    }
}
