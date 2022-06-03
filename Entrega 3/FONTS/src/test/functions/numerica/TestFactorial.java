package test.functions.numerica;

import main.domain.classes.functions.numerica.Factorial;
import main.domain.classes.exceptions.NegativeFactorialException;

import static org.junit.Assert.*;

import main.domain.classes.token.NumberT;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestFactorial {
    /**
     * Objecte de la prova: Test del mètode factorial() de la classe Factorial.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un enter.
     * Operativa: En aquest test es comprova que a partir d'un dígit positiu, es calcula correctament l'operació.
     * Primer es fa un setter del paràmetre en Integer i seguidament es calcula l'operació de Factorial.
     */
    @Test
    public void factorialPositiu() {
        Factorial fac = new Factorial(new NumberT(8));
        assertEquals(40320, fac.GetResultat().asNumber().intValue(), 0);
    }

    /**
     * Objecte de la prova: Test del mètode factorial() de la classe Factorial.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un enter.
     * Operativa: En aquest test es comprova que a partir d'un dígit negatiu, no es calcula l'operació i es llença
     * l'excepció NegativeFactorialException. Primer es fa un setter del paràmetre en Integer i seguidament es calcula
     * l'operació de Factorial, de manera que salta l'excepció.
     */
    @Test (expected = NegativeFactorialException.class)
    public void factorialNegatiu() throws NegativeFactorialException{
        Factorial fac = new Factorial(new NumberT(-4));
        fac.GetResultat();

    }
}
