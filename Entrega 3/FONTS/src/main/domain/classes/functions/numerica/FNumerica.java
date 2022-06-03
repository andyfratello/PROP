package main.domain.classes.functions.numerica;

import main.domain.classes.functions.Funcio;
import main.domain.classes.token.Token;
import main.domain.classes.token.IVisitable;

import main.domain.classes.types.Referencia;
import main.domain.classes.types.TType;
import main.domain.classes.types.BlocReferencies;

import java.util.List;
import java.util.ArrayList;

/** Classe abstracta que exten de la classe Funcio, representa les funcions que només admeten paràmetres de numerics
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public abstract class FNumerica extends Funcio
{
	List<Number> _values;

	/** Constructora
	 *	Marca el tipus de funció com a NUMBER
	 * 	@param params Llista de parametres
	 *  @throws RuntimeException [#FNUM] Parametre no es numero
	 */
	public FNumerica(List<IVisitable> params) throws RuntimeException {
		super(params);
		_tipus = TType.NUMBER;
	}

	/** Inicialització previa d'una funció */
	@Override
	protected void ini() {
		super.ini();
		_values = new ArrayList<>();
	}

	// ---------- IVISITOR ----------

	/** Implementacio per a funcions N-àries
	 *  @param b IVisitable de tipus BlocReferencies
	 */
	@Override
	public void visit(BlocReferencies b) { for (Referencia r : b.GetReferencies()) { visit(r); } }

	/** Implementació de visit(Token)
	 *  Comprova que el token es de tipus numero, en cas contrari, l'ignora
	 *  @param t IVisitable de tipus Token
	 */
	@Override
	public void visit(Token t) 
	{
		try { _values.add(t.asNumber()); }
		catch (Exception e) { /*System.err.println("[#FNUM] " + t.asString() + " " + t.getClass().getSimpleName());*/ }
	}
}
