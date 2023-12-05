package com.nataliasep.gmail.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nataliasep.gmail.R;
import com.nataliasep.gmail.adapter.Adapter;
import com.nataliasep.gmail.models.Correo;
import com.nataliasep.gmail.models.Usuario;

import java.util.ArrayList;

public class FragmentListado extends Fragment {

    public enum ListType{
        CORREOS_RECIBIDOS, CORREOS_ENVIADOS, CORREOS_NOLEIDOS, ENVIAR_CORREOS, CORREOS_PAPELERA, CORREOS_SPAM
    }
    private ListType tipoListado; //esta es la variable q guarda el valor enum

    public interface IOnAttachListener {
        ListType tipoListado();

        Usuario getUsuario();
        ArrayList<Correo> getListaCorreos();
    }
    private Usuario usuario;

    private ArrayList<Correo> listaCorreos;

    private Adapter adapter;
    private RecyclerView recyclerView;

    public FragmentListado(){
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvListado);
        actualizarListado(tipoListado, usuario, listaCorreos);
    }

    public void actualizarListado(ListType tipoListado, Usuario usuario, ArrayList<Correo> listaCorreos){
        this.tipoListado = tipoListado;
        this.listaCorreos = listaCorreos;
        this.usuario = usuario;
        switch (tipoListado){
            case CORREOS_RECIBIDOS:
                adapter = new Adapter(usuario, usuario.getRecibidos());
            case CORREOS_ENVIADOS:
                adapter = new Adapter(usuario, usuario.getEnviados());
            case CORREOS_NOLEIDOS:
                adapter = new Adapter(usuario, usuario.getNoLeidos());
            case ENVIAR_CORREOS:
                //
            case CORREOS_PAPELERA:
                adapter = new Adapter(usuario, usuario.getBorrados());
            case CORREOS_SPAM:
                adapter = new Adapter(usuario, usuario.getSpam());
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        usuario = attachListener.getUsuario();
        tipoListado = attachListener.tipoListado();
        listaCorreos = attachListener.getListaCorreos();
    }
}
