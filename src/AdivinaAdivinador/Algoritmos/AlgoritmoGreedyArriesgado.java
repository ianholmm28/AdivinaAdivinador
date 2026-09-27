package AdivinaAdivinador.Algoritmos;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;

public class AlgoritmoGreedyArriesgado implements IEstrategiaPregunta {

    @Override
    public Pregunta elegirMejorPregunta(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntas, ComparadorDePreguntas comparadorDePreguntas) {
        Pregunta mejorPregunta = preguntas.get(0);
        int mayorDiferencia = -1;

        for (Pregunta pregunta : preguntas) {
            int respuestasSi = 0;
            for (Personaje personaje : personajes) {
                if (comparadorDePreguntas.coincideCon(personaje, pregunta)) {
                    respuestasSi++;
                }
            }

            int respuestasNo = personajes.size() - respuestasSi;

            if (respuestasSi == 0 || respuestasNo == 0) {
                continue;
            }

            int diferencia = Math.abs(respuestasSi - respuestasNo);


            if (diferencia > mayorDiferencia) {
                mayorDiferencia = diferencia;
                mejorPregunta = pregunta;
            }
        }

        return mejorPregunta;
    }
}
