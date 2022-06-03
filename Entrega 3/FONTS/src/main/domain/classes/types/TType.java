package main.domain.classes.types;

import java.io.Serializable;

/** Enumerador dels diferents tiups que pot expresar un String d'una cel·la
 *	Organitzat en forma de jerarquia, on un tiups pot ser expresat de varies maneres
 *  @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public enum TType implements Serializable {
	REFERENCIA(null),
		BLOC(TType.REFERENCIA),
	FUNCTION(null),
		ERROR(TType.FUNCTION),
	STRING(null),
		DATE(TType.STRING),
		NUMBER(TType.STRING),
			BINARI(TType.NUMBER),
			HEXADECIMAL(TType.NUMBER),
			INT(TType.NUMBER),
			DOUBLE(TType.NUMBER)
	;

    private final TType _parent;
    
	/** Constructora per poder crear la jerarquia */
    TType(TType parent){ _parent = parent; }

	/** Comprova que un enum es subtipus de l'altre
	 * @param other TType a comprovar
	 * @return (TRUE) Si es subtipus de other, (FALSE) si no es subtipus
	 */
	public boolean is(TType other)
    {
		if (other == null) return false;
		for (TType t = this; t != null; t = t._parent) { if(t == other) return true; }
        return false;
    }
}
