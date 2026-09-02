package AdivinaAdivinador.Personajes;

public class Personaje {

    private final String nombre;
    private final Caracteristicas caracteristicas;

    public Personaje(String nombre, Caracteristicas caracteristicas) {
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
    }

    public String getNombre(){return nombre;}
    public Caracteristicas getCaracteristicas(){return caracteristicas;}

    @Override
    public String toString() {
        return (nombre + " - " + caracteristicas);
    }
}