package main.domain.controllers;

import main.persistence.controllers.CtrlPersistencia;
import main.domain.classes.config.Configuracio;
import main.domain.classes.functions.Interpret;
import main.domain.classes.types.TType;
import main.domain.classes.types.Pair;
import main.domain.classes.Cela;
import java.util.*;
import java.io.*;

/**
 * El controlador de Domini s’encarrega d’instanciar la resta de controladors de la capa de domini i proporciona una
 * interfície més pròxima a l’usuari dels mètodes de la resta de controladors com el CtrlDocument o el CtrlClipboard.
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class CtrlDomini {
    /**
     * Conte la instancia del CtrlDocument que s'utilitza per
     * mantenir el document obert i quin full es troba actiu.
     */
    private final CtrlDocument _ctrlDocument;
    /**
     * Conte la instancia del CtrlClipboard que proporciona la funcionalitat de copiar/tallar i enganxar
     */
    private final CtrlClipboard _ctrlClipboard;
    /**
     * Conte la instancia del CtrlPersistencia que ens proporciona acces a les dades generades per l'usuari anteriorment.
     */
    private final CtrlPersistencia _ctrlPersistencia;
    /**
     * Conte la instancia de la classe que mante la "configuracio" (Documents recents)
     */
    private Configuracio _config;

    /**
     * Constructora per defecte sense parametres que instancia els diferents controladors
     * i classes que depenen de CtrlDomini
     */
    public CtrlDomini() {
        _ctrlDocument = new CtrlDocument();
        _ctrlClipboard = new CtrlClipboard(_ctrlDocument);
        _ctrlPersistencia = new CtrlPersistencia();
        // Carrega config de capa de persistència
        if (_ctrlPersistencia.existeixConfiguracio()) {
            try {
                byte[] bytes = _ctrlPersistencia.carregaConfig();
                ByteArrayInputStream bs = new ByteArrayInputStream(bytes);
                ObjectInputStream is = new ObjectInputStream(bs);
                _config = (Configuracio) is.readObject();
                is.close();
            } catch (InvalidClassException e) {
                System.err.println("HANDLEABLE EXCEPTION -> CLASS VERSION MISMATCH");
                System.err.println("Regenerating config.cdp file");
                if (!_ctrlPersistencia.borraConfig()) {
                    System.err.println("Could not handle exception! Quitting.");
                    System.err.println(Thread.currentThread().getStackTrace().toString());
                    System.exit(-100);
                }
                _config = new Configuracio();
                System.err.println("SUCCESS -> Exception handled!");
            } catch (Exception e) {
                System.err
                        .println("HANDLEABLE EXCEPTION -> Exception when trying to write/create configuration file! E: "
                                + e.getMessage());
                System.err.println("Regenerating config.cdp file");
                if (!_ctrlPersistencia.borraConfig()) {
                    System.err.println("Could not handle exception! Quitting.");
                    System.err.println(Thread.currentThread().getStackTrace().toString());
                    System.exit(-100);
                }
                _config = new Configuracio();
                System.err.println("SUCCESS -> Exception handled!");
            }
        }
        else {
            _config = new Configuracio();
        }
    }


    /**
     * Busca les celes que contenen el valor usuari == valor
     * @param valor
     * @return Vector de Strings on cada entrada es el valor usuari d'una ocurrència de la cela
     */
    @Deprecated
    public Vector<String> cercarValorString(String valor) {
        Set<Pair<Pair<Integer, Integer>, String>> res = _ctrlDocument.obtenirFullActiu().CercarValor(valor); // cercarValor(valor);
        Vector<String> out = new Vector<String>();
        res.forEach(c -> {
            out.add(c.second());
        });
        return out;
    }

    /**
     * Busca les celes que contenen el valor usuari == valor
     * @param valor
     * @return Vector de Pair d'integers on cada entrada es la posició de cada ocurrència
     */
    public Vector<Pair<Integer, Integer>> cercarValorPos(String valor) {
        Set<Pair<Pair<Integer, Integer>, String>> res = _ctrlDocument.obtenirFullActiu().CercarValor(valor); // cercarValor(valor);
        Vector<Pair<Integer, Integer>> out = new Vector<Pair<Integer, Integer>>();
        res.forEach(c -> {
            out.add(c.first());
        });
        return out;
    }

    /**
     * Busca les celes que tenen el valor usuari == cerca i reemplaca aquest valor
     * per reemplac si estan dins del bloc format per les Celes idC1, idF1 i idC2,
     * idF2
     *
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     * @param cerca
     * @param reemplac
     */
    public void reemplacarValor(int idC1, int idF1, int idC2, int idF2, String cerca, String reemplac) {
        // _ctrlDocument.cercarValor(cerca).forEach(posICela -> {
        _ctrlDocument.obtenirFullActiu().CercarValor(cerca).forEach(posICela -> {
            if (posICela.first().first() >= idC1 && posICela.first().first() <= idC2
                    && posICela.first().second() >= idF1 && posICela.first().second() <= idF2) {
                modificarCela(posICela.first().first(), posICela.first().second(), posICela.second().replaceAll(cerca, reemplac));
            }
        });
    }

    /**
     * Copia el valor de la Cela i es guarda el seu valor en el Clipboard
     *
     * @param idC
     * @param idF
     */
    public void copiarCela(int idC, int idF) {
        _ctrlClipboard.copiarBloc(idC, idF, idC, idF);
    }

    /**
     * Talla el valor de la Cela, es guarda el seu valor en el Clipboard
     * i es borra el contingut de les Celes de les que es talla
     *
     * @param idC
     * @param idF
     */
    public void tallarCela(int idC, int idF) {
        _ctrlClipboard.tallarBloc(idC, idF, idC, idF);
    }

    /**
     * Enganxa el contingut del Clipboard des de la Cela (idF, idC)
     * El contingut que s'engenxa pot ser un bloc.
     *
     * @param idC
     * @param idF
     */
    public void enganxar(int idC, int idF) {
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
     *
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
     *
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
     * Retorna el valor d'usuari a la Cela idF, idC en el Full idFull
     * <p>
     * <b>(Es queda el Full idFull seleccionat) </b>
     *
     * @param idC
     * @param idF
     * @param idFull
     * @return String (Valor usuari) si existeix, null altrament
     */
    public String obtenirValorCela(int idC, int idF, int idFull) {
        if (_ctrlDocument.getDocumentObert().GetFull(idFull) == null
                || _ctrlDocument.getDocumentObert().GetFull(idFull).GetCela(idC, idF) == null) {
            return "";
        }
        _ctrlDocument.setFullActiu(idFull);
        Cela c = _ctrlDocument.obtenirCela(idC, idF);
        return c.GetValorUsuari();
    }

    /**
     * Retorna el valor real de la Cela idF, idC
     *
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
     *
     * @param idC
     * @param idF
     * @return String (Valor real) si existeix, null altrament
     */
    public String obtenirValorRealCela(int idC, int idF, int idFull) {
        if (_ctrlDocument.getDocumentObert().GetFull(idFull) == null
                || _ctrlDocument.getDocumentObert().GetFull(idFull).GetCela(idC, idF) == null) {
            return "";
        }
        _ctrlDocument.setFullActiu(idFull);
        Cela c = _ctrlDocument.obtenirCela(idC, idF);
        return c.GetValorReal();
    }

    /**
     * Ordena per columnes dins d'un bloc designat per les Celes idC1, idF1 i idC2, idF2
     *
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     */
    public void ordenarBloc(int idC1, int idF1, int idC2, int idF2) {
        _ctrlDocument.obtenirFullActiu().OrdenarBloc(new Pair<>(idC1, idF1), new Pair<>(idC2, idF2), false);
    }

    /**
     * Crea un full i es posa com a full actiu
     */
    public void crearFull() {
        _ctrlDocument.crearFull();
    }

    /**
     * Crea un full amb nom = nomFull i es posa com a full actiu
     * @param nomFull
     */
    public void crearFull(String nomFull) throws Exception {
        _ctrlDocument.CrearFull(nomFull);
    }

    /**
     * Crea un full de numCols*numFiles
     * @param numCols
     * @param numFiles
     */
    public void crearFull(int numCols, int numFiles) {
        _ctrlDocument.crearFull(numCols, numFiles);
    }

    /**
     * Crea un full de numCols*numFiles
     * @param nomFull
     * @param numCols
     * @param numFiles
     */
    public void crearFull(String nomFull, int numCols, int numFiles) throws Exception {
        _ctrlDocument.crearFull(nomFull, numCols, numFiles);
    }

    /**
     * Canvia el full actiu a idf
     * @param idf
     */
    public void setFullActiu(int idf) throws IndexOutOfBoundsException {
        if (!esIdFullValid(idf)) {
            throw new IndexOutOfBoundsException("No existeix Full amb id = " + Integer.valueOf(idf).toString());
        }
        _ctrlDocument.setFullActiu(idf);
    }

    /**
     * Retorna el nom del document obert actualment
     * @return String (Nom del document)
     */
    public String getNomDocument() {
        return _ctrlDocument.getDocumentObert().GetNom();
    }

    /**
     * Retorna el path del document obert actualment
     * @return String (Path del document)
     */
    public String getPathDocument() {
        return _ctrlDocument.getDocumentObert().GetLocal();
    }

    /**
     * Retorna una llista amb el numero d'IDs dels fulls del document obert
     * @return Llista d'integers (Llista d'IDs dels fulls)
     */
    public List<Integer> getIDsFulls() {
        return _ctrlDocument.getDocumentObert().getTotsIDs();
    }

    /**
     * Retorna el numero de files que te el full actiu
     * @return int (Numero de files del full actiu)
     */
    public int getNumFiles() {
        return _ctrlDocument.obtenirFullActiu().GetNumFil();
    }

    /**
     * Retorna el numero de columnes que te el full actiu
     * @return int (Numero de columnes del full actiu)
     */
    public int getNumColumnes() {
        return _ctrlDocument.obtenirFullActiu().GetNumCol();
    }

    /**
     * Retorna el numero de fulls del document obert
     * @return int (Numero de fulls del document obert)
     */
    public int getNumFulls() {
        return _ctrlDocument.getDocumentObert().GetNumFulls();
    }

    //TODO: Check si afegirConfig ja existeix el valor
    /**
     * Crea un document amb localitzacio = path, s'afegeix a documents recents i
     * es canvia el document obert al nou document si no n'hi ha cap
     * @param path (Path del document a crear)
     */
    public void crearDocument(String path) {
        _ctrlDocument.crearDocument(path);
        //_config.afegirConfig(_ctrlDocument.getDocumentObert().GetNom(), path);
        //guardaConfig();
    }

    /**
     * Crea un document amb localitzacio = path i nom = nomDoc, s'afegeix a documents recents i
     * es canvia el document obert al nou document si no n'hi ha cap
     * @param path (Path del document a crear)
     * @param nomDoc (Nom del document a crear)
     */
    public void crearDocument(String path, String nomDoc) {
        _ctrlDocument.crearDocument(path, nomDoc);
        //_config.afegirConfig(nomDoc, path);
        //guardaConfig();
    }

    /**
     * Es tanca el document que esta actualment obert
     */
    public void tancarDocument() {
        _ctrlDocument.tancarDocument();
    }

    /**
     * S'afegeix una fila just en el index = pos en el full actiu actualment
     * @param pos (Posicio on es vol inserir la fila)
     */
    public void afegirFila(int pos) {
        _ctrlDocument.AfegirFila(pos);
    }

    /**
     * S'afegeix una columna just en el index = pos en el full actiu actualment
     * @param pos (Posicio on es vol inserir la columna)
     */
    public void afegirColumna(int pos) {
        _ctrlDocument.AfegirColumna(pos);
    }

    /**
     * S'elimina la fila que es troba en l'index = pos en el full actiu actualment
     * @param pos (Posicio de la fila que es vol eliminar)
     */
    public void eliminarFila(int pos) {
        _ctrlDocument.EliminarFila(pos);
    }

    /**
     * S'elimina la columna que es troba en l'index = pos en el full actiu actualment
     * @param pos (Posicio de la columna que es vol eliminar)
     */
    public void eliminarColumna(int pos) {
        _ctrlDocument.EliminarColumna(pos);
    }

    /**
     * Afegeix una fila a la posicio amb l'index = pos en el full idFull
     * @param pos (Posicio de la fila que es vol eliminar)
     * @param idFull (Index del full)
     */
    public void afegirFilaFull(int pos, int idFull) {
        _ctrlDocument.setFullActiu(idFull);
        _ctrlDocument.AfegirFila(pos);
    }

    /**
     * Afegeix una columna a la posicio amb l'index = pos en el full idFull
     * @param pos (Posicio de la columna que es vol eliminar)
     * @param idFull (Index del full)
     */
    public void afegirColumnaFull(int pos, int idFull) {
        _ctrlDocument.setFullActiu(idFull);
        _ctrlDocument.AfegirColumna(pos);
    }

    /**
     * S'elimina la fila que es troba en l'index = pos en el full idFull
     * @param pos (Posicio de la fila que es vol eliminar)
     * @param idFull (Index del full)
     */
    public void eliminarFilaFull(int pos, int idFull) {
        _ctrlDocument.setFullActiu(idFull);
        _ctrlDocument.EliminarFila(pos);
    }

    /**
     * S'elimina la columna que es troba en l'index = pos en el full idFull
     * @param pos (Posicio de la columna que es vol eliminar)
     * @param idFull (Index del full)
     */
    public void eliminarColumnaFull(int pos, int idFull) {
        _ctrlDocument.setFullActiu(idFull);
        _ctrlDocument.EliminarColumna(pos);
    }

    /**
     * S'elimina el full actiu actualment
     */
    public void eliminarFullActiu() {
        _ctrlDocument.EliminarFullActiu();
    }

    /**
     * Es canvia el nom del full actiu a nom
     * @param nom (Nom nou del full actiu)
     */
    public void canviarNomFullActiu(String nom) {
        _ctrlDocument.canviarNomFull(nom);
    }

    /**
     * Es canvia el nom del document obert
     * @param nom (Nom nou del document obert)
     */
    public void canviarNomDocument(String nom) {
        _ctrlDocument.canviarNomDoc(nom);
    }

    /**
     * Es modifica el valor de la Cela idC, idF amb el valor valor del full actual
     * @param idC (id de la columna que identifica a la Cela)
     * @param idF (id de la fila que identifica a la Cela)
     * @param valor (Nou valor de la Cela)
     */
    public void modificarCela(int idC, int idF, String valor) {
        _ctrlDocument.obtenirFullActiu().ModificaCela(idC, idF, valor);
        //_config.documentModificat(_ctrlDocument.getDocumentObert().GetNom(), LocalDateTime.now());
        //guardaConfig();
    }

    /**
     * Es modifica el valor de la Cela idC, idF amb el valor valor del full idFull
     * @param idC (id de la columna que identifica a la Cela)
     * @param idF (id de la fila que identifica a la Cela)
     * @param idFull (id que identifica el full on es vol modificar la Cela)
     * @param valor (Nou valor de la Cela)
     */
    public void modificarCelaFull(int idC, int idF,int idFull, String valor) {
        _ctrlDocument.setFullActiu(idFull);
        _ctrlDocument.obtenirFullActiu().ModificaCela(idC, idF, valor);
        //_config.documentModificat(_ctrlDocument.getDocumentObert().GetNom(), LocalDateTime.now());
        //guardaConfig();
    }

    /**
     * S'elimina el document amb nom = nomDoc de la "configuracio" (Documents recents)
     * @param nomDoc (Nom del document que es vol eliminar)
     */
    public void eliminarDocument(String nomDoc) {
        _config.eliminarConfig(nomDoc);
        guardaConfig();
    }

    /**
     * Guarda l'objecte Configuracio _config en un fitxer utilitzant el CtrlPersistencia
     */
    public void guardaConfig() {
        // Escriu config a capa de persistència
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bs);
            os.writeObject(_config);
            os.close();
            byte[] bytes = bs.toByteArray();
            _ctrlPersistencia.guardaConfig(bytes);
        } catch (IOException e) {
            System.err.println(
                    "RUNTIME UNHANDLED EXCEPTION -> IOException when trying to save config file! E: " + e.toString());
            Thread.currentThread().getStackTrace();
            System.exit(-102);
        }

    }

    /**
     * Converteix les dades de Configuracio _config a una matriu de String per poder compartir-les entre capes
     */
    public Vector<Vector<String>> obtenirConfig() {
        return _config.simplify();
    }

    /**
     * Es guarda el full idFull en format CSV utilitzant el path del document + nomDoc.csv
     */
    public void guardaCSV(int idFull) throws IndexOutOfBoundsException {
        if (!esIdFullValid(idFull)) {
            throw new IndexOutOfBoundsException("Could not open Full with id: " + idFull);
        }
        _ctrlDocument.setFullActiu(idFull);
        Vector<Vector<String>> x = new Vector<Vector<String>>();
        int nF = _ctrlDocument.getDocumentObert().GetFull(idFull).GetMaxFil() + 1; // getNumFiles();
        int nC = _ctrlDocument.getDocumentObert().GetFull(idFull).GetMaxCol() + 1;//getNumColumnes();
        for (int i = 0; i < nF; i++) {
            x.add(new Vector<String>());
            for (int j = 0; j < nC; j++) {
                x.get(i).add(obtenirValorRealCela(j, i));
            }
        }
        String nomDoc = _ctrlDocument.getDocumentObert().GetNom();
        if (!nomDoc.endsWith(".csv")) {
            nomDoc = nomDoc + ".csv";
        }

        _ctrlPersistencia.guardaCSV(x, _ctrlDocument.getDocumentObert().GetLocal() + "/" + nomDoc);
        _config.afegirConfig(nomDoc, _ctrlDocument.getDocumentObert().GetLocal());
        guardaConfig();
    }

    // TODO: Mirar si el full esta buit o crear?
    // TODO: Buscar si existeix path en config
    /**
     * Es carrega el fitxer indicat per path amb format CSV en una nova instancia de Document i Full.
     * El Full sera del tamany del CSV carregat
     */
    public void carregaCSV(String path) throws IllegalStateException {
        Vector<Vector<String>> x = _ctrlPersistencia.carregaCSV(path);
        if (x.size() <= 0) return;
        if (_ctrlDocument.getDocumentObert() != null) {
            /*
            System.err.println("RUNTIME UNHANDELED EXCEPTION -> IllegalStateException: Tried to load CSV while a document is open!");
            System.err.println(Thread.currentThread().getStackTrace().toString());
            System.exit(-103);
            */
            tancarDocument();
        }
        crearDocument(path.substring(0, path.lastIndexOf("/")));
        crearFull(x.get(0).size(), x.size());
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < x.get(i).size(); j++) {
                if (getNumFiles() <= i) {
                    afegirFila(i);
                }
                if (getNumColumnes() <= j) {
                    afegirColumna(j);
                }
                modificarCela(j, i, x.get(i).get(j));
            }
        }
    }


    public void guardaDocument()
    {
        String nomDoc = _ctrlDocument.getDocumentObert().GetNom();
        if (!nomDoc.endsWith(".prop")) {
            nomDoc = nomDoc + ".prop";
        }
        String path = _ctrlDocument.getDocumentObert().GetLocal() + "/" + nomDoc;
        try {
            byte[] bytes = _ctrlDocument.documentToByteArray();
            _ctrlPersistencia.guardaDocumet(bytes, path);
        } catch (IOException e) {
            System.err.println("[#GUARDAT] Error al guardar el document" + e.getMessage());
            Thread.currentThread().getStackTrace();
            System.exit(-102);
        }
        _config.afegirConfig(nomDoc, _ctrlDocument.getDocumentObert().GetLocal());
        guardaConfig();
    }

    public void carregaDocument(String path) {
        if (_ctrlDocument.getDocumentObert() != null) {
            System.err.println("Ja hi ha un document obert!");
            return;
        }
        try { _ctrlDocument.byteArrayToDocument(_ctrlPersistencia.carregaDocumet(path)); }
        catch (Exception e) { System.err.println("[#CARREGA] Error al carregar document" + e.getMessage()); }
    }


    /**
     * Comprova si el id del full existeix
     */
    private boolean esIdFullValid(int id) {
        List<Integer> ids = getIDsFulls();
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) == id) {
                return true;
            }
        }
        return false;
    }


    /**
     * Crida al mètode getNCol de Ctrl ClipBoard que retorna el numero de columnes que té la Matriu de Cela
     */
    public int getNCol() {
        return _ctrlClipboard.getNCol();
    }
    /**
     * Crida al mètode getNFil de Ctrl ClipBoard que retorna el numero de files que té el Matriu de Cela
     */
    public int getNFil() {
        return _ctrlClipboard.getNFil();
    }
    /**
     * Crida al mètode getClipboard del controlador de clipboard el qual retorna un Matriu de Cela
     */
    //@Deprecated
    public ArrayList<ArrayList<String>> getClipboard() {
        return _ctrlClipboard.getClipboard();
    }
    /** Crida al mètode del Interpret i retorna el Type del String passat per paràmetre
     * @param s
     */
    public TType getType(String s) {
        return Interpret.getType(s);

    }
}
