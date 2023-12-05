package com.nataliasep.notasalumnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

public class MainActivity extends AppCompatActivity implements IClickListener,
    FragmentListado.IOnAttachListener, FragmentDetalle.IOnAttachListener{
    private final static String ALUMNOS = "alumnos";
    private final static String ALU_SELEC = "alumnoSeleccionado";
    private FragmentDetalle fragmentDetalle;
    private FragmentManager manager;
    private boolean hayDetalle;
    private Alumno[] listaAlumnos;
    private int selecAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            listaAlumnos = (Alumno[]) savedInstanceState.getSerializable(ALUMNOS);
            selecAlumno = savedInstanceState.getInt(ALU_SELEC);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        hayDetalle = findViewById(R.id.fcvFragmentoDetalle) != null;

        if(listaAlumnos == null){
            cargarDatos();
        }

        if(hayDetalle){
            fragmentDetalle = (FragmentDetalle) manager.findFragmentById(R.id.fcvFragmentoDetalle);

            //si hay detalle, quiero que en listado (donde esta en el detalle) quiero que se cargue lo que es el LISTADO
            if(!(manager.findFragmentById(R.id.fcvFragmentoListado) instanceof FragmentListado)){
                manager.popBackStack();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fcvFragmentoListado, FragmentListado.class, null)
                        .commit();
            }
        }
    }

    public void cargarDatos(){
        selecAlumno = 0;
        AlumnoParser parser = new AlumnoParser(this);
        if(parser.parse()){
            this.listaAlumnos = parser.getListaAlumnos();
            System.out.println(listaAlumnos.toString());
        }
    }

    @Override
    //controla q cuando lo pongo en horizontal (Se destruirá los fragments) los cargue facilmente sin tener q llamar al parser de nuevo
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(ALUMNOS, listaAlumnos);
        outState.putInt(ALU_SELEC, selecAlumno);
        super.onSaveInstanceState(outState);
    }

    @Override
    public Alumno getAlumno() {
       if(selecAlumno == -1){
           selecAlumno = 0;
       }
       return listaAlumnos[selecAlumno];
    }

    @Override
    public Alumno[] getAlumnos() {
        if(listaAlumnos == null){
            cargarDatos();
        }
        return listaAlumnos;
    }

    @Override
    public int getAlumnoSeleccionado() {
        return selecAlumno;
    }

    @Override
    public void onClick(int position) {
        selecAlumno = position;
        Alumno seleccionado = listaAlumnos[selecAlumno];
        if(hayDetalle){
            fragmentDetalle.muestraDetalle(seleccionado);
        }else{
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.fcvFragmentoListado, FragmentDetalle.class, null)
                    .commit();
        }
    }
}