package miproyecto;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LectorTeclado implements KeyListener {

    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    public static boolean a;
    public boolean espacio;

    public LectorTeclado() {
        arriba = false;
        abajo = false;
        izquierda = false;
        derecha = false;
        a = false;
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_A) {

            a = false;

        }

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                arriba = false;
                break;

            case KeyEvent.VK_DOWN:
                abajo = false;
                break;

            case KeyEvent.VK_LEFT:
                izquierda = false;
                break;

            case KeyEvent.VK_RIGHT:
                derecha = false;
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_A) {

            a = true;

        }

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                arriba = true;
               
                break;

            case KeyEvent.VK_DOWN:
              
                    abajo = true;
                
                break;

            case KeyEvent.VK_LEFT:
                izquierda = true;
                break;

            case KeyEvent.VK_RIGHT:
                derecha = true;
                break;

        }

    }

    public boolean getArriba() {
        return arriba;
    }

    public boolean getAbajo() {
        return abajo;
    }

    public boolean getIzquierda() {
        return izquierda;
    }

    public boolean getDerecha() {
        return derecha;
    }

    public boolean getEspacio() {
        return espacio;
    }

    public boolean getA() {
        return a;
    }

}
