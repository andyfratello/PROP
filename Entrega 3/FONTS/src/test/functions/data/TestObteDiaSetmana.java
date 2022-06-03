package test.functions.data;

import main.domain.classes.functions.data.obteDiaSetmana;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

import main.domain.classes.token.DateT;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestObteDiaSetmana {
    /**
     * Objecte de la prova: Test del mètode obteDiaSetmana() de la classe obteDiaSetmana.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un LocalDate.
     * Operativa: En aquest test es calcula el dia de la setmana d'una data (en LocalDate) entrada per paràmetre i es
     * comprova que el dia de la setmana és el correcte. Primer es fa un setter del paràmetre en LocalDate i seguidament
     * es calcula l'operació d'obteDiaSetmana.
     */
    @Test
    public void obteDiaSetmana() {
        obteDiaSetmana ods;

        ods = new obteDiaSetmana(new DateT(LocalDate.of(2022, Month.MARCH, 21)));
        assertEquals("Dilluns", ods.GetResultat().asString());
        ods = new obteDiaSetmana(new DateT(LocalDate.of(2022, Month.MARCH, 22)));
        assertEquals("Dimarts", ods.GetResultat().asString());
        ods = new obteDiaSetmana(new DateT(LocalDate.of(2022, Month.MARCH, 23)));
        assertEquals("Dimecres", ods.GetResultat().asString());
        ods = new obteDiaSetmana(new DateT(LocalDate.of(2022, Month.MARCH, 24)));
        assertEquals("Dijous", ods.GetResultat().asString());
        ods = new obteDiaSetmana(new DateT(LocalDate.of(2022, Month.MARCH, 25)));
        assertEquals("Divendres", ods.GetResultat().asString());
        ods = new obteDiaSetmana(new DateT(LocalDate.of(2022, Month.MARCH, 26)));
        assertEquals("Dissabte", ods.GetResultat().asString());
        ods = new obteDiaSetmana(new DateT(LocalDate.of(2022, Month.MARCH, 27)));
        assertEquals("Diumenge", ods.GetResultat().asString());
    }
    
}
