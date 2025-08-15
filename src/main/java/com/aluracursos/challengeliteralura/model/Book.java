package com.aluracursos.challengeliteralura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private List<Author> authors;
    @Enumerated(EnumType.STRING)
    private Idiomas languages;
    private Double download;

    public Book() {
    }

    public Book(DatosBook datosBook) {
        this.title = datosBook.title();
        this.authors = datosBook.authorList().stream()
                .map(Author::new)
                .toList();
        this.languages = datosBook.languages().isEmpty() ? null :
                Idiomas.fromCodigo(datosBook.languages().get(0));
        this.download = datosBook.download();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Idiomas getLanguages() {
        return languages;
    }

    public void setLanguages(Idiomas languages) {
        this.languages = languages;
    }

    public Double getDownload() {
        return download;
    }

    public void setDownload(Double download) {
        this.download = download;
    }

    @Override
    public String toString() {
        return "--- Detalles Libro ---" + '\n'
                + "- Title: " + title + '\n'
                + "- Authors: " + authors + '\n'
                + "- Languages: " + languages + '\n'
                + "- Download: " + download + '\n';
    }
}
