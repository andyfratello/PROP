package main.domain.classes;

import main.domain.classes.functions.Funcio;
import main.domain.classes.functions.Interpret;
import main.domain.classes.types.TType;
import main.domain.controllers.CtrlFuncions;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que representa una única cel·la d'un full de càlcul
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class Cela implements Comparable<Cela>, Serializable
{
	/** Guarda el valor introduit per l'usuari */
    private String _valorUsuari;

	/** Enum que determina el tipus del contingut real */
	private TType _tipusValorReal;
	
	/** Controlador de Funcions, necesari per poder crear referencies */
	private CtrlFuncions _cf;
	/** Boolean que determina si el contingut de la cel·la és una funció */
	private boolean _esFuncio;
	/** Funció continguda a la cel·la, si _esFuncio es fals, _funcio = NULL */
    private Funcio _funcio = null;

    // ---------- CONSTRUCTORES ----------
	/** Constructora per defecte de cel·la
	 *	Construeix una cel·la buida, no associada a cap full
 	 */
	public Cela()
    {
        _valorUsuari = "";
        _esFuncio = false;
		_funcio = null;
		_cf = null;
		_tipusValorReal = TType.STRING;
    }

	/** Constructora deprecada de cel·la, només existsteix per poder fer els tests
	 * @param valor Valor de l'usuari (no pot ser funció)
	 */
	@Deprecated
    public Cela(String valor) { this(valor, null, 0, 0); }

	/** Constructora per defecte de cel·la
	 * @param valor Valor de l'usuari
	 * @param cf Controlador de funcions al que pertany
	 */
    public Cela(String valor, CtrlFuncions cf, int columna, int fila)
    { 
		_cf = cf;
		this.ModificaValor(valor, columna, fila);
	}

	// ---------- GETTERS ----------
	/** Indica si la cel·la és una funció
	 * 	@return Boolean : Retornarà cert/fals si la cel·la és una funció */
	public boolean EsFuncio()	{ return _esFuncio; }
	/** Indica el tipus que represeta la cel·la
	 * 	@return TType */
	public TType GetTipus()		{ return _tipusValorReal; }
	/** Retorna la funció que hi te associada, o null si no en té
	 * 	@return Funcio */
	public Funcio GetFuncio()	{ return _funcio; }

	/** Retorna el valor introduit a la cel·la per l'usuari
	 * 	@return String : Valor introduit per l'usuari
	 */
    public String GetValorUsuari() { return _valorUsuari; }
		
	/**
	 * Retorna el valor "real" que conté la cel·la, en el cas d'una funció, aquest valor és el seu resultat
	 * @return Resultat de la funcio si en te si no, Valor Usuari
	 */
    public String GetValorReal() 
	{ 
		try { if (_esFuncio) return _funcio.GetResultat().toString(); }
		catch (Exception e) {
			System.err.println("[#CELL] " + _valorUsuari + ": " + e.getMessage() + " f: " + _funcio + " is? " + _esFuncio);
		}
		return _valorUsuari;
	}

	// ---------- SETTERS ----------
	/** Modifica el contingut de la cel·la, si es una funció, la crea.<p>
	 *  Actualitza les referencies de la funció anterior</p>
	 *  @param nValor : El nou valor de la cel·la
	 */
    public void ModificaValor(String nValor, int columna, int fila)
	{
		_valorUsuari = nValor;
		_tipusValorReal = Interpret.getType(nValor);
		_esFuncio = (_tipusValorReal == TType.FUNCTION);
		if (_cf == null) return;

		_funcio = _cf.GetFuncio(nValor, columna, fila);
		if (!_esFuncio) _funcio = null;
	}

	/** Esborra les referencies que te la Cela per facilitar el GC */
	public void delete()
	{
		_funcio = null;
		_cf = null;
	}
	/** Override del metode equals() de la classe Object
	 *  @param o : Object amb el que comparar
	 *  @return Boolean : Retorna true només si l'Objecte és el mateix
	 */
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Cela)) return false;
		Cela c = (Cela)o;
		return (c == this);
	}

	/** @return Retorna la representació toSring() de cela, el Valor de l'Usuari */
	@Override
	public String toString() {return _valorUsuari;}

	/** Compara dues cel·les de forma ASCENDENT
	 * 	Primer ordena alfabeticament, després numericament i finalment, per dates
	 * 	@param other Cela a la que comparar
	 * 	@return Int: -1 si es anterior, 0 si es igual, 1 si es posterior a other
	 */
	public int compareTo(Cela other)
	{
		String s1 = this.GetValorReal();
		String s2 = other.GetValorReal();

		TType t1 = Interpret.getType(s1);
		TType t2 = Interpret.getType(s2);

		int retorn = 0;
		if (t1.is(TType.NUMBER)){		// S1 es numero
			if (t2.is(TType.NUMBER)){
				Double d1 = Interpret.StringToNumber(s1).doubleValue();
				Double d2 = Interpret.StringToNumber(s2).doubleValue();
				retorn = d1.compareTo(d2);
			}
			else if (t2.is(TType.DATE))		retorn = -1;	// Numero abans que data
			else if (t2.is(TType.STRING))	retorn =  1;	// Numero despres de String
		}
		else if (t1.is(TType.DATE)){	// S1 es Data
			if (t2.is(TType.NUMBER))		retorn = 1;		// Data despres de Numero
			else if (t2.is(TType.DATE)) {
				LocalDate d1 = Interpret.StringToDate(s1);
				LocalDate d2 = Interpret.StringToDate(s2);
				retorn = d1.compareTo(d2);
			}
			else if (t2.is(TType.STRING))	retorn = 1;		// Data despres de String
		}
		else if (t1.is(TType.STRING)) {	// S1 es String
			if (t2.is(TType.NUMBER))		retorn = -1;	// String abans que numero
			else if (t2.is(TType.DATE))		retorn = -1;	// String abans que data
			else if (t2.is(TType.STRING))	retorn = s1.compareTo(s2);
		}

		return  Integer.compare(retorn, 0); // Fixa el valor de retorn entre -1 i 1
	/*
		int res = 0;

		String s1 = this.GetValorReal();
		String s2 = other.GetValorReal();

		if (Objects.equals(s1, s2)) return 0;

		try { return Double.compare(Double.parseDouble(s2), Double.parseDouble(s1)); }
		catch (Exception ignored) { }

		for (int i = 0; i < s1.length(); i++)
		{
			char c1 = s1.toLowerCase().charAt(i);
			if (i == s2.length()) return 1;
			char c2 = s2.toLowerCase().charAt(i);

			boolean c1Int = ( c1 >=48 && c1 <=57 );
			boolean c2Int = ( c2 >=48 && c2 <=57 );

			int cs = c1Int ? (c2Int ? 1 : 2 ) : (c2Int ? 3 : 4 );
			switch (cs){
				case 1:	// Int Int			s1 11 s2 10 -1
					if (c1 == c2) break;
					else return (c1 < c2) ? 1 : -1;
				case 2:	// Int String		s1 10 s2 1.0 -1
					if (c2 == '.') return -1;
					return 1;
				case 3: // String Int		s1 1.0 s2 10 1
					if (c1 == '.') return 1;
					return -1;
				case 4: // String String
					if (c1 == c2) break;
					else return (c1 < c2) ? 1 : -1;	// s1 a s2 b (a < b) 1
			}
			if (s1.length() < s2.length()) return -1;
		}
		return res;
	*/
	}
}
