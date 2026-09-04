package AdivinaAdivinador.Personajes;

public class Caracteristicas {

    public enum ColorPelo {NEGRO, RUBIO, ROJO, MARRON,NADA}
    public enum Genero {HOMBRE, MUJER}

    private final boolean esCalvo;
    private final ColorPelo colorPelo;
    private final Genero genero;
    private final boolean usaLentes;
    private final boolean tieneBarba;

    public Caracteristicas(boolean esCalvo, ColorPelo colorPelo, Genero genero, boolean usaLentes, boolean tieneBarba) {
        this.esCalvo = esCalvo;
        this.colorPelo = colorPelo;
        this.genero = genero;
        this.usaLentes = usaLentes;
        this.tieneBarba = tieneBarba;
    }

    public boolean esCalvo() {return esCalvo;}
    public ColorPelo getColorPelo() {return colorPelo;}
    public Genero getGenero() {return genero;}
    public boolean usaLentes() {return usaLentes;}
    public boolean tieneBarba() {return tieneBarba;}

    @Override public String toString() {
        return "Genero: " + genero + " | Calvo: " + esCalvo + " | Color de pelo: " + colorPelo + " | Barba: " + tieneBarba + " | Lentes: " + usaLentes;
    }
}