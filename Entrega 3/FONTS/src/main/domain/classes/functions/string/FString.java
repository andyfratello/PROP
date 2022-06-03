package main.domain.classes.functions.string;

import main.domain.classes.functions.Funcio;
import main.domain.classes.types.BlocReferencies;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.Token;
import main.domain.classes.types.TType;

import java.util.List;

/** Classe que representa una funció amb un sol parametre de tipus String
 * @author  Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public abstract class FString extends Funcio {
    /** Unic parametre de la funció */
    String _s;

    /** Constructora
     *  @param param parametre de la funcio
     */
    public FString(IVisitable param)
    {
        super(param);
        _tipus = TType.STRING;
    }

    // ---------- IVISITOR ----------
    /** Override de visit(BlocReferencies), aquestes funcions no admeten blocs
     *  @param b Bloc
     *  @throws RuntimeException [#FSTR] Parametre no pot ser bloc
     */
    @Override
    public void visit(BlocReferencies b) throws RuntimeException { throw new RuntimeException("[#FSTR] Parametre no pot ser bloc"); }

    /** Converteix el token a un String
     *  @param t IVisitable de tipus Token
     */
    @Override
    public void visit(Token t) { _s = t.asString(); }
}



