package AdivinaAdivinador.View;

import java.awt.*;
import javax.swing.*;
import AdivinaAdivinador.Personajes.*;
import AdivinaAdivinador.Preguntas.Pregunta;
import AdivinaAdivinador.FlujoDeJuego.Juego;
import java.util.List;
import java.util.ArrayList;

public class GameView extends JPanel {
    public final static int TILE_WIDTH = 90;
    public final static int TILE_HEIGHT = 130;
    public final static int WINDOW_WIDTH = TILE_WIDTH * 6 + 150;
    public final static int WINDOW_HEIGHT = TILE_HEIGHT * 4 + 280;

    private final Juego juego;
    private GameTile tiles[][];
    private GameView vistaOponente;
    private boolean controlesHumanos;

    private ChatPanel chatPanel;

    public GameView(Juego juego, Personaje personajeSecretoPropio, List<Personaje> personajesTablero, String title, boolean controlesHumanos, ChatPanel chatPanel) {
        this.juego = juego;
        this.controlesHumanos = controlesHumanos;
        this.chatPanel = chatPanel;
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        setUp(personajeSecretoPropio, personajesTablero, title);
    }
    
    public void setVistaOponente(GameView vistaOponente) {
        this.vistaOponente = vistaOponente;
    }

    private void setUp(Personaje personajeSecretoPropio, List<Personaje> personajesTablero, String title) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(41, 128, 185)); // Azul flat moderno

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        ResolverCapasPersonaje resolver = new ResolverCapasPersonaje();
        
        JPanel panelSecreto = new JPanel();
        panelSecreto.setLayout(new BoxLayout(panelSecreto, BoxLayout.Y_AXIS));
        panelSecreto.setOpaque(false);
        
        JLabel labelTitle = new JLabel(title);
        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelTitle.setForeground(Color.YELLOW);
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSecreto.add(labelTitle);
        panelSecreto.add(Box.createVerticalStrut(5));
        
        JLabel labelTurno = new JLabel("PERSONAJE SECRETO:");
        labelTurno.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelTurno.setForeground(Color.WHITE);
        labelTurno.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSecreto.add(labelTurno);
        panelSecreto.add(Box.createVerticalStrut(10));
        
        if (personajeSecretoPropio != null) {
            GameTile cartaSecreta = new GameTile(personajeSecretoPropio, resolver);
            for (java.awt.event.ActionListener al : cartaSecreta.getActionListeners()) {
                cartaSecreta.removeActionListener(al);
            }
            cartaSecreta.setAlignmentX(Component.CENTER_ALIGNMENT);
            cartaSecreta.setMaximumSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));
            panelSecreto.add(cartaSecreta);
        } else {
            JLabel misterio = new JLabel("?", SwingConstants.CENTER);
            misterio.setFont(new Font("Arial", Font.BOLD, 60));
            misterio.setForeground(Color.WHITE);
            misterio.setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));
            misterio.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelSecreto.add(misterio);
        }
        
        topPanel.add(panelSecreto, BorderLayout.EAST);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        tiles = new GameTile[4][6];
        int index = 0;
        
        for (int i = 0; i < 4; i++) {
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            rowPanel.setOpaque(false);
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, TILE_HEIGHT + 10));
            for (int j = 0; j < 6; j++) {
                if (i == 3 && j == 5) continue;
                
                // Asegurar no salir de los límites por si acaso
                Personaje p = (index < personajesTablero.size()) ? personajesTablero.get(index) : personajesTablero.get(0);
                GameTile tile = new GameTile(p, resolver);
                tiles[i][j] = tile;
                
                if (controlesHumanos) {
                    tile.addActionListener(e -> {
                        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres adivinar a " + p.getNombre() + "?", "Adivinar", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            boolean gano = juego.humanoAdivinaPersonaje(p);
                            if (gano) {
                                JOptionPane.showMessageDialog(this, "¡GANASTE! El personaje era " + p.getNombre());
                                System.exit(0);
                            } else {
                                chatPanel.addMessage("Sistema", "¡Fallaste! Ese no es el personaje.", false);
                                tile.setEliminated(true);
                                ejecutarTurnoMaquina();
                            }
                        }
                    });
                }

                rowPanel.add(tile);
                index++;
            }
            centerPanel.add(rowPanel);
        }
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Espacio entre personajes y chat

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        if (controlesHumanos && chatPanel != null) {
            chatPanel.setPreguntas(juego.getJugador1().getPreguntasDisponibles());
            chatPanel.setOnPreguntaEnviada(e -> {
                Pregunta pregunta = (Pregunta) e.getSource();
                chatPanel.addMessage("Tú", pregunta.getTexto(), true);
                chatPanel.setInputEnabled(false);
                
                Timer t1 = new Timer(1000, evt1 -> {
                    boolean respuesta = juego.humanoHacePregunta(pregunta);
                    chatPanel.addMessage("Máquina", respuesta ? "SÍ" : "NO", false);
                    actualizarTableros();
                    
                    Timer t2 = new Timer(1500, evt2 -> {
                        ejecutarTurnoMaquina();
                    });
                    t2.setRepeats(false);
                    t2.start();
                });
                t1.setRepeats(false);
                t1.start();
            });
        }

        add(mainPanel, BorderLayout.CENTER);
    }

    public void actualizarTableroInterno(List<Personaje> descartados) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (tiles[i][j] != null) {
                    if (descartados.contains(tiles[i][j].getPersonaje())) {
                        tiles[i][j].setEliminated(true);
                    }
                }
            }
        }
    }

    private void actualizarTableros() {
        this.actualizarTableroInterno(juego.getJugador1().getPersonajesDescartados());
        if (vistaOponente != null) {
            vistaOponente.actualizarTableroInterno(juego.getJugador2().getPersonajesDescartados());
        }
    }

    private void ejecutarTurnoMaquina() {
        String[] mensajeMaquina = juego.turnoMaquina();
        actualizarTableros();

        if (mensajeMaquina[0].startsWith("MAQUINA_GANA:")) {
            String nombre = mensajeMaquina[0].split(":")[2];
            JOptionPane.showMessageDialog(this, "¡LA MÁQUINA GANA! Adivinó tu personaje: " + nombre);
            System.exit(0);
        } else {
            chatPanel.addMessage("Máquina", mensajeMaquina[0], false);
            if (mensajeMaquina.length > 1) {
                Timer t3 = new Timer(1500, evt3 -> {
                    chatPanel.addMessage("Tú", mensajeMaquina[1], true);
                    chatPanel.setInputEnabled(true);
                });
                t3.setRepeats(false);
                t3.start();
            } else {
                chatPanel.setInputEnabled(true);
            }
        }
    }
}
