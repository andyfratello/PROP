package main.domain.classes.token;

import java.time.LocalDate;

/** Classe que representa un Token de tipus Number (numeric)
 *	@author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class NumberT extends Token
{
	private final Number _token;

	/**	Constructora amb objecte de tipus Number
	 *	@param s Numero
	 */
	public NumberT(Number s) {
		Number tmp = s;
		try {
			Double d = (Double) tmp;
			if (d == d.intValue()) tmp = d.intValue();
		}
		catch (Exception ignore) { }
		_token = tmp;
	}

	/**	Constructora amb enter
	 *	@param i valor del numero
	 */
	public NumberT(int i)	 { this((Integer)i); }

	/**	Constructora amb double
	 *	@param d valor del numero
	 */
	public NumberT(double d) { this((Double)d); }

	/** @return El numero que conté */
	public Number asNumber() { return _token; }

	/** @throws RuntimeException "Number cannot be expressed as LocalDate" */
	public LocalDate asDate() throws RuntimeException { throw new RuntimeException("Number cannot be expressed as Date"); }

	/** @return La representació com String del numero */
	public String asString() { return _token.toString(); }

	/** @return La representació com a String del numero */
	@Override
	public String toString() { return _token.toString(); }
}
