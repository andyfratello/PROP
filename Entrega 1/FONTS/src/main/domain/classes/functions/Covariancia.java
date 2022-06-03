package main.domain.classes.functions;

import java.util.*;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Covariancia extends Funcio2VectorsDoubles {
    private double mean(Vector<Double> vec, int n) {
        double sum = 0;
        for(int i = 0; i < n; i++) {
            sum = sum + vec.get(i);
        }
        return sum / n;
    }

    /**
     * Retorna la covariancia dels valors d'un vector de cel·les en Double
     * @param
     * @return Double
     */
    public double covariancia() {
        int n = _a.size();
        double sum = 0.0;
        double mean_a = mean(_a, n);
        double mean_b = mean(_b, n);
        //System.out.println("Mitjana:" + mean_a + " " + mean_b);
        //System.out.println("\n");
        for(int i = 0; i < n; i++) {
            sum += (_a.get(i) - mean_a)*(_b.get(i) - mean_b);
            //System.out.println(_a.get(i) + " " + _b.get(i));
            //System.out.println((_a.get(i) - mean_a) + " " + (_b.get(i) - mean_b));
            //System.out.println((_a.get(i) - mean_a) * (_b.get(i) - mean_b));
            //System.out.println(sum + "\n");
        }
        //System.out.println(sum);
        return Math.round((sum/(n - 1))*100000.0)/100000.0;
    }
}