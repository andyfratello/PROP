package main.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * La classe CustomTab es l’encarregada de reimplementar els botons de canvi de full. Aquesta reimplementació fa que es
 * pugui tancar fulls afegint una creu al panell i obrir-ne i seleccionar-ne de nous.
 * @author Andreu Orensanz Bargalló (andreu.orensanz@estudiantat.upc.edu)
 */
public class CustomTab extends JPanel {
    /** Panells de canvis de Full amb botons */
    static PanellFulls panellFulls;

    /**
     * Constructora de CustomTab
     * @param customJTabbedPane
     */
    public CustomTab(PanellFulls customJTabbedPane) {
        this.panellFulls = customJTabbedPane;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBorder(new EmptyBorder(5, 2, 2, 2));
        setOpaque(false);
        addLabel();
        add(new CustomButton("x"));
    }

    /** Afegeix el nom al full */
    private void addLabel() {
        JLabel label = new JLabel() {
            public String getText() {
                int index = panellFulls.indexOfTabComponent(CustomTab.this);
                if (index != -1) {
                    return panellFulls.getTitleAt(index);
                }
                return null;
            }
        };
        label.setBorder(new EmptyBorder(0, 0, 0, 10));
        add(label);
    }


    /**
     * Classe per a reimplementar el botó de tancar un panell amb full
     */
    class CustomButton extends JButton implements MouseListener {
        /**
         * Constructora de CustomButton
         * @param text
         */
        public CustomButton(String text) {
            int size = 15;
            setText(text);
            setPreferredSize(new Dimension(size, size));

            setToolTipText("close the Tab");
            setContentAreaFilled(false);
            setBorder(new EtchedBorder());
            setBorderPainted(false);

            setFocusable(false);
            addMouseListener(this);
        }

        /** Tanca el full quan es fa clic al botó amb la creu */
        @Override
        public void mouseClicked(MouseEvent e) {
            int index = panellFulls.indexOfTabComponent(CustomTab.this);
            if (index != -1) {
                panellFulls.removeTab(index);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        /** Pinta de color vermell la creu quan es passa el ratolí per sobre */
        @Override
        public void mouseEntered(MouseEvent e) {
            setBorderPainted(true);
            setForeground(Color.RED);
        }

        /** Pinta de color negre la creu quan no es passa el ratolí per sobre */
        @Override
        public void mouseExited(MouseEvent e) {
            setBorderPainted(false);
            setForeground(Color.BLACK);
        }
    }
}