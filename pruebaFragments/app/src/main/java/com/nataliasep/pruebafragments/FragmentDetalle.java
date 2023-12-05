package com.nataliasep.pruebafragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetalle extends Fragment{
    public interface IOnAttach{
        Correo getCorreo();
    }
    private TextView tvAsunto;
    private TextView tvCuerpo;
    private Correo correo;
    public FragmentDetalle(){
        super(R.layout.fragment_detalle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvAsunto = view.findViewById(R.id.tvAsunto);
        tvCuerpo = view.findViewById(R.id.tvCuerpo);
        mostrarDetalle(correo);
    }

    public void mostrarDetalle(Correo correo){
        tvAsunto.setText(correo.getAsunto());
        tvCuerpo.setText(correo.getCuerpo());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttach iOnAttach = (IOnAttach) context;
        correo = iOnAttach.getCorreo();
    }
}
