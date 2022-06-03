package test.types;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(value = Suite.class)
// Afegeix al array <nomtest>.class de la classe que vols que es comprovi al fer # make fulltest
@SuiteClasses(value = { 
    TestBinari.class, TestHexadecimal.class, TestPair.class
})
public class TestSuiteTipus {}
