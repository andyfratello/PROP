package main.presentation.views;

import main.domain.classes.types.Pair;
import main.presentation.*;
import main.presentation.controllers.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Objects;
import java.util.Vector;

/**
 * Aquesta vista és l’encarregada de mostrar el document, amb els diversos fulls creats. Inicialment està format per un
 * full de càlcul on les columnes van ordenades alfabèticament i les files ordenades numèricament. A sota es pot canviar
 * o generar més fulls amb els panells que hi ha sota el full. A sobre el full hi trobem una barra de text on hi
 * apareixerà el valor d’usuari de la cel·la seleccionada, mentre que a la cel·la hi apareixerà només el valor real. A
 * dalt de tot d’aquesta vista hi trobem una barra de menú on s’hi pot seleccionar les funcionalitats principals que
 * realitza el nostre full de càlcul, així com guardar el document actual i obrir-ne o crear-ne un de nou.
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class VistaSpreadsheet extends JFrame {
    /** Finestra de selecció de l'arxiu que es vol obrir */
    private final JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    /** Àrea de text del contingut de la cel·la seleccionada */
    public static JTextArea barraCont = new JTextArea();

    /** Barra de menús que inclou la majoria de funcionalitats del full de càlcul classificades en menús desplegables */
    private JMenuBar mb = new JMenuBar();
    /** Menú desplegable amb les opcions de crear o obrir un nou document, o guardar el document que s'està editant */
    private JMenu fitxer = new JMenu("Fitxer");
    /** Menú desplegable amb les opcions de copiar, retallar o enganxar cel·les o blocs seleccionats */
    private JMenu edita = new JMenu("Edita");
    /** Menú desplegable amb les opcions de copiar, retallar o enganxar cel·les o blocs seleccionats */
    private JMenu insereix = new JMenu("Insereix");
    /** Menú desplegable amb les opcions d’eliminar columna i eliminar fila */
    private JMenu elimina = new JMenu("Elimina");
    /** Menú desplegable amb les opcions de totes les funcions que calcula el nostre full de càlcul */
    private JMenu funcio = new JMenu("Funció");
    // file
    /** Ítem del menú desplegable de Fitxer per a crear un nou document */
    private JMenuItem nou = new JMenuItem("Nou");
    /** Ítem del menú desplegable de Fitxer per a obrir un nou document */
    private JMenuItem obre = new JMenuItem("Obre");
    /**
     * Ítem del menú desplegable de Fitxer per a guardar el document que s'està editant. Fa de submenú desplegable amb
     * les opcions de guardar a CSV o al format PROP
     */
    private JMenu guarda = new JMenu("Guarda");
    /** Ítem del submenú Guarda per a guardar el document que s'està editant a CSV */
    private JMenuItem aCSV = new JMenuItem("CSV (.csv)");
    /** Ítem del submenú Guarda per a guardar el doccument que s'està editant a PROP */
    private JMenuItem aPROP = new JMenuItem("PROP (.prop)"); // ?
    // edita
    /** Ítem del menú Edita per a copiar la cel·la o el bloc seleccionat */
    private JMenuItem copia = new JMenuItem("Copia");
    /** Ítem del menú Edita per a enganxar la cel·la o el bloc seleccionat */
    private JMenuItem enganxa = new JMenuItem("Enganxa");
    /** Ítem del menú Edita per a tallar la cel·la o el bloc seleccionat */
    private JMenuItem retalla = new JMenuItem("Retalla");
    /** Ítem del menú Edita per a cercar un valor al full seleccionat */
    private JMenuItem cerca = new JMenuItem("Cerca");
    /** Ítem del menú Edita per a reemplaçar un valor al full seleccionat */
    private JMenuItem reemplaca = new JMenuItem("Reemplaça");

    /** Submenú ítem del menú Edita per a ordenar el contingut de les cel·les d'un bloc seleccionat */
    private JMenuItem ordena = new JMenuItem("Ordena");
    ///** Ítem del submenú Ordena per a ordenar exclusivament Strings */
    //private JMenuItem oString = new JMenuItem ("Strings");
    ///** Ítem del submenú Ordena per a ordenar exclusivament Integers */
    //private JMenuItem oInteger = new JMenuItem("Integers");

    // insereix
    /** Submenú ítem del menú Insereix per a afegir files o bé a l'inici o a la posició de la cel·la seleccionada */
    private JMenu afFila = new JMenu("Fila");
    /** Ítem del submenú Fila per a afegir una fila al final del full */
    private JMenuItem fIni = new JMenuItem("Final");
    /** Ítem del submenú Fila per a afegir una fila a la posició de la cel·la que està seleccionada */
    private JMenuItem fPos = new JMenuItem("Posició seleccionada");
    /** Submenú ítem del menú Insereix per a afegir columnes o bé a l'inici o a la posició de la cel·la seleccionada */
    private JMenu afColumna = new JMenu("Columna");
    /** Ítem del submenú Columna per a afegir una columna al final del full */
    private JMenuItem cIni = new JMenuItem("Final");
    /** Ítem del submenú Columna per a afegir una columna a la posició de la cel·la que està seleccionada */
    private JMenuItem cPos = new JMenuItem("Posició seleccionada");

    /** Ítem del menú Insereix per a eliminar la fila de la cel·la seleccionada */
    private JMenuItem elFila = new JMenuItem("Eliminar Fila");
    /** Ítem del menú Insereix per a eliminar la columna de la cel·la seleccionada */
    private JMenuItem elColumna = new JMenuItem("Eliminar Columna");
    // funcio
    /** Ítem del menú Funció per a introduir la funció A Majúscula */
    private JMenuItem funcAMajuscula = new JMenuItem("A Majúscula");
    /** Ítem del menú Funció per a introduir la funció Absolut */
    private JMenuItem funcAbsolut = new JMenuItem("Absolut");
    /** Ítem del menú Funció per a introduir la funció Arrel */
    private JMenuItem funcArrel = new JMenuItem("Arrel");
    /** Ítem del menú Funció per a introduir la funció Arrodonir */
    private JMenuItem funcArrodonir = new JMenuItem("Arrodonir");
    /** Ítem del menú Funció per a introduir la funció Binari a Decimal */
    private JMenuItem funcBinariadecimal = new JMenuItem("Binari a Decimal");
    /** Ítem del menú Funció per a introduir la funció Correlació Pearson */
    private JMenuItem funcPearson = new JMenuItem("Correlació Pearson");
    /** Ítem del menú Funció per a introduir la funció Covariància */
    private JMenuItem funcCovariancia = new JMenuItem("Covariància");
    /** Ítem del menú Funció per a introduir la funció Decimal a Binari */
    private JMenuItem funcDecimalabinari = new JMenuItem("Decimal a Binari");
    /** Ítem del menú Funció per a introduir la funció Decimal a Hexadecimal */
    private JMenuItem funcDecimalahexadecimal = new JMenuItem("Decimal a Hexadecimal");
    /** Ítem del menú Funció per a introduir la funció Desviació estàndard */
    private JMenuItem funcDesviacioestandar = new JMenuItem("Desviació estàndard");
    /** Ítem del menú Funció per a introduir la funció Divisió */
    private JMenuItem funcDivisio = new JMenuItem("Divisió");
    /** Ítem del menú Funció per a introduir la funció Factorial */
    private JMenuItem funcFactorial = new JMenuItem("Factorial");
    /** Ítem del menú Funció per a introduir la funció Hexadecimal a Decimal */
    private JMenuItem funcHexadecimaladecimal = new JMenuItem("Hexadecimal a Decimal");
    /** Ítem del menú Funció per a introduir la funció Mediana */
    private JMenuItem funcMediana = new JMenuItem("Mediana");
    /** Ítem del menú Funció per a introduir la funció Mitjana */
    private JMenuItem funcMitjana = new JMenuItem("Mitjana");
    /** Ítem del menú Funció per a introduir la funció Multiplicació */
    private JMenuItem funcMultiplicacio = new JMenuItem("Multiplicació");
    /** Ítem del menú Funció per a introduir la funció Número paraules */
    private JMenuItem funcNumeroparaules = new JMenuItem("Número paraules");
    /** Ítem del menú Funció per a introduir la funció Obté any */
    private JMenuItem funcObteany = new JMenuItem("Obté any");
    /** Ítem del menú Funció per a introduir la funció Obté dia */
    private JMenuItem funcObtedia = new JMenuItem("Obté dia");
    /** Ítem del menú Funció per a introduir la funció Obté dia setmana */
    private JMenuItem funcObtediasetmana = new JMenuItem("Obté dia setmana");
    /** Ítem del menú Funció per a introduir la funció Obté mes */
    private JMenuItem funcObtemes = new JMenuItem("Obté mes");
    /** Ítem del menú Funció per a introduir la funció Potència */
    private JMenuItem funcPotencia = new JMenuItem("Potència");
    /** Ítem del menú Funció per a introduir la funció Reemplaça */
    private JMenuItem funcReemplaca = new JMenuItem("Reemplaça");
    /** Ítem del menú Funció per a introduir la funció Resta */
    private JMenuItem funcResta = new JMenuItem("Resta");
    /** Ítem del menú Funció per a introduir la funció Suma */
    private JMenuItem funcSuma = new JMenuItem("Suma");
    /** Ítem del menú Funció per a introduir la funció Tamany text */
    private JMenuItem funcTamanytext = new JMenuItem("Tamany text");
    /** Ítem del menú Funció per a introduir la funció Truncar */
    private JMenuItem funcTruncar = new JMenuItem("Truncar");
    /** Ítem del menú Funció per a introduir la funció Variancia */
    private JMenuItem funcVariancia = new JMenuItem("Variancia");
    /** Guarda el valor a cercar en indicar l'opció de cercar */
    private String valACercar;
    /** Botó per a canviar el color de la selecció de cel·les */
    private ColorChooserButton bCanviarColor = new ColorChooserButton("Color Selecció");

    /**
     * Constructora de la finestra del document amb els fulls de càlcul. S'entra per paràmetre el nom del document i un
     * boolean conforme el document és nou o ha sigut carregat dels fitxers de l'ordinador
     * @param nomDocument
     * @param DocCarregat
     */
    public VistaSpreadsheet(String nomDocument, boolean DocCarregat) {
        // Finestra
        //setBounds(500, 300, 500, 300);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        //setLayout(new GridLayout(1, 0));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(true);
        setTitle(nomDocument);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                e.getWindow().dispose();
                CtrlPresentacio.tancarDocument();
            }
        });

        // Taula
        JTabbedPane pf = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
        PanellFulls panellFulls = new PanellFulls(pf, DocCarregat);
        panellFulls.setBounds(8, 50, 1420, 690);
        add(panellFulls, BorderLayout.CENTER);


        //Color
        bCanviarColor.addColorChangedListener(new ColorChooserButton.ColorChangedListener() {
            @Override
            public void colorChanged(Color newColor) {
                panellFulls.canviarColorCela(newColor);
            }
        });

        // Menús desplegables
        //// Fitxer
        fitxer.add(nou);
        fitxer.add(obre);
        guarda.add(aCSV);
        guarda.add(aPROP);
        fitxer.add(guarda);

        ActionListener lAfFila = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.addFila();
            }
        };

        ActionListener lAfCol = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.addColumna();
            }
        };

        ActionListener lAfIniFila = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.addFilaFinal();
            }
        };
        ActionListener lAfIniCol = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.addColumnaFinal();
            }
        };

        ActionListener lElFila = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panellFulls.delFila();
            }
        };

        ActionListener lElColumna = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panellFulls.delCol();
            }
        };

        ActionListener lObre = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.setDialogTitle("Selecciona fitxer");
                chooser.setFileFilter(new FileNameExtensionFilter("PROP", "csv", "prop"));
                chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/data/dades"));
                int returnValue = chooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File arxiu = chooser.getSelectedFile();
                    if (arxiu.getName().endsWith(".csv")) {
                        CtrlPresentacio.carregarCSV(arxiu.getAbsolutePath());
                        CtrlPresentacio.vistaSpreadsheet(arxiu.getName().substring(0, arxiu.getName().length() - 4), true); // test.csv -> test
                    } else if (arxiu.getName().endsWith(".prop")) {
                        CtrlPresentacio.carregaDocument(arxiu.getAbsolutePath());
                        CtrlPresentacio.vistaSpreadsheet(arxiu.getName().substring(0, arxiu.getName().length() - 5), true);
                    }
                    setVisible(false);
                } else if (returnValue == JFileChooser.CANCEL_OPTION) {}
            }
        };

        ActionListener lNou = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.vistaCreacioDocument();
                setVisible(false);
            }
        };

        ActionListener lCSV = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.guardaFullaCSV();
                JFrame frame = new JFrame();
                JDialog senseLoc =  new JDialog(frame, "Document guardat a CSV");
                senseLoc.setBounds(500, 300, 400, 200);
                senseLoc.setLayout(null);

                JLabel txtGuardatACSV = new JLabel("El document " + nomDocument + " s'ha guardat com a CSV");
                txtGuardatACSV.setBounds(50, 20, 400, 40);
                JButton botoSortirGuardatACSV = new JButton("Acceptar");
                botoSortirGuardatACSV.setVisible(true);
                botoSortirGuardatACSV.setBounds(150, 110, 100, 30);
                senseLoc.add(txtGuardatACSV);
                senseLoc.add(botoSortirGuardatACSV);
                senseLoc.setVisible(true);

                ActionListener lSortirErrorACSV = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        senseLoc.dispose();
                        senseLoc.setVisible(false);
                    }
                };
                botoSortirGuardatACSV.addActionListener(lSortirErrorACSV);
            }
        };

        ActionListener lPROP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.guardaPROP();
                JFrame frame = new JFrame();
                JDialog senseLoc =  new JDialog(frame, "Document guardat a .prop");
                senseLoc.setBounds(800, 300, 400, 200);
                senseLoc.setLayout(null);

                JLabel txtGuardatAPROP = new JLabel("El document " + nomDocument + " s'ha guardat a .prop");
                txtGuardatAPROP.setBounds(80, 20, 400, 40);
                JButton botoSortirGuardatAPROP = new JButton("Acceptar");
                botoSortirGuardatAPROP.setVisible(true);
                botoSortirGuardatAPROP.setBounds(150, 110, 100, 30);
                senseLoc.add(txtGuardatAPROP);
                senseLoc.add(botoSortirGuardatAPROP);
                senseLoc.setVisible(true);

                ActionListener lSortirErrorAPROP = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        senseLoc.dispose();
                        senseLoc.setVisible(false);
                    }
                };
                botoSortirGuardatAPROP.addActionListener(lSortirErrorAPROP);
            }
        };

        EventListener canviaValor = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }
            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                String n = barraCont.getText();
                panellFulls.setValorCela(n);
            }
        };

        ActionListener lCopiar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panellFulls.copiar();
            }
        };

        ActionListener lEnganxa = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panellFulls.enganxa();
            }
        };

        ActionListener lRetalla = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panellFulls.retalla();
            }
        };

        barraCont.addKeyListener((KeyListener) canviaValor);

        nou.addActionListener(lNou);
        obre.addActionListener(lObre);
        aCSV.addActionListener(lCSV);
        aPROP.addActionListener(lPROP);

        afFila.add(fIni);
        afFila.add(fPos);
        insereix.add(afFila);
        fIni.addActionListener(lAfIniFila);
        fPos.addActionListener(lAfFila);
        afColumna.add(cIni);
        afColumna.add(cPos);
        insereix.add(afColumna);
        cIni.addActionListener(lAfIniCol);
        cPos.addActionListener(lAfCol);

        //afFila.addActionListener(lAfFila);
        elFila.addActionListener(lElFila);
        //afColumna.addActionListener(lAfCol);
        elColumna.addActionListener(lElColumna);

        //// Insereix
        //// Elimina
        elimina.add(elColumna);
        elimina.add(elFila);
        //// Copia
        copia.addActionListener(lCopiar);
        //copia.addKeyListener(kCopiar);
        //// Enganxa
        enganxa.addActionListener(lEnganxa);
        //enganxa.addKeyListener(kEnganxa);
        //// Retalla
        retalla.addActionListener(lRetalla);
        //retalla.addMenuKeyListener(kRetalla);

        edita.add(copia);
        edita.add(enganxa);
        edita.add(retalla);


        //// Edita
        edita.add(copia);
        edita.add(enganxa);
        edita.add(retalla);

        edita.add(cerca);
        ActionListener lCerca = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valACercar = JOptionPane.showInputDialog(null, "Introdueix el valor a cercar:", "Cerca", JOptionPane.INFORMATION_MESSAGE);
                Vector<Pair<Integer, Integer>> v = null;
                if (valACercar != null) v = CtrlPresentacio.cercarValorPos(valACercar);
                JFrame framePosicions = new JFrame();
                JDialog posValCercat =  new JDialog(framePosicions, "Cerca");
                posValCercat.setBounds(500, 300, 400, 200);
                posValCercat.setLayout(null);

                JLabel txtPosValCercat = new JLabel("Les posicions on es troba el valor cercat són:");
                txtPosValCercat.setBounds(60, 20, 400, 40);
                //System.out.println(v);
                JLabel posicionsValCercat;
                if (v != null && v.size() != 0) posicionsValCercat = new JLabel(valorsCercatsAString(v));
                else posicionsValCercat = new JLabel("El valor cercat no es troba a la taula");
                //System.out.println(valorsCercatsAString(v));
                posicionsValCercat.setBounds(60, 40, 400, 40);
                JButton botoSortir = new JButton("Sortir");
                botoSortir.setVisible(true);
                botoSortir.setBounds(150, 110, 100, 30);
                posValCercat.add(txtPosValCercat);
                posValCercat.add(posicionsValCercat);
                posValCercat.add(botoSortir);
                if (v != null) posValCercat.setVisible(true);
                else posValCercat.setVisible(false);

                ActionListener lSortirFitxerEsborrat = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        posValCercat.dispose();
                        posValCercat.setVisible(false);
                    }
                };
                botoSortir.addActionListener(lSortirFitxerEsborrat);
            }
        };

        cerca.addActionListener(lCerca);


        edita.add(reemplaca);
        ActionListener lReemplacaValCela = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField valCercat = new JTextField();
                JTextField valReempl = new JTextField();
                Object[] message = {
                        "Valor cercat: ", valCercat,
                        "Reemplaça per: ", valReempl
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Reemplaça", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    panellFulls.reemplacaValor(valCercat.getText(), valReempl.getText());
                }
            }
        };

        reemplaca.addActionListener(lReemplacaValCela);

        edita.add(ordena);
        ActionListener lOrdena = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.ordenarBloc();
            }
        };
        ordena.addActionListener(lOrdena);

       /* ActionListener lOrdenaString = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.ordenaStrings();
            }
        };
        ActionListener lOrdenaIntegers = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panellFulls.ordenaIntegers();
            }
        };
        ordena.add(oString);
        ordena.add(oInteger);
        oString.addActionListener(lOrdenaString);
        oInteger.addActionListener(lOrdenaIntegers);*/

        //// Funció
        funcio.add(funcAMajuscula);
        ActionListener lAMajuscula = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=MAJUS()");
            }
        };
        funcAMajuscula.addActionListener(lAMajuscula);

        funcio.add(funcAbsolut);
        ActionListener lAbsolut = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=ABS()");
            }
        };
        funcAbsolut.addActionListener(lAbsolut);

        funcio.add(funcArrel);
        ActionListener lArrel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=ARREL()");
            }
        };
        funcArrel.addActionListener(lArrel);

        funcio.add(funcArrodonir);
        ActionListener lArrodonir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=ARRODONIR()");
            }
        };
        funcArrodonir.addActionListener(lArrodonir);

        funcio.add(funcBinariadecimal);
        ActionListener lBinariadecimal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=BIN2DEC()");
            }
        };
        funcBinariadecimal.addActionListener(lBinariadecimal);

        funcio.add(funcPearson);
        ActionListener lPearson = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=PEARSON()");
            }
        };
        funcPearson.addActionListener(lPearson);

        funcio.add(funcCovariancia);
        ActionListener lCovariancia = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=COVARIANCIA()");
            }
        };
        funcCovariancia.addActionListener(lCovariancia);

        funcio.add(funcDecimalabinari);
        ActionListener lDecimalabinari = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=DEC2BIN()");
            }
        };
        funcDecimalabinari.addActionListener(lDecimalabinari);

        funcio.add(funcDecimalahexadecimal);
        ActionListener lDecimalahexadecimal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=DEC2HEX()");
            }
        };
        funcDecimalahexadecimal.addActionListener(lDecimalahexadecimal);

        funcio.add(funcDesviacioestandar);
        ActionListener lDesviacioestandar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=DESVEST()");
            }
        };
        funcDesviacioestandar.addActionListener(lDesviacioestandar);

        funcio.add(funcDivisio);
        ActionListener lDivisio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=DIVISIO()");
            }
        };
        funcDivisio.addActionListener(lDivisio);

        //funcio.add(funcDoubleaint);

        funcio.add(funcFactorial);
        ActionListener lFactorial = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=FACTORIAL()");
            }
        };
        funcFactorial.addActionListener(lFactorial);

        funcio.add(funcHexadecimaladecimal);
        ActionListener lHexadecimaladecimal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=HEX2DEC()");
            }
        };
        funcHexadecimaladecimal.addActionListener(lHexadecimaladecimal);

        funcio.add(funcMediana);
        ActionListener lMediana = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=MEDIANA()");
            }
        };
        funcMediana.addActionListener(lMediana);

        funcio.add(funcMitjana);
        ActionListener lMitjana = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=MITJANA()");
            }
        };
        funcMitjana.addActionListener(lMitjana);

        funcio.add(funcMultiplicacio);
        ActionListener lMultiplicacio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=MULT()");
            }
        };
        funcMultiplicacio.addActionListener(lMultiplicacio);

        funcio.add(funcNumeroparaules);
        ActionListener lNumeroparaules = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=NUMPARAULES()");
            }
        };
        funcNumeroparaules.addActionListener(lNumeroparaules);

        funcio.add(funcObteany);
        ActionListener lObteany = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=OBTEANY()");
            }
        };
        funcObteany.addActionListener(lObteany);

        funcio.add(funcObtedia);
        ActionListener lObtedia = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=OBTEDIA()");
            }
        };
        funcObtedia.addActionListener(lObtedia);

        funcio.add(funcObtediasetmana);
        ActionListener lObtediasetmana = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=DIASETMANA()");
            }
        };
        funcObtediasetmana.addActionListener(lObtediasetmana);

        funcio.add(funcObtemes);
        ActionListener lObtemes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=OBTEMES()");
            }
        };
        funcObtemes.addActionListener(lObtemes);

        funcio.add(funcPotencia);
        ActionListener lPotencia = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=POW()");
            }
        };
        funcPotencia.addActionListener(lPotencia);

        funcio.add(funcReemplaca);
        ActionListener lReemplaca = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=REPLACE()");
            }
        };
        funcReemplaca.addActionListener(lReemplaca);

        funcio.add(funcResta);
        ActionListener lResta = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=RESTA()");
            }
        };
        funcResta.addActionListener(lResta);

        funcio.add(funcSuma);
        ActionListener lSuma = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=SUMA()");
            }
        };
        funcSuma.addActionListener(lSuma);

        funcio.add(funcTamanytext);
        ActionListener lTamanytext = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=TAMANY()");
            }
        };
        funcTamanytext.addActionListener(lTamanytext);

        funcio.add(funcTruncar);
        ActionListener lTruncar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=TRUNCAR()");
            }
        };
        funcTruncar.addActionListener(lTruncar);

        funcio.add(funcVariancia);
        ActionListener lVariancia = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panellFulls.setValorCela("=VARIANCIA()");
            }
        };
        funcVariancia.addActionListener(lVariancia);

        // Barra de menú
        mb.add(fitxer);
        mb.add(edita);
        mb.add(insereix);
        mb.add(elimina);
        mb.add(funcio);
        mb.add(bCanviarColor);
        setJMenuBar(mb);

        // Barra contingut cel·la
        barraCont.setBounds(16, 10, 1000, 30);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        barraCont.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        add(barraCont, BorderLayout.NORTH);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Retorna integer a string en Hexavigesimal (base26 en caràcters)
     * @param x
     * @return String en base26 a caràcters
     */
    public static String integerToHexavigesimal(int x) {
        x++; // Per a tenir númeració començant des de 0
        StringBuilder columnName = new StringBuilder();
        while (x > 0) {
            int rem = x%26;
            if (rem == 0) {
                columnName.append("Z");
                x = (x/26)-1;
            } else {
                columnName.append((char) ((rem - 1) + 'A'));
                x = x/26;
            }
        }
        return (columnName.reverse()).toString();
    }

    /**
     * Retorna una cadena de posicions del full de càlcul a partir del vector de posicions
     * @param v
     * @return String
     */
    public static String valorsCercatsAString(Vector<Pair<Integer, Integer>> v) {
        String res = "";
        for (int i = 0; i < v.size(); ++i) {
            if (i != 0) res = res + ',';
            String col = integerToHexavigesimal(v.get(i).first());
            String fil = String.valueOf(v.get(i).second() + 1);
            res = res + col + fil;
        }
        return res;
    }

    /**
     * Posa a la barra de continguts el contingut del String passat per paràmetre
     * @param n
     */
    public static void setBarraCont(String n) {
        barraCont.setText(n);
    }
}
