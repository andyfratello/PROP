package test.functions;

import main.domain.classes.functions.CorrelacioPearson;
import java.util.Vector;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestCorrelacioPearson {
    /**
     * Objecte de la prova: Test del mètode correlacioPearson() de la classe CorrelacioPearson.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir de dos vectors de tipus Double, es calcula correctament
     * l'operació amb un resultat amb molts decimals. Primer es creen dos vectors de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter dels dos vectors i finalment es calcula l'operació de
     * CorrelacioPearson i es comprova el correcte funcionament.
     */
    @Test
    public void correlacioPearson1() {
        Vector<Double> x = new Vector<Double>();
        Vector<Double> y = new Vector<Double>();
        x.add(32.0);
        x.add(6.0);
        x.add(64.0);
        x.add(256.0);
        x.add(512.0);
        y.add(1024.0);
        y.add(634532.0);
        y.add(344234.0);
        y.add(32312.0);
        y.add(34232434232.0);
        CorrelacioPearson cp = new CorrelacioPearson();
        cp.setValue2V(x, y);
        assertEquals(0.8871231686530718, cp.correlacioPearson(), 0.0);
    }

    /**
     * Objecte de la prova: Test del mètode correlacioPearson() de la classe CorrelacioPearson.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos vectors de tipus Double.
     * Operativa: En aquest test es comprova que a partir de dos vectors de tipus Double, es calcula correctament
     * l'operació amb un resultat negatiu. Primer es creen dos vectors de Double i s'afegeixen els valors amb el qual es vol fer el test de
     * l'operació i, seguidament, es fa un setter dels dos vectors i finalment es calcula l'operació de
     * CorrelacioPearson i es comprova el correcte funcionament.
     */
    @Test
    public void correlacioPearson2() {
        Vector<Double> x = new Vector<Double>();
        Vector<Double> y = new Vector<Double>();
        x.add(1.5);
        x.add(1.3);
        x.add(2.5);
        y.add(4.0);
        y.add(9.7);
        y.add(4.5);
        CorrelacioPearson cp = new CorrelacioPearson();
        cp.setValue2V(x, y);
        assertEquals(-0.56504709, cp.correlacioPearson(), 0.00001);
    }
    
}
