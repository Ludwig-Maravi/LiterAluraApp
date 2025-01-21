
package com.bibliotecadigital.librosapps.Repositorio;

import com.bibliotecadigital.librosapps.Modelo.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LibroRepositorio extends JpaRepository <Libro, Long> {
    Optional<Libro> findById(Long id);
    
    
    @Query("SELECT a FROM Libro l JOIN l.autores a")
    List<Autor> buscarAutores();

    @Query("SELECT a FROM Libro l JOIN l.autores a WHERE a.fechaDeFallecimiento >= :anio")
    List<Autor> buscarAutoresvivos(int anio);

    @Query("SELECT DISTINCT l FROM Libro l JOIN l.idiomas i WHERE :idioma IN (i)")
    List<Libro> encontrarLibroporIdioma(String idioma);
    
}
