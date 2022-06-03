package main.presentation;

import main.domain.classes.types.Pair;
import main.domain.classes.types.TType;
import main.presentation.controllers.CtrlPresentacio;
import main.presentation.views.VistaSpreadsheet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

import static main.presentation.views.VistaSpreadsheet.valorsCercatsAString;

/**
 * La classe PanellFulls es tracta d’una reimplementació de l’objecte JTabbedPane que ens proporciona la llibreria
 * Swing de Java. Aquesta classe és l’encarregada de crear els panells que permeten la creació i canvi de fulls a
 * seleccionar. Dins d’aquests panells s’hi trobarà el full de càlcul determinat amb la capacitat de poder desplaçar-se
 * cap a totes direccions gràcies a les dos barres de desplaçament que ens aporta l’objecte JScrollPane de Swing.
 * @author Marc Clapés Marana (marc.clapes.marana@estudiantat.upc.edu)
 */
public class PanellFulls extends JTabbedPane {
    /** Número de panells de Full */
    private int numTabs;
    /** Estructura HashMap de Integer i DefaultTableModel per a guardar totes DefaultTableModel dels fulls creats */
    private HashMap <Integer, DefaultTableModel> doc = new HashMap<>();
    /** Estructura HashMap de Integer i JTable per a guardar tots els JTable dels fulls creats */
    private HashMap <Integer, JTable> doc1 = new HashMap<>();
    /** Guarda el valor String copiat */
    private String copia;
    /** Determina si s'ha copiat una cel·la o un bloc */
    private String copiat;
    /** Guarda el contingut de les cel·les copiades/retallades */
    private ArrayList<ArrayList<String>> c;
    /** Guarda l'índex del full on es copia/retalla contingut */
    private int FullCopiat;

    /**
     * Constuctora de PanellFulls
     * @param pn
     * @param docCarregat
     */
    public PanellFulls(JTabbedPane pn, boolean docCarregat) {
        setTabPlacement(pn.getTabPlacement());
        setTabLayoutPolicy(pn.getTabLayoutPolicy());
        createJTabbedPane();
        if (docCarregat) carregaValorCeles();
    }

    /**
     * Setter d'una cel·la de la taula amb el contingut String passat per paràmetre
     * @param s
     */
    public void setValorCela(String s) {
        JTable taula = doc1.get(getSelectedIndex());
        int cuentaFilasSeleccionadas = taula.getSelectedRowCount();
        int cuentaColSeleccionadas = taula.getSelectedColumnCount();

        if (cuentaFilasSeleccionadas == 1 && cuentaColSeleccionadas == 1) {
            int i = taula.getSelectedRow();
            int j = taula.getSelectedColumn();
            taula.setValueAt(s, i, j);
            if (s.length() != 0 && CtrlPresentacio.getType(s) != TType.FUNCTION) {
                CtrlPresentacio.modificarCela(j, i, (String) taula.getValueAt(i, j));
            }
        }
        int i = taula.getEditingRow();
        int j = taula.getEditingColumn();
        if (i != -1 && j != -1) {
            int i1 = taula.getSelectedRow();
            int j1 = taula.getSelectedColumn();
            taula.removeEditor();
            taula.setValueAt(s, i1, j1);
            if (s.length() != 0 && CtrlPresentacio.getType(s) != TType.FUNCTION) {
                CtrlPresentacio.modificarCela(j1, i1, (String) taula.getValueAt(i1, j1));
            }
            taula.editCellAt(i,j);
        }

    }

    /**
     * Es creen dos JTabbedPane. Un que inclou el primer full que es crea predeterminadament al crear el document amb
     * el nom de "Full 1" i un altre amb nom "+" que al fer-li clic crear un nou JTabbedPane amb un nou Full.
     */
    private void createJTabbedPane() {
        addChangeListener(canviFull);
        add(createJPanel(0), "Full " + (numTabs + 1), numTabs++);
        setTabComponentAt(0, new CustomTab(this));
        add(new JPanel(), "+", numTabs++);
        addChangeListener(changeListener);
    }

    /**
     * Crea un panell JScrollPane que inclou un objecte TaulaFull. Es pot fer scroll en totes direccions per a veure el
     * contingut complet del Full.
     * @param i
     * @return JScrollPane
     */
    private JScrollPane createJPanel(int i) {
        DefaultTableModel dtm = new DefaultTableModel(CtrlPresentacio.getNumFiles(), CtrlPresentacio.getNumColumnes());
        JTable taula = new JTable(dtm);
        taula.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        taula.setCellSelectionEnabled(true);
        taula.addKeyListener(tableKeyListener);
        taula.addKeyListener(canviaValor);
        ListSelectionModel event = taula.getSelectionModel();
        event.addListSelectionListener(getSelectionModel);
        JScrollPane sp = new JScrollPane(taula, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JTable t = new TaulaFull(taula);
        sp.setRowHeaderView(t);
        sp.setCorner(JScrollPane.UPPER_LEFT_CORNER, t.getTableHeader());
        doc.put(i, dtm);
        doc1.put(i, taula);
        //doc1.put(i, (TaulaFull) t);
        return sp;
    }

    KeyListener canviaValor = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }
        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            JTable taula = doc1.get(getSelectedIndex());
            int i = taula.getEditingRow();
            int j = taula.getEditingColumn();
            if (i != -1 && j != -1 && taula.getValueAt(i,j) != null && CtrlPresentacio.getType((String) taula.getValueAt(i, j)) != TType.FUNCTION) {
                CtrlPresentacio.modificarCela(j, i, (String) taula.getValueAt(i, j));
                VistaSpreadsheet.setBarraCont((String) taula.getValueAt(i, j));
            }
            int i1 = taula.getEditingRow();
            int j1 = taula.getEditingColumn();
            if (i1 != -1 && j1 != -1 && taula.getValueAt(i1, j1) != null) {
                if (CtrlPresentacio.getType((String) taula.getValueAt(i, j)) != TType.FUNCTION && CtrlPresentacio.getType(CtrlPresentacio.obtenirValorCela(j,i, getSelectedIndex()+1)) != TType.FUNCTION) {
                    CtrlPresentacio.modificarCela(j, i, (String) taula.getValueAt(i, j));
                }
                VistaSpreadsheet.setBarraCont(CtrlPresentacio.obtenirValorCela(j, i, getSelectedIndex() + 1));
            }


        }
    };

    //El método valueChange se debe sobreescribir obligatoriamente.
    //Se ejecuta automáticamente cada vez que se hace una nueva selección.
    ListSelectionListener getSelectionModel = e -> {
        JTable taula = doc1.get(getSelectedIndex());
        //Obtener el número de filas seleccionadas
        int cuentaFilasSeleccionadas = taula.getSelectedRowCount();
        int cuentaColSeleccionadas = taula.getSelectedColumnCount();
        if (cuentaFilasSeleccionadas == 1 && cuentaColSeleccionadas == 1) {
            int i = taula.getSelectedRow();
            int j = taula.getSelectedColumn();

            if ((taula.getValueAt(i,j)) != null) {
                String s = (String) taula.getValueAt(i, j);
                String p = CtrlPresentacio.obtenirValorCela(j,i, getSelectedIndex()+1);
                if (CtrlPresentacio.getType(s) != TType.FUNCTION && CtrlPresentacio.getType(p) != TType.FUNCTION) {
                    CtrlPresentacio.modificarCela(j, i, (String) taula.getValueAt(i, j));
                }
                VistaSpreadsheet.setBarraCont(CtrlPresentacio.obtenirValorCela(j, i, getSelectedIndex() + 1));
            }
            else {
                VistaSpreadsheet.setBarraCont(null);
            }

        }
        else {
            VistaSpreadsheet.setBarraCont(null);
        }
    };
    KeyListener tableKeyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                JTable taula = doc1.get(getSelectedIndex());
                int i = taula.getSelectedRow();
                int j = taula.getSelectedColumn();
                if (i != -1 && j != -1 && taula.getValueAt(i,j) != null) {
                    CtrlPresentacio.modificarCela(j, i, (String) taula.getValueAt(i, j));
                    taula.setValueAt(CtrlPresentacio.obtenirValorRealCela(j, i, getSelectedIndex() + 1), i, j);
                    VistaSpreadsheet.setBarraCont(CtrlPresentacio.obtenirValorCela(j, i, getSelectedIndex() + 1));
                }
                else {
                    VistaSpreadsheet.setBarraCont(null);
                }
                int i1 = taula.getEditingRow();
                int j1 = taula.getEditingColumn();
                if (i1 != -1 && j1 != -1 && taula.getValueAt(i1, j1) != null) {
                    CtrlPresentacio.modificarCela(j, i, (String) taula.getValueAt(i, j));
                    taula.setValueAt(CtrlPresentacio.obtenirValorRealCela(j, i, getSelectedIndex() + 1), i, j);
                    VistaSpreadsheet.setBarraCont(CtrlPresentacio.obtenirValorCela(j, i, getSelectedIndex() + 1));
                }
            }
            if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
                copiar();
            }
            else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
                enganxa();
            }
            else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_X) {
                retalla();
            }
            else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F) {
                String valACercar = JOptionPane.showInputDialog(null, "Introdueix el valor a cercar:", "Cerca", JOptionPane.INFORMATION_MESSAGE);
                Vector<Pair<Integer, Integer>> v = CtrlPresentacio.cercarValorPos(valACercar);
                JFrame framePosicions = new JFrame();
                JDialog posValCercat =  new JDialog(framePosicions, "Cerca");
                posValCercat.setBounds(500, 300, 400, 200);
                posValCercat.setLayout(null);

                JLabel txtPosValCercat = new JLabel("Les posicions on es troba el valor cercat són:");
                txtPosValCercat.setBounds(60, 20, 400, 40);
                JLabel posicionsValCercat;
                if (v.size() != 0) posicionsValCercat = new JLabel(valorsCercatsAString(v));
                else posicionsValCercat = new JLabel("El valor cercat no es troba a la taula");
                posicionsValCercat.setBounds(60, 40, 400, 40);
                JButton botoSortir = new JButton("Sortir");
                botoSortir.setVisible(true);
                botoSortir.setBounds(150, 110, 100, 30);
                posValCercat.add(txtPosValCercat);
                posValCercat.add(posicionsValCercat);
                posValCercat.add(botoSortir);
                posValCercat.setVisible(true);
                ActionListener lSortirFitxerEsborrat = e1 -> {
                    posValCercat.dispose();
                    posValCercat.setVisible(false);
                };
                botoSortir.addActionListener(lSortirFitxerEsborrat);
            }
            else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R) {

                JTextField valCercat = new JTextField();
                JTextField valReempl = new JTextField();
                Object[] message = {
                        "Valor cercat: ", valCercat,
                        "Reemplaça per: ", valReempl
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Reemplaça", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    reemplacaValor(valCercat.getText(), valReempl.getText());

                }
            }
        }
    };

    ChangeListener changeListener = e -> addNewTab();

    ChangeListener canviFull = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() instanceof JTabbedPane) {
                if (getSelectedIndex() != numTabs) {
                    CtrlPresentacio.setFullActiu(getSelectedIndex() + 1);
                }
            }
        }
    };

    /** S'afegeix una fila a sobre de la cel·la del full seleccionat */
    public void addFila() {

        DefaultTableModel t = doc.get(getSelectedIndex());
        JTable taula = doc1.get(getSelectedIndex());
        if (taula.getSelectedRowCount() == 1) { // Una sola cel·la
            int i = taula.getSelectedRow();
            CtrlPresentacio.afegirFilaFull(i, getSelectedIndex() + 1);
            t.addRow(new Object[]{});
            t.moveRow(taula.getRowCount() - 1, taula.getRowCount() - 1, i);
            taula.setModel(t);
            JOptionPane.showMessageDialog(null, "Fila afegida amb èxit");
        }
        else if (taula.getSelectedRowCount() > 1){  // Bloc
            JOptionPane.showMessageDialog(null, "Masses cel·les seleccionades");
        }
        else {  // Cap
            addFilaFinal();
        }
    }

    /** S'afegeix una fila a l'inici del full seleccionat */
    public void addFilaFinal() {
        int numFiles = CtrlPresentacio.getNumFiles();
        DefaultTableModel t = doc.get(getSelectedIndex());
        JTable taula = doc1.get(getSelectedIndex());
        // Afegeix fila al final del document (domini)
        CtrlPresentacio.afegirFilaFull(numFiles, getSelectedIndex() + 1);
        // Afegeix fila a presentacio
        t.addRow(new Object[]{});
        t.moveRow(taula.getRowCount() - 1, taula.getRowCount() - 1, numFiles);
        taula.setModel(t);
        JOptionPane.showMessageDialog(null, "Fila afegida amb èxit");
    }

    /** S'afegeix una columna a la esquerra de la cel·la del full seleccionat */
    public void addColumna() {
        DefaultTableModel dtm = doc.get(getSelectedIndex());
        JTable taula = doc1.get(getSelectedIndex());
        if (taula.getSelectedColumnCount() == 1) {
            int j = taula.getSelectedColumn();
            CtrlPresentacio.afegirColumnaFull(j, getSelectedIndex() + 1);
            taula.setAutoCreateColumnsFromModel(false);
            TableColumn col = new TableColumn(dtm.getColumnCount());
            col.setHeaderValue(dtm.getColumnName(taula.getSelectedColumn()));
            taula.addColumn(col);
            dtm.addColumn(dtm.getColumnName(dtm.getColumnCount()), new Object[] {});
            taula.moveColumn(taula.getColumnCount() - 1, j);

            // Actualitza headers
            for (int i = j+1; i < taula.getColumnCount(); ++i) {
                JTableHeader head = taula.getTableHeader();
                TableColumnModel model = head.getColumnModel();
                TableColumn coli = model.getColumn(i);
                coli.setHeaderValue(dtm.getColumnName(i));
                head.repaint();
            }
            JOptionPane.showMessageDialog(null, "Columna afegida amb èxit");
        }
        else if (taula.getSelectedColumnCount() > 1){
            JOptionPane.showMessageDialog(null, "Masses cel·les seleccionades");
        }
        else {  // AFEGIR COL AL FINAL
            addColumnaFinal();
            //JOptionPane.showMessageDialog(null, "No hi ha cap cel·la seleccionada");
        }
    }

    /** S'afegeix una columna a l'inici del full seleccionat */
    public void addColumnaFinal() {
        DefaultTableModel dtm = doc.get(getSelectedIndex());
        JTable taula = doc1.get(getSelectedIndex());
        // Afegeix columna al final a domini
        int ultimaCol = CtrlPresentacio.getNumColumnes();
        CtrlPresentacio.afegirColumnaFull(ultimaCol, getSelectedIndex() + 1);
        taula.setAutoCreateColumnsFromModel(false);
        TableColumn col = new TableColumn(dtm.getColumnCount());
        col.setHeaderValue(dtm.getColumnName(ultimaCol));
        taula.addColumn(col);
        dtm.addColumn(dtm.getColumnName(dtm.getColumnCount()), new Object[] {});
        //taula.moveColumn(taula.getColumnCount() - 1, 0);
        /*
        for (int i = 1; i < taula.getColumnCount(); ++i) {
            JTableHeader head = taula.getTableHeader();
            TableColumnModel model = head.getColumnModel();
            TableColumn coli = model.getColumn(i);
            coli.setHeaderValue(dtm.getColumnName(i));
            head.repaint();
        }
        */
        JOptionPane.showMessageDialog(null, "Columna afegida amb èxit");
    }

    /** S'elimina la fila de la cel·la seleccionada del full seleccionat */
    public void delFila() {
        DefaultTableModel dtm = doc.get(getSelectedIndex());
        JTable taula = doc1.get(getSelectedIndex());
        if (taula.getSelectedRow() != -1) {
            CtrlPresentacio.eliminarFilaFull(taula.getSelectedRow(), getSelectedIndex() + 1);
            dtm.removeRow(taula.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Fila eliminada amb èxit");
        } else {
            JOptionPane.showMessageDialog(null, "No hi ha cap fila seleccionada");
        }
    }

    /** S'elimina la columna de la cel·la seleccionada del full seleccionat */
    public void delCol() {
        DefaultTableModel dtm = doc.get(getSelectedIndex());
        JTable taula = doc1.get(getSelectedIndex());
        if (taula.getSelectedColumn() != -1) {
            CtrlPresentacio.eliminarColumnaFull(taula.getSelectedColumn(), getSelectedIndex() + 1);
            int n = taula.getColumnCount();
            int j = taula.getSelectedColumn();
            for (int i = j+1; i < n; ++i) {
                JTableHeader head = taula.getTableHeader();
                TableColumnModel model = head.getColumnModel();
                TableColumn coli = model.getColumn(i);
                coli.setHeaderValue(dtm.getColumnName(i-1));
                head.repaint();
            }
            taula.setAutoCreateColumnsFromModel(false);
            JTableHeader head = taula.getTableHeader();
            TableColumnModel tcm = head.getColumnModel();
            TableColumn col = tcm.getColumn(j);
            taula.removeColumn(col);

            JOptionPane.showMessageDialog(null, "Columna eliminada amb èxit");
        } else {
            JOptionPane.showMessageDialog(null, "No hi ha cap columna seleccionada");
        }
    }

    /** Retalla el bloc/cel·la seleccionada */
    public void copiar() {
        JTable taula = doc1.get(getSelectedIndex());
        if (taula.getSelectedRowCount() == 1 && taula.getSelectedColumnCount() == 1) {
            int iF = taula.getSelectedRow();
            int iC = taula.getSelectedColumn();
            CtrlPresentacio.copiaBloc(iC, iF, iC, iF);
            //copia = CtrlPresentacio.obtenirValorRealCela(iC, iF, getSelectedIndex()+1);
            copiat = "cela";
            c = CtrlPresentacio.getClipboard();
            FullCopiat = getSelectedIndex();
        }
        else {
            int iC1 = taula.getSelectedColumn();
            int iF1 = taula.getSelectedRow();
            int iC2 = taula.getSelectedColumn() + taula.getSelectedColumnCount();
            int iF2 = taula.getSelectedRowCount() + taula.getSelectedRow();
            CtrlPresentacio.copiaBloc(iC1, iF1, iC2, iF2);
            c = CtrlPresentacio.getClipboard();
            copiat = "bloc";
            FullCopiat = getSelectedIndex()+1;
        }
    }

    /** Enganxa a partir de la cel·la seleccionada l'últim que s'ha copiat o retallat */
    public void enganxa() {
        JTable taula = doc1.get(getSelectedIndex());
        int i = taula.getSelectedRow();
        int j = taula.getSelectedColumn();
        if (Objects.equals(copiat, "cela")) {
            if (taula.getValueAt(i,j) == null || taula.getValueAt(i,j).toString().length() == 0) {
                //CtrlPresentacio.modificarCela(j, i, copia);
                CtrlPresentacio.enganxa(j, i);
                //System.out.println("a " + j + '-' + i + " enganxa un " + CtrlPresentacio.obtenirValorRealCela(j, i, getSelectedIndex() + 1));
                taula.setValueAt(CtrlPresentacio.obtenirValorRealCela(j, i, getSelectedIndex() + 1), i, j);
                VistaSpreadsheet.setBarraCont(CtrlPresentacio.obtenirValorCela(j, i, getSelectedIndex() + 1));
            } else if (taula.getValueAt(i, j) != null) {
                //System.out.println("aquí hi ha un " + taula.getValueAt(i, j));
                String s = (String) taula.getValueAt(i, j);
                if (s.length() > 0) {
                    int option = JOptionPane.showConfirmDialog(null, "Cel·la ja té un valor, vols sobreescriure'l?", "Enganxa Cel·la", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        //CtrlPresentacio.modificarCela(j, i, copia);
                        CtrlPresentacio.enganxa(j, i);
                        taula.setValueAt(CtrlPresentacio.obtenirValorRealCela(j, i, getSelectedIndex() + 1), i, j);
                        VistaSpreadsheet.setBarraCont(CtrlPresentacio.obtenirValorCela(j, i, getSelectedIndex() + 1));
                    }
                }
            }
        }
        else if (Objects.equals(copiat, "bloc")) {

            int nc = CtrlPresentacio.getNCol();
            int nf = CtrlPresentacio.getNFil();

            if (nc+taula.getSelectedColumn() > taula.getColumnCount() || nf+taula.getSelectedRow() > taula.getRowCount()) {
                JOptionPane.showMessageDialog(null, "Valor copiat no hi cap");
            }
            else {
                boolean buit = true;
                for (j = taula.getSelectedColumn(); j < nc + taula.getSelectedColumn(); ++j) {
                    for (i = taula.getSelectedRow(); i < nf + taula.getSelectedRow(); ++i) {
                        if (taula.getValueAt(i, j) != null) {
                            String s = (String) taula.getValueAt(i, j);
                            if (s.length() > 0) {
                                buit = false;
                            }
                        }
                    }
                }
                if (buit) {
                    j = taula.getSelectedColumn();
                    i = taula.getSelectedRow();
                    CtrlPresentacio.enganxa(j, i);
                    for (j = taula.getSelectedColumn(); j < nc + taula.getSelectedColumn(); ++j) {
                        for (i = taula.getSelectedRow(); i < nf + taula.getSelectedRow(); ++i) {
                            taula.setValueAt(CtrlPresentacio.obtenirValorRealCela(j, i, FullCopiat), i, j);
                        }
                    }
                }
                else {

                    int option = JOptionPane.showConfirmDialog(null, "Alguna cel·la on s'enganxarà el bloc copiat ja té un valor, vols sobreescriure'l?", "Enganxa Bloc", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        j = taula.getSelectedColumn();
                        i = taula.getSelectedRow();
                        CtrlPresentacio.enganxa(j, i);
                        for (j = taula.getSelectedColumn(); j < nc + taula.getSelectedColumn(); ++j) {
                            for (i = taula.getSelectedRow(); i < nf + taula.getSelectedRow(); ++i) {
                                taula.setValueAt(CtrlPresentacio.obtenirValorRealCela(j, i, FullCopiat), i, j);
                            }
                        }
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No hi ha res copiat");
        }
    }

    /** Retalla el bloc/cel·la seleccionada */
    public void retalla() {
        JTable taula = doc1.get(getSelectedIndex());
        if (taula.getSelectedRowCount() == 1 && taula.getSelectedColumnCount() == 1) {
            int iF = taula.getSelectedRow();
            int iC = taula.getSelectedColumn();
            copia = CtrlPresentacio.obtenirValorRealCela(iC, iF, getSelectedIndex()+1);
            CtrlPresentacio.modificarCela(iC, iF, "");
            taula.setValueAt(null, iF, iC);
            VistaSpreadsheet.setBarraCont(null);
            FullCopiat = getSelectedIndex()+1;
            copiat = "cela";
        }
        else {

            int iC1 = taula.getSelectedColumn();
            int iF1 = taula.getSelectedRow();
            int iC2 = taula.getSelectedColumn() + taula.getSelectedColumnCount();
            int iF2 = taula.getSelectedRowCount() + taula.getSelectedRow();
            CtrlPresentacio.retallaBloc(iC1, iF1, iC2, iF2);
            c = CtrlPresentacio.getClipboard();
            for (int i = iC1; i < iC2; ++i) {
                for (int j = iF1; j < iF2; ++j  ) {
                    taula.setValueAt(null, j, i);
                    VistaSpreadsheet.setBarraCont(null);
                }
            }
            copiat = "bloc";
            FullCopiat = getSelectedIndex()+1;
        }
    }

    /** Afegeix un panell de full al document amb nom "Full n" on n és el número de l'últim full creat més 1 */
    private void addNewTab() {
        int index = numTabs - 1;
        if (getSelectedIndex() == index) { /* if click new tab */
            CtrlPresentacio.setFullActiu(getSelectedIndex());
            CtrlPresentacio.crearFull("Full " + index, CtrlPresentacio.getNumColumnes(), CtrlPresentacio.getNumFiles());
            /* add new tab */
            add(createJPanel(index), "Full " + (index + 1), index);
            /* set tab is custom tab */
            setTabComponentAt(index, new CustomTab(this));
            removeChangeListener(changeListener);
            setSelectedIndex(index);
            addChangeListener(changeListener);
            numTabs++;
        }
    }

    private void addNewTabCarrega() {
        int index = numTabs - 1;
        //CtrlPresentacio.setFullActiu(getSelectedIndex());
        //CtrlPresentacio.crearFull("Full " + index, CtrlPresentacio.getNumColumnes(), CtrlPresentacio.getNumFiles());
        /* add new tab */
        add(createJPanel(index), "Full " + (index + 1), index);
        /* set tab is custom tab */
        setTabComponentAt(index, new CustomTab(this));
        removeChangeListener(changeListener);
        //setSelectedIndex(index);
        addChangeListener(changeListener);
        numTabs++;
    }

    /**
     * S'esborra un panell de full amb l'índex passat per paràmetre
     * @param index
     */
    public void removeTab(int index) {
        remove(index);
        numTabs--;

        if (index == numTabs - 1 && index > 0) {
            setSelectedIndex(numTabs - 2);
        } else {
            setSelectedIndex(index);
        }

        if (numTabs == 1) {
            addNewTab();
        }
    }

    /** Guarda el Full seleccionat a format .csv */
    public void guardaFullaCSV() {
        CtrlPresentacio.guardaCSV(getSelectedIndex() + 1);
    }

    /** Guarda el Full seleccionat a format .prop */
    public void guardaPROP() {
        CtrlPresentacio.guardaDocument();
    }

    /** Afegeix valor a totes les cel·les al Full amb els valors de la capa de domini */
    private void carregaValorCeles() {
        int numFulls = CtrlPresentacio.getNumFulls();
        for (int k = 0; k < numFulls; ++k) {
            
            JTable taula = doc1.get(k);
            if (taula == null) {
                addNewTabCarrega();
            }
            //System.err.println(k);
            taula = doc1.get(k);
            for (int i = 0; i < CtrlPresentacio.getNumFiles(); ++i) {
                for (int j = 0; j < CtrlPresentacio.getNumColumnes(); ++j) {
                    taula.setValueAt(CtrlPresentacio.obtenirValorRealCela(j, i, k + 1), i, j);
                }
            }
        }
    }

    /**
     * Reemplaça el valor cercat per reempl dins de les cel·les seleccionades
     * @param cercat
     * @param reempl
     */
    public void reemplacaValor(String cercat, String reempl) {
        JTable taula = doc1.get(getSelectedIndex());
        int idCini = taula.getSelectedColumn();
        int idFini = taula.getSelectedRow();
        int idCfin = taula.getSelectedColumn() + taula.getSelectedColumnCount() - 1;
        int idFfin = taula.getSelectedRow() + taula.getSelectedRowCount() - 1;
        CtrlPresentacio.reemplacarValor(idCini, idFini, idCfin, idFfin, cercat, reempl);
        for (int i = idFini; i <= idFfin; ++i) {
            for (int j = idCini; j <= idCfin; ++j) {
                taula.setValueAt(CtrlPresentacio.obtenirValorRealCela(j, i, getSelectedIndex() + 1), i, j);
            }
        }
    }

    /** Ordena el bloc de cel·les seleccionat */
    public void ordenarBloc() {
        JTable taula = doc1.get(getSelectedIndex());
        int idCini = taula.getSelectedColumn();
        int idFini = taula.getSelectedRow();
        int idCfin = taula.getSelectedColumn() + taula.getSelectedColumnCount() - 1;
        int idFfin = taula.getSelectedRow() + taula.getSelectedRowCount() - 1;
        CtrlPresentacio.ordenarBloc(idCini, idFini, idCfin, idFfin);
        for (int i = idFini; i <= idFfin; ++i) {
            for (int j = idCini; j <= idCfin; ++j) {
                taula.setValueAt(CtrlPresentacio.obtenirValorRealCela(j, i, getSelectedIndex() + 1), i, j);
            }
        }
    }

    /**
     * Canviar el color de selecció de cel·les al Full seleccionat
     * @param newColor
     */
    public void canviarColorCela(Color newColor) {
        JTable taula = doc1.get(getSelectedIndex());
        taula.setSelectionBackground(newColor);
        //taula.setColorRenderer(newColor);
    }

}
