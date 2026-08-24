package adivina.adivinador;

/**
 *
 * @author holmian,Valentino cinti, Piston Lautaro
 */
public class Personaje {

    private int id;
    private String nombre;
    private String especie;
    private String bando;
    private int edad;
    private Boolean anteojos;


    private static final Personaje[] LISTA_PERSONAJES = new Personaje[23];

    static {
        LISTA_PERSONAJES[0]  = new Personaje(0, "Luke Skywalker", "Humano", "Rebelión", 23, false);
        LISTA_PERSONAJES[1]  = new Personaje(1, "Darth Vader", "Humano / Cyborg", "Imperio", 45, true);
        LISTA_PERSONAJES[2]  = new Personaje(2, "Leia Organa", "Humano", "Rebelión", 23, false);
        LISTA_PERSONAJES[3]  = new Personaje(3, "Han Solo", "Humano", "Aliado", 33, false);
        LISTA_PERSONAJES[4]  = new Personaje(4, "Yoda", "Desconocida", "Rebelión", 900, false);
        LISTA_PERSONAJES[5]  = new Personaje(5, "Chewbacca", "Wookiee", "Aliado", 200, false);
        LISTA_PERSONAJES[6]  = new Personaje(6, "Obi-Wan Kenobi", "Humano", "Rebelión", 57, false);
        LISTA_PERSONAJES[7]  = new Personaje(7, "Boba Fett", "Humano (Clon)", "Aliado", 35, true);
        LISTA_PERSONAJES[8]  = new Personaje(8, "Lando Calrissian", "Humano", "Aliado", 35, false);
        LISTA_PERSONAJES[9]  = new Personaje(9, "Emperador Palpatine", "Humano", "Imperio", 86, false);
        LISTA_PERSONAJES[10] = new Personaje(10, "Ahsoka Tano", "Togruta", "Rebelión", 32, false);
        LISTA_PERSONAJES[11] = new Personaje(11, "Din Djarin (Mando)", "Humano", "Aliado", 30, true);
        LISTA_PERSONAJES[12] = new Personaje(12, "Grand Moff Tarkin", "Humano", "Imperio", 64, false);
        LISTA_PERSONAJES[13] = new Personaje(13, "C-3PO", "Androide", "Rebelión", 112, true);
        LISTA_PERSONAJES[14] = new Personaje(14, "R2-D2", "Androide Astromecánico", "Rebelión", 33, false);
        LISTA_PERSONAJES[15] = new Personaje(15, "Rey", "Humana", "Rebelión", 19, false);
        LISTA_PERSONAJES[16] = new Personaje(16, "Kylo Ren", "Humano", "Imperio", 29, true);
        LISTA_PERSONAJES[17] = new Personaje(17, "Finn", "Humano", "Rebelión", 23, false);
        LISTA_PERSONAJES[18] = new Personaje(18, "Poe Dameron", "Humano", "Rebelión", 32, true);
        LISTA_PERSONAJES[19] = new Personaje(19, "Mace Windu", "Humano", "Aliado", 53, false);
        LISTA_PERSONAJES[20] = new Personaje(20, "Count Dooku", "Humano", "Imperio", 83, false);
        LISTA_PERSONAJES[21] = new Personaje(21, "General Grievous", "Kaleesh / Cyborg", "Imperio", 50, true);
        LISTA_PERSONAJES[22] = new Personaje(22, "Qui-Gon Jinn", "Humano", "Aliado", 60, false);
    }

       private Personaje(int id, String nombre, String especie, String bando, int edad, Boolean anteojos) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.bando = bando;
        this.edad = edad;
        this.anteojos = anteojos;
    }

    public Personaje(int id) {
        
            Personaje p = LISTA_PERSONAJES[id];
            this.id = p.id;
            this.nombre = p.nombre;
            this.especie = p.especie;
            this.bando = p.bando;
            this.edad = p.edad;
            this.anteojos = p.anteojos;
        }
    

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public String getBando() { return bando; }
    public int getEdad() { return edad; }
    public Boolean getAnteojos() { return anteojos; }

    @Override
    public String toString() {
        return id + " | Nombre = " + nombre + 
               ": especie = " + especie + 
               ", bando = " + bando + 
               ", edad = " + edad + 
               ", anteojos = " + anteojos + "\n";
    }
}