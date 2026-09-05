package AdivinaAdivinador.View;

import java.awt.*;
import javax.swing.*;
import AdivinaAdivinador.Personajes.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author holmian
 */
public class GameView extends JFrame {
        public final static int TILE_WIDTH = 100;
        public final static int TILE_HEIGHT = 145; // Aumentado para el nombre
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

        private String title = "Jugador";
        private GameQuestions[] preguntas = new GameQuestions[5];
        String[] textos = {"pregunta 1","pregunta 2","pregunta 3","pregunta 4","pregunta 5"};
        private JLabel labelTurno;
        private ImagenPersonaje panelPersonaje;
        private final Personaje personaje;
        private final List<Personaje> personajesTablero;

        private GameTile tiles[][];

        // Constructor real: recibe el personaje principal y los personajes del tablero
        public GameView(Personaje personaje, List<Personaje> personajesTablero) {
            this.personaje = personaje;
            this.personajesTablero = personajesTablero;
            setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
            setTitle(title);
            setUp();
            // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Removido para poder tener 2 ventanas sin que una cierre la otra de golpe
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(true);
            setVisible(true);
            setLocationRelativeTo(null);
        }



        private void setUp() {

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(30, 144, 255)); // Azul brillante de fondo

            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setOpaque(false);
            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            

            ResolverCapasPersonaje resolver = new ResolverCapasPersonaje();
            
            // Contenedor para el 1 a la derecha
            JPanel panelSecreto = new JPanel();
            panelSecreto.setLayout(new BoxLayout(panelSecreto, BoxLayout.Y_AXIS));
            panelSecreto.setOpaque(false);
            
            JLabel labelTurno = new JLabel("PERSONAJE SECRETO:");
            labelTurno.setFont(new Font("Arial", Font.BOLD, 14));
            labelTurno.setForeground(Color.WHITE);
            labelTurno.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            // Usamos un GameTile para que se vea IDENTICO a las cartas del tablero
            GameTile cartaSecreta = new GameTile(personaje, resolver);
            // Quitamos su ActionListener para que no se pueda clickear ni eliminar
            for (java.awt.event.ActionListener al : cartaSecreta.getActionListeners()) {
                cartaSecreta.removeActionListener(al);
            }
            cartaSecreta.setAlignmentX(Component.CENTER_ALIGNMENT);
            // GameTile ya tiene tamaño fijo así que BoxLayout no lo deformará
            cartaSecreta.setMaximumSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));
            
            panelSecreto.add(labelTurno);
            panelSecreto.add(Box.createVerticalStrut(10));
            panelSecreto.add(cartaSecreta);
            
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
                    if (i == 3 && j == 5) {
                        continue;
                    }
                    Personaje p = (index < personajesTablero.size()) ? personajesTablero.get(index) : personaje;
                    tiles[i][j] = new GameTile(p, resolver);
                    rowPanel.add(tiles[i][j]);
                    index++;
                }
                centerPanel.add(rowPanel);
            }
            centerPanel.add(Box.createVerticalGlue());

            JPanel footPanel = new JPanel(new FlowLayout());
            footPanel.setOpaque(false);
            for (int i = 0; i < preguntas.length; i++) {
                preguntas[i] = new GameQuestions(textos[i]);
                footPanel.add(preguntas[i]);
            }

            mainPanel.add(topPanel, BorderLayout.NORTH);
            mainPanel.add(centerPanel, BorderLayout.CENTER);
            mainPanel.add(footPanel, BorderLayout.SOUTH);

            setContentPane(mainPanel);
        }
}
