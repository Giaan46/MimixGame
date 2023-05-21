package Game;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class Juego extends JPanel {
    // sonido del juego
    URL direccionSonidoSalto,direccionSonidoChoque;
    AudioClip sonidoChoque,sonidoSalto;

    //objetos de las clases auto, obstaculo y fondo
    Mimi mimi = new Mimi(this);
    Miau miau= new Miau(this);
    Fondo fondo = new Fondo(this);

    // variables para el juego
   public static boolean juegoFinalizado = false;
    public static boolean pierdeVida = false;
    public static int vidas = 3;
    public static int puntos = 0 ;
    public static int nivel = 1;

    public Juego(){
        direccionSonidoChoque = getClass().getResource("/multimedia/choque.wav");
        sonidoChoque = Applet.newAudioClip(direccionSonidoChoque);

        direccionSonidoSalto = getClass().getResource("/multimedia/salto.wav");
        sonidoSalto = Applet.newAudioClip(direccionSonidoSalto);

        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                // el salto se activa cuando se preciona la tecla espacio
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    sonidoSalto.play();
                    mimi.KeyPressed(e);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
    }
    public void mover(){
        miau.mover();
        mimi.mover();
        fondo.mover();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D)g;
        dibujar(g2);
        dibujarPuntaje(g2);
    }
    public void dibujar(Graphics2D g){
        fondo.paint(g);
        mimi.paint(g);
        miau.paint(g);
        mover();
    }
    public void dibujarPuntaje(Graphics2D g){
        Graphics2D g1 =g, g2 = g;
        Font score = new Font("Arial",Font.BOLD,30);
        g.setFont(score);
        g.setColor(Color.blue);
        g1.drawString("Puntaje: " + puntos,1100,30);
        g1.drawString("Vidas: " + vidas,20,30);
        g1.drawString("Nivel: "+nivel,570,30);
        if(juegoFinalizado){
            g2.setColor(Color.red);
            g2.drawString("Perdiste !!",((float)getBounds().getCenterX()/2)+ 170,70);
        }
    }
    public void finJuego(){
        juegoFinalizado = true;
        sonidoChoque.play();
    }
    public void pierdeVidas(){
        sonidoChoque.play();
        pierdeVida = true;
    }

}
