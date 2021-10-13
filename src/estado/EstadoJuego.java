package estado;

import graficos.Assets;
import miproyecto.Juego;
import java.awt.*;
import entidades.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import miproyecto.Launcher;
import sound.Reproductor;

public class EstadoJuego extends Estado {

    public int x1, x2;
    public Juego miJuego;
    private Jugador1 nave;
    private final ArrayList<Entidades> entidades;
    public static final int MUNDO_1 = 1;
    public static final int MUNDO_2 = 2;
    public static final int MUNDO_3 = 3;
    public static final int MUNDO_4 = 4;
    public static final int MUNDO_5 = 5;
    public static final int MUNDO_JEFE = 6;
    public static int mundo = 1;
    private static long start, tiempoG;
    private static int enemigosMuertos;
    //Para la explosion 
    private BufferedImage explosion;
    //Coordenadas de la explosion
    private int x, y;

    public EstadoJuego(Juego miJuego, Estado estadoMedio) {
        super();
        explosion = Assets.explosion[Assets.explosion.length - 1];
        this.miJuego = miJuego;
        x = 0;
        y = 0;
        x1 = 0;
        x2 = 1600;
        enemigosMuertos = 0;

        entidades = new ArrayList<>();
        //Posicion por default para el enemigo
        tiempoG = 0;
        start = 0;

        //enemigos.add(new Enemigo(200, -190));
        //enemigos.add(new Enemigo(-99, 0));
        //Mundo 2
        //entidades.add(new Enemigo(((Launcher.getTamanio().width - 300) / 2), -100));
        //Mundo 1
        //entidades.add(new Enemigo(-90, -40));
        //enemigos.add(new Enemigo(InterfacePrincipal.getTamanio().width - 300, InterfacePrincipal.getTamanio().height - 150));
        //entidades.add(new Enemigo(0, -400));
        nave = new Jugador1((Launcher.getTamanio().width - 300) / 2, Launcher.getTamanio().height - 220, miJuego);

    }

    @Override
    public void actualizar() {
        perderVidaJugador();
        nave.actualizar();

        switch (mundo) {

            case MUNDO_1:
                //Toma del tiempo (cada caso un segundo)
                if ((System.currentTimeMillis() / 1000) - start == 10) {

                    mundo = MUNDO_2;
                    entidades.clear();
                    Assets.init();

                    nave.setX((Launcher.getTamanio().width - 300) / 2);
                    nave.setX(Launcher.getTamanio().height - 220);
                    nave.setVida(100);
                    Juego.setEstadoActual(Juego.getEstadoMedio(), Juego.ESTADO_MEDIO);

                }
                perderVidaEnemigos1();
                generaraEnemigosMundo1();
                generarBalasMundo1();
                break;

            case MUNDO_2:

                if ((System.currentTimeMillis() / 1000) - start == 10) {
                    mundo = MUNDO_3;
                    Assets.init();
                    entidades.clear();

                    nave.setX((Launcher.getTamanio().width - 300) / 2);
                    nave.setX(Launcher.getTamanio().height - 220);
                    nave.setVida(100);
                    Juego.setEstadoActual(Juego.getEstadoMedio(), Juego.ESTADO_MEDIO);
                }

                perderVidaEnemigos2();
                generarBalasMundo2();
                generarEnemigosMundo2();
                break;

            case MUNDO_3:

                if ((System.currentTimeMillis() / 1000) - start == 10) {
                    mundo = MUNDO_4;
                    Assets.init();
                    entidades.clear();

                    nave.setX((Launcher.getTamanio().width - 300) / 2);
                    nave.setX(Launcher.getTamanio().height - 220);
                    nave.setVida(100);
                    Juego.setEstadoActual(Juego.getEstadoMedio(), Juego.ESTADO_MEDIO);
                }

                perderVidaEnemigos3();
                generarBalasMundo3();
                generarEnemmigosMundo3();
                actualizarFondo();

                break;

            case MUNDO_4:
                if ((System.currentTimeMillis() / 1000) - start == 10) {

                    mundo = MUNDO_5;
                    Assets.init();
                    entidades.clear();

                    nave.setX((Launcher.getTamanio().width - 300) / 2);
                    nave.setX(Launcher.getTamanio().height - 220);
                    nave.setVida(100);
                    Juego.setEstadoActual(Juego.getEstadoMedio(), Juego.ESTADO_MEDIO);
                }
                perderVidaEnemigos4();
                generarEnemigosMundo4();
                generarBalasMundo4();
                break;

            case MUNDO_5:
                if ((System.currentTimeMillis() / 1000) - start == 10) {

                    mundo = MUNDO_JEFE;
                    Assets.init();
                    entidades.clear();

                    nave.setX((Launcher.getTamanio().width - 300) / 2);
                    nave.setX(Launcher.getTamanio().height - 220);
                    nave.setVida(100);
                    
                    Juego.setEstadoActual(Juego.getEstadoMedio(), Juego.ESTADO_MEDIO);
                    
                }
                generarBalasMundo5();
                generarEnemigosMundo5();
                perderVidaEnemigos5();
                break;

            case MUNDO_JEFE:

                
                perderVidaJefe();
                generarBalasMundo6();
                break;
        }

    }

    @Override
    public void dibujar(Graphics g) {

        switch (mundo) {
            case MUNDO_3:

                //Dibujar el fondo con variables para el movimiento
                dibujarFondo(g);
                break;

            default:
                g.drawImage(Assets.fondo, 0, 0, null);
                break;

        }

        //Ejecutar dibujar nave
        nave.dibujar(g);
        //Dibujar, actualizar y eliminar enemigos
        dibujarArrayLEnemigo(g);
        //dinujar bala 
        dibujarArrayBalas(g);
        //Dibujar explosion 
        g.drawImage(explosion, x, y, null);
        //Ejecutar la barrita de vida
        dibujarBarra(g);
        //Dibujo Cantidad de enemigos muertos
        g.setColor(Color.BLACK);
        g.fillRect(Launcher.getTamanio().width - 300, Launcher.getTamanio().height - 170, 100, 70);
        g.setColor(Color.WHITE);
        g.drawString(enemigosMuertos + "", Launcher.getTamanio().width - 270, Launcher.getTamanio().height - 120);
        g.drawImage(Assets.btnPausa, Launcher.getTamanio().width - 245, 0, null);

    }

    /**
     * Dibujar barra
     *
     * @param g pincel
     */
    public void dibujarBarra(Graphics g) {

        if (nave.getVida() <= 100 && nave.getVida() > 70) {
            g.drawImage(Assets.statusBar[0],
                    -25, Launcher.getTamanio().height - 160, null);

        }

        if (nave.getVida() <= 70 && nave.getVida() > 40) {

            g.drawImage(Assets.statusBar[1], -75, Launcher.getTamanio().height - 156,
                    null);
        }

        if (nave.getVida() <= 40 && nave.getVida() > 20) {

            g.drawImage(Assets.statusBar[2], -27, Launcher.getTamanio().height - 182,
                    null);
        }
        if (nave.getVida() <= 20 && nave.getVida() > 10) {
            g.drawImage(Assets.statusBar[3], -70, Launcher.getTamanio().height - 178,
                    null);
        }

        if (nave.getVida() <= 10 && nave.getVida() > 0) {
            g.drawImage(Assets.statusBar[4],
                    -27, Launcher.getTamanio().height - 167, null);

        }

        if (nave.getVida() <= 0) {
            g.drawImage(Assets.statusBar[5], -77,
                    Launcher.getTamanio().height - 171, null);

        }

    }

    public void dibujarFondo(Graphics g) {
        g.drawImage(Assets.fondo, x1, 0, null);
        g.drawImage(Assets.fondo, x2, 0, null);
    }

    /**
     * Actualiza el fondo para que vaya hacia arriba o hacia abajo
     */
    public void actualizarFondo() {

        if (miJuego.getLector().derecha) {
            x1 -= 10;
            x2 -= 10;

            if (x1 <= -1600) {
                x1 = 1590;

            }
            if (x2 <= -1600) {

                x2 = 1590;
            }
        }

        if (miJuego.getLector().izquierda) {
            x1 += 10;
            x2 += 10;
            if (x1 >= 1600) {
                x1 = -1590;

            }
            if (x2 >= 1600) {
                x2 = -1590;
            }

        }

    }

    //Metodos Mundo 1
    public void generaraEnemigosMundo1() {

        if ((System.currentTimeMillis() / 1000) - tiempoG == 2) {

            entidades.add(new Enemigo(-90, -40));
            tiempoG = System.currentTimeMillis() / 1000;
        }

    }

    public void generarBalasMundo1() {
        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {
                if (entidades.get(i).getX() % 140 == 0) {

                    entidades.add(new BalaEnemiga(entidades.get(i).getX() + 35, entidades.get(i).getY() + 140));
                }

            }

        }
    }

    private void perderVidaEnemigos1() {

        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {

                for (int j = 0; j < nave.getBalas().size(); j++) {

                    if (entidades.get(i).getX() - 40 < nave.getBalas().get(j).getX() && entidades.get(i).getX() > nave.getBalas().get(j).getX() - 55
                            && entidades.get(i).getY() - 15 < nave.getBalas().get(j).getY() && entidades.get(i).getY() > nave.getBalas().get(j).getY() - 140) {

                        nave.getBalas().remove(j);
                        ((Enemigo) entidades.get(i)).setVida(((Enemigo) entidades.get(i)).getVida() - 20);
                        if (((Enemigo) entidades.get(i)).getVida() <= 0) {
                            enemigosMuertos++;
                            Reproductor.sonarMuerte();
                            explosion(entidades.get(i).getX() + 30, entidades.get(i).getY() + 20);
                            entidades.remove(i);
                            break;

                        }

                    }
                }

            }
        }

    }

    //Metodos mundo 2
    public void perderVidaEnemigos2() {

        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {

                for (int j = 0; j < nave.getBalas().size(); j++) {

                    if (entidades.get(i).getX() - 40 < nave.getBalas().get(j).getX() && entidades.get(i).getX() > nave.getBalas().get(j).getX() - 70
                            && entidades.get(i).getY() - 15 < nave.getBalas().get(j).getY() && entidades.get(i).getY() > nave.getBalas().get(j).getY() - 90) {

                        nave.getBalas().remove(j);
                        ((Enemigo) entidades.get(i)).setVida(((Enemigo) entidades.get(i)).getVida() - 15);
                        if (((Enemigo) entidades.get(i)).getVida() <= 0) {
                            Reproductor.sonarMuerte();
                            enemigosMuertos++;
                            explosion(entidades.get(i).getX(), entidades.get(i).getY());
                            entidades.remove(i);
                            break;

                        }

                    }
                }

            }
        }

    }

    public void generarEnemigosMundo2() {

        if ((System.currentTimeMillis() / 1000) - tiempoG == 2) {

            entidades.add(new Enemigo(((Launcher.getTamanio().width - 300) / 2), -100));

            tiempoG = System.currentTimeMillis() / 1000;
        }

        /**
         * if (enemigos.get(enemigos.size() - 1).getX() == 1189 &&
         * enemigos.get(enemigos.size() - 1).getY() == 164) { }
         */
    }

    public void generarBalasMundo2() {
        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {

                if (entidades.get(i).getX() % 35 == 0) {

                    entidades.add(new BalaEnemiga(entidades.get(i).getX() + 40, entidades.get(i).getY() + 120));
                }
            }

        }

    }

    //Metodos mundo 3 
    public void perderVidaEnemigos3() {

        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {

                for (int j = 0; j < nave.getBalas().size(); j++) {

                    if (entidades.get(i).getX() - 15 < nave.getBalas().get(j).getX() && entidades.get(i).getX() > nave.getBalas().get(j).getX() - 110
                            && entidades.get(i).getY() - 15 < nave.getBalas().get(j).getY() && entidades.get(i).getY() > nave.getBalas().get(j).getY() - 180) {

                        nave.getBalas().remove(j);
                        ((Enemigo) entidades.get(i)).setVida(((Enemigo) entidades.get(i)).getVida() - 15);
                        if (((Enemigo) entidades.get(i)).getVida() <= 0) {
                            explosion(entidades.get(i).getX() + 80, entidades.get(i).getY() + 50);
                            enemigosMuertos++;
                            Reproductor.sonarMuerte();
                            entidades.remove(i);
                            break;

                        }

                    }
                }

            }
        }

    }

    public void generarEnemmigosMundo3() {

        if ((System.currentTimeMillis() / 1000) - tiempoG == 3) {
            entidades.add(new Enemigo(-99, 0));
            tiempoG = System.currentTimeMillis() / 1000;
        }

    }

    public void generarBalasMundo3() {
        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {

                if (entidades.get(i).getX() % 40 == 0) {

                    entidades.add(new BalaEnemiga(entidades.get(i).getX() + 75, entidades.get(i).getY() + 210));
                }
            }

        }

    }

    //Metodos mundo 4
    public void perderVidaEnemigos4() {
        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {

                for (int j = 0; j < nave.getBalas().size(); j++) {

                    if (entidades.get(i).getX() - 30 < nave.getBalas().get(j).getX() && entidades.get(i).getX() > nave.getBalas().get(j).getX() - 80
                            && entidades.get(i).getY() - 15 < nave.getBalas().get(j).getY() && entidades.get(i).getY() > nave.getBalas().get(j).getY() - 160) {

                        nave.getBalas().remove(j);
                        ((Enemigo) entidades.get(i)).setVida(((Enemigo) entidades.get(i)).getVida() - 12);
                        if (((Enemigo) entidades.get(i)).getVida() <= 0) {
                            enemigosMuertos++;
                            explosion(entidades.get(i).getX() + 40, entidades.get(i).getY() + 80);
                            Reproductor.sonarMuerte();
                            entidades.remove(i);
                            break;

                        }

                    }
                }

            }
        }
    }

    public void generarEnemigosMundo4() {

        if ((System.currentTimeMillis() / 1000) - tiempoG == 3) {

            entidades.add(new Enemigo(200, -190));
            tiempoG = System.currentTimeMillis() / 1000;
        }

    }

    public void generarBalasMundo4() {

        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {

                if (entidades.get(i).getX() % 40 == 0) {

                    entidades.add(new BalaEnemiga(entidades.get(i).getX() + 45, entidades.get(i).getY() + 180));
                }
            }

        }

    }

    //Metodos mundo 5
    public void perderVidaEnemigos5() {
        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {

                for (int j = 0; j < nave.getBalas().size(); j++) {

                    if (entidades.get(i).getX() - 10 < nave.getBalas().get(j).getX() && entidades.get(i).getX() > nave.getBalas().get(j).getX() - 150
                            && entidades.get(i).getY() - 15 < nave.getBalas().get(j).getY() && entidades.get(i).getY() > nave.getBalas().get(j).getY() - 160) {

                        nave.getBalas().remove(j);
                        ((Enemigo) entidades.get(i)).setVida(((Enemigo) entidades.get(i)).getVida() - 10);
                        if (((Enemigo) entidades.get(i)).getVida() <= 0) {
                            enemigosMuertos++;
                            explosion(entidades.get(i).getX() + 50, entidades.get(i).getY() + 20);
                            Reproductor.sonarMuerte();
                            entidades.remove(i);
                            break;

                        }

                    }
                }

            }
        }
    }

    public void generarEnemigosMundo5() {
        if ((System.currentTimeMillis() / 1000) - tiempoG == 3) {
            entidades.add(new Enemigo(Launcher.getTamanio().width - 300, Launcher.getTamanio().height - 150));
            tiempoG = System.currentTimeMillis() / 1000;
        }
    }

    public void generarBalasMundo5() {

        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i) instanceof Enemigo) {

                if (entidades.get(i).getY() % 40 == 0) {

                    entidades.add(new BalaEnemiga(entidades.get(i).getX() + 90, entidades.get(i).getY() + 85));
                }
            }

        }

    }


    //Metodos mundo jefe 
    public void generarBalasMundo6() {
        for (int i = 0; +i < entidades.size(); i++) {
            if (entidades.get(i) instanceof Enemigo) {
                if (entidades.get(i).getX() % 200 == 0) {

                    entidades.add(new BalaEnemiga(entidades.get(i).getX() + 120, entidades.get(i).getY() + 320));
                }
            }
        }
    }
    
    private void perderVidaJefe() {

        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {

                for (int j = 0; j < nave.getBalas().size(); j++) {

                    if (entidades.get(i).getX() - 20 < nave.getBalas().get(j).getX() && entidades.get(i).getX() > nave.getBalas().get(j).getX() - 200
                            && entidades.get(i).getY() - 15 < nave.getBalas().get(j).getY() && entidades.get(i).getY() > nave.getBalas().get(j).getY() - 350) {

                        nave.getBalas().remove(j);
                        ((Enemigo) entidades.get(i)).setVida(((Enemigo) entidades.get(i)).getVida() - 1);
                        if (((Enemigo) entidades.get(i)).getVida() <= 0) {
                            enemigosMuertos++;
                            explosion(entidades.get(i).getX() + 40, entidades.get(i).getY() + 80);
                            Reproductor.sonarMuerte();
                            entidades.remove(i);
                            Juego.setEstadoActual(Juego.getEstadoFinal(), Juego.ESTADO_FINAL);
                            break;

                        }

                    }
                }

            }
        }

    }

    public void dibujarArrayLEnemigo(Graphics g) {

        //Dibujar y actualizar cada enemigo
        for (int j = 0; j < entidades.size(); j++) {

            if (entidades.get(j) instanceof Enemigo) {

                entidades.get(j).actualizar();
                entidades.get(j).dibujar(g);

                //Condiciones para eliminar los enemigos
                if (mundo == MUNDO_1) {

                    if (entidades.get(j).getX() < -100) {
                        entidades.remove(j);

                    }
                }

                if (mundo == MUNDO_2 || mundo == MUNDO_3 || mundo == MUNDO_4) {

                    if (entidades.get(j).getX() > Launcher.getTamanio().width - 100) {

                        entidades.remove(j);

                    }

                }

                if (mundo == MUNDO_5) {

                    if (entidades.get(j).getY() > Launcher.getTamanio().height) {
                        entidades.remove(j);
                    }

                }
            }

        }

    }

    public void dibujarArrayBalas(Graphics g) {
        for (int i = 0; i < entidades.size(); i++) {
            //System.out.println("dibujo");

            if (entidades.get(i) instanceof BalaEnemiga) {

                entidades.get(i).actualizar();
                entidades.get(i).dibujar(g);
                if (entidades.get(i).getY() > Launcher.getTamanio().height) {
                    entidades.remove(i);
                }
            }

        }
    }

    public void perderVidaJugador() {

        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof BalaEnemiga) {

                if (nave.getX() - 20 < entidades.get(i).getX() && nave.getX() > entidades.get(i).getX() - 115
                        && nave.getY() < entidades.get(i).getY() + 90 && nave.getY() + 50 > entidades.get(i).getY()) {

                    entidades.remove(i);

                    nave.setVida(nave.getVida() - 5);

                    if (nave.getVida() <= 0) {

                        explosion(nave.getX() + 40, nave.getY());
                        Juego.setEstadoActual(Juego.getEstadoPerdido(), Juego.ESTADO_PERDIDO);

                    }
                }
            }
        }
    }

    public void explosion(int xExplosion, int yExplosion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Assets.explosion.length; i++) {
                    try {
                        x = xExplosion;
                        y = yExplosion;
                        explosion = Assets.explosion[i];
                        //System.out.println("imprimi");
                        Thread.currentThread().sleep(30);
                    } catch (InterruptedException e) {
                    }

                }

            }
        }).start();
    }

    public void setStartYTiempoG(long start) {
        EstadoJuego.start = start;
        EstadoJuego.tiempoG = start;
    }

    public Jugador1 getNave() {
        return nave;
    }

    public void setNave(Jugador1 nave) {
        this.nave = nave;
    }

    public ArrayList<Entidades> getEntidades() {
        return entidades;
    }

    public static void setMundo(int mundo) {
        EstadoJuego.mundo = mundo;
    }

    public static int getEnemigosMuertos() {
        return enemigosMuertos;
    }

    public static void setEnemigosMuertos(int enemigosMuertos) {
        EstadoJuego.enemigosMuertos = enemigosMuertos;
    }

    public static int getMundo() {
        return mundo;
    }
    
    

}
