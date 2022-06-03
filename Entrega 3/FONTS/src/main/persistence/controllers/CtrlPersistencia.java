package main.persistence.controllers;

import main.persistence.classes.config.GestorConfiguracio;
import main.persistence.classes.document.GestorDocument;
import main.persistence.classes.csv.GestorCSV;

import java.util.Vector;

/**
 * El controlador de Presentació s’encarrega de fer de comunicador entre les vistes de la capa de presentació. També és
 * el que transmet a la capa de presentació les dades de les capes inferiors.
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class CtrlPersistencia {
    /** Instància de GestorConfiguracio */
    private final GestorConfiguracio _gC;
    /** Instància de GestroCSV */
    private final GestorCSV _gCSV;
    /** Instància de GestorDocument */
    private final GestorDocument _gDoc;

    /**
     * Constructora sense paràmetres que instància les classes de capa de persistència
     * necessàries per poder proporcionar tota la funcionalitat (exportar/guardar .CSV, .PROP i carregar .CSV, .PROP)
     */
    public CtrlPersistencia() {
        _gC = new GestorConfiguracio();
        _gCSV = new GestorCSV();
        _gDoc = new GestorDocument();
    }

    /**
     * Aquest mètode permet guardar el contingut de la classe serialitzada Configuracio
     * en el fitxer "config.cdp"
     * @param bytes Configuració com a byte array
     */
    public void guardaConfig(byte[] bytes) {
        _gC.guardaConfig(bytes);
    }

    /**
     * Aquest mètode permet carregar el contingut de la classe serialitzada Configuracio
     * del fitxer "config.cdp"
     * @return Configuració com a byte array
     */
    public byte[] carregaConfig() {
        return _gC.carregaConfig();
    }

    /**
     * Aquest mètode determina si existeix contingut a la classe serialitzada Configuracio
     * @return true si existeix l'arxiu a configuració
     */
    public boolean existeixConfiguracio() {
        return _gC.existeixConfiguracio();
    }

    /**
     * Aquest mètode esborra el fitxer de Configuracio
     * @return true conforme s'ha esborrat
     */
    public boolean borraConfig() {
        return _gC.borraConfig();
    }

    /**
     * Aquest mètode guarda el full representat en matriu de Strings al path passat per paràmetre
     * @param x
     * @param path
     */
    public void guardaCSV(Vector<Vector<String>> x, String path) {
        _gCSV.guardaFull(x, path);
    }

    /**
     * Aquest mètode retorna el full representat en matriu de Strings que hi ha al path passat per paràmetre
     * @param path
     * @return matriu de Strings representant el full
     */
    public Vector<Vector<String>> carregaCSV(String path) {
        return _gCSV.carregaFull(path);
    }

    /**
     * Aquest mètode retorna el document representat en una sèrie de bytes, ja que s'ha serialitzat, que hi ha al path
     * passat per paràmetre
     * @param path
     * @return document representat en cadena de bytes
     */
    public byte[] carregaDocumet(String path) { return _gDoc.CarregarDocument(path); }

    /**
     * Aquest mètode guarda el document representat en bytes al path passat per paràmetre
     * @param bytes
     * @param path
     */
    public void guardaDocumet(byte[] bytes, String path) { _gDoc.GestorDocument(bytes, path);}
}
