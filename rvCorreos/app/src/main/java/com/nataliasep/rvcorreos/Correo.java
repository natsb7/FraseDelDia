package com.nataliasep.rvcorreos;

public class Correo {

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
