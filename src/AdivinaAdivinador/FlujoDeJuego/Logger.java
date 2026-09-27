package AdivinaAdivinador.FlujoDeJuego;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    
    private final String filePath;

    public Logger(String filePath) {
        this.filePath = filePath;
    }

    public void nuevaPartida() {
        try (FileWriter fw = new FileWriter(filePath, false);
             PrintWriter pw = new PrintWriter(fw)) {String cabecera = "=== NUEVA PARTIDA (" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ") ===";pw.println(cabecera);System.out.println(cabecera);}
        catch (IOException e) {
            System.err.println("Error iniciando log: " + e.getMessage());
        }
    }

    public void log(String mensaje) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String linea = "[" + timestamp + "] " + mensaje;

        System.out.println(linea);

        try (FileWriter fw = new FileWriter(filePath, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(linea);
        } catch (IOException e) {
            System.err.println("Error escribiendo log: " + e.getMessage());
        }
    }
}
