package mack.lp2.agendamento.model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;

    public Livro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Livro(String titulo, String autor) {
        this(0, titulo, autor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
