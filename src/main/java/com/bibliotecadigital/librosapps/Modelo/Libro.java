package com.bibliotecadigital.librosapps.Modelo;

import jakarta.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores = new ArrayList<Autor>();
    private List<String> idiomas;
    private Double numeroDeDescargas;

    public Libro(Long id, String titulo, List<String> idiomas, double numeroDeDescargas) {
        this.id = id;
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Libro() {
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void addAuthor(Autor autor) {
        this.autores.add(autor);
    }
    
    @Override
    public String toString() {
        String autores = this.getAutores().stream()
                .map(a -> a.getNombre())
                .collect(Collectors.joining(", "));

        return "Titulo: " + titulo + '\n'
                + "Autor(es): " + autores + '\n'
                + "Idioma: " + idiomas + '\n'
                + "Descargas: " + numeroDeDescargas;
    }
}
