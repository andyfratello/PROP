package test;

import main.domain.classes.Full;
import org.junit.Test;
import main.domain.classes.Document;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Marc Clapés Marana (marc.clapes.marana@estudiantat.upc.edu)
 */

public class TestDocument {

    /**
     * Objecte de la prova: Test del mètode Document(path) de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte Document.
     * Operativa: En aquest test es comprova que es crea correctament un objecte Document amb localitazacio = path, titol = sense_titol, id = 0, doc = null
     * Primer es crea un objecte Document, es crida als mètodes GetLocal(), GetNom(), GetId() i GetDocument().size() i es comprova que el resultat és
     * el correcte.
     */
    @Test
    public void TestConstructores() {
        String localitazacio = "./Documents";
        Document doc1 = new Document (localitazacio);
        assertEquals("./Documents", doc1.GetLocal());
        assertEquals("Sense_Títol", doc1.GetNom());
        assertEquals(0, doc1.GetId());
        assertEquals(0, doc1.GetNumFulls());

        String titol = "Excel";
        Document doc2 = new Document(localitazacio, titol);
        assertEquals("./Documents", doc2.GetLocal());
        assertEquals("Excel", doc2.GetNom());
        assertEquals(0, doc2.GetId());
        assertEquals(0, doc2.GetNumFulls());
    }

    /**
     * Objecte de la prova: Test del mètode printDocument() de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. Necessitem la classe Full.
     * Valors estudiats: Estratègia caixa gris. Metode per imprimir el contingut de Document, per veure visualment els canvis
     * Operativa: En aquest test es comprova que s'imprimeix un objecte Document correctament
     * Primer es crea un objecte Document, es creen dos Fulls amb els mètodes CrearFull(...) i s'imprimeix per pantalla el document.
     */
    @Test
    public void testPrintDocument()
    {
        String localitzacio = "./Documents";
        Document doc = new Document(localitzacio);
        doc.CrearFull(5, 5);
        doc.CrearFull(4, 4);
        System.out.println();
        doc.printDocument();
        System.out.println();
    }
    /**
     * Objecte de la prova: Test dels mètodes CrearFull(), CrearFull(Nom), CrearFull(numFiles, numCols) i CrearFull(nom, numFiles, numCols) de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. Necessitem la classe Full.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear fulls al document.
     * Operativa: En aquest test es comprova que es creen correctament un els fulls en el document.
     * Primer es crea un objecte Document, es crida als mètodes CrearFull(...) i es comprova que els resultats de GetID(), GetNomFull() i el GetNumFulls() siquin els correctes.
     */
    @Test
    public void testCreadoresFulls() throws Exception {
        String localitzacio = "./Documents";
        String nomF = "nomF";
        String nomF2 = "nomF2";
        Document doc = new Document(localitzacio);

        Full f1 = doc.CrearFull();
        assertEquals(1, f1.GetID());
        assertEquals("Full." +1, f1.GetNomFull());
        assertEquals(1, doc.GetNumFulls());

        Full f2 = doc.CrearFull(nomF);
        assertEquals(2, f2.GetID());
        assertEquals("nomF", f2.GetNomFull());
        assertEquals(2, doc.GetNumFulls());

        Full f3 = doc.CrearFull(5, 5);
        assertEquals(3, f3.GetID());
        assertEquals("Full." +3, f3.GetNomFull());
        assertEquals(3, doc.GetNumFulls());

        Full f4 = doc.CrearFull(nomF2,5, 5);
        assertEquals(4, f4.GetID());
        assertEquals("nomF2" , f4.GetNomFull());
        assertEquals(4, doc.GetNumFulls());

    }
    /**
     * Objecte de la prova: Test del mètode CrearFull(Nom) de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. Necessitem la classe Full.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear fulls al document.
     * Operativa: En aquest test es comprova que salta correctament l'Excepció.
     * Primer es crea un objecte Document, es crida al mètode CrearFull(nom) dues vegades i es comprova que salta l'Excepció.
     */
    @Test
    public void testCrearFullAmbNomExistent() throws Exception {
        String localitzacio = "./Documents";
        String nomF = "nomF";
        Document doc = new Document(localitzacio);

        doc.CrearFull(nomF);
        doc.CrearFull(nomF);

    }
    /**
     * Objecte de la prova: Test del mètode canviarNomDoc(nom) de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per canviar el nom del Document.
     * Operativa: En aquest test es comprova que es canvia el nom del Document correctament
     * Primer es crea un objecte Document, es crida al mètode canviarNomDoc(nom) i es comprova que GetNom() retorni el nom passat per paràmetre.
     */
    @Test
    public void testCanviarNomDoc() {
        String localitazacio = "./Documents";
        Document doc1 = new Document (localitazacio);
        String nomDoc = "newNom";
        doc1.canviarNomDoc(nomDoc);
        assertEquals("newNom" , doc1.GetNom());

    }
    /**
     * Objecte de la prova: Test del mètode ExisteixFull(id) de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. Necessitem la classe Full.
     * Valors estudiats: Estratègia caixa gris. Mètode saber si Existeix el Full amb clau primària id.
     * Operativa: En aquest test es comprova si el mètode ExisteixFull(id) retorna true al passar-li una id existent.
     * Primer es crea un objecte Document, es crida al mètode crearFull() i es comprova que ExisteixFull(id) retorni true.
     */
    @Test
    public void testExisteixFull() {
        String localitazacio = "./Documents";
        Document doc = new Document (localitazacio);
        Full f = doc.CrearFull();
        int id = f.GetID();
        assertTrue(doc.ExisteixFull(id));

    }
    /**
     * Objecte de la prova: Test del mètode ExisteixFull(id) de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. Necessitem la classe Full.
     * Valors estudiats: Estratègia caixa gris. Mètode saber si Existeix el Full amb clau primària id.
     * Operativa: En aquest test es comprova si el mètode ExisteixFull(id) retorna false al passar-li una id no existent.
     * Primer es crea un objecte Document, es crida al mètode crearFull() i es comprova que ExisteixFull(id) retorni false.
     */
    @Test
    public void testNoExisteixFull() {
        String localitazacio = "./Documents";
        Document doc = new Document (localitazacio);
        assertFalse(doc.ExisteixFull(20));
    }
    /**
     * Objecte de la prova: Test del mètode EliminarFull(id) de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. Necessitem la classe Full.
     * Valors estudiats: Estratègia caixa gris. Mètode saber Eliminar el Full amb clau primaria id.
     * Operativa: En aquest test es comprova si el mètode EliminarFull(id) elimina del Document el Full amb clau id.
     * Primer es crea un objecte Document, es crida al mètode crearFull() i al EliminarFull(id) i es comprova que ExisteixFull(id) retorni false.
     */
    @Test
    public void testEliminarFull() {
        String localitazacio = "./Documents";
        Document doc = new Document (localitazacio);
        Full f = doc.CrearFull();
        int id = f.GetID();
        doc.EliminarFull(id);
        assertFalse(doc.ExisteixFull(id));
    }
    /**
     * Objecte de la prova: Test del mètode GetFull(id) de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. Necessitem la classe Full.
     * Valors estudiats: Estratègia caixa gris. Mètode per qué ens retorni el Full amb clau primària id.
     * Operativa: En aquest test es comprova si el mètode GetFull(id) retorna el Full amb clau id.
     * Primer es crea un objecte Document, es crida al mètode crearFull() i es comprova que GetFull(id) retorni el full creat.
     */
    @Test
    public void testGetFull() {
        String localitazacio = "./Documents";
        Document doc = new Document (localitazacio);
        Full f = doc.CrearFull();
        int id = f.GetID();
        assertEquals(f, doc.GetFull(id));
    }
    /**
     * Objecte de la prova: Test del mètode GetTotsIDs() de la classe Document
     * Fitxers de dades necessaris: Dades introduïdes manualment. Necessitem la classe Full.
     * Valors estudiats: Estratègia caixa gris. Mètode per qué ens retorni una llista amb totes les id dels Fulls del Document.
     * Operativa: En aquest test es comprova si el mètode GetTotsIds() retorna totes les ids dels fulls del Document en una llista.
     * Primer es crea un objecte Document, es crida al mètode crearFull() dues vegades i es comprova que les id dels Fulls creats apareixin a la llista que retorna getTotsIDs().
     */
    @Test
    public void testGetTotsIDs() {
        String localitazacio = "./Documents";
        Document doc = new Document (localitazacio);
        Full f = doc.CrearFull();
        Full f2 = doc.CrearFull();
        List<Integer> llista = new ArrayList<>();
        llista.add(f.GetID());
        llista.add(f2.GetID());
        assertEquals(llista, doc.getTotsIDs());

    }

}