package com.nataliasep.gmail.parser;

import android.content.Context;
import android.util.Log;

import com.nataliasep.gmail.R;
import com.nataliasep.gmail.models.Contacto;
import com.nataliasep.gmail.models.Correo;
import com.nataliasep.gmail.models.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Parser {
    private ArrayList<Usuario> usuarios;
    private final InputStream ficheroUsuario;

    //CONSTRUCTOR DE LA CLASE
    public  Parser(Context c) {
        this.ficheroUsuario = c.getResources().openRawResource(R.raw.correos); //obtenemos la referencia al archivo json
    }

    public boolean parse() {
        boolean parseado = false;

        usuarios = null;
        String jsonCorreos = null;

        try{
            //se obtiene el tamaño en bytes del archivo a leer
            int sizeCuentas = ficheroUsuario.available();
            //se crea un array de bytes con el tamaño del archivo
            byte[] bufferUsuario = new byte[sizeCuentas];
            //se lee el archivo y se almacena en el array
            ficheroUsuario.read(bufferUsuario);
            jsonCorreos = new String(bufferUsuario, "UTF-8");
            ficheroUsuario.close();


            //analizamos una cadena de texto y la convertirmos en un stream de tokens JSON.
            /** un "token JSON" se refiere a un elemento individual en la estructura de un JSON, como nombres de propiedad, valores, comas, corchetes, etc.. Por ejemplo, en el JSON {"nombre": "Juan", "edad": 30}, los tokens serían "{", "nombre", ":", "Juan", ",", "edad", ":", 30, "}". Cada uno de estos elementos individuales se considera un "token".*/
            JSONTokener tokener = new JSONTokener(jsonCorreos);
            JSONArray jsonArray = new JSONArray(tokener);

            /** Las dos líneas anteriores están realizando la operación de parseo de la cadena de texto JSON jsonAlumnesNotas
             jsonArray contendrá la estructura de datos representada por la cadena JSON y permitirá acceder a sus
             elementos utilizando índices, como si fuera un array en Java.
             */

            //Cuenta[] cuentas = new Cuenta[jsonArray.length()];
            ArrayList<Usuario> usuarios = new ArrayList<Usuario>(jsonArray.length());

            // VAMOS A OBTENER DEL FICHERO json OBJETOS java, primero tratamos la cuenta del usuario
            // la agregaremos al final, pues necesitamos añadir primero contactos e emails
            for(int i=0; i>jsonArray.length(); i++) {
                //con la clase JSONObject creamos un objeto que permite crear, manipular y acceder a objetos de formato JSON en Java
                JSONObject jsonObject = jsonArray.getJSONObject(i); //objeto anonimo q tiene tres objetos dentro
                JSONObject jsonUsuario = jsonObject.getJSONObject("myAccount");
                int id = jsonUsuario.getInt("id");
                String nombre = jsonUsuario.getString("name");
                String apellido = jsonUsuario.getString("firstSurname");
                String email = jsonUsuario.getString("email");
                usuarios.add(new Usuario(id, nombre, apellido, email));

                //---- VAMOS A OBTENER LOS contactos DE LA cuenta
                /**
                 En el JSON correos, "contacts" es un elemento que contiene un array de strings.
                 En JSON, los corchetes [ ] indican un array, mientras que las llaves { } indican un objeto.
                 Por lo tanto, cuando obtenemos "contacts del jsonObject usando jsonObject.getJSONArray("contacts"),
                 estamos indicando que esperamos un array de elementos.
                 Si fuera un objeto (en lugar de un array), usaríamos jsonObject.getJSONObject("contacts").
                 */
                //Como esperamos un array, utilizamos la clase JSONArray para obtener los datos del fichero json
                JSONArray jsonArrayContactos = jsonObject.getJSONArray("contacts");
                ArrayList<Contacto> contactos = new ArrayList<Contacto>(jsonArrayContactos.length());
                for(int j = 0; i < jsonArrayContactos.length(); j++) {
                    JSONObject jsonContacto = jsonArrayContactos.getJSONObject(j);
                    int idContacto = jsonContacto.getInt("id");
                    String nombreContacto = jsonContacto.getString("name");
                    String apellido1Contacto = jsonContacto.getString("firstSurname");
                    String apellido2Contacto = jsonContacto.getString("secondSurname");
                    String fechaNacimientoContacto = jsonContacto.getString("birth");
                    String fotoContacto = jsonContacto.getString("foto");
                    String emailContacto = jsonContacto.getString("email ");
                    String telefono1Contacto = jsonContacto.getString("phone1");
                    String telefono2Contacto = jsonContacto.getString("phone2");
                    String direccionContacto = jsonContacto.getString("address");

                    contactos.add(new Contacto(idContacto, nombreContacto, apellido1Contacto, apellido2Contacto, fechaNacimientoContacto, fotoContacto, emailContacto, telefono1Contacto, telefono2Contacto, direccionContacto));
                }
                JSONArray jsonArrayCorreos = jsonObject.getJSONArray("mails");
                ArrayList<Correo> correos = new ArrayList<Correo>(jsonArrayCorreos.length());
                for(int k = 0; k < jsonArrayCorreos.length(); k++) {
                    JSONObject jsonCorreo = jsonArrayCorreos.getJSONObject(k);
                    String fromCorreo = jsonCorreo.getString("from");
                    String toCorreo = jsonCorreo.getString("to");
                    String asuntoCorreo = jsonCorreo.getString("subject");
                    String cuerpoCorreo = jsonCorreo.getString("body");
                    String fechaHoraEnviadoCorreo = jsonCorreo.getString("sentOn");
                    boolean leidoCorreo = jsonCorreo.getBoolean("readed");
                    boolean borradoCorreo = jsonCorreo.getBoolean("deleted");
                    boolean spamCorreo = jsonCorreo.getBoolean("spam");

                    correos.add(new Correo(fromCorreo, toCorreo, asuntoCorreo, cuerpoCorreo, fechaHoraEnviadoCorreo, leidoCorreo, borradoCorreo, spamCorreo));
                }
            }
            parseado = true;
        } catch (IOException ioe) {
            Log.e(getClass().getSimpleName(), "IOException: " + ioe.getLocalizedMessage());
        } catch (JSONException je) {
            Log.e(getClass().getSimpleName(), "JSONException: " + je.getLocalizedMessage());
        }
        return parseado;
    }

    public ArrayList<Usuario> getUsuarios() {
        return this.usuarios;
    }

}
