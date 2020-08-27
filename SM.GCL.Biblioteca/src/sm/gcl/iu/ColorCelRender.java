
package sm.gcl.iu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Clase creada para poder visualizar los colores de forma correcta en los 
 * comboBox que contienen colores.
 * Hereda de ListCellRenderer
 * @author Germán Castilla López
 */
public class ColorCelRender implements ListCellRenderer<Color>{
    
    /**
     * Método para la creación de la visualización correcta en el comboBox de los colores
     * @param list Lista de colores del comboBox
     * @param value Color a representar
     * @param index 
     * @param isSelected 
     * @param cellHasFocus
     * @return Botón del color a visualizar
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index, boolean isSelected, boolean cellHasFocus) {
        JButton b = new JButton();
        b.setBackground(value);
        b.setPreferredSize(new Dimension(25,25));
        return b;
    }
    
}
