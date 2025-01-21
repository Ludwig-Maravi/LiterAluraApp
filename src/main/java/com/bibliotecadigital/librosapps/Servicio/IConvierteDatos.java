
package com.bibliotecadigital.librosapps.Servicio;


public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
