package display;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import miproyecto.Persistencia;
import miproyecto.SpaceWar;

public class Display extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JFrame ventana;
    private final String titulo;
    private int width;
    private int height;
    private JButton btnPausa;
    private Canvas miCanvas;
    private Persistencia persistencia;
    private SpaceWar spaceWar;

    public Display(String titulo, int width, int height, 
            Persistencia persistencia, SpaceWar spaceWar) {
        super();
        this.persistencia = persistencia;
        this.spaceWar = spaceWar;
        this.titulo = titulo;
        this.width = width;
        this.height = height;
        createDisplay(); // Inicializa componentes de la ventna
        agregarEventoAlCerrar();

    }

    public void createDisplay() {

        ventana = new JFrame(titulo);

        ventana.setSize(width, height);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        // Cambiar el icono de la ventana
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../sources/Icon.png"));
        ventana.setIconImage(icon);

        //Boton Pausa
        btnPausa = new JButton("Pausar");
        btnPausa.setSize(100, 100);
        //ventana.add(btnPausa);
        ventana.setVisible(true);

        miCanvas = new Canvas();
        miCanvas.setPreferredSize(new Dimension(width, height));
        miCanvas.setPreferredSize(new Dimension(width, height));
        miCanvas.setMaximumSize(new Dimension(width, height));
        miCanvas.setMinimumSize(new Dimension(width, height));
        miCanvas.setFocusable(false);
        ventana.add(miCanvas);
        ventana.pack();

    }

    public void agregarEventoAlCerrar() {
        ventana.addWindowListener(new WindowAdapter() {

            @Override

            public void windowClosing(WindowEvent we) {

                try {

                    persistencia.escribirObjeto(spaceWar);

                } catch (Exception e) {
                    System.out.println(e);
                }

                System.exit(0);

            }

        });

    }

    public JFrame getVentana() {
        return ventana;
    }

    public void setVentana(JFrame ventana) {
        this.ventana = ventana;
    }

    public Canvas getMiCanvas() {
        return miCanvas;
    }

    public void setMiCanvas(Canvas miCanvas) {
        this.miCanvas = miCanvas;
    }

}
