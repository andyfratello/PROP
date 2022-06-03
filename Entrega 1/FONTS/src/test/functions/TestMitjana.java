package test.functions;

import main.domain.classes.functions.Mitjana;
import java.util.Vector;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestMitjana {
    /**
     * Objecte de la prova: Test del mètode mitjana() de la classe Mitjana.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir de dos vectors de tipus Double, es calcula correctament
     * l'operació. Primer es creen dos vectors de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter dels dos vectors i finalment es calcula l'operació de
     * Mitjana i es comprova el correcte funcionament.
     */
    @Test
    public void mitjana() {
        Vector<Double> x = new Vector<Double>();
        x.add(32.0);
        x.add(6.0);
        x.add(64.0);
        x.add(256.0);
        x.add(512.0);
        x.add(1024.0);
        x.add(634532.0);
        x.add(344234.0);
        x.add(32312.0);
        x.add(4522.0);
        Mitjana mit = new Mitjana();
        mit.setValueV(x);
        assertEquals(101749.4, mit.mitjana(), 0.0);
    }
}
