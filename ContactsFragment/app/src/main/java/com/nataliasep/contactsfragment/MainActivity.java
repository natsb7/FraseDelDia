package com.nataliasep.contactsfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;

public class MainActivity extends AppCompatActivity implements
FragmentListado.IOnAttachListener, FragmentDetalle.IOnAttachListener,
IContactoListener{
    private final static String CONTACTOS = "Contactos";
    private final static String CONTACTO_SELEC = "selecContacto";
    private FragmentDetalle fragmentDetalle;
    private FragmentManager manager;
    private boolean hayDetalle;
    private Contacto[] contactos;
    private int selecContacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(savedInstanceState != null){
            contactos = (Contacto[]) savedInstanceState.getSerializable(CONTACTOS);
            selecContacto = savedInstanceState.getInt(CONTACTO_SELEC);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        hayDetalle = findViewById(R.id.fcvDetalle) != null;

        if(hayDetalle){
            fragmentDetalle = (FragmentDetalle) manager.findFragmentById(R.id.fcvDetalle);

            if(manager.findFragmentById(R.id.fcvListado) instanceof FragmentDetalle){
                manager.popBackStack();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        //.addToBackStack(null)
                        .replace(R.id.fcvListado, FragmentListado.class, null)
                        .commit();


            }

        }
    }
    public void cargarDatos(){
        selecContacto = 0;
        ContactoParser parser = new ContactoParser(this);
        if(parser.parse()){
            this.contactos = parser.getContactos();
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CONTACTO_SELEC, selecContacto);
        outState.putSerializable(CONTACTOS, contactos);
        super.onSaveInstanceState(outState);
    }

    public Contacto[] getContactos() {
        if(contactos == null){
            cargarDatos();
        }
        return contactos;
    }
    public Contacto getContacto(){
        if(selecContacto == -1 ){
            selecContacto = 0;
        }
        return  contactos[selecContacto];
    }

    @Override
    public int getContactoSeleccionado() {
        return selecContacto;
    }

    @Override
    public void onClick(int position){
        selecContacto = position;
        Contacto seleccionado = contactos[selecContacto];
        if(hayDetalle){
            fragmentDetalle.muestraDetalle(seleccionado);
        }else{
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.fcvListado, FragmentDetalle.class, null)
                    .commit();
        }
    }



}