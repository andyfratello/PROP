package test.functions.numerica;

import main.domain.classes.functions.numerica.Covariancia;

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
        List<Referencia> x = new ArrayList<>();
        List<Referencia> y = new ArrayList<>();

        x.add(new ReferenciaStub(1.8));
        x.add(new ReferenciaStub(1.5));
        x.add(new ReferenciaStub(2.1));
        x.add(new ReferenciaStub(2.4));
        x.add(new ReferenciaStub(0.2));

        y.add(new ReferenciaStub(2.5));
        y.add(new ReferenciaStub(4.3));
        y.add(new ReferenciaStub(4.5));
        y.add(new ReferenciaStub(4.1));
        y.add(new ReferenciaStub(2.2));

        Covariancia co = new Covariancia(List.of(new BlocReferenciesStub(x), new BlocReferenciesStub(y)) );
        assertEquals(0.63, co.GetResultat().asNumber().doubleValue(), 0.00001);
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
        List<Referencia> x = new ArrayList<>();
        List<Referencia> y = new ArrayList<>();

        x.add(new ReferenciaStub(1.5));
        x.add(new ReferenciaStub(1.3));
        x.add(new ReferenciaStub(2.5));

        y.add(new ReferenciaStub(4.0));
        y.add(new ReferenciaStub(9.7));
        y.add(new ReferenciaStub(4.5));

        Covariancia co = new Covariancia(List.of(new BlocReferenciesStub(x), new BlocReferenciesStub(y)) );
        assertEquals(-1.1466666, co.GetResultat().asNumber().doubleValue(), 0.00001);
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
        List<Referencia> x = new ArrayList<>();
        List<Referencia> y = new ArrayList<>();

        x.add(new ReferenciaStub(-23.4));
        x.add(new ReferenciaStub(3.2));
        x.add(new ReferenciaStub(45.2));
        x.add(new ReferenciaStub(-12.85));

        y.add(new ReferenciaStub(56.3));
        y.add(new ReferenciaStub(78.32));
        y.add(new ReferenciaStub(7.642));
        y.add(new ReferenciaStub(-11.43));

        Covariancia co = new Covariancia(List.of(new BlocReferenciesStub(x), new BlocReferenciesStub(y)) );
        assertEquals(-323.968100, co.GetResultat().asNumber().doubleValue(), 0.00001);
    }
}
