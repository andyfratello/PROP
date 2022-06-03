package main.presentation.controllers;

import main.domain.controllers.CtrlDomini;
import main.domain.classes.types.TType;
import main.domain.classes.types.Pair;
import main.presentation.views.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * El controlador de Presentació s’encarrega de fer de comunicador entre les vistes de la capa de presentació. També és
 * el que transmet a la capa de presentació les dades de les capes inferiors.
 * @author Marc Clapés Marana (marc.clapes.marana@estudiantat.upc.edu)
 */
public class CtrlPresentacio {
    /** Instància del controlador de domini */
    private static CtrlDomini cd = new CtrlDomini();

    // Comunicació entre vistes //
    /** Mostra en pantalla la finestra del menú principal */
    public static void iniPresentacio() {
        VistaMenuPrincipal vMP = new VistaMenuPrincipal();
    }

    /** Mostra en pantalla la finestra de creació del Document */
    public static void vistaCreacioDocument() {
        VistaCreacioDocument vCD = new VistaCreacioDocument();
    }

    /**
     * Mostra en pantalla la finestra del Document amb els Fulls
     * @param nomDocument
     * @param docCarregat
     */
    public static void vistaSpreadsheet(String nomDocument, boolean docCarregat) {
        VistaSpreadsheet vS = new VistaSpreadsheet(nomDocument, docCarregat);
    }

    /** Mostra en pantalla la finestra per a seleccionar un arxiu per a carregar */
    public static void vistaCarregarDocument() {
        VistaCarregarDocument vCD = new VistaCarregarDocument();
    }

    /** Mostra en pantalla la finestra per a seleccionar un arxiu per a eliminar */
    public static void vistaEliminarDocument() {
        VistaEliminarDocument vCD = new VistaEliminarDocument();
    }

    /** Mostra en pantalla la finestra que mostra els documents recentment oberts */
    public static void vistaDocumentsRecents() { VistaDocumentsRecents vDR = new VistaDocumentsRecents(); }


    // Funcions domini //
    /**
     * Crida al mètode obtenirConfig de CtrlDomini i en retorna el valor en la Matriu de Strings que en calcula
     * @return Matriu de Strings
     */
    public static Vector<Vector<String>> obteConfig() {
        return cd.obtenirConfig();
    }

    /**
     * Crida al mètode crearDocument de CtrlDomini
     * @param path
     * @param titol
     */
    public static void crearDocument(String path, String titol) {
        cd.crearDocument(path, titol);
    }

    /**
     * Crida al mètode crearFull amb títol del full i el número de files i columnes com a paràmetre de CtrlDomini
     * @param s
     * @param col
     * @param fil
     */
    public static void crearFull(String s, int col, int fil) {
        try {
            cd.crearFull(s, col, fil);
        } catch (Exception ignored) {}
    }

    /**
     * Crida al mètode crearFull amb el número de files i columnes com a paràmetre de CtrlDomini
     * @param col
     * @param fil
     */
    public static void crearFull(int col, int fil) {
        cd.crearFull(col, fil);
    }

    /** Crida al mètode getNumFiles de CtrlDomini i en retorna el número de files */
    public static int getNumFiles() {
        return cd.getNumFiles();
    }

    /** Crida al mètode getNumColumnes de CtrlDomini i en retorna el número de columnes */
    public static int getNumColumnes() {
        return cd.getNumColumnes();
    }

    /** Crida al mètode getNumFulls de CtrlDomini i en retorna el número de fulls */
    public static int getNumFulls() {
        return cd.getNumFulls();
    }

    /**
     * Crida al mètode afegirFilaFull de CtrlDomini on afegeix una fila al full idFull sota de la posició de cel·la pos
     * @param pos
     * @param idFull
     */
    public static void afegirFilaFull(int pos, int idFull) {
        cd.afegirFilaFull(pos, idFull);
    }

    /**
     * Crida al mètode afegirColumnaFull de CtrlDomini on afegeix una columna al full idFull a la dreta de la posició de cel·la pos
     * @param pos
     * @param idFull
     */
    public static void afegirColumnaFull(int pos, int idFull) {
        cd.afegirColumnaFull(pos, idFull);
    }

    /**
     * Crida al mètode eliminarFilaFull de CtrlDomini on elimina una fila al full idFull a la posició pos
     * @param pos
     * @param idFull
     */
    public static void eliminarFilaFull(int pos, int idFull) {
        cd.eliminarFilaFull(pos, idFull);
    }

    /**
     * Crida al mètode eliminarColumnaFull de CtrlDomini on elimina una columna al full idFull a la posició pos
     * @param pos
     * @param idFull
     */
    public static void eliminarColumnaFull(int pos, int idFull) {
        cd.eliminarColumnaFull(pos, idFull);
    }

    /**
     * Crida al mètode setFullActiu de CtrlDomini on posa a actiu el full amb l'index passat per paràmetre
     * @param index
     */
    public static void setFullActiu(int index) {
        cd.setFullActiu(index);
        //System.out.println(index);
    }

    /** Crida al mètode tancarDocument de CtrlDomini que tanca el document */
    public static void tancarDocument() {
        cd.tancarDocument();
    }

    /**
     * Crida al mètode modificarCela de CtrlDomini que modifica la cel·la a idC i idF amb el valor passat per paràmetre
     * @param idC
     * @param idF
     * @param valor
     */
    public static void modificarCela(int idC, int idF, String valor) {
        cd.modificarCela(idC, idF, valor);
    }

    /**
     * Crida al mètode getNomDocument de CtrlDomini que retorna el nom del Document
     * @return String
     */
    public static String getNomDocument() {
        return cd.getNomDocument();
    }

    /**
     * Crida al mètode obtenirValorCela de CtrlDomini que retorna el valor de la cel·la a idC i idF del full idFull
     * @param idC
     * @param idF
     * @param idFull
     * @return String
     */
    public static String obtenirValorCela(int idC, int idF, int idFull) {
        return cd.obtenirValorCela(idC, idF, idFull);
    }

    /**
     * Crida al mètode obtenirValorRealCela de CtrlDomini que retorna el valor real de la cel·la a idC i idF del full idFull
     * @param idC
     * @param idF
     * @param idFull
     * @return
     */
    public static String obtenirValorRealCela(int idC, int idF, int idFull) {
        return cd.obtenirValorRealCela(idC, idF, idFull);
    }

    /**
     * Crida al mètode guardaCSV de CtrlDomini que guarda a format .csv el full amb l'índex idFull
     * @param idFull
     * @throws IndexOutOfBoundsException
     */
    public static void guardaCSV(int idFull) throws IndexOutOfBoundsException {
        cd.guardaCSV(idFull);
    }

    /**
     * Crida al mètode carregarCSV de CtrlDomini que carrega al full de càlcul el fitxer en format .csv al path passat per paràmetre
     * @param path
     */
    public static void carregarCSV(String path) {
        cd.carregaCSV(path);
    }

    /** Crida al mètode del guarda Document i guarda el document a format .prop */
    public static void guardaDocument() {
        cd.guardaDocument();
    }

    /**
     * Crida al mètode carregaDocument de CtrlDomini que carrega el document en format .prop al full de càlcul
     * @param path
     */
    public static void carregaDocument(String path) {
        cd.carregaDocument(path);
    }

    /**
     * Crida al mètode cercarValorPos de CtrlDomini que retorna les posicions de les cel·les que contenen el valor passat per paràmetre
     * @param valor
     * @return Matriu de Pair de Strings
     */
    public static Vector<Pair<Integer, Integer>> cercarValorPos(String valor) {
        return cd.cercarValorPos(valor);
    }

    /**
     * Crida al mètode reemplacarValor de CtrlDomini que reemplaça el valor cerca pel valor reemplac d'entre el bloc de
     * cel·les que hi ha entre idC1,idF1 i idC2 i idF2
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     * @param cerca
     * @param reemplac
     */
    public static void reemplacarValor(int idC1, int idF1, int idC2, int idF2, String cerca, String reemplac) {
        cd.reemplacarValor(idC1, idF1, idC2, idF2, cerca, reemplac);
    }

    /**
     * Ordena els valors de les cel·les dins del bloc entre idC1,idF1 i idC2 i idF2
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     */
    public static void ordenarBloc(int idC1, int idF1, int idC2, int idF2) {
        cd.ordenarBloc(idC1, idF1, idC2, idF2);
    }

    /**
     * Crida al mètode getNCol de CtrlDomini que retorna el número de columnes que conté l'ArrayList de dues dimensions
     * del clipboard
     * @return int
     */
    public static int getNCol() {
        return cd.getNCol();
    }

    /**
     * Crida al mètode getNFil de CtrlDomini que retorna el número de files que conté l'ArrayList de dues dimensions
     * del clipboard
     * @return int
     */
    public static int getNFil() {
        return cd.getNFil();
    }

    /**
     * Retorna l'array de dues dimensions que conté el contingut copiat o retallat
     * @return Matriu amb ArrayList de Cela
     */
    public static ArrayList<ArrayList<String>> getClipboard() {
        return cd.getClipboard();
    }

    /**
     * Crida al mètode copiarBloc del CtrlDomini que copia al clipboard intern a CtrlDomini el contingut de les cel·les
     * entre idC1, idF1, i idC2, idF2
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     */
    public static void copiaBloc(int idC1, int idF1, int idC2, int idF2) {
        cd.copiarBloc(idC1, idF1, idC2, idF2);
    }

    /**
     * Crida al mètode retallarBloc del CtrlDomini que copia al clipboard intern a CtrlDomini el contingut de les cel·les
     * entre idC1, idF1, i idC2, idF2 i esborra el contingut en aquell bloc de cel·les.
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     */
    public static void retallaBloc(int idC1, int idF1, int idC2, int idF2) {
        cd.tallarBloc(idC1, idF1, idC2, idF2);
    }

    /**
     * Crida al mètode enganxar del CtrlDomini que enganxa al bloc començant per j,i el contingut del clipboard
     * @param j
     * @param i
     */
    public static void enganxa(int j, int i) {
        cd.enganxar(j, i);
    }

    /**
     * Crida al mètode getType del CtrlDomini i retorna el Type del String passat per paràmetre
     * @param s
     */
    public static TType getType(String s) {
        return cd.getType(s);
    }

    /**
     * Crida al mètode eliminarDocument del CtrlDomini i elimina de configuració el document passat per paràmetre
     * @param nomDoc
     */
    public static void eliminarDocument(String nomDoc) {
        cd.eliminarDocument(nomDoc);
    }

}
