package test.types;

import main.domain.classes.types.Binari;
import main.domain.classes.types.Hexadecimal;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestHexadecimal {
    /**
     * Objecte de la prova: Test del mètode Hexadecimal() de la classe Hexadecimal
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Hexadecimal amb valor "0" predeterminadament.
     * Primer es crea un objecte hexadecimal sense paràmetres, es crida al mètode getValor() i es comprova que el resultat és
     * el correcte.
     */
    @Test
    public void Hexadecimal() {
        Hexadecimal hex = new Hexadecimal();
        assertEquals("0", hex.getValor());
    }

    /**
     * Objecte de la prova: Test del mètode Hexadecimal(String x) de la classe Hexadecimal
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal i es passa un String per paràmetre.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Hexadecimal amb un String per paràmetre.
     * Primer es crea un objecte hexadecimal amb un String per paràmetre, es crida al mètode getValor() i es comprova que el
     * resultat concorda amb el que s'ha passat per paràmetre.
     */
    @Test
    public void HexadecimalStringCorrecte() {
        Hexadecimal hex = new Hexadecimal("5c");
        assertEquals("5c", hex.getValor());
    }

    /**
     * Objecte de la prova: Test del mètode Hexadecimal(String x) de la classe Hexadecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal i es passa un String per paràmetre.
     * Operativa: En aquest test es comprova que no es crea correctament un objecte Hexadecimal amb un String per paràmetre i
     * es llença l'excepció NumberFormatException. Primer es crea un objecte hexadecimal amb un String per paràmetre, es
     * crida al mètode getValor() i es comprova que salta l'excepció.
     */
    @Test (expected=NumberFormatException.class)
    public void HexadecimalStringInorrecte() {
        Hexadecimal hex = new Hexadecimal("5k");
    }

    /**
     * Objecte de la prova: Test del mètode Hexadecimal(int x) de la classe Hexadecimal
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal i es passa un enter per paràmetre.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Hexadecimal amb un Integer positiu per paràmetre.
     * Primer es crea un objecte hexadecimal amb un Integer per paràmetre, es crida al mètode getValor() i es comprova que el
     * resultat concorda amb el que s'ha passat per paràmetre.
     */
    @Test
    public void HexadecimalIntPositiu() {
        Hexadecimal hex = new Hexadecimal(92);
        assertEquals("5c", hex.getValor());
    }

    /**
     * Objecte de la prova: Test del mètode Hexadecimal(int x) de la classe Hexadecimal
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal i es passa un enter per paràmetre.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Hexadecimal amb un Integer negatiu per paràmetre.
     * Primer es crea un objecte hexadecimal amb un Integer per paràmetre, es crida al mètode getValor() i es comprova que el
     * resultat concorda amb el que s'ha passat per paràmetre.
     */
    @Test
    public void HexadecimalIntNegatiu() {
        Hexadecimal hex = new Hexadecimal(-92);
        assertEquals("ffffffa4", hex.getValor());
    }

    /**
     * Objecte de la prova: Test del mètode setValor(String x) de la classe Hexadecimal
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal sense paràmetres.
     * Operativa: En aquest test es comprova que la funció setter guarda correctament a l'atribut String intern de la
     * classe Hexadecimal el valor String passat per paràmetre. Es comprova que el valor és Hexadecimal.
     */
    @Test
    public void setValorStringCorrecte() {
        Hexadecimal hex = new Hexadecimal();
        int res = hex.setValor("5c");
        assertEquals("5c", hex.getValor());
        assertEquals(1, res);
    }

    /**
     * Objecte de la prova: Test del mètode setValor(String x) de la classe Hexadecimal
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal sense paràmetres.
     * Operativa: En aquest test es comprova que la funció setter no guarda correctament a l'atribut String intern de la
     * classe Hexadecimal el valor String passat per paràmetre. Es comprova que el valor no és Hexadecimal.
     */
    @Test
    public void setValorInorrecte() {
        Hexadecimal hex = new Hexadecimal();
        int res = hex.setValor("5k");
        assertEquals("0",hex.getValor());
        assertEquals(0, res);
    }

    /**
     * Objecte de la prova: Test del mètode setValor(int x) de la classe Hexadecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal sense paràmetres.
     * Operativa: En aquest test es comprova que la funció setter guarda correctament a l'atribut String intern de la
     * classe Hexadecimal el valor Integer passat per paràmetre. Es passa el valor int a String i es comprova que el valor és
     * hexadecimal.
     */
    @Test
    public void setValorIntPositiu() {
        Hexadecimal hex = new Hexadecimal();
        int res = hex.setValor(92);
        assertEquals("5c", hex.getValor());
        assertEquals(1, res);
    }

    /**
     * Objecte de la prova: Test del mètode setValor(int x) de la classe Hexadecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal sense paràmetres.
     * Operativa: En aquest test es comprova que la funció setter guarda correctament a l'atribut String intern de la
     * classe Hexadecimal el valor Integer negatiu passat per paràmetre. Es passa el valor int a String i es comprova que el
     * valor és hexadecimal.
     */
    @Test
    public void setValorIntNegatiu() {
        Hexadecimal hex = new Hexadecimal();
        int res = hex.setValor(-92);
        assertEquals("ffffffa4", hex.getValor());
        assertEquals(1, res);
    }

    /**
     * Objecte de la prova: Test del mètode aDecimal() de la classe Hexadecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal amb enter com a paràmetre.
     * Operativa: En aquest test es comprova que la funció passa a integer el nombre hexadecimal que s'ha creat.
     * Es crea un objecte Hexadecimal amb un enter positiu com a paràmetre i es comprova que la funció el parseja correctament
     * a decimal.
     */
    @Test
    public void aDecimalPositiu() {
        Hexadecimal hex = new Hexadecimal(92);
        assertEquals(92, hex.aDecimal());
    }

    /**
     * Objecte de la prova: Test del mètode aDecimal() de la classe Hexadecimal.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal amb enter com a paràmetre.
     * Operativa: En aquest test es comprova que la funció passa a integer el nombre hexadecimal que s'ha creat.
     * Es crea un objecte Hexadecimal amb un enter negatiu com a paràmetre i es comprova que la funció el parseja correctament
     * a decimal.
     */
    @Test
    public void aDecimalNegatiu() {
        Binari bin = new Binari(-43);
        assertEquals(-43, bin.aDecimal());
    }

    /**
     * Objecte de la prova: Test del mètode aBinari(int x) de la classe Hexadecimal
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Hexadecimal amb enter com a paràmetre.
     * Operativa: En aquest test es comprova que la funció passa a Binari el nombre hexadecimal que s'ha creat.
     * Es crea un objecte Hexadecimal amb un enter com a paràmetre i es comprova que la funció el parseja correctament a
     * binari.
     */
    @Test
    public void aBinari() {
        Hexadecimal hex = new Hexadecimal(92);
        assertEquals(new Binari(92).getValor(), hex.aBinari().getValor());
    }

}
