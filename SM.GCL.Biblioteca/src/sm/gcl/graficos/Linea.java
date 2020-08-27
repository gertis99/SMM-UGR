
package sm.gcl.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase realizada para la creación y modificación de figuras tipo línea, usando
 * la clase de java Line2D.
 * Hereda de ShapeSM
 * @author Germán Castilla López
 */
public class Linea extends ShapeSM{
    
    /**
     * Objeto de la clase Line2D usado para la cración y modificación de la línea
     */
    private Line2D linea = null;
    
    /**
     * Constructor de la clase que incializa la Line2D
     */
    public Linea(){
        super();
        linea = new Line2D.Float();
    }
    
    /**
     * Método para indicar la posición de la línea
     * @param pos1 Punto donde comienza la línea
     * @param pos2 Punto donde termina la línea
     */
    public void setLinea(Point2D pos1, Point2D pos2){
        linea.setLine(pos1, pos2);
    }
    
    /**
     * Método para indicar la posición de la línea al editar su posición
     * @param pos Punto donde se quiere colocar el principio de la línea
     */
    public void setLocation(Point2D pos){
        double x = pos.getX() - linea.getX1();
        double y = pos.getY() - linea.getY1();

        Point2D nuevo = new Point2D.Double(linea.getX2() + x, linea.getY2() + y);
        linea.setLine(pos, nuevo);
    }
    
    /**
     * Método para saber si la línea contiene un punto específico
     * @param pos El punto a estudiar
     * @return True si el punto está contenido en la línea (o está a menos de dos píxeles de ella)
     */
    @Override
    public boolean contains(Point2D pos){
        boolean res = false;
        
        if(linea.ptLineDist(pos) <= 2.0)
            res = true;
        
        return res;
    }
    
    /**
     * Método para saber la coordenada x del comienzo de la línea
     * @return Double de la coordenada x del comienzo de la línea
     */
    @Override
    public double getX(){
        return linea.getX1();
    }
    
    /**
     * Método para saber la coordenada y del comienzo de la línea
     * @return Double de la coordenada y del comienzo de la línea
     */
    @Override
    public double getY(){
        return linea.getY1();
    }
    
    /**
     * Método para saber el ancho de la línea
     * @return Double con el ancho de la línea
     */
    @Override
    public double getAncho(){
        return linea.getX2()-linea.getX1();
    }
    
    /**
     * Método para saber el alto de la línea
     * @return Double con el alto de la línea
     */
    @Override
    public double getAlto(){
        return linea.getY2()-linea.getY1();
    }
    
    /**
     * Método para pintar la línea
     * @param g2d Objeto Graphics2D necesario para pintar figuras
     */
    @Override
    public void paint(Graphics2D g2d){
        super.paint(g2d);
        g2d.draw(linea);
    }
}
