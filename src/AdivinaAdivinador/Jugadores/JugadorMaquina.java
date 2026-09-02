package AdivinaAdivinador.Jugadores;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;

public class JugadorMaquina extends Jugador {

    public JugadorMaquina(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles) {
        super(personajes, preguntasDisponibles);
    }

    @Override
    public Personaje adivinarPersonaje() {
        // Lo hago dsp
        return null;
    }

    @Override
    public Pregunta elegirOpcion() {

        //aaaaa
        return getPreguntasDisponibles().get(0);
    }
}