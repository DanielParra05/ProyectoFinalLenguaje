package miproyecto;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Launcher {

    private static Dimension tamanio;
    private static Juego juego;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

            tamanio = Toolkit.getDefaultToolkit().getScreenSize();
            juego = new Juego("SpaceWars", tamanio.width - 200, tamanio.height - 100);
            juego.start();
        
    }

    public static Dimension getTamanio() {
        return tamanio;
    }
    
    public static Juego getJuego() {
        return juego;
    }

}
