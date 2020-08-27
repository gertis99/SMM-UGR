
package sm.gcl.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase realizada para la implementación de la operación de posterización
 * @author Germán Castilla López
 */
public class PosterizarOp extends BufferedImageOpAdapter{

    /**
     * Entero que indica el número de niveles al que se
     * reduce la banda
     */
    private int niveles;

    /**
     * Constructor de la clase que inicializa el número de niveles al que se
     * reduce la banda
     * @param niveles: Entero que indica los niveles al que se reduce la banda
     */
    public PosterizarOp(int niveles) {
        this.niveles = niveles;
    }
    
    /**
     * Método para aplicar la operación de posterización sobre la imagen deseada.
     * En este método se hacen todos los calculos necesario para la creación
     * y aplicación de la operación de posterización.
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
        int sample;
        float k = 256.0f/niveles;
        
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                for (int band = 0; band < srcRaster.getNumBands(); band++) {
                    sample = srcRaster.getSample(x, y, band);
                    sample = (int)(k*(int)(sample/k));
                    destRaster.setSample(x, y, band, sample);
                }
            }
        }
        return dest;
    }
    
}
