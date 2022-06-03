package test.functions;

import main.domain.classes.functions.Multiplicacio;
import java.util.Vector;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestMultiplicacio {
    /**
     * Objecte de la prova: Test del mètode multiplicacio() de la classe Multiplicacio.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir de dos vectors de tipus Double, es calcula correctament
     * l'operació. Primer es creen dos vectors de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter dels dos vectors. Finalment es calcula l'operació de
     * Multiplicacio i es comprova el correcte funcionament.
     */
    @Test
    public void multiplicacio() {
        Vector<Double> x = new Vector<Double>();
        x.add(32.0);
        x.add(6.0);
        x.add(12.7);
        Multiplicacio mul = new Multiplicacio();
        mul.setValueV(x);
        assertEquals(2438.4, mul.multiplicacio(), 0.1);
    }

}
