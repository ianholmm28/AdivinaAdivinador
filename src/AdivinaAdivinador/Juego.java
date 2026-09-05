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
import AdivinaAdivinador.View.GameView;
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
        System.out.println("Iniciando AdivinaAdivinador GUI...");
    }

    void jugarJuego() {
        presentar();
        List<Personaje> personajes = creadorDeListaDePersonajes.generarPersonajes(23);
        
        Personaje personajeSecretoJugador1 = selectorDePersonajeSecreto.seleccionar(personajes);
        Personaje personajeSecretoJugador2 = selectorDePersonajeSecreto.seleccionar(personajes);

        //Lo puso Gemini - Entindo que es para llamar al componente de UI pero no se como funciona el 
        //java.awt.EventQueue.invokeLater(new Runnable())
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdivinaAdivinador.View.MenuView(personajes, personajeSecretoJugador1, personajeSecretoJugador2);
            }
        });
    }
}