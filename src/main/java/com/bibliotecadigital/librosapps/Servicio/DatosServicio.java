package com.bibliotecadigital.librosapps.Servicio;

import com.bibliotecadigital.librosapps.Modelo.*;
import com.bibliotecadigital.librosapps.Repositorio.*;
import jakarta.transaction.Transactional;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired
    private LibroRepositorio libroRepositorio;

    public void guardarLibro(DatosLibro d) {
        try {
            Optional<Libro> libroBuscado = libroRepositorio.findById(d.id());

            if (libroBuscado.isEmpty()) {
                List<Autor> autores = guardarAutores( d.autor());
                Libro nuevoLibro = new Libro(d.id(), d.titulo(), d.idiomas(), d.numeroDeDescargas());

                for (Autor autor : autores) {
                    nuevoLibro.addAuthor(autor);
                }

                libroRepositorio.save(nuevoLibro);
                System.out.println("Libro guardado: " + nuevoLibro.toString());
            } else {
                System.out.println("No se puede registrar el mismo libro más de una vez");
            }
        } catch (Exception e) {
            System.err.println("Error al guardar el libro: " + e.getMessage());
        }
    }

    public List<Autor> guardarAutores(List<DatosAutor> datosAutor) {
        List<Autor> autores = new ArrayList<>();
        for (DatosAutor a : datosAutor) {
            // Llama a buscarAutor con los parámetros adecuados
            Autor autor = autorRepositorio.buscarAutor(a.nombre(), a.fechaDeNacimiento(), a.fechaDeFallecimiento());

            if (autor == null) {
                // Crear un nuevo autor y guardarlo en la base de datos
                autor = new Autor(a.nombre(), a.fechaDeNacimiento(), a.fechaDeFallecimiento());
                autorRepositorio.save(autor); // Guardar el nuevo autor
            }

            autores.add(autor);
        }
        return autores;
    }

    public void listarLibros() {
        List<Libro> libros = libroRepositorio.findAll();
        libros.stream().forEach(System.out::println);
    }

    public void listarAutores() {
        List<Autor> authors = libroRepositorio.buscarAutores();
        authors.forEach(a -> System.out.println(a.toString()));
    }

    public void listarAutoresVivos(int anio) {
        List<Autor> autores = libroRepositorio.buscarAutoresvivos(anio);
        autores.stream().forEach(System.out::println);
    }

    public void listarPorIdioma(String idioma) {
        List<Libro> libros = libroRepositorio.encontrarLibroporIdioma(idioma);
        libros.stream().forEach(System.out::println);
    }
}
