package com.nataliasep.pruebafragments;

import java.io.Serializable;

public class Correo implements Serializable {

    private String asunto;
    private String cuerpo;

    public Correo(String asunto, String cuerpo) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }
}
