package main.domain.controllers;

import main.domain.classes.types.Pair;
import main.domain.classes.Document;
import main.domain.classes.Cela;
import main.domain.classes.Full;
import java.util.Set;
import java.io.*;


/**
 * El controlador de Document s’encarrega de gestionar el Document Obert i el seu respectiu Full Actiu.
 * @author Marc Clapés Marana (marc.clapes.marana@estudiantat.upc.edu)
 */

public class CtrlDocument {
    /** Identificador intern del Full que està Actiu */
    private Full FullActiu;

    /** Identificador intern del Document Obert */
    private Document DocumentObert;
    /** Constructora */
    public CtrlDocument() {
        FullActiu = null;
        DocumentObert = null;
    }
    /** Getters */
    public Document getDocumentObert() {
        return DocumentObert;
    }
    public Full obtenirFullActiu() {
        return FullActiu;
    }
    /**
     * A partir de un índex d'un Full, passa aquest a FullActiu
     * @param idf Identificador de Full
     */
    public void setFullActiu(int idf) {
        FullActiu = DocumentObert.GetFull(idf);
    }
    /**
     *  Crida a la creadora de Document i li passa els paràmetres rebuts. Deixa com a DocumentObert el Document creat.
     * @param path Localització del Document
     */
    public void crearDocument(String path) {
        if (DocumentObert == null) {
            DocumentObert = new Document(path);
        }

    }
    /**
     *  Crida a la creadora de Document i li passa els paràmetres rebuts. Deixa com a DocumentObert el Document creat.
     * @param path Localització del Document
     * @param nomDoc Nom del Document
     */
    public void crearDocument(String path, String nomDoc) {
        if (DocumentObert == null) {
            DocumentObert = new Document(path, nomDoc);
        }

    }

    /**
     *  Crida a la funció de Document crearFull sense paràmetres. Deixa com a FullActiu el Full creat.
     */
    public void crearFull() {
        FullActiu = DocumentObert.CrearFull();
    }
    /**
     *  Crida a la funció de Document crearFull amb el paràmetre rebut. Deixa com a FullActiu el Full creat.
     *  @param nomF nom del Full a crear
     *  @exception Exception ja existeix un Full amb nomFull
     */
    public void CrearFull(String nomF) throws Exception {
        FullActiu = DocumentObert.CrearFull(nomF);

    }
    /**
     *  Crida a la funció de Document crearFull amb el paràmetre rebut. Deixa com a FullActiu el Full creat.
     * @param nC número de Columnes a crear
     * @param nF número de Files a crear
     */
    public void crearFull(int nC, int nF) {
        FullActiu = DocumentObert.CrearFull(nC, nF);
    }
    /**
     *  Crida a la funció de Document crearFull amb el paràmetre rebut.
     *  @param nomF nom del Full a crear
     *  @param nC número de Columnes a crear
     *  @param nF número de Files a crear
     *  @exception Exception ja existeix un Full amb nomFull
     */
    public void crearFull(String nomF, int nC, int nF) throws Exception {
        FullActiu = DocumentObert.CrearFull(nomF, nC, nF);
    }
    /**
     *  Crida a la funció de Document canviarNomDoc amb el paràmetre rebut.
     *  @param nom Nou Nom del DocumentObert
     */
    public void canviarNomDoc(String nom) {
        DocumentObert.canviarNomDoc(nom);
    }
    /**
     *  Crida a la funció de Document canviarNomFull amb el paràmetre rebut.
     *  @param nom Nou Nom del FullActiu
     */
    public void canviarNomFull(String nom) {
        FullActiu.SetNomFull(nom);
    }
    /**
     *  Es tanca el DocumentObert i es posen a Null l'atribut DocumentObert i FullActiu
     */
    public void tancarDocument() {
        DocumentObert = null;
        FullActiu = null;

    }

    /**
     * S'elimina el FullActiu, sí no hi ha més fulls se'n crea un de nou automàticament i passa a ser el FullActiu.
     * En cas que hi hagi Fulls amb identificadors més petits que l'eliminat passaria el primer en ordre descendent a ser el FullActiu.
     * En el cas que no hi hagi fulls amb identificadors més petits el fullActiu passaria a ser el següent amb identificador major.
     */
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
            FullActiu = DocumentObert.CrearFull();
        }
    }
    /**
     * Donades una columna i una fila, crida a la funció GetCela de Full i retorna la cel·la corresponent al FullActiu o null si no n'hi ha cap
     * @param idC Enter positiu que indica la columna començant per 0
     * @param idF Enter positiu que indica la fila començant per 0
     */
    public Cela obtenirCela(int idC, int idF) {
        return FullActiu.GetCela(idC, idF);

    }
    /**
     * Crida a la funció ModificaCela de Full
     * @param i identificador de la columna
     * @param j identificador de la fila
     * @param s String que es vol suplantar a la cel·la especificada
     */
    public void modificarCela(int i, int j, String s) {
        FullActiu.ModificaCela(i, j, s);
    }
    /**
     * Crida a la Funció CercarValor de Full
     * @param valor String que volem buscar al full
     * @return Conjunt de tuples de fila, columna i cel·la associada, fila i columna estan agrupades en un pair d'integers
     */
    public Set<Pair<Pair<Integer,Integer>, String> > CercarValor(String valor) {
        if (FullActiu != null) {
            return FullActiu.CercarValor(valor);
        }

        return null;
    }
    /**
     * Crida a la funció AfegirFila de Full
     * @param pos posició de la cel·la on s'ha d'afegir una Fila
     */
    public void AfegirFila(int pos) {
        FullActiu.AfegirFila(pos);
    }
    /**
     * Crida a la funció AfegirCol de Full
     * @param pos posició de la cel·la on s'ha d'afegir una Columna
     */
    public void AfegirColumna(int pos) {
        FullActiu.AfegirCol(pos);
    }
    /**
     * Crida a la funció EliminarFila de Full
     * @param pos posició de la cel·la s'ha d'eliminar una Fila
     */
    public void EliminarFila(int pos) {
        FullActiu.EliminarFila(pos);
    }
    /**
     * Crida a la funció EliminarColumna de Full
     * @param pos posició de la cel·la s'ha d'eliminar una Columna
     */
    public void EliminarColumna(int pos) {
        FullActiu.EliminarColumna(pos);
    }

    /**
     * Crida a la Funció OrdenarPerColumnes de Full
     * @param idC1 idF1 Pair(int Columna,int Fila) que determina la primera cantonada d'un area rectangular
     * @param idC2 idF2 Pair(int Columna,int Fila) que determina la segona cantonada de l'area quadrada
     */
    @Deprecated
    public void ordenarPerColumnes(int idC1, int idF1, int idC2, int idF2) {
        FullActiu.OrdenarBloc(new Pair<>(idC1,idF1), new Pair<>(idC2,idF2), false);
    }

    /** Converteix el document a un array de bytes per guardar
     *  a un fitxer binari
     * @return El document obert com a array de bytes
     * @throws IOException Error amb writeObject()
     */
    public byte[] documentToByteArray() throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(DocumentObert);
        os.close();
        return bs.toByteArray();
    }

    /** Carrega un document apartir d'un array de bytes
     * @param bytes Document com a byte array
     * @throws IOException Error amb InputStream
     * @throws ClassNotFoundException Byte array no és un document
     */
    public void byteArrayToDocument(byte[] bytes) throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream bs = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(bs);
        DocumentObert = (Document) is.readObject();
        is.close();
        FullActiu = DocumentObert.GetFull();
    }
}
