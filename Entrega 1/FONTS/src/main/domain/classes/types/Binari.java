package main.domain.classes.types;

/**
 * La classe Binari gestiona la conversió de tipus entre diferents bases a binari
 * i serveix com a tipus.
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Binari {
    private String _value;

    /** Constructora sense valor.
     *  Crea un objecte Binari amb valor 0
     */
    public Binari() {
        _value = Integer.toBinaryString(0);
    }

    /** Constructora amb valor. Emmagatzema el valor String x en l'objecte que es crea
     *
     * @param
     * @return Pot generar NumberFormatException si el valor del String x no es valid
     */
    public Binari(String x) throws NumberFormatException {
        int res = esBinari(x);
        if (res == 1) {
            _value = x;
        }
        else {
            throw new NumberFormatException(x);
        }
    }

    /** Constructora amb valor. Emmagatzema el valor int x en l'objecte que es crea<p>
     * Si x < 0 el valor es guarda en complement a 2 de 32 bits
     * @param
     */
    public Binari(int x) {
        _value = Integer.toBinaryString(x);
    }

    /** Guarda el valor entrat String al valor del objecte Binari
     *
     * @param
     * @return Retorna 1 si el valor s'ha pogut guardar, 0 altrament
     */
    public int setValor(String x) {
        int res = esBinari(x);
        if (res == 1) {
            _value = x;
        }
        return res;
    }

    /** Guarda el valor entrat int x a un String amb codificacio Binaria<p>
     * Si x < 0 el valor es guarda en complement a 2 de 32 bits
     * @param
     * @return Retorna 1 si el valor s'ha pogut guardar, 0 altrament
     */
    public int setValor(int x) {
        _value = Integer.toBinaryString(x);
        return 1;
    }

    /** Retorna un String amb la codificacio en Binari del objecte
     *
     * @param
     * @return String (p.e. "100101")
     */
    public String getValor() {
        return this._value;
    }

    /** Retorna el valor de l'objecte convertit a Decimal
     *
     * @param
     * @return
     */
    public int aDecimal() {
        int out = -1;
        try {
            out = Integer.parseUnsignedInt(_value, 2);
        } catch (NumberFormatException e) { // No hauria de passar mai!
            System.out.println("RUNTIME EXCEPTION! -> " + e.toString());
        }
        return out;
    }

    /** Retorna el valor de l'objecte convertit a Hexadecimal
     *
     * @param
     * @return Hexadecimal
     */
    public Hexadecimal aHexadecimal() {
        Hexadecimal out = new Hexadecimal();
        try {
            out.setValor(Integer.parseInt(_value,2));
        } catch (NumberFormatException e) { // No hauria de passar mai!
            System.out.println("RUNTIME EXCEPTION! -> " + e.toString());
        }
        return out;
    }

    private static int esBinari(String x) {
        try {
            Integer.parseInt(x, 2);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

}
