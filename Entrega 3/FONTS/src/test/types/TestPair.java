package test.types;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import main.domain.classes.types.Pair;
import org.junit.*;

/**
 * Classe de testeig de Pair.java
 * @author Marc Duch (marc.duch@estudiantat.upc.edu)
 */
public class TestPair {
    /**
     * Objecte de la prova: Test de la constructora de la classe Pair
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Pair.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Pair amb valors 12, "12"
     * predeterminadament. Primer es crea un objecte Pair i després es fa un assertEquals() dels dos elements amb first() i second()
     */
    @Test
    public void TestConstructora() {
        Pair<Integer,String> p1 = new Pair<>(12, "12");
        assertEquals(p1.first(), (Integer)12);
        assertEquals(p1.second(), "12");
    }

    /**
     * Objecte de la prova: Test del mètode setFirst() de Pair
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Pair i se li canvia el primer element.
     * Operativa: En aquest test es comprova que després de fer  setFirst() de 123, el .first() retornara el nou valor.
     */
    @Test
    public void TestSetFirst() {
        Pair<Integer,String> p1 = new Pair<>(111,"poma");
        p1.setFirst(123);
        assertEquals((Integer)123,p1.first());
    }

    /**
     * Objecte de la prova: Test del mètode setSecond() de Pair
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Pair i se li canvia el segon element.
     * Operativa: En aquest test es comprova que després de fer  setSecond() de "123", el .second() retorna el nou valor.
     */
    @Test
    public void TestSetSecond() {
        Pair<Integer,String> p1 = new Pair<>(111,"poma");
        p1.setSecond("123");
        assertEquals("123",p1.second());
    }

    /**
     * Objecte de la prova: Test del mètode first() de Pair
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Pair i es comprova el primer element.
     * Operativa: En aquest test es comprova que després de de crear un nou  Pair el  .first() retornara el assignat a la creadora.
     */
    @Test
    public void TestFirst() {
        Pair<Integer,String> p1 = new Pair<>(111,"poma");
        assertEquals((Integer)111,p1.first());
    }

    /**
     * Objecte de la prova: Test del mètode second() de Pair
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Pair i es comprova el primer element.
     * Operativa: En aquest test es comprova que després de crear un nou Pair, el second()  retornara el valor assignat a la creadora.
     */
    @Test
    public void TestSecond() {
        Pair<Integer,String> p1 = new Pair<>(111,"poma");
        assertEquals("poma",p1.second());
    }

    /**
     * Objecte de la prova: Test del mètode equals() de Pair
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es creen 6 pairs per efectuar diferents proves sobre equals().
     * Operativa: En aquest test es comprova que el mètode equals() només sigui cert quan els Pairs son idèntics en tipus i valors, o si son el mateix element (pair)
     */
    @Test
    public void TestEquals() {
        Pair<Integer,Boolean> p1 = new Pair<>(12,true);
        Pair<Double,Integer> p2 = new Pair<>(12.0,1);
        assertNotEquals(p1,p2);

		Pair<Integer,String> p3 = new Pair<>(1,"2");
		Pair<Integer,String> p4 = new Pair<>(1,"2");
		assertEquals(p3,p4);

		Pair<Integer,Integer> p5, p6;
		p5 = p6 = new Pair<>(0,0);
		assertEquals(p5,p6);
		assertSame(p5,p6);
	}

    /**
     * Objecte de la prova: Test del mètode toString() de Pair
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte Pair i es comprova el String resultatnt de toString().
     * Operativa: En aquest test es comprova que després d'executar el mètode toString(), l’string resultatnt estigui en el format adequat
     */
    @Test
    public void TestToString() {
        Pair<Integer,Integer> p1 = new Pair<>(12,12);
        assertEquals(p1.toString(), "12 12");
    }

}