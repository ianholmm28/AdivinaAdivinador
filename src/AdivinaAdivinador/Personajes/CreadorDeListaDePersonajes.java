package AdivinaAdivinador.Personajes;
import java.util.ArrayList;

public class CreadorDeListaDePersonajes {

    private final CreadorDePersonaje creadorDePersonaje;
    private final ComparadorDePersonajes comparador;

    public CreadorDeListaDePersonajes(CreadorDePersonaje creadorDePersonaje, ComparadorDePersonajes comparador) {
        this.creadorDePersonaje = creadorDePersonaje;
        this.comparador = comparador;
    }

    public ArrayList<Personaje> generarPersonajes(int cantidad) {
        ArrayList<Personaje> personajes = new ArrayList<>();
        while (personajes.size() < cantidad) {
            Caracteristicas caracteristicas = creadorDePersonaje.crearCaracteristicas();
            boolean repetido = false;
            for (int i = 0; i < personajes.size(); i++) {
                if (comparador.sonIguales(personajes.get(i), new Personaje("", caracteristicas))){
                    repetido = true;
                    break;
                }
            }
            if (!repetido){
                Personaje nuevo = creadorDePersonaje.crearPersonaje(caracteristicas);
                personajes.add(nuevo);
            }
        }
        return personajes;
    }
}