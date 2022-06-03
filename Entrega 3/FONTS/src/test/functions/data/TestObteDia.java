package test.functions.data;

import main.domain.classes.functions.data.obteDia;
import java.time.LocalDate;

import static org.junit.Assert.*;

import main.domain.classes.token.DateT;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestObteDia {
    /**
     * Objecte de la prova: Test del mètode obteDia() de la classe obteDia.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un LocalDate.
     * Operativa: En aquest test es calcula el dia d'una data (en LocalDate) entrada per paràmetre i es comprova que el
     * resultat és el correcte. Primer es fa un setter del paràmetre en LocalDate i seguidament es calcula l'operació
     * d'obteDia.
     */
    @Test
    public void obteDia() {
        LocalDate temps_actual = LocalDate.now();
        obteDia od = new obteDia(new DateT(temps_actual));
        assertEquals(temps_actual.getDayOfMonth(), od.GetResultat().asNumber().intValue());
    }
    
}
