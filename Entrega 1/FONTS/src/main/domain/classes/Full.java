package main.domain.classes;

import java.util.*;
import java.io.*;	// Temporal, pel metode PrintFull

import java.lang.IndexOutOfBoundsException;
import java.lang.NegativeArraySizeException;

import main.domain.classes.types.Pair;
import main.domain.classes.Cela;
//import main.domain.classes.functions.Interpret;

/** Classe contenidora del Cel·les
 *  Representa un full de càlcul
 *  @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class Full implements Serializable {
	/** Identificador intern del Full */
	private int _id;
	/** Nom del Full */
	private String _nomFull;
	/** Numero de columnes del Full */
	private int _nCol;
	/** Numero de files del Full */
	private int _nFil;
	
	/** Ultima fila amb una cel·la */
	private int _maxFila;       
	/** Ultima columna amb una cel·la */
	private int _maxColumna;

	/**
	 * Estructura de dades per guardar el conjunt de cel·les amb un valor no-null
	 * Es un diccionari aniuat en un altre diccionari, on les claus del primer diccionari representen les columnes
	 * i les claus del diccionari aniuat representen les files per la columna a la que pertanyen.
	 * Només es guarden les cel·les que contenen un string no null;
	 */
	private SortedMap<Integer, SortedMap<Integer,Cela> > _cjtCel;

	// -------- Constructores --------
	public Full(int id) { this(id, new String("Full " + id), 50, 100); }
	public Full(int id, int columnes, int files) { this(id,new String ("Full " + id ), columnes, files); }

	/**
	 *  Crea una instancia de Full
	 * @param id Identificador del full
	 * @param nom Nom del full
	 * @param columnes Numero de columnes
	 * @param files Numero de files
	 * @throws NegativeArraySizeException Salta si columnes o files son negatius
	 */
	public Full(int id, String nom, int columnes, int files) throws NegativeArraySizeException
	{
		if (columnes < 0 || files < 0) throw new NegativeArraySizeException("Columna/Fila han de ser >= 0");
		_id         = id;
		_nomFull    = nom;
		_nCol       = columnes;
		_nFil       = files;
		_maxFila    = 0;
		_maxColumna = 0;
		_cjtCel     = new TreeMap<Integer,SortedMap<Integer,Cela>>(Collections.reverseOrder());
	}

	// -------- Getters --------
	public int GetID() { return _id;}
	public String GetNomFull() { return _nomFull;}
	public int GetNumCol() { return _nCol; }
	public int GetNumFil() { return _nFil; }
	
	/**
	 * Donades una columna i una fila, retorna la cela corresponent o null si no n'hi ha cap
	 * @param columna Enter positiu que indica la columna començant per 0
	 * @param fila Enter positiu que indica la fila començant per 0
	 * @exception IndexOutOfBoundsException Un dels indexs esta fora del Full
	 */
	public Cela GetCela(int columna, int fila) throws IndexOutOfBoundsException
	{
		if(!indexosCorrectes(columna, fila)) throw new IndexOutOfBoundsException("Cel·la no esta inclosa al Full");

		if(!_cjtCel.containsKey(columna)) return null;
		return _cjtCel.get(columna).get(fila);
	}

	// -------- Setters --------

	/**
	 * Canvia el nom del Full i retorna el nom anterior
	 * @param nom Nou nom pel Full
	 * @return String: Nom anterior al canvi
	 */
	public String SetNomFull(String nom)
	{
		String old = _nomFull;
		_nomFull = nom;
		return old;
	}

	/**
	 * Metode principal per accedir a les cel·les del Full
	 * @param col identificador de la columna
	 * @param fila identificador de la fila
	 * @param valor String que es vol suplantar a la cel·la especificada
	 * @return Boolean: Retorna TRUE si la cel·la ja existia i s'ha matxacat el valor anterior
	 * @exception IndexOutOfBoundsException Un dels indexs esta fora del Full
	 */
	public boolean ModificaCela(int col, int fila, String valorU) { return ModificaCela(col,fila,valorU,valorU); }
	// TODO deprecar metode amb valorR (2na Entrega)
	public boolean ModificaCela(int col, int fila, String valorU, String valorR) throws IndexOutOfBoundsException
	{
		if (!indexosCorrectes(col, fila)) throw new IndexOutOfBoundsException("Cel·la no esta inclosa al Full");

		boolean celaMachacada = false;

		if (valorU == "")	// Eliminar cel·la
		{
			if (!_cjtCel.containsKey(col)) return false;

			celaMachacada = _cjtCel.get(col).containsKey(fila);

			if(celaMachacada) {
				_cjtCel.get(col).remove(fila);
				if(_cjtCel.get(col).size() == 0) _cjtCel.remove(col);
			}
			return celaMachacada;
		}



		if(!_cjtCel.containsKey(col)) _cjtCel.put(col, new TreeMap<Integer,Cela>(Collections.reverseOrder()) );

		celaMachacada = _cjtCel.get(col).containsKey(fila);

		if (celaMachacada)
		{
			Cela c = _cjtCel.get(col).get(fila);
			c.ModificaValor(valorU);
			c.SetValorReal(valorR);
		}
		else
		{
			Cela c = new Cela(valorU);
			c.SetValorReal(valorR);
			_cjtCel.get(col).put(fila, c);
		}

		if (fila > _maxFila) _maxFila = fila;
		if (col > _maxColumna) _maxColumna = col;

		return celaMachacada;
	}

	// -------- Operacions sobre files --------    
	/**
	 * Afegir una fila buida en una posició donada, si ja hi havia una fila, aquesta i les seguents seran desplaçades
	 * @param pos Index de la fila on volem afegir la nova fila
	 * @exception IndexOutOfBoundsException L'index pos esta fora del Full
	 */
	public void AfegirFila(int pos) throws IndexOutOfBoundsException
	{
		_nFil++;
		if(!indexFilaCorrecte(pos)) throw new IndexOutOfBoundsException("Fila no inclosa al Full");

		if (pos > _maxFila) return;
		_maxFila++;

		//Si la fila desplaça celes
		Set<Map.Entry<Integer,SortedMap<Integer,Cela>> > columnes = _cjtCel.entrySet();

		columnes.forEach(col -> { shiftFiles(col.getValue(), pos, false); });

		return;
	}

	/**
	 * Elmina la fila especificada, les files posteriors es mouen per tal de mantenir la seva posició relativa
	 * @param pos Index de la fila que volem eliminar
	 * @exception IndexOutOfBoundsException L'index pos esta fora del Full
	 */
	public void EliminarFila(int pos) throws IndexOutOfBoundsException
	{
		if (!indexFilaCorrecte(pos)) throw new IndexOutOfBoundsException("Fila no inclosa al Full");
		_nFil--;
		if (pos >= _maxFila) return;
		_maxFila--;

		//Si la fila desplaça celes
		Set<Map.Entry<Integer,SortedMap<Integer,Cela>> > columnes = _cjtCel.entrySet();

		columnes.forEach(col -> { shiftFiles(col.getValue(), pos, true); });

		return;
	}

	// -------- Operacions sobre columnes --------
	/**
	 * Afegeix una columna a la posició indicada, les columnes posterios es mouran per fer espai
	 * @param pos Index de la columna que volem afegir
	 * @exception IndexOutOfBoundsException L'index pos esta fora del Full
	 */
	public void AfegirCol(int pos) throws IndexOutOfBoundsException
	{
		_nCol++;
		if (!indexColsCorrecte(pos)) throw new IndexOutOfBoundsException("Columna no inclosa al Full");
		if (pos > _maxColumna) return;
		shiftCols(pos, false);
		return;
	}

	/**
	 * Elimina la columna indicada, les columnes posteriors es mouen per ocupar el la columna eliminada
	 * @param pos Index de la columna que volem eliminar
	 * @exception IndexOutOfBoundsException L'index pos esta fora del Full
	 */
	public void EliminarColumna(int pos) throws IndexOutOfBoundsException
	{
		if(!indexColsCorrecte(pos)) throw new IndexOutOfBoundsException("Columna no inclosa al Full");
		_nCol--;
		if(pos >= _maxColumna) return;
		shiftCols(pos, true);
		return;
	}

	/**
	 * Ordena per columnes, les cel·les que es troben entre els indexos especificats (inclosos)
	 * @param cel1 Pair(int Columna,int Fila) que determina la primera cantonada d'un area rectangular
	 * @param cel2 Pair(int Columna,int Fila) que determina la segona cantonada de l'area quadrada
	 * @param ascendent Boolean que determina si les cel·les s'ordenaran de forma ascendent o descendent
	 * @exception IndexOutOfBoundsException Un dels indexs esta fora del Full
	 */
	public void OrdenarPerColumnes(Pair<Integer,Integer> cel1, Pair<Integer, Integer> cel2, boolean ascendent) throws IndexOutOfBoundsException
	{
		if (!indexosCorrectes(cel1.first(), cel1.second())) throw new IndexOutOfBoundsException("La primera cel·la no esta inclosa al Full");
		if (!indexosCorrectes(cel2.first(), cel2.second())) throw new IndexOutOfBoundsException("La segona cel·la no esta inclosa al Full");
		
		int fCol = Integer.min(cel1.first(), cel2.first());
		int lCol = Integer.max(cel1.first(), cel2.first());

		int fFil = Integer.min(cel1.second(), cel2.second());
		int lFil = Integer.max(cel1.second(), cel2.second());

		for (int i = fCol; i <= lCol; i++)
		{
			SortedMap<Integer,Cela> subColumna = _cjtCel.get(i);
			if (subColumna != null){
				Vector<Cela> celesCol = new Vector<Cela>();
				for (int j = fFil; j <= lFil; j++)
				{
					Cela oldC = subColumna.remove(j);
					if (oldC != null) celesCol.add(oldC);
				}

				if (celesCol.size() != 0) {
					insertionSort(celesCol, ascendent);
					for (int j = 0; j < celesCol.size(); j++){ subColumna.put(fFil + j, celesCol.get(j)); }
				}

			}
		}
	}

	/**
	 * Busca totes les cel·les del full que continguin part del valor indicat
	 * @param valor String que volem buscar al full
	 * @return Set(Pair( Pair(int Columna,int Fila), Cela) ): Conjunt de tuples de fila, columna i cel·la associada, fila i columna estan agrupades en un Pair<Integer,Integer>
	 * NOTE: El valor de retorn podria ser el seu propi objecte ("struct" amb fila, columna i ce·la)
	 */
	public Set<Pair<Pair<Integer,Integer> , Cela> > CercarValor(String valor)
	{
		Set<Pair <Pair<Integer, Integer> ,Cela> > celesTrobades = new HashSet<Pair<Pair<Integer, Integer> ,Cela> >();

		_cjtCel.entrySet().forEach(col -> {
			SortedMap<Integer,Cela> celesDeCol = col.getValue();
			for (Map.Entry<Integer,Cela> cela: celesDeCol.entrySet())
			{
				if (cela.getValue().GetValorUsuari().contains(valor) )
				{
					// WARNING amb opcio -Xlint: Diu que l'element que s'espera el metode E es de tipus Object
					celesTrobades.add(new Pair(new Pair(col.getKey(), cela.getKey() ), cela.getValue()));
				}
			}
		});
		return celesTrobades;
	}

	// ---------- PRIVATES ----------
	private boolean indexosCorrectes(int columna, int fila) throws IndexOutOfBoundsException
	{
		boolean res = (indexFilaCorrecte(fila) && indexColsCorrecte(columna));
		if (!res) throw new IndexOutOfBoundsException("Columna/Fila no incloses a Full");
		return res;
	}
	private boolean indexFilaCorrecte (int fila) throws IndexOutOfBoundsException
	{
		boolean res = (fila >= 0 && fila < _nFil);
		if (!res) throw new IndexOutOfBoundsException("Fila no inclosa a Full");
		return res;
	}
	private boolean indexColsCorrecte (int colu) throws IndexOutOfBoundsException
	{
		boolean res = (colu >= 0 && colu < _nCol);
		if (!res) throw new IndexOutOfBoundsException("Columna no inclosa a Full");
		return res;
	}

	/**
	 * Insertion sort que ordena un vector de Cel·les de forma ascendent o descendent
	 * Descendent: Primer alfabeticament (Strings Z > A) i despres numericament (Doubles 10 > 1)
	 * Ascendent: primer numericament (Doubles 1 < 10) i despres alfabeticament (Strings A < Z)
	 * @param v Vector<Cela> vector a ordenar
	 * @param ascendent Mode d'ordenació, ascendent (TRUE) o descendent (FALSE)
	 */
	private void insertionSort(Vector<Cela> v, boolean ascendent)
	{
		int order = ascendent ? 1 : -1;
		int n = v.size();
		for(int i = 1; i < n; i++)   {
			Cela temp = v.get(i);
			int j = i - 1;
			while(j >= 0 && temp.compareTo(v.get(j)) == order)   {
				v.set(j + 1, v.get(j));
				j = j - 1;
			}
			v.set(j + 1, temp);
		}
	}
	
	/**
	 * Desplaça les columnes posteriors a la posició indicada segons si dita columna s'afegeix o s'elimina
	 * @param pos : Index de la columna que es vol eliminar/afegir
	 * @param eliminar : Boolean que determina si es vol eliminar (true) o afegir (false)
	 */
	private void shiftCols (int pos, boolean eliminar)
	{
		int offset;
		if (eliminar) offset = -1;
		else offset = 1;

		//Iterador amb tots els parells K - V de les columnes amb claus mes petites que pos (Tot i que headmap es al reves??)
		Iterator<Map.Entry<Integer,SortedMap<Integer,Cela> > > iterator = _cjtCel.headMap(pos - 1).entrySet().iterator();
		Set<Pair<Integer, SortedMap<Integer,Cela> >> tmp = new HashSet<Pair<Integer, SortedMap<Integer,Cela> > >();	//Columnes a re-insertar

		while (iterator.hasNext() )
		{
			Map.Entry<Integer ,SortedMap<Integer,Cela> > columna = iterator.next();
			if (!eliminar || pos != columna.getKey()) tmp.add(new Pair(columna.getKey() + offset, columna.getValue() ));
			iterator.remove();
		}
		
		Iterator<Pair<Integer, SortedMap<Integer, Cela> >> iteratorTmp = tmp.iterator();
		
		while (iteratorTmp.hasNext())
		{
			Pair<Integer, SortedMap<Integer, Cela> > nCol = iteratorTmp.next();
			_cjtCel.put(nCol.first(), nCol.second());
		}
	}

	/**
	 * Donat un diccionari de files (representa una sola columna) i una posicio, les files
	 * es mouen segons si s'ha afegit una fila, o s'ha eliminat
	 * @param col : diccionari de cel·les pertenyents a una columna (les claus son les files)
	 * @param pos : fila que es decideix eliminar/afegir
	 * @param eliminar : Boolean que determina si es vol eliminar (true) o afegir (false)
	 */
	private void shiftFiles (SortedMap<Integer,Cela> col, int pos, boolean eliminar)
	{
		int offset;
		if (eliminar)   offset = -1;
		else            offset =  1;

		// Iterador amb tots el parells K-V de una columna amb claus poteriors a "pos"
		// TOT I QUE HEADMAP EN TEORIA ES LES ANTERIORS????
		Iterator<Map.Entry<Integer,Cela> > iterator = col.headMap(pos - 1).entrySet().iterator();
		Set<Pair<Integer,Cela>> tmp = new HashSet<Pair<Integer,Cela>>();  // Cel·les a re-insertar

		while (iterator.hasNext())
		{
			Map.Entry<Integer,Cela> filaCela = iterator.next();
			// Aquest if nomes sera fals si es vol eliminar I la posicio es la mateixa
			if (!eliminar || pos != filaCela.getKey()) tmp.add(new Pair<Integer,Cela>(filaCela.getKey() + offset, filaCela.getValue() ) );
			iterator.remove();
		}

		Iterator<Pair<Integer,Cela> > iteratorTmp = tmp.iterator();

		while (iteratorTmp.hasNext())
		{
			Pair<Integer,Cela> nCela = iteratorTmp.next();
			col.put(nCela.first(), nCela.second());
		}
	}

	// Metode temporal per testeig/drivers
	public void PrintFull()
	{
		for (int i = 0; i < _nFil; i++)
		{
			for (int j = 0; j < _nCol; j++)
			{
				if(j == 0) System.out.print("|");
				if (_cjtCel.containsKey(j) && _cjtCel.get(j).containsKey(i)) System.out.print( _cjtCel.get(j).get(i).GetValorReal() );
				else System.out.print("  ");
				System.out.print("|");
			}
			System.out.println();
		}
	}
}
