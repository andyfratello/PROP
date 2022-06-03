# Directori EXE

> Path absolut: /EXE

## Descripció del directori
Aquest directori conté tots els codis compilats del sistema juntament amb els tests i els drivers. Aquests es poden
provar a partir del fitxer *Makefile* que es troba a `/FONTS/src`.

## Elements del directori

- **Fitxers .jar:**
Aquí es troben els fitxers .jar dels drivers una vegada compilats (`make jars`).
El Driver de CelaSeleccionada **no està completat**, ja que de moment no s'utilitza.
Serà utilitzat quan completem la capa de presentació

- **Directori out:**
Hi ha l'executable de totes les classes del model (exceptions, funcions i tipus) i els tests en format *.class*.
