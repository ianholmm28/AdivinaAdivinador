package AdivinaAdivinador.Personajes;

public class CreadorDePersonaje {

    private final ProveedorDeNombres proveedorDeNombres;
    private final ProveedorDeCaracteristicas proveedorDeCaracteristicas;

    public CreadorDePersonaje(
            ProveedorDeNombres proveedorDeNombres,
            ProveedorDeCaracteristicas proveedorDeCaracteristicas) {

        this.proveedorDeNombres = proveedorDeNombres;
        this.proveedorDeCaracteristicas = proveedorDeCaracteristicas;
    }

    public Personaje crearPersonaje() {

        Caracteristicas caracteristicas =
                proveedorDeCaracteristicas.obtenerCaracteristicas();

        String nombre =
                proveedorDeNombres.obtenerNombre(caracteristicas.getGenero());

        return new Personaje(nombre, caracteristicas);
    }
}