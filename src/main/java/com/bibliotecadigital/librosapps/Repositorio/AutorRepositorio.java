
package com.bibliotecadigital.librosapps.Repositorio;

import com.bibliotecadigital.librosapps.Modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AutorRepositorio  extends JpaRepository <Autor, Long>{
    
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre ")
    Autor buscarAutor(@Param("nombre") String nombre, @Param("fechaDeNacimiento") String fechaDeNacimiento, @Param("fechaDeFallecimiento") String fechaDeFallecimiento);

}
