package main.domain.classes.config;

import main.domain.classes.types.Pair;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

/**
 * La classe SerializableConfigComparator és una implementació Serialitzable del comparador necessari per a poder
 * ordenar l’estructura de la classe Configuració i poder serialitzar la classe per guardar-la a disc.
 */
class SerializableConfigComparator implements Serializable, Comparator<Pair<LocalDateTime, String>> {
    /**
     * Retorna -1 si p1.first() > p2.first(), 1 si p1.first() < p2.first(). I si p1.first() == p2.first() retorna 1 si
     * p1.second() > p2.second(), -1 si p1.second() < p2.second() i 0 si son iguals.
     * @param p1
     * @param p2
     * @return int
     */
    public int compare(Pair<LocalDateTime, String> p1, Pair<LocalDateTime, String> p2) {
        if (p1.first() == p2.first())
            return p1.second().compareTo(p2.second());
        return (p1.first().compareTo(p2.first()))*-1;
    }
}

/**
 * La classe Configuració s’encarrega de mantenir dades persistents globals del programa, en el nostre cas s’encarrega
 * de guardar la data d’última modificació, el nom i la localització de cada document que s’ha creat.
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class Configuracio implements Serializable {
    /** Conté una identificacio unica de la versió de la classe serialitzable. */
    private static final long serialVersionUID = 3L;
    /** Conté la estructura de dades on es guarden les dades respectives de cada document */
    private TreeMap<Pair<LocalDateTime, String>, String> configDocs;

    private boolean existeix(String nomDocument, String pathDocument) {
        for (var entry : configDocs.entrySet()) {
            if (entry.getKey().second().equals(nomDocument) && entry.getValue().equals(pathDocument)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Constructora per defecte que crea una nova instancia buida de la classe.
     */
    public Configuracio() {
        configDocs = new TreeMap<Pair<LocalDateTime, String>, String>(new SerializableConfigComparator());
    }

    /**
     * Afegeix un nou document amb nom = nomDocument, path = pathDocument i amb la data i hora actuals.
     * @param nomDocument
     * @param pathDocument
     */
    public void afegirConfig(String nomDocument, String pathDocument) {
        if (!existeix(nomDocument, pathDocument)) {
            configDocs.put(new Pair<LocalDateTime, String>(LocalDateTime.now(), nomDocument), pathDocument);
        } else {
            documentModificat(nomDocument, LocalDateTime.now());
        }
    }

    /**
     * Es canvia la data d’última modificació del document amb nom = nomDocument amb la data i hora ldt.
     * @param nomDocument
     * @param ldt
     */
    public void documentModificat(String nomDocument, LocalDateTime ldt) {
        for (var entry : configDocs.entrySet()) {
            if (entry.getKey().second().equals(nomDocument)) {
                String valorAnterior = entry.getValue();
                configDocs.remove(entry.getKey());
                configDocs.put(new Pair<LocalDateTime, String>(ldt, nomDocument), valorAnterior);
                return;
            }
        }
    }

    /**
     * Elimina el document amb nom = nomDocument de la configuració.
     * @param nomDocument
     */
    public void eliminarConfig(String nomDocument) {
        for (var entry : configDocs.entrySet()) {
            if (entry.getKey().second().equals(nomDocument)) {
                configDocs.remove(entry.getKey());
                return;
            }
        }
    }

    /**
     * Retorna una matriu d’Strings on cada entrada del primer index es un document i cada document té la data d'última
     * modificació, el nom del document i el path del document. Aquesta matriu esta ordenada per Data i hora de manera
     * descendent (Més recents primers), i si la data coincideix per nom del document.
     * @return Matriu de Strings
     */
    public Vector<Vector<String>> simplify() {
        Vector<Vector<String>> res = new Vector<Vector<String>>();
        configDocs.forEach((keyDoc, pathDoc) -> {
            res.add(new Vector<> (List.of(keyDoc.first().toString(), keyDoc.second(), pathDoc)));
        });
        return res;
    }
}
