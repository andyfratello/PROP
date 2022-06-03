package main.domain.classes.token; 

import main.domain.classes.functions.Interpret;
import java.time.LocalDate;

/** Classe que representa un Token de tipus LocalDate (data)
 *	@author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class DateT extends Token
{
	private final LocalDate _token;

	/** Constructora buida, crea una nova DataT amb la data actual  */
	public DateT() { _token = LocalDate.now(); }

	/** Constructora
	 * @param s Valor del token
	 */
	public DateT(LocalDate s) { _token = s; }

	/** Constructora apartir d'un string
	 * @param s Data en el format "dd/MM/yyyy"
	 */
	public DateT(String s) { _token = Interpret.StringToDate(s); }

	/** @throws RuntimeException "LocalDate cannot be expressed as Number" */
	public Number asNumber() throws RuntimeException { throw new RuntimeException("LocalDate cannot be expressed as Number"); }

	/** @return LocalDate que conte */
	public LocalDate asDate() { return _token; }

	/** @return La representació com a String de la data */
	public String asString() { return _token.toString(); }

	/** @return La representació com a String de la data */
	@Override
	public String toString() { return _token.toString(); }
}
