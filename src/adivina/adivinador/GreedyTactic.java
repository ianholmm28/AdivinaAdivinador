package adivina.adivinador;

import java.util.List;

public class GreedyTactic {

    // Paso 1, contar la cantidad de items que tienen algo en comun
    // paso 2, En base a lo anterior determinar que pregunta quita la mayoria de personajes
    // Paso 3, repetir hasta que se adivine
    // Paso 4, entregar resultado

    public String encontrarItemComun(List<Personaje> listaPersonajes) {
        String propiedad = "Ninguna";
        
        if (!listaPersonajes.isEmpty()) {
            Personaje primerPersonaje = listaPersonajes.get(0);
            
            propiedad = "Personaje ID: " + primerPersonaje.toString(); 
        }
        
        return propiedad;
    }
}
