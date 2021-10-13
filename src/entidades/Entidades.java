package entidades;

import java.awt.Graphics;

public abstract class Entidades {

    protected int x, y;

    public Entidades(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void dibujar(Graphics g);

    public abstract void actualizar();
}
