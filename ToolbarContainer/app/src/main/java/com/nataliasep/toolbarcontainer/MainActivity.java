package com.nataliasep.toolbarcontainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements FragmentPais.IOnAttachListener, FragmentAlumno.IOnAttachListener{
    private Alumno[] alumnos;
    private Pais[] paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        if (itemId == R.id.action_paises){
            fm.beginTransaction().replace(R.id.fcvFragmentContainer, FragmentPais.class, null).commit();
            Toast.makeText(this, "Países", Toast.LENGTH_SHORT).show();
            return true;
        }else if(itemId == R.id.action_alumnos){
            fm.beginTransaction().replace(R.id.fcvFragmentContainer, FragmentAlumno.class, null).commit();
            Toast.makeText(this, "Alumnos", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void cargarAlumnos(){
        AlumnoParser parser = new AlumnoParser(this);
        if(parser.parse()){
            this.alumnos = parser.getListaAlumnos();


        }
    }
    public Alumno[] getAlumnos() {
        if(alumnos == null){
            cargarAlumnos();
        }
        return alumnos;
    }

    public void cargarPaises(){
        PaisParser parser = new PaisParser(this);
        if(parser.parse()){
            this.paises = parser.getPaises();
        }

    }
    public Pais[] getPaises(){
        if(paises == null){
            cargarPaises();
        }
        return  paises;
    }
}