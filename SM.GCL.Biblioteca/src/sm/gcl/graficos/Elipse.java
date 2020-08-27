
package sm.gcl.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Clase realizada para la creación y edición de figuras elipse usando
 * la clase de java Ellipse2D.
 * Hereda de ShapeSM
 * @author Germán Castilla López
 */
public class Elipse extends ShapeSM{
    
    /**
     * Objeto de la clase Ellipse2D usado para la creación y modificación de la elipse
     */
    private Ellipse2D elipse = null;
    
    /**
     * Booleano que indica si la elipse debe estar alisada
     */
    private boolean alisado = false;
    
    /**
     * RenderingHints para pintar la elipse con suavizado
     */
    private RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    /**
     * RenderingHints para pintar la elipse sin suavizado
     */
    private RenderingHints renderNo = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    
    /**
     * Booleano que indica si la elipse debe estar rellenada
     */
    private boolean relleno = false;
    
    /**
     * Color de relleno de la elipse
     */
    private Color colorRelleno = Color.BLACK;
    
    /**
     * Booleano que indica si el relleno debe ser degradado
     */
    private boolean degradado = false;
    
    /**
     * Booleano que indica si el degradado debe ser horizontal
     */
    private boolean degradadoHorizontal = false;
    
    /**
     * Color del trazo de la elipse
     */
    private Color color = Color.BLACK;
    
    /**
     * Booleano que indica si el degradado va de esquina a esquina
     */
    private boolean degradadoEsquina = false;
    
    /**
     * Constructor de la clase que inicializa las propiedades básicas y
     * el objeto Ellipse2D usado para la creación de la elipse
     * @param al: Booleano que indica si la elipse debe ser alisada
     * @param re: Booleano que indica si la elipse debe estar rellena
     * @param c: Color que indica el color del relleno de la imagen. 
     */
    public Elipse(boolean al, boolean re, Color c){
        super();
        elipse = new Ellipse2D.Float();
        alisado = al; 
        relleno = re;
        colorRelleno = c;
    }
    
    /**
     * Método que asigna la posición de la elipse
     * @param p1: Punto donde comienza la diagonal de la elipse
     * @param p2: Punto donde acaba la diagonal de la elipse
     */
    public void setElipse(Point2D p1, Point2D p2){
        elipse.setFrameFromDiagonal(p1, p2);
    }

    /**
     * Método de consulta para saber si la elipse está alisada o no
     * @return true si la elipse está alisada
     */
    @Override
    public boolean isAlisado() {
        return alisado;
    }

    /**
     * Método para asignar si la elipse debe estar alisada o no
     * @param alisado: booleano que indique si la elipse debe ser alisada
     */
    @Override
    public void setAlisado(boolean alisado) {
        this.alisado = alisado;
    }
    
    /**
     * Método de consulta de si la elipse está rellena o no 
     * @return true si la elipse está rellena
     */
    @Override
    public boolean isRelleno() {
        return relleno;
    }

    /**
     * Método para asignar si la elipse debe estar rellena o no
     * @param relleno: booleano para indicar si la elipse debe estar rellena 
     */
    @Override
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }
    
    /**
     * Método de consulta de si el relleno es degradado o no
     * @return true si el relleno está degradado
     */
    @Override
    public boolean isDegradado() {
        return degradado;
    }

    /**
     * Método para asignar si el relleno debe estar degradado o no
     * @param degradado: Booleano para asignar si el relleno debe ser degradado
     */
    @Override
    public void setDegradado(boolean degradado) {
        this.degradado = degradado;
    }
    
    /**
     * Método para saber si el degradado debe ser horizontal o no
     * @return true si el degradado es horizontal
     */
    @Override
    public boolean isDegradadoHorizontal() {
        return degradadoHorizontal;
    }

    /**
     * Método para asignar si el degradado es horizontal
     * @param degradadoHorizontal: booleano para asignar si el degradado debe ser horizontal
     */
    @Override
    public void setDegradadoHorizontal(boolean degradadoHorizontal) {
        this.degradadoHorizontal = degradadoHorizontal;
    }
    
    /**
     * Método para indicar si el degradado es de esquina a esquina
     * @param degradadoEsquina 
     */
    @Override
    public void setDegradadoEsquina(boolean degradadoEsquina) {
        this.degradadoEsquina = degradadoEsquina;
    }
    
    /**
     * Método para saber de que color es el trazo de la elipse
     * @return El color del trazo de la elipse
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Método para asignar el color del trazo de la elipse
     * @param color: Color del trazo de la elipse 
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Método para crear el degradado del relleno. 
     * El degradado se crea con el color del trazo y del relleno.
     * Crea el degradado horizontalmente o verticalmente dependiendo de lo
     * indicado en los atributos de la elipse
     * @return El degradado GradientPaint
     */
    private GradientPaint crearDegradado(){
        GradientPaint res;
        if(!degradadoEsquina){
            if (!degradadoHorizontal) {
                Point2D p1 = new Point2D.Double(getX() + getAncho(), getY());
                Point2D p2 = new Point2D.Double(getX() + getAncho(), getY() + getAlto());
                res = new GradientPaint(p1, color, p2, colorRelleno);
            } else {
                Point2D p1 = new Point2D.Double(getX(), getY() + getAlto());
                Point2D p2 = new Point2D.Double(getX() + getAncho(), getY() + getAlto());
                res = new GradientPaint(p1, color, p2, colorRelleno);
            }
        }else{
            Point2D p1 = new Point2D.Double(getX(), getY());
            Point2D p2 = new Point2D.Double(getX() + getAncho(), getY() + getAlto());
            res = new GradientPaint(p1, color, p2, colorRelleno);
        }
        
        return res;
    }
    
    /**
     * Método para determinar la posición de la elipse al editar su posición
     * @param pos Punto donde se quiere que comience la elipse 
     */
    public void setLocation(Point2D pos){
        double height = elipse.getHeight();
        double width = elipse.getWidth();
        Dimension dim = new Dimension();
        dim.setSize(width, height);
        elipse.setFrame(pos, dim);
    }
    
    /**
     * Método para saber si un punto está dentro de la elipse
     * @param pos Punto que se quiere estudiar
     * @return True si el punto está dentro de la elipse
     */
    @Override
    public boolean contains(Point2D pos){
        boolean res = false;
        
        if(elipse.contains(pos))
            res = true;
        
        return res;
    }
    
    /**
     * Método para saber la coordenada x del punto de la esquina superior izquierda de la elipse
     * @return Double con la coordenada x del punto de la esquina superior izquierda de la elipse
     */
    @Override
    public double getX(){
        return elipse.getMinX();
    }
    
    /**
     * Método para saber la coordenada y del punto de la esquina superior izquierda de la elipse
     * @return Double con la coordenada y del punto de la esquina superior izquierda de la elipse
     */
    @Override
    public double getY(){
        return elipse.getMinY();
    }
    
    /**
     * Método para saber el ancho en píxeles de la elipse
     * @return Double con el ancho de la elipse
     */
    @Override
    public double getAncho(){
        return elipse.getWidth();
    }
    
    /**
     * Método para saber el alto en píxeles de la elipse
     * @return Double con el alto de la elipse
     */
    @Override
    public double getAlto(){
        return elipse.getHeight();
    }

    /**
     * Método para saber cual es el color del relleno de la elipse
     * @return Color de relleno de la elipse
     */
    @Override
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * Método para especificar el color de relleno de la elipse
     * @param colorRelleno Color de relleno de la elipse que se quiere aplicar
     */
    @Override
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }
    
    /**
     * Método para aplicar los atributos de la elipse al Graphics2D y hacer 
     * que la elipse se pinte con esos atributos
     * @param g2d Objeto encargado del dibujado de la elipse
     */
    @Override
    public void paint(Graphics2D g2d){
        super.paint(g2d);
        g2d.setPaint(color);
        if(alisado)
            g2d.setRenderingHints(render);
        else
            g2d.setRenderingHints(renderNo);
        
        if(relleno){
            g2d.draw(elipse);
            if(degradado)
                g2d.setPaint(crearDegradado());
            else
                g2d.setPaint(colorRelleno);
            g2d.fill(elipse);
        }
            
        else
            g2d.draw(elipse);           
    }
    
}
