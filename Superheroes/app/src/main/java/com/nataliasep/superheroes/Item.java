package com.nataliasep.superheroes;

import android.widget.ImageView;

public class Item {

    private String nombre;
    private String poderes;
    private String saga;
    private int foto;

    public Item(String nombre, String poderes, String saga, int foto) {
        this.nombre = nombre;
        this.poderes = poderes;
        this.saga = saga;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoderes() {
        return poderes;
    }

    public void setPoderes(String poderes) {
        this.poderes = poderes;
    }

    public String getSaga() {
        return saga;
    }

    public void setSaga(String saga) {
        this.saga = saga;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
