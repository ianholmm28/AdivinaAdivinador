package AdivinaAdivinador;
import AdivinaAdivinador.Jugadores.Jugador;
import AdivinaAdivinador.Jugadores.JugadorHumano;
import AdivinaAdivinador.Jugadores.JugadorMaquina;
import AdivinaAdivinador.Personajes.CreadorDeListaDePersonajes;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Personajes.SelectorDePersonajeSecreto;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.CreadorDeListaDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;

import java.util.List;

import java.util.ArrayList;

public class Juego {

    private final Sistema sistema;
    private final CreadorDeListaDePersonajes creadorDeListaDePersonajes;
    private final CreadorDeListaDePreguntas creadorDeListaDePreguntas;
    private final SelectorDePersonajeSecreto selectorDePersonajeSecreto;
    private final ComparadorDePreguntas comparadorDePreguntas;

    public Juego(Sistema sistema, CreadorDeListaDePersonajes creadorDeListaDePersonajes, CreadorDeListaDePreguntas creadorDeListaDePreguntas,
                 SelectorDePersonajeSecreto selectorDePersonajeSecreto, ComparadorDePreguntas comparadorDePreguntas) {
        this.sistema = sistema;
        this.creadorDeListaDePersonajes = creadorDeListaDePersonajes;
        this.creadorDeListaDePreguntas = creadorDeListaDePreguntas;
        this.selectorDePersonajeSecreto = selectorDePersonajeSecreto;
        this.comparadorDePreguntas = comparadorDePreguntas;
    }

    public void presentar() {
        System.out.println("Bienvenido a AdivinaAdivinador.\n- 1: Humano vs Maquina\n- 2: Maquina vs Maquina");
    }

    void jugarJuego() {

        presentar();
        List<Personaje> personajes = creadorDeListaDePersonajes.generarPersonajes(23);
        List<Pregunta> preguntas = creadorDeListaDePreguntas.crearPreguntas();
        int modo = sistema.ingresarInt(1,2);

        Personaje personajeSecretoJugador1 = selectorDePersonajeSecreto.seleccionar(personajes);
        Personaje personajeSecretoJugador2 = selectorDePersonajeSecreto.seleccionar(personajes);

        System.out.println("Modo de Juego: Humano vs Maquina. Elegi a tu rival:\n- 1: Maquina Segura\n- 2: Maquina Arriesgada");
        JugadorMaquina.Personalidad personalidad = sistema.ingresarInt(1,2) == 1 ? JugadorMaquina.Personalidad.SEGURA : JugadorMaquina.Personalidad.ARRIESGADA;

        Jugador jugador1;
        if (modo == 1) {
            jugador1 = new JugadorHumano(new ArrayList<>(personajes), new ArrayList<>(preguntas), personajeSecretoJugador1, sistema, comparadorDePreguntas);
        }
        else{
            jugador1 = new JugadorMaquina(new ArrayList<>(personajes), new ArrayList<>(preguntas), personajeSecretoJugador1, comparadorDePreguntas, personalidad);
        }

        Jugador jugador2 = new JugadorMaquina(new ArrayList<>(personajes), new ArrayList<>(preguntas), personajeSecretoJugador2, comparadorDePreguntas, personalidad);

        boolean juegoTerminado = false;
        Jugador ganador = null;
        boolean turnoJugador1 = false;



        while (!juegoTerminado){
            turnoJugador1 = !turnoJugador1;
            Jugador jugadorActual;
            if (turnoJugador1){
                jugadorActual = jugador1;
                //Debug
                System.out.println("=== PERSONAJES DEL JUGADOR HUMANO ===");
                for (Personaje personaje : jugador1.getPersonajes()) {
                    System.out.println(personaje);
                }
                System.out.println("=== PERSONAJE SECRETO DEL JUGADOR HUMANO ===\n"+jugador1.getPersonajeSecreto());
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