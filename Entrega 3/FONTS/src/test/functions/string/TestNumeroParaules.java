package test.functions.string;

import main.domain.classes.functions.string.numeroParaules;

import static org.junit.Assert.*;

import main.domain.classes.token.StringT;
import org.junit.Test;

/**
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class TestNumeroParaules {
    /**
     * Objecte de la prova: Test del mètode numeroParaules() de la classe numeroParaules
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String.
     * Operativa: En aquest test es comprova quantes paraules (substrings separats per un espai) hi ha a un String.
     * Primer es fa un setter del paràmetre en String i seguidament es calcula l'operació d'aMajuscula i se'n comprova
     * el correcte funcionament.
     */
    @Test
    public void numeroParaules() {
        numeroParaules np = new numeroParaules(new StringT("L'apostrof forma part de la paraula"));
        assertEquals(6, np.GetResultat().asNumber().intValue());
    }
}
