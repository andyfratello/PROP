package main.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * La classe NumeradorDeFiles es tracta d’una reimplementació de l’objecte DefaultTableCellRenderer de la llibreria de
 * Swing. És l’encarregada de dissenyar un sistema per a poder numerar les files i poder canviar de color un cop ha
 * estat seleccionada una cel·la d’una fila.
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class NumeradorDeFiles extends DefaultTableCellRenderer {
    /** Constructora de NumeradorDeFiles */
    public NumeradorDeFiles() {
        setHorizontalAlignment(JLabel.CENTER);
    }
    /** Color de selecció de cel·les */
    private Color color;

    /** Setter del color */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * S'obté un Component de NumeradorDeFiles amb la capacitat de poder numerar files d'un JTable i pintar de color
     * vermell i en negreta els números de les files de les cel·les seleccionades.
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return NumeradorDeFiles
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!table.isRowSelected(row)) {
            if(row == 2 && column == 2)
                c.setBackground(color);
            else
                c.setBackground(table.getBackground());
        }


        if (table != null) {
            JTableHeader header = table.getTableHeader();
            if (header != null) {
                setForeground(header.getForeground());
                setBackground(header.getBackground());
                setFont(header.getFont());
            }
        }
        if (isSelected) {
            setFont(getFont().deriveFont(Font.BOLD));

            if (table.isCellSelected(row, column)) {
                setForeground(Color.red);
            } else if (table.isRowSelected(row)) {
                setForeground(Color.green);
            } else if (table.isColumnSelected(column)) {
                setForeground(Color.blue);
            } else {
                setForeground(Color.black);
            }
        }

        setText((value == null) ? "" : value.toString());
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        return c;
    }
}
