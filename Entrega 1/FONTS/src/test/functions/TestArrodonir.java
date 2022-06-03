package test.functions;

import main.domain.classes.functions.Arrodonir;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestArrodonir {
    /**
     * Objecte de la prova: Test del mètode arrodonir() de la classe Arrodonir
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit positiu acabat en un decimal major a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació d'Arrodonir.
     */
    @Test
    public void arrodonirPositiu7() {
        Arrodonir arr = new Arrodonir();
        arr.setValueD(2.7);
        assertEquals(3, arr.arrodonir());
    }

    /**
     * Objecte de la prova: Test del mètode arrodonir() de la classe Arrodonir
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit positiu acabat en un decimal igual a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació d'Arrodonir.
     */
    @Test
    public void arrodonirPositiu5() {
        Arrodonir arr = new Arrodonir();
        arr.setValueD(3.5);
        assertEquals(4, arr.arrodonir());
    }

    /**
     * Objecte de la prova: Test del mètode arrodonir() de la classe Arrodonir
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit positiu acabat en un decimal menor a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació d'Arrodonir.
     */
    @Test
    public void arrodonirPositiu3() {
        Arrodonir arr = new Arrodonir();
        arr.setValueD(7.3);
        assertEquals(7, arr.arrodonir());
    }

    /**
     * Objecte de la prova: Test del mètode arrodonir() de la classe Arrodonir
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit negatiu acabat en un decimal major a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació d'Arrodonir.
     */
    @Test
    public void arrodonirNegatiu7() {
        Arrodonir arr = new Arrodonir();
        arr.setValueD(-2.7);
        assertEquals(-3, arr.arrodonir());
    }

    /**
     * Objecte de la prova: Test del mètode arrodonir() de la classe Arrodonir
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit negatiu acabat en un decimal igual a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació d'Arrodonir.
     */
    @Test
    public void arrodonirNegatiu5() {
        Arrodonir arr = new Arrodonir();
        arr.setValueD(-3.5);
        assertEquals(-3, arr.arrodonir());
    }

    /**
     * Objecte de la prova: Test del mètode arrodonir() de la classe Arrodonir
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Double.
     * Operativa: En aquest test es comprova que a partir d'un dígit negatiu acabat en un decimal menor a .5, es
     * calcula correctament l'operació. Primer es fa un setter del paràmetre en Double i seguidament es calcula
     * l'operació d'Arrodonir.
     */
    @Test
    public void arrodonirNegatiu3() {
        Arrodonir arr = new Arrodonir();
        arr.setValueD(-7.3);
        assertEquals(-7, arr.arrodonir());
    }
}
