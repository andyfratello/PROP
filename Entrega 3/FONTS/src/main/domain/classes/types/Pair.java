package main.domain.classes.types;

import java.io.Serializable;

/**
 * Classe generica per crear tuples
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class Pair<F extends Object,S extends Object> implements Serializable
{
	private F _first;
	private S _second;

	/** Constructora
	 *	@param first Primer element
	 *  @param second Segon element
	 */
	public Pair(F first, S second)
	{
		_first = first;
		_second = second;
	}

	/** Modifica el primer element
	 *  @param nFirst Nou valor pel primer element */
	public void setFirst(F nFirst) 		{_first = nFirst;	}
	/** Modifica el segon element
	 *  @param nSecond Nou valor pel segon element */
	public void setSecond(S nSecond)	{_second = nSecond;	}

	/** @return El valor del primer element */
	public F first()	{ return _first;	}
	/** @return El valor del segon element */
	public S second() 	{ return _second;	}

	/** Comparador entre pairs
	 * @param o Objecte
	 * @return (TRUE) Si o es un pair
	 */
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Pair)) return false;
		Pair<F,S> p = (Pair<F,S>)o;
		return (_first.equals(p.first()) && _second.equals(p.second()) );
	}

	/** Representacio d'un parell com a String
	 *  @return La representacio de cada element com a string separat per un espai
	 */
	@Override
	public String toString() {return _first.toString() + " " + _second.toString(); }

}
