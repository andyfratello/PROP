package test.functions.numerica;

import main.domain.classes.functions.numerica.Multiplicacio;
import java.util.Vector;

import static org.junit.Assert.*;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
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
        Vector<IVisitable> x = new Vector<>();
        x.add(new NumberT(32.0));
        x.add(new NumberT(6));
        x.add(new NumberT(12.7));

        Multiplicacio mul = new Multiplicacio(x);
        assertEquals(2438.4, mul.GetResultat().asNumber().doubleValue(), 0.1);
    }

}
