package test.functions;

import main.domain.classes.functions.decimalAHexadecimal;
import main.domain.classes.types.Hexadecimal;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestDecimalAHexadecimal {
    /**
     * Objecte de la prova: Test del mètode decimalAHexadecimal() de la classe DecimalAHexadecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un dígit enter.
     * Operativa: En aquest test es comprova que a partir d'un dígit enter positiu, es calcula correctament l'operació.
     * Primer es fa un setter del paràmetre en Integer i seguidament es calcula l'operació de DecimalAHexadecimal.
     */
    @Test
    public void decimalAHexadecimalPositiu() {
        decimalAHexadecimal dah = new decimalAHexadecimal();
        dah.setValueI(92);
        assertEquals(new Hexadecimal(92).getValor(),dah.decimalAHexadecimal().getValor());

    }

    /**
     * Objecte de la prova: Test del mètode decimalAHexadecimal() de la classe DecimalAHexadecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un dígit enter.
     * Operativa: En aquest test es comprova que a partir d'un dígit enter negatiu, es calcula correctament l'operació.
     * Primer es fa un setter del paràmetre en Integer i seguidament es calcula l'operació de DecimalAHexadecimal.
     */
    @Test
    public void decimalAHexadecimalNegatiu() {
        decimalAHexadecimal dah = new decimalAHexadecimal();
        dah.setValueI(-156);
        assertEquals(new Hexadecimal(-156).getValor(),dah.decimalAHexadecimal().getValor());
    }
    
}
