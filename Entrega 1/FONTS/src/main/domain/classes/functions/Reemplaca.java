package main.domain.classes.functions;

import main.domain.classes.exceptions.NegativeFactorialException;
import main.domain.classes.exceptions.NegativeRootException;
import main.domain.classes.types.Binari;
import main.domain.classes.types.Hexadecimal;

import java.time.LocalDate;
import java.util.Vector;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Reemplaca extends Funcio {
    private String _entrada;
    private String _vell;
    private String _nou;


    @Override
    public void setValueD(double _value) {

    }

    @Override
    public void setValueI(int _value) {

    }

    @Override
    public void setValueS(String _value) {

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

    public void setValueSSS(String x, String y, String z) {
        _entrada = x;
        _vell = y;
        _nou = z;
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

    /** Aquest metode reemplaca el valor vell per el valor nou en l'String entrada
     * @param
     */
    public String reemplaca() {
        return _entrada.replaceAll(_vell, _nou);
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
