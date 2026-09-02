package AdivinaAdivinador.Personajes;
import java.util.ArrayList;
import java.util.Random;

public class ProveedorDeNombres {

    private final Random random = new Random();

    private final ArrayList<String> nombresMasculinos;
    private final ArrayList<String> nombresFemeninos;

    public ProveedorDeNombres(
            ArrayList<String> nombresMasculinos,
            ArrayList<String> nombresFemeninos) {

        this.nombresMasculinos = new ArrayList<>(nombresMasculinos);
        this.nombresFemeninos = new ArrayList<>(nombresFemeninos);
    }

    public String obtenerNombre(Caracteristicas.Genero genero) {

        if (genero == Caracteristicas.Genero.HOMBRE) {
            return obtenerNombre(nombresMasculinos);
        }

        return obtenerNombre(nombresFemeninos);
    }

    private String obtenerNombre(ArrayList<String> nombres) {
        int indice = random.nextInt(nombres.size());
        return nombres.remove(indice);
    }
}