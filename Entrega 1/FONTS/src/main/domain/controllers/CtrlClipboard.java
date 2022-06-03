package main.domain.controllers;

import main.domain.classes.Cela;
import main.domain.controllers.CtrlDomini;
import main.domain.classes.types.Pair;

import java.awt.datatransfer.Clipboard;
import java.util.*;

/**
 * El controlador de clipboard s’encarrega de gestionar els mètodes copiar, tallar i enganxar cel·les i blocs al full corresponent.
 * Les cel·les copiades queden dins un ArrayList<ArrayList<Cela>> que fa la funció de porta-retalls (clipboard)
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class CtrlClipboard {
    ArrayList<ArrayList<Cela>> Clipboard = new ArrayList<ArrayList<Cela>>();
    private CtrlDomini _ctrlDomini;

    /**
     * Constructora
     */
    public CtrlClipboard(CtrlDomini cd) {
        _ctrlDomini = cd;
    }

    /*public CtrlBloc(ArrayList<ArrayList<Cela>> cl) {
        Clipboard = cl;
    }

    public static void setCtrlDomini(CtrlDomini cd) {
        _ctrlDomini = cd;
    }*/

    /**
     * Getter Clipboard
     */
    public ArrayList<ArrayList<Cela>> getClipboard() {
        return Clipboard;
    }

    /**
     * Copiar al clipboard totes les cel·les del bloc
     * @param int idF1, int idC1, int idF2, int idC2
     */
    public void copiarBloc(int idC1, int idF1, int idC2, int idF2) {
        for (int i = 0; i < (idC2 - idC1 + 1); ++i) {
            Clipboard.add(new ArrayList<Cela>());
            for (int j = 0; j < (idF2 - idF1 + 1); ++j) {
                Cela c = _ctrlDomini.obtenirCela(idC1 + i, idF1 + j);
                Cela nova = new Cela(c.GetValorUsuari());
                Clipboard.get(i).add(nova);
            }
        }
    }

    /**
     * Copiar al clipboard totes les cel·les del bloc i esborra el bloc
     * @param int idF1, int idC1, int idF2, int idC2
     */
    public void tallarBloc(int idC1, int idF1, int idC2, int idF2) {
        for (int i = 0; i < (idC2 - idC1 + 1); ++i) {
            Clipboard.add(new ArrayList<Cela>());
            for (int j = 0; j < (idF2 - idF1 + 1); ++j) {
                Cela c = _ctrlDomini.obtenirCela(idC1 + i, idF1 + j);
                Cela nova = new Cela(c.GetValorUsuari());
                Clipboard.get(i).add(nova); //set(j, nova);
                _ctrlDomini.modificarCela(i, j, ""); // Esborra contingut un cop copiat
            }
        }
    }

    /**
     * Enganxa al full les cel·les que té guardades al clipboard
     * @param int idF, int idC
     */
    public void enganxar(int idC, int idF) {
        int colSize = Clipboard.size();
        int rowSize = Clipboard.get(0).size();
        // Canviar valors del bloc que comença a la cel·la idF, idC
        for (int i = 0; i < colSize; ++i) {
            for (int j = 0; j < rowSize; ++j) {
                //Cela c = _ctrlDomini.obtenirCela(i + idC, j + idF);
                _ctrlDomini.modificarCela(i + idC, j + idF, Clipboard.get(i).get(j).GetValorUsuari());
            }
        }
    }
}
