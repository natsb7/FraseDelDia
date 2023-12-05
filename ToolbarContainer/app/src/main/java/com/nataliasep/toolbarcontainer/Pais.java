package com.nataliasep.toolbarcontainer;

public class Pais {
    private final String codigo;
    private final String nombre;
    private final long poblacion;
    private final String capital;

    public Pais(String codigo, String nombre, long poblacion, String capital) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.capital = capital;
    }

    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public long getPoblacion() {
        return poblacion;
    }
    public String getCapital() {
        return capital;
    }
}
