package main.domain.controllers;

import main.domain.classes.Cela;
import main.domain.controllers.CtrlDocument;
import main.domain.classes.types.Pair;

import java.math.BigDecimal;
import java.util.*;

/**
 * El controlador de clipboard s’encarrega de gestionar els mètodes copiar, tallar i enganxar cel·les i blocs al full corresponent.
 * Les cel·les copiades queden dins una matriu de cel·les que fa la funció de porta-retalls (clipboard)
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 * NOTE: L'us de les cel·les podria canviarse per strings?, si al enganxar es fa GetValorUsuari, perque no guardarlo directament?
 */
public class CtrlClipboard {
    /** Matriu de strings que conté tots els valors de les cel·les copiades */
    ArrayList<ArrayList<String>> Clipboard = new ArrayList<ArrayList<String>>();
    /** Instància de CtrlDocument */
    private CtrlDocument _ctrlDocument;

    /** Número de columnes */
    private int colSize = 0;
    /** Número de files */
    private int rowSize = 0;

    /**
     * Constructora
     */
    public CtrlClipboard(CtrlDocument cd) {
        _ctrlDocument = cd;
    }

    /**
     * Getter Clipboard
     */
    public ArrayList<ArrayList<String>> getClipboard() {
        return Clipboard;
    }

    public int getNCol() {
        return Clipboard.size();
    }
    public int getNFil() {
        return Clipboard.get(0).size();
    }

    /**
     * Copiar al clipboard totes les cel·les del bloc
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     */
    public void copiarBloc(int idC1, int idF1, int idC2, int idF2) {
        colSize = (idC2 - idC1);
        rowSize = (idF2 - idF1);
        Clipboard.clear();
        for (int i = 0; i <= colSize; ++i) {
            Clipboard.add(new ArrayList<String>());
            for (int j = 0; j <= rowSize; ++j) {

                String valor = "";
                if (_ctrlDocument.obtenirCela(idC1+i, idF1 +j ) != null) {
                    valor = _ctrlDocument.obtenirCela(idC1 + i, idF1 + j).GetValorUsuari();
                }
                Clipboard.get(i).add(valor);
            }
        }
    }

    /**
     * Copiar al clipboard totes les cel·les del bloc i esborra el bloc
     * @param idC1
     * @param idF1
     * @param idC2
     * @param idF2
     */
    public void tallarBloc(int idC1, int idF1, int idC2, int idF2) {
        colSize = (idC2 - idC1);
        rowSize = (idF2 - idF1);
        Clipboard.clear();
        for (int i = 0; i <= colSize; ++i) {
            Clipboard.add(new ArrayList<String>());
            for (int j = 0; j <= rowSize; ++j) {

                String valor = "";
                if (_ctrlDocument.obtenirCela(idC1+i, idF1 +j ) != null) {
                    valor = _ctrlDocument.obtenirCela(idC1 + i, idF1 + j).GetValorUsuari();
                }

                Clipboard.get(i).add(valor);
                _ctrlDocument.modificarCela(i, j, ""); // Esborra contingut un cop copiat
            }
        }
    }

    /**
     * Enganxa al full les cel·les que té guardades al clipboard
     * @param idC
     * @param idF
     */
    public void enganxar(int idC, int idF) {
        // Canviar valors del bloc que comença a la cel·la idF, idC
        //System.err.println("Comenca enganxa");
        for (int i = 0; i <= colSize; ++i) {

            for (int j = 0; j <= rowSize; ++j) {

                //System.err.println("Enganxant (" + String.valueOf(i + idC) + ", " + String.valueOf(j + idF) + ") = " + Clipboard.get(i).get(j));
                _ctrlDocument.modificarCela(i + idC, j + idF, Clipboard.get(i).get(j));
            }
        }
        //System.err.println("Enganxat!");
    }

}

