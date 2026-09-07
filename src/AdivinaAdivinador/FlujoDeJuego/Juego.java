package AdivinaAdivinador.FlujoDeJuego;
import AdivinaAdivinador.Jugadores.Jugador;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.Pregunta;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Utils.Logger;

import java.util.Random;

public class Juego {

    private final Jugador jugador1;
    private final Jugador jugador2;
    private final Random random = new Random();
    private final ComparadorDePreguntas comparadorDePreguntas;
    private final Logger logger;

    public Juego(Jugador jugador1, Jugador jugador2, ComparadorDePreguntas comparadorDePreguntas, Logger logger) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.comparadorDePreguntas = comparadorDePreguntas;
        this.logger = logger;
    }

    public Jugador getJugador1() { return jugador1; }
    public Jugador getJugador2() { return jugador2; }

    public boolean humanoHacePregunta(Pregunta preguntaElegida) {
        boolean resultado = comparadorDePreguntas.coincideCon(jugador2.getPersonajeSecreto(), preguntaElegida);
        jugador1.eliminarPersonajes(preguntaElegida, resultado);
        return resultado;
    }

    public boolean humanoAdivinaPersonaje(Personaje personajeElegido) {
        return personajeElegido.equals(jugador2.getPersonajeSecreto());
    }

    public String[] turnoMaquina() {
        return turnoCualquierMaquina(jugador2, jugador1);
    }

    public String[] turnoCualquierMaquina(Jugador actual, Jugador opuesto) {
        int opcion = actual.elegirOpcion();
        if (opcion == 1) {
            Pregunta pregunta = actual.elegirPregunta();
            boolean resultado = comparadorDePreguntas.coincideCon(opuesto.getPersonajeSecreto(), pregunta);
            actual.eliminarPersonajes(pregunta, resultado);
            return new String[] { actual.getNombre() + " pregunta: " + pregunta.getTexto(), resultado ? "SÍ" : "NO" };
        } else {
            Personaje personajeElegido = actual.adivinarPersonaje();
            if (personajeElegido.equals(opuesto.getPersonajeSecreto())) {
                return new String[] { "MAQUINA_GANA:" + actual.getNombre() + ":" + personajeElegido.getNombre() };
            } else {
                actual.getPersonajesDescartados().add(personajeElegido);
                return new String[] { actual.getNombre() + " intentó adivinar a " + personajeElegido.getNombre() + " y falló." };
            }
        }
    }
}