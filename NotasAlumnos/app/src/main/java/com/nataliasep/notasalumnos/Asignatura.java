package com.nataliasep.notasalumnos;

import java.io.Serializable;

public class Asignatura implements Serializable {

    private final String id;
    private final String nombre;

    public Asignatura(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
