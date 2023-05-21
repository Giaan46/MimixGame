package Game;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    public static int reiniciaJuego = -1;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, " evita que la gata te muerda el tobillo ");

        JFrame ventana = new JFrame("Jueguito");
        Juego jueguito = new Juego();
        ventana.add(jueguito);
        ventana.setSize(1300, 400);
        ventana.setLocation(70, 200);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            if (jueguito.juegoFinalizado) {
                reiniciaJuego = JOptionPane.showConfirmDialog(null, " miau miau te mordio el tobillo, queres jugar denuevo ?", "Perdiste!", JOptionPane.YES_NO_OPTION);
                if (reiniciaJuego == 0) {
                    reiniciaValores();
                } else if (reiniciaJuego == 1) {
                    System.exit(0);
                }
            } else {
                jueguito.repaint();
                try {
                    Thread.sleep(10);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (Juego.pierdeVida == true) {
                    JOptionPane.showMessageDialog(null, "Cuidado!!");
                    Juego.pierdeVida = false;
                    Juego.vidas--;
                    Mimi.y_inicial = 170;
                    Mimi.saltando = false;
                    Miau.x_inicial = 1300;
                }
            }
        }
    }

    private static void reiniciaValores () {
                Juego.juegoFinalizado = false;
                Miau.x_auxiliar = -4;
                Juego.puntos = 0;
                Juego.nivel = 1;
                Juego.vidas = 3;
                reiniciaJuego = -1;
                Miau.x_inicial = 1300;

    }
}

