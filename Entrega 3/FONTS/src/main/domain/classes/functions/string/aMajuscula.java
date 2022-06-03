package main.domain.classes.functions.string;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.StringT;
import main.domain.classes.token.Token;

/** La classe aMajuscula exten FString, representa una funció de capitalització, accepta 1 paràmetre
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class aMajuscula extends FString {

    /** "Identificador" de la funció */
    public static final String Nom = "MAJUS";

    /** Constructora amb un parametre
     * @param param Parametre
     */
    public aMajuscula(IVisitable param) { super(param); }

    /**	Implementació polimorfica de executa()
     *  Capitalitza l'string
     *  @return Token(StringT)
     */
    @Override
    public Token executa() { return new StringT(_s.toUpperCase()); }
}
