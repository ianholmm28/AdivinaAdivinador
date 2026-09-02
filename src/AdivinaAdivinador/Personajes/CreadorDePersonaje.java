package AdivinaAdivinador.Personajes;

public class CreadorDePersonaje {

    private final ProveedorDeNombres proveedorDeNombres;
    private final ProveedorDeCaracteristicas proveedorDeCaracteristicas;

    public CreadorDePersonaje(ProveedorDeNombres proveedorDeNombres, ProveedorDeCaracteristicas proveedorDeCaracteristicas) {
        this.proveedorDeNombres = proveedorDeNombres;
        this.proveedorDeCaracteristicas = proveedorDeCaracteristicas;
    }

    public Caracteristicas crearCaracteristicas() {
        return proveedorDeCaracteristicas.obtenerCaracteristicas();
    }

    public Personaje crearPersonaje(Caracteristicas caracteristicas) {
        String nombre = proveedorDeNombres.obtenerNombre(caracteristicas.getGenero());
        return new Personaje(nombre, caracteristicas);
    }
}