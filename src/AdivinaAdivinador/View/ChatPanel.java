package AdivinaAdivinador.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import AdivinaAdivinador.Preguntas.Pregunta;

public class ChatPanel extends JPanel {
    
    private JPanel messagesContainer;
    private JScrollPane scrollPane;
    private JComboBox<String> cbCategorias;
    private JComboBox<PreguntaWrapper> cbPreguntas;
    private JButton btnEnviar;
    private Map<String, List<PreguntaWrapper>> preguntasAgrupadas;
    private boolean isHumanInputEnabled;
    private ActionListener onPreguntaEnviada;
    
    public ChatPanel(boolean isHumanInputEnabled) {
        this.isHumanInputEnabled = isHumanInputEnabled;
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(10, 20, 10, 20)); // Margen interno para que no pise las esquinas
        
        messagesContainer = new JPanel();
        messagesContainer.setLayout(new BoxLayout(messagesContainer, BoxLayout.Y_AXIS));
        messagesContainer.setOpaque(false);
        messagesContainer.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        scrollPane = new JScrollPane(messagesContainer);
        scrollPane.setPreferredSize(new Dimension(450, 180));
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        // Ocultar barras de scroll pero permitir seguir scrolleando
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Auto scroll to bottom
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
        
        if (isHumanInputEnabled) {
            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            inputPanel.setOpaque(false);
            cbCategorias = new JComboBox<>();
            cbPreguntas = new JComboBox<>();
            btnEnviar = new JButton("Preguntar");
            
            cbCategorias.addActionListener(e -> actualizarPreguntasDropdown());
            
            btnEnviar.addActionListener(e -> {
                PreguntaWrapper pw = (PreguntaWrapper) cbPreguntas.getSelectedItem();
                if (pw != null && onPreguntaEnviada != null) {
                    onPreguntaEnviada.actionPerformed(new java.awt.event.ActionEvent(pw.pregunta, 0, "Pregunta"));
                    pw.utilizada = true;
                    actualizarPreguntasDropdown();
                }
            });
            
            JLabel lblCat = new JLabel("Categoría:");
            lblCat.setForeground(Color.WHITE);
            inputPanel.add(lblCat);
            inputPanel.add(cbCategorias);
            
            JLabel lblPreg = new JLabel("Pregunta:");
            lblPreg.setForeground(Color.WHITE);
            inputPanel.add(lblPreg);
            inputPanel.add(cbPreguntas);
            
            inputPanel.add(btnEnviar);
            
            add(inputPanel, BorderLayout.SOUTH);
        }
    }
    
    public void setOnPreguntaEnviada(ActionListener listener) {
        this.onPreguntaEnviada = listener;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fondo
        g2.setColor(new Color(45, 54, 69)); // Gris/Azul oscuro para que resalte
        g2.fillRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 30, 30);
        
        // Borde
        g2.setColor(new Color(150, 150, 150, 100)); // Borde gris claro semi-transparente
        g2.setStroke(new java.awt.BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 30, 30);
        
        g2.dispose();
    }
    
    public void setPreguntas(List<Pregunta> preguntasList) {
        if (!isHumanInputEnabled) return;
        preguntasAgrupadas = new LinkedHashMap<>();
        preguntasAgrupadas.put("Género", new ArrayList<>());
        preguntasAgrupadas.put("Pelo", new ArrayList<>());
        preguntasAgrupadas.put("Accesorios/Rasgos", new ArrayList<>());
        preguntasAgrupadas.put("Remera", new ArrayList<>());
        
        for (Pregunta p : preguntasList) {
            String tipo = p.getTipo().name();
            String cat = "Accesorios/Rasgos";
            if (tipo.contains("GENERO")) cat = "Género";
            else if (tipo.contains("PELO") || tipo.contains("CALVO") || tipo.contains("MARRON") || tipo.contains("NEGRO") || tipo.contains("ROJO")) cat = "Pelo";
            else if (tipo.contains("REMERA")) cat = "Remera";
            
            preguntasAgrupadas.get(cat).add(new PreguntaWrapper(p));
        }
        
        for (String cat : preguntasAgrupadas.keySet()) {
            cbCategorias.addItem(cat);
        }
        
        if (cbCategorias.getItemCount() > 0) {
            cbCategorias.setSelectedIndex(0);
        }
    }
    
    public void setInputEnabled(boolean enabled) {
        if (isHumanInputEnabled) {
            cbCategorias.setEnabled(enabled);
            cbPreguntas.setEnabled(enabled);
            if (enabled) {
                actualizarPreguntasDropdown();
            } else {
                btnEnviar.setEnabled(false);
            }
        }
    }
    
    private void actualizarPreguntasDropdown() {
        String cat = (String) cbCategorias.getSelectedItem();
        cbPreguntas.removeAllItems();
        if (cat != null && preguntasAgrupadas != null) {
            for (PreguntaWrapper pw : preguntasAgrupadas.get(cat)) {
                if (!pw.utilizada) {
                    cbPreguntas.addItem(pw);
                }
            }
        }
        btnEnviar.setEnabled(cbPreguntas.getItemCount() > 0 && cbPreguntas.isEnabled());
    }
    
    public void addMessage(String sender, String text, boolean isSender) {
        ChatBubble bubble = new ChatBubble(sender, text, isSender);
        
        JPanel wrapper = new JPanel(new FlowLayout(isSender ? FlowLayout.RIGHT : FlowLayout.LEFT, 0, 5));
        wrapper.setOpaque(false);
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, bubble.getPreferredSize().height + 10));
        wrapper.add(bubble);
        
        messagesContainer.add(wrapper);
        messagesContainer.revalidate();
        messagesContainer.repaint();
        
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }
    
    // Burbuja estilo WhatsApp
    private class ChatBubble extends JPanel {
        private String sender;
        private String text;
        private boolean isSender;

        public ChatBubble(String sender, String text, boolean isSender) {
            this.sender = sender;
            this.text = text;
            this.isSender = isSender;
            setOpaque(false);
            
            setLayout(new BorderLayout());
            setBorder(new EmptyBorder(8, 12, 8, 12));
            
            JLabel lblSender = new JLabel(sender);
            lblSender.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblSender.setForeground(isSender ? new Color(0, 100, 0) : new Color(0, 51, 153));
            
            JLabel lblText = new JLabel("<html><p style='width: 200px; font-family: Segoe UI, sans-serif;'>" + text + "</p></html>");
            lblText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblText.setForeground(new Color(40, 40, 40));
            
            add(lblSender, BorderLayout.NORTH);
            add(lblText, BorderLayout.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (isSender) {
                g2.setColor(new Color(210, 210, 210)); // Gris claro en lugar de verde
            } else {
                g2.setColor(Color.WHITE); // Blanco
            }
            
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            g2.dispose();
            super.paintComponent(g);
        }
    }
    
    private class PreguntaWrapper {
        Pregunta pregunta;
        boolean utilizada = false;
        
        PreguntaWrapper(Pregunta p) {
            this.pregunta = p;
        }
        
        @Override
        public String toString() {
            String txt = pregunta.getTexto();
            if (txt.equals("¿Mi personaje es hombre?")) return "Hombre";
            if (txt.equals("¿Mi personaje es mujer?")) return "Mujer";
            if (txt.startsWith("¿Mi personaje tiene remera ")) {
                return txt.replace("¿Mi personaje tiene remera ", "").replace("?", "");
            }
            if (txt.startsWith("¿Mi personaje es ")) {
                return txt.replace("¿Mi personaje es ", "").replace("?", "");
            }
            if (txt.startsWith("Mi personaje es ")) {
                return txt.replace("Mi personaje es ", "").replace("?", "");
            }
            if (txt.startsWith("¿Mi personaje tiene ")) {
                return txt.replace("¿Mi personaje tiene ", "").replace("?", "");
            }
            if (txt.startsWith("¿Mi personaje usa ")) {
                return txt.replace("¿Mi personaje usa ", "").replace("?", "");
            }
            return txt;
        }
    }
}
