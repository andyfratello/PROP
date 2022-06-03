package test.functions;

import main.domain.classes.functions.Mediana;
import java.util.Vector;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestMediana {
    /**
     * Objecte de la prova: Test del mètode mediana() de la classe Mediana.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir de dos vectors de tipus Double, es calcula correctament
     * l'operació. Primer es creen dos vectors de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter dels dos vectors i finalment es calcula l'operació de
     * Mediana i es comprova el correcte funcionament.
     */
    @Test
    public void mediana() {
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
        Mediana med = new Mediana();
        med.setValueV(x);
        assertEquals(768, med.mediana(), 0.0);
    }
    
}
