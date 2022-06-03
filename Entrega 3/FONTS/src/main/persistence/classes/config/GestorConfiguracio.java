package main.persistence.classes.config;

import java.io.*;

/**
 * Aquesta classe conte els metodes necessaris per manterir informacio sobre quins document s'han creat i on es troben
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class GestorConfiguracio {
    /** Conté el path complet on es guarda la configuració del programa */
    private final String path = System.getProperty("user.dir") + "/" + "config.cdp";

    /**
     * Guarda les dades del byte array bytes a disc en el path marcat per l’atribut path.
     * @param bytes
     */
    public void guardaConfig(byte[] bytes) {
        try {
            FileOutputStream outFile = new FileOutputStream(path);
            outFile.write(bytes);
            outFile.flush();
            outFile.close();
        } catch (IOException e) {
            System.err.println("RUNTIME UNHANDLED EXCEPTION -> IOException when trying to write/create config file! E: " + e.toString());
            Thread.currentThread().getStackTrace();
            System.exit(-2);
        }
    }

    /**
     * Retorna un byte array amb les dades llegides del fitxer marcat per l’atribut path
     * @return
     */
    public byte[] carregaConfig() {
        byte[] bytes = null;
        try {
            FileInputStream inFile = new FileInputStream(path);
            bytes = inFile.readAllBytes();
            inFile.close();
        } catch (IOException e) {
            System.err.println("RUNTIME UNHANDLED EXCEPTION -> IOException when trying to read config file! E: " + e.toString());
            Thread.currentThread().getStackTrace();
            System.exit(-2);
        }
        return bytes;
    }

    /**
     * Retorna true si existeix el fitxer marcat per path, false altrament.
     */
    public boolean existeixConfiguracio() {
        File f = new File(path);
        return f.exists();
    }

    /**
     * Elimina el fitxer marcat per path, si s’ha pogut eliminar retorna true, altrament retorna false.
     */
    public boolean borraConfig() {
        File f = new File(path);
        return f.delete();
    }
}
