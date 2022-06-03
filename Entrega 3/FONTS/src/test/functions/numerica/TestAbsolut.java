package test.functions.numerica;

import main.domain.classes.functions.numerica.Absolut;
import main.domain.classes.token.NumberT;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestAbsolut {
    /**
     * Objecte de la prova: Test del mètode absolut de la classe Absolut
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que amb un dígit negatiu, l'operació absolut es calcula correctament.
     * Primer es fa un setter del paràmetre en Double i seguidament es calcula l'operació d'Absolut
     */
    @Test
    public void absolutNegatiu() {

        Absolut abs = new Absolut(new NumberT(-3.0));
        assertEquals(3.0, abs.GetResultat().asNumber().doubleValue(), 0);
    }

    /**
     * Objecte de la prova: Test del mètode absolut de la classe Absolut
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que amb un dígit positiu, l'operació absolut es calcula correctament.
     * Primer es fa un setter del paràmetre en Double i seguidament es calcula l'operació d'Absolut
     */
    @Test
    public void absolutPositiu() {
        Absolut abs = new Absolut(new NumberT(64.0));
        assertEquals(64.0, abs.GetResultat().asNumber().doubleValue(), 0);
    }
    
}
