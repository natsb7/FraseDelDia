package com.nataliasep.contactsfragment;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ContactoParser {

    private Contacto[] contactos;
    private final InputStream contactosFile;

    public ContactoParser(Context contexto) {
        this.contactosFile = contexto.getResources().openRawResource(R.raw.contacts);
    }

    //este método lo llamamos desde el main y nos dirá si se ha podido parsear todo o no
    public boolean parse(){
        //inicializamos todas las variables que necesitaremos
        boolean parseado = false;
        String json = null;
        contactos = null;

        //parseamos haciendo try catch
        try{
            int size = contactosFile.available();
            byte[] buffer = new byte[size];
            contactosFile.read(buffer);
            json = new String(buffer, "UTF-8");
            JSONTokener tokener = new JSONTokener(json);
            JSONArray jsonArray = new JSONArray(tokener);
            contactos = new Contacto[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String firstSurname = jsonObject.getString("firstSurname");
                String secondSurname = jsonObject.getString("secondSurname");
                String photo = jsonObject.getString("photo");
                String birth = jsonObject.getString("birth");
                String company = jsonObject.getString("company");
                String email = jsonObject.getString("email");
                String phone1 = jsonObject.getString("phone1");
                String phone2 = jsonObject.getString("phone2");
                String address = jsonObject.getString("address");
                contactos[i] = new Contacto(id, name, firstSurname, secondSurname, photo, birth, company, email, phone1, phone2, address);
                parseado = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            if(contactosFile != null){
                try{
                    contactosFile.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return parseado;
    }

    public Contacto[] getContactos() {
        return this.contactos;
    }
}
