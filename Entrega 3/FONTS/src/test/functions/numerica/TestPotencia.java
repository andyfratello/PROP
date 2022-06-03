package test.functions.numerica;

import main.domain.classes.functions.numerica.Potencia;

import static org.junit.Assert.*;

import main.domain.classes.token.NumberT;
import org.junit.Test;

import java.util.List;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestPotencia {
    /**
     * Objecte de la prova: Test del mètode potencia() de la classe Potencia
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir d'un dígit que representa la base i un que representa
     * l'exponent, es calcula correctament l'operació potencia. Primer es fa un setter dels paràmetres en Double i
     * seguidament es calcula l'operació de Potencia.
     */
    @Test
    public void potencia() {
        Potencia pot = new Potencia(List.of(new NumberT(2), new NumberT(2)));
        assertEquals(4, pot.GetResultat().asNumber().intValue(), 0);
    }

    /**
     * Objecte de la prova: Test del mètode potencia() de la classe Potencia
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada dos Doubles.
     * Operativa: En aquest test es comprova que a partir d'un dígit que representa la base i un que representa
     * un exponent relativament gran, es calcula correctament l'operació potencia. Primer es fa un setter dels
     * paràmetres en Double i seguidament es calcula l'operació de Potencia.
     */
    @Test
    public void potenciaGran() {
        Potencia pot = new Potencia(List.of(new NumberT(2), new NumberT(100)));
        assertEquals(1267650600228229400000000000000.0, pot.GetResultat().asNumber().doubleValue(), 0);
    }
    
}
