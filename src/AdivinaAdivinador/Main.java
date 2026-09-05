package AdivinaAdivinador;

import AdivinaAdivinador.View.MenuView;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuView().setVisible(true);
        });
    }
}