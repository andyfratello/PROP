package test.functions;

import main.domain.classes.functions.Absolut;

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
        Absolut abs = new Absolut();
        abs.setValueD(-3.0);
        assertEquals(3.0, abs.absolut(), 0);
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
        Absolut abs = new Absolut();
        abs.setValueD(64.0);
        assertEquals(64.0, abs.absolut(), 0);
    }
    
}
