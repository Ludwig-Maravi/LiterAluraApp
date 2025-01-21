
package com.bibliotecadigital.librosapps.Modelo;

import com.fasterxml.jackson.annotation.*;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("id") 
        Long id,
        @JsonAlias("title") 
        String titulo,
        @JsonAlias("authors") 
        List<DatosAutor> autor,
        @JsonAlias("languages") 
        List<String> idiomas,
        @JsonAlias("download_count") 
        Double numeroDeDescargas
        ) {
        
}
