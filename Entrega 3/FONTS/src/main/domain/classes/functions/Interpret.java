package main.domain.classes.functions;

import java.time.format.DateTimeParseException;
import main.domain.classes.types.Hexadecimal;
import java.time.format.DateTimeFormatter;
import main.domain.classes.types.Binari;
import main.domain.classes.types.TType;
import java.time.LocalDate;

/**
 * Aquesta classe fa d'analitzador sintàctic de Strings, en determina el seu tipus i transforma
 * els tipus String a Number, Data.
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Interpret {
	/** Instància única d'Interpret */
	private static final Interpret INSTANCE = new Interpret();

	/** Constructora (privada ja que és un singleton) */
	private Interpret() {
	}

	/** Getter de la instància de Interpret
	* @return INSTANCE
	*/
	public static Interpret getInstance() { return INSTANCE; }


	/** Determina si un string es null o buit
	 * @param s String a determinar
	 * @return (TRUE) Si es null o buit
	 */
	private static boolean isNullOrEmpty(String s)
	{
		if (s == null) return  true;
		return s.length() == 0;
	}

	/**
	* Retorna true si String x pot ser parsejat a Integer, false en cas contrari
	* @param x String a determinar
	* @return Boolean
	*/
	private static boolean esInteger(String x) {
		if(isNullOrEmpty(x)) return  false;
		try { Integer.parseInt(x);
		} catch (NumberFormatException ex) { return false; }
		return true;
	}

	/**
	* Retorna true si String x pot ser parsejat a Double, false en cas contrari
	* @param x String a determinar
	* @return Boolean
	*/
	private static boolean esDouble(String x) {
		if(isNullOrEmpty(x)) return  false;
		try { Double.parseDouble(x);
		} catch (NumberFormatException ex) { return false; }
		return true;
	}

	/**
	* Retorna true si String x pot ser parsejat a Date, false en cas contrari
	* @param x String a determinar
	* @return Boolean
	*/
	private static boolean esDate(String x) {
		if(isNullOrEmpty(x)) return  false;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(x, formatter);
		}
		catch (DateTimeParseException exc) { return false; }
		return true;
	}

	/**
	* Retorna true si String x és considerat funció, false en cas contrari
	* @param x String a determinar
	* @return Boolean
	*/
	private static boolean esFunction(String x) {
		if(isNullOrEmpty(x)) return  false;
		return x.charAt(0) == '=' || (x.contains("(") && x.contains(")"));
	}

	/**
	* Retorna true si String x és una referencia, false en cas contrari
	* @param x String per determinar
	* @return Boolean
	*/
	private static boolean esReferencia(String x) {
		if(isNullOrEmpty(x)) return  false;
		String[] split = x.split("(?<=\\D)(?=\\d)");	// Dividir en lletres i digits
		if (split.length != 2) return false;
		try {
			HexavigesimalToInteger(split[0]);
			StringToNumber(split[1]);
			return true;
		}
		catch (Exception ignore) { }
		return false;
	}
    
    /**
	* Retorna true si String x és un bloc (A1:B3), false en cas contrari
	* @param x String per Determinar
	* @return Boolean
	*/
	private static boolean esBloc(String x)
	{
		String[] split = x.split(":");
		if (split.length != 2) return false;
		return (esReferencia(split[0]) && esReferencia(split[1]));
    }

    /**
     *  Retorna el tipus del String passat com a parametre
     *  @param x String per determinar
     *  @return TType
     */
    public static TType getType(String x) {
        if (esInteger(x))       return TType.INT;
        if (esDouble(x))        return TType.DOUBLE;
        if (esDate(x))          return TType.DATE;
        if (esFunction(x))      return TType.FUNCTION;
        if (Binari.esBinari(x)) return TType.BINARI;
        if (Hexadecimal.esHexadecimal(x)) return TType.HEXADECIMAL;
        if (esBloc(x))			return TType.BLOC;
        if (esReferencia((x)))  return TType.REFERENCIA;
        return TType.STRING;
    }

	public static Number StringToNumber(String s) throws NumberFormatException
	{
	    TType type = getType(s);
	    if(!type.is(TType.NUMBER)) throw new NumberFormatException(s + " is not a Number");
		switch (type)
        {
			case INT:         return Integer.valueOf(s);
			case HEXADECIMAL: return new Hexadecimal(s);
			case BINARI:      return new Binari(s);
			case DOUBLE:      return Double.valueOf(s);
			default: throw new NumberFormatException(s + " is " + type);
        }
	}

    /**
     *  Donat un string, el converteix a LocalDate amb format (dd/MM/yyyy). Salta "DateTimeParseException": El string no es una data
     *  @param s String en format (dd/MM/yyyy)
     *  @return LocalDate de la data pasada com a parametre
     */
	public static LocalDate StringToDate(String s) throws DateTimeParseException { return LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy")); }

	/**
	 * Retorna string en Hexavigesimal (base26 en caràcters) a integer
	 * @param x String en base26 a caràcters
	 * @return Integer
	 */
	public static int HexavigesimalToInteger(String x) {
		int res = 0;
		String hexavigesimal = x.toUpperCase();
		for (int i = 0; i < hexavigesimal.length(); i++) {
			res *= 26;
			res += hexavigesimal.charAt(i) - 'A' + 1;
		}
		--res;
		return res;
	}

	/** Tradueix un index de columna a la lletra corresponent
	 * @param col Index de la columna
	 * @return Lletres que representen la columna de la Referencia
	 */
	public static String colToString(int col)
	{
		if (col < 26) return String.valueOf((char) ('A' + col));
		return colToString(col/26 - 1).concat(String.valueOf((char)('A' + col%26)));
	}

}
