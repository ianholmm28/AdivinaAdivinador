package AdivinaAdivinador;
import AdivinaAdivinador.Personajes.ProveedorDeNombres;
import AdivinaAdivinador.Personajes.ProveedorDeCaracteristicas;
import AdivinaAdivinador.Personajes.CreadorDeListaDePersonajes;
import AdivinaAdivinador.Personajes.CreadorDePersonaje;
import AdivinaAdivinador.Personajes.ListaDeNombres;

public class Main {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        ProveedorDeNombres proveedorDeNombres = new ProveedorDeNombres(ListaDeNombres.nombresMasculinos(), ListaDeNombres.nombresFemeninos());

        ProveedorDeCaracteristicas proveedorDeCaracteristicas = new ProveedorDeCaracteristicas();

        CreadorDePersonaje creadorDePersonaje = new CreadorDePersonaje(proveedorDeNombres, proveedorDeCaracteristicas);

        CreadorDeListaDePersonajes creadorDeListaDePersonajes = new CreadorDeListaDePersonajes(creadorDePersonaje);

        Juego juego = new Juego(sistema, creadorDeListaDePersonajes);

        juego.jugarJuego();
    }
}