package AdivinaAdivinador.Personajes;

import java.util.List;
import java.util.Random;

public class SelectorDePersonajeSecreto {

    private final Random random;

    public SelectorDePersonajeSecreto() {
        this.random = new Random();
    }

    public Personaje seleccionar(List<Personaje> personajes) {
        return personajes.get(random.nextInt(personajes.size()));
    }
}