package main.domain.classes.functions.string;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;

/** La classe tamanyText exten FString, representa una funció que compta el nombre de caracters que té un String, accepta 1 paràmetre
 *  @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class tamanyText extends FString {

    /** "Identificador" de la funció */
    public static final String Nom = "TAMANY";

    /** Constructora amb un sol parametre
     * @param param Parametre
     */
    public tamanyText(IVisitable param) { super(param); }

    /**	Implementació polimorfica de executa():
     *  Aquest metode retorna el nombre de caracters en el text s
     *  @return Token (NumberT)
     */
    public Token executa() { return new NumberT(_s.length() ); }
}