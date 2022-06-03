package main.persistence.classes.csv;

import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Vector;
import java.nio.file.*;

/**
 * Aquesta classe conte els metodes necessaris per poder exportar fitxers CSV i poder carregar-los
 * @see "El format CSV no permet guardar funcions i per tant es perden dades (Utilitzant el format .PROP si que ho pertmet!)"
 * @author Joan Aluja Oraa (joan.aluja@estudiantat.upc.edu)
 */
public class GestorCSV {
    /**
     * Guarda les dades rebudes en la matriu x en el fitxer marcat per path en format CSV (Separat per comes)
     * @param x
     * @param path
     */
    public void guardaFull(Vector<Vector<String>> x, String path) {
        try {
            Path p = Paths.get(path); 
            BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            x.forEach(fila -> {
                for (int i = 0; i < fila.size(); i++) {
                    String aEscriure = "," + fila.get(i);
                    if (i == 0) {
                        aEscriure = fila.get(i);
                    }
                    try {
                        bw.write(aEscriure);
                    } catch (IOException e) {
                        System.err.println("RUNTIME UNHANDLED EXCEPTION -> IOException when trying to write/create CSV file! E: " + e.toString());
                        System.err.println(Thread.currentThread().getStackTrace().toString());
                        System.exit(-20);
                    }
                }

                try {
                    bw.write("\n");
                } catch (Exception e) {
                    System.err.println("RUNTIME UNHANDLED EXCEPTION -> IOException when trying to write/create CSV file! E: " + e.toString());
                    System.err.println(Thread.currentThread().getStackTrace().toString());
                    System.exit(-21);
                }
            });
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.err.println("RUNTIME UNHANDLED EXCEPTION -> IOException when trying to write/create CSV file! E: " + e.toString());
            System.err.println(Thread.currentThread().getStackTrace().toString());
            System.exit(-2);
        }
    }

    /**
     * Carrega el fitxer en format CSV marcat per path i retorna les dades en una matriu d’Strings.
     * @param path
     * @return
     */
    public Vector<Vector<String>> carregaFull(String path) {
        Vector<Vector<String>> f = new Vector<Vector<String>>();
        try {
            Path p = Paths.get(path);
            BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8);
            String strFil;
            int fila = 0;
            while((strFil = br.readLine()) != null) {
                f.add(new Vector<String>());
                String[] filaS = strFil.split(",", -1);
                for (String s: filaS) {
                    f.get(fila).add(s);
                }
                fila++;
            }
        } catch (IOException e) {
            System.err.println("RUNTIME UNHANDLED EXCEPTION -> IOException when trying to read CSV file! E: " + e.toString());
            System.err.println(Thread.currentThread().getStackTrace().toString());
            System.exit(-2);
        }
        return f;
    }
}
