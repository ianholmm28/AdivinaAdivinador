package AdivinaAdivinador.View;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Personajes.ResolverCapasPersonaje;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel que dibuja un Personaje como una pila de capas de imagen
 * (base + pelo + barba + lentes, segun corresponda) superpuestas.
 */
public class ImagenPersonaje extends JPanel {

    private static final Dimension TAMANIO_POR_DEFECTO = new Dimension(100, 125);

    private final List<BufferedImage> capas = new ArrayList<>();

    public ImagenPersonaje(Personaje personaje, ResolverCapasPersonaje resolver) {
        this(personaje, resolver, TAMANIO_POR_DEFECTO);
    }

    public ImagenPersonaje(Personaje personaje, ResolverCapasPersonaje resolver, Dimension dim) {
        cargarCapas(resolver.obtenerRutas(personaje));
        setOpaque(false);
        setPreferredSize(dim);
    }

    private void cargarCapas(List<String> rutas) {
        for (String ruta : rutas) {
            URL recurso = getClass().getResource(ruta);
            if (recurso == null) {
                System.err.println("No se encontro la imagen: " + ruta);
                continue; 
            }
            try {
                capas.add(ImageIO.read(recurso));
            } catch (IOException e) {
                System.err.println("No se pudo leer la imagen: " + ruta);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Mejorar la calidad del escalado de imagen
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        for (BufferedImage capa : capas) {
            g2.drawImage(capa, 0, 0, w, h, this);
        }
    }
}
