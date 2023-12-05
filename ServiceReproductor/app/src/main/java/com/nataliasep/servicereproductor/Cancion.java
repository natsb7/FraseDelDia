package com.nataliasep.servicereproductor;

import android.net.Uri;

public class Cancion {
    Uri uri;
    String titulo;
    String artista;
    String album;
    String año;
    String duracion;

    public Cancion(Uri uri, String titulo, String artista, String año, String album, String duracion) {
        this.titulo = titulo;
        this.uri = uri;
        this.artista = artista;
        this.año = año;
        this.album = album;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public Uri getUri(){
        return uri;
    }
    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public String getDuracion() {
        return duracion;
    }
}
