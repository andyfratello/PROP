package main.domain.classes.token; 

import java.time.LocalDate;

/** Token que representa un error en el moment de parsejar/crear una funcio
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class ErrorT extends Token
{
	private final String _token;

	/** Constructora
	 * @param s Error a guardar al Token
	 */
	public ErrorT(String s) { _token = s; }

	/** Llença una excepció amb el missatge que s'ha creat a l'hora d'interpretar-se com a Number
	 * @throws RuntimeException Error guardat al crear-se */
	public Number asNumber() throws RuntimeException	{ throw new RuntimeException(_token); }

	/** Llença una excepció amb el missatge que s'ha creat a l'hora d'interpretar-se com a LocalDate
	 *  @throws RuntimeException Error guardat al crear-se
	 */
	public LocalDate asDate() throws RuntimeException	{ throw new RuntimeException(_token); }

	/** @return El missatge que l'ha creat */
	public String asString()	{ return _token; }

	/** @return El missatge que l'ha creat */
	@Override
	public String toString() { return _token; }

}
