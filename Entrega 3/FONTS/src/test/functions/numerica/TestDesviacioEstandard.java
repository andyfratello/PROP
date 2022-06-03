package test.functions.numerica;

import main.domain.classes.functions.numerica.DesviacioEstandard;

import static org.junit.Assert.*;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDesviacioEstandard {
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
        List<Double> x = new ArrayList<>();
        x.add(600.0);
        x.add(470.0);
        x.add(170.0);
        x.add(430.0);
        x.add(300.0);

        List<IVisitable> p = new ArrayList<>();
        for (double d : x) p.add(new NumberT(d));

        DesviacioEstandard des = new DesviacioEstandard(p);
        assertEquals(147.323, des.GetResultat().asNumber().doubleValue(), 0.001);
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
        List<Double> x = new ArrayList<>();
        x.add(2.0);
        x.add(7.0);
        x.add(3.0);
        x.add(12.0);
        x.add(9.0);

        List<IVisitable> p = new ArrayList<>();
        for (double d : x) p.add(new NumberT(d));

        DesviacioEstandard des = new DesviacioEstandard(p);
        assertEquals(3.72, des.GetResultat().asNumber().doubleValue(), 0.001);
    }
}
