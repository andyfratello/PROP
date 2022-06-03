package main.persistence.classes.document;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Arrays;
import java.io.*;

/**
 * El GestorDocument s’encarrega de guardar/carregar les dades de les classes de domini en un format semblant al XML,
 * les etiquetes indiquen els diferents atributs que es necessiten per instanciar les classes corresponents (ex valor
 * real de cel.la o nom del document/full)
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class GestorDocument {
    /**
     * Guarda les dades rebudes en el fitxer marcat per path en un format inventat (.prop). El vector representen els
     * fulls, on el string de la parella és el nom del full, el TreeMap te per clau la posició de la cel·la i per valor,
     * el valor de l'usuari
     * @param bytes
     * @param path
     */
    public void GestorDocument(byte[] bytes, String path)
    {
        Path p = Paths.get(path);
        try {
            FileOutputStream outFile = new FileOutputStream(path);
            outFile.write(bytes);
            outFile.flush();
            outFile.close();
        }
        catch (IOException e) { System.err.println("[#SAVE] Error al guardar el document: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace())); }
    }

    /**
     * Carrega el fitxer en el nostre format inventat (.prop) i retorna un vector de parelles on el primer elemnt es el
     * nom del full, el segon es un diccionari amb les cel·les del full i el seu valor d'usuari
     * @param path
     */
    public byte[] CarregarDocument(String path)
    {
        byte[] bytes = null;
        try {
            FileInputStream inFile = new FileInputStream(path);
            bytes = inFile.readAllBytes();
            inFile.close();
        } catch (IOException e) { System.err.println("[#LOAD] Error al carregar el document: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace())); }
        return bytes;
    }
}
