/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miproyecto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Daniel Parra
 */
public class SpaceWar implements Serializable{
 
    private ArrayList<Jugador> jugadores;
    
    public SpaceWar(){
    
     jugadores = new ArrayList<>();
    
    }
    
    public void mayorMenor() {
        Jugador aux;
        for (int i = 0; i < jugadores.size(); i++) {
            for (int j = i + 1; j < jugadores.size(); j++) {
                if (jugadores.get(i).getNumeroDeMuertos() < jugadores.get(j).getNumeroDeMuertos()) {
                    aux =jugadores.get(i);
                    jugadores.set(i, jugadores.get(j));
                 jugadores.set(j, aux);
                }
            }
        }
    }

    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
}
