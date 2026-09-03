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
        public final static int TILE_HEIGHT = 125;
        public final static int WINDOW_WIDTH = TILE_WIDTH * 6 + 50;
        public final static int WINDOW_HEIGHT = TILE_HEIGHT * 4 + 150;

        private class GameTile extends JButton {
            public GameTile(Personaje personaje, ResolverCapasPersonaje resolver) {
                setLayout(new BorderLayout());
                ImagenPersonaje img = new ImagenPersonaje(personaje, resolver);
                add(img, BorderLayout.CENTER);
                setFocusable(false);
                setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));
                setBorderPainted(true);
                setContentAreaFilled(false);
                setMargin(new Insets(0,0,0,0));
            }
        }

        private class GameQuestions extends JButton {
            public GameQuestions(String texto) {
                super(texto);
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
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(true);
            setVisible(true);
            setLocationRelativeTo(null);
        }



        private void setUp() {

            JPanel mainPanel = new JPanel(new BorderLayout());

            JPanel topPanel = new JPanel(new FlowLayout());
            labelTurno = new JLabel("TU TURNO");
            topPanel.add(labelTurno);

            ResolverCapasPersonaje resolver = new ResolverCapasPersonaje();
            
            panelPersonaje = new ImagenPersonaje(personaje, resolver);
            topPanel.add(panelPersonaje);

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            tiles = new GameTile[4][6];
            int index = 0;
            
            for (int i = 0; i < 4; i++) {
                JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
                rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, TILE_HEIGHT));
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
                if (i < 3) {
                    centerPanel.add(Box.createVerticalStrut(3));
                }
            }
            centerPanel.add(Box.createVerticalGlue());

            JPanel footPanel = new JPanel(new FlowLayout());
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
