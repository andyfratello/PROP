package main.domain.classes.token;

import main.domain.classes.types.*;
import main.domain.classes.functions.*;

/**	
 *	Interficie del patró Visitor
 *	Les classes que l'implementen decideixen com afegir els diferents IVisitables
 *	Els metodes permeten processar els diferents tipus de IVisitable de manera diferent
 *	A la practica, IVisitor es inecesari ja que només te una classe que l'implementa
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */	
public interface IVisitor
{
	/** Implementació per al IVistable de tipus Funcio
	 * @param f IVisitable de tipus Funcio
	 * @throws RuntimeException
	 */
	void visit(Funcio f) throws RuntimeException;

	/** Implementació per al IVistable de tipus Referencia
	 * @param r IVisitable de tipus Referencia
	 * @throws RuntimeException
	 */
	void visit(Referencia r) throws RuntimeException;

	/** Implementació per al IVistable de tipus BlocReferencies
	 * @param b IVisitable de tipus BlocReferencies
	 * @throws RuntimeException
	 */
	void visit(BlocReferencies b) throws RuntimeException;

	/** Implementació per al IVistable de tipus Token
	 * @param t IVisitable de tipus Token
	 */
	void visit(Token t);
}
