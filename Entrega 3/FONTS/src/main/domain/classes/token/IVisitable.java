package main.domain.classes.token;

/**	Interfice que determina que una classe es visitable per un IVisitor
 *	Part del patro visitor
 *	@author Marc Duch Buechler
 */
public interface IVisitable
{
	/** La implementacio de cada classe sempre sera la mateixa: visitor.visit(this); */
	void accept(IVisitor visitor) throws RuntimeException;
}
