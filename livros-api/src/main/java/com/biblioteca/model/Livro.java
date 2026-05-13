package com.biblioteca.model;

/**
 * Entidade principal: representa um Livro no sistema.
 */
public class Livro {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private int anoPub;

    // ── Construtores ─────────────────────────────────────────────────────────

    public Livro() {}

    public Livro(Long id, String titulo, String autor, String isbn, int anoPub) {
        this.id     = id;
        this.titulo = titulo;
        this.autor  = autor;
        this.isbn   = isbn;
        this.anoPub = anoPub;
    }

    // ── Getters e Setters ─────────────────────────────────────────────────────

    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public String getTitulo()                { return titulo; }
    public void   setTitulo(String titulo)   { this.titulo = titulo; }

    public String getAutor()                 { return autor; }
    public void   setAutor(String autor)     { this.autor = autor; }

    public String getIsbn()                  { return isbn; }
    public void   setIsbn(String isbn)       { this.isbn = isbn; }

    public int  getAnoPub()                  { return anoPub; }
    public void setAnoPub(int anoPub)        { this.anoPub = anoPub; }

    // ── toString ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anoPub=" + anoPub +
                '}';
    }
}
