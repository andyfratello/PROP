package test.functions;

import main.domain.classes.functions.Truncar;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestTruncar {
    /**
     * Objecte de la prova: Test del mètode truncar() de la classe Truncar
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit acabat en un decimal menor a .5, es calcula
     * correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula l'operació de
     * Truncar.
     */
    @Test
    public void truncarMenor() {
        Truncar trunc = new Truncar();
        trunc.setValueD(9.3);
        assertEquals(9, trunc.truncar());
    }

    /**
     * Objecte de la prova: Test del mètode truncar() de la classe Truncar
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit acabat en un decimal major a .5, es calcula
     * correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula l'operació de
     * Truncar.
     */
    @Test
    public void truncarMajor() {
        Truncar trunc = new Truncar();
        trunc.setValueD(9.9);
        assertEquals(9, trunc.truncar());
    }
    
}
