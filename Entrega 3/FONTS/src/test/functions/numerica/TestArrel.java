package test.functions.numerica;

import main.domain.classes.functions.numerica.Arrel;
import main.domain.classes.exceptions.NegativeRootException;

import static org.junit.Assert.*;

import main.domain.classes.token.NumberT;
import org.junit.Test;

import java.util.List;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestArrel {
    /**
     * Objecte de la prova: Test del mètode arrel() de la classe Arrel
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir d'un dígit base en negatiu i un grau de l'arrel, no es calcula
     * correctament l'operació i es llença l'excepció NegativeRootException. Primer es fa un setter dels paràmetres en
     * Double i seguidament es calcula l'operació d'Arrel.
     */
    @Test (expected=NegativeRootException.class)
    public void arrelExcept() throws NegativeRootException {
        Arrel arr = new Arrel(List.of(new NumberT(-4), new NumberT(2)));
        arr.GetResultat();
    }

    /**
     * Objecte de la prova: Test del mètode arrel() de la classe Arrel
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir d'un dígit base en positiu i un grau de l'arrel, es calcula
     * correctament l'operació. Primer es fa un setter dels paràmetres en Double i seguidament es calcula l'operació
     * d'Arrel.
     */
    @Test
    public void arrel() throws NegativeRootException {
        Arrel arr = new Arrel(List.of(new NumberT(64), new NumberT(2)));
        assertEquals(8.0, arr.GetResultat().asNumber().doubleValue(), 0);
        arr = new Arrel(List.of(new NumberT(16384), new NumberT(7)));
        assertEquals(4.0, arr.GetResultat().asNumber().doubleValue(), 0.000000000000001);
    }
    
}
