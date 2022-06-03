package test.functions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import test.functions.data.TestObteAny;
import test.functions.data.TestObteDia;
import test.functions.data.TestObteDiaSetmana;
import test.functions.data.TestObteMes;
import test.functions.numerica.*;
import test.functions.string.TestAMajuscula;
import test.functions.string.TestNumeroParaules;
import test.functions.string.TestReemplaca;
import test.functions.string.TestTamanyText;


@RunWith(value = Suite.class)
// Afegeix al array <nomtest>.class de la classe que vols que es comprovi al fer # make fulltest
@SuiteClasses(value = { 
    TestAMajuscula.class, TestBinariADecimal.class,TestDecimalAHexadecimal.class,TestHexadecimalADecimal.class, TestNumeroParaules.class, TestObteMes.class,
    TestAbsolut.class, TestCorrelacioPearson.class,TestDivisio.class,TestMediana.class, TestObteAny.class,TestPotencia.class,TestSuma.class,
    TestArrel.class,TestCovarianca.class,TestMitjana.class, TestObteDia.class, TestReemplaca.class, TestTamanyText.class,
    TestArrodonir.class,TestDecimalABinari.class,TestFactorial.class,TestMultiplicacio.class, TestObteDiaSetmana.class,TestResta.class,TestTruncar.class,TestVariancia.class, TestDesviacioEstandard.class,
    TestInterpret.class,
})
public class TestSuiteFuncions {}
