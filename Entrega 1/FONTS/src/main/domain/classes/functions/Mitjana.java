package main.domain.classes.functions;

import java.util.*;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Mitjana extends FuncioVectorDoubles {
    /**
     * Retorna la mitjana dels valors d'un vector de cel·les en Double
     * @param
     * @return Double
     */
    public double mitjana() {
        double sum = 0;
        for (int i = 0; i < _value.size(); ++i) { sum += _value.get(i); }
        return sum/_value.size();
    }
}