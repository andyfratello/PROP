package main.domain.classes.types;

import java.util.Objects;

/**
 * La classe Binari gestiona la conversió de tipus entre diferents bases a binari
 * i serveix com a tipus.
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Binari extends Number{
    private String _value;

    /** Constructora sense valor.
     *  Crea un objecte Binari amb valor 0 a 0b0
     */
    public Binari() { setValor(0); }

    /** Constructora amb valor. Emmagatzema el valor String x en l'objecte que es crea
     * @param x String
     * @throws  NumberFormatException Si el valor del String x no es valid
     */
    public Binari(String x) throws NumberFormatException {
        if(!setValor(x)) throw new NumberFormatException();
    }

    /** Constructora amb valor. Emmagatzema el valor int x en l'objecte que es crea<p>
     * Si x més petit de 0 el valor es guarda en complement a 2 de 32 bits
     * @param x Enter a guardar en binari
     */
    public Binari(int x) { setValor(x); }

    /**
     *  Guarda el valor entrat String al valor del objecte Binari
     * @param x String
     * @return (TRUE) Si el valor s'ha pogut enmagatzemar (esBinari), si no, FALSE
     */
    public boolean setValor(String x) {
        if(esBinari(x)){
            _value = x.substring(2);
            _value = "0b".concat(_value.substring(Math.max(0, _value.length()-32)) );
            return true;
        }
        try {
            String inp = x.toLowerCase().replaceAll("0b","").trim();
            inp = inp.substring(Math.max(0,inp.length()-32));
            Integer.parseUnsignedInt(inp, 2);
            _value = "0b".concat(inp);
            return true;
        }
        catch (Exception e) { return false; }
    }

    /** Guarda el valor entrat int x a un String amb codificacio Binaria<p>
     * Si x més petit de 0 el valor es guarda en complement a 2 de 32 bits
     * @param x Valor enter
     * @return Retorna 1 si el valor s'ha pogut guardar, 0 altrament
     */
    public boolean setValor(int x) {
        _value = "0b".concat(Integer.toBinaryString(x));
        return true;
    }

    /** Retorna un String amb la codificacio en Binari
     * @return String (p.e. "0b100101")
     */
    public String getValor() { return this._value; }

    /** Retorna el valor de l'objecte convertit a enter
     * @return Int
     */
    public int aDecimal() {
        int out = -1;
        try {
            out = Integer.parseUnsignedInt(value(), 2);
        } catch (NumberFormatException e) { // No hauria de passar mai!
            System.out.println("RUNTIME EXCEPTION! -> " + e.toString());
        }
        return out;
    }

    /** Retorna el valor de l'objecte convertit a Hexadecimal
     * @return Hexadecimal
     */
    public Hexadecimal aHexadecimal() {
        Hexadecimal out = new Hexadecimal();
        try {
            out.setValor(aDecimal());
        } catch (NumberFormatException e) { // No hauria de passar mai!
            System.out.println("RUNTIME EXCEPTION! -> " + e.toString());
        }
        return out;
    }

    /**
     * Determina si un string es un binari o no
     * @param x String que comença per 0b seguit de uns i zeros
     * @return (TRUE) Si el String es pot interpretar com a binari i comença per 0b/0B, si no, FALSE
     */
    public static boolean esBinari(String x) {
        if (!(x.startsWith("0b")|| x.startsWith("0B"))) return false;
        try {
            String inp = x.substring(2);
            inp = inp.substring(Math.max(0, inp.length() - 32));
            Integer.parseUnsignedInt(inp, 2);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /** @return _value sense el 0b del principi */
    private String value() { return _value.substring(2); }

    // OVERRIDES DE NUMBER
    /** @return Retorna el valor enter del binari com double */
    public double doubleValue() { return Integer.valueOf(aDecimal()).doubleValue(); }
    /** @return Retorna el valor enter del binari com float */
	public float floatValue() { return Integer.valueOf(aDecimal()).floatValue(); }
    /** @return Retorna el valor enter del binari */
	public int intValue() { return aDecimal(); }
    /** @return Retorna el valor enter del binari com long */
	public long longValue() { return Integer.valueOf(aDecimal()).longValue(); }
    /** @return Retorna la representacio del Binari com a String */
	public String toString() { return _value; }

    /** Comparador entre Binaris
     *  @param other Objecte al que comparar
     *  @return (TRUE) Si other es Binari i el seu valor és el mateix, si no FALSE
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (!(other instanceof Binari)) return false;
        Binari o = (Binari) other;
        return Objects.equals(_value, o._value);
    }
}
