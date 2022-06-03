package main.domain.classes;

import main.domain.classes.functions.Interpret;
import main.domain.classes.types.Referencia;
import main.domain.controllers.CtrlFuncions;
import main.domain.classes.types.Pair;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


/** Classe contenidora del Cel·les
 *  Representa un full de càlcul
 *  @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class Full implements Serializable {
	/** Identificador intern del Full */
	private final int _id;
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
	/** TreeMap que conté totes les referencies a les cel·les del full, TreeMap no permet nulls com a claus o valors */
	private final TreeMap<Pair<Integer,Integer>, Referencia> _refs;
	/** Controlador de funcions del full */
	private final CtrlFuncions _cf;	// CtrlFuncions depen de un full (el que te les referencies)
	
	/**
	 * Estructura de dades per guardar el conjunt de cel·les amb un valor no-null
	 * Es un diccionari aniuat en un altre diccionari, on les claus del primer diccionari representen les columnes
	 * i les claus del diccionari aniuat representen les files per la columna a la qual pertanyen.
	 * Només es guarden les cel·les que contenen un string no null;
	 */
	private final SortedMap<Integer, SortedMap<Integer,Cela> > _cjtCel;

	// -------- Constructores --------

	/** Constructora amb un parametre
	 *  Crea un full amb la id indicada de 10c x 15f amb nom "Full id"
	 * 	@param id Identificador del full
	 *  @throws NegativeArraySizeException Salta si columnes o files son negatius*/
	public Full(int id) { this(id, "Full " + id, 10, 15); }

	/** Constructora amb un tres parametres
	 *  Crea un full amb la id indicada, les dimensions passades com a parametres i amb nom "Full id"
	 *  @param id Identificador del full
	 *  @param columnes Numero de columnes
	 *  @param files Numero de files
	 * 	@throws NegativeArraySizeException Salta si columnes o files son negatius */
	public Full(int id, int columnes, int files) { this(id,"Full " + id, columnes, files); }

	/** Crea una instancia de Full
	 *	@param id Identificador del full
	 *	@param nom Nom del full
	 * 	@param columnes Numero de columnes
	 * 	@param files Numero de files
	 * 	@throws NegativeArraySizeException Salta si columnes o files son negatius
	 */
	public Full(int id, String nom, int columnes, int files) throws NegativeArraySizeException
	{
		if (columnes < 0 || files < 0) throw new NegativeArraySizeException("Columna/Fila han de ser >= 0");
		_id			= id;
		_nomFull	= nom;
		_nCol 		= columnes;
		_nFil		= files;
		_maxFila	= 0;
		_maxColumna = 0;
		_cf 		= new CtrlFuncions(this);
		_cjtCel		= new TreeMap<> (Collections.reverseOrder());
		_refs 		= new TreeMap<> (new SerializablePairComparator());

	}

	// -------- Getters --------
	/** @return ID del full */
	public int GetID() { return _id;}
	/** @return Nom del full */
	public String GetNomFull() { return _nomFull;}
	/** @return Numero de columnes del full */
	public int GetNumCol() { return _nCol; }
	/** @return Numero de files del full */
	public int GetNumFil() { return _nFil; }
	/** @return Últim index de columna amb una cel·la */
	public int GetMaxCol() { return _maxColumna; }
	/** @return Últim index de fila amb una cel·la */
	public int GetMaxFil() { return _maxFila; }

	/**
	 *	Donades una columna i una fila, retorna la cela corresponent o null si no n'hi ha cap
	 * 	@param columna Enter positiu que indica la columna començant per 0
	 * 	@param fila Enter positiu que indica la fila començant per 0
	 *  @return Retorna la cel·la guardada a la posició indicada, null si no n'hi ha
	 *  @throws IndexOutOfBoundsException <p>
	 *  [#FULL] Fila no inclosa a Full </p>
	 *  [#FULL] Columna no inclosa a Full
	 */
	public Cela GetCela(int columna, int fila) throws IndexOutOfBoundsException
	{
		indexosCorrectes(columna, fila);

		if(!_cjtCel.containsKey(columna)) return null;
		return _cjtCel.get(columna).get(fila);
	}

	/** Retorna un mapa on les claus son la posició de la cela, el valor es una parella strings, el primer representa el valor d'usuari i el segon el real
	 * @return Map( K=(columna,fila), V=(vUsuari,vReal) )
	 */
	public Map<Pair<Integer,Integer>, Pair<String,String> > GetCeles() {
		Map<Pair<Integer,Integer>, Pair<String,String> > res = new HashMap<>();
		_cjtCel.forEach((idCol, columna) -> columna.forEach((idFila, cela) -> res.put(new Pair<>(idCol,idFila), new Pair<>(cela.GetValorUsuari(), cela.GetValorReal()) ) ));
		return res;
	}

	/** Retorna la referecia a una cel·la del full
	 * 	Si no exsisteix, la crea
	 * 	@param columna Columna de la refrencia
	 * 	@param fila Fila de la referencia
	 * 	@return Referencia que apunta a (columna, fila)
	 * 	@throws IndexOutOfBoundsException <p>
	 * 	[#FULL] Fila no inclosa a Full </p>
	 * 	[#FULL] Columna no inclosa a Full
	 */
	public Referencia GetReferencia(int columna, int fila)
	{
		indexosCorrectes(columna, fila);
		Pair<Integer,Integer> pos = new Pair<>(columna,fila);

		if (_refs.containsKey(pos) ) return _refs.get(pos);

		Referencia r = new Referencia(pos, this);
		_refs.put(pos, r);
		return r;
	}

	/** Retorna la referecia a una cel·la del full
	 *  @param p Pair(columna,fila)
	 *	@return Referencia que apunta a (columna, fila)
	 *  @see Full#GetReferencia(int,int)  */
	public Referencia GetReferencia(Pair<Integer,Integer> p) { return GetReferencia(p.first(), p.second()); }
	// -------- Setters --------
	/**
	 * Canvia el nom del Full i retorna el nom anterior
	 * @param nom Nou nom pel Full
	 * @return Nom anterior al canvi
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
	 *  @throws IndexOutOfBoundsException <p>
	 *  [#FULL] Fila no inclosa a Full </p>
	 *  [#FULL] Columna no inclosa a Full
	 */
	public boolean ModificaCela(int col, int fila, String valor) throws IndexOutOfBoundsException
	{
		indexosCorrectes(col, fila);

		if (valor.equals("") ) return eliminaCela(col, fila);	// S'indica que es vol eliminar una cel·la
		
		if(!_cjtCel.containsKey(col)) _cjtCel.put(col, new TreeMap<>(Collections.reverseOrder()) );

		boolean celaMachacada = _cjtCel.get(col).containsKey(fila);

		if (celaMachacada) _cjtCel.get(col).get(fila).ModificaValor(valor,col,fila);	// Si exsisteix es modifica
		else _cjtCel.get(col).put(fila, new Cela(valor, _cf, col, fila));				// Else, es crea una nova

		_maxFila = Math.max(fila, _maxFila);
		_maxColumna = Math.max(col, _maxColumna);
		/*
		System.out.print("[#FULL] Cel·les afectades per la modificació: " );
		GetReferencia(col,fila).Update().forEach(p -> System.out.print("|" + Interpret.colToString(p.first()) + Integer.valueOf(p.second() + 1).toString()));
		System.out.println();
		*/
		return celaMachacada;
	}

	// -------- Operacions sobre files --------    
	/**
	 * Afegir una fila buida en una posició donada, si ja hi havia una fila, aquesta i les seguents seran desplaçades
	 * @param pos Index de la fila on volem afegir la nova fila
	 * @exception IndexOutOfBoundsException [#FULL] Fila no inclosa a Full
	 */
	public void AfegirFila(int pos) throws IndexOutOfBoundsException
	{
		_nFil++;
		indexFilaCorrecte(pos);

		if (pos > _maxFila) return;
		//Si la fila desplaça celes
		_maxFila++;
		_cjtCel.forEach((iCol, columna) -> shiftFiles(columna, pos, false));
	}

	/**
	 * Elmina la fila especificada, les files posteriors es mouen per tal de mantenir la seva posició relativa
	 * @param pos Index de la fila que volem eliminar
	 *  @throws IndexOutOfBoundsException [#FULL] Fila no inclosa a Full
	 */
	public void EliminarFila(int pos) throws IndexOutOfBoundsException
	{
		indexFilaCorrecte(pos);
		_nFil--;
		if (pos >= _maxFila) return;
		//Si la fila desplaça celes
		_maxFila--;
		_cjtCel.forEach((iCol, columna) -> shiftFiles(columna, pos, true));
	}

	// -------- Operacions sobre columnes --------
	/**
	 * Afegeix una columna a la posició indicada, les columnes posterios es mouran per fer espai
	 * @param pos Index de la columna que volem afegir
	 * @exception IndexOutOfBoundsException [#FULL] Columna no inclosa a Full
	 */
	public void AfegirCol(int pos)
	{
		_nCol++;
		indexColsCorrecte(pos);
		if (pos > _maxColumna) return;
		shiftCols(pos, false);
	}

	/**
	 * Elimina la columna indicada, les columnes posteriors es mouen per ocupar el la columna eliminada
	 * @param pos Index de la columna que volem eliminar
	 * @exception IndexOutOfBoundsException [#FULL] Columna no inclosa a Full
	 */
	public void EliminarColumna(int pos) throws IndexOutOfBoundsException
	{
		indexColsCorrecte(pos);
		_nCol--;
		if(pos >= _maxColumna) return;
		shiftCols(pos, true);
	}

	/**
	 * 	Ordena per columnes, les cel·les que es troben entre els indexos especificats (inclosos)
	 * 	@param cel1 Pair(Columna,Fila) que determina la primera cantonada d'un area rectangular
	 * 	@param cel2 Pair(Columna,Fila) que determina la segona cantonada de l'area quadrada
	 * 	@param ascendent Boolean que determina si les cel·les s'ordenaran de forma ascendent o descendent
	 *	@exception IndexOutOfBoundsException <p>
	 *  [#Full] Primera posició erronea:</p>
	 *  [#Full] Segona posició erronea:
	 */
	public void OrdenarBloc(Pair<Integer,Integer> cel1, Pair<Integer, Integer> cel2, boolean ascendent)
	{
	/*
		int idC1 = cel1.first();
		int idF1 = cel1.second();
		int idC2 = cel2.first();
		int idF2 = cel2.second();

		int colSize_ordena = (idC2 - idC1);
		int rowSize_ordena = (idF2 - idF1);
		ArrayList<Double> numarr = new ArrayList<Double>();
		ArrayList<String> strarr = new ArrayList<String>();

		for (int i = 0; i <= colSize_ordena; ++i) {
			for (int j = 0; j <= rowSize_ordena; ++j) {
				if (GetCela(idC1 + i, idF1 + j) != null) {
					try {
						double c = Double.parseDouble(GetCela(idC1 + i, idF1 + j).GetValorUsuari());
						numarr.add(c);
					} catch (Exception e) {
						strarr.add(GetCela(idC1 + i, idF1 + j).GetValorUsuari());
					}
				}
				ModificaCela(j, i, ""); // Esborra contingut un cop copiat
			}
		}

		Collections.sort(numarr);
		Collections.sort(strarr);
		int q = 0;
		for (int i = 0; (i <= colSize_ordena) && (q < (numarr.size() + strarr.size())); ++i) {
			for (int j = 0; (j <= rowSize_ordena) && (q < (numarr.size() + strarr.size())); ++j) {
				if (q < strarr.size()) {
					ModificaCela(i + idC1, j + idF1, strarr.get(q));
				} else {
					ModificaCela(i + idC1, j + idF1, new BigDecimal(numarr.get(q - strarr.size())).stripTrailingZeros().toPlainString());
				}
				++q;
			}
		}
		 */
		try {indexosCorrectes(cel1.first(), cel1.second()); }
		catch (IndexOutOfBoundsException e) { throw new IndexOutOfBoundsException("[#Full] Primera posició erronea:" + e.getMessage().replace("[#FULL]", "")); }
		try {indexosCorrectes(cel2.first(), cel2.second()); }
		catch (IndexOutOfBoundsException e) { throw new IndexOutOfBoundsException("[#Full] Segona posició erronea:" + e.getMessage().replace("[#FULL]", "")); }

		int fCol = Integer.min(cel1.first(), cel2.first());
		int lCol = Integer.max(cel1.first(), cel2.first());

		int fFil = Integer.min(cel1.second(), cel2.second());
		int lFil = Integer.max(cel1.second(), cel2.second());

		for (int i = fCol; i <= lCol; i++)
		{
			SortedMap<Integer,Cela> subColumna = _cjtCel.get(i);
			if (subColumna != null){
				Vector<Cela> celesCol = new Vector<>();
				for (int j = fFil; j <= lFil; j++)
				{
					Cela oldC = subColumna.remove(j);
					if (oldC != null) celesCol.add(oldC);
				}
				// System.out.println("[#CELL] Pre ordenar: " + celesCol);
				if (celesCol.size() != 0) {
					insertionSort(celesCol, ascendent);
					// System.out.println("[#CELL] Post ordenar: " + celesCol);
					for (int j = 0; j < celesCol.size(); j++){ subColumna.put(fFil + j, celesCol.get(j)); }
				}

			}
		}

	}

	/**
	 * Busca totes les cel·les del full que continguin part del valor indicat
	 * @param valor String que volem buscar al full
	 * @return Conjunt de tuples de fila, columna i cel·la associada, fila i columna estan agrupades en un pair d'Integers
	 * NOTE: El valor de retorn podria ser el seu propi objecte ("struct" amb fila, columna i ce·la)
	 */
	public Set<Pair<Pair<Integer,Integer> , String> > CercarValor(String valor)
	{
		Set<Pair <Pair<Integer, Integer> ,String> > celesTrobades = new HashSet<>();

		_cjtCel.forEach((columna, celesDeCol) -> celesDeCol.forEach((fila, cela) -> {
			if (cela.GetValorUsuari().contains(valor)) celesTrobades.add(new Pair<>(new Pair<>(columna, fila), cela.GetValorUsuari()));
		} ));
		return celesTrobades;
	}

	// ---------- PRIVATES ----------

	private boolean eliminaCela(int col, int fil)
	{
		if (!_cjtCel.containsKey(col)) 			return false;	// No exsisteix la columna: col
		if (!_cjtCel.get(col).containsKey(fil)) return false;	// No exsisteix la cel·la a la fila: fil

		int lastCol = _maxColumna;

		_cjtCel.get(col).get(fil).delete();	// Eliminem la cel·a (esborrem referencies)
		_cjtCel.get(col).remove(fil);		// La eliminem del TreeMap

		if(_cjtCel.get(col).isEmpty()) {	// Si era la ultima cel·la de la columna, l'eliminem
			_cjtCel.remove(col);
			lastCol = _cjtCel.firstKey();
		}
		if (_maxFila == fil) {				// Si era una cel·la al maxim de la fila, tornem a calcular el maxim
			_maxFila = 0;
			_cjtCel.forEach((columna, celesDeCol) -> _maxFila = Math.max(celesDeCol.firstKey(), _maxFila));
		}
		_maxColumna = lastCol;
		return true;
	}

	/** Comprova si una columna i una fila estan incloses al full
	 *  @param columna Index de la columna
	 *  @param fila Index de a Fila
	 *  @throws IndexOutOfBoundsException <p>
	 *  [#FULL] Fila no inclosa a Full </p>
	 *  [#FULL] Columna no inclosa a Full
	 */
	private void indexosCorrectes(int columna, int fila) throws IndexOutOfBoundsException
	{
		indexFilaCorrecte(fila);
		indexColsCorrecte(columna);
	}

	/** Donat l'index d'una fila, llença una excepcio si aquesta no esta inclosa al full
	 *  @param fila Index de la fila
	 *  @throws IndexOutOfBoundsException [#FULL] Fila no inclosa a Full
	 */
	private void indexFilaCorrecte (int fila) throws IndexOutOfBoundsException {
		if (fila < 0 || fila >= _nFil) throw new IndexOutOfBoundsException("[#FULL] Fila no inclosa a Full");
	}

	/** Donat l'index d'una columna, llença una excepcio si aquesta no esta inclosa al full
	 *  @param colu Index de la columna
	 *  @throws IndexOutOfBoundsException [#FULL] Fila no inclosa a Full
	 */
	private void indexColsCorrecte (int colu) throws IndexOutOfBoundsException {
		if (colu < 0 || colu >= _nCol) throw new IndexOutOfBoundsException("[#FULL] Columna no inclosa a Full");
	}

	/**
	 * Insertion sort que ordena un vector de Cel·les de forma ascendent o descendent
	 * Descendent: Primer alfabeticament (Strings Z > A) i despres numericament (Doubles 10 > 1)
	 * Ascendent: primer numericament (Doubles 1 < 10) i despres alfabeticament (Strings A < Z)
	 * @param v vector de cel·les a ordenar
	 * @param ascendent Mode d'ordenació, ascendent (TRUE) o descendent (FALSE)
	 */
	private void insertionSort(Vector<Cela> v, boolean ascendent)
	{
		int order = ascendent ? -1 : 1;
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
		int offset = eliminar ? -1 : 1;

		Iterator<Map.Entry<Integer,SortedMap<Integer,Cela> > > iterator = _cjtCel.headMap(pos - 1).entrySet().iterator();
		Set<Pair<Integer, SortedMap<Integer,Cela> >> tmp = new HashSet<>();	//Columnes a re-insertar

		while (iterator.hasNext() ) {
			Map.Entry<Integer ,SortedMap<Integer,Cela> > columna = iterator.next();
			// Només es fals si es vol eliminar I la posició es la mateix
			if (!eliminar || pos != columna.getKey()) tmp.add(new Pair<>(columna.getKey() + offset, columna.getValue() ));
			iterator.remove();
		}

		for (Pair<Integer, SortedMap<Integer, Cela>> nCol : tmp) {
			_cjtCel.put(nCol.first(), nCol.second());
		}
	}

	/**
	 * Donat un diccionari de files (representa una sola columna) i una posicio, les files
	 * es mouen segons si s'ha afegit una fila, o s'ha eliminat
	 * @param col diccionari de cel·les pertenyents a una columna (les claus son les files)
	 * @param pos fila que es decideix eliminar/afegir
	 * @param eliminar Boolean que determina si es vol eliminar (true) o afegir (false)
	 */
	private void shiftFiles (SortedMap<Integer,Cela> col, int pos, boolean eliminar)
	{
		int offset = eliminar ? -1 : 1;

		Iterator<Map.Entry<Integer,Cela> > iterator = col.headMap(pos - 1).entrySet().iterator();
		Set<Pair<Integer,Cela>> tmp = new HashSet<>();  // Cel·les a re-insertar

		while (iterator.hasNext()) {
			Map.Entry<Integer,Cela> filaCela = iterator.next();
			// Aquest if nomes sera fals si es vol eliminar I la posicio es la mateixa
			if (!eliminar || pos != filaCela.getKey()) tmp.add(new Pair<>(filaCela.getKey() + offset, filaCela.getValue() ) );
			iterator.remove();
		}

		for (Pair<Integer, Cela> nCela : tmp) {
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

/** Classe comparadora de parells d'enters */
class SerializablePairComparator implements Comparator<Pair<Integer,Integer>>, Serializable {
	/** Compara dos parells d'enters
	 * 	@param p1 the first object to be compared.
	 * 	@param p2 the second object to be compared.
	 * 	@return Compara el primer, si son iguals, compara el segon
	 */
	@Override
	public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
		return Objects.equals(p1.first(), p2.first()) ? p1.second().compareTo(p2.second() ): p1.first().compareTo(p2.first() );
	}
}
