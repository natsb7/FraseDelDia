package com.nataliasep.ahorcado;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    Random random = new Random();
    ArrayList<String> palabras;
    private String palabraSecreta;
    private String palabraGuiones;
    private String palabraEspacios;
    private int intentos = 6;
    private StringBuilder cadenaLetras = new StringBuilder();


    public String generarPalabra(Context context) {
        palabras = leerArchivo(context);
        int num = random.nextInt(palabras.size());
        String palabra = palabras.get(num);
        return palabra;
    }
    public String toGuiones(String palabra){
        StringBuilder sb = new StringBuilder();
        int nLetras = palabra.length();
        char []palabraGuiones = new char[nLetras];
        int i = 0;
        while (i< palabraGuiones.length){
            sb.append("_");
            i++;
        }
        return sb.toString();
    }
    public String toEspacios(String palabra){
        String newPalabra = palabra.replace("", " ").trim();
        return newPalabra;
    }

    public String comprobarLetra(String letra){
        char letrap  = letra.charAt(0);
        boolean letraEncontrada = false;
        for(int i = 0; i< palabraSecreta.length(); i++){
            if(palabraSecreta.charAt(i) == letrap){
                StringBuilder newPalabraGuiones = new StringBuilder(palabraGuiones);
                newPalabraGuiones.setCharAt(i, letrap);
                palabraGuiones = newPalabraGuiones.toString();
                letraEncontrada = true;
            }
        }
        if (letraEncontrada){
            return palabraGuiones;
        }else{
            return null;
        }
    }

    public int comprobarAcierto(String palabraGuiones){
        int indice = palabraGuiones.indexOf('_');
        return indice;
    }

    public void registrarFallo(String letra){
        intentos--;
        cadenaLetras.append(letra).append(", ");
    }


    public int getIntentos() {
        return intentos;
    }

    public StringBuilder getCadenaLetras() {
        return cadenaLetras;
    }


    public void leerArchivo(Context context) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                palabras = new ArrayList<>();
                try (InputStream inputStream = context.getResources().openRawResource(R.raw.words_es);
                     BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

                    String palabra;
                    while ((palabra = br.readLine()) != null) {
                        palabras.add(palabra);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
    }




}
