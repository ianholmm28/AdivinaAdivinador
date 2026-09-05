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

    public void jugarJuego() {

        boolean juegoTerminado = false;
        Jugador ganador = null;
        boolean turnoJugador1 = random.nextBoolean();

        while (!juegoTerminado) {
            turnoJugador1 = !turnoJugador1;
            Jugador jugadorActual;
            Jugador jugadorOpuesto;
            if (turnoJugador1) {
                jugadorActual = jugador1;
                jugadorOpuesto = jugador2;
                //Debug
                System.out.println("\n=== PERSONAJES DE " + jugadorActual.getNombre() + " ===");
                for (Personaje personaje : jugador1.getPersonajesDisponibles()) {
                    System.out.println(personaje);
                }
                System.out.println("PERSONAJE SECRETO DEL JUGADOR HUMANO:" + jugador1.getPersonajeSecreto());
            } else {
                jugadorActual = jugador2;
                jugadorOpuesto = jugador1;
                //Debug
                System.out.println("\n=== PERSONAJES DE " + jugadorActual.getNombre() + " ===");
                for (Personaje personaje : jugador2.getPersonajesDisponibles()) {
                    System.out.println(personaje);
                }
                System.out.println("PERSONAJE SECRETO DE MAQUINA:" + jugador2.getPersonajeSecreto());
            }
                boolean resultado = false;
                int opcion = jugadorActual.elegirOpcion();
                if (opcion == 1) {
                    Pregunta preguntaElegida = jugadorActual.elegirPregunta();
                    System.out.println(jugadorActual.getNombre() + ": " + preguntaElegida.getTexto());
                    resultado = comparadorDePreguntas.coincideCon(jugadorActual.getPersonajeSecreto(), preguntaElegida);
                    jugadorActual.eliminarPersonajes(preguntaElegida, resultado);
                } else {
                    Personaje personajeElegido = jugadorActual.adivinarPersonaje();
                    System.out.println(jugadorActual.getNombre() + ": ¿Mi personaje es " + personajeElegido.getNombre() + "?");
                    if (personajeElegido.equals(jugadorActual.getPersonajeSecreto())) {
                        resultado = true;
                        ganador = jugadorActual;
                        juegoTerminado = true;
                    } else {
                        jugadorActual.getPersonajesDescartados().add(personajeElegido);
                    }
                }
                String respuesta = resultado == true ? "Sí" : "No";
                System.out.println(jugadorOpuesto.getNombre() + ": " + respuesta);
            }
        System.out.println("El ganador es " + ganador.getNombre() + ".");
        }
    }