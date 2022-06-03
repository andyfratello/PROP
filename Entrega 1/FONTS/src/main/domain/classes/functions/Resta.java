package main.domain.classes.functions;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Resta extends Funcio2Doubles {
    /**
     * Retorna la resta del valor de dues cel·les en Double
     * @param double _x, double _y
     * @return Double
     */
    public double resta() {
        return Math.round((_x - _y)*100000.0)/100000.0;
    }
}