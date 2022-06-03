package test;

import main.domain.classes.Full;
import main.domain.classes.Cela;
import main.domain.classes.types.Pair;

import org.junit.*;

import java.io.*;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.lang.IndexOutOfBoundsException;
import java.lang.NegativeArraySizeException;

/**
 * Classe de testeig de Full.java
 * @author Marc Duch (marc.duch@estudiantat.upc.edu)
 */
public class TestFull
{
    private static Full _full;

    /**
     * Inicialitzacio estandard de full amb 10 columnes i 10 files
     * per tal de poder efectuar les proves que requereixin cel·les
     */
    @Before
    public void setUp()
    {
        _full = new Full(1,10,10);

        _full.ModificaCela(0,0,"A1");
        _full.ModificaCela(1,1,"B2");
        _full.ModificaCela(1,2,"B3");
        _full.ModificaCela(2,4,"C5");
        _full.ModificaCela(5,5,"F6");
    }

    /** Metode per imprimir el contingut de Full, per veure visualment els canvis */
    @After
    public void printFull()
    {
        System.out.println();
        if (_full != null )_full.PrintFull();
        System.out.println();
    }

    // -------- Constructores --------
    /**
     * Objecte de la prova: Test de les constructores de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es creen 3 fulls per provar les 3 constructores diferents.
     * Operativa: Creem els fulls amb les constructores i comprovem que els valors obtinguts per els getters singuin els mateixos que els introduits.
     */
    @Test
    public void TestConstructora()
    {
        System.out.println("Test Constructores");
        _full = null;
        System.out.println("Full 1");
        Full f1 = new Full(1);
        assertEquals(f1.GetID(),1);
        assertEquals(f1.GetNomFull(),"Full 1");
        assertEquals(f1.GetNumCol(),50);
        assertEquals(f1.GetNumFil(),100);
        f1.PrintFull();

        System.out.println("Full 2");
        Full f2 = new Full(2, 20, 7);
        assertEquals(f2.GetID(),2);
        assertEquals(f2.GetNomFull(),"Full 2");
        assertEquals(f2.GetNumCol(),20);
        assertEquals(f2.GetNumFil(),7);
        f2.PrintFull();

        System.out.println("Nom 3");
        Full f3 = new Full(3,"Nom 3", 10, 3);
        assertEquals(f3.GetID(),3);
        assertEquals(f3.GetNomFull(),"Nom 3");
        assertEquals(f3.GetNumCol(),10);
        assertEquals(f3.GetNumFil(),3);
        f3.PrintFull();
    }

    /**
     * Objecte de la prova: Test de les constructores de Full amb errors
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es creen un full amb un número de columnes negatiu
     * Operativa: Quan es crea un full amb el numero de files o de columnes inferior a 0 (negatiu) llença una excepció de tipus NegativeArraySizeException
     */
    @Test(expected=NegativeArraySizeException.class)
    public void TestConstructoraExcepcio() { Full full = new Full(1,"Full amb indexos incorrectes",-1, 0); }

    // -------- Getters --------
    /**
     * Objecte de la prova: Test del mètode GetID() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou
     * Operativa: Creem una nova Cela amb el paramatre “test” i comprovem que el valorUsuari sigui el mateix
     */
    @Test
    public void TestGetID() {
        assertEquals("IDs iguals", _full.GetID(), 1);
        _full = null;
    }

    /**
     * Objecte de la prova: Test del mètode GetCela() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es consulten dues cel·les, una amb valor (0,0) i l’altre buida/no definida (9,9).
     * Operativa: Comprovem que la cel·la definida a 0,0 ens retorni el valor assignat a setUp() i que la cel·la a la posició 9,9, que no esta definida, ens retorni null
     */
    @Test
    public void TestGetCela()
    {
        Cela c = _full.GetCela(0,0);
        assertNotNull("Cel·la existeix", c);
        assertEquals("Cel·la es A1", c.GetValorReal(), "A1");

        Cela c2 = _full.GetCela(9,9);
        assertNull("Cel·la null, cantonada abaix a la dreta", c2);
    }

    /**
     * Objecte de la prova: Test del mètode GetCela() de Full amb errors
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es consulta una cel·la fora del full
     * Operativa: Comprovem que si accedim a una cel·la fora del full, en aquest cas la posició 10,10 que correspondíra
     * a la 11a columna i la 11 fila, d’un full de 10x10 ens llençi l’excepció IndexOutOfBoundsException
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void TestGetCelaExcepcio() {
        _full.GetCela(10, 10);
    }

    /**
     * Objecte de la prova: Test del mètode SetNomFull() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es modifica el nom del full creat a setUp(), que té el nom per defecte de “Full 1” i que un cop canviat, el nou nom es el especificat i que el que retorna es l’anterior
     * Operativa: Canviem el nom del full creat al SetUp() per “NouNom” i comprovem ue el retornat es “Full 1” amb un assertEquals()
     */
    @Test
    public void TestSetNomFull() {
        String nomAntic = _full.SetNomFull("NouNom");
        assertEquals("Nom antic correcte", nomAntic, "Full 1");
        assertEquals("Nom actual correcte", "NouNom", _full.GetNomFull());
        _full = null;
    }

    /**
     * Objecte de la prova: Test del mètode ModificaCela() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es modifiquen dues cel·les del full creat a setUp(), i es comprova que el boolea que retorna correspongui amb si la cel·la s’ha modificat o creat.
     * Operativa: Modifiquem la cel·la a 0,0, que tenia anteriorment el valor de “A1” i fem un assertTrue() per comprovar
     * que la cel·la s’ha modificat, despres modifiquem la cel·la a 0,1 i fem un assertFalse() per comprovar que la cel·la era buida. El metode printFull() ens permet veure el canvi efectuat pel test.
     */
    @Test
    public void TestModificaCela()
    {
        System.out.println("Test Cel·la Modificada");
        boolean celModificada = _full.ModificaCela(0,0, "nValor");
        assertTrue("La cel·la s'ha modificat", celModificada);
        celModificada = _full.ModificaCela(0,1, "nCela");
        assertFalse("La cel·la es nova", celModificada);
    }

    /**
     * Objecte de la prova: Test del mètode ModificaCela() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es modifiquen dues cel·les del full creat a setUp(), i es comprova que
     * el boolea que retorna correspongui amb si la cel·la s’ha modificat o crea, a més, es comprova que al fer
     * GetCela(), ens retorni null, ja que la posició es buidarà si el string es “”.
     * Operativa: Modifiquem la cel·la a 0,0, que tenia anteriorment el valor de “A1” i fem un assertTrue() per
     * comprovar que la cel·la s’ha modificat seguit d’un assertNull de la mateixa cel·la per determinar que s’ha “eliminat”
     * Despres modifiquem la cel·la a 0,1 i fem un assertFalse() per comprovar que la cel·la era buida seguit d’un
     * assertNull() per comprovar que al fer get de dita cel·la segueix estant buida.
     * El mètode printFull() ens permet veure els canvi efectuat pel test.
     */
    @Test
    public void TestModificaCelaEliminar()
    {
        System.out.println("Test Eliminar Cel·la");
        boolean celModificada;

        celModificada = _full.ModificaCela(0,0, "");
        assertTrue("La cel·la s'ha modificat", celModificada);
        assertNull("La cel·la s'ha eliminat", _full.GetCela(0,0));

        celModificada = _full.ModificaCela(0,1, "");
        assertFalse("La cel·la es nova", celModificada);
        assertNull("La cel·la s'ha eliminat", _full.GetCela(0,1));
    }

    /**
     * Objecte de la prova: Test del mètode ModificaCela() de Full amb errors
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es consulta modifica una cel·la amb índexs negatius.
     * Operativa: Comprovem que si modifiquem a una cel·la fora del full, en aquest cas la posició -1,-1 ens llença l’excepció IndexOutOfBoundsException.
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void TestModificaCelaExcepcio() { _full.ModificaCela(-1,-1, "valor"); }

    /**
     * Objecte de la prova: Test del mètode AfegirFila() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’afegeix una fila al final del full i es comprova que el tamany
     * s’hagi actualitzat i que hi poguem accedir mitjançant el GetCela() i ModificaCela().
     * Operativa: Afegim una fila al final del full (últim index + 1 o directament el tamany) i comprovem que podem
     * modificar una Cela a la posició 0,10 i que dita Cela es nova amb assertFalse(). Comprovem el tamany amb un assertEquals() de GetNumCol().
     */
    @Test
    public void TestAfegirFilaFinal()
    {
        System.out.println("Test Afegir Fila Final");
        _full.AfegirFila(_full.GetNumFil() - 1);
        boolean novaCela = _full.ModificaCela(0,10,"A11");
        assertEquals("Tamny del full ha augmentat", _full.GetNumFil(), 11);
        assertFalse("Nova cel·la afegida a la fila 11", novaCela);
    }

    /**
     * Objecte de la prova: Test del mètode AfegirFila() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’afegeix una fila al principi del full i es comprova que el tamany s’hagi actualitzat i que les cel·les presents pel setUp() s’hagin mogut adecuadament..
     * Operativa: Afegim una fila al index 0, la primera fila i comprovem que la cel·la que abans estava a 0,0 segueix sent la mateixa amb assertSame()
     * El mètode printFull() ens permet veure els canvi efectuat pel test a les altres columnes.
     */
    @Test
    public void TestAfegirFilaPrincipi()
    {
        System.out.println("Test Afegir Fila Principi");
        _full.ModificaCela(0,0, "A1");
        Cela cPre = _full.GetCela(0,0);
        _full.AfegirFila(0);
        Cela cPost = _full.GetCela(0,1);

        assertSame("La cel·la s'ha desplaçat", cPre,cPost);
    }

    /**
     * Objecte de la prova: Test del mètode AfegirFila() de Full amb errors
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’afegeix una fila amb índex  negatiu.
     * Operativa: Comprovem que si afegim una fila a un índex negatiu, llençi l’excepció IndexOutOfBoundsException
     */
    @Test(expected= IndexOutOfBoundsException.class)
    public void TestAfegirFilaExcepcio() { _full.AfegirFila(-1); }


    /**
     * Objecte de la prova: Test del mètode EliminarFila() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’elimina una fila al final del full i es comprova que el tamany s’hagi actualitzat
     * Operativa: Eliminem la ultima fila del full (últim index + 1 o directament el tamany) i comprovem el tamany amb un assertEquals() de GetNumFilal().
     */
    @Test
    public void TestEliminarFilaFinal()
    {
        System.out.println("Test Eliminar Fila Final");
        int oldSize = _full.GetNumFil();
        _full.EliminarFila(_full.GetNumFil() - 1);
        assertEquals("Tamny del full ha disminuit", _full.GetNumFil(), 9);
    }

    /**
     * Objecte de la prova: Test del mètode EliminarFila() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’elimina una fila al index 0 del full i es comprova que el tamany
     * s’hagi actualitzat i que una cel·la s’hagi desplaçat.
     * Operativa: Eliminem la primera fila del full i comprovem el tamany amb un assertEquals() de GetNumFilal().
     * Comprovem que la cel·la que es troba a 0,1 ara està a 0,0 amb un assertSame()
     */
    @Test
    public void TestEliminarFilaPrincipi()
    {
        System.out.println("Test Eliminar Fila Principi");
        Cela cPre = _full.GetCela(0,1);
        _full.EliminarFila(0);
        Cela cPost = _full.GetCela(0,0);

        assertSame("La cel·la s'ha desplaçat", cPre,cPost);
    }

    /**
     * Objecte de la prova: Test del mètode EliminarFila() de Full amb errors
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’elimina una fila fora del full.
     * Operativa: Comprovem que si eliminem una fila a un índex fora del full, llençi la excepción IndexOutOfBoundsException
     */
    @Test(expected= IndexOutOfBoundsException.class)
    public void TestEliminarFilaExcepcio() { _full.AfegirFila(_full.GetNumFil() + 1); }

    /**
     * Objecte de la prova: Test del mètode AfegirCol() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’afegeix una column al principi del full i es comprova que el tamany
     * s’hagi actualitzat i que les cel·les presents pel setUp() s’hagin mogut adequadament.
     * Operativa: Afegim una columna al index 0, la primera columna i comprovem que la cel·la que abans estava a 0,0
     * ara esta a 1,0 i  segueix sent la mateixa amb assertSame()
     * El mètode printFull() ens permet veure els canvi efectuat pel test a les altres columnes.
     */
    @Test
    public void TestAfegirColPrincipi()
    {
        System.out.println("Test Afegir Columna Principi");
        Cela oldC = _full.GetCela(0,0);
        _full.AfegirCol(0);
        Cela newC = _full.GetCela(1,0);
        assertSame("Mateixa cel·la desplaçada", oldC,newC);
    }

    /**
     * Objecte de la prova: Test del mètode AfegirCol() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’afegeix una columna al final del full i es comprova que el tamany s’hagi actualitzat.
     * Operativa: Afegim una columna al final del full (el tamany) i comprovem que el tamany ha canviat amb un assertEquals() de GetNumColl().
     */
    @Test
    public void TestAfegirColFinal()
    {
        _full.AfegirCol(_full.GetNumCol() - 1);
        assertEquals(_full.GetNumCol(), 11);
    }

    /**
     * Objecte de la prova: Test del mètode AfegirCol() de Full amb errors
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’elimina una columna fora del full.
     * Operativa: Comprovem que si eliminem una columna a un índex fora del full, llençi la excepció IndexOutOfBoundsException
     */
    @Test(expected= IndexOutOfBoundsException.class)
    public void TestAfegirColExcepcio() { _full.AfegirCol(_full.GetNumCol() + 1); }

    /**
     * Objecte de la prova: Test del mètode EliminarColumna() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’elimina la columna al index 0 del full i es comprova que el tamany
     * s’hagi actualitzat i que una cel·la s’hagi desplaçat.
     * Operativa: Eliminem la primera columna del full i comprovem el tamany amb un assertEquals() de GetNumCol().
     * Comprovem que la cel·la que es troba a 1,1 ara està a 0,1 amb un assertSame().
     */
    @Test
    public void TestEliminarColPrincipi()
    {
        System.out.println("Test Eliminar Columna Principi");
        Cela oldC = _full.GetCela(1,1);
        _full.EliminarColumna(0);
        Cela newC = _full.GetCela(0,1);
        assertSame("Mateixa cel·la desplaçada", oldC,newC);
    }

    /**
     * Objecte de la prova: Test del mètode EliminarColumna() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’elimina la  ultima columna del full, al index 9 (nColumnes - 1) i es
     * comprova que el tamany s’hagi actualitzat.
     * Operativa: Eliminem la ultima columna del full i comprovem el tamany amb un assertEquals() de GetNumCol().
     */
    @Test
    public void TestEliminarColFinal()
    {
        System.out.println("Test Eliminar Columna Final");
        _full.EliminarColumna(_full.GetNumCol() - 1);
        assertEquals(_full.GetNumCol(), 9);
    }

    /**
     * Objecte de la prova: Test del mètode EliminarColumna() de Full amb errors
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’elimina una columna a un índex  negatiu.
     * Operativa: Comprovem que si eliminem una columna a un índex negatiu, llençi l’excepció IndexOutOfBoundsException
     */
    @Test(expected= IndexOutOfBoundsException.class)
    public void TestEliminarColExcepcio() { _full.EliminarColumna(-1); }

    /**
     * Objecte de la prova: Test del mètode OrdenarPerColumnes() de Full amb el boolea ascendent = false
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es modifiquen un conjunt de cel·les de la primera columna i després es
     * crida sobre la primera columna, desde la segona fila (index 1) fins la 4a fila.
     * Operativa: Un cop cridada la operació d’ordenar, comprovem que el primer element es l’esperat
     * El mètode printFull() ens permet veure els canvi efectuat pel test a les altres files.
     */
    @Test
    public void TestOrdenaPerColumnesDescendent()
    {
        System.out.println("Test Ordenar Columnes de forma descendent");

        _full.ModificaCela(0,0,"aaa");
        _full.ModificaCela(0,1,"123 A4");
        _full.ModificaCela(0,2,"122 A5");
        _full.ModificaCela(0,3,"zxy A2");
        _full.ModificaCela(0,4,"abc A3");
        _full.ModificaCela(0,6,"zzz");

        System.out.println("Full abans d'ordenar la columna 'A'");
        _full.PrintFull();

        Cela c = _full.GetCela(0,3);
        _full.OrdenarPerColumnes(new Pair(0,1), new Pair(0,5), false);
        assertSame(c, _full.GetCela(0,1));

        System.out.println("Full despres d'ordenar la columna 'A'");
    }

    /**
     * Objecte de la prova: Test del mètode OrdenarPerColumnes() de Full amb el boolea ascendent = true
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es modifiquen un conjunt de cel·les de la primera columna i
     * després es crida sobre les primeres tres columnes, desde la segona fila (index 1) fins la 4a fila.
     * Operativa: Un cop cridada la operació d’ordenar, comprovem que el primer element es l’esperat
     * El mètode printFull() ens permet veure els canvi efectuat pel test a les altres files.
     */
    @Test
    public void TestOrdenaPerColumnesAscendentBloc()
    {
        System.out.println("Test Ordenar Columnes de forma descendent");

        _full.ModificaCela(0,0,"aaa");
        _full.ModificaCela(0,1,"123 A4");
        _full.ModificaCela(0,2,"122 A5");
        _full.ModificaCela(0,3,"zxy A2");
        _full.ModificaCela(0,4,"abc A3");
        _full.ModificaCela(0,6,"zzz");

        System.out.println("Full abans d'ordenar la columna 'A'");
        _full.PrintFull();

        Cela c = _full.GetCela(0,2);
        _full.OrdenarPerColumnes(new Pair(2,1), new Pair(0,5), true);
        assertSame(c, _full.GetCela(0,1));

        System.out.println("Full despres d'ordenar la columna 'A'");
    }

    /**
     * Objecte de la prova: Test del mètode CercarValor() de Full
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S’executa el primer cop per buscar una cel·la que apareix només un cop.
     * El segon cop es busca un substring que apareix més d’un cop al full
     * Per cada crida, recorre el Set que retorna comprovant que l’objecte Cela es el mateix que el que s’obté amb el GetCela() de full mitjançant un assertSame()
     * Operativa: Primer es crida CercarValor(“A1”), i només s’espera un sol resultat, per la segona crida, busca
     * l’string “B” i per a cada cel·la amb un caràcter ‘B’, es comprova que la cel·la retornada per CercarValor() i
     * el GetCela() de les posicions retornades, són la mateixa Cela amb un assertSame()
     */
    @Test
    public void TestCercaValor()
    {
        Set<Pair<Pair<Integer,Integer>,Cela>> tuples = _full.CercarValor("A1");
        for (Pair<Pair<Integer,Integer>,Cela> t : tuples) { assertEquals(t.second(),_full.GetCela(t.first().first(),t.first().second())); }

        tuples = _full.CercarValor("B");
        for (Pair<Pair<Integer,Integer>,Cela> t : tuples) { assertEquals(t.second(),_full.GetCela(t.first().first(),t.first().second())); }

        _full=null;
    }
}