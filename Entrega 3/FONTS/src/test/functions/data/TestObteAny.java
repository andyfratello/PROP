package test.functions.data;

import main.domain.classes.functions.data.obteAny;
import java.time.LocalDate;

import static org.junit.Assert.*;

import main.domain.classes.token.DateT;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestObteAny {
    /**
     * Objecte de la prova: Test del mètode obteAny() de la classe obteAny.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un LocalDate.
     * Operativa: En aquest test es calcula l'any d'una data (en LocalDate) entrada per paràmetre i es comprova que el
     * resultat és el correcte. Primer es fa un setter del paràmetre en LocalDate i seguidament es calcula l'operació
     * d'obteAny.
     */
    @Test
    public void obteAny() {
        LocalDate temps_actual = LocalDate.now();
        obteAny oa = new obteAny( new DateT(temps_actual) );
        assertEquals(temps_actual.getYear(), oa.GetResultat().asNumber().intValue());
    }
}
