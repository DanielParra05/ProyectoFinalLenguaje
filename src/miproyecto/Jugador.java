/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miproyecto;

import java.io.Serializable;

/**
 *
 * @author Daniel Parra
 */
public class Jugador implements Serializable{
    
    private String nombre;
    private int numeroDeMuertos;

    public Jugador(String nombre) {
        numeroDeMuertos = 0;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre+": "+numeroDeMuertos+" enemigos asesinados";
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroDeMuertos(int numeroDeMuertos) {
        this.numeroDeMuertos = numeroDeMuertos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroDeMuertos() {
        return numeroDeMuertos;
    }

     
    
    
    
}
