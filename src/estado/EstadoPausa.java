/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado;

import graficos.Assets;
import java.awt.Graphics;

/**
 *
 * @author Daniel Parra
 */
public class EstadoPausa extends Estado{

    @Override
    public void actualizar() {
        
    }

    @Override
    public void dibujar(Graphics g) {
      g.drawImage(Assets.fondoPausa, 0, 0, null);
      g.drawImage(Assets.btn1, 100, 330, null);
      g.drawImage(Assets.btn2, 880, 330, null);
    }
    
}
