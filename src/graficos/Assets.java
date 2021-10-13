package graficos;

import estado.EstadoJuego;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import miproyecto.Juego;
import mundos.PropiedadesMundos;

public class Assets {

    // Imagenes estaticas para todo el juego
    public static BufferedImage jugador, enemigoR, fondo, bala, fuego, balaEnemiga, enemigo1;
    //Fondos interfaces
    public static BufferedImage fondoMedio, fondoPerdido, fondoPpal, fondoPausa,
            fondoCreditos1, fondoCreditos2, fondoFinal;
    public static BufferedImage btn1, btn2, btn3, btnPausa;
    public static BufferedImage gif1;
    private static SpriteSheet sheet1, sheet2, sheet3, sheet4, sheet5;
    public static BufferedImage[] statusBar, explosion;

    // Metodo para inicializar los Assets
    public static void cargarRecursos() {

        //Fondos que se cargaran Predeterminadamente 
        fondoMedio = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("FondoMedio"));
        fondoPausa = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("FondoPausa"));
        fondoPerdido = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("HasPerdido"));
        fondoPpal = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("FondoPpal"));
        fondoCreditos1 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("FondoCreditos1"));
        fondoCreditos2 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("FondoCreditos2"));
        fondoFinal = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("FondoFinal"));
        
        sheet1 = new SpriteSheet(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Nave")));
        jugador = sheet1.crop(0, 0, 130, 130);

        sheet2 = new SpriteSheet(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("MiBala")));
        bala = sheet2.crop(0, 230, 76, 70);
        fuego = sheet2.crop(0, 0, 76, 74);

        sheet3 = new SpriteSheet(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("StatusBar")));
        sheet4 = new SpriteSheet(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Explosion")));
        statusBar = new BufferedImage[6];
        explosion = new BufferedImage[19];
        cortarStatusBar();
        explosion();
        btnPausa = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnPausa"));
        balaEnemiga = resize(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("BalaEnemiga")), 110, 30);
    }

    public static void init() {

        switch (Juego.estado) {

            case Juego.ESTADO_PRINCIPAL:

                btn1 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnJugar"));
                btn2 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnCreditos"));
                btn3 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnScore"));

                break;

            case Juego.ESTADO_JUEGO:

                switch (EstadoJuego.mundo) {

                    case EstadoJuego.MUNDO_1:
                        cargarRecursosNivel1();
                        break;

                    case EstadoJuego.MUNDO_2:

                        cargarRecursosNivel2();

                        break;

                    case EstadoJuego.MUNDO_3:
                        cargarRecursosNivel3();

                        break;

                    case EstadoJuego.MUNDO_4:
                        cargarRecursosNivel4();

                        break;

                    case EstadoJuego.MUNDO_5:

                        cargarRecursosNivel5();
                        break;

                    case EstadoJuego.MUNDO_JEFE:

                        cargarRecursosNivelJefe();

                        break;

                }

                break;

            case Juego.ESTADO_PERDIDO:
                btn1 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnVolver"));
                btn2 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnIntentar"));
                break;

            case Juego.ESTADO_MEDIO:

                btn1 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnVolver"));
                btn2 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnContinuar"));

                break;
                
            case Juego.ESTADO_PAUSA:
                btn1 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnVolver"));
                btn2 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnContinuar"));
                break;
                
            case Juego.ESTADO_CREDITOS:
                
                btn1 = resize(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnVolver")), 125, 99);
                break;
                
            case Juego.ESTADO_FINAL:
                
                btn2 = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("btnContinuar"));
                break;

        }

    }

    /**
     * Redimensiona la imagen
     *
     * @param bufferedImage
     * @param newW
     * @param newH
     * @return Imagen redimensionada
     */
    public static BufferedImage resize(BufferedImage bufferedImage, int newH, int newW) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }

    public static void cortarStatusBar() {

        statusBar[0] = sheet3.crop(0, 0, 270, 80);
        statusBar[1] = sheet3.crop(270, 0, 320, 80);
        statusBar[2] = sheet3.crop(0, 100, 320, 80);
        statusBar[3] = sheet3.crop(270, 105, 320, 80);
        statusBar[4] = sheet3.crop(0, 230, 320, 80);
        statusBar[5] = sheet3.crop(270, 230, 320, 80);

    }

    public static void explosion() {

        for (int i = 0; i < explosion.length; i++) {

            explosion[i] = sheet4.crop(50 * i, 0, 51, 125);
            if (i == explosion.length - 1) {
                explosion[i] = sheet4.crop(50 * i + 10, 0, 51, 125);
            }
        }

    }

    public static void cargarRecursosNivel1() {
        sheet4 = new SpriteSheet(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Enemigo1")));
        fondo = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Fondo1"));
        enemigo1 = resize(sheet4.crop(750, 230, 220, 240), 180, 100);

    }

    public static void cargarRecursosNivel2() {

        sheet4 = new SpriteSheet(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Enemigo2")));
        enemigo1 = resize(sheet4.crop(310, 200, 165, 200), 150, 115);
        fondo = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Fondo2"));
    }

    public static void cargarRecursosNivel3() {

        sheet4 = new SpriteSheet(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Enemigo3")));
        enemigo1 = resize(sheet4.crop(0, 0, 250, 300), 230, 180);
        fondo = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Fondo3"));
    }

    public static void cargarRecursosNivel4() {
        enemigo1 = resize(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Enemigo4")), 212, 130);
        fondo = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Fondo4"));
    }

    public static void cargarRecursosNivel5() {
        enemigo1 = resize(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Enemigo5")), 240, 217);
        fondo = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Fondo5"));
    }

    public static void cargarRecursosNivelJefe() {
        enemigo1 = resize(ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Jefe")), 420, 250);
        fondo = ImageLoader.load(PropiedadesMundos.propiedades.getProperty("Fondo6"));
    }

}
