package AdivinaAdivinador.Personajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ResolverCapasPersonaje {

    private static final String RAIZ = "/AdivinaAdivinador.Assets/Imagenes/";
    int valor;
    
    public List<String> obtenerRutas(Personaje personaje) {
        Random random = new Random();
        List<String> rutas = new ArrayList<>();
        Caracteristicas c = personaje.getCaracteristicas();
        String carpeta = (c.getGenero() == Caracteristicas.Genero.HOMBRE) ? "Hombre" : "Mujer";

        rutas.add(RAIZ + carpeta + "/" + (c.getGenero() == Caracteristicas.Genero.HOMBRE ? "pelado.png" : "pelada.png"));

        if (!c.esCalvo()) {
            String archivoPelo = "pelo_" + c.getColorPelo() + ".png";
            rutas.add(RAIZ + carpeta + "/" + archivoPelo);
        }
        rutas.add(RAIZ + "Remeras/" + c.getColorRemera()+".png");
        if(c.tieneBarba()){
            String archivoPelo = "barba_" + c.getColorPelo() + ".png";
            rutas.add(RAIZ + carpeta + "/" + archivoPelo);
          }
        if(c.usaLentes()){
            valor = random.nextInt(1, 3);
            rutas.add(RAIZ + "Anteojos/anteojos" + valor + ".png");
        }
        if(c.usaGorro()){
            valor = random.nextInt(1, 3);
            rutas.add(RAIZ + "Anteojos/Sombrero" + valor + ".png");
        }

        
        
        
        return rutas;
    }
}