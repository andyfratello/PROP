package test.functions;

import main.domain.classes.functions.Suma;
import java.util.Vector;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestSuma {
    /**
     * Objecte de la prova: Test del mètode suma() de la classe Suma.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un vector de tipus Double.
     * Operativa: En aquest test es comprova que a partir d'un vector de tipus Double, es calcula correctament
     * l'operació amb un resultat amb decimals. Primer es crea un vector de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter del vector. Finalment es calcula l'operació de
     * Suma i es comprova el correcte funcionament.
     */
    @Test
    public void suma() {
        Vector<Double> x = new Vector<Double>();
        x.add(32.0);
        x.add(6.0);
        x.add(64.0);
        x.add(256.0);
        x.add(51.2);
        x.add(1024.0);
        x.add(634532.0);
        x.add(3.44230);
        x.add(32.312);
        x.add(4522.0);
        Suma sum = new Suma();
        sum.setValueV(x);
        assertEquals(640522.9543, sum.suma(), 0.0);
    }

}
