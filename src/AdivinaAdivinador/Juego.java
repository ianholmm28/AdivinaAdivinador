package AdivinaAdivinador;
import java.util.ArrayList;

public class Juego {

    Sistema sistema = new Sistema();

    public void Presentar() {
        System.out.println("Bienvenido a AdivinaAdivinador de Star Wars. Esto me imagino que se saca cuando se haga la interfaz.");
        System.out.println("- 1: Humano vs Maquina\n- 2: Maquina vs Maquina");
    }

    public ArrayList<Personaje> iniciarListaPersonajes() {

        String[] nombres = {
                "Obi-Wan Kenobi (Ep II)",
                "Luke Skywalker (Ep IV)",
                "Han Solo (Ep V)",
                "Lando Calrissian (Ep V)",
                "Anakin Skywalker (Ep II)",
                "Darth Vader (Ep V)",
                "Palpatine (Ep II)",
                "Count Dooku",
                "Boba Fett",
                "Mace Windu",
                "Poe Dameron (Ep VII)",
                "Yoda",
                "Chewbacca",
                "Leia Organa",
                "Padmé Amidala",
                "Rey (Ep VIII)",
                "Ahsoka Tano (TCW S7)",
                "Asajj Ventress",
                "Capitan Phasma",
                "Bo-Katan Kryze",
                "Hera Syndulla",
                "Kylo Ren (Ep VII)",
                "Mon Mothma (Ep VI)"
        };
        boolean[] esHumano = {true, true, true, true, true, true, true, true, true, true, true, false, false, true, true, true, false, false, false, true, false, true, true};
        boolean[] esVillano = {false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, true, true, false, false, true, false};
        boolean[] esMasculino = {true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, true, false};
        boolean[] tienePelo = {true, true, true, true, true, false, false, true, false, false, true, false, true, true, true, true, true, false, false, true, true, true, true};
        boolean[] tieneBarba = {true, false, false, true, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false};
        boolean[] esForceSensitive = {true, true, false, false, true, true, true, true, false, true, false, true, false, true, false, true, true, true, false, false, false, true, false};
        boolean[] usaCasco = {false, false, false, false, false, true, false, false, true, false, true, false, false, false, false, false, false, false, true, true, false, true, false};
        boolean[] usaBlaster = {false, true, true, true, false, false, false, false, true, false, true, false, true, true, true, false, false, false, true, true, true, false, false};

        ArrayList<Personaje> personajes = new ArrayList<>();

        for (int i = 0; i < nombres.length; i++) {
            personajes.add(new Personaje(
                    i + 1,
                    nombres[i],
                    esHumano[i],
                    esVillano[i],
                    esForceSensitive[i],
                    esMasculino[i],
                    tienePelo[i]
            ));
        }

        for (int i = 0; i < personajes.size(); i++) { //Eliminar
            System.out.println(personajes.get(i).toString());
        }

        return personajes;
    }


    void jugarJuego() {

        ArrayList<Personaje> personajes = iniciarListaPersonajes();
        ArrayList<String> preguntasDisponibles = new ArrayList<>();
        preguntasDisponibles.add("¿Mi personaje es humano?");
        preguntasDisponibles.add("¿Mi personaje es un villano?");
        preguntasDisponibles.add("¿Mi personaje es sensible a la Fuerza?");
        preguntasDisponibles.add("¿Mi personaje es hombre?");
        preguntasDisponibles.add("¿Mi personaje tiene pelo?");
        int modo = sistema.ingresarInt(1,2);

        Jugador jugador1;
        if (modo == 1) {
            jugador1 = new JugadorHumano(new ArrayList<>(personajes), sistema);
        }
        else{
            jugador1 = new JugadorMaquina(new ArrayList<>(personajes));
        }

        Jugador jugador2 = new JugadorMaquina(new ArrayList<>(personajes));

        boolean juegoTerminado = false;
        Jugador ganador = null;
        boolean turnoJugador1 = false;

        while (!juegoTerminado){
            turnoJugador1 = !turnoJugador1;
            Jugador jugadorActual;
            if (turnoJugador1){
                jugadorActual = jugador1;
            }
            else{
                jugadorActual = jugador2;
            }

            Pregunta pregunta = jugadorActual.elegirOpcion();
            if (pregunta != null){
                //Preguntini
            }
            else{
                Personaje personajeElegido = jugadorActual.adivinarPersonaje();
                if (personajeElegido == jugadorActual.getPersonajeSecreto()){
                    ganador = jugadorActual;
                    juegoTerminado = true;
                }
            }
        }
        System.out.println("El ganador es " + ganador + ". Que la Fuerza te acompañe.");
    }
}