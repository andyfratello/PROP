package test.functions;

import main.domain.classes.functions.doubleAInt;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestDoubleAInt {
    /**
     * Objecte de la prova: Test del mètode doubleAInt() de la classe doubleAInt.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit positiu acabat en un decimal major a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació de doubleAInt.
     */
    @Test
    public void doubleAIntPosMaj() {
        doubleAInt dai = new doubleAInt();
        dai.setValueD(2.6);
        assertEquals(3, dai.doubleAInt());
    }

    /**
     * Objecte de la prova: Test del mètode doubleAInt() de la classe doubleAInt.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit positiu acabat en un decimal menor a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació de doubleAInt.
     */
    @Test
    public void doubleAIntPosMen() {
        doubleAInt dai = new doubleAInt();
        dai.setValueD(7.2);
        assertEquals(7, dai.doubleAInt());
    }

    /**
     * Objecte de la prova: Test del mètode doubleAInt() de la classe doubleAInt.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit negatiu acabat en un decimal major a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació de doubleAInt.
     */
    @Test
    public void doubleAIntNegMaj() {
        doubleAInt dai = new doubleAInt();
        dai.setValueD(-6.7);
        assertEquals(-7, dai.doubleAInt());
    }

    /**
     * Objecte de la prova: Test del mètode doubleAInt() de la classe doubleAInt.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit negatiu acabat en un decimal menor a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació de doubleAInt.
     */
    @Test
    public void doubleAIntNegMen() {
        doubleAInt dai = new doubleAInt();
        dai.setValueD(-10.2);
        assertEquals(-10, dai.doubleAInt());
    }
    
}
