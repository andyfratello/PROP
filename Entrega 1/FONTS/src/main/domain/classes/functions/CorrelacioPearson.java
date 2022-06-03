package main.domain.classes.functions;

import java.util.*;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class CorrelacioPearson extends Funcio2VectorsDoubles {
    /**
     * Retorna la Correlacio de Pearson dels valors d'un vector de cel·les en Double
     * @param
     * @return Double
     */
    public double correlacioPearson() {
        double sx = 0.0;
        double sy = 0.0;
        double sxx = 0.0;
        double syy = 0.0;
        double sxy = 0.0;

        int n = _a.size();

        for(int i = 0; i < n; ++i) {
            double x = _a.get(i);
            double y = _b.get(i);
            sx += x;
            sy += y;
            sxx += x * x;
            syy += y * y;
            sxy += x * y;
        }

        double cov = sxy / n - sx * sy / n / n;
        double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
        double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);

        return cov / sigmax / sigmay;
    }
}