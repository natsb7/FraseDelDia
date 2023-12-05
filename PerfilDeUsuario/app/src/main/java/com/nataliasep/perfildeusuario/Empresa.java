package com.nataliasep.perfildeusuario;

import java.io.Serializable;


public class Empresa implements Serializable {
    private final String nombreEmp;
    private final String cif;
    private final String dirEmp;
    private final String web;
    private final String email;

    public Empresa(String nombreEmp, String cif, String dirEmp, String web, String email) {
        this.nombreEmp = nombreEmp;
        this.cif = cif;
        this.dirEmp = dirEmp;
        this.web = web;
        this.email = email;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public String getCif() {
        return cif;
    }

    public String getDirEmp() {
        return dirEmp;
    }

    public String getWeb() {
        return web;
    }

    public String getEmail() {
        return email;
    }
}
