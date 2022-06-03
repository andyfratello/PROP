# Directori drivers

> Path absolut: /FONTS/src/drivers

## Descripció del directori
Aquest directori conté el codi dels Drivers dels controladors que proporcionen alguna funcionalitat principal del sistema

## Elements del directori

- **DriverDomini.java:**
Conté el codi del driver del CtrlDomini (Proporciona totes les funcionalitats actuals del sistema)

Al ser executat es mostra un menu amb les possibles opcions a executar. Es pot entrar el nombre o titol del metode a provar.

Us:

    0. Tancar el Driver
        Finalitza la execucio del Driver


    1. constructora
        Genera les instancies i valors necessaris per poder utilitzar
        la resta de metodes del driver.
        Demana per terminal el nom i ubicacio(No implementat) del Document.
        Crea un Full amb el tamany per defecte de 1000*1000 sense titol.


    **TOTS ELS SEGUENTS METODES REQUEREIXEN HAVER COMPLETAT LA CONSTRUCTORA**


    2. cercarValor
        Permet cercar valors en un Bloc format a partir de 2 Celes.
        Mostra els resultats per pantalla.


    3. copiarCela
        Demana per pantalla la posicio de la Cela que es vol copiar.
        Aquest valor de la Cela es guarda per despres poder ser enganxat.


    4. tallarCela
        Demana per pantalla la posicio de la Cela que es vol tallar.
        Aquest valor de la Cela es guarda per despres poder ser enganxat.


    5. copiarBloc
        Demana per pantalla les posicions de les dues Celes que formen el bloc que es vol copiar.
        Aquests valors de les Celes es guarden per despres poder ser enganxats.


    6. tallarBloc
        Demana per pantalla les posicions de les dues Celes que formen el bloc que es vol tallar.
        Aquests valors de les Celes es guarden per despres poder ser enganxats.


    7. enganxar
        Demana la posicio de la Cela a partir de la qual es vol enganxar el contingut copiat/tallat
        amb anterioritat, si no s'ha copiat/tallat amb anterior, no es modifica cap Cela.


    8. obtenirValorCela
        Demana per pantalla la posicio de la Cela de la qual se'n vol obtenir el valor (Valor entrat per l'usuari) i mostra aquest valor.


    9. obtenirValorRealCela
        Demana per pantalla la posicio de la Cela de la qual se'n vol obtenir el valor real (Valor calculat) i mostra aquest valor.


    10. ordenarPerColumnes
        Demana per pantalla les posicions de les dues Celes que formen el bloc que es vol ordenar per columnes. 
        Les ordena pero no mostra els canvis.


    11. getDocumentObert
        Mostra per pantalla el nom i localitzacio del document obert.


    12. obtenirFullActiu
        Mostra el ID del Full actiu actualment. Si no hi han fulls mostra un missatge per indicar-ho.


    13. setFullActiu
        Mostra les possibles IDs dels Fulls del Document actual, si n'hi han.
        Demana que es seleccioni un dels IDs per canviar de Full actiu i el canvia.


    14. crearDocument
        Si ja hi ha un document obert aquest metode no crea ni canvia el document obert!
        Demana el nom i localitzacio del nou document que es vol crear i es canvia a aquest nou document.


    15. tancarDocument
        Es tanca el ducment obert.


    16. obtenirCela
        Aquest metode, de manera identica al 8|obtenirValorCela, demana la posicio de la Cela
        de la que es vol obtenir. I mostra el seu valor usuari (Valor entrat per l'usuari).
        Aquest metode difereix del obtenirValor en que retorna la instancia de la Cela en si.


    17. modificarCela
        Aquest metode demana la posicio de la Cela que es vol modificar, despres demana el valor que es vol posar en aquella posicio. 
        Un cop te els valors guarda el valor entrat per l'usuari i fa els calculs, si corresponen, per despres guardar-los en la Cela corresponent.


    18. afegirFila
        Demana la posicio en la qual es vol afegir una fila (0.."Nombre Files"), i l'afegeix.


    19. afegirColumna
        Demana la posicio en la qual es vol afegir una columna (0.."Nombre Columnes"), i l'afegeix.


    20. eliminarFila
        Demana la posicio en la qual es vol eliminar una fila (0..("Nombre Files"-1)), i la elimina.


    21. eliminarColumna
        Demana la posicio en la qual es vol eliminar una columna (0..("Nombre Columnes"-1)), i la elimina.


    22. reemplacarValor
        Demana les posicions de les dues Celes que formen el bloc en el qual es vol reemplacar.
        Despres demana el valor a reemplacar i el valor que el reemplaca.
        Una vegada te totes les dades reemplaca cada ocurrencia.


    23. canviarNomDocument
        Modifica el nom del Cocument obert actualment per el valor entrat per teclat.


    24. canviarNomFull
        Modifica el nom del Full actiu per el valor entrat per teclat.


    25. eliminarFull
        Elimina el Full actiu si n'hi ha.


    26. crearFull
        Crea un Full amb el nom, nombre de columnes, nombre de files del nou Full a crear.
        Es canvia el Full actiu al nou Full.



- **DriverDocument.java:**
Conté el codi del driver del CtrlDocument (Proporciona totes les funcionalitats de la classe Document i les seves relacions)

Al ser executat es mostra un menu amb les possibles opcions a executar. Es pot entrar el nombre o titol del metode a provar.

Us:

    0. Tancar el Driver
        Finalitza la execucio del Driver


    1. constructora
        Genera les instancies i valors necessaris per poder utilitzar
        la resta de metodes del driver.
        Demana per terminal el nom i ubicacio(No implementat) del Document.
        Crea un Full amb el tamany per defecte de 1000*1000 sense titol.


    2. cercarValor
        Permet cercar valors en un Bloc format a partir de 2 Celes.
        Mostra els resultats per pantalla.


    3. ordenarPerColumnes
        Demana per pantalla les posicions de les dues Celes que formen el bloc que es vol ordenar per columnes. 
        Les ordena pero no mostra els canvis.


    4. getDocumentObert
        Mostra per pantalla el nom i localitzacio del document obert.


    5. obtenirFullActiu
        Mostra el ID del Full actiu actualment. Si no hi han fulls mostra un missatge per indicar-ho.


    6. setFullActiu
        Mostra les possibles IDs dels Fulls del Document actual, si n'hi han.
        Demana que es seleccioni un dels IDs per canviar de Full actiu i el canvia.

    
    7. crearDocument
        Si ja hi ha un document obert aquest metode no crea ni canvia el document obert!
        Demana el nom i localitzacio del nou document que es vol crear i es canvia a aquest nou document.


    8. tancarDocument
        Es tanca el ducment obert.

    
    9. obtenirCela
        Aquest metode, de manera identica al 8|obtenirValorCela, demana la posicio de la Cela
        de la que es vol obtenir. I mostra el seu valor usuari (Valor entrat per l'usuari).
        Aquest metode difereix del obtenirValor en que retorna la instancia de la Cela en si.


    10. modificarCela
        Aquest metode demana la posicio de la Cela que es vol modificar, despres demana el valor que es vol posar en aquella posicio.
        Un cop te els valors guarda el valor entrat per l'usuari i fa els calculs, si corresponen, per despres guardar-los en la Cela corresponent.


    11. afegirFila
        Demana la posicio en la qual es vol afegir una fila (0.."Nombre Files"), i l'afegeix.


    12. afegirColumna
        Demana la posicio en la qual es vol afegir una columna (0.."Nombre Columnes"), i l'afegeix.


    13. eliminarFila
        Demana la posicio en la qual es vol eliminar una fila (0..("Nombre Files"-1)), i la elimina.


    14. eliminarColumna
        Demana la posicio en la qual es vol eliminar una columna (0..("Nombre Columnes"-1)), i la elimina.
    

    15. canviarNomDocument
        Modifica el nom del Cocument obert actualment per el valor entrat per teclat.


    16. canviarNomFull
        Modifica el nom del Full actiu per el valor entrat per teclat.


    17. eliminarFull
        Elimina el Full actiu si n'hi ha.


    18. crearFull
        Crea un Full amb el nom, nombre de columnes, nombre de files del nou Full a crear.
        Es canvia el Full actiu al nou Full.


- **DriverClipboard.java:**
Conté el codi del driver del CtrlClipboard (Proporciona totes les funcionalitats de copiar/retallar/enganxar de Celes/Blocs)

Al ser executat es mostra un menu amb les possibles opcions a executar. Es pot entrar el nombre o titol del metode a provar.

Us:

    0. Tancar el Driver
        Finalitza la execucio del Driver


    1. constructora
        Genera les instancies i valors necessaris per poder utilitzar
        la resta de metodes del driver.
        Demana per terminal el nom i ubicacio(No implementat) del Document.
        Crea un Full amb el tamany per defecte de 1000*1000 sense titol.


    2. modificarCela
        Aquest metode demana la posicio de la Cela que es vol modificar, despres demana el valor que es vol posar en aquella posicio.
        Un cop te els valors guarda el valor entrat per l'usuari i fa els calculs, si corresponen, per despres guardar-los en la Cela corresponent.

    3. copiarCela
        Demana per pantalla la posicio de la Cela que es vol copiar.
        Aquest valor de la Cela es guarda per despres poder ser enganxat.


    4. tallarCela
        Demana per pantalla la posicio de la Cela que es vol tallar.
        Aquest valor de la Cela es guarda per despres poder ser enganxat.


    5. copiarBloc
        Demana per pantalla les posicions de les dues Celes que formen el bloc que es vol copiar.
        Aquests valors de les Celes es guarden per despres poder ser enganxats.


    6. tallarBloc
        Demana per pantalla les posicions de les dues Celes que formen el bloc que es vol tallar.
        Aquests valors de les Celes es guarden per despres poder ser enganxats.


    7. enganxar
        Demana la posicio de la Cela a partir de la qual es vol enganxar el contingut copiat/tallat
        amb anterioritat, si no s'ha copiat/tallat amb anterior, no es modifica cap Cela.

    
