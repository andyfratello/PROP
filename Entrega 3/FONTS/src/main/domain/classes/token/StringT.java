package main.domain.classes.token; 

import main.domain.classes.functions.Interpret;
import java.time.LocalDate;

/** Representa un Token de tipus String
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class StringT extends Token
{
	private final String _token;

	/** Constructora
	 * @param s Valor del token
	 */
	public StringT(String s) { _token = s; }

	/** Intenta parsejar l'string com a Number
	 * @throws RuntimeException "Number cannot be expressed as Number" */
	public Number asNumber() throws RuntimeException	{ return Interpret.StringToNumber(_token);}
	/** Intenta parsejar l'string com a LocalDate
	 * @throws RuntimeException "Number cannot be expressed as LocalDate" */
	public LocalDate asDate() throws RuntimeException	{ return Interpret.StringToDate(_token);}
	/** @return El string de _token*/
	public String asString()	{ return _token; }
	/** @return El string de _token*/
	@Override
	public String toString() { return _token; }
}
