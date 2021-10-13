/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import javax.sound.sampled.*;
import java.io.*;

/**
 *
 * @author Pipe
 */
public class Reproductor {

    private static Clip sonidoJuego, sonidoBala, sonidoMuerte;

    public static void cargarAudios(){

        try{
        
        sonidoJuego = AudioSystem.getClip();
        sonidoBala = AudioSystem.getClip();
        sonidoMuerte = AudioSystem.getClip();
        sonidoJuego.open(AudioSystem.getAudioInputStream(new File("src/Sources/Audio.wav")));
        sonidoBala.open(AudioSystem.getAudioInputStream(new File("src/Sources/audioDisparo.wav")));
        sonidoMuerte.open(AudioSystem.getAudioInputStream(new File("src/Sources/audioMuerte.wav")));
        }catch (LineUnavailableException | IOException | UnsupportedAudioFileException a){
            System.out.println(a);
        }
        

    }

    public static void sonarAudioJuego(){

        sonidoJuego.start();
        sonidoJuego.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public static void sonaraBala() {

        sonidoBala.setFramePosition(0);
        sonidoBala.start();

    }

    public static void sonarMuerte() {

        sonidoMuerte.setFramePosition(0);
        sonidoMuerte.start();
    }

}
