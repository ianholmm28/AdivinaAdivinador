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

    public String turnoMaquina() {
        return turnoCualquierMaquina(jugador2, jugador1);
    }

    public String turnoCualquierMaquina(Jugador actual, Jugador opuesto) {
        int opcion = actual.elegirOpcion();
        if (opcion == 1) {
            Pregunta pregunta = actual.elegirPregunta();
            boolean resultado = comparadorDePreguntas.coincideCon(opuesto.getPersonajeSecreto(), pregunta);
            actual.eliminarPersonajes(pregunta, resultado);
            return actual.getNombre() + " pregunta: " + pregunta.getTexto() + "\nLa respuesta automática fue: " + (resultado ? "Sí" : "No");
        } else {
            Personaje personajeElegido = actual.adivinarPersonaje();
            if (personajeElegido.equals(opuesto.getPersonajeSecreto())) {
                return "MAQUINA_GANA:" + actual.getNombre() + ":" + personajeElegido.getNombre();
            } else {
                actual.getPersonajesDescartados().add(personajeElegido);
                return actual.getNombre() + " intentó adivinar a " + personajeElegido.getNombre() + " y falló.";
            }
        }
    }
}