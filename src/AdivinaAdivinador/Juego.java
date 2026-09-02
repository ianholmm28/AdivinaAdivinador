package AdivinaAdivinador;
import AdivinaAdivinador.Jugadores.Jugador;
import AdivinaAdivinador.Jugadores.JugadorHumano;
import AdivinaAdivinador.Jugadores.JugadorMaquina;
import AdivinaAdivinador.Personajes.CreadorDeListaDePersonajes;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.Pregunta;

import java.util.List;

import java.util.ArrayList;

public class Juego {

    private final Sistema sistema;
    private final CreadorDeListaDePersonajes creadorDeListaDePersonajes;

    public Juego(Sistema sistema, CreadorDeListaDePersonajes creadorDeListaDePersonajes) {
        this.sistema = sistema;
        this.creadorDeListaDePersonajes = creadorDeListaDePersonajes;
    }

    public void presentar() {
        System.out.println("Bienvenido a AdivinaAdivinador.\n- 1: Humano vs Maquina\n- 2: Maquina vs Maquina");
    }

    void jugarJuego() {

        presentar();
        List<Personaje> personajes = creadorDeListaDePersonajes.generarPersonajes(23);
        int modo = sistema.ingresarInt(1,2);

        Jugador jugador1;
        if (modo == 1) {
            jugador1 = new JugadorHumano(new ArrayList<>(personajes), sistema);
        }
        else{
            jugador1 = new JugadorMaquina(new ArrayList<>(personajes));
        }

        Jugador jugador2 = new JugadorMaquina(new ArrayList<>(personajes));

        boolean juegoTerminado = false;
        Jugador ganador = null;
        boolean turnoJugador1 = false;

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