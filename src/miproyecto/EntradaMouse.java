/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miproyecto;

import entidades.Enemigo;
import estado.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Daniel Parra
 */
public class EntradaMouse implements MouseListener {

    private Registrarse registrarse;
    private Score score;
    private SpaceWar spaceWar;

    public EntradaMouse(Registrarse registrarse, Score score, SpaceWar spaceWar) {
        this.registrarse = registrarse;
        this.score = score;
        this.spaceWar = spaceWar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        switch (Juego.estado) {
            case Juego.ESTADO_PERDIDO:
                if (Juego.getEstadoActual() instanceof EstadoPerdido) {

                    //Boton Salir
                    if (e.getX() >= 302 && e.getX() <= 480
                            && e.getY() >= 401 && e.getY() <= 580) {
                        accionesParaSalir();
                       
                    }

                    //Boton Volver a intentar
                    if (e.getX() >= 600 && e.getX() <= 781
                            && e.getY() >= 401 && e.getY() <= 577) {
                        ((EstadoJuego) Juego.getEstadoJuego()).getEntidades().clear();
                        ((EstadoJuego) Juego.getEstadoJuego()).getNave().setX((Launcher.getTamanio().width - 300) / 2);
                        ((EstadoJuego) Juego.getEstadoJuego()).getNave().setY(Launcher.getTamanio().height - 220);
                        ((EstadoJuego) Juego.getEstadoJuego()).getNave().setVida(100);
                        ((EstadoJuego) Juego.getEstadoJuego()).getNave().getBalas().clear();
                        Juego.setEstadoActual(Juego.getEstadoJuego(), Juego.ESTADO_JUEGO);
                        EstadoJuego.setEnemigosMuertos(0);

                    }

                }
                break;
            case Juego.ESTADO_PRINCIPAL:

                //Jugar
                if (Juego.getEstadoActual() instanceof EstadoPrincipal) {

                    if (e.getX() >= 142 && e.getX() <= 318
                            && e.getY() >= 302 && e.getY() <= 477) {

                        Juego.getDisplay().getMiCanvas().setEnabled(false);
                       
                        registrarse.setVisible(true);

                    }
                    //Creditos
                    if (e.getX() >= 482 && e.getX() <= 660
                            && e.getY() >= 302 && e.getY() <= 479) {
                        Juego.setEstadoActual(Juego.getEstadoCreditos(), Juego.ESTADO_CREDITOS);

                    }
                    //Score
                    if (e.getX() >= 822 && e.getX() <= 999
                            && e.getY() >= 302 && e.getY() <= 479) {

                        score.actualizarLista();
                        score.setVisible(true);

                    }

                }
                break;

            case Juego.ESTADO_MEDIO:
                //Salir
                if (e.getX() >= 302 && e.getX() <= 480
                        && e.getY() >= 401 && e.getY() <= 580) {
                    accionesParaSalir();
                    cambiarMuertos();
                }

                //Continuar
                if (e.getX() >= 601 && e.getX() <= 781
                        && e.getY() >= 400 && e.getY() <= 579) {

                    ((EstadoJuego) Juego.getEstadoJuego()).getEntidades().clear();
                    ((EstadoJuego) Juego.getEstadoJuego()).getNave().setX((Launcher.getTamanio().width - 300) / 2);
                    ((EstadoJuego) Juego.getEstadoJuego()).getNave().setY(Launcher.getTamanio().height - 220);
                    ((EstadoJuego) Juego.getEstadoJuego()).getNave().setVida(100);
                    ((EstadoJuego) Juego.getEstadoJuego()).getNave().getBalas().clear();
                    Juego.setEstadoActual(Juego.getEstadoJuego(), Juego.ESTADO_JUEGO);
                    cambiarMuertos();

                    if (((EstadoJuego) Juego.getEstadoJuego()).getMundo() == EstadoJuego.MUNDO_JEFE) {
                        ((EstadoJuego) Juego.getEstadoJuego()).getEntidades().add(new Enemigo(0, -400));

                    }

                }

                break;

            case Juego.ESTADO_JUEGO:
                //Btn Pausa
                if (e.getX() >= 1121 && e.getX() <= 1164
                        && e.getY() >= 0 && e.getY() <= 44) {

                    Juego.setEstadoActual(Juego.getEstadoPausa(), Juego.ESTADO_PAUSA);

                }
                break;

            case Juego.ESTADO_PAUSA:

                //Salir
                if (e.getX() >= 101 && e.getX() <= 281
                        && e.getY() >= 331 && e.getY() <= 511) {
                    accionesParaSalir();
                    cambiarMuertos();
                }

                //Continuar
                if (e.getX() >= 882 && e.getX() <= 1061
                        && e.getY() >= 331 && e.getY() <= 511) {

                    Juego.setEstadoActual(Juego.getEstadoJuego(), Juego.ESTADO_JUEGO);
                }

                break;

            case Juego.ESTADO_CREDITOS:
                if (e.getX() >= 1 && e.getX() <= 98
                        && e.getY() >= 545 && e.getY() <= 651) {

                    Juego.setEstadoActual(Juego.getEstadoPrincipal(), Juego.ESTADO_PRINCIPAL);
                }

                break;

            case Juego.ESTADO_FINAL:
                //Continuar
                if (e.getX() >= 981 && e.getX() <= 1160
                        && e.getY() >= 4 && e.getY() <= 179) {

                    cambiarMuertos();
                    Juego.setEstadoActual(Juego.getEstadoPrincipal(), Juego.ESTADO_PRINCIPAL);

                }
                break;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void cambiarMuertos() {

        spaceWar.getJugadores().get(spaceWar.getJugadores().size() - 1)
                .setNumeroDeMuertos(spaceWar.getJugadores().
                        get(spaceWar.getJugadores().size() - 1).
                        getNumeroDeMuertos() + EstadoJuego.getEnemigosMuertos());
        EstadoJuego.setEnemigosMuertos(0);

    }

    public void accionesParaSalir() {

        EstadoJuego.setMundo(1);
        ((EstadoJuego) Juego.getEstadoJuego()).getEntidades().clear();
        ((EstadoJuego) Juego.getEstadoJuego()).getNave().setX((Launcher.getTamanio().width - 300) / 2);
        ((EstadoJuego) Juego.getEstadoJuego()).getNave().setY(Launcher.getTamanio().height - 220);
        ((EstadoJuego) Juego.getEstadoJuego()).getNave().setVida(100);

        Juego.setEstadoActual(Juego.getEstadoPrincipal(), Juego.ESTADO_PRINCIPAL);

    }

}
