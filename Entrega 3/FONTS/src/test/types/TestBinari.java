package test.types;

import main.domain.classes.types.Binari;
import main.domain.classes.types.Hexadecimal;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestBinari {
    /**
     * Objecte de la prova: Test del mètode Binari() de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Binari amb valor "0" predeterminadament.
     * Primer es crea un objecte binari sense paràmetres, es crida al mètode getValor() i es comprova que el resultat és
     * el correcte.
     */
    @Test
    public void Binari() {
        Binari bin = new Binari();
        assertEquals("0b0",bin.getValor());
    }

    /**
     * Objecte de la prova: Test del mètode Binari(String x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari i es passa un String per paràmetre.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Binari amb un String per paràmetre.
     * Primer es crea un objecte binari amb un String per paràmetre, es crida al mètode getValor() i es comprova que el
     * resultat concorda amb el que s'ha passat per paràmetre.
     */
    @Test
    public void BinariStringCorrecte() {
        Binari bin = new Binari("101");
        assertEquals("0b101",bin.getValor());
    }

    /**
     * Objecte de la prova: Test del mètode Binari(String x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari i es passa un String per paràmetre.
     * Operativa: En aquest test es comprova que no es crea correctament un objecte Binari amb un String per paràmetre i
     * es llença l'excepció NumberFormatException. Primer es crea un objecte binari amb un String per paràmetre, es
     * crida al mètode getValor() i es comprova que salta l'excepció
     */
    @Test (expected=NumberFormatException.class)
    public void BinariStringIncorrecte() throws NumberFormatException{
        Binari bin = new Binari("17");
    }

    /**
     * Objecte de la prova: Test del mètode Binari(String x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari i es passa un String per paràmetre.
     * Operativa: En aquest test es comprova que no es crea correctament un objecte Binari amb un String per paràmetre i
     * es llença l'excepció NumberFormatException. Primer es crea un objecte binari amb un String per paràmetre, es
     * crida al mètode getValor() i es comprova que salta l'excepció
     */
    @Test (expected=NumberFormatException.class)
    public void BinariStringIncorrecte0b() throws NumberFormatException{
        Binari bin = new Binari("0b17");
    }

    /**
     * Objecte de la prova: Test del mètode Binari(int x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari i es passa un enter per paràmetre.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Binari amb un Integer positiu per paràmetre.
     * Primer es crea un objecte binari amb un Integer per paràmetre, es crida al mètode getValor() i es comprova que el
     * resultat concorda amb el que s'ha passat per paràmetre.
     */
    @Test
    public void BinariIntPositiu() {
        Binari bin = new Binari(5);
        assertEquals("0b101",bin.getValor());
    }

    /**
     * Objecte de la prova: Test del mètode Binari(int x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari i es passa un enter per paràmetre.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Binari amb un Integer negatiu per paràmetre.
     * Primer es crea un objecte binari amb un Integer per paràmetre, es crida al mètode getValor() i es comprova que el
     * resultat concorda amb el que s'ha passat per paràmetre.
     */
    @Test
    public void BinariIntNegatiu() {
        Binari bin = new Binari(-5);
        assertEquals("0b11111111111111111111111111111011",bin.getValor());
    }

    /**
     * Objecte de la prova: Test del mètode setValor(String x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari sense paràmetres.
     * Operativa: En aquest test es comprova que la funció setter guarda correctament a l'atribut String intern de la
     * classe Binari el valor String passat per paràmetre. Es comprova que el valor és Binari.
     */
    @Test
    public void setValorStringCorrecte() {
        Binari bin = new Binari();
        boolean res = bin.setValor("101");
        assertEquals("0b101",bin.getValor());
        assertTrue(res);
    }

    /**
     * Objecte de la prova: Test del mètode setValor(String x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari sense paràmetres.
     * Operativa: En aquest test es comprova que la funció setter no guarda correctament a l'atribut String intern de la
     * classe Binari el valor String passat per paràmetre. Es comprova que el valor no és Binari.
     */
    @Test
    public void setValorStringIncorrecte() {
        Binari bin = new Binari();
        boolean res = bin.setValor("17");
        assertEquals("0b0",bin.getValor());
        assertFalse(res);
    }

    /**
     * Objecte de la prova: Test del mètode setValor(int x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari sense paràmetres.
     * Operativa: En aquest test es comprova que la funció setter guarda correctament a l'atribut String intern de la
     * classe Binari el valor Integer passat per paràmetre. Es passa el valor int a String i es comprova que el valor és
     * binari.
     */
    @Test
    public void setValorIntPositiu() {
        Binari bin = new Binari();
        boolean res = bin.setValor(5);
        assertEquals("0b101",bin.getValor());
        assertTrue(res);
    }

    /**
     * Objecte de la prova: Test del mètode setValor(int x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari sense paràmetres.
     * Operativa: En aquest test es comprova que la funció setter guarda correctament a l'atribut String intern de la
     * classe Binari el valor Integer negatiu passat per paràmetre. Es passa el valor int a String i es comprova que el
     * valor és binari.
     */
    @Test
    public void setValorIntNegatiu() {
        Binari bin = new Binari();
        boolean res = bin.setValor(-5);
        assertEquals("0b11111111111111111111111111111011",bin.getValor());
        assertTrue(res);
    }

    /**
     * Objecte de la prova: Test del mètode aDecimal() de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari amb enter com a paràmetre.
     * Operativa: En aquest test es comprova que la funció passa a integer el nombre binari que s'ha creat.
     * Es crea un objecte Binari amb un enter positiu com a paràmetre i es comprova que la funció el parseja correctament
     * a decimal.
     */
    @Test
    public void aDecimalPositiu() {
        Binari bin = new Binari(5);
        assertEquals(5, bin.aDecimal());
    }

    /**
     * Objecte de la prova: Test del mètode aDecimal() de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari amb enter com a paràmetre.
     * Operativa: En aquest test es comprova que la funció passa a integer el nombre binari que s'ha creat.
     * Es crea un objecte Binari amb un enter negatiu com a paràmetre i es comprova que la funció el parseja correctament
     * a decimal.
     */
    @Test
    public void aDecimalNegatiu() {
        Binari bin = new Binari(-16);
        assertEquals(-16, bin.aDecimal());
    }

    /**
     * Objecte de la prova: Test del mètode aHexadecimal(int x) de la classe Binari
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Binari amb enter com a paràmetre.
     * Operativa: En aquest test es comprova que la funció passa a Hexadecimal el nombre binari que s'ha creat.
     * Es crea un objecte Binari amb un enter com a paràmetre i es comprova que la funció el parseja correctament a
     * hexadecimal.
     */
    @Test 
    public void aHexadecimal() {
        Binari bin = new Binari(73);
        assertEquals(new Hexadecimal(73).getValor(), bin.aHexadecimal().getValor());
    }

    @Test
    public void TestEsBinari()
    {
        assertEquals(new Binari(-13), new Binari("0b111111111111111111111111111110011") );
        assertEquals(-13, new Binari("0b111111111111111111111111111110011").aDecimal());
        assertTrue(Binari.esBinari("0b111111111111111111111111111110011"));
    }
}
