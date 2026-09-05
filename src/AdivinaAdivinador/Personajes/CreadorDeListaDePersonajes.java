package AdivinaAdivinador.Personajes;
import java.util.ArrayList;
import AdivinaAdivinador.Algoritmos.AlgoritmoMergeSort;

public class CreadorDeListaDePersonajes {

    private final CreadorDePersonaje creadorDePersonaje;
    private final ComparadorDePersonajes comparador;
    private final AlgoritmoMergeSort mergeSort;

    public CreadorDeListaDePersonajes(CreadorDePersonaje creadorDePersonaje, ComparadorDePersonajes comparador, AlgoritmoMergeSort mergeSort) {
        this.creadorDePersonaje = creadorDePersonaje;
        this.comparador = comparador;
        this.mergeSort = mergeSort;
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
        personajes = mergeSort.ordenarPorGenero(personajes);
        return personajes;
    }
}