package com.nataliasep.gmail.models;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private final int id;
    private final String nombre;
    private final String primerApellido;
    private final String email;
    private final ArrayList<Contacto> contactos;
    private final ArrayList<Correo> correos;

    public Usuario(int id, String nombre, String primerApellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.email = email;
        this.contactos = new ArrayList<>();
        this.correos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public ArrayList<Correo> getCorreos(){
        return correos;
    }
    /*public Correo getLeidos(){
        Correo correo = null;
        for(int i = 0; i< correos.length; i++){
            if(correos.get(i).isLeido()){
                correo = correos[i];
            }
        }
        return correo;
    }*/
    public ArrayList<Correo> getBorrados(){
        ArrayList<Correo> correosBorrados = new ArrayList<>();
        for(int i = 0; i< correos.size(); i++){
            if(correos.get(i).isBorrado()){
                Correo aux = correos.get(i);
                correosBorrados.add(aux);
            }
        }
        return correosBorrados;
    }
    public ArrayList<Correo> getSpam(){
        ArrayList<Correo> correosSpam = new ArrayList<>();
        for(int i = 0; i< correos.size(); i++){
            if(correos.get(i).isSpam()){
                Correo aux = correos.get(i);
                correosSpam.add(aux);
            }
        }
        return correosSpam;
    }

    public ArrayList<Correo> getRecibidos(){
        ArrayList<Correo> correosRecibidos = new ArrayList<>();
        for(int i = 0; i<correos.size(); i++){
            if(!(correos.get(i).getFrom().equals(getEmail()))){
                Correo aux = correos.get(i);
                correosRecibidos.add(aux);
            }
        }
        return correosRecibidos;

    }
    public ArrayList<Correo> getEnviados(){
        ArrayList<Correo> correosEnviados = new ArrayList<>();
        for (int i = 0; i<correos.size(); i++){
            if(correos.get(i).getFrom().equals(getEmail())){
                Correo aux = correos.get(i);
                correosEnviados.add(aux);
            }
        }
        return correosEnviados;
    }
    public ArrayList<Correo> getNoLeidos(){
        ArrayList<Correo> correosNoLeidos = new ArrayList<>();
        for (int i = 0; i <correos.size(); i++){
            if(!correos.get(i).isLeido()){
                Correo aux = correos.get(i);
                correosNoLeidos.add(aux);
            }
        }
        return correosNoLeidos;
    }
}
