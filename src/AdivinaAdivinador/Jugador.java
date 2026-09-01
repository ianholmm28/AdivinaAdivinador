package AdivinaAdivinador;
import java.util.ArrayList;
import java.util.Random;

public abstract class Jugador {

    private ArrayList<Personaje> personajes;
    private ArrayList<Pregunta> preguntasDisponibles;
    private Personaje personajeSecreto;

    public Jugador(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
        preguntasDisponibles = new ArrayList<>();
        preguntasDisponibles.add(new Pregunta("¿Mi personaje es humano?", Pregunta.TipoPregunta.HUMANO));
        preguntasDisponibles.add(new Pregunta("¿Mi personaje es un villano?", Pregunta.TipoPregunta.VILLANO));
        preguntasDisponibles.add(new Pregunta("¿Mi personaje es sensible a la Fuerza?", Pregunta.TipoPregunta.FORCE_SENSITIVE));
        preguntasDisponibles.add(new Pregunta("¿Mi personaje es hombre?",Pregunta.TipoPregunta.MASCULINO));
        preguntasDisponibles.add(new Pregunta("¿Mi personaje tiene pelo?", Pregunta.TipoPregunta.PELO));
        Random random = new Random();
        personajeSecreto = personajes.get(random.nextInt(personajes.size()));
    }

    public ArrayList<Personaje> getPersonajes() {return personajes;}
    public Personaje getPersonajeSecreto(){return personajeSecreto;}

    public ArrayList<Pregunta> getPreguntasDisponibles() {
        return preguntasDisponibles;
    }

    public abstract Personaje adivinarPersonaje();
    public abstract Pregunta elegirOpcion();
}