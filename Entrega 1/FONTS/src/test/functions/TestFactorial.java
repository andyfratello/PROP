package test.functions;

import main.domain.classes.functions.Factorial;
import main.domain.classes.exceptions.NegativeFactorialException;

import static org.junit.Assert.*;
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
    public void factorialPositiu() throws NegativeFactorialException{
        Factorial fac = new Factorial();
        fac.setValueI(8);
        assertEquals(40320, fac.factorial(), 0);
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
        Factorial fac = new Factorial();
        fac.setValueI(-4);
        fac.factorial();
    }
}
