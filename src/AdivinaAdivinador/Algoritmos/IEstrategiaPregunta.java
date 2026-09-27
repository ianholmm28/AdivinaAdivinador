package AdivinaAdivinador.Algoritmos;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;

public interface IEstrategiaPregunta {
    Pregunta elegirMejorPregunta(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntas, ComparadorDePreguntas comparadorDePreguntas);
}
