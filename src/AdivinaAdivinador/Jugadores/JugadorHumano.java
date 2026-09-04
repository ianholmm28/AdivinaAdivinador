package AdivinaAdivinador.Jugadores;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import AdivinaAdivinador.Sistema;
import java.util.ArrayList;

public class JugadorHumano extends Jugador{

    private final Sistema sistema;

    public JugadorHumano(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto, Sistema sistema, ComparadorDePreguntas comparadorDePreguntas) {
        super(personajes, preguntasDisponibles, personajeSecreto, comparadorDePreguntas);
        this.sistema = sistema;
    }

    @Override public int elegirOpcion() {
        System.out.println("=== Turno del Jugador 1 ===\n- 1: Elegir Pregunta\n- 2: Adivinar Personaje");
        return sistema.ingresarInt(1, 2);
    }

    @Override public Pregunta elegirPregunta() {
        ArrayList<Pregunta> preguntas = getPreguntasDisponibles();

        System.out.println("=== Preguntas disponibles ===");
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println((i + 1) + ": " + preguntas.get(i).getTexto());
        }

        int preguntaElegida = sistema.ingresarInt(1, preguntas.size());
        return preguntas.remove(preguntaElegida - 1);
    }

    @Override public Personaje adivinarPersonaje() {
        System.out.println("=== Personajes disponibles ===");
        for (int i = 0; i < getPersonajes().size(); i++) {
            System.out.println((i + 1) + ": " + getPersonajes().get(i).getNombre());
        }
        int personajeElegido = sistema.ingresarInt(1, getPersonajes().size());
        return getPersonajes().get(personajeElegido - 1);
    }
}