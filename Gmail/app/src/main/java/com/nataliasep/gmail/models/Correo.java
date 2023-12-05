package com.nataliasep.gmail.models;

import java.io.Serializable;
import java.util.Calendar;

public class Correo implements Serializable {

    private final String from;
    private final String to;
    private final String asunto;
    private final String cuerpo;
    private final String fecha;
    private final boolean leido;
    private final boolean borrado;
    private final boolean spam;

    public Correo(String from, String to, String asunto, String cuerpo, String fecha, boolean leido, boolean borrado, boolean spam) {
        this.from = from;
        this.to = to;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
        this.leido = leido;
        this.borrado = borrado;
        this.spam = spam;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public String getFecha(){
        return fecha;
    }
    public String getSoloFecha() {
        String fecha = getFecha();
        String[] partes = fecha.split(" ");
        fecha = partes[0];
        return fecha;
    }
    public String getHora(){
        String hora;
        String fecha = getFecha();
        String[] partes = fecha.split(" ");
        hora = partes[1];
        return hora;
    }
    public boolean isLeido() {
        return leido;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public boolean isSpam() {
        return spam;
    }


}
