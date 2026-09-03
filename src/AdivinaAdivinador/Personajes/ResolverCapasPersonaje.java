package AdivinaAdivinador.Personajes;

import java.util.ArrayList;
import java.util.List;

public class ResolverCapasPersonaje {

    private static final String RAIZ = "/AdivinaAdivinador.Assets/Imagenes/";
    
    public List<String> obtenerRutas(Personaje personaje) {
        List<String> rutas = new ArrayList<>();
        Caracteristicas c = personaje.getCaracteristicas();
        String carpeta = (c.getGenero() == Caracteristicas.Genero.HOMBRE) ? "Hombre" : "Mujer";

        rutas.add(RAIZ + carpeta + "/" + (c.getGenero() == Caracteristicas.Genero.HOMBRE ? "pelado.png" : "pelada.png"));

        if (!c.esCalvo() && !c.getColorPelo().equals("NADA")) {
            String archivoPelo = "pelo_" + c.getColorPelo() + ".png";
            rutas.add(RAIZ + carpeta + "/" + archivoPelo);
            System.out.println("tengo pelo");
        }
        rutas.add(RAIZ + "Remeras/");

        return rutas;
    }
}