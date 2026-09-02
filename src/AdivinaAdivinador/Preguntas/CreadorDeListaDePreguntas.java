package AdivinaAdivinador.Preguntas;
import java.util.ArrayList;

public class CreadorDeListaDePreguntas {

    public ArrayList<Pregunta> crearPreguntas() {

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new Pregunta("¿Mi personaje es hombre?", TiposDePregunta.GENERO));
        preguntas.add(new Pregunta("¿Mi personaje es calvo?", TiposDePregunta.CALVO));
        preguntas.add(new Pregunta("¿Mi personaje tiene barba?", TiposDePregunta.BARBA));
        preguntas.add(new Pregunta("¿Mi personaje usa lentes?", TiposDePregunta.LENTES));
        preguntas.add(new Pregunta("¿Mi personaje tiene pelo rubio?", TiposDePregunta.PELO_RUBIO));
        preguntas.add(new Pregunta("¿Mi personaje tiene pelo negro?", TiposDePregunta.PELO_NEGRO));
        preguntas.add(new Pregunta("¿Mi personaje tiene pelo rojo?", TiposDePregunta.PELO_ROJO));

        return preguntas;
    }
}