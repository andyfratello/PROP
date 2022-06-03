package test.functions;

import main.domain.classes.functions.decimalABinari;
import main.domain.classes.types.Binari;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestDecimalABinari {
    /**
     * Objecte de la prova: Test del mètode decimalABinari() de la classe DecimalABinari.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un dígit enter.
     * Operativa: En aquest test es comprova que a partir d'un dígit enter positiu, es calcula correctament l'operació.
     * Primer es fa un setter del paràmetre en Integer i seguidament es calcula l'operació de BinariADecimal.
     */
    @Test
    public void  decimalABinariPositiu() {
        decimalABinari dab = new decimalABinari();
        dab.setValueI(5);
        assertEquals(new Binari(5).getValor(), dab.decimalABinari().getValor());
    }

    /**
     * Objecte de la prova: Test del mètode decimalABinari() de la classe DecimalABinari.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un dígit enter.
     * Operativa: En aquest test es comprova que a partir d'un dígit enter negatiu, es calcula correctament l'operació.
     * Primer es fa un setter del paràmetre en Integer i seguidament es calcula l'operació de DecimalABinari.
     */
    @Test
    public void decimalABinariNegatiu() {
        decimalABinari dab = new decimalABinari();
        dab.setValueI(-32);
        assertEquals(new Binari(-32).getValor(), dab.decimalABinari().getValor());
    }
}
