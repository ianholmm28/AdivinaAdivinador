package AdivinaAdivinador.Jugadores;

import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;
import java.util.Random;

public class JugadorMaquinaSegura extends Jugador {

    private final Random random = new Random();

    public JugadorMaquinaSegura(ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto, ComparadorDePreguntas comparadorDePreguntas) {
        super(personajes, preguntasDisponibles, personajeSecreto, comparadorDePreguntas);
    }

    @Override
    public int elegirOpcion() {
        int cantidadPersonajes = getPersonajes().size();
        if (cantidadPersonajes <= 2) {
            return 2;
        }
        if (cantidadPersonajes == 3 && Math.random() < 0.2) {
            return 2;
        }
        return 1;
    }

    @Override
    public Pregunta elegirPregunta() {
        Pregunta mejorPregunta = getPreguntasDisponibles().get(0);
        int mejorDiferencia = getPersonajes().size();

        for (Pregunta pregunta : getPreguntasDisponibles()) {
            int respuestasSi = 0;
            for (Personaje personaje : getPersonajes()) {
                if (getComparadorDePreguntas().coincideCon(personaje, pregunta)) {
                    respuestasSi++;
                }
            }
            int respuestasNo = getPersonajes().size() - respuestasSi;
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
        getPreguntasDisponibles().remove(mejorPregunta);
        return mejorPregunta;
    }

    @Override
    public Personaje adivinarPersonaje() {
        // Lo hago después
        return null;
    }
}