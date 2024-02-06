package com.nataliasep.perfildeusuario.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nataliasep.perfildeusuario.R;
import com.nataliasep.perfildeusuario.modelo.Persona;

import org.w3c.dom.Text;

public class FragmentPersonal extends Fragment {

    public interface IOnAttachListener {
        Persona getPersona();
    }

    private Persona persona;
    private TextView tvNombre;
    private TextView tvApellidos;
    private TextView tvCumple;
    private TextView tvDireccion;
    private TextView tvID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_personal, container, false);
        tvNombre = layout.findViewById(R.id.tvNombre);
        tvApellidos = layout.findViewById(R.id.tvApellidos);
        tvCumple = layout.findViewById(R.id.tvCumple);
        tvDireccion = layout.findViewById(R.id.tvDireccion);
        tvID = layout.findViewById(R.id.tvID);

        tvNombre.setText(persona.getNombre());
        tvApellidos.setText(persona.getApellidoCompleto());
        tvCumple.setText(persona.getFechaNacimientoYEdad());
        tvDireccion.setText(persona.getDireccion());
        tvID.setText(persona.getId());

        return layout;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener listener = (IOnAttachListener) context;
        persona = listener.getPersona();
    }
}
