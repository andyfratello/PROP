package test.functions.numerica;

import main.domain.classes.functions.numerica.Resta;

import static org.junit.Assert.*;

import main.domain.classes.token.NumberT;
import org.junit.Test;

import java.util.List;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestResta {
    /**
     * Objecte de la prova: Test del mètode resta() de la classe Resta.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir de dos Doubles positius, es calcula correctament l'operació
     * resta entre els dos dígits. Primer es fa un setter dels paràmetres en Double i seguidament es calcula l'operació
     * de Resta i s'en comprova el correcte resultat.
     */
    @Test
    public void resta() {
        Resta res = new Resta(List.of(new NumberT(2121), new NumberT(7272)));
        assertEquals(-5151.0, res.GetResultat().asNumber().doubleValue(), 0.0);
    }
    
}
