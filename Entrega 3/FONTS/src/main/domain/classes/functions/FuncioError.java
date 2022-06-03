package main.domain.classes.functions;

import main.domain.classes.token.ErrorT;
import main.domain.classes.token.Token;

import main.domain.classes.types.BlocReferencies;

/**
 * Aquesta classe mostra els errors si no s'ha escrit bé una funció
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class FuncioError extends Funcio
{
	private ErrorT _token;

	/** Constructora
	 * @param s String amb l'error que ha causat la creació de la funcio de tipus error
	 */
	public FuncioError(String s) { super(new ErrorT("[#T]-" + s) ); }

	/** @return Token(ErrorT) Token d'error amb el missatge que s'ha creat */
	protected Token executa() { return _token; }


	// ---------- OVERRIDES ----------

	/** Visit del unic parametre (Token - ErrorT)
	 * @param t IVisitable de tipus Token
	 */
	@Override
	public void visit(Token t) { _token = (ErrorT)t; }

	/** Metode buit, no es crida mai */
	@Override
	public void visit(BlocReferencies b) { }
}
