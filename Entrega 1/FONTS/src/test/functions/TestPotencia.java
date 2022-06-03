package test.functions;

import main.domain.classes.functions.Potencia;

import static org.junit.Assert.*;
import org.junit.Test;

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
        Potencia pot = new Potencia();
        pot.setValue2D(2, 2);
        assertEquals(4.0, pot.potencia(), 0);
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
        Potencia pot = new Potencia();
        pot.setValue2D(2, 100);
        assertEquals(1267650600228229400000000000000.0, pot.potencia(), 0.0);
    }
    
}
