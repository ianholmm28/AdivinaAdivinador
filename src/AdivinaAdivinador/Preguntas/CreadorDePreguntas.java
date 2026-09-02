package AdivinaAdivinador.Preguntas;
import java.util.ArrayList;

public class CreadorDePreguntas {

    public enum TipoPregunta {GENERO, CALVO, LENTES, PELO_RUBIO, PELO_ROJO, PELO_NEGRO, BARBA}

    public ArrayList<Pregunta> crearPreguntas() {

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new Pregunta("¿Mi personaje es hombre?", TipoPregunta.GENERO));

        return preguntas;
    }
}
