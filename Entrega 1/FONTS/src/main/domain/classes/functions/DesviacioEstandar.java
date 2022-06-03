package main.domain.classes.functions;

import java.util.*;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class DesviacioEstandar extends FuncioVectorDoubles {

    private double variance(Vector<Double> vec) {
        int n = vec.size();
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += vec.get(i);
        }

        double mean = (double)sum/(double)n;
        double sqDiff = 0;
        for (int i = 0; i < n; i++) {
            sqDiff += (vec.get(i) - mean) * (vec.get(i) - mean);
        }
        return (double)sqDiff/n;
    }

    /**
     * Retorna la desviació estándar dels valors d'un vector de cel·les en Double
     * @param
     * @return Double
     */
    public double desviacioEstandar() {
        return Math.sqrt(variance(_value));
    }
}
