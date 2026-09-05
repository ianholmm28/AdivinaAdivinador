package AdivinaAdivinador.View;

import javax.swing.*;
import java.awt.*;
import AdivinaAdivinador.Personajes.Personaje;
import java.util.List;

public class MenuView extends JFrame {

    public MenuView(List<Personaje> personajes, Personaje p1, Personaje p2) {
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
            GameView gv = new GameView(p1, personajes);
            gv.setTitle("Jugador Humano");
            dispose();
        });
        
        JButton btnMvM = new JButton("Máquina vs Máquina");
        btnMvM.setFont(new Font("Arial", Font.BOLD, 18));
        btnMvM.setBackground(new Color(220, 20, 60));
        btnMvM.setForeground(Color.WHITE);
        btnMvM.setFocusPainted(false);
        btnMvM.addActionListener(e -> {
            GameView gv1 = new GameView(p1, personajes);
            gv1.setTitle("Máquina 1");
            gv1.setLocation(gv1.getLocation().x - gv1.getWidth() / 2 - 10, gv1.getLocation().y);
            
            GameView gv2 = new GameView(p2, personajes);
            gv2.setTitle("Máquina 2");
            gv2.setLocation(gv2.getLocation().x + gv2.getWidth() / 2 + 10, gv2.getLocation().y);
            
            dispose();
        });
        
        panel.add(titulo);
        panel.add(btnHvM);
        panel.add(btnMvM);
        
        setContentPane(panel);
        setVisible(true);
    }
}
