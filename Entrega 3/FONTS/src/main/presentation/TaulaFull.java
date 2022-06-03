package main.presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * La classe TaulaFull es tracta d’una reimplementació de l’objecte JTable de Swing. S’encarrega de crear una taula amb
 * la capacitat de que les files vagin ordenades numèricament, les columnes alfabèticament i que es pugui redimensionar
 * les columnes sense problemes. També permet poder seleccionar únicament una cel·la i no una fila sencera com és en cas
 * de l’objecte original JTable.
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class TaulaFull extends JTable implements ChangeListener, PropertyChangeListener, TableModelListener  {
    /** Taula JTable que representa el full */
    private JTable main;

    private NumeradorDeFiles nf = new NumeradorDeFiles();

    /**
     * Constructora de TaulaFull on reimplementa el JTable passat per paràmetre
     * @param table
     */
    public TaulaFull(JTable table) {
        main = table;
        main.addPropertyChangeListener(this);
        main.getModel().addTableModelListener(this);

        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setFocusable(false);
        setAutoCreateColumnsFromModel(false);
        setSelectionModel( main.getSelectionModel() );

        TableColumn column = new TableColumn();
        column.setHeaderValue(" ");
        addColumn(column);
        column.setCellRenderer(nf);

        getColumnModel().getColumn(0).setPreferredWidth(50);
        setPreferredScrollableViewportSize(getPreferredSize());
    }

    public void setColorRenderer(Color newColor) {
        nf.setColor(newColor);
    }

    /** S'actualitza el JViewport al fer scroll per veure els continguts de TaulaFull */
    @Override
    public void addNotify() {
        super.addNotify();
        Component c = getParent();
        if (c instanceof JViewport) {
            JViewport viewport = (JViewport)c;
            viewport.addChangeListener( this );
        }
    }

    /**
     * Retorna el número de files de la taula
     * @return número de files taula
     */
    @Override
    public int getRowCount() {
        return main.getRowCount();
    }

    /**
     * Retorna l'alçada (mida en pantalla) d'una fila. Es delega aquest mètode a la taula principal
     * @param row
     * @return alçada d'una fila
     */
    @Override
    public int getRowHeight(int row) {
        int rowHeight = main.getRowHeight(row);
        if (rowHeight != super.getRowHeight(row)) {
            super.setRowHeight(row, rowHeight);
        }
        return rowHeight;
    }

    /**
     * Aquest mètode obté el valor que se li posarà a les cel·les de la primera columna que seran un valor numèric fixe
     * definint la numeració de files
     * @param row
     * @param column
     * @return
     */
    @Override
    public Object getValueAt(int row, int column) {
        return Integer.toString(row + 1);
    }

    /**
     * No permet editar les cel·les de la primera columna, que és on hi haurà la numeració de les cel·les
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * No fa res ja que la nova taula ignora el model
     * @param value
     * @param row
     * @param column
     */
    @Override
    public void setValueAt(Object value, int row, int column) {}

    /**
     * Sincronitza el scrolling de la fila de la taula sincronitzada amb la taula principal
     * @param e
     */
    public void stateChanged(ChangeEvent e) {
        JViewport viewport = (JViewport) e.getSource();
        JScrollPane scrollPane = (JScrollPane)viewport.getParent();
        scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
    }

    /**
     * Implementa el PropertyChangeListener
     * @param e
     */
    public void propertyChange(PropertyChangeEvent e) {
        //  Keep the row table in sync with the main table
        if ("selectionModel".equals(e.getPropertyName())) {
            setSelectionModel(main.getSelectionModel());
        }
        if ("rowHeight".equals(e.getPropertyName())) {
            repaint();
        }
        if ("model".equals(e.getPropertyName())) {
            main.getModel().addTableModelListener(this);
            revalidate();
        }
    }

    /**
     * Implementa el TableModelListener
     * @param e
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        revalidate();
    }

}
