package AdivinaAdivinador.FlujoDeJuego;

import AdivinaAdivinador.Jugadores.Jugador;
import AdivinaAdivinador.Jugadores.JugadorHumano;
import AdivinaAdivinador.Jugadores.JugadorMaquina;
import AdivinaAdivinador.Personajes.CreadorDeListaDePersonajes;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Personajes.SelectorDePersonajeSecreto;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.CreadorDeListaDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;

import java.util.ArrayList;

public class CreadorDeJuego {

    public enum Modo {HUMANO_VS_MAQUINA, MAQUINA_VS_MAQUINA}
    public enum Personalidad {SEGURA, ARRIESGADA, LOCA};
    private final CreadorDeListaDePersonajes creadorDeListaDePersonajes;
    private final CreadorDeListaDePreguntas creadorDeListaDePreguntas;
    private final SelectorDePersonajeSecreto selectorDePersonajeSecreto;
    private final ComparadorDePreguntas comparadorDePreguntas;
    private final Logger logger;

    public CreadorDeJuego(CreadorDeListaDePersonajes creadorDeListaDePersonajes, CreadorDeListaDePreguntas creadorDeListaDePreguntas, SelectorDePersonajeSecreto selectorDePersonajeSecreto, ComparadorDePreguntas comparadorDePreguntas, Logger logger) {
        this.creadorDeListaDePersonajes = creadorDeListaDePersonajes;
        this.creadorDeListaDePreguntas = creadorDeListaDePreguntas;
        this.selectorDePersonajeSecreto = selectorDePersonajeSecreto;
        this.comparadorDePreguntas = comparadorDePreguntas;
        this.logger = logger;
    }

    public Juego crearJuego(Modo modo, Personalidad personalidad1, Personalidad personalidad2) {
        logger.nuevaPartida();
        if (modo == Modo.HUMANO_VS_MAQUINA) {
            logger.log("MODO: Humano vs Máquina");
            logger.log("Personalidad Máquina: " + personalidad2);
        } else {
            logger.log("MODO: Máquina vs Máquina");
            logger.log("Personalidad Máquina 1: " + personalidad1);
            logger.log("Personalidad Máquina 2: " + personalidad2);
        }

        ArrayList<Personaje> personajes = creadorDeListaDePersonajes.generarPersonajes(23);
        ArrayList<Pregunta> preguntas = creadorDeListaDePreguntas.crearPreguntas();

        Jugador jugador1 = modo == Modo.HUMANO_VS_MAQUINA ? new JugadorHumano("HUMANO", personajes, new ArrayList<>(preguntas), selectorDePersonajeSecreto.seleccionar(personajes), comparadorDePreguntas) :
                                       new JugadorMaquina("MAQUINA " + personalidad1, personajes, new ArrayList<>(preguntas), selectorDePersonajeSecreto.seleccionar(personajes), comparadorDePreguntas, personalidad1);

        String nombreJugador2 = "MAQUINA " + personalidad2;
        Jugador jugador2 = new JugadorMaquina(nombreJugador2, new ArrayList<>(personajes), new ArrayList<>(preguntas), selectorDePersonajeSecreto.seleccionar(personajes), comparadorDePreguntas, personalidad2, logger);

        logger.log("Personaje Secreto J1 (" + jugador1.getNombre() + "): " + jugador1.getPersonajeSecreto().getNombre());
        logger.log("Personaje Secreto J2 (" + jugador2.getNombre() + "): " + jugador2.getPersonajeSecreto().getNombre());

        return new Juego(jugador1, jugador2, comparadorDePreguntas, logger);
    }
}