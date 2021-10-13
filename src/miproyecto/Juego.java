package miproyecto;

import display.Display;
import estado.*;
import graficos.Assets;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import mundos.PropiedadesMundos;
import sound.Reproductor;

public class Juego implements Runnable{

    private static Estado estadoMedio, estadoJuego, estadoPrincipal,
            estadoPerdido, estadoPausa, estadoCreditos, estadoFinal;
    private static Estado estadoActual = null;
    private static Persistencia persistencia;
    public static final int ESTADO_PRINCIPAL = 1;
    public static final int ESTADO_JUEGO = 2;
    public static final int ESTADO_MEDIO = 3;
    public static final int ESTADO_CREDITOS = 4;
    public static final int ESTADO_PERDIDO = 5;
    public static final int ESTADO_PAUSA = 6;
    public static final int ESTADO_FINAL = 7;

  

    public static int estado = 1;

    int width, height, x;
    private static Display display;
    String titulo;
    boolean ejecutando = false;
    Thread hiloPrincipal;
    BufferStrategy bs;
    Graphics g;
    public LectorTeclado lector;
    public EntradaMouse lectorM;
    private  Registrarse registrarse;
    private  Score score;
    private SpaceWar spaceWar;

    public Juego(String titulo, int w, int h) {
        persistencia = new Persistencia();
         try {

            Object objetoPersistente = persistencia.leerObjeto();

            spaceWar = (SpaceWar) objetoPersistente;

        } catch (Exception e) {
         
          spaceWar = new SpaceWar();
        }
       
        score = new Score(spaceWar);
        registrarse = new Registrarse(score, spaceWar);
        lector = new LectorTeclado();
        lectorM = new EntradaMouse(registrarse, score, spaceWar);
        this.width = w;
        this.height = h;
        this.titulo = titulo;
       
    }

    public static void setEstadoActual(Estado estadoActual, int numeroEstado) {
        Juego.estado = numeroEstado;
        Assets.init();
        Juego.estadoActual = estadoActual;

        if (estadoActual instanceof EstadoJuego) {

            ((EstadoJuego) Juego.getEstadoJuego()).setStartYTiempoG(System.currentTimeMillis() / 1000);

        }

    }

    public static Estado getEstadoActual() {
        return estadoActual;
    }

    /**
     * Inicializa los elementos del juego
     */
    private void inicializar() {
        //Inicializar la interfaz para nuestro juego
        display = new Display(titulo, width, height, persistencia, spaceWar);
        //Como solo las ventanas puden escuchar los teclados se le agrega a la ventana
        display.getVentana().addKeyListener(lector);
        display.getMiCanvas().addMouseListener(lectorM);
        //Cargar audios
        Reproductor.cargarAudios();
        Reproductor.sonarAudioJuego();
        //Cargar las propiedades de los mundos
        PropiedadesMundos.cargarPropiedades();
        //Cargra recursos constantes
        Assets.cargarRecursos();
        //Cargar recursos variables
        Assets.init();
        //Creamos los estados de juego
        estadoMedio = new EstadoMedio();
        estadoCreditos = new EstadoCreditos();
        estadoPrincipal = new EstadoPrincipal();
        estadoPerdido = new EstadoPerdido();
        estadoFinal = new EstadoFinal (spaceWar);
        estadoPausa = new EstadoPausa();
        estadoJuego = new EstadoJuego(this, estadoMedio);

        //modificamos el estado actual
        Juego.setEstadoActual(estadoPrincipal, Juego.ESTADO_PRINCIPAL);
    }

    public synchronized void start() {
        if (ejecutando) {
            return;
        }
        ejecutando = true;
        hiloPrincipal = new Thread(this);
        hiloPrincipal.start();
    }

    public synchronized void stop() {
        if (!ejecutando) {
            return;
        }
        ejecutando = false;

        try {
            hiloPrincipal.join();
        } catch (InterruptedException e) {
            System.out.println(e);

        }
    }

    private void actualizar() {
        if (Juego.getEstadoActual() != null) {
            Juego.getEstadoActual().actualizar();
            // Juego.getEstadoActual().
        }
    }

    private void dibujar() {
        /**
         * Es una manera ne que el pc dibuja cosas, y utiliza un buffer para eso
         * es un espacio donde se guardan datos para mostrar y se procesan antes
         * de poderlos visualizar
         */
        bs = display.getMiCanvas().getBufferStrategy();

        if (bs == null) {
            display.getMiCanvas().createBufferStrategy(3);
            return;
        }
        //Encargado de pintar como un pincel :)
        g = bs.getDrawGraphics();
        //Tamaño de las letras
        g.setFont(new Font("Arial", Font.BOLD, 40));

        //Limpiamos el tablero o pantalla
        g.clearRect(0, 0, width, height);
        //Empieza a dibujar
        if (Juego.getEstadoActual() != null) {
            Juego.getEstadoActual().dibujar(g);
        }
        //Finaliza las dibujadas
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        inicializar();
        /**
         * limitar la cantidad de veces que se van a llamar por segundo variabes
         * necesarias para la correcta ejecucion
         */

        int fps = 60;
        double vecesPorActualizacion = 1000000000 / fps;
        double delta = 0;
        long ahora;
        long ultimo = System.nanoTime();
        //Cuenta hasta que llegamos a 1 segundo 
        long timer = 0;
        //Cantidad de veces que dibujamos y actualizamos
        int tick = 0;

        while (ejecutando) {

            ahora = System.nanoTime();
            delta += (ahora - ultimo) / vecesPorActualizacion;
            timer += ahora - ultimo;
            ultimo = ahora;

            if (delta >= 1) {
                actualizar();
                //Actualiza las variables y demas
                dibujar();
                delta--;
                tick++;

            }

            if (timer >= 1000000000) {
                //System.out.println("Tiks " + tick);
                tick = 0;
                timer = 0;
            }

        }

        stop();
    }

    public LectorTeclado getLector() {
        return lector;
    }

    public void setLector(LectorTeclado lector) {
        this.lector = lector;
    }

    public static Estado getEstadoJuego() {
        return estadoJuego;
    }

    public static Estado getEstadoMedio() {
        return estadoMedio;
    }

    public static Estado getEstadoPrincipal() {
        return estadoPrincipal;
    }

    public static Estado getEstadoPerdido() {
        return estadoPerdido;
    }

    public static Estado getEstadoPausa() {
        return estadoPausa;
    }

    public static Estado getEstadoCreditos() {
        return estadoCreditos;
    }

    public static Estado getEstadoFinal() {
        return estadoFinal;
    }
    
    

 
    public static Display getDisplay() {
        return display;
    }


    
   

    

}

