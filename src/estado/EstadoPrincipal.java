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
public class EstadoPrincipal extends Estado {
    
   

    @Override
    public void actualizar() {
        
       
      
    }

    @Override
    public void dibujar(Graphics g) {
        
        g.drawImage(Assets.fondoPpal, 0,0 ,null);
        g.drawImage(Assets.btn1, 140,300 ,null);
        g.drawImage(Assets.btn2, 480, 300,null);
        g.drawImage (Assets.btn3,820,300,null);
        
        
      
     
    }

    

}
