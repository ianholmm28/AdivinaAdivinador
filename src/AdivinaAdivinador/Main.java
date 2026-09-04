package AdivinaAdivinador;

import AdivinaAdivinador.Algoritmos.AlgoritmoMergeSort;
import AdivinaAdivinador.FlujoDeJuego.CreadorDeJuego;
import AdivinaAdivinador.FlujoDeJuego.Juego;
import AdivinaAdivinador.Personajes.*;
import AdivinaAdivinador.Preguntas.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        ProveedorDeNombres proveedorDeNombres = new ProveedorDeNombres(ListaDeNombres.nombresMasculinos(), ListaDeNombres.nombresFemeninos());

        ProveedorDeCaracteristicas proveedorDeCaracteristicas = new ProveedorDeCaracteristicas();

        CreadorDePersonaje creadorDePersonaje = new CreadorDePersonaje(proveedorDeNombres, proveedorDeCaracteristicas);

        ComparadorDePersonajes comparadorDePersonajes = new ComparadorDePersonajes();

        AlgoritmoMergeSort mergeSort = new AlgoritmoMergeSort();

        CreadorDeListaDePersonajes creadorDeListaDePersonajes = new CreadorDeListaDePersonajes(creadorDePersonaje, comparadorDePersonajes, mergeSort);

        CreadorDeListaDePreguntas creadorDeListaDePreguntas = new CreadorDeListaDePreguntas();

        SelectorDePersonajeSecreto selectorDePersonajeSecreto = new SelectorDePersonajeSecreto();

        ComparadorDePreguntas comparadorDePreguntas = new ComparadorDePreguntas();

        CreadorDeJuego creadorDeJuego = new CreadorDeJuego(sistema, creadorDeListaDePersonajes, creadorDeListaDePreguntas, selectorDePersonajeSecreto, comparadorDePreguntas);

        Juego juego = creadorDeJuego.crearJuego();
        juego.jugarJuego;
    }
}