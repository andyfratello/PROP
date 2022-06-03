package main.domain.classes.types;

/**
 * La classe Hexadecimal gestiona la conversió de tipus entre diferents bases a hexadecimal
 * i serveix com a tipus.
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */

public class Hexadecimal {
    private String _value;

    /** Constructora sense valor.
     *  Crea un objecte Hexadecimal amb valor 0
     */
    public Hexadecimal() {
        _value = Integer.toHexString(0);
    }

    /** Constructora amb valor. Emmagatzema el valor String x en l'objecte que es crea
     *
     * @param x
     * @return Pot generar NumberFormatException si el valor del String x no es valid
     */
    public Hexadecimal(String x) throws NumberFormatException {
        int res = esHexadecimal(x);
        if (res == 1) {
            _value = x;
        }
        else {
            throw new NumberFormatException(x);
        }
    }

    /** Constructora amb valor. Emmagatzema el valor x en l'objecte que es crea<p>
     * Si x < 0 el valor es guarda en complement a 2 de 32 bits
     * @param x
     */
    public Hexadecimal(int x) {
        _value = Integer.toHexString(x);
    }

    /** Guarda el valor entrat String x en l'objecte Hexadecimal
     *
     * @param x
     * @return Retorna 1 si el valor s'ha pogut guardar, 0 altrament
     */
    public int setValor(String x) {
        int res = esHexadecimal(x);
        if (res == 1) {
            _value = x;
        }
        return res;
    }

    /** Guarda el valor entrat int x en l'objecte Hexadecimal<p>
     * Si x < 0 el valor es guarda en complement a 2 de 32 bits
     * @param x
     * @return Retorna 1 si el valor s'ha pogut guardar, 0 altrament
     */
    public int setValor(int x) {
        _value = Integer.toHexString(x);
        return 1;
    }

    /** Retorna un String amb la codificacio en Hexadecimal del objecte
     *
     * @param -
     * @return String (p.e. "5c2f")
     */
    public String getValor() {
        return this._value;
    }

    /** Retorna el valor de l'objecte convertit a Decimal
     *
     * @param -
     * @return int
     */
    public int aDecimal() {
        int out = -1;
        try {
            out = Integer.parseUnsignedInt(_value, 16);
        } catch (NumberFormatException e) { // No hauria de passar mai!
            System.out.println("RUNTIME EXCEPTION! -> " + e.toString());
        }
        return out;
    }

    /** Retorna el valor de l'objecte convertit a Hexadecimal
     *
     * @param -
     * @return Hexadecimal
     */
    public Binari aBinari() {
        Binari out = new Binari();
        try {
            out.setValor(Integer.parseInt(_value,16));
        } catch (NumberFormatException e) { // No hauria de passar mai!
            System.out.println("RUNTIME EXCEPTION! -> " + e.toString());
        }
        return out;
    }

    private static int esHexadecimal(String x) {
        try {
            Integer.parseInt(x, 16);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
