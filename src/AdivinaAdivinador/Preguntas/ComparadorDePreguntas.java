package AdivinaAdivinador.Preguntas;

import AdivinaAdivinador.Personajes.Caracteristicas;
import AdivinaAdivinador.Personajes.Personaje;

public class ComparadorDePreguntas {

    public boolean coincideCon(Personaje personaje, Pregunta pregunta){
        switch (pregunta.getTipo()){
            case GENERO:
                return personaje.getCaracteristicas().getGenero() == Caracteristicas.Genero.HOMBRE;
            case CALVO:
                return personaje.getCaracteristicas().esCalvo();
            case BARBA:
                return personaje.getCaracteristicas().tieneBarba();
            case LENTES:
                return personaje.getCaracteristicas().usaLentes();
            case PELO_NEGRO:
                return personaje.getCaracteristicas().getColorPelo() == Caracteristicas.ColorPelo.NEGRO;
            case PELO_RUBIO:
                return personaje.getCaracteristicas().getColorPelo() == Caracteristicas.ColorPelo.RUBIO;
            case PELO_ROJO:
                return personaje.getCaracteristicas().getColorPelo() == Caracteristicas.ColorPelo.ROJO;
            case PELO_MARRON:
                return personaje.getCaracteristicas().getColorPelo() == Caracteristicas.ColorPelo.MARRON;
            case REMERA_BLANCA:
                return personaje.getCaracteristicas().getColorRemera() == Caracteristicas.ColorRemera.BLANCO;
            case REMERA_AZUL:
                return personaje.getCaracteristicas().getColorRemera() == Caracteristicas.ColorRemera.AZUL;
            case REMERA_ROJA:
                return personaje.getCaracteristicas().getColorRemera() == Caracteristicas.ColorRemera.ROJO;
            case REMERA_VERDE:
                return personaje.getCaracteristicas().getColorRemera() == Caracteristicas.ColorRemera.VERDE;
            case REMERA_AMARILLA:
                return personaje.getCaracteristicas().getColorRemera() == Caracteristicas.ColorRemera.AMARILLO;
            case REMERA_LILA:
                return personaje.getCaracteristicas().getColorRemera() == Caracteristicas.ColorRemera.LILA;
            case GORRO:
                return personaje.getCaracteristicas().usaGorro();
        }
        return false;
    }
}
