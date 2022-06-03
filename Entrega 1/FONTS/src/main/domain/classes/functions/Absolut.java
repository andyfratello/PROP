package main.domain.classes.functions;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Absolut extends FuncioDouble {
    /** Aquest metode retorna el valor absolut del parametre de entrada x, es a dir ,
     *  elimina el sisgne de manera que sempre sigui positiu.
     * @param
     * @return double
     */
    public double absolut() {
        return Math.abs(_value);
    }
}