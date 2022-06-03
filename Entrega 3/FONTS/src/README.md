# Directori src

> Path absolut: /FONTS/src

## Descripció del directori
Aquest directori conté tot el codi del projecte organitzat per packages

## Elements del directori

- **Directori drivers:**
Conté els drivers que hem utilitzat al nostre sistema per poder testejar el sistema complet.
- **Directori main:**
Conté tots els codis de les classes del model classificats per directoris, un per cada capa (seguint l'arquitectura
en tres capes). El directori domini és l'únic que té contingut.
- **Directori test:**
Conté tots els tests unitaris, fets amb JUnit, de les classes del model conceptual clapssificats per les classes que
realitzen alguna funcionalitat i les classes que defineixen tipus de dades.
- ***Makefile***
Permet executar el sistema i testejar les classes a partir dels exec. Conté diverses opcions.

## Descripció Makefile

- `make executable`

      Compila i crea l'executable principal per a iniciar el programa (Format .jar) en el directori `EXE` i l'executa

- `make executa`

      Executa el fitxer executable principal per tal d'iniciar el programa


- `make jars`

      Crea tots els executables dels exec (Format .jar) en el directori `EXE`

- `make executaDriver**NomDriver**`

      Intenta executar el jar del Driver corresponent

      Exemple: make executaDriverDomini -> executa el jar del Driver Domini

      El Driver de CelaSeleccionada **no està completat**, ja que de moment no s'utilitza.

      Serà utilitzat quan completem la capa de presentació

- `make fulltest`

      Executa tots els test/tests suites de totes les classes

- `make jar**NomDriver**`

      Compila el codi del sistema i el driver corresponent i crea en un .jar executable

      Exemple: make jarDomini -> crea el jar i l'executa

- `make Test**NomTest/NomSuite**`

      Executa el/els test/tests NomTest/NomSuite

      - Tests
         1. TestSuiteFuncions
         2. TestSuiteTipus
         3. TestInterpret
         4. TestCela
         5. TestFull
         6. TestDocument


- `make all`

      Compila totes les classes del sistema
