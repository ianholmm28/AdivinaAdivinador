package AdivinaAdivinador.Jugadores;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.Pregunta;
import AdivinaAdivinador.Sistema;
import java.util.ArrayList;

public class JugadorHumano extends Jugador{

    private final Sistema sistema;

    public JugadorHumano(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Sistema sistema) {
        super(personajes, preguntasDisponibles);
        this.sistema = sistema;
    }

    @Override
    public Personaje adivinarPersonaje(){
        // lo hago dsp
        return null;
    }

    @Override
    public Pregunta elegirOpcion() {

        System.out.println("=== Turno del Jugador 1 ===\n" + "- 1: Elegir Pregunta\n" + "- 2: Adivinar Personaje");
        int opcion = sistema.ingresarInt(1, 2);

        if (opcion == 1) {
            ArrayList<Pregunta> preguntas = getPreguntasDisponibles();
            System.out.println("=== Preguntas disponibles ===");
            for (int i = 0; i < preguntas.size(); i++) {
                System.out.println((i + 1) + ": " + preguntas.get(i));
            }

            int preguntaElegida = sistema.ingresarInt(1, preguntas.size());return preguntas.remove(preguntaElegida - 1);
        }
        return null;
    }
}