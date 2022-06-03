package main.domain.classes.functions;

import main.domain.classes.types.BlocReferencies;
import main.domain.classes.types.Referencia;
import main.domain.classes.token.IVisitable;
import main.domain.classes.token.IVisitor;
import main.domain.classes.token.StringT;
import main.domain.classes.token.NumberT;
import main.domain.classes.token.ErrorT;
import main.domain.classes.token.DateT;
import main.domain.classes.token.Token;
import main.domain.classes.types.TType;
import java.io.Serializable;
import java.util.*;

/**
 * Aquesta classe abstracta conté tots els mètodes de totes les funcions que s'implementen al sistema
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public abstract class Funcio implements IVisitable, IVisitor, Serializable
{

	/** Llista de parametres de la funció guardada com
	 * IVisitables(Funcio/Referencia/BlocReferencies/Token) */
	protected List<IVisitable> _params;

	/** Parametres de tipus referencia d'una funció (inclou subfuncions) */
	private final Set<Referencia> _referencies;

	/** Tipus de retorn, inutilitzat */
	@Deprecated
	protected TType _tipus;

	/** Token Error generat a l'hora de calcular el resultat, NULL altrament */
	private ErrorT _error = null;

	// ---------- CONSTRUCTORES ----------

	/** Constructora amb un sol parametre
	 * @param parametre Parametre de la funcio
	 * @throws RuntimeException [#FUNC] Funcio sense parametres
	 */
	public Funcio(IVisitable parametre) throws RuntimeException {
		this(List.of(parametre));
	}

	/**	Constructora amb llista de parametres
	 * @param params Llista de parametres
	 * @throws RuntimeException [#FUNC] Funcio sense parametres
	 */
	public Funcio(List<IVisitable> params)  throws RuntimeException
	{
		_params = params;
		_referencies = new HashSet<>();
		if (_params.size() == 0) throw new RuntimeException("[#FUNC] Funcio sense parametres");
	}
	
	// ---------- GETTERS ----------

	/** Retorna el tipus del objecte de retorn */
	@Deprecated public final TType GetType() { return _tipus; }
	
	/** Retorna el valor de la funció com a String */
	public final Token GetResultat() throws RuntimeException
	{
		paramsToValues();
		return _error == null ? executa() : _error;
	}
	
	/** @return Retorna la llista de tokens que té com a parametres */
	protected final List<IVisitable> GetParams() { return _params; }
	/** @return Retorna el conjunt de cel·les a les que referencia la funció */
	public final Set<Referencia> GetReferencies() {
		paramsToValues();
		return _referencies;
	}

	// ---------- IMPLEMENTACIO DE FUNCIONS ----------

	/** Metode abstracte que calcula la funcio en si
	 * @return Retorna el token que conté el resultat
	 */
	protected abstract Token executa() throws RuntimeException;
	
	// ---------- METODES HELPER PER FUNCIONS ----------

	/** Aplica el patró visitor sobre tots els parametres */
	protected final void paramsToValues()
	{
		// Inicialització de les subfuncions
		ini();

		_referencies.clear();
		_error = null;

		try { for (IVisitable p : _params) p.accept(this); }
		catch (Exception e) { _error = new ErrorT(e.getMessage()); }
	}

	/** Inicialització previa d'una funció */
	protected void ini() { }

	// ---------- IVISITOR ----------

	/** Comprova que la funció no depen de si mateixa per calcular el resultat
	 * 	Executa visit() sobre el resultat de la funció
	 * 	@param f Funcio
	 *	@throws RuntimeException [#FUNC] Cicle de referencies
	 */
	public final void visit(Funcio f) throws RuntimeException
	{
		// Les subfuncions
		_referencies.addAll(f.GetReferencies());
		visit(f.GetResultat());
	}
	
	/**	Delega la responsabilitat a visit(Funcio) o visit(Token) segons si l'element de la referencia es o no una funció 
	 *	@param r Referencia
	*/
	public final void visit(Referencia r) throws RuntimeException
	{
		_referencies.add(r);
		if (r.teCicle()) throw new RuntimeException("[#ERROR] Cicle de referencies!");
		Token t = new ErrorT("[#FUNC] _tipus de referencia no reconegut " + r.GetValorReal() + " " + r.GetTipus());

		if (r.EsFuncio()) { t = r.GetFuncio().GetResultat(); }
		else if (r.GetTipus().is(TType.NUMBER)) 	t = new NumberT(Interpret.StringToNumber(r.GetValorReal()));
		else if (r.GetTipus().is(TType.DATE))		t = new DateT(Interpret.StringToDate(r.GetValorReal()));
		else if (r.GetTipus().is(TType.STRING)) 	t = new StringT(r.GetValorReal());

		visit(t == null ? new StringT(r.GetValorReal()) : t);
	}

	// --- ABSTRACTES --- //

	/** Implementacio abstracte de visit(BlocReferencies)
	 * @param b IVisitable de tipus BlocReferencies
	 * @throws RuntimeException Funcio no ccepta blocs
	 */
	public abstract void visit(BlocReferencies b) throws RuntimeException;

	/** Implementació abstracta de visit(Token)
	 * @param t IVisitable de tipus Token
	 */
	public abstract void visit(Token t);

	// ----------- IVISITABLE ----------
	public void accept(IVisitor visitor) { visitor.visit(this); }

	// ---------- EQUALS ----------
	/**	Override de la funcio equals d'Object per tal de poder fer ús del HashSet
	 *	@param o Object
	 */
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Funcio)) return false;
		return (o == this);
	}

	/** @return Retorna la representació de la funcio i els seus params */
	@Override
	public String toString()
	{
		String sParams= "";
		for (IVisitable p : _params) sParams = sParams.concat(p.toString() + ",");
		return this.getClass().getSimpleName().concat("(" + sParams.substring(0,sParams.length()-1) + ")");
	}
}