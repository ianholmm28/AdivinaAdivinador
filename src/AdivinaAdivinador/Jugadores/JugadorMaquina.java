package AdivinaAdivinador.Jugadores;

import AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;
import java.util.Random;
import AdivinaAdivinador.Algoritmos.AlgoritmoGreedy;

public class JugadorMaquina extends Jugador {

    private final Random random = new Random();
    private final AlgoritmoGreedy greedy = new AlgoritmoGreedy();
    private final CreadorDeJuego.Personalidad personalidad;

    public JugadorMaquina(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto,
                          ComparadorDePreguntas comparadorDePreguntas, CreadorDeJuego.Personalidad personalidad) {
        super(personajes, preguntasDisponibles, personajeSecreto, comparadorDePreguntas);
        this.personalidad = personalidad;
    }

    @Override
    public int elegirOpcion() {
        int cantidadPersonajes = getPersonajes().size();
        if (cantidadPersonajes <= 2) {
            return 2;
        }
        if (personalidad == CreadorDeJuego.Personalidad.ARRIESGADA && cantidadPersonajes <= 4 && Math.random() < 0.5) {
            return 2;
        }
        return 1;
    }

    @Override
    public Pregunta elegirPregunta() {
        Pregunta mejorPregunta = greedy.elegirMejorPregunta(getPersonajes(), getPreguntasDisponibles(), getComparadorDePreguntas());
        getPreguntasDisponibles().remove(mejorPregunta);
        return mejorPregunta;
    }

    @Override
    public Personaje adivinarPersonaje() {
        // Lo hago después
        return null;
    }
}