package main.domain.classes.functions;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class tamanyText extends FuncioString {
    /** Aquest metode retorna el nombre de caracters en el text s
     * @param
     * @return int
     */
    public int tamanyText() {
        return _value.length();
    }
}