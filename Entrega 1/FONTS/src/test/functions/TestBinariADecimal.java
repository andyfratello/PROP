package test.functions;

import main.domain.classes.types.Binari;
import main.domain.classes.functions.binariADecimal;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestBinariADecimal {
    /**
     * Objecte de la prova: Test del mètode binariADecimal() de la classe BinariADecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Binari.
     * Operativa: En aquest test es comprova que a partir d'un dígit Binari positiu, es calcula correctament l'operació.
     * Primer es fa un setter del paràmetre en Binari i seguidament es calcula l'operació de BinariADecimal.
     */
    @Test
    public void binariADecimalPositiu() {
        binariADecimal bad = new binariADecimal();
        bad.setValueB(new Binari(5));
        assertEquals(5, bad.binariADecimal());
    }

    /**
     * Objecte de la prova: Test del mètode binariADecimal() de la classe BinariADecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Binari.
     * Operativa: En aquest test es comprova que a partir d'un dígit Binari negatiu, es calcula correctament l'operació.
     * Primer es fa un setter del paràmetre en Binari i seguidament es calcula l'operació de BinariADecimal.
     */
    @Test
    public void binariADecimalNegatiu() {
        binariADecimal bad = new binariADecimal();
        bad.setValueB(new Binari(-16));
        assertEquals(-16, bad.binariADecimal());
    }
    
}
