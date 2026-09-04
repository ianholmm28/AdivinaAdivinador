package AdivinaAdivinador.Personajes;

public class Caracteristicas {

    public enum ColorPelo {MARRON, NEGRO, RUBIO, ROJO}
    public enum Genero {HOMBRE, MUJER}
    public enum ColorRemera {BLANCO, AZUL, ROJO, VERDE,LILA,AMARILLO}

    private final boolean esCalvo;
    private final ColorPelo colorPelo;
    private final Genero genero;
    private final boolean usaLentes;
    private final boolean tieneBarba;
    private final ColorRemera colorRemera;

    public Caracteristicas(boolean esCalvo, ColorPelo colorPelo, Genero genero, boolean usaLentes, boolean tieneBarba, ColorRemera colorRemera) {
        this.esCalvo = esCalvo;
        this.colorPelo = colorPelo;
        this.genero = genero;
        this.usaLentes = usaLentes;
        this.tieneBarba = tieneBarba;
        this.colorRemera = colorRemera;
    }

    public boolean esCalvo() {return esCalvo;}
    public ColorPelo getColorPelo() {return colorPelo;}
    public Genero getGenero() {return genero;}
    public boolean usaLentes() {return usaLentes;}
    public boolean tieneBarba() {return tieneBarba;}
    public ColorRemera getColorRemera() {return colorRemera;}

    @Override public String toString() {
        return "Genero: " + genero + " | Calvo: " + esCalvo + " | Color de pelo: " + colorPelo + " | Color de Remera: " + colorRemera + " | Barba: " + tieneBarba + " | Lentes: " + usaLentes;
    }
}