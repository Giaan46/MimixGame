package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Miau {
    // objeto de la clase juego
    Juego jueguito;
    // variables que delimitan el area del obstaculo
    Area cabeza,cuerpo,gato;
    //variables del tamanio del personaje
    int anchoObstaculo = 100;
    int altoObstaculo = 100;
    //coordenadas iniciales donde se ubica el objeto
    static int x_inicial = 1300;
    static int y_inicial = 270;
    //coordenadas para manipular el objeto
    static int x_auxiliar = -4;


    public Miau(Juego jueguito){
        this.jueguito = jueguito;


    }
    public void mover() {
        if (x_inicial <= -100) {
            Juego.puntos++;
            x_inicial = 1300;
            if (Juego.puntos == 3 | Juego.puntos == 6 | Juego.puntos == 9 | Juego.puntos == 12) {
                x_auxiliar += -2;
                Juego.nivel++;
            }

        } else {
            if (colision()) {
                if(Juego.vidas == 0){
                    jueguito.finJuego();
                }else{
                    jueguito.pierdeVidas();
                }

            } else {
                x_inicial += x_auxiliar;
            }
        }
    }
    public  void paint(Graphics2D g){

        ImageIcon auto = new ImageIcon(getClass().getResource("/multimedia/Miau.png"));
        g.drawImage(auto.getImage(), x_inicial, y_inicial, anchoObstaculo, altoObstaculo, null);
    }
    public Area getBounds(){
        Ellipse2D forma1 = new Ellipse2D.Double(x_inicial,y_inicial,40,40);
        Rectangle forma2 = new Rectangle(x_inicial + 12 , y_inicial + 16,50,53);

        cabeza = new Area(forma1);
        cuerpo = new Area(forma2);

        gato = cabeza;
        gato.add(cabeza);
        gato.add(cuerpo);

        return gato;


    }
    public boolean colision(){
        Area areaA = new Area(jueguito.mimi.getBounds());
        areaA.intersect(getBounds());

        return !areaA.isEmpty();

    }


}
