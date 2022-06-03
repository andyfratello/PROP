package main.domain.classes.functions;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class aMajuscula extends FuncioString {
    /** Aquest metode retorna el string s en majuscula
     * @param
     * @return String
     */
    public String aMajuscula() {
        return _value.toUpperCase();
    }
}