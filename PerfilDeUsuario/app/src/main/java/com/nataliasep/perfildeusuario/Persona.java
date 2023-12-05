package com.nataliasep.perfildeusuario;

import java.io.Serializable;

public class Persona implements Serializable {
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final String fch_nacimiento;
    private final String direccion;
    private final int id;
    private final String contrasenya;

    public Persona(String nombre, String apellido1, String apellido2, String fch_nacimiento, String direccion, int id, String contrasenya) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fch_nacimiento = fch_nacimiento;
        this.direccion = direccion;
        this.id = id;
        this.contrasenya = contrasenya;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getFch_nacimiento() {
        return fch_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getId() {
        return id;
    }

    public String getContrasenya() {
        return contrasenya;
    }
}
