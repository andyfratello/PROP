package test.functions.numerica;

import main.domain.classes.functions.numerica.Variancia;

import static org.junit.Assert.*;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
import org.junit.Test;

import java.util.Vector;

public class TestVariancia {
    /**
     * Objecte de la prova: Test del mètode variancia() de la classe Variancia.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir d'un vector de tipus Double, es calcula correctament
     * l'operació amb un resultat amb decimals. Primer es crea un vector de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter del vector. Finalment es calcula l'operació de
     * Variancia i es comprova el correcte funcionament.
     */
    @Test
    public void variancia1() {
        Vector<IVisitable> x = new Vector<>();
        x.add(new NumberT(600.0));
        x.add(new NumberT(470.0));
        x.add(new NumberT(170.0));
        x.add(new NumberT(430.0));
        x.add(new NumberT(300.0));
        Variancia var = new Variancia(x);
        assertEquals(21704, var.GetResultat().asNumber().doubleValue(), 0.00001);
    }

    /**
     * Objecte de la prova: Test del mètode variancia() de la classe Variancia.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir de dos vectors de tipus Double, es calcula correctament
     * l'operació amb un resultat amb decimals. Primer es creen dos vectors de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter dels dos vectors. Finalment es calcula l'operació de
     * Variancia i es comprova el correcte funcionament.
     */
    @Test
    public void variancia2() {
        Vector<IVisitable> x = new Vector<>();
        x.add(new NumberT(2.0));
        x.add(new NumberT(7.0));
        x.add(new NumberT(3.0));
        x.add(new NumberT(12.0));
        x.add(new NumberT(9.0));
        Variancia var = new Variancia(x);

        assertEquals(13.84, var.GetResultat().asNumber().doubleValue(), 0.00001);
    }
}
