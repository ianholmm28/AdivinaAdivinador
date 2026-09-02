package AdivinaAdivinador.Jugadores;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.Pregunta;

import java.util.ArrayList;

public class JugadorMaquina extends Jugador {

    public JugadorMaquina(ArrayList<Personaje> personajes) {
        super(personajes);
    }

    @Override public Personaje adivinarPersonaje(){
        Personaje personaje = new Personaje(1, "a", true, true, true, true, true);
        return personaje;
    }

    @Override
    public Pregunta elegirOpcion() {
        Pregunta pregunta = new Pregunta("a", Pregunta.TipoPregunta.PELO);
        return pregunta;
    }
}