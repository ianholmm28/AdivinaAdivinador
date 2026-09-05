package AdivinaAdivinador.View;

import java.awt.*;
import javax.swing.*;
import AdivinaAdivinador.Personajes.*;
import AdivinaAdivinador.Preguntas.Pregunta;
import AdivinaAdivinador.FlujoDeJuego.Juego;
import java.util.List;
import java.util.ArrayList;

public class GameView extends JFrame {
    public final static int TILE_WIDTH = 100;
    public final static int TILE_HEIGHT = 145;
    public final static int WINDOW_WIDTH = TILE_WIDTH * 6 + 150;
    public final static int WINDOW_HEIGHT = TILE_HEIGHT * 4 + 280;

    private class GameQuestions extends JButton {
        public GameQuestions(String texto) {
            super(texto);
            setBackground(new Color(70, 130, 180));
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 12));
            setFocusPainted(false);
        }
    }

    private final Juego juego;
    private GameTile tiles[][];
    private GameView vistaOponente;
    private boolean controlesHumanos;

    public GameView(Juego juego, Personaje personajeSecretoPropio, List<Personaje> personajesTablero, String title, boolean controlesHumanos) {
        this.juego = juego;
        this.controlesHumanos = controlesHumanos;
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setTitle(title);
        setUp(personajeSecretoPropio, personajesTablero);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }
    
    public void setVistaOponente(GameView vistaOponente) {
        this.vistaOponente = vistaOponente;
    }

    private void setUp(Personaje personajeSecretoPropio, List<Personaje> personajesTablero) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 144, 255));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        ResolverCapasPersonaje resolver = new ResolverCapasPersonaje();
        
        JPanel panelSecreto = new JPanel();
        panelSecreto.setLayout(new BoxLayout(panelSecreto, BoxLayout.Y_AXIS));
        panelSecreto.setOpaque(false);
        
        JLabel labelTurno = new JLabel("PERSONAJE SECRETO:");
        labelTurno.setFont(new Font("Arial", Font.BOLD, 14));
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
                                JOptionPane.showMessageDialog(this, "¡Fallaste! Ese no es el personaje.");
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

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        if (controlesHumanos) {
            JPanel footPanel = new JPanel(new GridLayout(3, 5, 5, 5));
            footPanel.setOpaque(false);
            footPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            ArrayList<Pregunta> preguntasDisponibles = juego.getJugador1().getPreguntasDisponibles();
            
            for (Pregunta pregunta : preguntasDisponibles) {
                GameQuestions btnPregunta = new GameQuestions(pregunta.getTexto());
                btnPregunta.addActionListener(e -> {
                    boolean respuesta = juego.humanoHacePregunta(pregunta);
                    JOptionPane.showMessageDialog(this, "La Máquina dice: " + (respuesta ? "SÍ" : "NO"));
                    btnPregunta.setEnabled(false);
                    
                    actualizarTableros();
                    ejecutarTurnoMaquina();
                });
                footPanel.add(btnPregunta);
            }
            mainPanel.add(footPanel, BorderLayout.SOUTH);
        }

        setContentPane(mainPanel);
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
        String mensajeMaquina = juego.turnoMaquina();
        actualizarTableros(); // Actualizar tableros después del turno de la máquina

        if (mensajeMaquina.startsWith("MAQUINA_GANA:")) {
            String nombre = mensajeMaquina.split(":")[1];
            JOptionPane.showMessageDialog(this, "¡LA MÁQUINA GANA! Adivinó tu personaje: " + nombre);
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, "TURNO DE LA MÁQUINA:\n" + mensajeMaquina);
        }
    }
}
