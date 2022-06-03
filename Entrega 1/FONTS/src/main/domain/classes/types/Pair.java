package main.domain.classes.types;


/**
 * Classe generica per crear tuples
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class Pair<F extends Object,S extends Object>
{
	private F _first;
	private S _second;

	public Pair(F first, S second)
	{
		_first = first;
		_second = second;
	}

	public void setFirst(F nFirst) 		{_first = nFirst;	}
	public void setSecond(S nSecond)	{_second = nSecond;	}

	public F first()	{ return _first;	}
	public S second() 	{ return _second;	}

	@Override
	public boolean equals(Object o){
		if (!(o instanceof Pair)) return false;
		Pair p = (Pair)o;
		return (_first.equals(p.first()) && _second.equals(p.second()) );
	}

	@Override
	public String toString() {return new String(_first.toString() + " | " + _second.toString()); }

}
