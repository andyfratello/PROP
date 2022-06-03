package main.domain.classes.token; 

import java.io.Serializable;
import java.time.LocalDate;

/** Classe base del Tokens que utilitzen les funcions
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public abstract class Token implements IVisitable, Serializable
{
	/**	@return La representació del token com a Number
	 * 	@throws RuntimeException Token no es pot expressar com a Number
	 */
	public abstract Number asNumber()	throws RuntimeException;

	/**	@return La representació del token com a LocalDate
	 * 	@throws RuntimeException Token no es pot expressar com a LocalDate
	 */
	public abstract LocalDate asDate()	throws RuntimeException;

	/**	@return La representació del token com a String */
	public abstract String asString();

	// ---------- IVISITABLE ----------
	/** Implementacio del patro visitor */
	public final void accept(IVisitor visitor) { visitor.visit(this); }
	
}
