package main.domain.controllers;

import main.domain.classes.Document;
import main.domain.classes.Full;
import main.domain.classes.Cela;
import main.domain.classes.functions.Interpret;
import main.domain.classes.types.TType;
import main.domain.controllers.CtrlDocument;
import main.domain.controllers.CtrlCelaSeleccionada;
import main.domain.controllers.CtrlClipboard;
import main.domain.classes.types.Pair;

import java.util.Vector;
import java.util.Set;



/**
 *
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class CtrlDomini {
    private CtrlDocument _ctrlDocument;
    //private CtrlCelaSeleccionada _ctrlCelaSeleccionada;
    private CtrlClipboard _ctrlClipboard;


    public CtrlDomini() {
        _ctrlDocument = new CtrlDocument();
        //_ctrlCelaSeleccionada = new CtrlCelaSeleccionada();
        _ctrlClipboard = new CtrlClipboard(this);
        Interpret.setCtrlDomini(this);
    }

    /** Busca les celes que contenen el valor usuari == valor
     * @param valor
     * @return Set<Pair<Pair<Integer,Integer> , Cela> > on cada entrada es una ocurrencia de la cela i la seva posició
     */
    public Set<Pair<Pair<Integer,Integer> , Cela> > cercarValor(String valor) {
        //return _ctrlDocument.cercarValor(valor);
        return _ctrlDocument.obtenirFullActiu().CercarValor(valor);
    }

    /** Busca les celes que contenen el valor usuari == valor
     * @param valor
     * @return Vector<Cela> on cada entrada es una ocurrencia de la cela
     */
    public Vector<Cela> cercarValorCela(String valor) {
        Set<Pair<Pair<Integer,Integer> , Cela> > res = _ctrlDocument.obtenirFullActiu().CercarValor(valor);//cercarValor(valor);
        Vector<Cela> out = new Vector<Cela>();
        res.forEach(c -> {out.add(c.second());});
        return out;
    }

    /** Busca les celes que contenen el valor usuari == valor
     * @param valor
     * @return Vector<String> on cada entrada es el valor usuari d'una ocurrencia de la cela
     */
    public Vector<String> cercarValorString(String valor) {
        Set<Pair<Pair<Integer,Integer> , Cela> > res = _ctrlDocument.obtenirFullActiu().CercarValor(valor); //cercarValor(valor);
        Vector<String> out = new Vector<String>();
        res.forEach(c -> {out.add(c.second().GetValorUsuari());});
        return out;
    }

    /**
     * Busca les celes que tenen el valor usuari == cerca i reemplaca aquest valor per reemplac si estan dins del bloc format per les Celes idC1, idF1 i idC2, idF2
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     * @param cerca
     * @param reemplac
     */
    public void reemplacarValor(int idC1, int idF1, int idC2, int idF2, String cerca, String reemplac) {
        //_ctrlDocument.cercarValor(cerca).forEach(posICela -> {
        _ctrlDocument.obtenirFullActiu().CercarValor(cerca).forEach(posICela -> {
            if (posICela.first().first().intValue() >= idC1 && posICela.first().first().intValue() <= idC2 && posICela.first().second().intValue() >= idF1 && posICela.first().second().intValue() <= idF2) {
                modificarCela(posICela.first().first().intValue(), posICela.first().second().intValue(), reemplac);
            }
        });
    }

    /**
     * Copia el valor de la Cela i es guarda el seu valor en el Clipboard
     * @param idC
     * @param idF
     */
    public void copiarCela(int idC, int idF) {
        _ctrlClipboard.copiarBloc(idC, idF, idC, idF);
    }

    /**
     * Talla el valor de la Cela, es guarda el seu valor en el Clipboard
     * i es borra el contingut de les Celes de les que es talla
     * @param idC
     * @param idF
     */
    public void tallarCela(int idC, int idF) {
        _ctrlClipboard.tallarBloc(idC, idF, idC, idF);
    }

    /**
     * Enganxa el contingut del Clipboard des de la Cela (idF, idC)
     * @param idC
     * @param idF
     */
    public void enganxar (int idC, int idF) {
        _ctrlClipboard.enganxar(idC, idF);
    }

    /**
     * Copia el contingut del Bloc identificat per idF1, idC1, idF2, idC2 en
     * el Clipboard
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     */
    public void copiarBloc(int idC1, int idF1, int idC2, int idF2) {
        _ctrlClipboard.copiarBloc(idC1, idF1, idC2, idF2);
    }

    /**
     * Talla el contingut del Bloc identificat per idF1, idC1, idF2, idC2 en
     * el Clipboard i borra el contingut de les Celes dins del bloc
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     */
    public void tallarBloc(int idC1, int idF1, int idC2, int idF2) {
        _ctrlClipboard.tallarBloc(idC1, idF1, idC2, idF2);
    }

    /**
     * Retorna el valor d'usuari a la Cela idF, idC
     * @param idC
     * @param idF
     * @return String
     */
    public String obtenirValorCela(int idC, int idF) {
        if (_ctrlDocument.obtenirFullActiu().GetCela(idC, idF) == null) {
            return "";
        }
        Cela c = _ctrlDocument.obtenirCela(idC, idF);
        return c.GetValorUsuari();
    }

    /**
     * Retorna el valor d'usuari a la Cela idF, idC en el Full idFull <p> <b>(Es queda el Full idFull seleccionat) </b>
     * @param idC
     * @param idF
     * @param idFull
     * @return String (Valor usuari) si existeix, null altrament
     */
    public String obtenirValorCela(int idC, int idF, int idFull) {
        if (_ctrlDocument.getDocumentObert().GetFull(idFull) == null || _ctrlDocument.getDocumentObert().GetFull(idFull).GetCela(idC, idF) == null) {
            return "";
        }
        _ctrlDocument.setFullActiu(idFull);
        Cela c = _ctrlDocument.obtenirCela(idC, idF);
        return c.GetValorUsuari();
    }

    /**
     * Retorna el valor real de la Cela idF, idC
     * @param idC
     * @param idF
     * @return String (Valor real) si existeix, null altrament
     */
    public String obtenirValorRealCela(int idC, int idF) {
        if (_ctrlDocument.obtenirFullActiu().GetCela(idC, idF) == null) {
            return "";
        }
        Cela c = _ctrlDocument.obtenirCela(idC, idF);
        return c.GetValorReal();
    }

    /**
     * Retorna el valor real de la Cela idF, idC
     * @param idC
     * @param idF
     * @return String (Valor real) si existeix, null altrament
     */
    public String obtenirValorRealCela(int idC, int idF, int idFull) {
        if (_ctrlDocument.getDocumentObert().GetFull(idFull) == null || _ctrlDocument.obtenirFullActiu().GetCela(idC, idF) == null) {
            return "";
        }
        _ctrlDocument.setFullActiu(idFull);
        Cela c = _ctrlDocument.obtenirCela(idC, idF);
        return c.GetValorReal();
    }

    // TODO: Ascendent o descendent?
    public void ordenarPerColumnes(int idC1, int idF1, int idC2, int idF2) {
        _ctrlDocument.obtenirFullActiu().OrdenarPerColumnes(new Pair<Integer,Integer>(idC1,idF1), new Pair<Integer,Integer>(idC2,idF2), false);
    }

    // CtrlDocument
    public Document getDocumentObert() {
        return _ctrlDocument.getDocumentObert();
    }

    public void crearFull() {
        _ctrlDocument.crearFull();
    }

    public void crearFull(String nomFull) throws Exception {
        _ctrlDocument.CrearFull(nomFull);
    }

    public void crearFull(int numFiles, int numCols) {
        _ctrlDocument.crearFull(numFiles, numCols);
    }

    public void crearFull(String nomFull, int numFiles, int numCols) throws Exception {
        _ctrlDocument.crearFull(nomFull, numFiles, numCols);
    }

    public Full obtenirFullActiu() {
        return _ctrlDocument.obtenirFullActiu();
    }

    public void setFullActiu(int idf) {
        _ctrlDocument.setFullActiu(idf);
    }

    public void crearDocument(String path) {
        _ctrlDocument.crearDocument(path);
    }

    public void crearDocument(String path, String nomDoc) {
        _ctrlDocument.crearDocument(path, nomDoc);
    }

    public void tancarDocument() {
        _ctrlDocument.tancarDocument();
    }

    public void afegirFila(int pos) {
        _ctrlDocument.AfegirFila(pos);
    }

    public void afegirColumna(int pos) {
        _ctrlDocument.AfegirColumna(pos);
    }

    public void eliminarFila(int pos) {
        _ctrlDocument.EliminarFila(pos);
    }

    public void eliminarColumna(int pos) {
        _ctrlDocument.EliminarColumna(pos);
    }

    public Cela obtenirCela(int idC, int idF) {
        return _ctrlDocument.obtenirCela(idC, idF);
    }
    public void eliminarFullActiu() { _ctrlDocument.EliminarFullActiu(); }
    public void canviarNomFullActiu(String nom) { _ctrlDocument.canviarNomFull(nom); }
    public void canviarNomDocument(String nom) { _ctrlDocument.canviarNomDoc(nom); }

    public Set<Pair<Pair<Integer,Integer>, Cela> > CercarValor(String valor) { return _ctrlDocument.CercarValor(valor); }

    public void modificarCela(int idC, int idF, String valor) {
        String valorReal = valor;
        if (valor.length() != 0 && Interpret.getType(valor) == TType.FUNCTION) {
            valorReal = Interpret.callFunction(valor);
        }
        _ctrlDocument.obtenirFullActiu().ModificaCela(idC, idF, valor, valorReal);
    }
}
