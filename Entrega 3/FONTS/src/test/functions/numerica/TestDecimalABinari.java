package test.functions.numerica;

import main.domain.classes.functions.numerica.decimalABinari;
import main.domain.classes.token.NumberT;
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
        decimalABinari dab = new decimalABinari(new NumberT(5));
        Binari res = (Binari)dab.GetResultat().asNumber();
        assertEquals(new Binari(5).getValor(), res.getValor());
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
        decimalABinari dab = new decimalABinari( new NumberT(-32));
        Binari res = (Binari)dab.GetResultat().asNumber();
        assertEquals(new Binari(-32).getValor(), res.getValor());
    }
}
