package main.domain.classes.functions;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Truncar extends FuncioDouble {
    /** Trunca el valor entrat x a un enter, es a dir, elimina els decimals
     * deixant nomes la part entera del valor.
     * Per exemple: 9.3 -> 9, 9.9-> 9
     * @param
     * @return int
     */
    public int truncar() {
        return (int)_value;
    }
}
