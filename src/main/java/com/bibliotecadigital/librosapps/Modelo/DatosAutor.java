
package com.bibliotecadigital.librosapps.Modelo;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        /*@JsonAlias("id") 
        Long id,*/
        @JsonAlias("name") 
        String nombre,
        @JsonAlias("birth_year") 
        String fechaDeNacimiento,
        @JsonAlias("death_year") 
        String fechaDeFallecimiento
        ) {
    
}
