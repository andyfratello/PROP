package main.domain.classes;

import java.io.*;
import java.lang.String;
import java.lang.Comparable;
import main.domain.classes.types.TType;

//import main.domain.classes.functions.Interpret;


/**
 * Classe que representa una única cel·la d'un full de càlcul
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class Cela implements Comparable<Cela>, Serializable
{
	/** Guarda el valor introduit per l'usuari */
    private String _valorUsuari;

	/** Boolean que determina si el contingut de la cel·la és una funció */
	private boolean _esFuncio;
	
	/**
	 * Funció de la cel·la, si _esFuncio es false, serà null
	 * Per ara, _funcio té el resultat de la funcio
	 */
    private String _funcio;

    //TODO: (2 entrega) Colors de les celles

    // ---------- CONSTRUCTORES ----------
    public Cela()
    {
        _valorUsuari = new String();		
		_esFuncio = false;
		_funcio = null;
    }

    public Cela(String valor) { _esFuncio = false; this.ModificaValor(valor); }

	// ---------- GETTERS ----------
	/**
	 * Indica si la cel·la és una funció
	 * @return Boolean : Retornarà cert/fals si la cel·la és una funció
     */
	public boolean EsFuncio() { return _esFuncio; }

	/**
	 * Retorna el valor introduit a la cel·la per l'usuari
	 * @return String : Valor introduit per l'usuari
	 */
    public String GetValorUsuari() { return _valorUsuari; }
		
	/**
	 * Retorna el valor "real" que conté la cel·la, en el cas d'una funció, aquest valor és el seu resultat
	 * Funció usada principalment pel Parser, per determinar els parametres de les funcions
	 * @return String : Valor utilitzat per les funcions
	 */
    public String GetValorReal() 
	{ 
		if (_esFuncio) return _funcio;
		return _valorUsuari; 
	}

	// ---------- SETTERS ----------
	/**
	 *  Modifica el contingut de la cel·la
	 *  @param nValor : El nou valor de la cel·la
	 *
	*/
    public void ModificaValor(String nValor)
	{
		_valorUsuari = nValor;

		/*					2na ENTREGA
		// Temporal, fins que Interpret compili
        _esFuncio = (Interpret.getType(nValor) == TType.FUNCTION);		
		//	Falta determinar si el parser retorna un objecte de tipus Funcio
		if (_esFuncio)
        {
			_funcio = Interpret.callFunction(nValor);
		}
		else {
			_funcio = null;
		}
		*/
	}

	public void SetValorReal(String valor)
	{
		_esFuncio = true;
		_funcio = valor;
	}

	/**
	 * Override del metode equals() de la classe Object
	 * @param o : Object amb el que comparar
	 * @return Boolean : Retorna true només si el objecte es el mateix
	 */
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Cela)) return false;
		if (o == this) return true;
		
		return false;
	}

	@Override
	public String toString() {return _valorUsuari;}

	// Descendent: Primer alfabeticament (Strings z > A) i despres numericament (Doubles 10 > 1)

	/**
	 * Compara dues cel·les de forma descendent caracter a caracter
	 *
	 * @param other
	 * @return Int: -1 si This es anterior, 0 si es igual, 1 si This es posterior s1 de s2
	 */

	public int compareTo(Cela other)
	{
		int res = 0;

		String s1 = this.GetValorReal();
		String s2 = other.GetValorReal();

		if (s1 == s2) return 0;

		for (int i = 0; i < s1.length(); i++)
		{
			char c1 = s1.toLowerCase().charAt(i);
			if (i == s2.length()) return 1;
			char c2 = s2.toLowerCase().charAt(i);

			boolean c1Int = ( c1 <=48 && c1 <=57 );
			boolean c2Int = ( c2 <=48 && c2 <=57 );

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
	}

/*
	public Funcio GetFuncio() { return _funcio; }
	
	public void setColor(color)
	{
	}
*/
}
