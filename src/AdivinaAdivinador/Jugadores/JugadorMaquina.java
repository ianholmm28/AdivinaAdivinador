package AdivinaAdivinador.Jugadores;

import AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego;
import AdivinaAdivinador.Personajes.Personaje;
import AdivinaAdivinador.Preguntas.ComparadorDePreguntas;
import AdivinaAdivinador.Preguntas.Pregunta;
import java.util.ArrayList;
import java.util.Random;
import AdivinaAdivinador.Algoritmos.AlgoritmoGreedy;

public class JugadorMaquina extends Jugador {

    private final Random random = new Random();
    private final AlgoritmoGreedy greedy = new AlgoritmoGreedy();
    private final CreadorDeJuego.Personalidad personalidad;

    public JugadorMaquina(String nombre, ArrayList<Personaje> personajes, ArrayList<Pregunta> preguntasDisponibles, Personaje personajeSecreto,
                          ComparadorDePreguntas comparadorDePreguntas, CreadorDeJuego.Personalidad personalidad) {
        super(nombre, personajes, preguntasDisponibles, personajeSecreto, comparadorDePreguntas);
        this.personalidad = personalidad;
    }

    private float obtenerUmbral(int cantidadPersonajes) {
        float umbral = 0;
        if (personalidad == CreadorDeJuego.Personalidad.ARRIESGADA) {
            switch (cantidadPersonajes) {
                case 1, 2, 3:
                    umbral = 1f;
                    break;
                case 4:
                    umbral = 0.8f;
                    break;
                case 5:
                    umbral = 0.5f;
                    break;
                case 6:
                    umbral = 0.2f;
                    break;
            }
        }
        else{
            switch (cantidadPersonajes){
                case 1, 2:
                    umbral = 1f;
                    break;
                case 3:
                    umbral = 0.3f;
                    break;
                case 4:
                    umbral = 0.05f;
                    break;
            }
        }
        return umbral;
    }

    @Override
    public int elegirOpcion() {
        System.out.println("=== TURNO DE " + getNombre() + " ===");
        if (Math.random() < obtenerUmbral(getPersonajesDisponibles().size()) || personalidad == CreadorDeJuego.Personalidad.LOCA) {
            System.out.println("Elegi adivinar.");
            return 2;
        }
        System.out.println("Elegi hacer pregunta.");
        return 1;
    }

    @Override
    public Pregunta elegirPregunta() {
        Pregunta mejorPregunta = greedy.elegirMejorPregunta(getPersonajesDisponibles(), getPreguntasDisponibles(), getComparadorDePreguntas());
        getPreguntasDisponibles().remove(mejorPregunta);
        return mejorPregunta;
    }

    @Override
    public Personaje adivinarPersonaje() {
        ArrayList<Personaje> personajesDisponibles = getPersonajesDisponibles();
        return getPersonajesDisponibles().get(random.nextInt(personajesDisponibles.size()));
    }
}