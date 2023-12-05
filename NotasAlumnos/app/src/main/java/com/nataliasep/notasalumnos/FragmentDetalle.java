package com.nataliasep.notasalumnos;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentDetalle extends Fragment {

    public interface IOnAttachListener{

        Alumno getAlumno();
    }
    private Alumno alumno;
    private AdaptadorNotas adaptadorNotas;
    public FragmentDetalle(){
        super(R.layout.fragment_detalle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rvDetalle);
        adaptadorNotas = new AdaptadorNotas(alumno.getNotas());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorNotas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        if(alumno != null){
            muestraDetalle(alumno);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        alumno = attachListener.getAlumno();
    }

    public void muestraDetalle(Alumno alumno) {
        this.alumno = alumno;
        adaptadorNotas.setNotas(alumno.getNotas());

    }
}
