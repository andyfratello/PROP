package test.functions;

import main.domain.classes.functions.Divisio;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestDivisio {
    /**
     * Objecte de la prova: Test del mètode divisio() de la classe Divisio.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir de dos Doubles positius on el valor de la divisió no és
     * exacte, es calcula correctament l'operació. Primer es fa un setter dels paràmetres en Double i seguidament es
     * calcula l'operació de Divisio.
     */
    @Test
    public void divisioPosPosPeriod() {
        Divisio div = new Divisio();
        div.setValue2D(1548, 27);
        assertEquals(57.3333333333333333333333333333333333333333333333333333333333333333333333333333, div.divisio(), 0.0);
    }

    /**
     * Objecte de la prova: Test del mètode divisio() de la classe Divisio.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir d'un Double positiu com a dividend i un Double negatiu com a
     * divisor on el valor de la divisió no és exacte, es calcula correctament l'operació. Primer es fa un setter dels
     * paràmetres en Double i seguidament es calcula l'operació de Divisio.
     */
    @Test
    public void divisioPosNegPeriod() {
        Divisio div = new Divisio();
        div.setValue2D(16784, -47);
        assertEquals(-357.1063829787234042553191489361702127765957446808510638297872340425531914893617021277659574468085, div.divisio(), 0.0);
    }

    /**
     * Objecte de la prova: Test del mètode divisio() de la classe Divisio.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir d'un Double negatiu com a dividend i un Double positiu com a
     * divisor on el valor de la divisió no és exacte, es calcula correctament l'operació. Primer es fa un setter dels
     * paràmetres en Double i seguidament es calcula l'operació de Divisio.
     */
    @Test
    public void divisioNegPosPeriod() {
        Divisio div = new Divisio();
        div.setValue2D(-154, 6);
        assertEquals(-25.6666666666666666666666666666666666666666666666666666666666666666666666666666, div.divisio(), 0.0);
    }

    /**
     * Objecte de la prova: Test del mètode divisio() de la classe Divisio.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir d'un Double negatiu com a dividend i un Double negatiu com a
     * divisor on el valor de la divisió no és exacte, es calcula correctament l'operació. Primer es fa un setter dels
     * paràmetres en Double i seguidament es calcula l'operació de Divisio.
     */
    @Test
    public void divisioNegNegPeriod() {
        Divisio div = new Divisio();
        div.setValue2D(-19757, -468);
        assertEquals(42.21581196581196581196581196581196581196581196581196581196581196581196581196581196581196581196581196, div.divisio(), 0.0);
    }

    /**
     * Objecte de la prova: Test del mètode divisio() de la classe Divisio.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir de dos Doubles positius on el valor de la divisió és exacte,
     * es calcula correctament l'operació. Primer es fa un setter dels paràmetres en Double i seguidament es calcula
     * l'operació de Divisio.
     */
    @Test
    public void divisioExacte() {
        Divisio div = new Divisio();
        div.setValue2D(845, 5);
        assertEquals(169.0, div.divisio(), 0.0);
    }
    
}
