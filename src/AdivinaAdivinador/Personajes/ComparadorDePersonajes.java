package AdivinaAdivinador.Personajes;

public class ComparadorDePersonajes {

    public boolean sonIguales(Personaje personaje1, Personaje personaje2) {

        Caracteristicas caracteristicas1 = personaje1.getCaracteristicas();
        Caracteristicas caracteristicas2 = personaje2.getCaracteristicas();

        return caracteristicas1.esCalvo() == caracteristicas2.esCalvo()
                && caracteristicas1.getColorPelo() == caracteristicas2.getColorPelo()
                && caracteristicas1.getGenero() == caracteristicas2.getGenero()
                && caracteristicas1.usaLentes() == caracteristicas2.usaLentes()
                && caracteristicas1.tieneBarba() == caracteristicas2.tieneBarba()
                && caracteristicas1.getColorRemera() == caracteristicas2.getColorRemera()
                && caracteristicas1.usaGorro() == caracteristicas2.usaGorro();
    }
}