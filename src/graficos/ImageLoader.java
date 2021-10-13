package graficos;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageLoader {

    /**
     * Funcilnalidad que permite cargar una imagen
     * @param path ruta donde esta la imagen
     * @return retorna una nueva imagen cargada desde la ruta, si existe.
     */
    public static BufferedImage load(String path) {

        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        return null;

    }

}
