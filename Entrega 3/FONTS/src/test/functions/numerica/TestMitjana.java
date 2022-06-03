package test.functions.numerica;

import main.domain.classes.functions.numerica.Mitjana;
import java.util.Vector;

import static org.junit.Assert.*;

import main.domain.classes.token.IVisitable;
import main.domain.classes.token.NumberT;
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
        Vector<IVisitable> x = new Vector<>();
        x.add(new NumberT(32));
        x.add(new NumberT(6));
        x.add(new NumberT(64));
        x.add(new NumberT(256));
        x.add(new NumberT(512));
        x.add(new NumberT(1024));
        x.add(new NumberT(634531.5));
        x.add(new NumberT(344234.5));
        x.add(new NumberT(32312));
        x.add(new NumberT(4522));

        Mitjana mit = new Mitjana(x);
        assertEquals(101749.4, mit.GetResultat().asNumber().doubleValue(), 0.0);
    }
}
