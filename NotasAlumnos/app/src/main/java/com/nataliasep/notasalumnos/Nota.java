package com.nataliasep.notasalumnos;

import java.io.Serializable;

public class Nota implements Serializable {

    private final int nia;
    private final Asignatura asignatura;
    private final double nota;

    public Nota(int nia, Asignatura asignatura, double nota) {
        this.nia = nia;
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public int getNia() {
        return nia;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public double getNota() {
        return nota;
    }
}
