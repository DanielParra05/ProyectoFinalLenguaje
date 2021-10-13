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
public class EstadoCreditos extends Estado{
    
    private int y1, y2;

    public EstadoCreditos() {
        
        y1= 0;
        y2 = 719;
    }
    
    

    @Override
    public void actualizar() {
        actualizarFondo();
      
    }

    @Override
    public void dibujar(Graphics g) {
      
        
      g.drawImage(Assets.fondoCreditos2, 0, y1, null);
      g.drawImage(Assets.fondoCreditos1, 0, y2, null);
      g.drawImage(Assets.btn1, 0, 545, null);
    }
    
    public void actualizarFondo(){
        
        y1-=3;
        y2-=3;
        
       if (y1 <= -719) {
       y1=700;
       }
     
       if (y2 <=-719) {
       y2=700;
       }
       
    }
    
}
