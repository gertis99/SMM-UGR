
package sm.gcl.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase realizada para la implementación de la operación de resaltado
 * del color rojo.
 * @author Germán Castilla López
 */
public class RedOp extends BufferedImageOpAdapter{

    /**
     * Entero que indica el umbral para la operación de resaltado del rojo
     */
    private int umbral;

    /**
     * Constructor de la clase que incializa el umbral para la resaltación
     * del color rojo
     * @param umbral Entero que indica el umbral apartir del que se resalta el rojo
     */
    public RedOp(int umbral) {
        this.umbral = umbral;
    }
    
    /**
     * Método para aplicar la operación de resaltado del rojo sobre la imagen deseada.
     * En este método se hacen todos los calculos necesario para la creación
     * y aplicación de la operación de resaltado del rojo.
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
        
        int resta = 0;
        int suma = 0;
        
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                resta = 0;
                suma = 0;
                
                srcRaster.getPixel(x, y, pixelComp);
                resta = pixelComp[0];
                for(int i = 1; i < pixelComp.length; i++){
                    resta -= pixelComp[i];
                }
                
                if(resta >= umbral){
                    pixelCompDest = pixelComp;
                }else{
                    for(int i = 0; i < pixelComp.length; i++){
                        suma += pixelComp[i];
                    }
                    
                    for(int i = 0; i < pixelCompDest.length; i++){
                        pixelCompDest[i] = suma/3;
                    }
                }
                
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        
        return dest;
    }
    
}
