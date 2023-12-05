package com.nataliasep.toolbarcontainer;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AlumnoParser {
        //declaramos el array que albergará la lista de alumnos y los dos files que reciben los JSON de raw
        private Alumno[] listaAlumnos;
        private final InputStream alumnosFile;

        public AlumnoParser(Context contexto) {
            this.alumnosFile = contexto.getResources().openRawResource(R.raw.alumnos_notas);
        }

        public boolean parse(){
            boolean parseado = false;
            String alumnosJSON = null;
            listaAlumnos = null;
            try{
                //se obtiene el tamaño en bytes del archivo a leer
                int alumnosSize = alumnosFile.available();
                //se crea una array de bytes con el tamaño del archivo
                byte[] alumnosBuffer = new byte[alumnosSize];
                //se leer el archivo y se almacena en la array
                alumnosFile.read(alumnosBuffer);
                alumnosJSON = new String(alumnosBuffer, StandardCharsets.UTF_8);
                alumnosFile.close();
                //se convierte el String alumnosJSON en el contenido de la array
                JSONTokener tokener = new JSONTokener(alumnosJSON);
                JSONArray jsonArray = new JSONArray(tokener);
                listaAlumnos = new Alumno[jsonArray.length()];
                //obtenemos los datos del alumno
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int nia = jsonObject.getInt("nia");
                    String alumNom = jsonObject.getString("nombre");
                    String apellido1 = jsonObject.getString("apellido1");
                    String apellido2 = jsonObject.getString("apellido2");
                    String fchNacimiento = jsonObject.getString("fechaNacimiento");
                    String email = jsonObject.getString("email");
                    listaAlumnos[i] = new Alumno(nia, alumNom, apellido1, apellido2, fchNacimiento, email);
                }
                parseado = true;
            }catch (IOException e){
                Log.e(getClass().getSimpleName(),"IOException" + e.getLocalizedMessage());
            }catch (JSONException ex){
                Log.e(getClass().getSimpleName(), "JSONException" + ex.getLocalizedMessage());
            }
            return parseado;
        }

        public Alumno[] getListaAlumnos() {
            return this.listaAlumnos;
        }
    }
