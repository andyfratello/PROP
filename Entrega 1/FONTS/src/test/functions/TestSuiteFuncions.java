package test.functions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(value = Suite.class)
// Afegeix al array <nomtest>.class de la classe que vols que es comprovi al fer # make fulltest
@SuiteClasses(value = { 
    TestAMajuscula.class,TestBinariADecimal.class,TestDecimalAHexadecimal.class,TestHexadecimalADecimal.class,TestNumeroParaules.class,TestObteMes.class,
    TestAbsolut.class,TestCorrelacioPearson.class,TestDivisio.class,TestMediana.class,TestObteAny.class,TestPotencia.class,TestSuma.class,
    TestArrel.class,TestCovarianca.class,TestDoubleAInt.class,TestMitjana.class,TestObteDia.class,TestReemplaca.class,TestTamanyText.class,
    TestArrodonir.class,TestDecimalABinari.class,TestFactorial.class,TestMultiplicacio.class,TestObteDiaSetmana.class,TestResta.class,TestTruncar.class,TestVariancia.class,TestDesviacioEstandar.class,
    TestInterpret.class,
})
public class TestSuiteFuncions {}
