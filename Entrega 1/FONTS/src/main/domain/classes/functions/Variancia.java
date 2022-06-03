package main.domain.classes.functions;

import java.util.*;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Variancia extends FuncioVectorDoubles {

    /**
     * Retorna la variància dels valors d'un vector de cel·les en Double
     * @param
     * @return Double
     */
    public double variancia() {
        int n = _value.size();
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += _value.get(i);
        }

        double mean = (double)sum/(double)n;
        double sqDiff = 0;
        for (int i = 0; i < n; i++) {
            sqDiff += (_value.get(i) - mean) * (_value.get(i) - mean);
        }
        return (double)sqDiff/n;
    }
}

