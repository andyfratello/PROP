# PROP Grup 1.1
Projecte de Programació, Grup 11, subgrup 1.1. <br>Professor: Jordi Turmo ([turmo@cs.upc.edu]()).

## Membres del grup

- Joan Aluja ([joan.aluja@estudiantat.upc.edu]())
- Marc Clapés ([marc.clapes.marana@estudiantat.upc.edu]())
- Marc Duch ([marc.duch@estudiantat.upc.edu]())
- Andreu Orensanz ([andreu.orensanz@estudiantat.upc.edu]()).

## Elements del directori

### DOCS:
Conté tota la documentació referent als tests d'integració de tot el projecte, relació de les classes implementades per 
membre de l’equip i la documentació Javadoc de tot el codi.

### EXE:
Fitxers executables (*.class*) de totes les classes que permeten provar les funcionalitats principals implementades i
un fitxer *.jar* del qual es pot executar el programa sencer
Hi ha subdirectoris per cada un dels tipus de classes: test, excepcions, funcions, tipus, que segueixen l'estructura
determinada pels *packages*

### FONTS:
Codi de les classes de domini, persistència i presentació associades a les funcionalitats principals. Inclou també els
tests JUnit. Tots els fitxers estan dins dels subdirectoris que segueixen l'estructura de packages, perquè el codi sigui
recompilable directament. També inclou el fitxer *Makefile*.

### lib:
S'hi troben les llibreries externes que hem hagut d'utilitzar per als tests JUnit.

## Provar el programa

Per a executar el programa cal anar al directori `/FONTS/src`, que és on es troba el *Makefile* del programa. Es
construeix el sistema entrant `make` a la consola i es podrà provar totes les classes individualment a través dels 
exec.

### Descripció Makefile

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

## Enunciat del projecte

#### Full de càlcul senzill
Es tracta de construir un entorn per a la manipulació de dades disposats en forma de taules, on es puguin efectuar 
operacions amb funcions.
L’entorn ha de permetre com a mínim el tractament de dades de tipus numèric, textual i tipus data. S’ha de poder 
gestionar com a mínim documents (grups de fulls), fulls, files, columnes, blocs (o rangs, de n x m cel·les) i cel·les.
El programa ha d’oferir un entorn tant còmode com sigui possible, i obligatòriament ha de:

- Permetre carregar documents (a partir de diferents formats, com a mínim csv) i guardar documents (a diferents formats,
  com a mínim csv)

- Permetre la gestió de tots els elements mencionats al primer paràgraf:
   1. Crear/eliminar documents
   2. Afegir fulls (de n x m cel·les), eliminar fulls
   3. Afegir/eliminar files/columnes
   4. Copiar/moure continguts de blocs
   5. Modificar contingut de cel·les
   6. Utilitzar valors o referències a altres cel·les (del mateix full o opcionalment d’un altre full)

- Permetre ordenacions en blocs, com a mínim amb un criteri 

- Permetre cerques i reemplaçaments en blocs 

- Permetre definir funcions, generals o dependents del tipus de dada, a aplicar sobre cel·les/files/columnes. S’ha de 
incorporar els següents tipus de funcions (com a mínim, dues de cada subtipus):
      1. Numèriques
         1. Truncats
         2. Conversions
         3. Operacions aritmètiques unàries (i.e. amb un sol paràmetre, per exemple incrementar, valor absolut, etc.)
      2. Dates
         1. Extraccions d’elements
         2. Dia setmana
      3. Text
         1. Reemplaçaments
         2. Longituds
      4. Estadístiques (en aquest cas s’han de implementar totes)
         1. Mitjana, mediana
         2. Variància, covariància, desviació estàndard 
         3. Coeficient de correlació de Pearson

- Permetre que a partir de les funcions anteriors es puguin calcular dades derivades (per ex, una nova columna com a 
resultat d’aplicar una funció sobre una altra)
   
   I opcionalment:

   - Permetre composicions de funcions predefinides

   - Permetre funcions més complexes
                        
   A més dels altres factors de qualitat de qualsevol programa (disseny, codificació, reusabilitat, modificabilitat, 
   documentació,...), es valorarà en particular l'eficiència, flexibilitat i usabilitat d’aquest.

#### Reducció de funcionalitat als equips de 3 persones:

- Només s’ha de implementar una funció per subtipus

- Ordenacions i cerques són opcionals

#### Dates dels lliuraments:

- Primer: divendres 22 d’abril de 2022

- Segon: dilluns 23 de maig de 2022

- Tercer: dilluns 30 de maig de 2022 (lliuraments interactius: a partir del 31 de maig de 2022)

