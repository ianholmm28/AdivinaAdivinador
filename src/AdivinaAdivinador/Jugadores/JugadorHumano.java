package AdivinaAdivinador.Jugadores;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import AdivinaAdivinador.Sistema;
import java.util.ArrayList;

public class JugadorHumano extends Jugador{

    private final Sistema sistema;

    public JugadorHumano(String nombre, ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto, Sistema sistema, ComparadorDePreguntas comparadorDePreguntas) {
        super(nombre, personajes, preguntasDisponibles, personajeSecreto, comparadorDePreguntas);
        this.sistema = sistema;
    }

    @Override public int elegirOpcion() {
        System.out.println("=== TURNO DE " + getNombre() + " ===\n- 1: Elegir Pregunta\n- 2: Adivinar Personaje");
        int opcion = sistema.ingresarInt(1, 2);
        while (opcion == 1 && getPreguntasDisponibles().isEmpty()){
            System.out.println("No quedan pregunta disponibles. Elegi otra opcion.");
            opcion = sistema.ingresarInt(1, 2);
        }
        return opcion;
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
        ArrayList<Personaje> disponibles = getPersonajesDisponibles();
        for (int i = 0; i < disponibles.size(); i++) {
            System.out.println((i + 1) + ": " + disponibles.get(i).getNombre());
        }
        int personajeElegido = sistema.ingresarInt(1, disponibles.size());
        return disponibles.get(personajeElegido - 1);
    }
}