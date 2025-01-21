package com.bibliotecadigital.librosapps;

import com.bibliotecadigital.librosapps.Principal.Principal;
import com.bibliotecadigital.librosapps.Servicio.DatosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrosappsApplication implements CommandLineRunner{
    
    @Autowired
    private DatosServicio servicio;

	public static void main(String[] args) {
		SpringApplication.run(LibrosappsApplication.class, args);
	}
      
    @Override
    public void run(String... args) throws Exception {
        System.out.println("hola mundo");
        Principal principal = new Principal( servicio);
        principal.muestraElMenu();
        
    }    
}
