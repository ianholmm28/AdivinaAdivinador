package AdivinaAdivinador.Preguntas;

public class Pregunta {

    private String texto;
    private TipoPregunta tipo;
    public enum TipoPregunta{}

    public Pregunta(String texto, TipoPregunta tipo){
        this.texto = texto;
        this.tipo = tipo;
    }

    public String getTexto(){return texto;}
    public TipoPregunta getTipo(){return tipo;}

    @Override public String toString() {return texto;}
}