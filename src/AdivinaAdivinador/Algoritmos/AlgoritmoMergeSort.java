package AdivinaAdivinador.Algoritmos;

import AdivinaAdivinador.Personajes.Caracteristicas;
import AdivinaAdivinador.Personajes.Personaje;

import java.util.ArrayList;

public class AlgoritmoMergeSort {

    public ArrayList<Personaje> ordenarPorGenero(ArrayList<Personaje> personajes) {

        if (personajes.size() <= 1) {
            return personajes;
        }

        int mitad = personajes.size() / 2;
        ArrayList<Personaje> izquierda = new ArrayList<>(personajes.subList(0, mitad));
        ArrayList<Personaje> derecha = new ArrayList<>(personajes.subList(mitad, personajes.size()));

        izquierda = ordenarPorGenero(izquierda);
        derecha = ordenarPorGenero(derecha);
        return combinar(izquierda, derecha);
    }

    private ArrayList<Personaje> combinar(ArrayList<Personaje> izquierda, ArrayList<Personaje> derecha) {

        ArrayList<Personaje> resultado = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < izquierda.size() && j < derecha.size()) {
            if (esMenor(izquierda.get(i), derecha.get(j))) {
                resultado.add(izquierda.get(i));
                i++;
            } else {
                resultado.add(derecha.get(j));
                j++;
            }
        }

        while (i < izquierda.size()) {
            resultado.add(izquierda.get(i));
            i++;
        }

        while (j < derecha.size()) {
            resultado.add(derecha.get(j));
            j++;
        }

        return resultado;
    }

    private boolean esMenor(Personaje personaje1, Personaje personaje2) {

        Caracteristicas.Genero genero1 = personaje1.getCaracteristicas().getGenero();
        Caracteristicas.Genero genero2 = personaje2.getCaracteristicas().getGenero();

        if (genero1 == Caracteristicas.Genero.MUJER && genero2 == Caracteristicas.Genero.HOMBRE) {
            return true;
        }
        return false;
    }
}