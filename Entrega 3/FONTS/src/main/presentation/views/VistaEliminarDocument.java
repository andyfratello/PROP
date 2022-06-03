package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Aquesta vista s’encarrega d’obrir l’explorador de documents, on es pot seleccionar únicament fitxers amb els formats
 * que suporta el nostre sistema que es poden eliminar. A sota a la dreta hi ha un botó de cancel·lar i un d’esborrar.
 * El primer tornarà la VistaMenuPrincipal i el segon eliminarà el fitxer seleccionat i tornarà al menú altre cop.
 * @author Marc Clapés Marana (marc.clapes.marana@estudiantat.upc.edu)
 */
public class VistaEliminarDocument {
    /** Finestra de resultat conforme s'ha esborrat o bé no existeix el document seleccionat */
    private JFrame frame = new JFrame();

    /** Constructora de la finestra d'eliminar document */
    public VistaEliminarDocument() {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setDialogTitle("Selecciona fitxer a esborrar");
        chooser.setFileFilter(new FileNameExtensionFilter("PROP", "csv", "prop"));
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/data/dades"));
        int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File arxiu = chooser.getSelectedFile();
            CtrlPresentacio.eliminarDocument(chooser.getName(arxiu));
            eliminarFitxer(arxiu);
        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            CtrlPresentacio.iniPresentacio();
        }
    }

    /**
     * S'esborra del sistema el fitxer passat per paràmetre i apareix un missatge conforme s'ha realitzat correctament.
     * Surt un missatge d'error en cas que el fitxer no existeixi
     * @param fitxer
     */
    private void eliminarFitxer(File fitxer) {
        JButton botoSortir = new JButton("Sortir");
        boolean status = fitxer.delete();
        if (!status) {
            JDialog fitxerNoExisteix =  new JDialog(frame, "El fitxer no existeix");
            fitxerNoExisteix.setBounds(800, 300, 400, 200);
            fitxerNoExisteix.setLayout(null);

            JLabel txtFitxerNoExisteix = new JLabel("El fitxer " + fitxer.getName() + " no existeix");
            txtFitxerNoExisteix.setBounds(80, 20, 400, 40);
            botoSortir.setVisible(true);
            botoSortir.setBounds(150, 110, 100, 30);
            fitxerNoExisteix.add(txtFitxerNoExisteix);
            fitxerNoExisteix.add(botoSortir);
            fitxerNoExisteix.setVisible(true);

            ActionListener lSortirFitxerNoExisteix = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fitxerNoExisteix.dispose();
                    fitxerNoExisteix.setVisible(false);
                }
            };
            botoSortir.addActionListener(lSortirFitxerNoExisteix);

        } else {
            JDialog fitxerEsborrat =  new JDialog(frame, "Fitxer esborrat correctament");
            fitxerEsborrat.setBounds(800, 300, 400, 200);
            fitxerEsborrat.setLayout(null);

            JLabel txtFitxerEsborrat = new JLabel("El fitxer " + fitxer.getName() + " s'ha esborrat correctament");
            txtFitxerEsborrat.setBounds(80, 20, 400, 40);
            botoSortir.setVisible(true);
            botoSortir.setBounds(150, 110, 100, 30);
            fitxerEsborrat.add(txtFitxerEsborrat);
            fitxerEsborrat.add(botoSortir);
            fitxerEsborrat.setVisible(true);

            ActionListener lSortirFitxerEsborrat = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fitxerEsborrat.dispose();
                    fitxerEsborrat.setVisible(false);
                }
            };
            botoSortir.addActionListener(lSortirFitxerEsborrat);
        }
    }
}
