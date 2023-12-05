package com.nataliasep.pruebafragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragments extends Fragment {

    public interface IOnAttachListener {
        List<Correo> getCorreos();
        int getCorreoSeleccionado();
    }
    private List<Correo> correos;
    private int correoSelect;
    private IOnClickListener clickListener;
    public Fragments(){
        super(R.layout.fragment_listado);

    }

    public void onViewCreatedView(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Adapter adapter = new Adapter(correos,clickListener);
        RecyclerView rvListado = view.findViewById(R.id.rvFragments);
        rvListado.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvListado.setHasFixedSize(true);
        rvListado.setAdapter(adapter);
        rvListado.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvListado.scrollToPosition(correoSelect);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener iOnAttachListener = (IOnAttachListener) context;
        correos = iOnAttachListener.getCorreos();
        correoSelect = iOnAttachListener.getCorreoSeleccionado();
        clickListener = (IOnClickListener) context;



    }
}
