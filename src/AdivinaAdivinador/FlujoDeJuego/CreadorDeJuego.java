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
import AdivinaAdivinador.Sistema;

import java.util.ArrayList;

public class CreadorDeJuego {

    public enum Modo {HUMANO_VS_MAQUINA, MAQUINA_VS_MAQUINA}
    public enum Personalidad {SEGURA, ARRIESGADA, LOCA};
    private final Sistema sistema;
    private final CreadorDeListaDePersonajes creadorDeListaDePersonajes;
    private final CreadorDeListaDePreguntas creadorDeListaDePreguntas;
    private final SelectorDePersonajeSecreto selectorDePersonajeSecreto;
    private final ComparadorDePreguntas comparadorDePreguntas;

    public CreadorDeJuego(Sistema sistema, CreadorDeListaDePersonajes creadorDeListaDePersonajes, CreadorDeListaDePreguntas creadorDeListaDePreguntas, SelectorDePersonajeSecreto selectorDePersonajeSecreto, ComparadorDePreguntas comparadorDePreguntas) {
        this.sistema = sistema;
        this.creadorDeListaDePersonajes = creadorDeListaDePersonajes;
        this.creadorDeListaDePreguntas = creadorDeListaDePreguntas;
        this.selectorDePersonajeSecreto = selectorDePersonajeSecreto;
        this.comparadorDePreguntas = comparadorDePreguntas;
    }

    public Juego crearJuego(Modo modo, Personalidad personalidad1, Personalidad personalidad2) {
        ArrayList<Personaje> personajes = creadorDeListaDePersonajes.generarPersonajes(23);
        ArrayList<Pregunta> preguntas = creadorDeListaDePreguntas.crearPreguntas();

        Jugador jugador1 = modo == Modo.HUMANO_VS_MAQUINA ? new JugadorHumano("HUMANO", personajes, new ArrayList<>(preguntas), selectorDePersonajeSecreto.seleccionar(personajes), sistema, comparadorDePreguntas) :
                                       new JugadorMaquina("MAQUINA " + personalidad1, personajes, new ArrayList<>(preguntas), selectorDePersonajeSecreto.seleccionar(personajes), comparadorDePreguntas, personalidad1);

        String nombreJugador2 = "MAQUINA " + personalidad2;
        Jugador jugador2 = new JugadorMaquina(nombreJugador2, new ArrayList<>(personajes), new ArrayList<>(preguntas), selectorDePersonajeSecreto.seleccionar(personajes), comparadorDePreguntas, personalidad2);

        return new Juego(jugador1, jugador2, comparadorDePreguntas);
    }
}