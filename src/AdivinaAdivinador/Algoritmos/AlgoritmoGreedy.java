package AdivinaAdivinador.Algoritmos;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;

public class AlgoritmoGreedy {

    public Pregunta elegirMejorPregunta(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntas, ComparadorDePreguntas comparadorDePreguntas) {
        Pregunta mejorPregunta = preguntas.get(0);
        int mejorDiferencia = personajes.size();

        for (Pregunta pregunta : preguntas) {
            int respuestasSi = 0;
            for (Personaje personaje : personajes) {
                if (comparadorDePreguntas.coincideCon(personaje, pregunta)) {
                    respuestasSi++;
                }
            }

            int respuestasNo = personajes.size() - respuestasSi;
            int diferencia;

            if (respuestasSi > respuestasNo) {
                diferencia = respuestasSi - respuestasNo;
            } else {
                diferencia = respuestasNo - respuestasSi;
            }

            if (diferencia < mejorDiferencia) {
                mejorDiferencia = diferencia;
                mejorPregunta = pregunta;
            }
        }

        return mejorPregunta;
    }
}