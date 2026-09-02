package AdivinaAdivinador.Personajes;
import java.util.ArrayList;
import java.util.Arrays;

public class ListaDeNombres {

    public static ArrayList<String> nombresFemeninos() {
        return new ArrayList<>(Arrays.asList("Ana", "Laura", "Sofía", "Micaela", "Milena", "Monica", "Valentina", "Tatiana", "Florencia", "Paola", "Patricia",
                                            "Emilia", "Marta", "Marina", "Cecilia", "Carina", "Delfina", "Celeste"));
    }

    public static ArrayList<String> nombresMasculinos() {
        return new ArrayList<>(Arrays.asList("Juan", "Pedro", "Carlos", "Ian", "Thomas", "Lautaro", "Santino", "Joaquín", "Diego", "Lionel", "Jorge", "Gonzalo",
                                            "Tiziano", "Alejo", "Mauro", "Matias", "Mateo", "Ignacio", "Julian", "Jose"));
    }
}