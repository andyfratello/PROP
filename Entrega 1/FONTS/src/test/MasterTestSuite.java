package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.types.TestSuiteTipus;
import test.functions.TestSuiteFuncions;


@RunWith(value = Suite.class)
// Afegeix al array <nomtest>.class de la classe que vols que es comprovi al fer # make fulltest
@SuiteClasses(value = { 
    TestCela.class, TestDocument.class, TestFull.class, TestSuiteTipus.class, TestSuiteFuncions.class
})
public class MasterTestSuite {}
