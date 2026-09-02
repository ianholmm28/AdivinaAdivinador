package AdivinaAdivinador.Preguntas;
import java.util.ArrayList;

public class CreadorDePreguntas {
    public ArrayList<Pregunta> crearPreguntas() {

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new Pregunta(
                "¿Mi personaje es humano?",
                Pregunta.TipoPregunta.HUMANO
        ));

        preguntas.add(new Pregunta(
                "¿Mi personaje es un villano?",
                Pregunta.TipoPregunta.VILLANO
        ));

        preguntas.add(new Pregunta(
                "¿Mi personaje es sensible a la Fuerza?",
                Pregunta.TipoPregunta.FORCE_SENSITIVE
        ));

        preguntas.add(new Pregunta(
                "¿Mi personaje es hombre?",
                Pregunta.TipoPregunta.MASCULINO
        ));

        preguntas.add(new Pregunta(
                "¿Mi personaje tiene pelo?",
                Pregunta.TipoPregunta.PELO
        ));

        return preguntas;
    }
}
