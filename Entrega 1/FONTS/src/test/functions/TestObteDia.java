package test.functions;

import main.domain.classes.functions.obteDia;
import java.time.LocalDate;

import static org.junit.Assert.*;
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
        obteDia od = new obteDia();
        LocalDate temps_actual = LocalDate.now();
        od.setValueLD(temps_actual);
        assertEquals(temps_actual.getDayOfMonth(), od.obteDia());
    }
    
}
