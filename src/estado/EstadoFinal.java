/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado;

import graficos.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import miproyecto.Launcher;
import miproyecto.SpaceWar;

/**
 *
 * @author Daniel Parra
 */
public class EstadoFinal extends Estado{
    
    private SpaceWar spaceWar;

    public EstadoFinal(SpaceWar spaceWar) {
        this.spaceWar = spaceWar;
    }
    
    

    @Override
    public void actualizar() {
        
    }

    @Override
    public void dibujar(Graphics g) {
       g.drawImage(Assets.fondoFinal, 0, 0, null);
       g.drawImage(Assets.btn2, 980, 0, null);
        g.setFont(new Font("AR DESTINE", Font.BOLD, 65));
       g.setColor(Color.BLACK);
       g.drawString(spaceWar.getJugadores().
               get(spaceWar.getJugadores().size()-1).getNumeroDeMuertos()+1+"", 
               Launcher.getTamanio().width -1080, Launcher.getTamanio().height - 155);
    }
    
}
