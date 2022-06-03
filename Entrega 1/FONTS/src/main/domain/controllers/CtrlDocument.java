package main.domain.controllers;

import main.domain.classes.Cela;
import main.domain.classes.Full;
import main.domain.classes.Document;
import main.domain.classes.types.Pair;

import java.util.Set;

/**
 * @author Marc Clapés Marana (marc.clapes.marana@estudiantat.upc.edu)
 */

public class CtrlDocument {
    private Full FullActiu;
    private Document DocumentObert;

    public CtrlDocument() {
        FullActiu = null;
        DocumentObert = null;
    }

    public Document getDocumentObert() {
        return DocumentObert;
    }
    public Full obtenirFullActiu() {
        return FullActiu;

    }


    public void setFullActiu(int idf) {
        FullActiu = DocumentObert.GetFull(idf);
    }

    public void crearDocument(String path) {
        if (DocumentObert == null) {
            DocumentObert = new Document(path);
        }

    }
    public void crearDocument(String path, String nomDoc) {
        if (DocumentObert == null) {
            DocumentObert = new Document(path, nomDoc);
        }

    }
    public void crearFull() {
        FullActiu = DocumentObert.CrearFull();
    }
    public void CrearFull(String nomF) throws Exception {
        FullActiu = DocumentObert.CrearFull(nomF);

    }

    public void crearFull(int nC, int nF) {
        FullActiu = DocumentObert.CrearFull(nC, nF);
    }
    public void crearFull(String nomF, int nC, int nF) throws Exception {
        FullActiu = DocumentObert.CrearFull(nomF, nC, nF);
    }

    public void canviarNomDoc(String nom) {
        DocumentObert.canviarNomDoc(nom);
    }
    public void canviarNomFull(String nom) {
        FullActiu.SetNomFull(nom);
    }
    public void tancarDocument() {
        DocumentObert = null;
        FullActiu = null;

    }

    public void EliminarFullActiu() {
        if (DocumentObert.GetNumFulls() > 1) {
            int id = FullActiu.GetID();
            DocumentObert.EliminarFull(id);
            boolean trobat = false;
            int id1 = id;
            while (id1 > 0 && !trobat) {
                if (DocumentObert.ExisteixFull(id1)) {
                    FullActiu = DocumentObert.GetFull(id1);
                    trobat = true;
                }
                --id1;
            }
            int id2 = id;
            while (!trobat) {
                if (DocumentObert.ExisteixFull(id2)) {
                    FullActiu = DocumentObert.GetFull(id2);
                    trobat = true;
                }
                ++id2;
            }

        }
        else if (DocumentObert.GetNumFulls() == 1) {
            int id = FullActiu.GetID();
            DocumentObert.EliminarFull(id);
            FullActiu = null;
        }
    }
    public Cela obtenirCela(int idC, int idF) {
        return FullActiu.GetCela(idC, idF);

    }
    public void modificarCela(int i, int j, String s) {
        FullActiu.ModificaCela(i, j, s);
    }

    public Set<Pair<Pair<Integer,Integer>, Cela> > CercarValor(String valor) {
        if (FullActiu != null) {
            return FullActiu.CercarValor(valor);
        }

        return null;
    }

    public void AfegirFila(int pos) {
        FullActiu.AfegirFila(pos);
    }
    public void AfegirColumna(int pos) {
        FullActiu.AfegirCol(pos);
    }
    public void EliminarFila(int pos) {
        FullActiu.EliminarFila(pos);
    }
    public void EliminarColumna(int pos) {
        FullActiu.EliminarColumna(pos);
    }

    public void ordenarPerColumnes(int idC1, int idF1, int idC2, int idF2) {
        FullActiu.OrdenarPerColumnes(new Pair<Integer,Integer>(idC1,idF1), new Pair<Integer,Integer>(idC2,idF2), false);
    }
}
