package AdivinaAdivinador;

import AdivinaAdivinador.View.MenuView;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            // Personalizaciones globales
            javax.swing.UIManager.put("Button.font", new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
            javax.swing.UIManager.put("Label.font", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            javax.swing.UIManager.put("ComboBox.font", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new MenuView().setVisible(true);
        });
    }
}