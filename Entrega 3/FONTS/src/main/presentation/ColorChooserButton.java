package main.presentation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

/**
 * Aquesta classe modifica el JButton d'acord amb el color que s'ha seleccionat amb el JColorChooser
 */
public class ColorChooserButton extends JButton {
    /** Representa l'últim color seleccionat al panell */
    private Color current;
    /** Array de listeners per a notificar el canvi de color al botó */
    private final List<ColorChangedListener> listeners = new ArrayList<>();

    /**
     * Constructora del botó del botó amb l'icona del color
     * @param bText
     */
    public ColorChooserButton(String bText) {
        setSelectedColor(Color.WHITE);
        setText(bText);
        addActionListener(arg0 -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a color", current);
            setSelectedColor(newColor);
        });
    }

    /**
     * Setter del nou color al botó
     * @param newColor
     */
    public void setSelectedColor(Color newColor) {
        setSelectedColor(newColor, true);
    }

    /**
     * Setter el color del botó i notifica que s'ha de pintar a la selecció de cel·les
     * @param newColor
     * @param notifica
     */
    public void setSelectedColor(Color newColor, boolean notifica) {
        if (newColor == null) return;
        current = newColor;
        setIcon(createIcon(current, 16, 16));
        repaint();
        if (notifica) {
            for (ColorChangedListener l : listeners) {
                l.colorChanged(newColor);
            }
        }
    }

    /**
     * Interfície pel listener de canvi de color
     */
    public interface ColorChangedListener {
        void colorChanged(Color newColor);
    }

    /**
     * Afegeix el listener a l'array de ColorChangedListener
     * @param toAdd
     */
    public void addColorChangedListener(ColorChangedListener toAdd) {
        listeners.add(toAdd);
    }

    /**
     * Crea l'icona del botó amb el color corresponent
     * @param main
     * @param width
     * @param height
     * @return Retorna la icona del botó
     */
    public static ImageIcon createIcon(Color main, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(main);
        graphics.fillRect(0, 0, width, height);
        graphics.setXORMode(Color.DARK_GRAY);
        graphics.drawRect(0, 0, width-1, height-1);
        image.flush();
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }
}
