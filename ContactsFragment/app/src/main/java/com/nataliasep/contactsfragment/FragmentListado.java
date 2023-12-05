package com.nataliasep.contactsfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentListado extends Fragment {
    public interface IOnAttachListener{
        Contacto[] getContactos();
        int getContactoSeleccionado();
    }
    private Contacto[] contactos;
    private int contactoSeleccionado;
    private IContactoListener clickListener;


    public FragmentListado(){
        super(R.layout.fragment_listado);
    }

    public void onViewCreated(View view, Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rvFragments);
        ContactoAdapter contactoAdapter = new ContactoAdapter(contactos, clickListener);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(contactoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.scrollToPosition(contactoSeleccionado);
   }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        contactos = attachListener.getContactos();
        contactoSeleccionado = attachListener.getContactoSeleccionado();
        clickListener = (IContactoListener) context;



    }
}
