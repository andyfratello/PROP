package main.domain.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author Marc Clapés Marana (marc.clapes.marana@estudiantat.upc.edu)
 */

public class Document implements Serializable {
    private final String localitzacio;
    private String nom;
    public int id;
    public TreeMap<Integer, Full> Doc;
    public Document(String local) {
        localitzacio = local;
        nom = "Sense_Títol";
        id = 0;
        Doc = new TreeMap<>();

    }
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

    public Full CrearFull() {
        id = id+1;
        String nomFull = "Full." + id;
        Full f = new Full(id, nomFull, 1000, 1000);
        Doc.put(id, f);
        return f;

    }

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
    public Full CrearFull(int numCols, int numFiles) {
        id = id+1;
        String nomFull = "Full." + id;
        Full f = new Full(id, nomFull, numCols, numFiles);
        Doc.put(id, f);
        return f;
    }
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
    public boolean ExisteixFull(int id) {
        return Doc.containsKey(id);
    }
    public void EliminarFull(int id) {
        Doc.remove(id);

    }
    public Full GetFull(int id) {
        if (Doc.containsKey(id)) {
            return Doc.get(id);

        }
        return null;
    }
    public List<Integer> getTotsIDs() {
        List<Integer> v = new ArrayList<>();
        for (int i = 1; i <= id; ++i) {
            if (Doc.containsKey(i)) {
                v.add(i);
            }
        }
        return v;
    }

    public void printDocument() {
        for (int key : Doc.keySet()) {
            GetFull(key).PrintFull();
            System.out.println();
        }
    }

}