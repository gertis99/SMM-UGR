
package sm.gcl.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase realizada para la creación y modificación de rectángulos usando la 
 * clase de Java Rectangle2D.
 * Hereda de la clase ShapeSM.
 * @author Germán Castilla López
 */
public class Rectangulo extends ShapeSM{
    
    /**
     * Objeto de la clase Rectangle2D usado para la cración y modificación del rectángulo
     */
    private Rectangle2D rectangulo = null;
    
    /**
     * Booleano que indica si el rectángulo debe estar rellenada
     */
    private boolean relleno = false;
    
    /**
     * Color de relleno del rectángulo
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
     * Color del trazo del rectángulo
     */
    private Color color = Color.BLACK;
    
    /**
     * Booleano que indica si el degradado va de esquina a esquina
     */
    private boolean degradadoEsquina = false;
    
    /**
     * Constructor de la clase que asigna los atributos básicos del rectángulo
     * e incializa el Rectangle2D
     * @param re Booleano para saber si el rectángulo debe ser relleno
     * @param c Color del relleno
     */
    public Rectangulo(boolean re, Color c){
        super();
        rectangulo =  new Rectangle2D.Float();
        relleno = re;
        colorRelleno = c;
    }
    
    /**
     * Método para indicar donde se situa el rectángulo
     * @param p1 Punto donde comienza la diagonal del rectángulo
     * @param p2 Punto donde acaba la diagonal del rectángulo
     */
    public void setRectangulo(Point2D p1, Point2D p2){
        rectangulo.setFrameFromDiagonal(p1, p2);
    }

    /**
     * Método para saber el color del trazo del rectángulo
     * @return El color del trazo
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Método para asignar el color del trazo del rectángulo
     * @param color Color del trazo a asignar
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }
   
    /**
     * Método para saber si el relleno está degradado
     * @return True si el relleno está degradado
     */
    @Override
    public boolean isDegradado() {
        return degradado;
    }

    /**
     * Método para asignar si el relleno debe ser degradado
     * @param degradado Booleano que indica si el relleno debe ser degradado
     */
    @Override
    public void setDegradado(boolean degradado) {
        this.degradado = degradado;
    }
    
    /**
     * Método para saber si el degradado es horizontal o vertical
     * @return True si el degradado es horizontal
     */
    @Override
    public boolean isDegradadoHorizontal() {
        return degradadoHorizontal;
    }
    
    /**
     * Método para asignar si el degradado debe ser horizontal o vertical
     * @param degradadoHorizontal Booleano que indica si el degradado es horizontal o no
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
     * Método para crear el degradado del relleno. 
     * El degradado se crea con el color del trazo y del relleno.
     * Crea el degradado horizontalmente o verticalmente dependiendo de lo
     * indicado en los atributos del rectángulo
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
     * Método para determinar la posición el rectángulo al editar su posición
     * @param pos Punto donde se quiere que comience el rectángulo 
     */
    public void setLocation(Point2D pos){
        double height = rectangulo.getHeight();
        double width = rectangulo.getWidth();
        Dimension dim = new Dimension();
        dim.setSize(width, height);
        rectangulo.setFrame(pos, dim);
    }
    
    /**
     * Método para colocar un réctangulo en la posición indicada.
     * Usa el método setRect de la clase Rectangle2D.
     * @param x Coordenada x donde empieza el rectángulo
     * @param y Coordenada y donde empieza el rectángulo
     * @param w Ancho del rectángulo
     * @param h Alto del rectángulo
     */
    public void setRec(double x, double y, double w, double h){
        rectangulo.setRect(x, y, w, h);
    }
    
    /**
     * Método para saber si un punto está dentro del rectángulo
     * @param pos Punto que se quiere estudiar
     * @return True si el punto está dentro del rectángulo
     */
    @Override
    public boolean contains(Point2D pos){
        boolean res = false;
        
        if(rectangulo.contains(pos))
            res = true;
        
        return res;
    }
    
    /**
     * Método para saber la coordenada x del punto de la esquina superior izquierda del rectángulo
     * @return Double con la coordenada x del punto de la esquina superior izquierda del rectángulo
     */
    @Override
    public double getX(){
        return rectangulo.getMinX();
    }
    
    /**
     * Método para saber la coordenada y del punto de la esquina superior izquierda del rectángulo
     * @return Double con la coordenada y del punto de la esquina superior izquierda del rectángulo
     */
    @Override
    public double getY(){
        return rectangulo.getMinY();
    }
    
    /**
     * Método para saber el ancho en píxeles del rectángulo
     * @return Double con el ancho del rectángulo
     */
    @Override
    public double getAncho(){
        return rectangulo.getWidth();
    }
    
    /**
     * Método para saber el alto en píxeles del rectángulo
     * @return Double con el alto del rectángulo
     */
    @Override
    public double getAlto(){
        return rectangulo.getHeight();
    }
    
    /**
     * Método para saber cual es el color del relleno del rectángulo
     * @return Color de relleno del rectángulo
     */
    @Override
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * Método para especificar el color de relleno del rectángulo
     * @param colorRelleno Color de relleno del rectángulo que se quiere aplicar
     */
    @Override
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }
    
    /**
     * Método para saber si el rectángulo está relleno
     * @return True si el rectángulo está relleno
     */
    @Override
    public boolean isRelleno() {
        return relleno;
    }

    /**
     * Método para asignar si el rectángulo debe estar relleno
     * @param relleno Booleano que asigna si el rectángulo debe estar relleno
     */
    @Override
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }
    
    /**
     * Método para aplicar los atributos del rectángulo al Graphics2D y hacer 
     * que el rectángulo se pinte con esos atributos
     * @param g2d Objeto encargado del dibujado del rectángulo
     */
    @Override
    public void paint(Graphics2D g2d){
        super.paint(g2d);
        g2d.setPaint(color);
        if(relleno){
            g2d.draw(rectangulo);
            if(degradado)
                g2d.setPaint(crearDegradado());
            else
                g2d.setPaint(colorRelleno);
            g2d.fill(rectangulo);
        }
            
        else
            g2d.draw(rectangulo);
    }
}
