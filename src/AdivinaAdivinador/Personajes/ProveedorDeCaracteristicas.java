package AdivinaAdivinador.Personajes;

public class ProveedorDeCaracteristicas {

    public Caracteristicas obtenerCaracteristicas() {

        Caracteristicas.ColorPelo colorPelo;
        Caracteristicas.Genero genero = Caracteristicas.Genero.values()[(int) (Math.random() * Caracteristicas.Genero.values().length)];

        boolean usaLentes = Math.random() < 0.4;
        boolean esCalvo;
        boolean tieneBarba;

        if (genero == Caracteristicas.Genero.HOMBRE) {
            esCalvo = Math.random() < 0.3;
            tieneBarba = Math.random() < 0.5;
        }
        else {
            esCalvo = false;
            tieneBarba = false;
        }

        if (esCalvo && !tieneBarba){
            colorPelo = Caracteristicas.ColorPelo.NADA;
        }
        else{
            colorPelo = Caracteristicas.ColorPelo.values()[(int) (Math.random() * Caracteristicas.ColorPelo.values().length)];
        }

        Caracteristicas caracteristicas = new Caracteristicas(esCalvo, colorPelo, genero, usaLentes, tieneBarba);
        return caracteristicas;
    }
}