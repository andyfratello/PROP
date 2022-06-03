package test.functions;

import main.domain.classes.functions.DesviacioEstandar;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Vector;

public class TestDesviacioEstandar {
    /**
     * Objecte de la prova: Test del mètode desviacioEstandar() de la classe DesviacioEstandar.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un vector de tipus Double.
     * Operativa: En aquest test es comprova que a partir d'un vector de tipus Double, es calcula correctament
     * l'operació amb un resultat amb decimals. Primer es crea un vector de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter del vector. Finalment es calcula l'operació de
     * DesviacioEstandar i es comprova el correcte funcionament.
     */
    @Test
    public void desviacioEstandar1() {
        Vector<Double> x = new Vector<Double>();
        x.add(600.0);
        x.add(470.0);
        x.add(170.0);
        x.add(430.0);
        x.add(300.0);
        DesviacioEstandar des = new DesviacioEstandar();
        des.setValueV(x);
        assertEquals(147.323, des.desviacioEstandar(), 0.001);
    }

    /**
     * Objecte de la prova: Test del mètode desviacioEstandar() de la classe DesviacioEstandar.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un vector de tipus Double.
     * Operativa: En aquest test es comprova que a partir d'un vector de tipus Double, es calcula correctament
     * l'operació amb un resultat amb decimals. Primer es crea un vector de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter del vector. Finalment es calcula l'operació de
     * DesviacioEstandar i es comprova el correcte funcionament.
     */
    @Test
    public void desviacioEstandar2() {
        Vector<Double> x = new Vector<Double>();
        x.add(2.0);
        x.add(7.0);
        x.add(3.0);
        x.add(12.0);
        x.add(9.0);
        DesviacioEstandar des = new DesviacioEstandar();
        des.setValueV(x);
        assertEquals(3.72, des.desviacioEstandar(), 0.001);
    }
}
