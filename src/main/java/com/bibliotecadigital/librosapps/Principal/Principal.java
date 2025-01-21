package com.bibliotecadigital.librosapps.Principal;

import com.bibliotecadigital.librosapps.Modelo.*;
import com.bibliotecadigital.librosapps.Servicio.*;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private DatosServicio datosServicio;
    private Libro libro = new Libro();

    public Principal(DatosServicio servicio) {
        this.datosServicio = servicio;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro  por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libro por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAnio();
                    break;
                case 5:
                    ListarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    public void buscarLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        String titulo = teclado.nextLine();
        try {
            String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+"));
            Datos datos = conversor.obtenerDatos(json, Datos.class);
            List<DatosLibro> resultados = datos.resultados();

            System.out.println("Este es el dato" + datos);

            if (resultados.isEmpty()) {
                System.out.println("Libro no encontrado");
            } else {
                datosServicio.guardarLibro(resultados.get(0));
            }
        } catch (Exception e) {
            System.err.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    private void listarLibrosRegistrados() {
        datosServicio.listarLibros();
    }

    private void listarAutoresRegistrados() {
        datosServicio.listarAutores();
    }

    private void listarAutoresVivosPorAnio() {
        System.out.println("Ingresa el año que desea consultar :");
        int anio = Integer.parseInt(teclado.nextLine());
        datosServicio.listarAutoresVivos(anio);
    }

    private void ListarLibrosPorIdioma() {
        System.out.println("Ingrese las dos letras del idioma del libro que desea buscar.");

        String idioma = teclado.nextLine().trim();

        if (validaIdioma(idioma)) {
            datosServicio.listarPorIdioma(idioma);
        } else {
            System.out.println("El idioma ingresado no es válido. Asegúrese de usar el formato ISO 639-1 (dos letras).");
        }
    }

    private boolean validaIdioma(String idioma) {
        return idioma != null && idioma.length() == 2 && idioma.matches("[a-zA-Z]{2}");
    }

}
