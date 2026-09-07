package AdivinaAdivinador.View;

import AdivinaAdivinador.Utils.GestorLogs;
import AdivinaAdivinador.Utils.Logger;
import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    public MenuView() {
        setTitle("Adivina Adivinador - Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(new Color(35, 40, 50)); // Gris oscuro moderno
        
        JLabel titulo = new JLabel("ADIVINA ADIVINADOR", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(240, 240, 240));
        
        JButton btnHvM = new JButton("Humano vs Máquina");
        btnHvM.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnHvM.setBackground(new Color(41, 128, 185)); // Azul flat
        btnHvM.setForeground(Color.WHITE);
        btnHvM.setFocusPainted(false);
        btnHvM.setBorderPainted(false);
        btnHvM.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            
            Logger logger = new GestorLogs("partida.log");
            AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego creadorJuego = new AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego(sistema, creadorListaP, creadorListaPreg, selector, compPreg, logger);
            
            AdivinaAdivinador.FlujoDeJuego.Juego nuevoJuego = creadorJuego.crearJuego(AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Modo.HUMANO_VS_MAQUINA, null, personalidad2);
            
            JFrame gameFrame = new JFrame("Adivina Adivinador - Humano vs Máquina");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setLayout(new BorderLayout());
            
            ChatPanel chatPanel = new ChatPanel(true);
            chatPanel.setPreferredSize(new Dimension(800, 180));
            
            GameView gvMaquina = new GameView(nuevoJuego, null, nuevoJuego.getJugador2().getPersonajes(), "Tablero de la Máquina", false, null);
            GameView gvHumano = new GameView(nuevoJuego, nuevoJuego.getJugador1().getPersonajeSecreto(), nuevoJuego.getJugador1().getPersonajes(), "Tu Tablero", true, chatPanel);
            
            gvHumano.setVistaOponente(gvMaquina);
            
            JPanel boardsPanel = new JPanel(new GridLayout(1, 2));
            boardsPanel.add(gvMaquina); // Máquina a la izquierda
            boardsPanel.add(gvHumano);  // Jugador a la derecha
            
            gameFrame.add(boardsPanel, BorderLayout.CENTER);
            
            JPanel chatContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10)); // FlowLayout para no estirar ancho
            chatContainer.setBackground(new Color(41, 128, 185)); // Mismo azul que los tableros
            chatPanel.setPreferredSize(new Dimension(600, 180)); // Ancho más pequeño
            chatContainer.add(chatPanel);
            gameFrame.add(chatContainer, BorderLayout.SOUTH);
            
            gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
            
            dispose();
        });
        
        JButton btnMvM = new JButton("Máquina vs Máquina");
        btnMvM.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnMvM.setBackground(new Color(192, 57, 43)); // Rojo flat
        btnMvM.setForeground(Color.WHITE);
        btnMvM.setFocusPainted(false);
        btnMvM.setBorderPainted(false);
        btnMvM.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMvM.addActionListener(e -> {
            String[] opciones = {"Segura", "Arriesgada", "Loca"};
            int selec1 = JOptionPane.showOptionDialog(this, "Elige la personalidad de la MÁQUINA 1:", "IA 1",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (selec1 == -1) return;
            
            int selec2 = JOptionPane.showOptionDialog(this, "Elige la personalidad de la MÁQUINA 2:", "IA 2",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (selec2 == -1) return;

            int verChat = JOptionPane.showConfirmDialog(this, "¿Deseas ver el chat de las máquinas?", "Ver Chat", JOptionPane.YES_NO_OPTION);
            boolean showChat = (verChat == JOptionPane.YES_OPTION);

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
            
            Logger logger = new GestorLogs("partida.log");
            AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego creadorJuego = new AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego(sistema, creadorListaP, creadorListaPreg, selector, compPreg, logger);
            
            AdivinaAdivinador.FlujoDeJuego.Juego nuevoJuego = creadorJuego.crearJuego(AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego.Modo.MAQUINA_VS_MAQUINA, p1, p2);
            
            JFrame gameFrame = new JFrame("Adivina Adivinador - Máquina vs Máquina");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setLayout(new BorderLayout());
            
            ChatPanel chatPanel = null;
            if (showChat) {
                chatPanel = new ChatPanel(false);
                chatPanel.setPreferredSize(new Dimension(600, 180));
                
                JPanel chatContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10)); // FlowLayout para no estirar ancho
                chatContainer.setBackground(new Color(41, 128, 185)); // Mismo azul que los tableros
                chatContainer.add(chatPanel);
                gameFrame.add(chatContainer, BorderLayout.SOUTH);
            }
            
            GameView gv1 = new GameView(nuevoJuego, nuevoJuego.getJugador1().getPersonajeSecreto(), nuevoJuego.getJugador1().getPersonajes(), "Máquina 1 (" + p1 + ")", false, null);
            GameView gv2 = new GameView(nuevoJuego, nuevoJuego.getJugador2().getPersonajeSecreto(), nuevoJuego.getJugador2().getPersonajes(), "Máquina 2 (" + p2 + ")", false, null);
            
            gv1.setVistaOponente(gv2);
            gv2.setVistaOponente(gv1);
            
            JPanel boardsPanel = new JPanel(new GridLayout(1, 2));
            boardsPanel.add(gv1);
            boardsPanel.add(gv2);
            gameFrame.add(boardsPanel, BorderLayout.CENTER);
            
            gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
            
            final ChatPanel finalChatPanel = chatPanel;
            
            dispose();
            
            final boolean[] turnoJ1 = {true};
            javax.swing.Timer timer = new javax.swing.Timer(2500, evt -> {
                String[] mensaje;
                boolean isJ1 = turnoJ1[0];
                if (isJ1) {
                    mensaje = nuevoJuego.turnoCualquierMaquina(nuevoJuego.getJugador1(), nuevoJuego.getJugador2());
                } else {
                    mensaje = nuevoJuego.turnoCualquierMaquina(nuevoJuego.getJugador2(), nuevoJuego.getJugador1());
                }
                
                gv1.actualizarTableroInterno(nuevoJuego.getJugador1().getPersonajesDescartados());
                gv2.actualizarTableroInterno(nuevoJuego.getJugador2().getPersonajesDescartados());
                
                turnoJ1[0] = !turnoJ1[0];
                
                if (mensaje[0].startsWith("MAQUINA_GANA:")) {
                    ((javax.swing.Timer)evt.getSource()).stop();
                    String ganador = mensaje[0].split(":")[1];
                    String adivino = mensaje[0].split(":")[2];
                    if (finalChatPanel != null) {
                        finalChatPanel.addMessage("Sistema", "¡" + ganador + " GANA! Adivinó al personaje: " + adivino, true);
                    }
                    JOptionPane.showMessageDialog(null, "¡" + ganador + " GANA! Adivinó al personaje: " + adivino);
                } else {
                    if (finalChatPanel != null) {
                        finalChatPanel.addMessage(isJ1 ? "Máquina 1" : "Máquina 2", mensaje[0], isJ1);
                        if (mensaje.length > 1) {
                            javax.swing.Timer delay = new javax.swing.Timer(1000, e2 -> {
                                finalChatPanel.addMessage(isJ1 ? "Máquina 2" : "Máquina 1", mensaje[1], !isJ1);
                            });
                            delay.setRepeats(false);
                            delay.start();
                        }
                    } else {
                        System.out.println(mensaje[0]);
                        if (mensaje.length > 1) System.out.println(mensaje[1]);
                    }
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
