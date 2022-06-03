package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Aquesta vista és l’encarregada de mostrar els 5 fitxers més recentment utilitzats, per ordre de més recent a menys.
 * Es mostra el path i el nom del fitxer i un botó per orbrir-lo. A sota de la vista s’hi proporciona també un botó per
 * a tornar al menú principal.
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class VistaDocumentsRecents extends JFrame {
    /** Panell on s'inclou tots els elements de la finestra */
    private final JPanel lamina = new JPanel();
    /** Títol de la finestra */
    private final JLabel titolVista = new JLabel("Selecciona un document recent");
    /** Text quan no hi ha documents recents en pantalla */
    private final JLabel noRecents = new JLabel("No hi ha documents recents");
    /** Nom i data del document més recent */
    private JLabel nomDoc1 = new JLabel();
    /** Nom i data del segon document més recent */
    private JLabel nomDoc2 = new JLabel();
    /** Nom i data del tercer document més recent */
    private JLabel nomDoc3 = new JLabel();
    /** Nom i data del quart document més recent */
    private JLabel nomDoc4 = new JLabel();
    /** Nom i data del cinquè document més recent */
    private JLabel nomDoc5 = new JLabel();
    /** Botó d'obrir el document més recent */
    private final JButton bDoc1 = new JButton("Obrir");
    /** Botó d'obrir el segon document més recent */
    private final JButton bDoc2 = new JButton("Obrir");
    /** Botó d'obrir el tercer document més recent */
    private final JButton bDoc3 = new JButton("Obrir");
    /** Botó d'obrir el quart document més recent */
    private final JButton bDoc4 = new JButton("Obrir");
    /** Botó d'obrir el cinquè document més recent */
    private final JButton bDoc5 = new JButton("Obrir");
    /** Vector on s'hi guarda el nom, la data i el path dels 5 documents més recentment oberts */
    private Vector<Vector<String>> vecNomDoc;

    /** Botó de retornar a la pantalla del menú principal */
    private final JButton bSortir = new JButton("Enrere");

    /** Constructora de la finestra de documents recents */
    public VistaDocumentsRecents() {
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Documents Recents");

        vecNomDoc = CtrlPresentacio.obteConfig();

        // Títol finestra
        titolVista.setBounds(10, 3, 200, 30);
        add(titolVista);

        // No hi ha documents recents
        if (vecNomDoc.size() == 0) {
            noRecents.setBounds(100, 35, 250, 30);
            add(noRecents);
        }

        // Doc1
        if (vecNomDoc.size() > 0) {
            String tmp = vecNomDoc.get(0).get(0).substring(0,9) + ' ' + vecNomDoc.get(0).get(0).substring(11,19);
            nomDoc1.setText(vecNomDoc.get(0).get(1) + "      " + tmp);
            nomDoc1.setBounds(100, 35, 250, 30);
            bDoc1.setBounds(385, 35, 90, 20);
            add(nomDoc1);
            ActionListener lDoc1 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String path = vecNomDoc.get(0).get(2);
                    if (vecNomDoc.get(0).get(1).endsWith(".csv")) {
                        // Carregar CSV
                        CtrlPresentacio.carregarCSV(path + "/" + vecNomDoc.get(0).get(1));
                    } else if (vecNomDoc.get(0).get(1).endsWith(".prop")) {
                        // Carregar PROP
                        CtrlPresentacio.carregaDocument(path + "/" + vecNomDoc.get(0).get(1));
                    }
                    CtrlPresentacio.vistaSpreadsheet(vecNomDoc.get(0).get(1), true); // test.csv -> test
                }
            };
            bDoc1.addActionListener(lDoc1);
            add(bDoc1);
        }

        // Doc2
        if (vecNomDoc.size() > 1) {
            String tmp = vecNomDoc.get(1).get(0).substring(0,9) + ' ' + vecNomDoc.get(1).get(0).substring(11,19);
            nomDoc2.setText(vecNomDoc.get(1).get(1) + "      " + tmp);
            nomDoc2.setBounds(100, 75, 250, 30);
            bDoc2.setBounds(385, 75, 90, 20);
            add(nomDoc2);
            ActionListener lDoc2 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String path = vecNomDoc.get(1).get(2);
                    if (vecNomDoc.get(1).get(1).endsWith(".csv")) {
                        // Carregar CSV
                        CtrlPresentacio.carregarCSV(path + "/" + vecNomDoc.get(1).get(1));
                    } else if (vecNomDoc.get(1).get(1).endsWith(".prop")) {
                        // Carregar PROP
                        CtrlPresentacio.carregaDocument(path + "/" + vecNomDoc.get(1).get(1));
                    }
                    CtrlPresentacio.vistaSpreadsheet(vecNomDoc.get(1).get(1), true); // test.csv -> test
                }
            };
            bDoc2.addActionListener(lDoc2);
            add(bDoc2);
        }

        // Doc3
        if (vecNomDoc.size() > 2) {
            String tmp = vecNomDoc.get(2).get(0).substring(0,9) + ' ' + vecNomDoc.get(2).get(0).substring(11,19);
            nomDoc3.setText(vecNomDoc.get(2).get(1) + "      " + tmp);
            nomDoc3.setBounds(100, 115, 250, 30);
            bDoc3.setBounds(385, 115, 90, 20);
            add(nomDoc3);
            ActionListener lDoc3 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String path = vecNomDoc.get(2).get(2);
                    if (vecNomDoc.get(2).get(1).endsWith(".csv")) {
                        // Carregar CSV
                        CtrlPresentacio.carregarCSV(path + "/" + vecNomDoc.get(2).get(1));
                    } else if (vecNomDoc.get(2).get(1).endsWith(".prop")) {
                        // Carregar PROP
                        CtrlPresentacio.carregaDocument(path + "/" + vecNomDoc.get(2).get(1));
                    }
                    CtrlPresentacio.vistaSpreadsheet(vecNomDoc.get(2).get(1), true); // test.csv -> test
                }
            };
            bDoc3.addActionListener(lDoc3);
            add(bDoc3);
        }

        // Doc4
        if (vecNomDoc.size() > 3) {
            String tmp = vecNomDoc.get(3).get(0).substring(0,9) + ' ' + vecNomDoc.get(3).get(0).substring(11,19);
            nomDoc4.setText(vecNomDoc.get(3).get(1) + "      " + tmp);
            nomDoc4.setBounds(100, 155, 250, 30);
            bDoc4.setBounds(385, 155, 90, 20);
            add(nomDoc4);
            ActionListener lDoc4 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String path = vecNomDoc.get(3).get(2);
                    if (vecNomDoc.get(3).get(1).endsWith(".csv")) {
                        // Carregar CSV
                        CtrlPresentacio.carregarCSV(path + "/" + vecNomDoc.get(3).get(1));
                    } else if (vecNomDoc.get(3).get(1).endsWith(".prop")) {
                        // Carregar PROP
                        CtrlPresentacio.carregaDocument(path + "/" + vecNomDoc.get(3).get(1));
                    }
                    CtrlPresentacio.vistaSpreadsheet(vecNomDoc.get(3).get(1), true); // test.csv -> test
                }
            };
            bDoc4.addActionListener(lDoc4);
            add(bDoc4);
        }

        // Doc5
        if (vecNomDoc.size() > 4) {
            String tmp = vecNomDoc.get(4).get(0).substring(0,9) + ' ' + vecNomDoc.get(4).get(0).substring(11,19);
            nomDoc5.setText(vecNomDoc.get(4).get(1) + "      " + tmp);
            nomDoc5.setBounds(100, 195, 250, 30);
            bDoc5.setBounds(385, 195, 90, 20);
            add(nomDoc5);
            ActionListener lDoc5 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String path = vecNomDoc.get(4).get(2);
                    if (vecNomDoc.get(4).get(1).endsWith(".csv")) {
                        // Carregar CSV
                        CtrlPresentacio.carregarCSV(path + "/" + vecNomDoc.get(4).get(1));
                    } else if (vecNomDoc.get(4).get(1).endsWith(".prop")) {
                        // Carregar PROP
                        CtrlPresentacio.carregaDocument(path + "/" + vecNomDoc.get(4).get(1));
                    }
                    CtrlPresentacio.vistaSpreadsheet(vecNomDoc.get(4).get(1), true); // test.csv -> test
                }
            };
            bDoc5.addActionListener(lDoc5);
            add(bDoc5);
        }

        // Botó sortir
        bSortir.setBounds(150, 235, 200, 20);
        ActionListener lEnrere = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.iniPresentacio();
                setVisible(false);
            }
        };
        bSortir.addActionListener(lEnrere);
        add(bSortir);

        add(lamina);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
