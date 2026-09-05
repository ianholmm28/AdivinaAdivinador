package AdivinaAdivinador.Personajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ResolverCapasPersonaje {

    private static final String RAIZ = "/AdivinaAdivinador.Assets/Imagenes/";
    int valor;
    
    public List<String> obtenerRutas(Personaje personaje) {
        Random random = new Random(personaje.getNombre().hashCode());
        List<String> rutas = new ArrayList<>();
        Caracteristicas c = personaje.getCaracteristicas();
        valor = random.nextInt(1, 3);
        String carpeta = (c.getGenero() == Caracteristicas.Genero.HOMBRE) ? "Hombre" : "Mujer";

        rutas.add(RAIZ + carpeta + "/" + (c.getGenero() == Caracteristicas.Genero.HOMBRE ? "pelado1.png" : "pelada"+valor+".png"));

        if (!c.esCalvo()) {
            String archivoPelo = "pelo_" + c.getColorPelo() + ".png";
            rutas.add(RAIZ + carpeta + "/" + archivoPelo);
        }
        rutas.add(RAIZ + "Remeras/" + c.getColorRemera()+".png");
        
        //Falta poner las barbas de mi abuelo como las pastillas del abuelo pero con barba XD
        // :v
        /*if(c.tieneBarba()){
            String archivoPelo = "barba_" + c.getColorPelo() + ".png";
            rutas.add(RAIZ + carpeta + "/" + archivoPelo);
          }*/
        if(c.usaLentes()){
            valor = random.nextInt(1, 3);
            rutas.add(RAIZ + "Anteojos/anteojos" + valor + ".png");
        }
        if(c.usaGorro()){
            if(c.getGenero() == Caracteristicas.Genero.HOMBRE){
                valor = random.nextInt(1, 4);
                rutas.add(RAIZ + "Sombrero/sombrero" + valor + ".png");
            }
            else{
                rutas.add(RAIZ + "Sombrero/sombrero3.png");
            }
        }

        
        
        
        return rutas;
    }
}