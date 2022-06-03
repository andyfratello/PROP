package test.functions.string;

import main.domain.classes.functions.string.Reemplaca;

import static org.junit.Assert.*;

import main.domain.classes.token.StringT;
import org.junit.Test;

import java.util.List;

/**
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TestReemplaca {
    /**
     * Objecte de la prova: Test del mètode reemplaca() de la classe Reemplaca
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es passa per entrada tres Strings.
     * Operativa: En aquest test es comprova que amb un String d'entrada, un String que representa un substring de
     * l'entrada que es vol reemplaçar i un String amb el qual es vol reemplaçar aquest substring. Primer es fa un
     * setter dels tres Strings passats per paràmetre i seguidament es calcula l'operació de Reemplaca.
     */
    @Test
    public void reemplaca() {
        Reemplaca ree = new Reemplaca(List.of(new StringT("Hola UPC"), new StringT("UPC"),new StringT("mon")));
        assertEquals("Hola mon", ree.GetResultat().asString());
    }
}
