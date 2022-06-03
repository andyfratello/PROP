package main.domain.classes.functions;

import java.util.*;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Mediana extends FuncioVectorDoubles {
    /**
     * Retorna la mediana dels valors d'un vector de cel·les en Double
     * @param
     * @return Double
     */
    public double mediana() {
        Collections.sort(_value);
        double m = 0;
        int n = _value.size();
        if(n%2 == 1) {
            m = _value.get((n+1)/2-1);
        } else {
            m = (_value.get(n/2-1) + _value.get(n/2))/2;
        }
        return m;
    }
}