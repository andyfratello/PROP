package test.functions;

import main.domain.classes.functions.Covariancia;
import java.util.Vector;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestCovarianca {
    /**
     * Objecte de la prova: Test del mètode covariancia() de la classe Covariancia.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir de dos vectors de tipus Double, es calcula correctament
     * l'operació. Primer es creen dos vectors de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter dels dos vectors i finalment es calcula l'operació de
     * Covariancia i es comprova el correcte funcionament.
     */
    @Test
    public void covarianca1() {
        Vector<Double> x = new Vector<Double>();
        Vector<Double> y = new Vector<Double>();
        x.add(1.8);
        x.add(1.5);
        x.add(2.1);
        x.add(2.4);
        x.add(0.2);
        y.add(2.5);
        y.add(4.3);
        y.add(4.5);
        y.add(4.1);
        y.add(2.2);
        Covariancia co = new Covariancia();
        co.setValue2V(x, y);
        assertEquals(0.63, co.covariancia(), 0.00001);
    }

    /**
     * Objecte de la prova: Test del mètode covariancia() de la classe Covariancia.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir de dos vectors de tipus Double, es calcula correctament
     * l'operació amb un resultat amb molts decimals. Primer es creen dos vectors de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter dels dos vectors i finalment es calcula l'operació de
     * Covariancia i es comprova el correcte funcionament.
     */
    @Test
    public void covarianca2() {
        Vector<Double> x = new Vector<Double>();
        Vector<Double> y = new Vector<Double>();
        x.add(1.5);
        x.add(1.3);
        x.add(2.5);
        y.add(4.0);
        y.add(9.7);
        y.add(4.5);
        Covariancia co = new Covariancia();
        co.setValue2V(x, y);
        assertEquals(-1.1466666, co.covariancia(), 0.00001);
    }

    /**
     * Objecte de la prova: Test del mètode covariancia() de la classe Covariancia.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir de dos vectors de tipus Double (amb dígits positius i negatius), es calcula correctament
     * l'operació. Primer es creen dos vectors de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter dels dos vectors i finalment es calcula l'operació de
     * Covariancia i es comprova el correcte funcionament.
     */
    @Test
    public void covarianca3() {
        Vector<Double> x = new Vector<Double>();
        Vector<Double> y = new Vector<Double>();
        x.add(-23.4);
        x.add(3.2);
        x.add(45.2);
        x.add(-12.85);
        y.add(56.3);
        y.add(78.32);
        y.add(7.642);
        y.add(-11.43);
        Covariancia co = new Covariancia();
        co.setValue2V(x, y);
        assertEquals(-323.968100, co.covariancia(), 0.00001);
    }
}
