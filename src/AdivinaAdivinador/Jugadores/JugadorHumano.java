package AdivinaAdivinador.Jugadores;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;

public class JugadorHumano extends Jugador {

    public JugadorHumano(String nombre, ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto, ComparadorDePreguntas comparadorDePreguntas) {
        super(nombre, personajes, preguntasDisponibles, personajeSecreto, comparadorDePreguntas);
    }
}