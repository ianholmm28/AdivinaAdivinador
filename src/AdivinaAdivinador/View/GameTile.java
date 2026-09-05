package AdivinaAdivinador.View;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Personajes.ResolverCapasPersonaje;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.Timer;

public class GameTile extends JButton implements ActionListener {
    private final ImagenPersonaje imagenAdelante;
    
    private boolean isEliminated = false;
    private final Personaje personaje;

    public GameTile(Personaje personaje, ResolverCapasPersonaje resolver) {
        this.personaje = personaje;
        imagenAdelante = new ImagenPersonaje(personaje, resolver);
        
        setFocusable(false);
        setPreferredSize(new Dimension(GameView.TILE_WIDTH, GameView.TILE_HEIGHT));
        setBorderPainted(true);
        setContentAreaFilled(false);
        setMargin(new Insets(0,0,0,0));
        
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isEliminated = !isEliminated;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        int w = getWidth();
        int h = getHeight() - 20; // Dejar 20px abajo para el nombre
        
        // Dibujar un fondo blanco o con color para la carta
        g2.setColor(new java.awt.Color(240, 248, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(java.awt.Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        
        // Dibujar el personaje siempre
        List<BufferedImage> capas = imagenAdelante.getCapas();
        for (BufferedImage capa : capas) {
            g2.drawImage(capa, 0, 0, w, h, null);
        }
        
        // Si está eliminado, dibujar oscurecido y la X
        if (isEliminated) {
            // Fondo oscuro semitransparente sobre la imagen (no sobre el nombre)
            g2.setColor(new java.awt.Color(0, 0, 0, 150));
            g2.fillRoundRect(0, 0, w, h, 15, 15);
            
            // Dibujar la X roja
            g2.setColor(new java.awt.Color(220, 20, 60)); // Crimson
            g2.setStroke(new java.awt.BasicStroke(6, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
            
            int offset = 20;
            g2.drawLine(offset, offset, w - offset, h - offset);
            g2.drawLine(w - offset, offset, offset, h - offset);
        }
        
        // Dibujar el nombre del personaje abajo
        g2.setColor(new java.awt.Color(50, 50, 50));
        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        String nombre = personaje.getNombre();
        java.awt.FontMetrics fm = g2.getFontMetrics();
        int textX = (getWidth() - fm.stringWidth(nombre)) / 2;
        int textY = getHeight() - 5;
        g2.drawString(nombre, textX, textY);
        
        g2.dispose();
    }
}
