package AdivinaAdivinador.View;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    public MenuView() {
        setTitle("Adivina Adivinador - Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(50, 50, 80)); // Azul oscuro
        
        JLabel titulo = new JLabel("ADIVINA ADIVINADOR", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        
        JButton btnHvM = new JButton("Humano vs Máquina");
        btnHvM.setFont(new Font("Arial", Font.BOLD, 18));
        btnHvM.setBackground(new Color(70, 130, 180));
        btnHvM.setForeground(Color.WHITE);
        btnHvM.setFocusPainted(false);
        btnHvM.addActionListener(e -> {
            String[] opciones = {"Segura", "Arriesgada", "Loca"};
            int seleccion = JOptionPane.showOptionDialog(this, "Elige la personalidad de la máquina:", "Personalidad IA",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            
            if (seleccion == -1) return;
            
            AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad personalidad2;
            if (seleccion == 0) {
                personalidad2 = AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad.SEGURA;
            } else if (seleccion == 1) {
                personalidad2 = AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad.ARRIESGADA;
            } else {
                personalidad2 = AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad.LOCA;
            }

            AdivinaAdivinador.Sistema sistema = new AdivinaAdivinador.Sistema();
            AdivinaAdivinador.Personajes.ComparadorDePersonajes compPersonajes = new AdivinaAdivinador.Personajes.ComparadorDePersonajes();
            AdivinaAdivinador.Algoritmos.AlgoritmoMergeSort merge = new AdivinaAdivinador.Algoritmos.AlgoritmoMergeSort();
            AdivinaAdivinador.Personajes.CreadorDePersonaje creadorPers = new AdivinaAdivinador.Personajes.CreadorDePersonaje(
                new AdivinaAdivinador.Personajes.ProveedorDeNombres(AdivinaAdivinador.Personajes.ListaDeNombres.nombresMasculinos(), AdivinaAdivinador.Personajes.ListaDeNombres.nombresFemeninos()), 
                new AdivinaAdivinador.Personajes.ProveedorDeCaracteristicas()
            );
            
            AdivinaAdivinador.Personajes.CreadorDeListaDePersonajes creadorListaP = new AdivinaAdivinador.Personajes.CreadorDeListaDePersonajes(creadorPers, compPersonajes, merge);
            AdivinaAdivinador.Preguntas.CreadorDeListaDePreguntas creadorListaPreg = new AdivinaAdivinador.Preguntas.CreadorDeListaDePreguntas();
            AdivinaAdivinador.Personajes.SelectorDePersonajeSecreto selector = new AdivinaAdivinador.Personajes.SelectorDePersonajeSecreto();
            AdivinaAdivinador.Preguntas.ComparadorDePreguntas compPreg = new AdivinaAdivinador.Preguntas.ComparadorDePreguntas();
            
            AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego creadorJuego = new AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego(sistema, creadorListaP, creadorListaPreg, selector, compPreg);
            
            AdivinaAdivinador.FlujoDeJuego.Juego nuevoJuego = creadorJuego.crearJuego(AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Modo.HUMANO_VS_MAQUINA, null, personalidad2);
            
            GameView gvMaquina = new GameView(nuevoJuego, null, nuevoJuego.getJugador2().getPersonajes(), "Tablero de la Máquina", false);
            gvMaquina.setLocation(50, 50);

            GameView gvHumano = new GameView(nuevoJuego, nuevoJuego.getJugador1().getPersonajeSecreto(), nuevoJuego.getJugador1().getPersonajes(), "Tablero del Jugador Humano", true);
            gvHumano.setLocation(600, 50);
            
            gvHumano.setVistaOponente(gvMaquina);
            
            dispose();
        });
        
        JButton btnMvM = new JButton("Máquina vs Máquina");
        btnMvM.setFont(new Font("Arial", Font.BOLD, 18));
        btnMvM.setBackground(new Color(220, 20, 60));
        btnMvM.setForeground(Color.WHITE);
        btnMvM.setFocusPainted(false);
        btnMvM.addActionListener(e -> {
            String[] opciones = {"Segura", "Arriesgada", "Loca"};
            int selec1 = JOptionPane.showOptionDialog(this, "Elige la personalidad de la MÁQUINA 1:", "IA 1",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (selec1 == -1) return;
            
            int selec2 = JOptionPane.showOptionDialog(this, "Elige la personalidad de la MÁQUINA 2:", "IA 2",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (selec2 == -1) return;

            AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad p1 = selec1 == 0 ? AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad.SEGURA : (selec1 == 1 ? AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad.ARRIESGADA : AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad.LOCA);
            AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad p2 = selec2 == 0 ? AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad.SEGURA : (selec2 == 1 ? AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad.ARRIESGADA : AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Personalidad.LOCA);

            AdivinaAdivinador.Sistema sistema = new AdivinaAdivinador.Sistema();
            AdivinaAdivinador.Personajes.ComparadorDePersonajes compPersonajes = new AdivinaAdivinador.Personajes.ComparadorDePersonajes();
            AdivinaAdivinador.Algoritmos.AlgoritmoMergeSort merge = new AdivinaAdivinador.Algoritmos.AlgoritmoMergeSort();
            AdivinaAdivinador.Personajes.CreadorDePersonaje creadorPers = new AdivinaAdivinador.Personajes.CreadorDePersonaje(
                new AdivinaAdivinador.Personajes.ProveedorDeNombres(AdivinaAdivinador.Personajes.ListaDeNombres.nombresMasculinos(), AdivinaAdivinador.Personajes.ListaDeNombres.nombresFemeninos()), 
                new AdivinaAdivinador.Personajes.ProveedorDeCaracteristicas()
            );
            
            AdivinaAdivinador.Personajes.CreadorDeListaDePersonajes creadorListaP = new AdivinaAdivinador.Personajes.CreadorDeListaDePersonajes(creadorPers, compPersonajes, merge);
            AdivinaAdivinador.Preguntas.CreadorDeListaDePreguntas creadorListaPreg = new AdivinaAdivinador.Preguntas.CreadorDeListaDePreguntas();
            AdivinaAdivinador.Personajes.SelectorDePersonajeSecreto selector = new AdivinaAdivinador.Personajes.SelectorDePersonajeSecreto();
            AdivinaAdivinador.Preguntas.ComparadorDePreguntas compPreg = new AdivinaAdivinador.Preguntas.ComparadorDePreguntas();
            
            AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego creadorJuego = new AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego(sistema, creadorListaP, creadorListaPreg, selector, compPreg);
            
            AdivinaAdivinador.FlujoDeJuego.Juego nuevoJuego = creadorJuego.crearJuego(AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Modo.MAQUINA_VS_MAQUINA, p1, p2);
            
            GameView gv1 = new GameView(nuevoJuego, nuevoJuego.getJugador1().getPersonajeSecreto(), nuevoJuego.getJugador1().getPersonajes(), "Máquina 1 (" + p1 + ")", false);
            gv1.setLocation(50, 50);

            GameView gv2 = new GameView(nuevoJuego, nuevoJuego.getJugador2().getPersonajeSecreto(), nuevoJuego.getJugador2().getPersonajes(), "Máquina 2 (" + p2 + ")", false);
            gv2.setLocation(600, 50);
            
            gv1.setVistaOponente(gv2);
            gv2.setVistaOponente(gv1);
            
            dispose();
            
            final boolean[] turnoJ1 = {true};
            javax.swing.Timer timer = new javax.swing.Timer(2500, evt -> {
                String mensaje;
                if (turnoJ1[0]) {
                    mensaje = nuevoJuego.turnoCualquierMaquina(nuevoJuego.getJugador1(), nuevoJuego.getJugador2());
                } else {
                    mensaje = nuevoJuego.turnoCualquierMaquina(nuevoJuego.getJugador2(), nuevoJuego.getJugador1());
                }
                
                gv1.actualizarTableroInterno(nuevoJuego.getJugador1().getPersonajesDescartados());
                gv2.actualizarTableroInterno(nuevoJuego.getJugador2().getPersonajesDescartados());
                
                turnoJ1[0] = !turnoJ1[0];
                
                if (mensaje.startsWith("MAQUINA_GANA:")) {
                    ((javax.swing.Timer)evt.getSource()).stop();
                    String ganador = mensaje.split(":")[1];
                    String adivino = mensaje.split(":")[2];
                    JOptionPane.showMessageDialog(null, "¡" + ganador + " GANA! Adivinó al personaje: " + adivino);
                } else {
                    System.out.println(mensaje);
                }
            });
            timer.start();
        });
        
        panel.add(titulo);
        panel.add(btnHvM);
        panel.add(btnMvM);
        
        setContentPane(panel);
        setVisible(true);
    }
}
