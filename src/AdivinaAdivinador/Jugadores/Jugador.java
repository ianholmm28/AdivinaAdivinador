package AdivinaAdivinador.Jugadores;
import AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;

import java.util.ArrayList;
import java.util.Random;

public abstract class Jugador {

    private final String nombre;
    private final ArrayList<Personaje> personajes;
    private final ArrayList<Personaje> personajesDescartados;
    private final ArrayList<Pregunta> preguntasDisponibles;
    private final Personaje personajeSecreto;
    private final ComparadorDePreguntas comparadorDePreguntas;

    public Jugador(String nombre, ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto, ComparadorDePreguntas comparadorDePreguntas) {
        this.nombre = nombre;
        this.personajes = personajes;
        this.preguntasDisponibles = preguntasDisponibles;
        this.personajeSecreto = personajeSecreto;
        this.comparadorDePreguntas = comparadorDePreguntas;
        this.personajesDescartados = new ArrayList<>();
    }

    public String getNombre(){return nombre;}

    public ArrayList<Personaje> getPersonajes() {return personajes;}

    public ArrayList<Personaje> getPersonajesDescartados() {return personajesDescartados;}

    public ArrayList<Personaje> getPersonajesDisponibles(){
        ArrayList<Personaje> disponibles = new ArrayList<>();
        for (Personaje personaje : getPersonajes()){
            if (!personajesDescartados.contains(personaje)){
                disponibles.add(personaje);
            }
        }
        return disponibles;
    }

    public Personaje getPersonajeSecreto() {
        return personajeSecreto;
    }

    public ArrayList<Pregunta> getPreguntasDisponibles() {return preguntasDisponibles;}

    public ComparadorDePreguntas getComparadorDePreguntas() {return comparadorDePreguntas;}

    public abstract Personaje adivinarPersonaje();

    public abstract Pregunta elegirPregunta();

    public abstract int elegirOpcion();

    public void eliminarPersonajes(Pregunta pregunta, boolean respuesta) {
        for (Personaje personaje : personajes) {
            if (!personajesDescartados.contains(personaje) && comparadorDePreguntas.coincideCon(personaje, pregunta) != respuesta) {
                personajesDescartados.add(personaje);
            }
        }
    }
}