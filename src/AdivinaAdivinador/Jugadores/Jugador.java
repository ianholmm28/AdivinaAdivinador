package AdivinaAdivinador.Jugadores;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.Pregunta;

import java.util.ArrayList;
import java.util.Random;

public abstract class Jugador {

    private final ArrayList<Personaje> personajes;
    private final ArrayList<Pregunta> preguntasDisponibles;
    private final Personaje personajeSecreto;

    public Jugador(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles) {
        this.personajes = personajes;
        this.preguntasDisponibles = preguntasDisponibles;

        Random random = new Random();
        personajeSecreto = personajes.get(random.nextInt(personajes.size()));
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public Personaje getPersonajeSecreto() {
        return personajeSecreto;
    }

    public ArrayList<Pregunta> getPreguntasDisponibles() {
        return preguntasDisponibles;
    }

    public abstract Personaje adivinarPersonaje();

    public abstract Pregunta elegirOpcion();
}