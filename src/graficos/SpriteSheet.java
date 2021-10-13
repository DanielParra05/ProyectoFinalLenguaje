package graficos;


import java.awt.image.*;

public class SpriteSheet {
    /**
     * Spreed imagen que contiene las imagenes pequenas
     */
    private BufferedImage imagen;

    public SpriteSheet(BufferedImage imagen) {
        this.imagen = imagen;
    }
    
    /**
     * Permite conrtar la imagen segun los parametros indicados, 
     * se supone que la imagen es una cuadricula con 
     * imagenes mas pequenas
     * @param col columna donde se encuentra la imagen en pixeles
     * @param row fila donde se encuenta la imagen en pixeles
     * @param w ancho de la imagen a cortar en pixeles
     * @param h alto de la imagen a cortar en pixeles
     * @return  Imagen recortada. 
     */
    public BufferedImage crop (int col, int row, int w, int h){
        return imagen.getSubimage(col, row, w, h);
    }
    
   
    
    
    
    
}
