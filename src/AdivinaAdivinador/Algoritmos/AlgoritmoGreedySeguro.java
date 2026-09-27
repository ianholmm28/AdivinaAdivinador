package AdivinaAdivinador.Algoritmos;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;

public class AlgoritmoGreedySeguro implements IEstrategiaPregunta {

    private String ultimaExplicacion = "";

    @Override
    public String getNombreAlgoritmo() {
        return "AlgoritmoGreedySeguro";
    }

    @Override
    public String getUltimaExplicacion() {
        return ultimaExplicacion;
    }

    @Override
    public Pregunta elegirMejorPregunta(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntas, ComparadorDePreguntas comparadorDePreguntas) {
        Pregunta mejorPregunta = preguntas.get(0);
        int mejorDiferencia = personajes.size();
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
            int diferencia = Math.abs(respuestasSi - respuestasNo);

            if (diferencia < mejorDiferencia) {
                mejorDiferencia = diferencia;
                mejorPregunta = pregunta;
                mejorSi = respuestasSi;
                mejorNo = respuestasNo;
            }
        }

        ultimaExplicacion = "Elegí la pregunta '" + mejorPregunta.getTexto() + "' usando " + getNombreAlgoritmo() +
                " porque busca dividir a los " + personajes.size() + " personajes disponibles lo más cerca del 50/50 posible (" +
                mejorSi + " dicen Sí vs " + mejorNo + " dicen No, diferencia mínima de " + mejorDiferencia + ").";

        return mejorPregunta;
    }
}