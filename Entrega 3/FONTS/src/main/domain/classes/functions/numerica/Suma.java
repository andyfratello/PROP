package main.domain.classes.functions.numerica;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.Token;
import java.util.List;

/** La classe Suma es una classe que exten de la classe FNumerica, retorna la suma dels paràmetres
 *  @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Suma extends FNumerica {
	
	/** "Identificador" de la funció */
	public static final String Nom = "SUMA";

	/** Constructora
	 * @param params Llista de parametres
	 */
	public Suma(List<IVisitable> params) { super(params); }
	
	/**	Implementació polimorfica de executa():
	*	Retorna la suma dels valors de _params
	*	@return Token(NumberT)
	*/
	@Override
	protected Token executa() {
		double sum = 0;
		for (Number n : _values) { sum += n.doubleValue(); }
		return new NumberT(sum);
	}
}
