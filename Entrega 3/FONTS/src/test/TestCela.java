package test;

import main.domain.classes.Cela;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de testeig de Cela.java
 * @author Marc Duch (marc.duch@estudiantat.upc.edu)
 */
public class TestCela
{
    /**
     * Objecte de la prova: Test de la constructora de Cela
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Cela i es comprova que el valorUsuari sigui el mateix.
     * Operativa: Creem una nova Cela amb el paràmetre "test" i comprovem que el valorUsuari sigui el mateix.
     */
    @Test
    public void TestConstructora()
    {
        Cela c = new Cela("test");
        assertEquals("ValorUsuari igual", "test", c.GetValorUsuari());
    }

    /**
     * Objecte de la prova: Test del mètode EsFuncio() de Cela
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Cela i si el que conté és o no funció.
     * Operativa: Creem una nova Cela amb el paràmetre "test" i comprovem que el contingut es o no una funció i retorna false si no ho és.
     */
    @Test
    public void TestEsFuncio()
    {
        Cela c = new Cela("cela");
        assertFalse(c.EsFuncio());
    }

    /**
     * Objecte de la prova: Test del mètode GetValorUsuari() de Cela
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Cela amb un String com a paràmetre
     * Operativa: Creem una nova Cela amb un paràmetre String i comprovem que el contingut del valor usuari és el mateix que l’introduït.
     */
    @Test
    public void TestGetValorUsuari()
    {
        Cela c = new Cela("Patata");
        assertEquals(c.GetValorUsuari(), "Patata");
    }

    /**
     * Objecte de la prova: Test del mètode ModificaValor() de Cela
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Cela amb un String com a paràmetre.
     * Operativa: Creem una nova Cela amb un paràmetre String i es crida al mètode ModificaValor amb un altre String.
     * Seguidament comprovem que efectivament s’ha modificat amb el String que hem introduït.
     */
    @Test
    public void TestModificaValor()
    {
        Cela c = new Cela("old");
        c.ModificaValor("new", 0, 0);
        assertEquals(c.GetValorUsuari(),"new");
    }

    /**
     * Objecte de la prova: Test del mètode equals() de Cela
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es creen tres objectes Cela amb un String com a paràmetre.
     * Operativa: Creem tres noves Cel·les, dues amb el mateix paràmetre String i una tercera que serà igual que la
     * primera i comprovem que quan cridem a equals() entre la primera i la segona surt false (ja que tot i tenir el
     * mateix valor les cel·les són diferents) i quan cridem a equals() entre la primera i la tercera surt true (perquè
     * són la mateixa cel·la exactament).
     */
    @Test
    public void TestEquals()
    {
        Cela c1 = new Cela("c");
        Cela c2 = new Cela("c");
        Cela c3 = c1;

        assertEquals(c1, c3);
        assertEquals(c3, c3);

        assertNotEquals(c1, c2);
        assertNotEquals(c2, c1);
    }

    /**
     * Objecte de la prova: Test del mètode toString() de Cela
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Cela amb un String com a paràmetre
     * Operativa: Creem una nova Cela amb un paràmetre String i comprovem que el string introduït és el mateix que quan
     * cridem al mètode toString(), que simplement imprimeix el contingut de la cel·la
     */
    @Test
    public void TestToString()
    {
        Cela c = new Cela("c");
        assertEquals(c.toString(),"c");
    }

    /**
     * Objecte de la prova: Test del mètode comareTo() de Cela.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es creen 10 cel·les amb diferents valors i es comprova que el compareTo de les dues cel·les sigui l’esperat (ordenació descendent) i que si es comparen les mateixes cel·les en ordre invers, el resultat s’invertirà.
     * S’espera que torni -1 si la cel·la que executa compareTo(celComparada) es anterior a la comparada, 0 si son iguals i 1 si aquesta es posterior.
     * Operativa: El primer assertEquals() comprova que una parella de cel·les son iguals independentment de si son majúscules.
     * El segon assertEquals() comprova  entre números i lletres es faci correctament, és a dir, primer les lletres i després els números
     * El tercer assertEquals() comprova que entre una parella de cel·les, els números enters es comparin adequadament, 12 anterior a 11 (descendent).
     * El quart assertEquals() comprova que una parella de cel·les que tenen numeros i lletres s’ordenin adequadament.
     * El cinquè assertEquals() comprova que una parella de cel·les tenen funcioni correctament amb números decimals.
     * El cinquè assertEquals() comprova que una parella de cel·les tenen funcioni correctament amb números negatius.
     */
    @Test
    public void TestCompareTo()
    {
        Cela c1 = new Cela("abc");
        Cela c2 = new Cela("Abc");

        Cela c3 = new Cela("12");
        Cela c4 = new Cela("11");

        Cela c5 = new Cela("1a");
        Cela c6 = new Cela("1B");

        Cela c7 = new Cela("1.0");
        Cela c8 = new Cela("100");

        Cela c9 = new Cela("-100");
        Cela c10 = new Cela("0");

        //(-1) z - a ; INF - 0 (1)
        assertEquals("1 - 2",   0 , c1.compareTo(c2));
        assertEquals("2 - 1",   0 , c2.compareTo(c1));
        assertEquals("1 - 3",   -1, c1.compareTo(c3));
        assertEquals("3 - 1",   1 , c3.compareTo(c1));
        assertEquals("3 - 4",   -1, c3.compareTo(c4));
        assertEquals("4 - 3",   1 , c4.compareTo(c3));
        assertEquals("5 - 6",   1 , c5.compareTo(c6));
        assertEquals("6 - 5",   -1, c6.compareTo(c5));
        assertEquals("7 - 8",   1 , c7.compareTo(c8));
        assertEquals("8 - 7",   -1, c8.compareTo(c7));
        assertEquals("9 - 10",  1 , c9.compareTo(c10));
        assertEquals("10 - 9",  -1, c10.compareTo(c9));
    }

}
