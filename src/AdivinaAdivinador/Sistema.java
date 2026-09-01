package AdivinaAdivinador;
import java.util.Scanner;

public class Sistema {

    Scanner scan = new Scanner(System.in);

    public int ingresarInt(int min, int max) {
        int opcion;
        while (true) {
            try {
                System.out.print("Input: ");
                opcion = scan.nextInt();
                if (opcion > max || opcion < min) {
                    throw new IllegalArgumentException();
                }
                scan.nextLine();
                break;
            } catch (Exception e) {
                scan.nextLine();
                System.out.println("Por favor, ingresa una opcion valida.");
            }
        }
        return opcion;
    }
}
