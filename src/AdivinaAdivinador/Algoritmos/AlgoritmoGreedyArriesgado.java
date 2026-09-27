package AdivinaAdivinador.Algoritmos;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;

public class AlgoritmoGreedyArriesgado implements IEstrategiaPregunta {

    private String ultimaExplicacion = "";

    @Override
    public String getNombreAlgoritmo() {
        return "AlgoritmoGreedyArriesgado";
    }

    @Override
    public String getUltimaExplicacion() {
        return ultimaExplicacion;
    }

    @Override
    public Pregunta elegirMejorPregunta(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntas, ComparadorDePreguntas comparadorDePreguntas) {
        Pregunta mejorPregunta = preguntas.get(0);
        int mayorDiferencia = -1;
        int mejorSi = 0;
        int mejorNo = 0;

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
                mejorSi = respuestasSi;
                mejorNo = respuestasNo;
            }
        }

        ultimaExplicacion = "Elegí la pregunta '" + mejorPregunta.getTexto() + "' usando " + getNombreAlgoritmo() +
                " porque busca la mayor disparidad (" + mejorSi + " dicen Sí vs " + mejorNo +
                " dicen No, diferencia máxima de " + mayorDiferencia + ") para intentar descartar un grupo masivo de personajes.";

        return mejorPregunta;
    }
}
