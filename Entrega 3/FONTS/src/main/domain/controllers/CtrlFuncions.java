package main.domain.controllers;

import main.domain.classes.functions.numerica.*;
import main.domain.classes.functions.string.*;
import main.domain.classes.functions.data.*;
import main.domain.classes.functions.*;
import main.domain.classes.types.*;
import main.domain.classes.token.*;
import main.domain.classes.Full;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Classe encarregada de instanciar les funcions apartir d'un string
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class CtrlFuncions implements Serializable {
	/** Full al que pertany el controlador */
	private final Full _full;

	/** Constructora sense parametres
	 *  Només per el driver */
	@Deprecated
	public CtrlFuncions()		{ _full = new Full(1, 5, 6); }

	/** Constructora per defecte
	 * @param f Full al que pertany (d'on treura les referencies) */
	public CtrlFuncions(Full f) { _full = f; }

	/**
	 *	Donat un string s, retorna la subclasse de Funcio corresponent a la funció
	 * 	@param s String que comença per '=' indicant que és una funció
	 */
	public Funcio GetFuncio(String s, int columna, int fila)
	{
		String input = s.trim();
		Referencia refActual = _full.GetReferencia(columna,fila);
		try {	// Si la pos/referencia tenia una funció, eliminem la dependencia de les referencies que tenia
			refActual.GetFuncio().GetReferencies().forEach( r -> r.eliminarRefrenciada(refActual));
		} catch (Exception ignored) { }

		Funcio f = (input.length() != 0 && input.charAt(0) == '=') ?
			/* TRUE  */	stringToFuncio(input.substring(1)) :
			/* FALSE */	new FuncioError("Funcio mal declarada: " + input);

		f.GetReferencies().forEach( r -> r.afegirReferenciada(refActual));

		return f;
	}

	/** Donat un String que representa una funció, retorna una nova instància
	 * 	de la subclasse a la qual fa referencia
	 * @param s String tal que: NOM_FUNCIO( PARAMETRES )
	 * @return Funcio
	 */
	private Funcio stringToFuncio(String s)
	{
		Funcio f;

		if ( (s.length()-1) != s.lastIndexOf(')')) return new FuncioError("[#ERROR] Funcio mal definida: " + s);

		String inp = s.substring(0,s.length()-1);	// Eliminem el parentesi final
		String[] tmp = inp.split("\\(", 2);
		String nFuncio = tmp[0];
		String p = tmp[1];
		//System.out.println("[#STR2FUNC] " + s + " - p: " + p );
		try {
			switch( nFuncio.toUpperCase().trim()){
				case Absolut.Nom:
					f = new Absolut ( stringToParam(p));
					break;
				case aMajuscula.Nom:
					f = new aMajuscula( stringToParam(p));
					break;
				case Arrel.Nom:
					f = new Arrel( stringToParamList(p));
					break;
				case Arrodonir.Nom:
					f = new Arrodonir( stringToParam(p));
					break;
				case binariADecimal.Nom:
					f = new binariADecimal( stringToParam(p));
					break;
				case CorrelacioPearson.Nom:
					f = new CorrelacioPearson( stringToParamList(p));
					break;
				case Covariancia.Nom:
					f = new Covariancia( stringToParamList(p));
					break;
				case decimalABinari.Nom:
					f = new decimalABinari( stringToParam(p));
					break;
				case decimalAHexadecimal.Nom:
					f = new decimalAHexadecimal( stringToParam(p));
					break;
				case DesviacioEstandard.Nom:
					f = new DesviacioEstandard( stringToParamList(p));
					break;
				case Divisio.Nom:
					f = new Divisio( stringToParamList(p));
					break;
				case Factorial.Nom:
					f = new Factorial( stringToParam(p));
					break;
				case hexadecimalADecimal.Nom:
					f = new hexadecimalADecimal( stringToParam(p));
					break;
				case Mediana.Nom:
					f = new Mediana( stringToParamList(p));
					break;
				case Mitjana.Nom:
					f = new Mitjana( stringToParamList(p));
					break;
				case Multiplicacio.Nom:
					f = new Multiplicacio( stringToParamList(p));
					break;
				case numeroParaules.Nom:
					f = new numeroParaules( stringToParam(p));
					break;
				case obteAny.Nom:
					f = new obteAny( stringToParam(p));
					break;
				case obteDia.Nom:
					f = new obteDia( stringToParam(p));
					break;
				case obteDiaSetmana.Nom:
					f = new obteDiaSetmana( stringToParam(p));
					break;
				case obteMes.Nom:
					f = new obteMes( stringToParam(p));
					break;
				case Potencia.Nom:
					f = new Potencia( stringToParamList(p));
					break;
				case Reemplaca.Nom:
					f = new Reemplaca( stringToParamList(p));
					break;
				case Resta.Nom:
					f = new Resta( stringToParamList(p));
					break;
				case Suma.Nom:
					f = new Suma( stringToParamList(p) );
					break;
				case tamanyText.Nom:
					f = new tamanyText( stringToParam(p));
					break;
				case Truncar.Nom:
					f = new Truncar( stringToParam(p));
					break;
				case Variancia.Nom:
					f = new Variancia( stringToParamList(p));
					break;
				default: f = new FuncioError("Funció no reconeguda");
			}
		}
		catch (Exception e){ f = new FuncioError(e.getMessage()); }

		return f;
	}

	/** Donat un String que conté un conjunt de parametres separats per comes
	 *	construeix una Llista de IVisitable (parametres)
	 * @param s String amb un conjunt de parametres separats per comes
	 * @return List(IVisitable) parametres
	 */
	private List<IVisitable> stringToParamList(String s)
	{
		//System.out.println("[#STR2PARAMLIST] " + s);
		List<IVisitable> res = new ArrayList<>();

		String params = s;
		int nextComa = 0;
		while (nextComa != -1 || params.length() == 0)
		{
			nextComa = params.indexOf(',');
			int iniciFuncio = params.indexOf('(');

			/* Hi ha una funcio ('(') AND és el seguent parametre OR l'ultim*/
			if (iniciFuncio != -1 && (iniciFuncio < nextComa || nextComa == - 1)) { nextComa = finalFuncio(params); }

			if (nextComa != -1)
				res.add(stringToParam(params.substring(0,nextComa)));	// Si hi han més parametres
			else res.add(stringToParam(params));						// Si es l'ultim parametre

			if (nextComa == params.length()) nextComa = -1;				// Per el canvi de nextComa de les funcions
			params = params.substring(nextComa + 1);
		}
		// for (IVisitable v : res) System.out.println("RES: " + v);	// DEBUG
		return res;
	}

	/**
	 *	Donat un string que conté mínim una funció funcio, retorna el index del'ultim parentesi que pertany a la funció inicial
	 *	@param	s String amb una funció al principi (pot tenir més elements al final)
	 *	@return int Index de l'ultim parentesi ')' de la funció
	 */
	private int finalFuncio(String s)
	{
		int iniciFuncio = s.indexOf('(');
		int i = 1;
		int subF = 1;
		while (subF > 0)
		{
			char c = s.charAt(iniciFuncio + i);
			if (c == '(') subF++;
			if (c == ')') subF = subF - 1;
			i++;
		}
		return i + iniciFuncio;
	}

	/** Donat un String, retorna el parametre (IVisitable) que representa
	 * @param param String amb la representació d'un parametre
	 * @return El parametre que representa s, si no el representa cap IVisitable en particular, es retorna com a StringT
	 */
	private IVisitable stringToParam(String param)
	{
		String s = param.trim();
		TType tipus = Interpret.getType(s);
		IVisitable v = null;

		switch(tipus)
		{
			case INT: case DOUBLE: case HEXADECIMAL: case BINARI:
				try{ v = new NumberT( Interpret.StringToNumber(s)); }
				catch (Exception e) { System.err.println("[#STR2PAR] " + s + " no es numero - " + e.getMessage()); }
			break;
			case BLOC:
				try{ v = stringToBloc(s); }
				catch (Exception e) { System.err.println("[#STR2PAR] " + s + " no es bloc - " + e.getMessage()); }
			break;
			case REFERENCIA:
				try{ v = _full.GetReferencia(stringToCoords(s)); }
				catch (Exception e) { System.err.println("[#STR2PAR] " + s + " no es referencia - " + e.getMessage()); }
			break;
			case DATE:
				try{ v = new DateT (Interpret.StringToDate(s)); }
				catch (Exception e) { System.err.println("[#STR2PAR] " + s + " no es data - " + e.getMessage()); }
			break;
			case FUNCTION:
				v = stringToFuncio(s);
			break;
			default:
				v = new StringT(s);
		}
		v = (v == null) ? new StringT(s) : v;

		//System.out.println("[#STR2PARAM] " + s + " | " + v.getClass().getSimpleName());
		return v;
	}

	/** Donat un string que representa un bloc de referencies, retorna el bloc designat per els dos extrems
	 * @param s String en format de bloc: AAA111:BBB111
	 * @return BlocReferencies
	 * @throws RuntimeException [#STR2BLC] Bloc mal declarat
	 */
	private BlocReferencies stringToBloc(String s) throws RuntimeException
	{
		String[] refs = s.split(":");
		
		if (refs.length != 2) throw new RuntimeException("[#STR2BLC] Bloc mal declarat: "+ s );
		
		Pair<Integer,Integer> cel1 = stringToCoords(refs[0]);
		Pair<Integer,Integer> cel2 = stringToCoords(refs[1]);
		
		return new BlocReferencies(cel1, cel2, _full);
	}

	/** Donat un string que representa una coordenada (referencia), retorna un pair amb els indexos que representa
	 * @param s String en format de coordenada: AAA111
	 * @return Pair(columna, fila) Traduccio del string a coordenades del full
	 * @throws RuntimeException [#STR2COOR] Coordenades mal definides
	 */
	private Pair<Integer,Integer> stringToCoords(String s) throws RuntimeException
	{
		String[] split = s.split("(?<=\\D)(?=\\d)");	// Dividr en lletres i digits
		
		if (split.length != 2) throw new RuntimeException("[#STR2COOR] Coordenades mal definides: " + s);
		
		int columna = Interpret.HexavigesimalToInteger(split[0]);
		int fila = Integer.parseInt(split[1]) - 1;
		
		return new Pair<> (columna, fila);
	}
	
	// ---------- GETTERS ----------
	// TEMPORAL, PER TESTEJAR
	@Deprecated
	public Full GetFull() { return _full; }
}
