package AdivinaAdivinador;

public class Personaje {

    private int id;
    private String nombre;
    private boolean esHumano;
    private boolean esVillano;
    private boolean esForceSensitive;
    private boolean esMasculino;
    private boolean tienePelo;

    public Personaje(int id, String nombre, boolean esHumano, boolean esVillano, boolean esForceSensitive,
                     boolean esMasculino, boolean tienePelo) {
        this.id = id;
        this.nombre = nombre;
        this.esHumano = esHumano;
        this.esVillano = esVillano;
        this.esForceSensitive = esForceSensitive;
        this.esMasculino = esMasculino;
        this.tienePelo = tienePelo;
    }

    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public boolean isEsHumano(){return esHumano;}
    public boolean isEsVillano(){return esVillano;}
    public boolean isEsForceSensitive(){return esForceSensitive;}
    public boolean isEsMasculino(){return esMasculino;}
    public boolean isTienePelo(){return tienePelo;}

    @Override
    public String toString() {
        return ("ID: "+id + " - Nombre: "+nombre + " - EsHumano: "+esHumano + " - EsVillano: "+esVillano + " - EsForceSensitive: "+esForceSensitive + " - EsMasculino: "+esMasculino + " - TienePelo: "+tienePelo);
    }
}