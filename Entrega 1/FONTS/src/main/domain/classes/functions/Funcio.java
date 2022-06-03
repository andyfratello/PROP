package main.domain.classes.functions;

import java.util.*;
import java.time.LocalDate;
import main.domain.classes.types.Binari;
import main.domain.classes.types.Hexadecimal;
import main.domain.classes.exceptions.NegativeRootException;
import main.domain.classes.exceptions.NegativeFactorialException;

/**
 * Aquesta classe abstracta conté tots els mètodes de totes les funcions que s'implementen al sistema
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public abstract class Funcio {
    // Setters
    public abstract void setValueD(double _value);
    public abstract void setValueI(int _value);
    public abstract void setValueS(String _value);
    public abstract void setValueLD(LocalDate _value);
    public abstract void setValueV(Vector<Double> _value);
    public abstract void setValue2D(double _x, double _y);
    public abstract void setValueB(Binari _value);
    public abstract void setValueH(Hexadecimal _value);
    public abstract void setValueSSS(String _entrada, String _vell, String _nou);
    public abstract void setValue2V(Vector<Double> x, Vector<Double> y);

    // Mètodes d'operació
    public abstract double absolut();
    public abstract int factorial() throws NegativeFactorialException;
    public abstract String reemplaca();
    public abstract Binari decimalABinari();
    public abstract int obteDia();
    public abstract double arrel() throws NegativeRootException;
    public abstract double mediana();
    public abstract double resta();
    public abstract Hexadecimal decimalAHexadecimal();
    public abstract String obteDiaSetmana();
    public abstract int arrodonir();
    public abstract double mitjana();
    public abstract double suma();
    public abstract int doubleAInt();
    public abstract int obteMes();
    public abstract double correlacioPearson();
    public abstract double multiplicacio();
    public abstract int truncar();
    public abstract int hexadecimalADecimal();
    public abstract int tamanyText();
    public abstract double covariancia();
    public abstract String aMajuscula();
    public abstract int numeroParaules();
    public abstract double divisio();
    public abstract double potencia();
    public abstract int binariADecimal();
    public abstract int obteAny();
    public abstract double desviacioEstandar();
    public abstract double variancia();
}