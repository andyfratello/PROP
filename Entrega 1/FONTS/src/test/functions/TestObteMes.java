package test.functions;

import main.domain.classes.functions.obteMes;
import java.time.LocalDate;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestObteMes {
    /**
     * Objecte de la prova: Test del mètode obteMes() de la classe obteMes.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un LocalDate.
     * Operativa: En aquest test es calcula el mes d'una data (en LocalDate) entrada per paràmetre i es comprova que el
     * resultat és el correcte. Primer es fa un setter del paràmetre en LocalDate i seguidament es calcula l'operació
     * d'obteMes.
     */
    @Test
    public void obteMes() {
        obteMes om = new obteMes();
        LocalDate temps_actual = LocalDate.now();
        om.setValueLD(temps_actual);
        assertEquals(temps_actual.getMonthValue(), om.obteMes());
    }
    
}
