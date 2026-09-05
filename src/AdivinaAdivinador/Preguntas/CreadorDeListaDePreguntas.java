package AdivinaAdivinador.Preguntas;
import java.util.ArrayList;

public class CreadorDeListaDePreguntas {

    public ArrayList<Pregunta> crearPreguntas() {

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new Pregunta("¿Mi personaje es hombre?", TiposDePregunta.GENERO));
        preguntas.add(new Pregunta("¿Mi personaje es calvo?", TiposDePregunta.CALVO));
        preguntas.add(new Pregunta("¿Mi personaje tiene barba?", TiposDePregunta.BARBA));
        preguntas.add(new Pregunta("¿Mi personaje usa lentes?", TiposDePregunta.LENTES));
        preguntas.add(new Pregunta("¿Mi personaje es rubio?", TiposDePregunta.PELO_RUBIO));
        preguntas.add(new Pregunta("Mi personaje es castaño?", TiposDePregunta.PELO_MARRON));
        preguntas.add(new Pregunta("¿Mi personaje es morocho?", TiposDePregunta.PELO_NEGRO));
        preguntas.add(new Pregunta("¿Mi personaje es pelirrojo?", TiposDePregunta.PELO_ROJO));
        preguntas.add(new Pregunta("¿Mi personaje tiene remera blanca?", TiposDePregunta.REMERA_BLANCA));
        preguntas.add(new Pregunta("¿Mi personaje tiene remera azul?", TiposDePregunta.REMERA_AZUL));
        preguntas.add(new Pregunta("¿Mi personaje tiene remera roja?", TiposDePregunta.REMERA_ROJA));
        preguntas.add(new Pregunta("¿Mi personaje tiene remera verde?", TiposDePregunta.REMERA_VERDE));
        preguntas.add(new Pregunta("¿Mi personaje tiene remera amarilla?", TiposDePregunta.REMERA_AMARILLA));
        preguntas.add(new Pregunta("¿Mi personaje tiene remera lila?", TiposDePregunta.REMERA_LILA));
        preguntas.add(new Pregunta("¿Mi personaje usa gorro?", TiposDePregunta.GORRO));

        return preguntas;
    }
}