CLASS_INPUT = ./test/*.java \
./test/types/*.java \
./test/functions/stubs/*.java \
./test/functions/numerica/*.java  \
./test/functions/*.java  \
./test/functions/string/*.java  \
./test/functions/data/*.java  \
./test/*.java  \
./drivers/*.java  \
./main/domain/controllers/*.java  \
./main/domain/classes/*.java  \
./main/domain/classes/exceptions/*.java  \
./main/domain/classes/types/*.java  \
./main/domain/classes/functions/numerica/*.java  \
./main/domain/classes/functions/*.java \
./main/domain/classes/functions/string/*.java  \
./main/domain/classes/functions/data/*.java  \
./main/domain/classes/config/*.java  \
./main/domain/classes/token/*.java  \
./main/presentation/*.java \
./main/presentation/controllers/*.java \
./main/presentation/views/*.java \
./main/persistence/controllers/*.java \
./main/persistence/classes/csv/*.java \
./main/persistence/classes/config/*.java \
./main/persistence/classes/document/*.java \


CLASS_INPUT_PRESENTACIO =	./main/presentation/controllers/*.java \
							./main/presentation/views/*.java \
							./main/presentation/*.java \
							./main/domain/classes/functions/*.java \
							./main/domain/classes/exceptions/*.java \
							./main/domain/classes/types/*.java \
							./main/domain/classes/token/*.java \
							./main/domain/controllers/*.java

CLASS_OUTPUT =	../../EXE/out/

JAR_OUTPUT =	../../EXE/

JUNIT_JARS =	../../lib/junit-4.12.jar:../../lib/hamcrest-core-1.3.jar

JUNIT_TESTS = 	./test/*.java \
		 ./test/types/*.java \
		 ./test/functions/*.java

INTERPRET_TESTS =	./test/functions/TestInterpret.java

FUNCIONS_TESTS =	./test/functions/*.java

TYPE_TESTS = 		./test/types/TestSuiteTipus.java \
			 ./test/types/TestBinari.java \
			 ./test/types/TestHexadecimal.java

INTERPRET_CLASS_INPUT =	./main/domain/classes/functions/*.java \
						./main/domain/classes/types/Binari.java \
						./main/domain/classes/types/Hexadecimal.java \
						./main/domain/classes/types/TType.java \
						./main/domain/classes/types/Pair.java \
						./main/domain/classes/exceptions/*.java \
						./main/domain/controllers/CtrlDomini.java \
						./main/domain/controllers/CtrlDocument.java \
						./main/domain/controllers/CtrlClipboard.java

TYPE_CLASS_INPUT =	./main/domain/classes/types/Binari.java \
			 ./main/domain/classes/types/Hexadecimal.java

DOCUMENT_CLASS_INPUT =	./main/domain/controllers/CtrlDocument.java \
			 ./main/domain/classes/Document.java \
			 ./main/domain/classes/Full.java \
			 ./main/domain/classes/Cela.java

CLASS_SOURCES =		$(CLASS_OUTPUT)drivers/*.class \
			$(CLASS_OUTPUT)main/domain/classes/*.class \
			$(CLASS_OUTPUT)main/domain/classes/exceptions/*.class \
			$(CLASS_OUTPUT)main/domain/classes/functions/*.class \
			$(CLASS_OUTPUT)main/domain/classes/types/*.class \
			$(CLASS_OUTPUT)main/domain/controllers/*.class

all:
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT)

executable:
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	jar cmvf ./drivers/Presentacio.mf $(JAR_OUTPUT)PROP1-1.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)PROP1-1.jar

executa:
	java -jar $(JAR_OUTPUT)PROP1-1.jar

jarDocument:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverDocument.java
	jar cmvf ./drivers/Document.mf $(JAR_OUTPUT)DriverDocument.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverDocument.jar

jarClipboard:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverClipboard.java
	jar cmvf ./drivers/Clipboard.mf $(JAR_OUTPUT)DriverClipboard.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverClipboard.jar

jarCelaSeleccionada:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverCelaSeleccionada.java
	jar cmvf ./drivers/CelaSeleccionada.mf $(JAR_OUTPUT)DriverCelaSeleccionada.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverCelaSeleccionada.jar

jarDomini:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverDomini.java
	jar cmvf ./drivers/Domini.mf $(JAR_OUTPUT)DriverDomini.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverDomini.jar

jarPresentacio:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT_PRESENTACIO) ./drivers/DriverPresentacio.java
	jar cmvf ./drivers/Presentacio.mf $(JAR_OUTPUT)DriverPresentacio.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverPresentacio.jar

jarFuncio:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverCtrlFuncio.java
	jar cmvf ./drivers/DriverCtrlFuncio.mf $(JAR_OUTPUT)DriverCtrlFuncions.jar -C $(CLASS_OUTPUT) .
	java -jar $(JAR_OUTPUT)DriverCtrlFuncions.jar
# 	
# 	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverCtrlFuncio.java
# 	jar cmvf ./drivers/DriverCtrlFuncions.mf $(JAR_OUTPUT)DriverCtrlFuncions.jar -C $(CLASS_OUTPUT) .
# 	java -jar $(JAR_OUTPUT)DriverCtrlFuncions.jar

	
	
jars: 
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./drivers/DriverDocument.java ./drivers/DriverClipboard.java ./drivers/DriverDomini.java ./drivers/DriverCelaSeleccionada.java
	jar cmvf ./drivers/Document.mf $(JAR_OUTPUT)DriverDocument.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./drivers/Clipboard.mf $(JAR_OUTPUT)DriverClipboard.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./drivers/Domini.mf $(JAR_OUTPUT)DriverDomini.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./drivers/CelaSeleccionada.mf $(JAR_OUTPUT)DriverCelaSeleccionada.jar -C $(CLASS_OUTPUT) .

executaDriverDomini:
	java -jar $(JAR_OUTPUT)DriverDomini.jar

executaDriverDocument:
	java -jar $(JAR_OUTPUT)DriverDocument.jar

executaDriverClipboard:
	java -jar $(JAR_OUTPUT)DriverClipboard.jar

executaDriverCelaSeleccionada:
	java -jar $(JAR_OUTPUT)DriverCelaSeleccionada.jar
	
executaDirverCtrlFuncio:
	java -jar $(JAR_OUTPUT)DriverCtrlFuncio.jar

executaDriverPresentacio:
	java -jar $(JAR_OUTPUT)DriverPresentacio.jar

buildinterpret:
	javac -d $(CLASS_OUTPUT) $(INTERPRET_CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(INTERPRET_CLASS_INPUT) $(INTERPRET_TESTS)

buildfuncions:
	javac -d $(CLASS_OUTPUT) $(INTERPRET_CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(INTERPRET_CLASS_INPUT) $(FUNCIONS_TESTS)

buildtypes:
	javac -d $(CLASS_OUTPUT) $(TYPE_CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(TYPE_CLASS_INPUT) $(TYPE_TESTS)

fulltest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.MasterTestSuite

TestSuiteFuncions: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.functions.TestSuiteFuncions

TestSuiteTipus: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.types.TestSuiteTipus

TestInterpret: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.functions.TestInterpret

TestCela: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestCela

TestFull: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestFull

TestDocument: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestDocument

TestReferencia: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestReferencia

TestTType: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore test.TestTType
	
clean:
	rm -r ../../EXE/out/*

distclean:
	rm -r ../../EXE/*
