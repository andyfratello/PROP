package main.domain.classes.types;

import main.domain.classes.functions.Interpret;
import main.domain.classes.functions.Funcio;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.IVisitor;
import main.domain.classes.Cela;
import main.domain.classes.Full;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa una referencia a una cel·la d'un full
 * @author Marc Duch Buechler
 */
public class Referencia implements IVisitable, Serializable
{
	/** Posicio a la que apunta la referencia */
	private Pair<Integer,Integer> _pos;
	/** Full al qual pertany la referencia */
	private Full _full;
	/** Conjunt de Referencies que apunten a aquesta */
	private final Set<Referencia> _referenciadaPer;
	
	// ---------- CONSTRUCTORA ----------

	/** Constructra de Referencia
	 *	@param pos Posició que es referencia
	 *	@param f Full al qual pertany la referencia
	 */
	public Referencia(Pair<Integer,Integer> pos, Full f){
		_pos = pos;
		_full = f;
		_referenciadaPer = new HashSet<>();
	}
	
	// ---------- GETTERS ----------
	/** @return Retorna la posicio a la qual apunta la referencia */
	public Pair<Integer,Integer> GetPos() { return _pos; }
	/** @return El valor real de la cela a la qual apunta la referencia */
	public String GetValorReal()	{ return getCela().GetValorReal(); }
	/** @return Retorna el tipus de la cela a la qual apunta la referencia */
	public TType GetTipus() 		{ return getCela().GetTipus(); }
	/** @return (TRUE) Si apunta a una cela que te una funcio */
	public boolean EsFuncio()		{ return getCela().EsFuncio(); }
	/** @return Retorna la funcio de la cela a la qual apunta la referencia
	 *  NULl Si no en te */
	public Funcio GetFuncio()		{ return getCela().GetFuncio(); }
	
	// ---------- IVISITABLE ----------

	/** Implementacio de la Interficie IVisitable
	 * @param visitor Visitor que vol afegir a Referencia
	 */
	public void accept(IVisitor visitor) { visitor.visit(this); }
	
	// ---------- ALTRES ----------
	/** Retorna la representacio com a string de la referencia
	 * @return Retorna els indexos en format string: A1 A3
	 */
	public String toString() { return Interpret.colToString(_pos.first()) + Integer.valueOf(_pos.second() + 1).toString(); }

	/** Afegeix una nova refrencia al conjunt de refrencies que referencien a this
	 * @param r Referencia que fa referencia a this
	 * @return (TRUE) Si es crea un cicle
	 */
	public boolean afegirReferenciada(Referencia r) {
		//System.out.println("[#REF]: " + r + " -+-> " + this);
		_referenciadaPer.add(r);
	 	return teCicle();
	}

	/** Eliminem un referencia del conjunt de referencies que referenciaven a "this"
	 * @param r Referencia que referenciava a 'this'
	 */
	public void eliminarRefrenciada(Referencia r) {	/*System.out.println("[#REF]: " + r + " -x-> " + this);*/	_referenciadaPer.remove(r);	}

	/** Comprova si la referencia forma un cicle
	 * @return (TRUE) Si es forma un cicle
	 */
	public boolean teCicle() { return  teCicle(new HashSet<>(), new HashSet<>()); }

	/** Funció per actualitzar el valor de una referencia i totes les que depenen d'ella
	 * @return Conjunt de referencies que s'han actualitzat
	 */
	public Set<Pair<Integer,Integer> > Update()
	{
		Set<Pair<Integer,Integer> > refsAfectades = new HashSet<>();
		_referenciadaPer.forEach(r -> {
			if (refsAfectades.add(r.GetPos()) && !r.teCicle() )	// Si no ha estat afectada, s'actualitza
				refsAfectades.addAll(r.Update());
		});
		return refsAfectades;
	}

	@Deprecated
	public Set<Referencia> GetReferenciadaPer() { return _referenciadaPer; }

	@Deprecated
	public void Delete()		// No utilitzat
	{
		_full = null;
		_pos = null;
		_referenciadaPer.clear();
	}

	/** Override de equals()
	 * @param o Objecte amb el que es compara
	 * @return (TRUE) Si l'altre objecte es una referencia amb la mateixa posició i full
	 */
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Referencia)) return false;
		Referencia other = (Referencia) o;
		return (this._full.GetID() == other._full.GetID() && this._pos.equals(other._pos));
	}
	
	//---------- PRIVATE ----------
	/** Comprova si r esta inclosa a alguna de les cel·les referenciades
	 * @param visited Conjunt de referencies visitades
	 * @param recursiveStack Conjunt de referències a la pila recursiva
	 * @return (TRUE) Si existeix un cicle
	 */
	private boolean teCicle(Set<Referencia> visited, Set<Referencia> recursiveStack)
	{
		if (visited.add(this)) /* this no esta a visited */
		{
			recursiveStack.add(this);
			for (Referencia r : _referenciadaPer) {
				if (!visited.contains(r) && r.teCicle(visited, recursiveStack)) return true;
				else if (recursiveStack.contains(r)) return true;
			}
		}
		recursiveStack.remove(this); // L'eliminem de la "pila" recursiva
		return false;
	}

	/** Agafa la referencia a la cel·la,
	 *  si no n'hi ha, en crea una temporal
	 * 	@return Cela a la posicio esperada o new Cela()
	 */
	private Cela getCela()
	{ 
		Cela c = _full.GetCela(_pos.first(), _pos.second());
		// Si no hi ha Cela en la posició referenciada, es crea una nova cel·la buida
		return c == null ? new Cela() : c; 
	}
}

