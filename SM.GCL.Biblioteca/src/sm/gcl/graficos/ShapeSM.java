
package sm.gcl.graficos;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase creada como clase padre para las demás clases de las figuras.
 * En esta clase se determinarán diferentes atributos básicos comunes a todas las figuras.
 * @author Germán Castilla López
 */
public class ShapeSM {
    
    /**
     * Color del trazo de la figura
     */
    private Color color = Color.BLACK;
    
    /**
     * Color del relleno de la figura
     */
    private Color colorRelleno = Color.BLACK;
    
    /**
     * Booleano que indica si la figura debe ser alisada
     */
    private boolean alisado = false;
    
    /**
     * BasicStroke para confiugrar el trazo de la figura
     */
    private BasicStroke stroke = null;
    
    /**
     * Booleano que indica si la figura debe estar rellena
     */
    private boolean relleno = false;
    
    /**
     * Composite para configurar la transparencia de la figura
     */
    private Composite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
    
    /**
     * Float que indica el grado de transparencia de la figura
     */
    private float transparencia = 1.0f;
    
    /**
     * Booleano que indica si el relleno debe ser degradado
     */
    private boolean degradado = false;
    
    /**
     * Booleano que indica si el degradado debe ser horizontal
     */
    private boolean degradadoHorizontal = false;
    
    /**
     * Booleano que indica si el degradado va de esquina a esquina
     */
    private boolean degradadoEsquina = false;
    
    /**
     * Constructor sin parámetros que asigna el ancho del trazo de la figura a 2
     */
    public ShapeSM(){
        stroke = new BasicStroke(2);
    }
    
    /**
     * Método para saber de que color es el trazo de la figura
     * @return El color del trazo de la figura
     */
    public Color getColor() {
        return color;
    }

    /**
     * Método para asignar el color del trazo de la figura
     * @param color: Color del trazo de la figura 
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Método para saber cual es el color del relleno de la figura
     * @return Color de relleno de la figura
     */
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * Método para especificar el color de relleno de la figura
     * @param colorRelleno Color de relleno de la figura que se quiere aplicar
     */
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }

    /**
     * Método de consulta de si la figura está rellena o no 
     * @return true si la figura está rellena
     */
    public boolean isRelleno() {
        return relleno;
    }

    /**
     * Método para asignar si la figura debe estar rellena o no
     * @param relleno: booleano para indicar si la figura debe estar rellena 
     */
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    /**
     * Método de consulta de si el relleno es degradado o no
     * @return true si el relleno está degradado
     */
    public boolean isDegradado() {
        return degradado;
    }

    /**
     * Método para asignar si el relleno debe estar degradado o no
     * @param degradado: Booleano para asignar si el relleno debe ser degradado
     */
    public void setDegradado(boolean degradado) {
        this.degradado = degradado;
    }

    /**
     * Método para saber si el degradado debe ser horizontal o no
     * @return true si el degradado es horizontal
     */
    public boolean isDegradadoHorizontal() {
        return degradadoHorizontal;
    }

    /**
     * Método para asignar si el degradado es horizontal
     * @param degradadoHorizontal: booleano para asignar si el degradado debe ser horizontal
     */
    public void setDegradadoHorizontal(boolean degradadoHorizontal) {
        this.degradadoHorizontal = degradadoHorizontal;
    }
    
    /**
     * Método para indicar si el degradado es de esquina a esquina
     * @param degradadoEsquina 
     */
    public void setDegradadoEsquina(boolean degradadoEsquina) {
        this.degradadoEsquina = degradadoEsquina;
    }
    
    /**
     * Método de consulta para saber si la figura está alisada o no
     * @return true si la figura está alisada
     */
    public boolean isAlisado() {
        return alisado;
    }

    /**
     * Método para asignar si la figura debe estar alisada o no
     * @param alisado: booleano que indique si la figura debe ser alisada
     */
    public void setAlisado(boolean alisado) {
        this.alisado = alisado;
    }

    /**
     * Método que devuelve un objeto de la clase BasicStroke, con los atributos
     * del trazo de la figura
     * @return un objeto de la clase BasicStroke, con los atributos
     * del trazo de la figura
     */
    public BasicStroke getStroke() {
        return stroke;
    }

    /**
     * Método para asignar los atributos del trazo de la figura con la clase
     * BasicStroke
     * @param stroke BasicStroke, con los atributos del trazo de la figura
     */
    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    /**
     * Método para saber el grado de transparencia de la figura
     * @return Float con el grado de transparencia de la figura
     */
    public float getTransparencia() {
        return transparencia;
    }

    /**
     * Método que asigna el grado de transparencia de la figura con la clase AlphaComposite
     * @param transparencia Float con el grado de transparencia deseado
     */
    public void setTransparencia(float transparencia) {
        this.transparencia = transparencia;
        comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.transparencia);
    }
    
    /**
     * Método que devuelve si un punto está dentro de la figura o no.
     * Desarrollado en las clases hijo
     */
    public boolean contains(Point2D pos){
        return false;
    }
    
    /**
     * Método que devuelve la coordenada x del primer punto de la figura
     * Desarrollado en las clases hijo
     */
    public double getX(){
        return 0;
    }
    
    /**
     * Método que devuelve la coordenada y del primer punto de la figura
     * Desarrollado en las clases hijo
     */
    public double getY(){
        return 0;
    }
    
    /**
     * Método que devuelve el ancho de la figura
     * Desarrollado en las clases hijo
     */
    public double getAncho(){
        return 0;
    }
    
    /**
     * Método que devuelve el alto de la figura
     * Desarrollado en las clases hijo
     */
    public double getAlto(){
        return 0;
    }
    
    /**
     * Método que asigna los atributos básicos al Graphics2D que tiene la figura
     * @param g2d Objeto necesario para pintar la figura
     */
    public void paint(Graphics2D g2d){
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.setComposite(comp);
        g2d.setPaint(color);
    }
    
}
