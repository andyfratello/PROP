package main.domain.classes;

import java.util.*;
import java.io.*;


/** Classe contenidora dels Fulls
 *  Representa un Document de Fulls
 *  @author Marc Clapés Maraña (marc.clapes.marana@estudiantat.upc.edu)
 */
public class Document implements Serializable {
    /** Localització on es guardarà el Document */
    private final String localitzacio;

    /** Nom amb el qual es guardarà el Document */
    private String nom;

    /** Identificador intern del Document */
    public int id;

    /**
     * Estructura de dades per guardar el conjunt de Fulls amb un valor no-null
     * Es un TreeMap de Fulls on cada un d'ells té el seu propi identificador.
     */
    public TreeMap<Integer, Full> Doc;
    // --Constructores--
    /**
     *  Crea una instància de Document
     * @param local Localització del Document
     */
    public Document(String local) {
        localitzacio = local;
        nom = "Sense_Títol";
        id = 0;
        Doc = new TreeMap<>();

    }
    /**
     *  Crea una instància de Document
     * @param local Localització del Document
     * @param nomDoc Nom del Document
     */
    public Document(String local, String nomDoc) {
        localitzacio = local;
        Doc = new TreeMap<>();
        id = 0;
        nom = nomDoc;
    }

    //GETTERS
    public String GetLocal() {
        return localitzacio;
    }
    public String GetNom() {
        return nom;
    }
    public int GetId() {return id; }
    public void canviarNomDoc(String n) {
        nom = n;
    }
    public int GetNumFulls(){ return Doc.size();}

    /**
     * Es crea un Full sense passar-li atributs per paràmetre i es retorna el Full creat.
     * @return Retorna el full creat
     */
    public Full CrearFull() {
        id = id+1;
        String nomFull = "Full." + id;
        Full f = new Full(id, nomFull, 1000, 1000);
        Doc.put(id, f);
        return f;

    }
    /**
     * Donat un nom es crea un Full i se li atribueixen els atributs restants i es retorna el Full creat.
     * @param nomFull Nom del Full a crear
     * @return Retorna el full creat
     */
    public Full CrearFull(String nomFull) throws Exception {
        for (int i = 0; i < id; ++i) {
            if (ExisteixFull(i) && Objects.equals(GetFull(i).GetNomFull(), nomFull)) {
                throw new Exception(nomFull + " ja existeix");
            }
        }
        id = id+1;
        Full f = new Full(id, nomFull,1000, 1000);
        Doc.put(id, f);
        return f;
    }
    /**
     * Donat un número de Columnes i de Files, es crea un Full i se li atribueixen els atributs restants i es retorna el Full creat.
     * @param numCols Número de Columnes del Full a crear
     * @param numFiles Número de Files del Full a crear
     * @return Retorna el full creat
     */
    public Full CrearFull(int numCols, int numFiles) {
        id = id+1;
        String nomFull = "Full." + id;
        Full f = new Full(id, nomFull, numCols, numFiles);
        Doc.put(id, f);
        return f;
    }
    /**
     * Donat un nom, un número de Columnes i de Files, es crea un Full i es retorna el Full creat.
     * @param nomFull Nom del Full a crear
     * @param numCols Número de Columnes del Full a crear
     * @param numFiles Número de Files del Full a crear
     * @return Retorna el full creat
     */
    public Full CrearFull(String nomFull, int numCols, int numFiles) throws Exception {
        for (int i = 0; i < id; ++i) {
            if (ExisteixFull(i) && (Objects.equals(GetFull(i).GetNomFull(), nomFull))) {
                throw new Exception(nomFull + " ja existeix");
            }
        }
        id = id+1;
        Full f = new Full(id, nomFull, numCols, numFiles);
        Doc.put(id, f);
        return f;

    }
    /**
     * Retorna True si l'índex del Full passat per paràmetre existeix en el TreeMap, retorna Fals en cas contrari
     * @param id Identificador de Full
     * @return Boolea indicant si el full amb l'identificador expecificat exsisteix
     */
    public boolean ExisteixFull(int id) {
        return Doc.containsKey(id);
    }
    /**
     * Si existeix el Full identificar per l'índex passat per paràmetre l'elimina del TreeMap.
     * @param id Identificador de Full
     */
    public void EliminarFull(int id) { Doc.remove(id); }
    /** Retorna el Full identificat per índex, aquest existeix en el TreeMap.
     *  @param id Identificador de Full
     *  @return Retorna el full amb l'identificador indicat, null si no exsisteix
     */
    public Full GetFull(int id) {
        if (Doc.containsKey(id)) {
            return Doc.get(id);

        }
        return null;
    }

    /** @return Retorna el primer full del document, NULL si no en té cap */
    public Full GetFull()
    {
        return Doc.firstEntry().getValue();
    }

    /** @return Retorna una Llista de tots els identificadors dels Fulls que existeixin en el TreeMap */
    public List<Integer> getTotsIDs() {
        List<Integer> v = new ArrayList<>();
        Doc.forEach((id, full) -> v.add(id));
        return v;
    }
}