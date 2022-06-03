package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;
import java.io.File;

/**
 * Aquesta vista és l’encarregada de crear un document. S’indica clarament 4 camps de text per indicar la localització
 * del document (path), el nom del document, les files i les columnes que es vol tenir inicialment en els fulls.
 * Només es pot crear document si no s’indica almenys la localització on estarà guardat, si no és així salta un
 * missatge d’error conforme no s’ha indicat el path. La localització del document es pot indicar manualment escrita
 * a la barra de text o es pot seleccionar el directori fent clic al botó triar directori, on s’obre l’explorador de
 * documents i es pot seleccionar el directori a guardar el document. A sota de la vista s’hi proporciona també un botó
 * per a tornar al menú principal.
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class VistaCreacioDocument extends JFrame {
    /** Finestra de selecció del directori on es vol crear el document creat */
    private JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    /** Panell on s'inclou tots els elements de la finestra */
    private JPanel lamina = new JPanel();
    /** Panell on s'inclou tots els elements de la pantalla */
    private JLabel titolVista = new JLabel("Crear Document");
    /** Botó de crear document */
    private JButton crear = new JButton("CREAR");
    /** Botó de tornar a la pantalla del menú principal */
    private JButton sortir = new JButton("ENRERE");

    /** Botó per a obrir l'explorador d'arxius i poder seleccionar el directori on guardar el document */
    private JButton bTriarDirectori = new JButton("triar directori");
    /** Text indicant que la barra de text del costat és per a introduir la localització del directori on poder guardar el document */
    private JLabel txtPath = new JLabel("PATH:");
    /** Àrea de text per a introduir la localització del directori on poder guardar el document */
    private JTextArea areaPath = new JTextArea();
    /** Text indicant que la barra de text del costat és per a introduir el nom del document que es vol crear */
    private JLabel txtNom = new JLabel("NOM DOCUMENT:");
    /** Àrea de text per a introduir el nom del document que es vol crear */
    private JTextArea areaNom = new JTextArea();
    /** Text indicant que la barra de text del costat és per a introduir el número de columnes del document que es vol crear */
    private JLabel txtColumnes = new JLabel("COLUMNES:");
    /** Àrea de text per a introduir el número de columnes del document que es vol crear */
    private JTextArea areaColumnes = new JTextArea();
    /** Text indicant que la barra de text del costat és per a introduir el número de files del document que es vol crear */
    private JLabel txtFiles = new JLabel("FILES:");
    /** Àrea de text per a introduir el número de files del document que es vol crear */
    private JTextArea areaFiles = new JTextArea();

    /** Pantalla d'error que apareix quan es vol crear un document sense path */
    private JFrame frame = new JFrame ("JFrame");

    /** Constructora de la finestra de creació de document */
    public VistaCreacioDocument() {
        // Finestra
        setBounds(500, 300, 500, 300);
        //setExtendedState(Frame.MAXIMIZED_BOTH);
        setResizable(true);
        setTitle("Nou Document");

        // Títol finestra
        titolVista.setBounds(10, 5, 120, 30);
        add(titolVista);


        // Botó crear
        crear.setBounds(150, 200, 200, 20);
        add(crear);

        // Botó sortir
        sortir.setBounds(150, 240, 200, 20);
        add(sortir);

        // Botó triar directori
        bTriarDirectori.setBounds(385, 35, 90, 20);//(380, 35, 90, 20);
        add(bTriarDirectori);


        // text del path que apareix quan al seleccionar el directori
        areaPath.setBounds(175, 35, 200, 20);
        add(areaPath);

        // Area text Nom Document
        areaNom.setBounds(175,75, 200,20);
        add(areaNom);


        KeyAdapter keycol = new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if((caracter < '0') || (caracter > '9'))
                {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        };
        KeyListener keyfil = new KeyListener()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if((caracter < '0') || (caracter > '9'))
                {
                    e.consume();  // ignorar el evento de teclado
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    crear.doClick();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {


            }
        };

        // Area text Número Columnes
        areaColumnes.addKeyListener(keycol);
        areaColumnes.setBounds(175,115, 200,20);
        add(areaColumnes);

        // Area text Número Files
        areaFiles.addKeyListener(keyfil);
        areaFiles.setBounds(175,155, 200,20);
        add(areaFiles);


        // Text Path
        txtPath.setBounds(50, 35, 200, 20);
        add(txtPath);

        // Text Nom Document
        txtNom.setBounds(50, 75, 200, 20);
        add(txtNom);

        // Text Número Columnes
        txtColumnes.setBounds(50, 115, 200, 20);
        add(txtColumnes);

        // Text Número Files
        txtFiles.setBounds(50, 155, 200, 20);
        add(txtFiles);


        add(lamina);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListener lTriarDocument = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Posar els formats que ens facin falta
                chooser.setDialogTitle("Selecciona directori");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/data/dades"));
                int returnValue = chooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File arxiu = chooser.getSelectedFile();
                    areaPath.setText(arxiu.getPath());
                    areaPath.setBounds(175, 35, 200, 20);
                    //CtrlPresentacio.carregarDirectori(arxiu.getAbsolutePath());
                } else if (returnValue == JFileChooser.CANCEL_OPTION) {
                    CtrlPresentacio.vistaCreacioDocument();
                }
            }
        };

        ActionListener lCrear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int files = 0;
                int columnes = 0;
                if (areaColumnes.getText().length() == 0) columnes = 50;
                else columnes = Integer.parseInt(areaColumnes.getText());

                if (areaFiles.getText().length() == 0) files = 50;
                else files = Integer.parseInt(areaFiles.getText());


                if (areaPath.getText().length() == 0){
                    JDialog senseLoc =  new JDialog(frame, "Error: no Path");
                    senseLoc.setBounds(800, 300, 400, 200);
                    senseLoc.setLayout(null);

                    JLabel txtErrorPath = new JLabel("S'ha d'especificar el path del Document");
                    txtErrorPath.setBounds(80, 20, 400, 40);
                    JButton botoSortirErrorPath = new JButton("Sortir");
                    botoSortirErrorPath.setVisible(true);
                    botoSortirErrorPath.setBounds(150, 110, 100, 30);
                    senseLoc.add(txtErrorPath);
                    senseLoc.add(botoSortirErrorPath);
                    senseLoc.setVisible(true);

                    ActionListener lSortirErrorPath = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            senseLoc.dispose();
                            senseLoc.setVisible(false);
                        }
                    };

                    botoSortirErrorPath.addActionListener(lSortirErrorPath);

                } else if (areaNom.getText().length() == 0) {
                    CtrlPresentacio.crearDocument(areaPath.getText(), "Sense_titol");
                    CtrlPresentacio.crearFull("Full 1", columnes , files);
                    CtrlPresentacio.vistaSpreadsheet("Sense_titol", false);
                    setVisible(false);

                } else { // s'ha introduït un nom
                    CtrlPresentacio.crearDocument(areaPath.getText(), areaNom.getText());
                    CtrlPresentacio.crearFull(columnes , files);
                    CtrlPresentacio.vistaSpreadsheet(CtrlPresentacio.getNomDocument(), false);
                    setVisible(false);
                }


            }
        };

        ActionListener lEnrere = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresentacio.iniPresentacio();
                setVisible(false);
            }
        };

        bTriarDirectori.addActionListener(lTriarDocument);
        crear.addActionListener(lCrear);
        sortir.addActionListener(lEnrere);
    }
}
