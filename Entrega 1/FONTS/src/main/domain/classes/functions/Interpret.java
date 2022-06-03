package main.domain.classes.functions;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import main.domain.classes.types.TType;
import main.domain.classes.types.Binari;
import main.domain.classes.types.Hexadecimal;

import main.domain.controllers.CtrlDomini;

import main.domain.classes.exceptions.NegativeRootException;
import main.domain.classes.exceptions.NegativeFactorialException;

/**
 * Aquesta classe fa d'analitzador sintàctic de l'input d'una cel·la, en determina el seu tipus i transforma
 * els tipus String a Double, Integer, Data o Funció. Si l'input d'una cel·la és una referència al contingut d'una
 * altra, l'Interpret s'encarrega d'obtenir aquest valor. També s'encarrega de gestionar totes les funcions i
 * retornar-ne el resultat.
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class Interpret {
    private static Interpret INSTANCE = new Interpret();
    private static CtrlDomini ctrlDomini;

    /**
     * Constructora (privada ja que és un singleton)
     */
    private Interpret() {
    }

    /**
     * Getter de la instància de Interpret
     * @return INSTANCE
     */
    public static Interpret getInstance() {
        return INSTANCE;
    }

    /**
     * Setter del controlador de domini
     * @param cd
     */
    public static void setCtrlDomini(CtrlDomini cd) {
        ctrlDomini = cd;
    }

    /**
     * Retorna true si String x pot ser parsejat a Integer, false en cas contrari
     * @param x
     * @return Boolean
     */
    private static boolean esInteger(String x) {
        int lngt = x.length();
        if (x == null) return false;
        if (lngt == 0) return false;
        for (int i = 0; i < lngt; ++i) {
            if (x.charAt(i) < '0' || x.charAt(i) > '9') return false;
        }
        return true;
    }

    /**
     * Retorna true si String x pot ser parsejat a Double, false en cas contrari
     * @param x
     * @return Boolean
     */
    private static boolean esDouble(String x) {
        try {
            Double doub = Double.parseDouble(x);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * Retorna true si String x pot ser parsejat a Date, false en cas contrari
     * @param x
     * @return Boolean
     */
    private static boolean esDate(String x) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(x, formatter);
        }
        catch (DateTimeParseException exc) {
            return false;
        }
        return true;
    }

    /**
     * Retorna true si String x és considerat funció, false en cas contrari
     * @param x
     * @return Boolean
     */
    private static boolean esFunction(String x) {
        if (x.charAt(0) == '=') return true;
        return false;
    }

    /**
     * Retorna el tipus de l'input de l'usuari a determinada cel·la
     * @param x
     * @return TType
     */
    public static TType getType(String x) {
        if (esInteger(x)) return TType.INT;
        if (esDouble(x)) return TType.DOUBLE;
        if (esDate(x)) return TType.DATE;
        if (esFunction(x)) return TType.FUNCTION;
        return TType.STRING;
    }

    /**
     * D'un input de cadena de caràcters, el retorna com a Double
     * @param x
     * @return Double
     */
    public static double getDouble(String x) {
        if (getType(x) == TType.DOUBLE) return Double.parseDouble(x);
        return -1;
    }

    /**
     * D'un input de cadena de caràcters, el retorna com a Int
     * @param x
     * @return Int
     */
    public static int getInt(String x) {
        if (getType(x) == TType.INT) return Integer.parseInt(x);
        return -1;
    }

    /**
     * D'un input de cadena de caràcters, el retorna com a Date (ex January 11 2004)
     * @param x
     * @return Date
     */
    public static LocalDate getData(String x) {
        if (getType(x) == TType.DATE) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(x, formatter);
            return date;
        }
        return LocalDate.now();
    }

    /**
     * Retorna l'únic paràmetre de la funció en format String
     * @param x
     * @return String
     */
    private static String getParamUnic(String x) {
        return x.substring(x.indexOf("(") + 1, x.indexOf(")"));
    }

    /**
     * Retorna el primer paràmetre de la funció en format String
     * @param x
     * @return String
     */
    private static String getParamPrimer(String x) {
        return x.substring(x.indexOf("(") + 1, x.indexOf(","));
    }

    /**
     * Retorna el segon paràmetre de la funció en format String
     * @param x
     * @return String
     */
    private static String getParamMig(String x) {
        String a = x.substring(x.indexOf(",") + 1, x.indexOf(")") + 1); // "lluna,mon)"
        String b = a.replaceAll("\\)", ""); // "lluna,mon" -> ens carreguem ) que dóna problemes amb fer replaceAll
        String[] res = b.split(",");
        return res[0];
    }

    /**
     * Retorna l'últim paràmetre de la funció en format String
     * @param x
     * @return String
     */
    private static String getParamUltim(String x) {
        String a = x.substring(x.indexOf(",") + 1, x.indexOf(")") + 1); // " lluna, mon)"
        return a.substring(a.indexOf(",") + 1, a.indexOf(")"));
    }

    /**
     * Retorna les dos cadenes de paràmetres que necessiten les funcions de covariancia i pearson
     * @param x
     * @return Vector<String> de dues posicions
     */
    private static Vector<String> obteCadenaAmbParentesis(String x) {
        Vector<String> res = new Vector<String>();
        // (A1:A12;B1:B12)
        String sub1 = x.substring(x.indexOf("("), x.indexOf(";")); // sub1 = (A1:A12
        String sub2 = x.substring(x.indexOf(";") + 1, x.indexOf(")") + 1); // sub2 = B1:B12)
        res.add(sub1 + ')');
        res.add('(' + sub2);
        return res;
    }

    /**
     * Retorna string en Hexavigesimal (base26 en caràcters) a integer
     * @param String en base26 a caràcters
     * @return Integer
     */
    private static int hexavigesimalToInteger(String x) {
        int res = 0;
        for (int i = 0; i < x.length(); i++) {
            res *= 26;
            res += x.charAt(i) - 'A' + 1;
        }
        --res;
        return res;
    }

    /**
     * Retorna integer a string en Hexavigesimal (base26 en caràcters)
     * @param Integer
     * @return String en base26 a caràcters
     */
    private static String integerToHexavigesimal(int x) {
        x++; // Per a tenir númeració començant des de 0
        StringBuilder columnName = new StringBuilder();
        while (x > 0) {
            int rem = x%26;
            if (rem == 0) {
                columnName.append("Z");
                x = (x/26)-1;
            } else {
                columnName.append((char) ((rem - 1) + 'A'));
                x = x/26;
            }
        }
        return (columnName.reverse()).toString();
    }

    /**
     * Retorna tots els paràmetres en un vector de Double. Si són referències, el valor real de la cel·la es guarda al vector
     * @param x
     * @return Vector<Double>
     */
    private static Vector<Double> getCadenaParametres(String x) {
        String sub = x.substring(x.indexOf("(") + 1, x.indexOf(")"));
        Vector<Double> res = new Vector<Double>();
        // Num. individuals. ex. 23,34,2,12,8,97
        if (Character.isDigit(sub.charAt(0))) {
            String[] paramString = sub.split(",");
            for (String param : paramString) {
                double paramD = 0.0;
                try {
                    paramD = Double.parseDouble(param);
                } catch (Exception e) {
                    //System.out.println("#ERROR1");
                }
                res.add(paramD);
            }
        }
        // Referències. ex. A1:A20 o A11:P11 o A27:G30
        else {
            String[] paramString = sub.split(":");
            String primer = paramString[0];
            String segon = paramString[1];
            // ex. primer = A11; segon = P11
            String primerLl = primer.replaceAll("[^A-Z]", ""); // A
            String segonLl = segon.replaceAll("[^A-Z]", ""); // P
            String primerNum = primer.replaceAll("[^0-9]", ""); // 11
            String segonNum = segon.replaceAll("[^0-9]", ""); // 11
            Vector<String> ref = new Vector<String>();
            // Cel·les d'una mateixa columna
            if (primerLl.equals(segonLl)) {
                //System.out.println("mCol");
                String s = primerLl;
                int first = 0;
                try {
                    first = Integer.parseInt(primerNum);
                } catch (Exception e) {
                    System.out.println("#ERROR");
                }
                int last = 0;
                try {
                    last = Integer.parseInt(segonNum);
                } catch (Exception e) {
                    System.out.println("#ERROR");
                }
                for (int i = 0; i < (last - first + 1); ++i) {
                    ref.add(s + (Integer.toString(first + i)));
                    //System.out.println(s + (Integer.toString(first + i)));
                }
            }
            // Cel·les d'una mateixa fila
            else if (primerNum.equals(segonNum)) {
                //System.out.println("mFila");
                String s = primerNum;
                int first = hexavigesimalToInteger(primerLl);
                int last = hexavigesimalToInteger(segonLl);
                for (int i = 0; i < (last - first + 1); ++i) {
                    ref.add(integerToHexavigesimal(first + i) + s);
                }
            }
            // Cel·les dins d'un mateix bloc
            else {
                //System.out.println("mBloc");
                int firstL = hexavigesimalToInteger(primerLl); // A
                int lastL = hexavigesimalToInteger(segonLl); // G
                int firstN = 0;
                try {
                    firstN = Integer.parseInt(primerNum); // 27
                } catch (Exception e) {
                    System.out.println("#ERROR");
                }
                int lastN = 0;
                try {
                    lastN = Integer.parseInt(segonNum); // 30
                } catch (Exception e) {
                    System.out.println("#ERROR");
                }
                for (int i = 0; i < (lastL - firstL + 1); ++i) {
                    for (int j = 0; j < (lastN - firstN + 1); ++j) {
                        String ll = integerToHexavigesimal(firstL + i);
                        String num = Integer.toString(firstN + j);
                        ref.add(ll + num);
                    }
                }
            }
            //System.out.println(ref);
            for (int i = 0; i < ref.size(); ++i) {
                Double d = 0.0;
                try {
                    d = Double.parseDouble(obteValor(ref.get(i)));
                } catch (Exception e) {
                    System.out.println("#ERROR");
                }
                res.add(d);
            }
        }
        return res;
    }

    /**
     * Retorna true si el paràmetre és una referència a una altra columna (ex. A1, B1, ..., ZZZ99). false en cas contrari
     * @param x
     * @return Boolean
     */
    private static boolean esReferencia(String x) {
        int len = x.length();
        if (x.charAt(0) >= 'A' && x.charAt(0) <= 'Z') {
            // la 1a és lletra i la resta números
            boolean getOut = false;
            for (int i = 1; !getOut && i < len; ++i) {
                if (x.charAt(i) >= '1' && x.charAt(i) <= '9') {
                    if (i == len - 1) return true;
                } else getOut = true; //no és número
            }

            // les 2 primeres són lletres i la resta números
            if (x.charAt(1) >= 'A' && x.charAt(1) <= 'Z') {
                getOut = false;
                for (int i = 2; !getOut && i < len; ++i) {
                    if (x.charAt(i) >= '1' && x.charAt(i) <= '9') {
                        if (i == len - 1) return true;
                    } else getOut = true; //no és número
                }
            }
            // les 3 primeres són lletres i la resta números
            if ((x.charAt(1) >= 'A' && x.charAt(1) <= 'Z') && (x.charAt(2) >= 'A' && x.charAt(2) <= 'Z')) {
                getOut = false;
                for (int i = 3; !getOut && i < len; ++i) {
                    if (x.charAt(i) >= '1' && x.charAt(i) <= '9') {
                        if (i == len - 1) return true;
                    } else getOut = true; //no és número
                }
            }
        }
        return false;
    }

    /**
     * Detecta l'identificador passat per String i retorna el valor d'aquella cel·la
     * @param x
     * @return String
     */
    private static String obteValor(String x) {
        String lletres = x.replaceAll("[^A-Z]", "");
        String numeros = x.replaceAll("[^0-9]", "");
        int col = hexavigesimalToInteger(lletres);
        int fil = Integer.parseInt(numeros) - 1;
        return ctrlDomini.obtenirValorRealCela(col, fil);
    }

    /**
     * Detecta la paraula clau dins de x, crida a la funció corresponent i retorna el resultat en String
     * @param x
     * @return String resultat de l'operació
     */
    public static String callFunction(String x) {
        String res = "";
        // ARRODONIR
        if (x.contains("ARRODONIR")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            double paramD = 0.0;
            try {
                paramD = Double.parseDouble(param);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            Arrodonir a = new Arrodonir();
            a.setValueD(paramD);
            int resI = a.arrodonir();
            res = Integer.toString(resI);
        }
        // TRUNCAR
        if (x.contains("TRUNCAR")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            double paramD = 0.0;
            try {
                paramD = Double.parseDouble(param);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            Truncar t = new Truncar();
            t.setValueD(paramD);
            int resI = t.truncar();
            res = Integer.toString(resI);
        }
        // DEC2BIN
        if (x.contains("DEC2BIN")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            int paramI = 0;
            try {
                paramI = Integer.parseInt(param);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            decimalABinari dAb = new decimalABinari();
            dAb.setValueI(paramI);
            Binari resB = dAb.decimalABinari();
            res = "0b" + resB.getValor();
        }
        // BIN2DEC
        if (x.contains("BIN2DEC")) {
            String param = getParamUnic(x);
            param = param.replaceAll("0b", "");
            if (esReferencia(param)) {
                param = obteValor(param);
                param = param.replaceAll("0b", "");
            }
            Binari bi = new Binari(param);
            binariADecimal bAd = new binariADecimal();
            bAd.setValueB(bi);
            int resI = bAd.binariADecimal();
            res = Integer.toString(resI);
        }
        // DEC2HEX
        if (x.contains("DEC2HEX")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            int paramI = 0;
            try {
                paramI = Integer.parseInt(param);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            decimalAHexadecimal dAh = new decimalAHexadecimal();
            dAh.setValueI(paramI);
            Hexadecimal resH = dAh.decimalAHexadecimal();
            res = "0x" + resH.getValor();
        }
        // HEX2DEC
        if (x.contains("HEX2DEC")) {
            String param = getParamUnic(x);
            param = param.replaceAll("0x", "");
            if (esReferencia(param)) {
                param = obteValor(param);
                param = param.replaceAll("0x", "");
            }
            Hexadecimal he = new Hexadecimal(param);
            hexadecimalADecimal hAd = new hexadecimalADecimal();
            hAd.setValueH(he);
            int resI = hAd.hexadecimalADecimal();
            res = Integer.toString(resI);
        }
        // ABSOLUT
        if (x.contains("ABS")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            double paramD = 0.0;
            try {
                paramD = Double.parseDouble(param);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            Absolut abs = new Absolut();
            abs.setValueD(paramD);
            double resD = abs.absolut();
            res = Double.toString(resD);
        }
        // POTENCIA
        if (x.contains("POTENCIA")) {
            String paramA = getParamPrimer(x);
            String paramB = getParamUltim(x);
            if (esReferencia(paramA)) paramA = obteValor(paramA);
            if (esReferencia(paramB)) paramB = obteValor(paramB);
            double paramDA = 0.0;
            try {
                paramDA = Double.parseDouble(paramA);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            double paramDB = 0.0;
            try {
                paramDB = Double.parseDouble(paramB);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            Potencia pot = new Potencia();
            pot.setValue2D(paramDA, paramDB);
            double resD = pot.potencia();
            res = Double.toString(resD);
        }
        // ARREL
        if (x.contains("ARREL")) {
            String paramA = getParamPrimer(x);
            String paramB = getParamUltim(x);
            if (esReferencia(paramA)) paramA = obteValor(paramA);
            if (esReferencia(paramB)) paramB = obteValor(paramB);
            double paramDA = 0.0;
            try {
                paramDA = Double.parseDouble(paramA);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            double paramDB = 0.0;
            try {
                paramDB = Double.parseDouble(paramB);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            Arrel arr = new Arrel();
            arr.setValue2D(paramDA, paramDB);
            double resD = 0.0;
            try {
                resD = arr.arrel();
            } catch (Exception e) {}
            res = Double.toString(resD);
        }
        // FACTORIAL
        if (x.contains("FACTORIAL")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            int paramI = 0;
            try {
                paramI = Integer.parseInt(param);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            Factorial fac = new Factorial();
            fac.setValueI(paramI);
            int resI = 0;
            try {
               resI = fac.factorial();
            } catch (Exception e) {}
            res = Integer.toString(resI);
        }
        // OBTEDIA
        if (x.contains("OBTEDIA")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate paramD = LocalDate.now();
            try {
                paramD = LocalDate.parse(param, formatter);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            obteDia oD = new obteDia();
            oD.setValueLD(paramD);
            int resI = oD.obteDia();
            res = Integer.toString(resI);
        }
        // OBTEMES
        if (x.contains("OBTEMES")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate paramD = LocalDate.now();
            try {
                paramD = LocalDate.parse(param, formatter);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            obteMes oM = new obteMes();
            oM.setValueLD(paramD);
            int resI = oM.obteMes();
            res = Integer.toString(resI);
        }
        // OBTEANY
        if (x.contains("OBTEANY")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate paramD = LocalDate.now();
            try {
                paramD = LocalDate.parse(param, formatter);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            obteAny oA = new obteAny();
            oA.setValueLD(paramD);
            int resI = oA.obteAny();
            res = Integer.toString(resI);
        }
        // OBTEDIASETMANA
        if (x.contains("OBTEDIASETMANA")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate paramD = LocalDate.now();
            try {
                paramD = LocalDate.parse(param, formatter);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            obteDiaSetmana oDS = new obteDiaSetmana();
            oDS.setValueLD(paramD);
            res = oDS.obteDiaSetmana();
        }
        // MAJUS
        if (x.contains("MAJUS")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            aMajuscula aM = new aMajuscula();
            aM.setValueS(param);
            res = aM.aMajuscula();
        }
        // REEMPLACA
        if (x.contains("REEMPLACA")) {
            String paramA = getParamPrimer(x);
            //System.out.println(paramA);
            String paramB = getParamMig(x);
            //System.out.println(paramB);
            String paramC = getParamUltim(x);
            //System.out.println(paramC);

            if (esReferencia(paramA)) paramA = obteValor(paramA);
            if (esReferencia(paramB)) paramB = obteValor(paramB);
            if (esReferencia(paramC)) paramC = obteValor(paramC);

            Reemplaca re = new Reemplaca();
            re.setValueSSS(paramA, paramB, paramC);
            res = re.reemplaca();
        }
        // TAMANY
        if (x.contains("TAMANY")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            tamanyText tt = new tamanyText();
            tt.setValueS(param);
            int resI = tt.tamanyText();
            res = Integer.toString(resI);
        }
        // NUMPARAULES
        if (x.contains("NUMPARAULES")) {
            String param = getParamUnic(x);
            if (esReferencia(param)) param = obteValor(param);
            numeroParaules nP = new numeroParaules();
            nP.setValueS(param);
            int resI = nP.numeroParaules();
            res = Integer.toString(resI);
        }

        // SUMA
        if (x.contains("SUMA")) {
            Vector<Double> params = getCadenaParametres(x);
            Suma s = new Suma();
            s.setValueV(params);
            double resD = s.suma();
            res = Double.toString(resD);
        }
        // RESTA
        if (x.contains("RESTA")) {
            String paramA = getParamPrimer(x);
            String paramB = getParamUltim(x);
            if (esReferencia(paramA)) paramA = obteValor(paramA);
            if (esReferencia(paramB)) paramB = obteValor(paramB);
            double paramDA = 0.0;
            try {
                paramDA = Double.parseDouble(paramA);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            double paramDB = 0.0;
            try {
                paramDB = Double.parseDouble(paramB);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            Resta r = new Resta();
            r.setValue2D(paramDA, paramDB);
            double resD = r.resta();
            res = Double.toString(resD);
        }
        // MULTIPLICACIÓ
        if (x.contains("MULTIPLICACIO")) {
            Vector<Double> params = getCadenaParametres(x);
            Multiplicacio m = new Multiplicacio();
            m.setValueV(params);
            double resD = m.multiplicacio();
            res = Double.toString(resD);
        }
        // DIVISIÓ
        if (x.contains("DIVISIO")) {
            String paramA = getParamPrimer(x);
            String paramB = getParamUltim(x);
            if (esReferencia(paramA)) paramA = obteValor(paramA);
            if (esReferencia(paramB)) paramB = obteValor(paramB);
            double paramDA = 0.0;
            try {
                paramDA = Double.parseDouble(paramA);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            double paramDB = 0.0;
            try {
                paramDB = Double.parseDouble(paramB);
            } catch (Exception e) {
                System.out.println("#ERROR");
            }
            Divisio d = new Divisio();
            d.setValue2D(paramDA, paramDB);
            double resD = d.divisio();
            res = Double.toString(resD);
        }
        // MITJANA
        if (x.contains("MITJANA")) {
            Vector<Double> params = getCadenaParametres(x);
            Mitjana mi = new Mitjana();
            mi.setValueV(params);
            double resD = mi.mitjana();
            res = Double.toString(resD);
        }
        // MEDIANA
        if (x.contains("MEDIANA")) {
            Vector<Double> params = getCadenaParametres(x);
            Mediana me = new Mediana();
            me.setValueV(params);
            double resD = me.mediana();
            res = Double.toString(resD);
        }
        // VARIANCIA
        if (x.contains("VARIANCIA")) {
            Vector<Double> params = getCadenaParametres(x);
            Variancia va = new Variancia();
            va.setValueV(params);
            double resD = va.variancia();
            res = Double.toString(resD);
        }

        // DESVIACIÓ ESTÀNDAR
        if (x.contains("DESVEST")) {
            Vector<Double> params = getCadenaParametres(x);
            DesviacioEstandar de = new DesviacioEstandar();
            de.setValueV(params);
            double resD = de.desviacioEstandar();
            res = Double.toString(resD);
        }

        // CORRELACIÓ DE PEARSON
        if (x.contains("PEARSON")) {
            Vector<String> s = obteCadenaAmbParentesis(x);
            Vector<Double> params1 = getCadenaParametres(s.get(0));
            Vector<Double> params2 = getCadenaParametres(s.get(1));
            CorrelacioPearson cp = new CorrelacioPearson();
            cp.setValue2V(params1, params2);
            double resD = cp.correlacioPearson();
            res = Double.toString(resD);
        }
        // COVARIANCIA
        if (x.contains("COVARIANCIA")) {
            Vector<String> s = obteCadenaAmbParentesis(x);
            Vector<Double> params1 = getCadenaParametres(s.get(0));
            Vector<Double> params2 = getCadenaParametres(s.get(1));
            if (params1.size() != params2.size()) throw new ArithmeticException();
            Covariancia co = new Covariancia();
            co.setValue2V(params1, params2);
            double resD = co.covariancia();
            res = Double.toString(resD);
        }

        return res;
    }
}