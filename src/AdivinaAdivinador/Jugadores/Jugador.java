package AdivinaAdivinador.Jugadores;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;

import java.util.ArrayList;
import java.util.Random;

public abstract class Jugador {

    private final ArrayList<Personaje> personajes;
    private final ArrayList<Pregunta> preguntasDisponibles;
    private final Personaje personajeSecreto;
    private final ComparadorDePreguntas comparadorDePreguntas;

    public Jugador(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto, ComparadorDePreguntas comparadorDePreguntas) {
        this.personajes = personajes;
        this.preguntasDisponibles = preguntasDisponibles;
        this.personajeSecreto = personajeSecreto;
        this.comparadorDePreguntas = comparadorDePreguntas;
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public Personaje getPersonajeSecreto() {
        return personajeSecreto;
    }

    public ArrayList<Pregunta> getPreguntasDisponibles() {return preguntasDisponibles;}

    public abstract Personaje adivinarPersonaje();

    public abstract Pregunta elegirPregunta();

    public abstract int elegirOpcion();

    public void eliminarPersonajes(Pregunta pregunta, boolean respuesta) {
        for (int i = getPersonajes().size() - 1; i >= 0; i--) {
            Personaje personaje = getPersonajes().get(i);
            if (comparadorDePreguntas.coincideCon(personaje, pregunta) != respuesta) {
                getPersonajes().remove(i);
            }
        }
    }
}