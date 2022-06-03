package test.functions;

import main.domain.classes.functions.aMajuscula;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestAMajuscula {
    /**
     * Objecte de la prova: Test del mètode aMajuscula() de la classe aMajuscula
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String.
     * Operativa: En aquest test es comprova que amb un String on els caràcters estan barrejats entre majúscules i
     * minúscules, l'operació aMajuscula es calcula correctament i tots els caràcters es passen a majúscules. Primer es
     * fa un setter del paràmetre en String i seguidament es calcula l'operació d'aMajuscula.
     */
    @Test
    public void aMajusculaMix() {
        aMajuscula am = new aMajuscula();
        am.setValueS("aMaJuSCUla");
        assertEquals("AMAJUSCULA", am.aMajuscula());
    }

    /**
     * Objecte de la prova: Test del mètode aMajuscula() de la classe aMajuscula
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String.
     * Operativa: En aquest test es comprova que amb un String on tots els caràcters estan en minúscules, l'operació
     * aMajuscula es calcula correctament i tots els caràcters es passen a majúscules. Primer es fa un setter del
     * paràmetre en String i seguidament es calcula l'operació d'aMajuscula.
     */
    @Test
    public void aMajusculaMin() {
        aMajuscula am = new aMajuscula();
        am.setValueS("amajuscula");
        assertEquals("AMAJUSCULA", am.aMajuscula());
    }

    /**
     * Objecte de la prova: Test del mètode aMajuscula() de la classe aMajuscula
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String.
     * Operativa: En aquest test es comprova que amb un String on tots els caràcters estan en majúscules, l'operació
     * aMajuscula es calcula correctament i tots els caràcters es passen a majúscules. Primer es fa un setter del
     * paràmetre en String i seguidament es calcula l'operació d'aMajuscula.
     */
    @Test
    public void aMajusculaMaj() {
        aMajuscula am = new aMajuscula();
        am.setValueS("AMAJUSCULA");
        assertEquals("AMAJUSCULA", am.aMajuscula());
    }
}
