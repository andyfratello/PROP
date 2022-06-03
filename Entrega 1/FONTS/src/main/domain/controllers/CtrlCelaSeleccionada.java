package main.domain.controllers;

import java.util.Set;
import main.domain.classes.Cela;
import main.domain.controllers.CtrlDocument;
import main.domain.classes.types.Pair;

/**
 * Classe que representa la cel·la seleccionada per l'USUARI
 * La seva rellevancia sera més aparent amb la introducció de la capa de presentació
 * @author Marc Duch Buechler (marc.duch@estudiantat.upc.edu)
 */
public class CtrlCelaSeleccionada {
    /** Documento obert */
    private CtrlDocument _docObert;
    /** Referencia a l'objecte Cela seleccionat */
    private Cela _celaSeleccionada;
    /** Posicio de la cel·la seleccionada */
    private Pair<Integer,Integer> _posicio;


    // Creadores
    private CtrlCelaSeleccionada(Cela cela, Pair<Integer,Integer> pos, CtrlDocument doc){
        _celaSeleccionada = cela;
        _posicio = pos;
        _docObert = doc;
    }

    public CtrlCelaSeleccionada(CtrlDocument doc) { this(null, null, doc); }

    public CtrlCelaSeleccionada(CtrlDocument doc, Cela c)
    {
        Pair<Integer,Integer> nPos = null;
        Set<Pair<Pair<Integer,Integer>, Cela>> cels = doc.CercarValor(c.GetValorUsuari());
        for (Pair<Pair<Integer,Integer>, Cela> pairCela : cels) { if (c.equals(pairCela.second()) ) nPos = pairCela.first(); }

        _posicio = nPos;
        _docObert =doc;
        _celaSeleccionada = c;
    }

    public CtrlCelaSeleccionada(CtrlDocument doc, Pair<Integer,Integer> nPos) { this (doc.obtenirCela(nPos.first(),nPos.second()), nPos, doc); }

    // Getters
	public Cela GetCelaSeleccionada() { check(); return _celaSeleccionada; }
	public Pair<Integer,Integer> GetPosicio() { check(); return _posicio; }
    
    // Setters
    public void SelectNovaCela (Pair<Integer,Integer> pos)
    {
        _celaSeleccionada = _docObert.obtenirCela(pos.first(),pos.second());
        _posicio = pos;
    }

    public String GetValorUsuari() { check(); return _celaSeleccionada == null ?  null : _celaSeleccionada.GetValorUsuari(); }

    public String GetValorReal() { check(); return _celaSeleccionada == null ?  null :  _celaSeleccionada.GetValorReal(); }

    public void Modificar(String valor) {
        check();
        if(_celaSeleccionada != null) _celaSeleccionada.ModificaValor(valor);
        else _docObert.modificarCela(_posicio.first(),_posicio.first(), valor);
    }

    private void check() { this.SelectNovaCela(_posicio);}

    /**
     public boolean CanviarColor (TColor color)
     {
     if (_celaSeleccionada == null) return false;
     _celaSeleccionada.Color...
     }
     **/
}
