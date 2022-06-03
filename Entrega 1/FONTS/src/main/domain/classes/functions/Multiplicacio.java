package main.domain.classes.functions;

import java.util.*;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Multiplicacio extends FuncioVectorDoubles {
    /**
     * Retorna la multiplicació dels valors d'un vector de cel·les en Double
     * @param
     * @return Double
     */
    public double multiplicacio() {
        double mult = 1;
        for (int i = 0; i < _value.size(); ++i) { mult *= _value.get(i); }
        return mult;
    }
}