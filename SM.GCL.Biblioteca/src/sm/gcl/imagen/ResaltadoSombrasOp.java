package sm.gcl.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase creada para la implementación de la operación píxel a píxel
 * de creación propia, resaltado de las sombras.
 * @author Germán Castilla López
 */
public class ResaltadoSombrasOp extends BufferedImageOpAdapter{
    
    /**
     * Entero que indica el umbral apartir del cual se considera
     * que un píxel no es sombra
     */
    private int umbral;

    /**
     * Constructor de la clase que inicializa el umbral apartir del cual se considera
     * que un píxel no es sombra
     * @param umbral Enetero de umbral apartir del cual se considera
     * que un píxel no es sombra
     */
    public ResaltadoSombrasOp(int umbral) {
        this.umbral = umbral;
    }
    
    /**
     * Método para aplicar la operación de resaltado de sombras sobre la imagen deseada.
     * En este método se hacen todos los calculos necesario para la creación
     * y aplicación de la operación de resaltado de sombras.
     * @param src: Imagen a la que aplicar la operación
     * @param dest: Imagen resultado
     * @return La imagen con la operación realizada.
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int[] pixelComp = new int[srcRaster.getNumBands()];
        int[] pixelCompDest = new int[srcRaster.getNumBands()];
        
        int suma;
        
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                suma = 0;
                
                srcRaster.getPixel(x, y, pixelComp);
             
                for(int i = 0; i < pixelComp.length; i++){
                    suma += pixelComp[i];
                }
                
                if(suma >= umbral){
                    for(int i = 0; i < pixelCompDest.length; i++){
                        pixelCompDest[i] = (int)(pixelComp[i]*1.3);
                        if(pixelCompDest[i] > 255)
                            pixelCompDest[i] = 255;
                    }
                        
                }else{
                    if(suma < umbral){
                        for(int i = 0; i < pixelCompDest.length; i++)
                            pixelCompDest[i] = (int)(pixelCompDest[i]*0.7);
                    }
                }
                
                
                
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        
        return dest;
    }
}
