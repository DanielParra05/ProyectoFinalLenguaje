package entidades;

import java.awt.Graphics;
import java.util.ArrayList;

import graficos.Assets;
import miproyecto.Juego;
import miproyecto.Launcher;
import sound.Reproductor;

public class Jugador1 extends Entidades {

    private final Juego miJuego;
    private int x, y;
    private boolean var;
    private ArrayList<Bala1> balas;
    private int vida;

    public Jugador1(int x, int y, Juego miJuego) {
        super(x, y);
        this.miJuego = miJuego;
        this.x = x;
        this.y = y;
        var = true;
        balas = new ArrayList<>();
        vida = 100;

    }

    @Override
    public void dibujar(Graphics g) {
        // TODO Auto-generated method stub

        g.drawImage(Assets.jugador, x, y, null);

        dibujarBalaYfuego(g);
    }

    @Override
    public void actualizar() {

        crearBala();
        eliminarBala();
        moverNave();

    }

    /**
     * Crea la bala :D
     */
    public void crearBala() {

        if (var == true) {

            if (miJuego.getLector().getA()) {

                balas.add(new Bala1(x + 23, y - 40));

                Reproductor.sonaraBala();
                var = false;
            }
        }

        if (!miJuego.getLector().getA()) {
            var = true;
        }

    }

    public void eliminarBala() {

        for (int i = 0; i < balas.size(); i++) {

            balas.get(i).actualizar();

            if (balas.get(i).getY() < -318) {

                balas.remove(i);

            }

        }
    }

    public void moverNave() {
        if (miJuego.getLector().getArriba()) {

            if (y > Launcher.getTamanio().height - 400) {
                y -= 4;
            }

        }

        if (miJuego.getLector().getAbajo()) {

            if (y < Launcher.getTamanio().height - 220) {
                y += 4;
            }

        }

        if (miJuego.getLector().getDerecha()) {
            if (x < Launcher.getTamanio().width - 320) {
                x += 4;
            }

        }
        if (miJuego.getLector().getIzquierda()) {
            if (x > -5) {
                x -= 4;
            }

        }

    }

    public void dibujarBalaYfuego(Graphics g) {
        for (int i = 0; i < balas.size(); i++) {
            balas.get(i).dibujar(g);

        }

        if (miJuego.getLector().arriba || miJuego.getLector().abajo
                || miJuego.getLector().izquierda || miJuego.getLector().derecha) {
            g.drawImage(Assets.fuego, x + 28, y + 80, null);
        }

        if (miJuego.getLector().arriba) {
            g.drawImage(Assets.fuego, x + 28, y + 80, null);
        }

    }

    public ArrayList<Bala1> getBalas() {
        return balas;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

 
    public int getVida() {
        return vida;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    
    
        
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

}
