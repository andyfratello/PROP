package test.functions.numerica;

import main.domain.classes.functions.numerica.CorrelacioPearson;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import test.functions.stubs.BlocReferenciesStub;
import test.functions.stubs.ReferenciaStub;
import main.domain.classes.types.Referencia;
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
        List<Referencia> x = new ArrayList<>();
        List<Referencia> y = new ArrayList<>();

        x.add(new ReferenciaStub(32.0) );
        x.add(new ReferenciaStub(6.0));
        x.add(new ReferenciaStub(64.0));
        x.add(new ReferenciaStub(256.0));
        x.add(new ReferenciaStub(512.0));

        y.add(new ReferenciaStub(1024.0));
        y.add(new ReferenciaStub(634532.0));
        y.add(new ReferenciaStub(344234.0));
        y.add(new ReferenciaStub(32312.0));
        y.add(new ReferenciaStub(34232434232.0));

        CorrelacioPearson cp = new CorrelacioPearson(List.of(new BlocReferenciesStub(x), new BlocReferenciesStub(y)) );
        assertEquals(0.8871231686530718, cp.GetResultat().asNumber().doubleValue(), 0.0);
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
        List<Referencia> x = new ArrayList<>();
        List<Referencia> y = new ArrayList<>();

        x.add(new ReferenciaStub(1.5) );
        x.add(new ReferenciaStub(1.3));
        x.add(new ReferenciaStub(2.5));

        y.add(new ReferenciaStub(4.0));
        y.add(new ReferenciaStub(9.7));
        y.add(new ReferenciaStub(4.5));

        CorrelacioPearson cp = new CorrelacioPearson(List.of(new BlocReferenciesStub(x), new BlocReferenciesStub(y)) );
        assertEquals(-0.56504709, cp.GetResultat().asNumber().doubleValue(), 0.00001);
    }
    
}
