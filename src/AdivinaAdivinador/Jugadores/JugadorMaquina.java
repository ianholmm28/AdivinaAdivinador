package AdivinaAdivinador.Jugadores;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;

public class JugadorMaquina extends Jugador {

    public JugadorMaquina(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto) {
        super(personajes, preguntasDisponibles, personajeSecreto);
    }

    @Override
    public Personaje adivinarPersonaje() {
        // Lo hago después
        return null;
    }

    @Override
    public Pregunta elegirOpcion() {
        //aaaaaauuuu
        return null;
    }
}