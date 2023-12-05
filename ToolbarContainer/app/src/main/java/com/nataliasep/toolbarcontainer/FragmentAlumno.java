package com.nataliasep.toolbarcontainer;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentAlumno extends Fragment {
    public interface IOnAttachListener{
        Alumno[] getAlumnos();
    }
    private Alumno[] listaAlumnos;

    public FragmentAlumno(){
        super(R.layout.fragmen_alumno);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AlumnoAdapter alumnoAdapter = new AlumnoAdapter(listaAlumnos);
        RecyclerView recyclerView = view.findViewById(R.id.rvAlumnos);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(alumnoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        listaAlumnos = attachListener.getAlumnos();
    }
}
