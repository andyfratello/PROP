package main.domain.classes.types;

import java.util.Objects;

/**
 * La classe Hexadecimal gestiona la conversió de tipus entre diferents bases a hexadecimal
 * i serveix com a tipus.
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Hexadecimal extends Number {
    private String _value;

    /** Constructora sense valor.
     *  Crea un objecte Hexadecimal amb valor 0 - 0x0
     */
    public Hexadecimal() { setValor(0); }

    /** Constructora amb valor. Emmagatzema el valor String x en l'objecte que es crea
     * @param x String amb el valor Hexadecimal
     * @throws  NumberFormatException Si el valor del String x no es valid
     */
    public Hexadecimal(String x) throws NumberFormatException {
        if (!setValor(x)) throw new NumberFormatException();
    }

    /** Constructora amb valor. Emmagatzema el valor x en l'objecte que es crea<p>
     * Si x més petit de 0 el valor es guarda en complement a 2 de 32 bits
     * @param x Valor decimal a expresarse com a Hexadecimal
     */
    public Hexadecimal(int x) { setValor(x); }

    /** Guarda el valor entrat String x en l'objecte Hexadecimal
     * @param x String codificat en Hexadecimal
     * @return Retorna si el valor s'ha pogut guardar
     */
    public boolean setValor(String x) {
        if (esHexadecimal(x)) {
            _value = x.substring(2);
            _value = "0x".concat(_value.substring(Math.max(0, _value.length()-8)) );
            return true;
        }
        try {
            String inp = x.toUpperCase().replaceAll("0X","").trim();
            inp = inp.substring(Math.max(0,inp.length()-8));
            Integer.parseUnsignedInt(inp, 16);
            _value = "0x".concat(inp);
            return true;
        }
        catch (Exception e) { return false; }
    }

    /** Guarda el valor entrat int x en l'objecte Hexadecimal<p>
     * Si x més petit de 0 el valor es guarda en complement a 2 de 32 bits
     * @param x Nou valor enter
     * @return Retorna si el valor s'ha pogut guardar
     */
    public boolean setValor(int x) {
        _value = "0x".concat(Integer.toHexString(x).toUpperCase());
        return true;
    }

    /** Retorna un String amb la codificacio en Hexadecimal del objecte
     * @return String (p.e. "5C2f")
     */
    public String getValor() { return this._value; }

    /** Retorna el valor de l'objecte convertit a Decimal
     * @return int
     */
    public int aDecimal() {
        int out = 0;
        try {
            out = Integer.parseUnsignedInt(value(), 16);
        } catch (NumberFormatException e) { // No hauria de passar mai!
            System.out.println("RUNTIME EXCEPTION! -> " + e.getMessage());
        }
        return out;
    }

    /** Retorna el valor de l'objecte convertit a Binari
     * @return Binari
     */
    public Binari aBinari() {
        Binari out = new Binari();
        try {
            out.setValor(aDecimal());
        } catch (NumberFormatException e) { // No hauria de passar mai!
            System.out.println("RUNTIME EXCEPTION! -> " + e.getMessage());
        }
        return out;
    }

    /** Donat un String, determina si es Hexadecimal
     * @param x Sring a determinar
     * @return (TRUE) Si comença er 0x/0X i es pot expressar com ahexadecimal, si no, FALSE
     */
    public static boolean esHexadecimal(String x) {
        if (!(x.startsWith("0x") || x.startsWith("0X"))) return false;
        try {
            String inp = x.substring(2);
            inp = inp.substring(Math.max(0, inp.length() - 8));
            Integer.parseUnsignedInt(inp, 16);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /** @return _value sense el 0x del principi */
    private String value() { return _value.substring(2); }  // Potser falta .toLowerCase()
    
    // OVERRIDES DE NUMBER

    /** @return Retorna el valor enter del hexadecimal com double */
	public double doubleValue() { return Integer.valueOf(aDecimal()).doubleValue(); }
    /** @return Retorna el valor enter del hexadecimal */
	public int intValue() { return aDecimal(); }
    /** @return Retorna el valor enter del hexadecimal com long */
	public long longValue() { return Integer.valueOf(aDecimal()).longValue(); }
    /** @return Retorna el valor enter del hexadecimal com float */
	public float floatValue() { return Integer.valueOf(aDecimal()).floatValue(); }
    /** @return Retorna la representacio en String del Hexadecimal */
    @Override
	public String toString() { return _value; }

    /** Comparador entre Hexadecimals
     *  @param other Objecte al que comparar
     *  @return (TRUE) Si other es Hexadecimal i el seu valor es el mateix, si no FALSE
     */
	@Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (!(other instanceof Hexadecimal)) return false;
        Hexadecimal o = (Hexadecimal) other;
        return Objects.equals(_value, o._value);
    }
}
