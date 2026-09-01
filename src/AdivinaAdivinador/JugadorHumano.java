package AdivinaAdivinador;
import java.util.ArrayList;

public class JugadorHumano extends Jugador {

    Sistema sistema;

    public JugadorHumano(ArrayList<Personaje> personajes, Sistema sistema) {
        super(personajes);
        this.sistema = sistema;
    }

    @Override
    public Personaje adivinarPersonaje() {
        Personaje personaje = new Personaje(1, "a", true, true, true, true, true);
        return personaje;
    }

    @Override
    public Pregunta elegirOpcion() {

        System.out.println("=== Turno del Jugador 1 ===\n- 1: Elegir Pregunta\n- 2: Adivinar Personaje");

        int opcion = sistema.ingresarInt(1, 2);

        if (opcion == 1) {
            ArrayList<Pregunta> preguntas = getPreguntasDisponibles();
            System.out.println("=== Preguntas disponibles ===");
            for (int i = 0; i < preguntas.size(); i++) {
                System.out.println((i + 1) + ": " + preguntas.get(i));
            }

            int preguntaElegida = sistema.ingresarInt(1, preguntas.size());
            return preguntas.remove(preguntaElegida - 1);
        }
        else {
            return null;
        }
    }
}