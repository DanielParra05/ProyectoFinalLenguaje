package entidades;

import java.awt.Graphics;

import graficos.Assets;

public class Bala1 extends Entidades {

    private int x, y;

    public Bala1(int x, int y) {
        super(x, y);
        // TODO Auto-generated constructor stub
        this.x = x;
        this.y = y;

    }

    @Override
    public void dibujar(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(Assets.bala, x, y, null);

    }

    @Override
    public void actualizar() {
        // TODO Auto-generated method stub

        y -= 14;

    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    
}
