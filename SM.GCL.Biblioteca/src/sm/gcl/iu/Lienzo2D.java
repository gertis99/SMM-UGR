
package sm.gcl.iu;

import sm.gcl.graficos.Elipse;
import sm.gcl.graficos.Formas;
import static sm.gcl.graficos.Formas.LINEA;
import sm.gcl.graficos.Linea;
import sm.gcl.graficos.Rectangulo;
import sm.gcl.graficos.RectanguloRedondeado;
import sm.gcl.graficos.ShapeSM;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.BoundingBox;

/**
 * Clase que crea el lienzo donde se plasmará la imagen y las figuras creadas por 
 * el usuario.
 * Hereda de la clase JPanel
 * @author Germán Castilla López
 */
public class Lienzo2D extends javax.swing.JPanel {

    /**
     * Variable usada para indicar la figura a pintar
     */
    private Formas forma = null;
    
    /**
     * Vector de figuras que almacena las figuras pintadas sobre el lienzo
     */
    private List<ShapeSM> vShape = new ArrayList();
    
    /**
     * Puntos usados para la creación de figuras y demás utilidades
     */
    private Point2D p1, p2;
    
    /**
     * Figura a pintar
     */
    private ShapeSM shape;
    
    /**
     * Imagen que almacena el lienzo
     */
    private BufferedImage img;
    
    /**
     * Color del trazo de la figura a pintar
     */
    private Color color = Color.BLACK;
    
    /**
     * Color del relleno de la figura a pintar
     */
    private Color colorRelleno = Color.BLACK;
    
    /**
     * Booleano que indica si la figura a pintar debe estar alisada
     */
    private boolean alisado = false;
    
    /**
     * Entero que indica el grosor de la figura a pintar
     */
    private int grosor = 2;
    
    /**
     * Booleano que indica si el trazo de la figura a pintar debe ser discontinuo
     */
    private boolean discontinuo = false;
    
    /**
     * BasicStroke para configurar el trazo de la figura a pintar
     */
    private BasicStroke stroke = new BasicStroke(grosor);
    
    /**
     * Patrón de discontinuidad de las figuras con trazo discontinuo
     */
    private float patronDiscontinuidad[] = {15.0f, 15.0f};
    
    /**
     * Patrón de discontinuidad de las figuras con trazo continuo
     */
    private float sinPatron[] = {0.0f, 0.0f};
    
    /**
     * Booleano que indica si la figura a pintar debe estar rellena
     */
    private boolean relleno = false;
    
    /**
     * Float que indica el grado de transparencia con el que se debe pintar la figura
     */
    private float transparencia = 1.0f;
    
    /**
     * Booleano que indica si se está en modo edición
     */
    private boolean editar = false;
    
    /**
     * Figura que se va a editar
     */
    private ShapeSM aMover;
    
    /**
     * Figura que marca el area de dibujado
     */
    private Shape area;
    
    /**
     * Rectángulo usado como marco para la figura seleccionada a editar
     */
    private Rectangle2D marco = null;
    
    /**
     * Booleano que indica si la figura a pintar lleva el relleno degradado
     */
    private boolean degradado = false;
    
    /**
     * Booleano que indica si la figura a pintar lleva el relleno degradado horizontal
     */
    private boolean degradadoHorizontal = false;
    
    /**
     * Booleano que indica si el degradado va de esquina a esquina
     */
    private boolean degradadoEsquina = false;
    
    
    /**
     * Constructor de la clase, crea el lienzo llamando al initComponents() e 
     * inicializa el area de dibujo.
     */
    public Lienzo2D() {
        initComponents();
        area = new Rectangle2D.Float();
    }

    /**
     * Método para comprobar la forma que se va a dibujar a continuación
     * @return Elemento del enumerado Formas con la próxima forma a dibujar
     */
    public Formas getForma() {
        return forma;
    }

    /**
     * Método para indicar que forma se va a dibujar a continuación
     * @param forma Elemento del enumerado Formas con la próxima forma a dibujar
     */
    public void setForma(Formas forma) {
        this.forma = forma;
    }
    
    /**
     * Método usado para crear un lienzo nuevo blanco
     * @param img Imagen vacía que se le asigna al lienzo
     */
    public void setNuevoLienzo(BufferedImage img){
        Rectangulo rec = new Rectangulo(true, Color.WHITE);
        rec.setRec(0, 0, img.getWidth(), img.getHeight());
        rec.setColor(Color.WHITE);
        vShape.add(rec);
        this.img = img;
    }
    
    /**
     * Método creado para que el lienzo blanco se quede fijado.
     */
    public void fijarLienzo(){
        seProcesa();
    }
    
    /**
     * Método para asignar una imagen al lienzo
     * @param img Imagen a asignar en el lienzo
     */
    public void setImage(BufferedImage img) {
        this.img = img;
        if (img != null) {
            setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        }
    }

    /**
     * Método para obtener la imagen que contiene el lienzo
     * @return La imagen original que contiene el lienzo
     */
    public BufferedImage getImage() {
        return img;
    }
    
    /**
     * Método para obtener la imagen que contiene el lienzo con las figuras
     * pintadas
     * @param drawVector Booleano para indicar si se vuelcan las figuras sobre la imagen o no
     * @return La imagen con o sin las figuras volcadas
     */
    public BufferedImage getImage(boolean drawVector) {
        if (drawVector) {
            BufferedImage imgout = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
            
            this.paint(imgout.createGraphics());
            return(imgout);
        } else 
            return img;
        
    }

    /**
     * Método para saber de qué color será el trazo de la próxima figura a dibujar
     * @return El color del trazo de la próxima figura a dibujar
     */
    public Color getColor() {
        return color;
    }

    /**
     * Método para indicar el color del trazo de la próxima figura a dibujar
     * @param color El color del trazo de la próxima figura a dibujar
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Método usado para cambiar el color del trazo de la figura seleccionada
     * @param color Color al que se quiere cambiar el trazo de la figura seleccionada
     */
    public void updateColor(Color color){
        aMover.setColor(color);
        this.repaint();
    }
    
    /**
     * Método usado para cambiar el color del relleno de la figura seleccionada
     * @param color Color al que se quiere cambiar el relleno de la figura seleccionada
     */
    public void updateColorRelleno(Color color){
        aMover.setColorRelleno(color);
        this.repaint();
    }
    
    /**
     * Método para cambiar el estado del relleno de la figura seleccionada
     * @param r Booleano para cambiar el estado del relleno de la figura seleccionada
     */
    public void updateRelleno(boolean r){
        aMover.setRelleno(r);
        this.repaint();
    }
    
    /**
     * Método para cambiar el estado del alisado de la figura seleccionada
     * @param a Booleano para cambiar el estado del alisado de la figura seleccionada
     */
    public void updateAlisado(boolean a){
        aMover.setAlisado(a);
        this.repaint();
    }
    
    /**
     * Método para cambiar el estado del degradado de la figura seleccionada
     * @param d Booleano para cambiar el estado del degradado de la figura seleccionada
     */
    public void updateDegradado(boolean d){
        aMover.setDegradado(d);
        this.repaint();
    }
    
    /**
     * Método para cambiar la dirección del degradado de la figura actual
     * @param dh Booleano para cambiar la dirección del degradado de la figura actual
     */
    public void updateDegradadoHorizontal(boolean dh){
        aMover.setDegradadoHorizontal(dh);
        this.repaint();
    }
    
    /**
     * Método para cambiar la dirección del degradado de la figura actual
     * @param dh Booleano para cambiar la dirección del degradado de la figura actual
     */
    public void updateDegradadoEsquina(boolean dh){
        aMover.setDegradadoEsquina(dh);
        this.repaint();
    }
    
    /**
     * Método para cambiar el grosor del trazo de la figura seleccionada
     * @param g Entero que indica el nuevo grosor del trazo de la figura seleccionada
     */
    public void updateGrosor(int g){
        this.grosor = g;
        if(!discontinuo){
            if(grosor>=2)
                stroke = new BasicStroke(grosor);
        }else{
            stroke = new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, 
                patronDiscontinuidad, 0.0f);
        }
        aMover.setStroke(stroke);
        this.repaint();
    }
    
    /**
     * Método para cambiar el trazo de discontinuo a continuo o viceversa de la
     * figura seleccionada.
     * @param d Booleano que indica si el trazo de la figura seleccionada
     * debe cambiarse a continuo o discontinuo
     */
    public void updateDiscontinuo(boolean d){
        this.discontinuo = d;
        if(discontinuo)
            stroke = new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, 
                patronDiscontinuidad, 0.0f);
        else{
            stroke = new BasicStroke(grosor);
        }
        aMover.setStroke(stroke);
        this.repaint();
    }
    
    /**
     * Método para comprobar que figura está seleccionada para editar
     * @return La figura seleccionada para editar
     */
    public ShapeSM getFigura(){
        return aMover;
    }

    /**
     * Método para comprobar de que color será el relleno de la próxima figura a dibujar
     * @return El color del relleno de la próxima figura a dibujar
     */
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * Método para indicar el color del relleno de la próxima figura a dibujar
     * @param colorRelleno El color del relleno de la próxima figura a dibujar
     */
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }

    /**
     * Método para comprobar si la siguiente figura a dibujar estará alisada
     * @return True si la siguiente figura a dibujar estará alisada
     */
    public boolean isAlisado() {
        return alisado;
    }

    /**
     * Método para indicar si la siguiente figura a dibujar estará alisada
     * @param alisado Booleano que indica si la siguiente figura a dibujar estará alisada
     */
    public void setAlisado(boolean alisado) {
        this.alisado = alisado;
    }

    /**
     * Método para comprobar el grosor de la siguiente figura a dibujar
     * @return Entero del grosor de siguiente figura a dibujar
     */
    public int getGrosor() {
        return grosor;
    }

    /**
     * Método para indicar el grosor de la siguiente figura a dibujar y crear el
     * BasicStroke con ese grosor
     * @param grosor Entero del grosor de la siguiente figura a dibujar
     */
    public void setGrosor(int grosor) {
        this.grosor = grosor;
        if(!discontinuo){
            if(grosor>=2)
                stroke = new BasicStroke(grosor);
        }else{
            stroke = new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, 
                patronDiscontinuidad, 0.0f);
        }        
    }

    /**
     * Método para comprobar el BasicStroke de la siguiente figura a dibujar
     * @return El BasicStroke de la siguiente figura a dibujar
     */
    public BasicStroke getStroke() {
        return stroke;
    }

    /**
     * Método para indicar el BasicStroke de la siguiente figura a dibujar
     * @param stroke El BasicStroke de la siguiente figura a dibujar
     */
    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    /**
     * Método para comprobar si la siguiente figura a dibujar tiene el trazo discontinuo
     * @return True si la siguiente figura a dibujar tiene el trazo discontinuo
     */
    public boolean isDiscontinuo() {
        return discontinuo;
    }

    /**
     * Método para indicar si la siguiente figura a dibujar tiene el trazo discontinuo
     * y crear el BasicStroke con esta opción
     * @param discontinuo Booleano para indicar si la siguiente figura a dibujar tiene el trazo discontinuo
     */
    public void setDiscontinuo(boolean discontinuo) {
        this.discontinuo = discontinuo;
        if(discontinuo)
            stroke = new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, 
                patronDiscontinuidad, 0.0f);
        else{
            stroke = new BasicStroke(grosor);
        }
    }

    /**
     * Método para comprobar si la siguiente figura a dibujar estará rellena
     * @return True si la siguiente figura a dibujar estará rellena
     */
    public boolean isRelleno() {
        return relleno;
    }

    /**
     * Método para indicar si la siguiente figura a dibujar estará rellena
     * @param relleno Booleano para indicar si la siguiente figura a dibujar estará rellena
     */
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    /**
     * Método para comprobar la transparencia de la siguiente figura a dibujar
     * @return Float con el valor de la transparencia de la siguiente figura a dibujar
     */
    public float getTransparencia() {
        return transparencia;
    }

    /**
     * Método para indicar el grado de transparencia de la siguiente figura a dibujar
     * @param transparencia Float con el valor de la transparencia de la siguiente figura a dibujar
     */
    public void setTransparencia(float transparencia) {
        this.transparencia = transparencia;       
    }

    /**
     * Método para comprobar si se está en modo edición
     * @return True si está en modo edición
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * Método para indicar si se está en modo edición
     * @param editar Booleano para indicar si se está en modo edición
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
        this.repaint();
    }

    /**
     * Método para obtener la figura que determina el área de dibujo del lienzo
     * @return Figura que determina el área de dibujo del lienzo
     */
    public Shape getArea() {
        return area;
    }

    /**
     * Método para indicar la figura que determina el área de dibujo del lienzo
     * @param area Figura que determina el área de dibujo del lienzo
     */
    public void setArea(Shape area) {
        this.area = area;
    }

    /**
     * Método para comprobar si la siguiente figura a dibujar tendrá un relleno degradado
     * @return True si la siguiente figura a dibujar tendrá un relleno degradado
     */
    public boolean isDegradado() {
        return degradado;
    }

    /**
     * Método para indicar si la siguiente figura a dibujar tendrá un relleno degradado
     * @param degradado Booleano que indica si la siguiente figura a dibujar tendrá un relleno degradado
     */
    public void setDegradado(boolean degradado) {
        this.degradado = degradado;
    }

    /**
     * Método para indicar si la siguiente figura a dibujar tendrá un relleno degradado horizontal
     * @return True si la siguiente figura a dibujar tendrá un relleno degradado horizontal
     */
    public boolean isDegradadoHorizontal() {
        return degradadoHorizontal;
    }

    /**
     * Método para indicar si la siguiente figura a dibujar tendrá un relleno degradado horizontal
     * @param degradadoHorizontal Booleano que indica si la siguiente figura a dibujar tendrá un relleno degradado horizontal
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
     * Método que vuelca las figuras dibujadas sobre la imagen del lienzo y borra
     * las figuras del vector de figuras.
     * Se usa cuando el usuario quiere realizar una operación sobre la imagen y 
     * las figuras pintadas.
     */
    public void seProcesa(){
        BufferedImage imgout = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
            
        this.paint(imgout.createGraphics());
        setImage(imgout);
        vShape = new ArrayList();
    }
     
    /**
     * Método que crea un rectángulo que hará de marco sobre la figura seleccionada a editar.
     * @param g2d Objeto necesario para pintar el rectángulo
     */
    private void crearMarco(Graphics2D g2d){
        if(editar){
            if(aMover != null){
                marco = new Rectangle2D.Double(aMover.getX(), aMover.getY(), aMover.getAncho(), aMover.getAlto());
                    g2d.setColor(Color.BLACK);
                BasicStroke strokeMarco = new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.0f, 
                    patronDiscontinuidad, 0.0f);
                g2d.setStroke(strokeMarco);
                g2d.draw(marco);
            }
        }else{
            aMover = null;
        }
    }
    
    /**
     * Método que pinta la imagen del lienzo, crea el aréa de dibujo y pinta las figuras
     * creadas por el usuario
     * @param g Objeto necesario para pintar imagen y figuras
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if(img!=null) g2d.drawImage(img, 0, 0, Color.WHITE, this);
        g2d.clip(area);
        crearMarco(g2d);
        
        for (ShapeSM s : vShape) {
            s.paint(g2d);
        }
    }
    
        
    /**
     * Método que crea la siguiente figura a pintar.
     * Para ello hace uso de todos los atributos necesarios para crear una figura
     * y que han sido indicados con sus respectivos métodos.
     * Solo crea la figura si no se está en modo edición.
     */
    public void createShape(){
        if(!editar){
            switch(forma){
                case LINEA:{
                    Linea linea = new Linea();
                    linea.setStroke(stroke);
                    linea.setColor(color);
                    linea.setTransparencia(transparencia);
                    vShape.add(linea);
                    shape = linea;
                    break;
                }
                case RECTANGULO:{
                    Rectangulo rectangulo = new Rectangulo(relleno, colorRelleno);
                    rectangulo.setStroke(stroke);
                    rectangulo.setColor(color);
                    rectangulo.setTransparencia(transparencia);
                    rectangulo.setDegradado(degradado);
                    rectangulo.setDegradadoHorizontal(degradadoHorizontal);
                    rectangulo.setDegradadoEsquina(degradadoEsquina);
                    vShape.add(rectangulo);
                    shape = rectangulo;
                    break;
                }
                case RECTANGULO_REDONDEADO:{
                    RectanguloRedondeado rectangulo = new RectanguloRedondeado(alisado, relleno, colorRelleno);
                    rectangulo.setStroke(stroke);
                    rectangulo.setColor(color);
                    rectangulo.setTransparencia(transparencia);
                    rectangulo.setDegradado(degradado);
                    rectangulo.setDegradadoHorizontal(degradadoHorizontal);
                    rectangulo.setDegradadoEsquina(degradadoEsquina);
                    vShape.add(rectangulo);
                    shape = rectangulo;
                    break;
                }
                case ELIPSE:{
                    Elipse elipse = new Elipse(alisado, relleno, colorRelleno);
                    elipse.setStroke(stroke);
                    elipse.setColor(color);
                    elipse.setTransparencia(transparencia);
                    elipse.setDegradado(degradado);
                    elipse.setDegradadoHorizontal(degradadoHorizontal);
                    elipse.setDegradadoEsquina(degradadoEsquina);
                    vShape.add(elipse);
                    shape = elipse;
                    break;
                }
            }
        }

    }

    /**
     * Método usado para editar la posición de la figura seleccionada.
     * @param s La figura a mover
     * @param pos Nuevo punto donde empezará la imagen seleccionada.
     */
    public void setLocation(ShapeSM s, Point2D pos){
        if(s instanceof Linea){
            Linea line = ((Linea) s);
            line.setLocation(pos);
            
        } else if (s instanceof Rectangulo) {
            Rectangulo rec = (Rectangulo) s;
            rec.setLocation(pos);

        } else if (s instanceof RectanguloRedondeado) {
            RectanguloRedondeado rec = (RectanguloRedondeado) s;
            rec.setLocation(pos);
            
        } else if(s instanceof Elipse){
            Elipse elipse = (Elipse) s;
            elipse.setLocation(pos);
        }   
         
    }
        
    /**
     * Método usado para "refrescar" el estado de una figura que se está creando
     * o que está siendo su posición modificada.
     */
    public void updateShape() {
        if (editar) {
            setLocation(aMover, p2);
            this.repaint();
        } else {
            switch (forma) {
                case LINEA: {
                    Linea linea = (Linea) shape;
                    linea.setLinea(p1, p2);
                    break;
                }
                case RECTANGULO: {
                    Rectangulo rec = (Rectangulo) shape;
                    rec.setRectangulo(p1, p2);
                    break;
                }
                case RECTANGULO_REDONDEADO:{
                    RectanguloRedondeado rec = (RectanguloRedondeado) shape;
                    rec.setRectangulo(p1, p2);
                    break;
                }
                case ELIPSE: {
                    Elipse el = (Elipse) shape;
                    el.setElipse(p1, p2);
                    break;
                }
            }
        
        }
    }
    
    /**
     * Método que devuelve la figura que está seleccionada para editar en un punto concreto
     * si esque hay una figura en ese punto.
     * @param p Punto a comprobar si hay alguna figura
     * @return La figura seleccionada o null si no hay ninguna figura en ese punto
     */
    private ShapeSM getSelectedShape(Point2D p){
        for(ShapeSM s:vShape){                   
            if(s.contains(p)) return s;           
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que gestiona el evento de arrastrar el ratón
     * @param evt 
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        p2 = evt.getPoint();
        updateShape();
        this.repaint();
    }//GEN-LAST:event_formMouseDragged

    /**
     * Método que gestiona el evento de mantener el ratón pinchando
     * @param evt 
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        p1 = evt.getPoint();
        if(editar){
            aMover = getSelectedShape(p1);
        }else{         
            createShape();
            this.repaint();
        }
    }//GEN-LAST:event_formMousePressed

    /**
     * Método que gestiona el evento cuando se deja de pinchar con el ratón
     * @param evt 
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        this.formMouseDragged(evt);
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
