package AdivinaAdivinador.Personajes;
import java.util.ArrayList;
import java.util.List;

public class CreadorDeListaDePersonajes {

    private final CreadorDePersonaje creadorDePersonaje;

    public CreadorDeListaDePersonajes(
            CreadorDePersonaje creadorDePersonaje) {

        this.creadorDePersonaje = creadorDePersonaje;
    }

    public List<Personaje> generarPersonajes(int cantidad) {

        List<Personaje> personajes = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            personajes.add(creadorDePersonaje.crearPersonaje());
        }

        return personajes;
    }
}