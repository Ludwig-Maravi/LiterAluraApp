
package com.bibliotecadigital.librosapps.Modelo;


public enum Idiomas {
    Ingles("en"),
    Castellano("es"),
    Chino("zh"),
    Aleman("de"),
    Frances("fr"),
    Italiano("it"),
    Portuges("pt");

    private String idioma;

    Idiomas (String idioma){
        this.idioma = idioma;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas1 : Idiomas.values()) {
            if (idiomas1.idioma.equalsIgnoreCase(text)) {
                return idiomas1;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
}
