package test.functions;

import main.domain.classes.functions.hexadecimalADecimal;
import main.domain.classes.types.Hexadecimal;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestHexadecimalADecimal {
    /**
     * Objecte de la prova: Test del mètode hexadecimalADecimal() de la classe hexadecimalADecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Hexadecimal.
     * Operativa: En aquest test es comprova que a partir d'un dígit Hexadecimal positiu, es calcula correctament
     * l'operació. Primer es fa un setter del paràmetre en Hexadecimal i seguidament es calcula l'operació de
     * hexadecimalADecimal.
     */
    @Test
    public void hexadecimalADecimalPositiu() {
        hexadecimalADecimal had = new hexadecimalADecimal();
        had.setValueH(new Hexadecimal(2678));
        assertEquals(2678, had.hexadecimalADecimal());
    }

    /**
     * Objecte de la prova: Test del mètode hexadecimalADecimal() de la classe hexadecimalADecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un Hexadecimal.
     * Operativa: En aquest test es comprova que a partir d'un dígit Hexadecimal negatiu, es calcula correctament
     * l'operació. Primer es fa un setter del paràmetre en Hexadecimal i seguidament es calcula l'operació de
     * hexadecimalADecimal.
     */
    @Test
    public void hexadecimalADecimalNegatiu() {
        hexadecimalADecimal had = new hexadecimalADecimal();
        had.setValueH(new Hexadecimal(-9894));
        assertEquals(-9894, had.hexadecimalADecimal());
    }
    
}
