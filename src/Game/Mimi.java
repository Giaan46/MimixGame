package Game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Mimi {
    Juego jueguito;
    // variables que nos ayudan a saber si el auto salta
    static boolean saltando = false;
    boolean sube = false;
    boolean baja = false;
    // variables que delimitan el area del objeto
    Area llantaDelantera, llantaTrasera, carroceria, tractor;
    int anchopersonaje = 212;
    int altoPersonaje = 178;
    //coordenadas iniciales de personaje
    static int x_inicial = 50;
    static int y_inicial = 170;
    // coordenadas para manipular el personaje
    int x_auxiliar = 0;
    int y_auxiliar = 0;


    public Mimi(Juego jueguito) {
        this.jueguito = jueguito;


    }

    public void mover() {
        if (x_inicial + x_auxiliar > 0 && x_inicial + x_auxiliar < jueguito.getWidth() - anchopersonaje) {
            x_inicial += x_auxiliar;
        }
        if (saltando) {

            if (y_inicial == 170) {// si el auto esta en el suelo
                sube = true;
                y_auxiliar = -2;
                baja = false;
            }
            if (y_inicial == 100) {// si el auto llego al limite del salto
                baja = true;
                y_auxiliar = 2;
                sube = false;
            }
            if (sube) {
                y_inicial += y_auxiliar;
            }
            if (baja) {
                y_inicial += y_auxiliar;
                if (y_inicial == 170) {//si el auto llego al suelo
                    saltando = false;
                }
            }
        }}
    public void paint(Graphics2D g) {
        ImageIcon auto = new ImageIcon(getClass().getResource("/multimedia/Mimi.png"));
        g.drawImage(auto.getImage(), x_inicial, y_inicial, anchopersonaje, altoPersonaje, null);
    }

    public void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            saltando = true;
        }
    }
    public  Area getBounds() {

        Rectangle forma1 = new Rectangle(x_inicial, y_inicial, 95, 62);
        carroceria = new Area(forma1);

        Ellipse2D forma2 = new Ellipse2D.Double(x_inicial,y_inicial +88 , 100 , 48) ;
        llantaTrasera = new Area(forma2);

        Ellipse2D forma3 = new Ellipse2D.Double(x_inicial + 73,y_inicial + 39, 38,38);
        llantaDelantera = new Area(forma3);
        tractor = carroceria;
        tractor.add(carroceria);
        tractor.add(llantaTrasera);
        tractor.add(llantaDelantera);

        return tractor;
    }
}


