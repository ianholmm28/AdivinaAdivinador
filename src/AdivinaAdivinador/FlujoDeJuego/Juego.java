package AdivinaAdivinador.FlujoDeJuego;
import AdivinaAdivinador.Jugadores.Jugador;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.Pregunta;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;

import java.util.Random;

public class Juego {

    private final Jugador jugador1;
    private final Jugador jugador2;
    private final Random random = new Random();
    private final ComparadorDePreguntas comparadorDePreguntas;

    public Juego(Jugador jugador1, Jugador jugador2, ComparadorDePreguntas comparadorDePreguntas) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.comparadorDePreguntas = comparadorDePreguntas;
    }

    void jugarJuego() {

        boolean juegoTerminado = false;
        Jugador ganador = null;
        boolean turnoJugador1 = random.nextBoolean();

        while (!juegoTerminado){
            Jugador jugadorActual;
            if (turnoJugador1){
                jugadorActual = jugador1;
                //Debug
                System.out.println("=== PERSONAJES DEL JUGADOR HUMANO ===");
                for (Personaje personaje : jugador1.getPersonajes()) {
                    System.out.println(personaje);
                }
                System.out.println("PERSONAJE SECRETO DEL JUGADOR HUMANO:"+jugador1.getPersonajeSecreto());
            }
            else{
                jugadorActual = jugador2;
            }

            int opcion = jugadorActual.elegirOpcion();
            if (opcion == 1){
                Pregunta preguntaElegida = jugadorActual.elegirPregunta();
                boolean respuesta = comparadorDePreguntas.coincideCon(jugadorActual.getPersonajeSecreto(), preguntaElegida);
                jugadorActual.eliminarPersonajes(preguntaElegida, respuesta);

            }
            else{
                Personaje personajeElegido = jugadorActual.adivinarPersonaje();
                if (personajeElegido == jugadorActual.getPersonajeSecreto()){
                    ganador = jugadorActual;
                    juegoTerminado = true;
                }
            }
        }
        System.out.println("El ganador es " + ganador + ".");
    }
}