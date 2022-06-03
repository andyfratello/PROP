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
 * Aquesta vista és l’encarregada de mostrar la primera pantalla que apareix a l’executar el nostre sistema que consisteix
 * amb un menú amb un text amb el títol del programa i 5 botons. 4 d’ells ens portaran a vistes diferents on es pot crear
 * un nou document, carregar-ne un, eliminar-ne un o obrir-ne un recentment obert. L’altre botó servirà per sortir i
 * tancar el programa.
 * @author Marc Clapés Marana (marc.clapes.marana@estudiantat.upc.edu)
 */
public class VistaMenuPrincipal extends JFrame {
    /** Panell on s'inclou tots els elements de la finestra */
    private final JPanel lamina = new JPanel();
    /** Títol de la finestra */
    private final JLabel titolVista = new JLabel("Full de càlcul PROP");
    /** Botó per anar a la finestra de creació de document */
    private final JButton bNouDoc = new JButton("Nou Document");
    /** Botó per anar a la finestra de documents recentment oberts */
    private final JButton bDocRecents = new JButton("Documents recents");
    /** Botó per anar a la finestra de carregar un document */
    private final JButton bCarregarDoc = new JButton("Carregar Document");
    /** Botó per anar a la finestra d'eliminar un document */
    private final JButton bEliminarDoc = new JButton("Eliminar Document");
    /** Botó de sortir per a tancar el programa */
    private final JButton bSortir = new JButton("Sortir");

    /** Constructora de la finestra del menú principal */
    public VistaMenuPrincipal() {
        // Finestra
        setBounds(500, 300, 500, 300);
        //setExtendedState(Frame.MAXIMIZED_BOTH);
        setResizable(true);
        setTitle("Full de càlcul PROP");

        // Títol finestra
        titolVista.setBounds(10, 5, 120, 30);
        add(titolVista);

        // Botó nou document
        bNouDoc.setBounds(150, 50, 200, 20);
        add(bNouDoc);

        // Botó doc recents
        bDocRecents.setBounds(150, 90, 200, 20);
        add(bDocRecents);

        // Botó carregar document
        bCarregarDoc.setBounds(150, 130, 200, 20);
        add(bCarregarDoc);

        // Botó eliminar document
        bEliminarDoc.setBounds(150, 170, 200, 20);
        add(bEliminarDoc);

        // Botó sortir
        bSortir.setBounds(150, 235, 200, 20);
        add(bSortir);

        add(lamina);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListener creacioDocument = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("FileChooser.openButtonText", "Obrir");
                UIManager.put("FileChooser.chooseButtonText", "Triar");
                UIManager.put("FileChooser.cancelButtonText", "Cancel·lar");
                CtrlPresentacio.vistaCreacioDocument();
                setVisible(false);
            }
        };

        ActionListener documentsRecents = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.vistaDocumentsRecents();
                setVisible(false);
            }
        };

        ActionListener carregarDocument = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("FileChooser.openButtonText", "Obrir");
                UIManager.put("FileChooser.cancelButtonText", "Cancel·lar");
                CtrlPresentacio.vistaCarregarDocument();
                setVisible(false);
            }
        };

        // Mirar què caldrà fer aquí
        ActionListener eliminarDocument = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("FileChooser.openButtonText", "Esborrar");
                UIManager.put("FileChooser.cancelButtonText", "Cancel·lar");
                CtrlPresentacio.vistaEliminarDocument();
                //setVisible(false);
            }
        };

        ActionListener sortir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        bNouDoc.addActionListener(creacioDocument);
        bDocRecents.addActionListener(documentsRecents);
        bCarregarDoc.addActionListener(carregarDocument);
        bEliminarDoc.addActionListener(eliminarDocument);
        bSortir.addActionListener(sortir);
    }

}
