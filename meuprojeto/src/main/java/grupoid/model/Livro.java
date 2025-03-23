package grupoid.model;

public class Livro {
    //Definição dos atributos
    private int id;
    private String titulo;
    private int autorId;
    private String isbn;
    private String genero;

    //Construtores
    public Livro(int id, String titulo, int autorId, String isbn, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.autorId = autorId;
        this.isbn = isbn;
        this.genero = genero;
    }

    //Getters e Setters
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

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
}
