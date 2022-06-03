package main.domain.classes.functions.data;

import main.domain.classes.types.BlocReferencies;
import main.domain.classes.token.IVisitable;
import main.domain.classes.functions.Funcio;
import main.domain.classes.token.Token;
import main.domain.classes.types.TType;

import java.time.LocalDate;

/** La classe FData es una classe abstracta que exten de la classe Funcio, representa les funcions que només admeten un sol paràmetre de tipus Data
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public abstract class FData extends Funcio
{
    LocalDate _data;

    public FData(IVisitable param) {
        super(param);
        _tipus = TType.DATE;
    }

    // ---------- IVISITOR ----------
    /**
     * Override de visit(BlocReferencies), aquestes funcions no admeten blocs
     * @param b Bloc
     * @throws RuntimeException [#FDATA] Parametre no pot ser bloc
     */
    @Override
    public void visit(BlocReferencies b) throws RuntimeException
    {
        throw new RuntimeException("[#FDATA] Parametre no pot ser bloc");
    }

    /** Override visit(Token), inicialitza el parametre
     * @param t IVisitable de tipus Token
     */
    @Override
    public void visit(Token t)
    {
        try { _data = t.asDate(); }
        catch (Exception e) { System.out.println("[#FDATA] Parametre no es Data: " + t.asString() + " " + t.getClass()); }
    }


}
