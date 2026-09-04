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

    enum Modo {HUMANO_VS_MAQUINA, MAQUINA_VS_MAQUINA}
    public enum Personalidad {SEGURA, ARRIESGADA};
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

    private Modo elegirModo(){
        System.out.println("Bienvenido a AdivinaAdivinador Selecciona el modo de juego:\n- 1: Humano vs Maquina\n- 2: Maquina vs Maquina");
        Modo modo = sistema.ingresarInt(1,2) == 1 ? Modo.HUMANO_VS_MAQUINA : Modo.MAQUINA_VS_MAQUINA;
        System.out.println("----------");
        return modo;
    }

    private Personalidad elegirPersonalidadMaquina(Modo modo){
        Personalidad personalidad = Personalidad.ARRIESGADA;
        if (modo == Modo.HUMANO_VS_MAQUINA){
            System.out.println("Selecciona la personalidad de la maquina:\n- 1: Segura\n- 2: Arriesgada");
            personalidad = sistema.ingresarInt(1,2) == 1 ? Personalidad.SEGURA : Personalidad.ARRIESGADA;
            System.out.println("----------");
        }
        return personalidad;
    }

    public Juego crearJuego() {
        ArrayList<Personaje> personajes = creadorDeListaDePersonajes.generarPersonajes(23);
        ArrayList<Pregunta> preguntas = creadorDeListaDePreguntas.crearPreguntas();

        Modo modo = elegirModo();
        Jugador jugador1 = modo == Modo.HUMANO_VS_MAQUINA ? new JugadorHumano(personajes, preguntas, selectorDePersonajeSecreto.seleccionar(personajes), sistema, comparadorDePreguntas) :
                                       new JugadorMaquina(personajes, preguntas, selectorDePersonajeSecreto.seleccionar(personajes), comparadorDePreguntas, Personalidad.SEGURA);

        Personalidad personalidad = elegirPersonalidadMaquina(modo);
        Jugador jugador2 = new JugadorMaquina(personajes, preguntas, selectorDePersonajeSecreto.seleccionar(personajes), comparadorDePreguntas, personalidad);

        return new Juego(jugador1, jugador2, comparadorDePreguntas);
    }
}