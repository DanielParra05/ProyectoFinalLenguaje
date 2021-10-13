package entidades;

import estado.EstadoJuego;
import java.awt.Graphics;
import graficos.Assets;
import miproyecto.Launcher;

public class Enemigo extends Entidades {

    private int x, y, z;
    private int vida;

    public Enemigo(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        int z = 0;
        vida = 100;

    }

    @Override
    public void dibujar(Graphics g) {
        // TODO Auto-generated method stub
        dibujarEnemigoYBalas(g);

    }

    @Override
    public void actualizar() {

        switch (EstadoJuego.mundo) {
            case EstadoJuego.MUNDO_1:

                movimientoEnemigos1();

                break;

            case EstadoJuego.MUNDO_2:
                movimientoEnemigos2();

                break;
            case EstadoJuego.MUNDO_3:
                movimientoEnemigos3();

                break;

            case EstadoJuego.MUNDO_4:

                movimientoEnemigos4();
                break;

            case EstadoJuego.MUNDO_5:

                movimientoEnemigos5();

                break;

            case EstadoJuego.MUNDO_JEFE:
                movimientoJefe();
                break;

        }

    }

    public void movimientoEnemigos1() {

        if (z == 0) {

            if (y < 60) {
                x += 2;
                y += 1;
            } else {
                z = 1;
            }
        }
        if (z == 1) {
            if (y > Launcher.getTamanio().height - 800) {
                x += 2;
                y -= 1;
            } else {
                z = 0;
            }

        }

    }

    public void movimientoEnemigos2() {
        if (z == 0) {

            y += 4;

            if (y == 0) {
                z = 1;
            }

        }

        if (z == 1) {
            x += 4;
            y += 1;
            if (y > 170) {
                z = 2;
            }

        }

        if (z == 2) {

            x -= 4;

            if (x < 0) {

                z = 3;
            }

        }

        if (z == 3) {
            x += 4;
            y -= 1;
            if (y < 0) {
                z = 1;
            }

        }

    }

    public void movimientoEnemigos3() {
        if (z == 0) {

            if (x < Launcher.getTamanio().width - 320) {
                x += 3;
            } else {
                z = 1;
            }

            if (y < Launcher.getTamanio().height - 700) {
                y += 1;
            }

        }

        if (z == 1) {
            if (x > 0) {
                x -= 3;
            } else {
                z = 2;
            }
        }

        if (z == 2) {
            if (x < Launcher.getTamanio().width - 320) {
                x += 3;
            } else {
                z = 3;
            }

            if (y > -200) {
                y -= 1;
            }

        }

        if (z == 3) {

            x -= 3;

        }

    }

    public void movimientoEnemigos4() {

        if (z == 0) {

            if (x >= 0) {

                x -= 2;
                y += 3;

            } else {

                z = 1;

            }

        }
        if (z == 1) {

            if (y < Launcher.getTamanio().height - 300) {

                y += 3;
                x += 2;
            } else {
                z = 2;
            }

        }

        if (z == 2) {

            if (y > -5) {
                y -= 3;
                x += 2;
            } else {

                z = 3;

            }

        }

        if (z == 3) {

            if (y < Launcher.getTamanio().height - 300) {

                y += 12;
            } else {
                z = 2;
            }

        }

    }

    public void movimientoEnemigos5() {

        if (z == 0) {

            if (x > 0) {
                x -= 5;
                y -= 3;

            } else {

                z = 1;

            }

        }

        if (z == 1) {

            if (y < Launcher.getTamanio().height - 150) {
                y += 15;
            } else {

                z = 2;

            }

        }

        if (z == 2) {

            if (x < Launcher.getTamanio().width - 380) {

                y -= 3;
                x += 5;

            } else {
                z = 3;
            }

        }

        if (z == 3) {
            y += 15;
        }
    }

    public void movimientoJefe() {
        if (z == 0) {
            if (!(x == 410 && y == 92)) {
                x += 5;
                y += 6;

            } else {
                z = 1;
            }

        }

        if (z == 1) {
            if (!(x == -145 && y == 758)) {

                x -= 5;
                y += 6;

            } else {

                z = 2;
                x = Launcher.getTamanio().width - 301;
                System.out.println(Launcher.getTamanio().width - 299);
                y = -350;
            }
        }

        if (z == 2) {
            if (!(x == 590 && y == 220)) {

                x -= 5;
                y += 6;

            } else {

                z = 3;
            }

        }

        if (z == 3) {
            if (!(x == 1045 && y == 766)) {

                x += 5;
                y += 6;
            } else {
                x = -220;
                y = 0;
                z = 4;
            }

        }

        if (z == 4) {

            if (!(x > Launcher.getTamanio().width - 100)) {

                x += 10;
            } else {

                z = 5;

            }
        }

        if (z == 5) {

            if (!(x < -400)) {
                x -= 10;

            } else {
                x = 0;
                y = -400;
                z = 0;

            }

        }

    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

   
    public int getVida() {
        return vida;
    }

   
    public void setVida(int vida) {
        this.vida = vida;
    }

    public void dibujarEnemigoYBalas(Graphics g) {

        g.drawImage(Assets.enemigo1, x, y, null);
    }

}
