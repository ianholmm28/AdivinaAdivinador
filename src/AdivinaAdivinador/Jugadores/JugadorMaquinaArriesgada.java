package AdivinaAdivinador.Jugadores;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import AdivinaAdivinador.Personajes.Personaje;

import java.util.ArrayList;
import java.util.Random;


public class JugadorMaquinaArriesgada extends Jugador {

    private final Random random = new Random();

    public JugadorMaquinaArriesgada(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto, ComparadorDePreguntas comparadorDePreguntas) {
        super(personajes, preguntasDisponibles, personajeSecreto, comparadorDePreguntas);
    }

    @Override
    public int elegirOpcion() {
        int cantidadPersonajes = getPersonajes().size();
        if (cantidadPersonajes <= 2) {
            return 2;
        }
        if (cantidadPersonajes == 3 && Math.random() < 0.2) {
            return 2;
        }
        return 1;
    }

    @Override
    public Pregunta elegirPregunta() {
        // Estrategia más arriesgada
        return null;
    }

    @Override
    public Personaje adivinarPersonaje() {
        // Elegir personaje
        return null;
    }
}
