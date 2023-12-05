package com.nataliasep.pruebafragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.PersistableBundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.nataliasep.pruebafragments.R;
import com.nataliasep.pruebafragments.FragmentDetalle;
import com.nataliasep.pruebafragments.Fragments;
import com.nataliasep.pruebafragments.IOnClickListener;
import com.nataliasep.pruebafragments.Correo;

public class MainActivity extends AppCompatActivity
            implements Fragments.IOnAttachListener, FragmentDetalle.IOnAttach,
                IOnClickListener{
    private List<Correo> correos;
    int cantidadDeCorreos = 20;
    private FragmentManager manager;
    private FragmentDetalle fragmentDetalle;
    private int correoSelecionado;
    private boolean hayDetalle;
    private static final String CORREOS_KEY = "Correos";
    private static final String SELECTED_CORREO_KEY = "CorreoSelectionado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            correos = (ArrayList<Correo>) savedInstanceState.getSerializable(CORREOS_KEY);
            correoSelecionado = savedInstanceState.getInt(SELECTED_CORREO_KEY);
        }else{
            cargarDatos();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        manager = getSupportFragmentManager();

        hayDetalle = findViewById(R.id.fcvDetalle) != null;

        if (hayDetalle){
            fragmentDetalle = (FragmentDetalle) manager.findFragmentById(R.id.fcvDetalle);
            if(manager.findFragmentById(R.id.fcvListado) instanceof FragmentDetalle){
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fcvListado, Fragments.class, null)
                        .commit();
            }
        }
    }

    public void cargarDatos() {
        correoSelecionado = -1;
        correos = new ArrayList<Correo>(cantidadDeCorreos);
        correos.add(new Correo("Asunto 1", "Cuerpo 1"));
        correos.add(new Correo("Asunto 2", "Cuerpo 2"));
        correos.add(new Correo("Asunto 3", "Cuerpo 3"));
        correos.add(new Correo("Asunto 4", "Cuerpo 4"));
        correos.add(new Correo("Asunto 5", "Cuerpo 5"));
        correos.add(new Correo("Asunto 6", "Cuerpo 6"));
        correos.add(new Correo("Asunto 7", "Cuerpo 7"));
        correos.add(new Correo("Asunto 8", "Cuerpo 8"));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SELECTED_CORREO_KEY, correoSelecionado);
        outState.putSerializable(CORREOS_KEY, (ArrayList<Correo>)correos);
        super.onSaveInstanceState(outState);
    }

    public List<Correo> getCorreos(){
        return correos;
    }

    @Override
    public int getCorreoSeleccionado() {
        return 0;
    }

    @Override
    public Correo getCorreo() {
        if(correoSelecionado <0){
            correoSelecionado = 0;
        }

        return correos.get(correoSelecionado);
    }

    @Override
    public void onClick(int position) {
       correoSelecionado = position;
       if(hayDetalle){
           fragmentDetalle.mostrarDetalle(correos.get(correoSelecionado));
       }else{
           manager.beginTransaction()
                   .setReorderingAllowed(true)
                   .addToBackStack(null)
                   .replace(R.id.fcvListado, FragmentDetalle.class, null)
                   .commit();
       }
    }

    public void onBackPressed() {
        super.onBackPressed();
        setTitle(R.string.app_name);
    }
}