package main.domain.classes.types;

import main.domain.classes.functions.Interpret;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.IVisitor;
import main.domain.classes.Full;
import java.io.Serializable;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;

/**
 * Classe que representa un bloc de referencies d'un full
 * 
 * @author Marc Duch Buechler
 */
public class BlocReferencies implements IVisitable, Serializable
{
	/** Posició mínima (cantonada dalt/dreta) */
	private Pair<Integer,Integer> _min;
	/** Posició màxima (cantonada baix/esquerra) */
	private Pair<Integer,Integer> _max;
	/** Full al que pertany el bloc */
	private Full _full;
	/** Conjunt de referencies del bloc */
	List<Referencia> _bloc;

	// ---------- CONSTRUCTORA ----------
	public BlocReferencies(Pair<Integer,Integer> pos1, Pair<Integer,Integer> pos2, Full f){
		int fCol = Integer.min(pos1.first(), pos2.first());
		int fFil = Integer.min(pos1.second(), pos2.second());

		int lCol = Integer.max(pos1.first(), pos2.first());
		int lFil = Integer.max(pos1.second(), pos2.second());
		
		_min = new Pair<>(fCol, fFil);
		_max = new Pair<>(lCol, lFil);

		_full = f;
		referencies();
	}
	
	// ---------- SETTERS ----------
	
	// Set pos / update pos ???
	
	// ---------- GETTERS ----------

	/** @return Pair(columna,fila) minima */
	public Pair<Integer,Integer> GetMin() { return _min; }
	/** @return Pair(columna,fila) maxima */
	public Pair<Integer,Integer> GetMax() { return _max; }
	
	/** @return Numero de referencies del bloc */
	public int GetSize() { return (_max.first() - _min.first() + 1) * (_max.second() - _min.second() + 1); }
	
	/**	
	 *	Retorna una llista amb totes les referencies en l'area formada per _min/_max
	 *	@return Llista de referencies contingudes al bloc
	 */
	public List<Referencia> GetReferencies() { return _bloc; }

	/** Protected per implementar stub */
	protected void referencies(){
		_bloc = new ArrayList<>();

		for (int c = _min.first(); c <= _max.first(); c++) { // Iterem per columnes
			for(int f = _min.second(); f <= _max.second(); f++) { // Iterem per files
				_bloc.add(_full.GetReferencia(c,f) );
			}
		}
	}
	
	// ---------- IVISITABLE ----------

	/** Implementacio del patro
	 * @param visitor Funció en la que participa (com a parametre)
	 */
	public void accept(IVisitor visitor) { visitor.visit(this); }
	
	// ---------- ALTRES ----------
	
	/** Retorna els indexos en format string: ex "A1:A3" 
	 *	@return String en format AAA111:BBB222
	 */
	public String toString() { return pairToString(_min) + ":" + pairToString(_max); }
	

	// Potser innecesari?
	public void Delete()
	{
		_full = null;
		_max = null;
		_min = null;
	}
	
	//---------- PRIVATE ----------
	private String pairToString(Pair<Integer,Integer> pos) { return Interpret.colToString(pos.first()) + Integer.valueOf(pos.second() + 1).toString(); }
}

