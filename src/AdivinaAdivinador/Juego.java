package AdivinaAdivinador;
import AdivinaAdivinador.Jugadores.Jugador;
import AdivinaAdivinador.Jugadores.JugadorHumano;
import AdivinaAdivinador.Jugadores.JugadorMaquina;
import AdivinaAdivinador.Personajes.CreadorDeListaDePersonajes;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Personajes.SelectorDePersonajeSecreto;
import AdivinaAdivinador.Preguntas.CreadorDeListaDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;

import java.util.List;

import java.util.ArrayList;

public class Juego {

    private final Sistema sistema;
    private final CreadorDeListaDePersonajes creadorDeListaDePersonajes;
    private final CreadorDeListaDePreguntas creadorDeListaDePreguntas;
    private final SelectorDePersonajeSecreto selectorDePersonajeSecreto;

    public Juego(Sistema sistema, CreadorDeListaDePersonajes creadorDeListaDePersonajes, CreadorDeListaDePreguntas creadorDeListaDePreguntas, SelectorDePersonajeSecreto selectorDePersonajeSecreto) {
        this.sistema = sistema;
        this.creadorDeListaDePersonajes = creadorDeListaDePersonajes;
        this.creadorDeListaDePreguntas = creadorDeListaDePreguntas;
        this.selectorDePersonajeSecreto = selectorDePersonajeSecreto;
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

        Jugador jugador1;
        if (modo == 1) {
            jugador1 = new JugadorHumano(new ArrayList<>(personajes), new ArrayList<>(preguntas), personajeSecretoJugador1, sistema);
        }
        else{
            jugador1 = new JugadorMaquina(new ArrayList<>(personajes), new ArrayList<>(preguntas), personajeSecretoJugador1);
        }

        Jugador jugador2 = new JugadorMaquina(new ArrayList<>(personajes), new ArrayList<>(preguntas), personajeSecretoJugador2);

        boolean juegoTerminado = false;
        Jugador ganador = null;
        boolean turnoJugador1 = false;


        //debug
        System.out.println("=== PERSONAJES DEL JUGADOR HUMANO ===");
        for (Personaje personaje : jugador1.getPersonajes()) {
            System.out.println(personaje);
        }
        System.out.println("=== PERSONAJE SECRETO DEL JUGADOR HUMANO ===\n"+jugador1.getPersonajeSecreto());



        while (!juegoTerminado){
            turnoJugador1 = !turnoJugador1;
            Jugador jugadorActual;
            if (turnoJugador1){
                jugadorActual = jugador1;
            }
            else{
                jugadorActual = jugador2;
            }

            Pregunta pregunta = jugadorActual.elegirOpcion();
            if (pregunta != null){
                //Preguntini
            }
            else{
                Personaje personajeElegido = jugadorActual.adivinarPersonaje();
                if (personajeElegido == jugadorActual.getPersonajeSecreto()){
                    ganador = jugadorActual;
                    juegoTerminado = true;
                }
            }
        }
        System.out.println("El ganador es " + ganador + ". Que la Fuerza te acompañe.");
    }
}