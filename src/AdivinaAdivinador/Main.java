package AdivinaAdivinador;
import AdivinaAdivinador.Personajes.*;
import AdivinaAdivinador.Preguntas.CreadorDeListaDePreguntas;

public class Main {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        ProveedorDeNombres proveedorDeNombres = new ProveedorDeNombres(ListaDeNombres.nombresMasculinos(), ListaDeNombres.nombresFemeninos());

        ProveedorDeCaracteristicas proveedorDeCaracteristicas = new ProveedorDeCaracteristicas();

        CreadorDePersonaje creadorDePersonaje = new CreadorDePersonaje(proveedorDeNombres, proveedorDeCaracteristicas);

        ComparadorDePersonajes comparadorDePersonajes = new ComparadorDePersonajes();

        CreadorDeListaDePersonajes creadorDeListaDePersonajes = new CreadorDeListaDePersonajes(creadorDePersonaje, comparadorDePersonajes);

        SelectorDePersonajeSecreto selectorDePersonajeSecreto = new SelectorDePersonajeSecreto();

        CreadorDeListaDePreguntas creadorDeListaDePreguntas = new CreadorDeListaDePreguntas();

        Juego juego = new Juego(sistema, creadorDeListaDePersonajes, creadorDeListaDePreguntas, selectorDePersonajeSecreto);

        juego.jugarJuego();
    }
}