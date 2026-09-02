package AdivinaAdivinador.Preguntas;

public class Pregunta {

    private String texto;
    private TiposDePregunta tipo;

    public Pregunta(String texto, TiposDePregunta tipo){
        this.texto = texto;
        this.tipo = tipo;
    }

    public String getTexto(){return texto;}
    public TiposDePregunta getTipo(){return tipo;}
}