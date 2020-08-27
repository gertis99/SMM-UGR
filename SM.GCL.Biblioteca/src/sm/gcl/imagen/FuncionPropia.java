
package sm.gcl.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 *  Clase creada para la función propia opcional
 * @author Germán Castilla López
 */
public class FuncionPropia extends BufferedImageOpAdapter{

    /**
     * Constructor de la clase
     */
    public FuncionPropia() {
        
    }
    
    /**
     * Método filter usado para aplicar la operación sobre la imagen
     * @param src Imagen a la que aplicar la operación
     * @param dest Imagen con la operación aplicada
     * @return Imagen con la operación aplicada
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
        
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixelComp);
                int aux = pixelCompDest[0];
                pixelCompDest[0] = pixelComp[1];
                pixelCompDest[1] = pixelComp[2];
                pixelCompDest[2] = aux;
                
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        
        return dest;
    }
}
