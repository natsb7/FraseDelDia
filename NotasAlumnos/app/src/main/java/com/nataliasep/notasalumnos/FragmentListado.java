package com.nataliasep.notasalumnos;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentListado extends Fragment {

    public interface IOnAttachListener{
        Alumno[] getAlumnos();

        int getAlumnoSeleccionado();

    }
    private Alumno[] listaAlumnos;
    private int alumnoSeleccionado;
    private IClickListener clickListener;

    public FragmentListado(){
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdaptadorAlumno adaptadorAlumno = new AdaptadorAlumno(listaAlumnos, clickListener);
        RecyclerView recyclerView = view.findViewById(R.id.rvListado);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorAlumno);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.scrollToPosition(alumnoSeleccionado);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        listaAlumnos = attachListener.getAlumnos();
        alumnoSeleccionado = attachListener.getAlumnoSeleccionado();
        clickListener = (IClickListener) context;
    }
}
