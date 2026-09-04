package AdivinaAdivinador.Jugadores;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;

public class JugadorMaquina extends Jugador {

    public JugadorMaquina(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto, ComparadorDePreguntas comparadorDePreguntas) {
        super(personajes, preguntasDisponibles, personajeSecreto, comparadorDePreguntas);
    }

    @Override
    public int elegirOpcion() {
        //aaaaaauuuu
        return 67;
    }

    @Override
    public Pregunta elegirPregunta() {
        // I have brought peace, freedom, justice and security to my new empire.
        // Your new empire?
        // Don't make me kill you.
        // Anakin my allegiance is to the republic, to democracy!!
        // If you're not with me, then you're my enemy.
        //Only a Sith deals in absolutes. I will do what i must.
        // You will try.
        return null;
    }

    @Override
    public Personaje adivinarPersonaje() {
        // Lo hago después
        return null;
    }
}