package test.functions;

import main.domain.classes.functions.Interpret;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import main.domain.classes.types.TType;

//import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestInterpret {
    // Interpret Interpret;

    // @BeforeEach
    /* void setUp() {
        Interpret = Interpret.getInstance();
    }*/

    // private static CtrlDomini ctrlDomini;
    /*public static void setCtrlDomini(CtrlDomini cd) {
        ctrlDomini = cd;
    }*/

    /**
     * Objecte de la prova: Test del mètode testInterpretAsSingleton de la classe TestInterpret.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es comprova que només existeixi una instància de Interpret ja que es tracta d'una classe singleton.
     * Operativa: Es defineixen dues instàncies de Interpret i es comprova que aquestes siguin la mateixa ja que només en pot existir una.
     */
    @Test
    public void testInterpretAsSingleton() {
        Interpret instance1 = Interpret.getInstance();
        Interpret instance2 = Interpret.getInstance();
        //System.out.println("Mirant que el singleton tingui només una instància igual");
        assertEquals(true, instance1==instance2);
    }


    // getType()
    /**
     * Objecte de la prova: Test del mètode getType de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es comprova que l'entrada introduïda manualment sigui de tipus INT
     * Operativa: En aquest test es comprova que el string passat per paràmetre a la funció getType sigui de tipus INT, definit al enum TType
     */
    @Test
    public void getTypeInt() {
        assertEquals(TType.INT, Interpret.getType("1234"));
    }

    /**
     * Objecte de la prova: Test del mètode getType de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es comprova que l'entrada introduïda manualment sigui de tipus DOUBLE
     * Operativa: En aquest test es comprova que el string passat per paràmetre a la funció getType sigui de tipus DOUBLE, definit al enum TType
     */
    @Test
    public void getTypeDouble() {
        assertEquals(TType.DOUBLE, Interpret.getType("12.34"));
    }

    /**
     * Objecte de la prova: Test del mètode getType de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es comprova que l'entrada introduïda manualment sigui de tipus DATE
     * Operativa: En aquest test es comprova que el string passat per paràmetre a la funció getType sigui de tipus DATE, definit al enum TType
     */
    @Test
    public void getTypeDate() {
        assertEquals(TType.DATE, Interpret.getType("11/09/1714"));
    }

    /**
     * Objecte de la prova: Test del mètode getType de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es comprova que l'entrada introduïda manualment sigui de tipus FUNCTION
     * Operativa: En aquest test es comprova que el string passat per paràmetre a la funció getType sigui de tipus FUNCTION, definit al enum TType
     */
    @Test
    public void getTypeFunction() {
        assertEquals(TType.FUNCTION, Interpret.getType("=ARREL(4)"));
    }

    /**
     * Objecte de la prova: Test del mètode getType de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es comprova que l'entrada introduïda manualment sigui de tipus STRING
     * Operativa: En aquest test es comprova que el string passat per paràmetre a la funció getType sigui de tipus STRING, definit al enum TType
     */
    @Test
    public void getTypeString() {
        assertEquals(TType.STRING, Interpret.getType("chicago"));
    }

    // getDouble()
    /**
     * Objecte de la prova: Test del mètode getDouble de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa a Double el String passat per paràmetre
     * Operativa: En aquest test es comprova que el String es pot parsejar a tipus Double i es parseja si així és el cas.
     * Es dóna un marge de 0,0001 entre el double entrat manualment i el parsejat amb la funció getDouble
     */
    @Test
    public void getDouble1() {
        assertEquals(12.14, Interpret.getDouble("12.14"), 0.0001);
    }

    /**
     * Objecte de la prova: Test del mètode getDouble de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa a Double el String passat per paràmetre
     * Operativa: En aquest test es comprova que el String es no pot parsejar a tipus Double ja que és un dígit i retorna -1.
     * Es dóna un marge de 0,0001 entre el double entrat manualment i el parsejat amb la funció getDouble
     */
    @Test
    public void getDouble2() {
        assertEquals(-1, Interpret.getDouble("test"), 0.0001);
    }

    // getInt()
    /**
     * Objecte de la prova: Test del mètode getInt de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa a Integer el String passat per paràmetre
     * Operativa: En aquest test es comprova que el String es pot parsejar a tipus Integer i es parseja si així és el cas.
     */
    @Test
    public void getInt1() {
        assertEquals(1256, Interpret.getInt("1256"));
    }

    /**
     * Objecte de la prova: Test del mètode getInt de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa a Integer el String passat per paràmetre
     * Operativa: En aquest test es comprova que el String es no pot parsejar a tipus Integer ja que és un dígit i retorna -1.
     */
    @Test
    public void getInt2() {
        assertEquals(-1, Interpret.getInt("test"));
    }

    // getData()
    /**
     * Objecte de la prova: Test del mètode getData de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa a Date el String passat per paràmetre
     * Operativa: En aquest test es comprova que el String es pot parsejar a tipus LocalDate i es parseja si així és el cas.
     * Es defineix el format que utilitzem per als tipus Date amb el DateTimeFormatter i es comprova que es pot parsejar amb el mateix format
     */
    @Test
    public void getData1() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        assertEquals(LocalDate.parse("07/02/2001", formatter), Interpret.getData("07/02/2001"));
    }

    /**
     * Objecte de la prova: Test del mètode getData de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa a Date el String passat per paràmetre
     * Operativa: En aquest test es comprova que el String no es pot parsejar a tipus LocalDate amb un format que hem definit i retorna la data actual.
     */
    @Test
    public void getData2() {
        assertEquals(LocalDate.now(), Interpret.getData("test"));
    }

    // callFunction()
    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció ARRODONIR i es passa un dígit negatiu com a valor.
     * Seguidament es comprova que la funció és calculada i el valor és el correcte, que és el que introduïm manualment.
     */
    @Test
    public void callFunctionArrodonir1() {
        assertEquals("-3", Interpret.callFunction("=ARRODONIR(-2.7)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció ARRODONIR i es passa un dígit positiu com a valor.
     * Seguidament es comprova que la funció és calculada i el valor és el correcte, que és el que introduïm manualment.
     */
    @Test
    public void callFunctionArrodonir2() {
        assertEquals("7", Interpret.callFunction("=ARRODONIR(7.3)"));
    }

    /*
    @Test
    public void callFunctionArrodonir3() {
        assertEquals("7", Interpret.callFunction("=ARRODONIR(AB3)"));
    }*/

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció TRUNCAR i es passa un dígit amb decimals com a valor.
     * Seguidament es comprova que la funció és calculada i el valor és el correcte, que és el que introduïm manualment.
     */
    @Test
    public void callFunctionTruncar1() {
        assertEquals("9", Interpret.callFunction("=TRUNCAR(9.9)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció DEC2BIN i es passa un dígit com a valor.
     * Seguidament es comprova que la funció és calculada i que es passa el dígit del paràmetre a Binari, i es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionDecimalABinari1() {
        assertEquals("0b101", Interpret.callFunction("=DEC2BIN(5)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció BIN2DEC i es passa un dígit en binari com a valor.
     * Seguidament es comprova que la funció és calculada i que es passa el dígit en binari del paràmetre a decimal, i es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionBinariADecimal1() {
        assertEquals("5", Interpret.callFunction("=BIN2DEC(0b101)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció DEC2HEX i es passa un dígit com a valor.
     * Seguidament es comprova que la funció és calculada i que es passa el dígit del paràmetre a Hexadecimal, i es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionDecimalAHexadecimal1() {
        assertEquals("0x37", Interpret.callFunction("=DEC2HEX(55)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció HEX2DEC i es passa un dígit en hexadecimal com a valor.
     * Seguidament es comprova que la funció és calculada i que es passa el dígit en hexadecimal del paràmetre a decimal, i es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionHexadecimalADecimal1() {
        assertEquals("155", Interpret.callFunction("=HEX2DEC(0x9B)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció ABS i es passa un dígit negatiu com a valor.
     * Seguidament es comprova que la funció és calculada i que es passa el dígit del paràmetre a positiu, i es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionAbsolut1() {
        assertEquals("3.0", Interpret.callFunction("=ABS(-3)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció POTENCIA i es passa un dígit que és la base i un altre que és l'exponent.
     * Seguidament es comprova que la funció és calculada i que es calcula la potència a partir de la base i l'exponent que es passen per paràmetre, i es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionPotencia1() {
        assertEquals("4.0", Interpret.callFunction("=POTENCIA(2,2)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció ARREL i es passa un dígit que és la base i un altre que és el grau de l'arrel.
     * Seguidament es comprova que la funció és calculada i que es calcula l'arrel a partir de la base i el grau de l'arrel que es passen per paràmetre, i es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionArrel1() {
        assertEquals("4.0", Interpret.callFunction("=ARREL(16,2)"));
    }
    /*
    @Test
    public void callFunctionArrel2() {
        assertEquals("7", Interpret.callFunction("=ARREL(AB3,B2)"));
    }*/

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció FACTORIAL i es passa un dígit en integer per paràmetre.
     * Seguidament es comprova que la funció és calculada i que es calcula el factorial del valor que es passa per paràmetre, i es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionFactorial1() {
        assertEquals("40320", Interpret.callFunction("=FACTORIAL(8)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció OBTEDIA i es passa un string representant una data en format dd/MM/yyyy per paràmetre.
     * Seguidament es comprova que la funció és calculada parsejant el string del paràmetre a LocalDate i en retorna el dia. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionObteDia1() {
        assertEquals("11", Interpret.callFunction("=OBTEDIA(11/04/2022)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció OBTEMES i es passa un string representant una data en format dd/MM/yyyy per paràmetre.
     * Seguidament es comprova que la funció és calculada parsejant el string del paràmetre a LocalDate i en retorna el número del mes. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionObteMes1() {
        assertEquals("4", Interpret.callFunction("=OBTEMES(11/04/2022)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció OBTEANY i es passa un string representant una data en format dd/MM/yyyy per paràmetre.
     * Seguidament es comprova que la funció és calculada parsejant el string del paràmetre a LocalDate i en retorna l'any. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionObteAny1() {
        assertEquals("2022", Interpret.callFunction("=OBTEANY(11/04/2022)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció OBTEDIASETMANA i es passa un string representant una data en format dd/MM/yyyy per paràmetre.
     * Seguidament es comprova que la funció és calculada parsejant el string del paràmetre a LocalDate i en retorna el nom del dia de la setmana en dues lletres: dl, dm, dc, dj, dv, ds, dg.
     * Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionObteDiaSetmana1() {
        assertEquals("dl", Interpret.callFunction("=OBTEDIASETMANA(11/04/2022)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció MAJUS i es passa un string per paràmetre.
     * Seguidament es comprova que la funció és calculada i passa tots els caràcters del string a majúscules. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionAMajuscula1() {
        assertEquals("CHICAGO", Interpret.callFunction("=MAJUS(chicago)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció REEMPLACA i es passa un string d'entrada, un string amb el substring es vol substituir de l'entrada i un string amb el qual es vol substituir.
     * Seguidament es comprova que la funció és calculada i reemplaça el substring del string entrada amb el string del 3r paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionReemplaca1() {
        assertEquals("hola mon", Interpret.callFunction("=REEMPLACA(hola lluna,lluna,mon)"));
    }
    /*
    @Test
    public void callFunctionReemplaca3() {
        assertEquals("hola mon", Interpret.callFunction("=REEMPLACA(B3,ZZ34,AP2)"));
    }*/

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció REEMPLACA i es passa un string d'entrada amb més de dues paraules, un string amb el substring del que es vol substituir de l'entrada i un string amb el qual es vol substituir.
     * Seguidament es comprova que la funció és calculada i reemplaça el substring del string entrada amb el string del 3r paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionReemplaca2() {
        assertEquals("hola bona nit", Interpret.callFunction("=REEMPLACA(hola bona tarda,tarda,nit)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció TAMANY i es passa un string per paràmetre.
     * Seguidament es comprova que la funció és calculada i es retorna un dígit que representa el número de caràcters que té el string passat per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionTamany1() {
        assertEquals("7", Interpret.callFunction("=TAMANY(chicago)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String amb l'operació que es vol calcular i amb quins valors.
     * Operativa: En aquest test es comprova que el String es tracta de la funció NUMPARAULES i es passa un string per paràmetre.
     * Seguidament es comprova que la funció és calculada i es retorna un dígit que representa el número de substrings separat per espais (paraules) hi ha al string passat per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionNumeroParaules1() {
        assertEquals("3", Interpret.callFunction("=NUMPARAULES(chicago is great)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció RESTA i es passen dos dígits per paràmetre.
     * Seguidament es comprova que la funció és calculada i es retorna el valor de restar el primer dígit pel segon dels que s'han passat per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionResta1() {
        assertEquals("6.2", Interpret.callFunction("=RESTA(51.2,45)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció DIVISIÓ i es passen dos dígits per paràmetre.
     * Seguidament es comprova que la funció és calculada i es retorna el valor de dividir el primer dígit entre segon dels que s'han passat per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionDivisio1() {
        assertEquals("2.5", Interpret.callFunction("=DIVISIO(5,2)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada una cadena de dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció SUMA i una cadena de dígits com a paràmetres (mínim dos).
     * Seguidament es comprova que la funció és calculada i es retorna el valor de sumar tots els dígits passats per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionSuma1() {
        assertEquals("128.5", Interpret.callFunction("=SUMA(51.2,45,30,2.3)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada una cadena de dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció VARIANCIA i una cadena de dígits com a paràmetres (mínim dos).
     * Seguidament es comprova que la funció és calculada i es retorna el valor de realitzar la variància de tots els dígits passats per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionVariancia1() {
        assertEquals("21704.0", Interpret.callFunction("=VARIANCIA(600,470,170,430,300)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada una cadena de dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció DESVEST i una cadena de dígits com a paràmetres (mínim dos).
     * Seguidament es comprova que la funció és calculada i es retorna el valor de realitzar la desviació estándar de tots els dígits passats per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionDesviacioEstandar1() {
        assertEquals("147.32277488562318", Interpret.callFunction("=DESVEST(600,470,170,430,300)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada una cadena de dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció MULTIPLICACIO i una cadena de dígits com a paràmetres (mínim dos).
     * Seguidament es comprova que la funció és calculada i es retorna el valor de multiplicar tots els dígits passats per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionMultiplicacio1() {
        assertEquals("158976.0", Interpret.callFunction("=MULTIPLICACIO(51.2,45,30,2.3)"));
    }
    /*
    @Test
    public void callFunctionNumeroMitjana2() {
        assertEquals("3.25", Interpret.callFunction("=MITJANA(A13:AA13)"));
    }*/

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada una cadena de dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció MITJANA i una cadena de dígits com a paràmetres (mínim dos).
     * Seguidament es comprova que la funció és calculada i es retorna el valor de fer la mitjana de tots els dígits passats per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionMitjana1() {
        assertEquals("3.25", Interpret.callFunction("=MITJANA(2,3,4,4)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada una cadena de dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció MEDIANA i una cadena de dígits com a paràmetres (mínim dos).
     * Seguidament es comprova que la funció és calculada i es retorna el valor de fer la mediana de tots els dígits passats per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionMediana1() {
        assertEquals("12.5", Interpret.callFunction("=MEDIANA(9,10,12,13,13,15)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dues cadenes de dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció PEARSON i dues cadenes de dígits com a paràmetres (amb mínim dos dígits cada cadena).
     * Seguidament es comprova que la funció és calculada i es retorna el valor de fer la correlació de Pearson de tots els dígits de les cadenes passades per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionCorrelacioPearson1() {
        assertEquals("0.8871231686530718", Interpret.callFunction("=PEARSON(32,6,64,256,512;1024,634532,344234,32312,34232434232)"));
    }
    /*
    @Test
    public void callFunctionCorrelacioPearson2() {
        assertEquals("3.25", Interpret.callFunction("=PEARSON(A13:D13;A14:B17)"));
    }*/

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dues cadenes de dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció COVARIANCIA i dues cadenes de dígits com a paràmetres (amb mínim dos dígits cada cadena).
     * Seguidament es comprova que la funció és calculada i es retorna el valor de fer la covariància de tots els dígits de les cadenes passades per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionCorrelacioCovariancia1() {
        assertEquals("187.75", Interpret.callFunction("=COVARIANCIA(5,20,40,80,100;10,24,33,54,10)"));
    }

    /**
     * Objecte de la prova: Test del mètode callFunction de la classe Interpret
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dues cadenes de dígits.
     * Operativa: En aquest test es comprova que el String es tracta de la funció COVARIANCIA i dues cadenes de dígits com a paràmetres (amb mínim dos dígits cada cadena). Es comproven resultats amb molts decimals
     * Seguidament es comprova que la funció és calculada i es retorna el valor de fer la covariància de tots els dígits de les cadenes passades per paràmetre. Per últim, es comprova que el valor és el correcte.
     */
    @Test
    public void callFunctionCorrelacioCovariancia2() {
        assertEquals("-0.05805", Interpret.callFunction("=COVARIANCIA(65.21,64.75,65.26,65.76,65.96;67.25,66.39,66.12,65.70,66.64)"));
    }
}
