package entidades;

import java.awt.Graphics;
import graficos.Assets;


public class BalaEnemiga extends Entidades{
	
	

	public BalaEnemiga(int x, int y) {
		super(x, y);
		
	}

	@Override
	public void dibujar(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.balaEnemiga, x, y, null);
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		y += 12;
		
	}

        @Override
    public int getX() {
        return x;
    }

        @Override
    public int getY() {
        return y;
    }
        
        

}
