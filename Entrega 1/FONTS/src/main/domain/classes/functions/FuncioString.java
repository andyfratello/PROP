package main.domain.classes.functions;

import main.domain.classes.exceptions.NegativeFactorialException;
import main.domain.classes.exceptions.NegativeRootException;
import main.domain.classes.types.Binari;
import main.domain.classes.types.Hexadecimal;

import java.time.LocalDate;
import java.util.Vector;

/**
 * Aquesta classe emmagatzema el valor String que despres s'utilitzara en una de les funcions
 * que hereden d'aquesta classe
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class FuncioString extends Funcio {
    protected String _value;

    @Override
    public void setValueD(double _value) {

    }

    @Override
    public void setValueI(int _value) {

    }

    /**
     * Aquest metode emmagatzema el valor String que despres s'utilitzara en una de les funcions
     * que hereden d'aquesta
     * @param x
     */
    public void setValueS(String x) {
        _value = x;
    }

    @Override
    public void setValueLD(LocalDate _value) {

    }

    @Override
    public void setValueV(Vector<Double> _value) {

    }

    @Override
    public void setValue2D(double _x, double _y) {

    }

    @Override
    public void setValueB(Binari _value) {

    }

    @Override
    public void setValueH(Hexadecimal _value) {

    }

    @Override
    public void setValueSSS(String _entrada, String _vell, String _nou) {

    }

    @Override
    public void setValue2V(Vector<Double> x, Vector<Double> y) {

    }

    @Override
    public double absolut() {
        return 0;
    }

    @Override
    public int factorial() throws NegativeFactorialException {
        return 0;
    }

    @Override
    public String reemplaca() {
        return null;
    }

    @Override
    public Binari decimalABinari() {
        return null;
    }

    @Override
    public int obteDia() {
        return 0;
    }

    @Override
    public double arrel() throws NegativeRootException {
        return 0;
    }

    @Override
    public double mediana() {
        return 0;
    }

    @Override
    public double resta() {
        return 0;
    }

    @Override
    public Hexadecimal decimalAHexadecimal() {
        return null;
    }

    @Override
    public String obteDiaSetmana() {
        return null;
    }

    @Override
    public int arrodonir() {
        return 0;
    }

    @Override
    public double mitjana() {
        return 0;
    }

    @Override
    public double suma() {
        return 0;
    }

    @Override
    public int doubleAInt() {
        return 0;
    }

    @Override
    public int obteMes() {
        return 0;
    }

    @Override
    public double correlacioPearson() {
        return 0;
    }

    @Override
    public double multiplicacio() {
        return 0;
    }

    @Override
    public int truncar() {
        return 0;
    }

    @Override
    public int hexadecimalADecimal() {
        return 0;
    }

    @Override
    public int tamanyText() {
        return 0;
    }

    @Override
    public double covariancia() {
        return 0;
    }

    @Override
    public String aMajuscula() {
        return null;
    }

    @Override
    public int numeroParaules() {
        return 0;
    }

    @Override
    public double divisio() {
        return 0;
    }

    @Override
    public double potencia() {
        return 0;
    }

    @Override
    public int binariADecimal() {
        return 0;
    }

    @Override
    public int obteAny() {
        return 0;
    }

    @Override
    public double desviacioEstandar() { return 0; }

    @Override
    public double variancia() { return 0; }

}
