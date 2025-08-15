package com.aluracursos.challengeliteralura.model;

public enum Idiomas {
    ENGLISH ("en", "English"),
    ESPAÑOL ("es", "Español");

    private String codigo;
    private String texto;

    Idiomas(String codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public String getCodigo() {
        return codigo;
    }

    public static Idiomas fromCodigo(String text) {
        for (Idiomas Idioma : Idiomas.values()) {
            if (Idioma.codigo.equalsIgnoreCase(text)) {
                return Idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna Idioma encontrada: " + text);
    }
    public static Idiomas fromTexto(String text) {
        for (Idiomas Idioma : Idiomas.values()) {
            if (Idioma.texto.equalsIgnoreCase(text)) {
                return Idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna Idioma encontrada: " + text);
    }
}
