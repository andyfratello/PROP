package test.functions.string;

import main.domain.classes.functions.string.tamanyText;

import static org.junit.Assert.*;

import main.domain.classes.token.StringT;
import org.junit.Test;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestTamanyText {
    /**
     * Objecte de la prova: Test del mètode tamanyText() de la classe tamanyText.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada un String.
     * Operativa: En aquest test es comprova el número de caràcters que conté el String passat per paràmetre. Primer es
     * fa un setter del paràmetre en String i seguidament es calcula l'operació de tamanyText i se'n comprova el
     * correcte funcionament.
     */
    @Test
    public void tamanyText() {
        tamanyText tt = new tamanyText(new StringT("Aqui hi han 25 caracters!"));
        assertEquals(25, tt.GetResultat().asNumber().intValue());
    }
}
