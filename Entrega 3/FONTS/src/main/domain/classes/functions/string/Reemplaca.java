package main.domain.classes.functions.string;

import main.domain.classes.functions.Funcio;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.StringT;
import main.domain.classes.token.Token;
import main.domain.classes.types.BlocReferencies;

import java.util.List;

/** La classe Reemplaça (Reemplaca.java) representa una funció de reemplaçament
 *  Accepta 3 parametres, el String d’entrada, el valor que es vol reemplaçar
 *  i el valor per el qual es vol reemplaçar
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Reemplaca extends Funcio {
    private String _entrada;
    private String _vell;
    private String _nou;

    private int i;

    /** "Identificador" de la funció */
    public static final String Nom = "REPLACE";

    /** Constructora
     * @param params Llista de parametres
     * @throws RuntimeException "[#REEMP] Funcio esperava 3 parametres"
     */
    public Reemplaca(List<IVisitable> params) throws RuntimeException {
        super(params);
        if (_params.size() != 3) throw new RuntimeException("[#REEMP] Funcio esperava 3 parametres, en te: " + _params.size());
    }

    /** Implementació polimorfica de executa()
     * @return Token(StringT) amb el String d'entrada amb els valors reemplaçats
     */
    protected Token executa() { return new StringT(_entrada.replaceAll(_vell, _nou) ); }

    /** Inicialització previa d'una funció */
    @Override
    protected void ini() { i = 0; }

    /** Override de visit(BlocReferencies), aquesta funció no admet blocs
     *  @param b Bloc
     *  @throws RuntimeException [#REEMP] Parametre no pot ser bloc
     */
    @Override
    public void visit(BlocReferencies b) throws RuntimeException { throw new RuntimeException("[#REEMP] Parametre no pot ser bloc"); }

    /** Override de visit(Token)
     *  Per poder inacialitzar correctament els tres parametres
     *  @param t IVisitable de tipus Token
     */
    @Override
    public void visit(Token t) {
        switch (i){
            case 0: _entrada = t.asString();    break;
            case 1: _vell    = t.asString();    break;
            case 2: _nou     = t.asString();    break;
            default:    System.err.println("[#REEMP] masses parametres: " + i);
        }
        i++;
    }


}
