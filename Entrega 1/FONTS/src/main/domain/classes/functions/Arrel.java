package main.domain.classes.functions;

import main.domain.classes.exceptions.NegativeRootException;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Arrel extends Funcio2Doubles {
    /** Aquest metode retorna l'arrel naria de x
     * @param _x no pot ser negatiu, sino es generara una excepcio (NegativeRootException)
     * @return double
     * @see <p> <b> <i> ATENCIO: </i> </b> Si n > 2 es poden produir errors de precissió sobretot en nombres grans (Millor no fer comparacions del tipus ==)
     */
    public double arrel() throws NegativeRootException {
        if (_x < 0) {
            throw new NegativeRootException(_x);
        }
        else if (_y == 2) {
            return Math.sqrt(_x);
        }
        return Math.pow(_x, 1.0 / _y);
    }
}