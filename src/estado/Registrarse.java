/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import miproyecto.Juego;
import miproyecto.Jugador;
import miproyecto.Launcher;
import miproyecto.SpaceWar;

/**
 *
 * @author Daniel Parra
 */
public class Registrarse extends JFrame implements ActionListener {

    private JButton btnVolver, btnAceptar;
    private JTextField cmpId;
    private JLabel txtNombre;
    private SpaceWar spaceWar;
    
    public Registrarse(Score score, SpaceWar spaceWar) {

        setLayout(null);
        btnVolver = new JButton("Volver");
        btnAceptar = new JButton("Aceptar");
        //Ubicacion y tamaño de los botones
        btnAceptar.setBounds(60, 180, 100, 30);
        btnVolver.setBounds(220, 180, 100, 30);
        //Adicion del evento al boton
        btnAceptar.addActionListener(this);
        btnVolver.addActionListener(this);
        this.spaceWar = spaceWar;
        //Adicion del boton JFrame
        add(btnAceptar);
        add(btnVolver);

        //Inicializacion de la etiqueta de texto
        txtNombre = new JLabel("Ingrese su nombre:");
        //Ubicacion y tamaño de la etiqueta
        txtNombre.setBounds(135, -30, 600, 150);
        //Adicion de la etiqueta al JFrame
        getContentPane().add(txtNombre);

        //Inicializacion del campo de texto
        cmpId = new JTextField();
        //Ubicacion y tamaño
        cmpId.setBounds(140, 55, 100, 25);
        //Adicion del campo de texto al JFrame
        add(cmpId);

        setIconImage(new ImageIcon(getClass().getResource("../sources/Icon.png")).getImage()); //Agregar icono de la aplicacacion

        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon uno = new ImageIcon(this.getClass().getResource("../sources/fondoRegistrar.jpg"));
        JLabel fondo = new JLabel();
        fondo.setIcon(uno);
        getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(50, 0, uno.getIconWidth(), uno.getIconHeight());
        setBackground(Color.white);

        setSize(380, 270); //Tamaño y posicion del JFrame
        setResizable(false);
        setLocationRelativeTo(null);
       
        setAlwaysOnTop(true);
        
         setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
 
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                Juego.getDisplay().getMiCanvas().setEnabled(true);
                setVisible(false);
                System.out.println();
            }
        });

 
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnVolver) {

            setVisible(false);
           Juego.getDisplay().getMiCanvas().setEnabled(true);
        }

        if (e.getSource() == btnAceptar) {

            if (!cmpId.getText().trim().equals("")) {
                setVisible(false);
                Juego.getDisplay().getMiCanvas().setEnabled(true);
                spaceWar.getJugadores().add(new Jugador(cmpId.getText()));
                cmpId.setText("");
                Juego.setEstadoActual(Juego.getEstadoJuego(), Juego.ESTADO_JUEGO);
                EstadoJuego.setEnemigosMuertos(0);
                EstadoJuego.setMundo(1);
                ((EstadoJuego) Juego.getEstadoJuego()).getEntidades().clear();
                ((EstadoJuego) Juego.getEstadoJuego()).getNave().setX((Launcher.getTamanio().width - 300) / 2);
                ((EstadoJuego) Juego.getEstadoJuego()).getNave().setY(Launcher.getTamanio().height - 220);
                ((EstadoJuego) Juego.getEstadoJuego()).getNave().setVida(100);
                ((EstadoJuego) Juego.getEstadoJuego()).getNave().getBalas().clear();
                Juego.setEstadoActual(Juego.getEstadoJuego(), Juego.ESTADO_JUEGO);
                
            } else {

                setAlwaysOnTop(false);
                int respuesta = JOptionPane.showConfirmDialog(null, "Debe ingresar un nombre para jugar!!",
                        "", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION);
                
                if (respuesta == JOptionPane.CLOSED_OPTION || respuesta == JOptionPane.OK_OPTION) {
                    setAlwaysOnTop(true);
                }

            }

        }

    }
    
   

}
