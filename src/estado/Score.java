/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import miproyecto.Juego;
import miproyecto.Jugador;
import miproyecto.SpaceWar;

/**
 *
 * @author Daniel Parra
 */
public class Score extends JFrame implements ActionListener {

    //Declaracion de los botones 
    private final JButton btnAceptar, btnEliminar;
    DefaultListModel<Object> a;

    //Declaracion de la lista que mostrara los jugadores menor a tiempo a mayor
    private final JList<Object> listaDeJugadores;

    private SpaceWar spaceWar;

    //Declaracion de etiqueta de texto
    private final JLabel txtIndicador;

    public Score(SpaceWar spaceWar) {
        setLayout(null);
        //Inicilizacion del boton
        btnEliminar = new JButton("Eliminar");
        btnAceptar = new JButton("Aceptar");
        //Posicion y tamaño del boton 
        btnAceptar.setBounds(150, 280, 100, 30);
        btnEliminar.setBounds(270, 280, 100, 30);
        //Adicion del Boton al JFrame          
        getContentPane().add(btnAceptar);
        getContentPane().add(btnEliminar);
        //Adicion del evento al boton
        btnAceptar.addActionListener(this);
        btnEliminar.addActionListener(this);

        this.spaceWar = spaceWar;

        a = new DefaultListModel<>();

        //Se iniciliza la etiqueta y se le asigna un texto
        txtIndicador = new JLabel("Score");
        //Ubicacion del texto
        txtIndicador.setBounds(20, 10, 600, 25);
        //Fuente del texto y tamaño
        txtIndicador.setFont(new java.awt.Font("Arial Black", 10, 20));
        //Adicion de la etiqueta al JFrame
        getContentPane().add(txtIndicador);

        //Se inicializa la lista de jugadores y se agrega al JFrame
        listaDeJugadores = new JList<Object>();
        JScrollPane scroll = new JScrollPane(listaDeJugadores);
        scroll.setBounds(20, 40, 530, 220);
        add(scroll);

        //Agregar icono de la aplicacacion
        setIconImage(new ImageIcon(getClass().getResource("../sources/Icon.png")).getImage());

        //Titulo de la pantalla
        setTitle("Score");
        //Posición y tamaño del JFrame
        setSize(578, 356);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.white);

        //Se agrega el fondo del JFrame
          ((JPanel) getContentPane()).setOpaque(false); ImageIcon uno = new
          ImageIcon(this.getClass().getResource("../sources/FondoScore.jpg"));
          JLabel fondo = new JLabel(); fondo.setIcon(uno);
          getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
          fondo.setBounds(20, 20, uno.getIconWidth(), uno.getIconHeight());
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAceptar) {

            setVisible(false);

        }

        if (e.getSource() == btnEliminar) {
            
            if (!listaDeJugadores.isSelectionEmpty()) {
                spaceWar.getJugadores().remove((Jugador) listaDeJugadores.getSelectedValue());
                actualizarLista();
            
            }else {
            
                JOptionPane.showMessageDialog(null, "Seleccione un jugador para eliminar", "",  JOptionPane.ERROR_MESSAGE);
            }
            
           

        }

    }

    public void actualizarLista() {

        spaceWar.mayorMenor();
        a.clear();

        for (int i = 0; i < spaceWar.getJugadores().size(); i++) {

            a.addElement(spaceWar.getJugadores().get(i));

        }

        listaDeJugadores.setModel(a);
            
    }

}
